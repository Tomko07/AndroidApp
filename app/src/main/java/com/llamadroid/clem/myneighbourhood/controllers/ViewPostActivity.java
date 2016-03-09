package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.llamadroid.clem.myneighbourhood.R;

import java.util.UUID;


public class ViewPostActivity extends AppCompatActivity
{
    private static final String EXTRA_POST_ID =
            "com.llamadroid.clem.myneighbourhood.post_id";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // Add toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.app_bar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.activity_fragment);

        if (fragment == null)
        {
            UUID postId = (UUID) getIntent().getSerializableExtra(EXTRA_POST_ID);
            fragment = ViewPostFragment.newInstance(postId);
            manager.beginTransaction().add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context packageContext, UUID postId)
    {
        Intent intent = new Intent(packageContext, ViewPostActivity.class);
        intent.putExtra(EXTRA_POST_ID, postId);
        return intent;
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
}
