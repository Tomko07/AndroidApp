package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.SaveSharedPreference;
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

        if(SaveSharedPreference.getUserName(this).length() == 0)
        {
            // User's session closed -> Start welcome activity
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        else
        {
            // User's session still open -> get corresponding user object.
            String username = SaveSharedPreference.getUserName(this);
            User user = UserMap.get(this).getUser(username);

            if(user == null)
            {
                // The user was not recognised -> Start welcome activity
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
            }
            else
            {
                // The user was recognised -> Start home activity
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra(EXTRA_USER, user);
                startActivity(intent);
            }
        }
    }
}
