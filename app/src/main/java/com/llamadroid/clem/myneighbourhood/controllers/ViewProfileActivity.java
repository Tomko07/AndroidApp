package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.SaveSharedPreference;
import com.llamadroid.clem.myneighbourhood.ToolbarOptions;
import com.llamadroid.clem.myneighbourhood.models.CurrentUser;
import com.llamadroid.clem.myneighbourhood.models.User;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ViewProfileActivity extends AppCompatActivity
{
    private static final String EXTRA_USER =
        "com.llamadroid.clem.myneighbourhood.user";

    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        // Add toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.app_bar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setIcon(R.drawable.logo);

        mUser = (User) getIntent().getSerializableExtra(EXTRA_USER);

        inflateTextViews();
        inflateContactButton();
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


    private void inflateTextViews()
    {
        TextView usernameView = (TextView) findViewById(R.id.view_profile_username);
        usernameView.setText(mUser.getUserName());

        TextView genderView = (TextView) findViewById(R.id.view_profile_gender);
        genderView.setText(mUser.getGender() == 'f' ? "Female" : "Male");
        genderView.setVisibility(View.INVISIBLE);

        TextView emailView = (TextView) findViewById(R.id.view_profile_email);
        emailView.setText(mUser.getEmail());
        emailView.setVisibility(View.INVISIBLE);

        TextView postcodeView = (TextView) findViewById(R.id.view_profile_postcode);
        postcodeView.setText(mUser.getPostcode());

        TextView phoneView = (TextView) findViewById(R.id.view_profile_phone);
        phoneView.setText(mUser.getPhone());
        phoneView.setVisibility(View.INVISIBLE);

        TextView addressView = (TextView) findViewById(R.id.view_profile_address);
        addressView.setText(mUser.getAddress());
        addressView.setVisibility(View.INVISIBLE);

        TextView statusView = (TextView) findViewById(R.id.view_profile_status);
        statusView.setText(mUser.isVerified() ? "Verified" : "Unverified");

        TextView dobView = (TextView) findViewById(R.id.view_profile_dob);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        dobView.setText(mUser.getDOB() == null ? "" : format.format(mUser.getDOB()));
        dobView.setVisibility(View.INVISIBLE);

        TextView aboutView = (TextView) findViewById(R.id.view_profile_about);
        aboutView.setText(mUser.getDescription());
    }

    private void inflateContactButton()
    {
        Button contactButton = (Button) findViewById(R.id.button_profile_contact);
        contactButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO
            }
        });
    }

    public static Intent newIntent(Context packageContext, User user)
    {
        Intent intent = new Intent(packageContext, ViewProfileActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

}
