package com.llamadroid.clem.myneighbourhood.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.ToolbarOptions;
import com.llamadroid.clem.myneighbourhood.models.CurrentUser;
import com.llamadroid.clem.myneighbourhood.models.User;
import com.llamadroid.clem.myneighbourhood.models.UserMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;


public class EditProfileActivity extends AppCompatActivity
{
    private User mUser;

    private EditText mFnameField, mLnameField, mEmailField, mPostcodeField;
    private EditText mAddressField, mPhoneField, mDobField, mAboutField;

    private Spinner mGenderSpin;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Add toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.app_bar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUser = CurrentUser.getCurrentUser();

        inflateTextFields();
        inflateSpinner();
        inflateTextViews();
        inflateSaveButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return ToolbarOptions.getOption(item.getItemId(), this)
                || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    private void inflateTextFields()
    {
        mFnameField = (EditText) findViewById(R.id.field_profile_fname);
        mFnameField.setText(mUser.getFirstName());

        mLnameField = (EditText) findViewById(R.id.field_profile_lname);
        mLnameField.setText(mUser.getLastName());

        mEmailField = (EditText) findViewById(R.id.field_profile_email);
        mEmailField.setText(mUser.getEmail());

        mPostcodeField = (EditText) findViewById(R.id.field_profile_postcode);
        mPostcodeField.setText(mUser.getPostcode());

        mAddressField = (EditText) findViewById(R.id.field_profile_address);
        mAddressField.setText(mUser.getAddress());

        mPhoneField = (EditText) findViewById(R.id.field_profile_phone);
        mPhoneField.setText(mUser.getPhone());

        mDobField = (EditText) findViewById(R.id.field_profile_dob);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        mDobField.setText(mUser.getDOB() == null ? "" : format.format(mUser.getDOB()));

        mAboutField = (EditText) findViewById(R.id.field_profile_about);
        mAboutField.setText(mUser.getDescription());
    }

    private void inflateSpinner()
    {
        mGenderSpin = (Spinner)findViewById(R.id.spinner_profile_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items_gender, R.layout.spinner_profile_gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderSpin.setAdapter(adapter);
    }

    private void inflateTextViews()
    {
        TextView mStatusView = (TextView) findViewById(R.id.edit_profile_status);
        mStatusView.setText(mUser.isVerified() ? "Status: Verified" : "Status: Unverified");

        TextView mVerifyView = (TextView) findViewById(R.id.edit_profile_verify);
        mVerifyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });
    }

    private void inflateSaveButton()
    {
        Button saveButton = (Button) findViewById(R.id.button_edit_profile_save);
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String fname = mFnameField.getText().toString();
                if(!"".equals(fname) && !mUser.getFirstName().equals(fname))
                    mUser.setFirstName(fname);

                String lname = mLnameField.getText().toString();
                if(!"".equals(lname) && !mUser.getLastName().equals(lname))
                    mUser.setLastName(lname);

                String email = mEmailField.getText().toString();
                if(!"".equals(email) && !mUser.getEmail().equals(email))
                    if(!emailIsValid(email))
                    {
                        printToast("Invalid email.");
                        return;
                    }
                    else
                        mUser.setEmail(email);

                String postcode = mPostcodeField.getText().toString();
                if(!"".equals(postcode) && !mUser.getPostcode().equals(postcode))
                    if(!postcodeIsValid(postcode))
                    {
                        printToast("Invalid postcode.");
                        return;
                    }
                    else
                    {
                        mUser.setPostcode(postcode);
                        mUser.setVerified(false);
                    }

                String address = mAddressField.getText().toString();
                mUser.setAddress(address);

                String phone = mPhoneField.getText().toString();
                mUser.setPhone(phone);

                String dateStr = mDobField.getText().toString();
                if(!"".equals(dateStr))
                {
                    try
                    {
                        Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.UK).parse(dateStr);
                        if(date.before(new Date()))
                            mUser.setDOB(date);
                    }
                    catch (ParseException pe)
                    {
                        Log.e("EditProfileActivity", "ParseException caught", pe);
                    }
                }
                else
                    mUser.setDOB(null);

                String about = mAboutField.getText().toString();
                mUser.setDescription(about);

                String gender = mGenderSpin.getSelectedItem().toString();
                if(!gender.equals("Gender"))
                    mUser.setGender(gender.charAt(0));

                printToast("Changes saved.");
            }
        });
    }

    private void printToast(String errorMessage)
    {
        Toast.makeText(this, errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    private boolean emailIsValid(String email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && UserMap.get(this).getUser(email) == null;
    }

    private boolean postcodeIsValid(String postcode)
    {
        String regExpUK = "(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|" +
                "(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKPSTUW])|" +
                "([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) [0-9][A-Z-[CIKMOV]]{2})";
        return !"".equals(postcode) && Pattern.matches(regExpUK, postcode);
    }
}
