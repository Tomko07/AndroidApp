package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.SaveSharedPreference;
import com.llamadroid.clem.myneighbourhood.models.CurrentUser;
import com.llamadroid.clem.myneighbourhood.models.User;
import com.llamadroid.clem.myneighbourhood.models.UserMap;

/**
 * Class handling which activity to start,
 * depending on whether the user is still logged in or not.
 */
public class MainActivity extends AppCompatActivity
{
    /** Key for intent extra. */
    private static final String EXTRA_USER = "com.llamadroid.clem.myneighbourhood.user";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = SaveSharedPreference.getUserName(this);

        if(username.length() == 0)
        {
            // User's session closed -> Start welcome activity
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
        else
        {
            if(CurrentUser.getCurrentUser() == null)
            {
                User user = UserMap.get(this).getUser(username);

                if(user == null)
                {
                    // User was not recognised -> Start welcome activity
                    startActivity(new Intent(this, WelcomeActivity.class));
                    finish();
                }
                else
                {
                    CurrentUser.logUserIn(user);
                    // User's session open -> Start home activity
                    startActivity(new Intent(this, HomeActivity.class));
                    finish();
                }
            }
        }
    }
}
