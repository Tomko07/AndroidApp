package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;


public class ViewPostActivity extends SingleFragmentActivity
{
    private static final String EXTRA_POST_ID =
            "com.llamadroid.clem.myneighbourhood.post_id";


    public static Intent newIntent(Context packageContext, UUID postId)
    {
        Intent intent = new Intent(packageContext, ViewPostActivity.class);
        intent.putExtra(EXTRA_POST_ID, postId);
        return intent;
    }

    @Override
    protected Fragment createFragment()
    {
        UUID postId = (UUID) getIntent().getSerializableExtra(EXTRA_POST_ID);
        return ViewPostFragment.newInstance(postId);
    }
}
