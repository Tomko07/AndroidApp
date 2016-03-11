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
import com.llamadroid.clem.myneighbourhood.SaveSharedPreference;
import com.llamadroid.clem.myneighbourhood.ToolbarOptions;
import com.llamadroid.clem.myneighbourhood.models.CurrentUser;

/**
 * Class handling user interactions with the home screen.
 */
public class HomeActivity extends AppCompatActivity
{
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
                // TODO
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
                // TODO
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
                // TODO
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
                // TODO
            }
        });
    }

    private void inflateOfferServiceButton()
    {
        Button offerServiceButton = (Button)findViewById(R.id.button_offer_service);
        offerServiceButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO
            }
        });
    }

    private void inflateRequestServiceButton()
    {
        Button requestServiceButton = (Button)findViewById(R.id.button_request_service);
        requestServiceButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO
            }
        });
    }
}
