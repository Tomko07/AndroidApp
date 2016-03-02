package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.SaveSharedPreference;
import com.llamadroid.clem.myneighbourhood.models.User;

/**
 * Class handling user interactions with the welcoming screen.
 */
public class WelcomeActivity extends AppCompatActivity
{
    /** Request code for LoginActivity. */
    private static final int REQUEST_CODE_LOGIN = 0;
    /** Request code for RegisterActivity. */
    private static final int REQUEST_CODE_REGISTER = 1;
    /** Key for the intent extra. */
    public static final String EXTRA_USER =
            "com.llamadroid.clem.myneighbourhood.user";

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
        if(resultCode == RESULT_OK && data != null)
        {
            User user;
            switch(requestCode)
            {
                // User logged in
                case REQUEST_CODE_LOGIN:
                    user = LoginActivity.getLoggedInUser(data);
                    break;
                // User registered
                case REQUEST_CODE_REGISTER:
                    user = RegisterActivity.getLoggedInUser(data);
                    break;
                default:
                    return;
            }

            // Save user's email address to restore his/her session later
            SaveSharedPreference.setUserName(this, user.getEmail());

            // Start HomeActivity and stop this activity
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(EXTRA_USER, user);
            startActivity(intent);
            finish();
        }
    }

    /* To be used by other activities to retrieve user object. */
    public static User getLoggedInUser(Intent result)
    {
        return (User)result.getSerializableExtra(EXTRA_USER);
    }
}
