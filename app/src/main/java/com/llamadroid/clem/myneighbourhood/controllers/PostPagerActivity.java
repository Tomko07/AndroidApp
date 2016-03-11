package com.llamadroid.clem.myneighbourhood.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.SaveSharedPreference;
import com.llamadroid.clem.myneighbourhood.ToolbarOptions;
import com.llamadroid.clem.myneighbourhood.models.CurrentUser;
import com.llamadroid.clem.myneighbourhood.models.Post;
import com.llamadroid.clem.myneighbourhood.models.PostSet;

import java.util.List;
import java.util.UUID;


/**
 * Class displaying creating fragments to view posts,
 * and swipe from a post to another.
 */
public class PostPagerActivity extends AppCompatActivity
{
    private static final String EXTRA_CRIME_ID =
            "com.llamadroid.clem.myneighbourhood.post_id";

    private List<Post> mPosts;


    public static Intent newIntent(Context packageContext, UUID postId)
    {
        Intent intent = new Intent(packageContext, PostPagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, postId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_pager);

        // Add toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.app_bar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Id of the post selected in the list of posts.
        UUID postID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        ViewPager viewPager = (ViewPager) findViewById(R.id.activity_post_pager_view_pager);
        mPosts = PostSet.get(this).getAllPosts();

        FragmentManager manager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(manager)
        {
            @Override
            public Fragment getItem(int position)
            {
                Post post = mPosts.get(position);
                return ViewPostFragment.newInstance(post.getId());
            }

            @Override
            public int getCount()
            {
                return mPosts.size();
            }
        });

        for(int i = 0; i < mPosts.size(); i++)
        {
            if(mPosts.get(i).getId().equals(postID))
            {
                viewPager.setCurrentItem(i);
                break;
            }
        }
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
}
