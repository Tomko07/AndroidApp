package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.SaveSharedPreference;
import com.llamadroid.clem.myneighbourhood.models.CurrentUser;
import com.llamadroid.clem.myneighbourhood.models.User;
import com.llamadroid.clem.myneighbourhood.models.UserMap;

import java.util.regex.Pattern;

/**
 * Class handling user interactions with the register screen.
 */
public class RegisterActivity extends AppCompatActivity
{
    /** Text fields containing the user's email address and password. */
    private EditText mEmailField, mPasswordField, mConfirmPasswordField;

    /** Text fields containing the user's postcode and first and last names. */
    private EditText mFNameField, mLNameField, mPostcodeField;

    /** Key for the intent extra. */
    private static final String NEW_USER = "com.llamadroid.clem.myneighbourhood.new_user";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inflating widgets and setting listeners to buttons.
        inflateCancelButton();
        inflateTextFields();
        inflateRegisterButton();
    }

    /**
     * When cancel button is pressed, pass the result to WelcomeActivity,
     * and stop this activity.
     */
    private void inflateCancelButton()
    {
        Button cancelButton = (Button)findViewById(R.id.button_registration_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void inflateTextFields()
    {
        mEmailField = (EditText)findViewById(R.id.field_register_email);
        mPasswordField = (EditText)findViewById(R.id.field_register_password);
        mConfirmPasswordField = (EditText)findViewById(R.id.field_confirm_password);
        mFNameField = (EditText)findViewById(R.id.field_register_fname);
        mLNameField = (EditText)findViewById(R.id.field_register_lname);
        mPostcodeField = (EditText)findViewById(R.id.field_register_postcode);
    }

    /**
     * When register button is pressed,
     * - check the validity of the email (not empty and with the correct format)
     * - check the validity of the password (not empty and identical to the confirmed one)
     * - check the validity of the postcode
     * - if correct, pass the user object to WelcomeActivity and stop this activity
     * - otherwise, print error message in a Toast.
     */
    private void inflateRegisterButton()
    {
        Button registerButton = (Button)findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = mEmailField.getText().toString();

                if(!emailIsValid(email))
                {
                    printErrorToast("Invalid email address.");
                    mEmailField.setText("");
                    return;
                }

                String postcode = mPostcodeField.getText().toString();

                if(!postcodeIsValid(postcode))
                {
                    printErrorToast("Invalid postcode.");
                    mPostcodeField.setText("");
                    return;
                }

                String password = mPasswordField.getText().toString();

                if (!password.equals(mConfirmPasswordField.getText().toString()))
                {
                    printErrorToast("The passwords don't match.");
                    mPasswordField.setText("");
                    mConfirmPasswordField.setText("");
                }
                else
                {
                    String firstName = mFNameField.getText().toString();
                    String lastName = mLNameField.getText().toString();

                    User user = new User(email, firstName, lastName, password, postcode);
                    UserMap.get(RegisterActivity.this).addUser(user);
                    CurrentUser.logUserIn(UserMap.get(RegisterActivity.this).getUser(user.getEmail()));

                    // Save user's email address to restore his/her session later
                    SaveSharedPreference.setUserName(RegisterActivity.this, user.getEmail());

                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    private void printErrorToast(String errorMessage)
    {
        Toast.makeText(this, errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    private boolean emailIsValid(String email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && UserMap.get(RegisterActivity.this).getUser(email) == null;
    }

    private boolean postcodeIsValid(String postcode)
    {
        String regExpUK = "(GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|" +
                "(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKPSTUW])|" +
                "([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) [0-9][A-Z-[CIKMOV]]{2})";
        return !"".equals(postcode) && Pattern.matches(regExpUK, postcode);
    }


    /**
     * Creates intent to be used by other activities.
     */
    public static Intent newIntent(Context packageContext)
    {
        return new Intent(packageContext, RegisterActivity.class);
    }

    /**
     * To be used by other activities to retrieve user object.
     */
    public static User getLoggedInUser(Intent result)
    {
        return (User)result.getSerializableExtra(NEW_USER);
    }
}
