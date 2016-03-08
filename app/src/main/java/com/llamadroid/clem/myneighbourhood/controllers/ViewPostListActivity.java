package com.llamadroid.clem.myneighbourhood.controllers;

import android.support.v4.app.Fragment;


public class ViewPostListActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new ViewPostListFragment();
    }
}
