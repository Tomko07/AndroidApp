package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.models.CurrentUser;

/**
 * Class handling user interactions with the home screen.
 */
public class HomeActivity extends AppCompatActivity
{
    /** Key for the intent extra. */
    private static final String CURRENT_USER = "com.llamadroid.clem.myneighbourhood.current_user";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Add toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.app_bar));
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
        Button latestPostsButton = (Button)findViewById(R.id.button_latest_posts);
        latestPostsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(HomeActivity.this, ViewPostListActivity.class));
            }
        });
    }

    private void inflateNewPostButton()
    {
        Button newPostButton = (Button)findViewById(R.id.button_new_post);
        newPostButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(HomeActivity.this, NewPostActivity.class));
            }
        });
    }

    private void inflateBuyButton()
    {
        Button buyButton = (Button)findViewById(R.id.button_buy);
        buyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CurrentUser.logUserOut();
                finish();
            }
        });
    }

    private void inflateSellButton()
    {
        Button sellButton = (Button)findViewById(R.id.button_sell);
        sellButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateBorrowButton()
    {
        Button borrowButton = (Button)findViewById(R.id.button_borrow);
        borrowButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateGiveButton()
    {
        Button giveButton = (Button)findViewById(R.id.button_give);
        giveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateOfferServiceButton()
    {
        Button offerServiceButton = (Button)findViewById(R.id.button_offer_service);
        offerServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void inflateRequestServiceButton()
    {
        Button requestServiceButton = (Button)findViewById(R.id.button_request_service);
        requestServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
