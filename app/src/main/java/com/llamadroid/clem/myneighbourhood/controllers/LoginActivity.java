package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.llamadroid.clem.myneighbourhood.models.User;
import com.llamadroid.clem.myneighbourhood.models.UserMap;
import com.llamadroid.clem.myneighbourhood.R;


/**
 * Class handling user interactions with the login screen.
 */
public class LoginActivity extends AppCompatActivity
{
    /** Text fields containing the user's email address and password. */
    private EditText mEmailField, mPasswordField;

    /** Key for the intent extra. */
    private static final String USER = "com.llamadroid.clem.myneighbourhood.new_user";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inflating widgets and setting listeners to buttons.
        inflateCancelButton();
        inflateTextFields();
        inflateLoginButton();
        inflateForgotButton();
    }

    /**
     * When cancel button is pressed, pass the result to WelcomeActivity,
     * and stop this activity.
     */
    private void inflateCancelButton()
    {
        Button cancelButton = (Button)findViewById(R.id.button_login_cancel);
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
        mEmailField = (EditText) findViewById(R.id.field_login_email);
        mPasswordField = (EditText) findViewById(R.id.field_login_password);
    }

    /**
     * When login button is pressed,
     * - check the validity of the email (not empty and existing in the database)
     * - check the validity of the password (not empty and matching the one in the database)
     * - if both are correct, pass the user object to WelcomeActivity and stop this activity
     * - otherwise, print error message in a Toast.
     */
    private void inflateLoginButton()
    {
        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();

                // Checking that the input is valid and matches a user in the database
                if (emailIsValid(email))
                {
                    if (passwordIsValid(email, password))
                    {
                        User user = UserMap.get(LoginActivity.this).getUser(email);
                        user.setIsLoggedOn(true);

                        // Passing the User object to the WelcomeActivity
                        Intent intent = new Intent();
                        intent.putExtra(USER, user);
                        setResult(RESULT_OK, intent);

                        finish();
                    }
                    else
                    {
                        printErrorToast("Invalid password");
                        mPasswordField.setText("");
                    }
                }
                else
                {
                    printErrorToast("Invalid email");
                    mEmailField.setText("");
                    mPasswordField.setText("");
                }
            }
        });
    }

    /**
     * When forgot button is pressed, start ForgotPasswordActivity and stop this activity.
     */
    private void inflateForgotButton()
    {
        Button forgotButton = (Button) findViewById(R.id.button_forgot_password);
        forgotButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                printErrorToast("Forgot Password"); // REMOVE
                mPasswordField.setText("");
                // Start ForgotPasswordActivity
            }
        });
    }

    private void printErrorToast(String errorMessage)
    {
        Toast.makeText(LoginActivity.this, errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    private boolean inputIsValid(String input)
    {
        return input != null && !"".equals(input);
    }

    private boolean emailIsValid(String email)
    {
        return inputIsValid(email) && UserMap.get(this).contains(email);
    }

    private boolean passwordIsValid(String email, String password)
    {
        if (inputIsValid(password))
        {
            String rightPassword = UserMap.get(this).getUser(email).getPassword();
            return rightPassword.equals(password);
        }
        return  false;
    }

    /**
     * Creates intent to be used by other activities.
     */
    public static Intent newIntent(Context packageContext)
    {
        return new Intent(packageContext, LoginActivity.class);
    }

    /**
     * To be used by other activities to retrieve user object.
     */
    public static User getLoggedInUser(Intent result)
    {
        return (User)result.getSerializableExtra(USER);
    }
}