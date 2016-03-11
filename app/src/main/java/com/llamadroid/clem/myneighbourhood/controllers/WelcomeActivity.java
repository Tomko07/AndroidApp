package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.llamadroid.clem.myneighbourhood.R;


/**
 * Class handling user interactions with the welcoming screen.
 */
public class WelcomeActivity extends AppCompatActivity
{
    /** Request code for LoginActivity. */
    private static final int REQUEST_CODE_LOGIN = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Button to start login process.
        Button loginButton = (Button)findViewById(R.id.button_welcome_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start login activity
                Intent intent = LoginActivity.newIntent(WelcomeActivity.this);
                startActivityForResult(intent, REQUEST_CODE_LOGIN);
            }
        });

        // Button to start registration process.
        Button newAccountButton = (Button)findViewById(R.id.button_welcome_new_account);
        newAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start register activity
                Intent intent = RegisterActivity.newIntent(WelcomeActivity.this);
                startActivityForResult(intent, REQUEST_CODE_LOGIN);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // If user confirmed login or registration process, retrieve the corresponding user object
        if(resultCode == RESULT_OK)
        {
            // Start HomeActivity and stop this activity
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}
