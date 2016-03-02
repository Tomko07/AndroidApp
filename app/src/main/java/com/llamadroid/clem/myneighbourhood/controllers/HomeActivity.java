package com.llamadroid.clem.myneighbourhood.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.models.User;

public class HomeActivity extends AppCompatActivity
{
    private Toolbar mToolbar;

    private Button mLatestPostsButton, mNewPostButton;

    private Button mBuyButton, mSellButton, mBorrowButton, mGiveButton,
                    mOfferServiceButton, mRequestServiceButton;

    private static final String CURRENT_USER =
            "com.llamadroid.clem.myneighbourhood.current_user";

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(savedInstanceState != null)
            mUser = (User)savedInstanceState.getSerializable(CURRENT_USER);
        else
            mUser = WelcomeActivity.getLoggedInUser(getIntent());

        // Add toolbar
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setIcon(R.drawable.logo);

        // Inflate widgets and add listeners to buttons.
        inflateLatestPostsButton();
        inflateNewPostButton();
        inflateBuyButton();
        inflateSellButton();
        inflateBorrowButton();
        inflateGiveButton();
        inflateOfferServiceButton();
        inflateRequestServiceButton();


/* FOR CHILD ACTIVITIES
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDefaultDisplayHomeAsUpEnabled();
        */
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(CURRENT_USER, mUser);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        mUser = (User) savedInstanceState.getSerializable(CURRENT_USER);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_item_profile:
                // Start profile activity
                return true;
            case R.id.menu_item_messages:
                // Start messages activity
                return true;
            case R.id.menu_item_home:
                // Already on the home screen -> do nothing.
                return true;
            case R.id.menu_item_posts:
                // Start posts activity
                return true;
            case R.id.menu_item_settings:
                // Start settings activity
                return true;
            case R.id.menu_item_request_category:
                // Start request category activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }


    private void inflateLatestPostsButton()
    {
        mLatestPostsButton = (Button)findViewById(R.id.button_latest_posts);
        mLatestPostsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Start latest posts activity
            }
        });
    }

    private void inflateNewPostButton()
    {
        mNewPostButton = (Button)findViewById(R.id.button_new_post);
        mNewPostButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Start new post activity
            }
        });
    }

    private void inflateBuyButton()
    {
        mBuyButton = (Button)findViewById(R.id.button_buy);
        mBuyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateSellButton()
    {
        mSellButton = (Button)findViewById(R.id.button_sell);
        mSellButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateBorrowButton()
    {
        mBorrowButton = (Button)findViewById(R.id.button_borrow);
        mBorrowButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateGiveButton()
    {
        mGiveButton = (Button)findViewById(R.id.button_give);
        mGiveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateOfferServiceButton()
    {
        mOfferServiceButton = (Button)findViewById(R.id.button_offer_service);
        mOfferServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void inflateRequestServiceButton()
    {
        mRequestServiceButton = (Button)findViewById(R.id.button_request_service);
        mRequestServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
