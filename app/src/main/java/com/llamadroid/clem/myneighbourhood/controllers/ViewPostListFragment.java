package com.llamadroid.clem.myneighbourhood.controllers;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.models.Post;
import com.llamadroid.clem.myneighbourhood.models.PostSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Class displaying the list of posts.
 */
public class ViewPostListFragment extends Fragment
{
    private RecyclerView mPostRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_view_post_list, container, false);

        mPostRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_posts);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    // Update list view when the user slides the screen.
    private void updateUI()
    {
        PostSet postSet = PostSet.get(getActivity());
        List<Post> posts = new ArrayList<>(postSet.getAllPosts());

        PostAdapter adapter = new PostAdapter(posts);
        mPostRecyclerView.setAdapter(adapter);
    }

    // Holds a view for a single post.
    private class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mTitleTextView;
        private TextView mAuthorTextView;
        private TextView mDateTextView;
        private TextView mStatusTextView;
        private TextView mCategoryTextView;

        private Post mPost;

        public PostHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);

            // Inflating the TextView widgets.
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_post_title);
            mAuthorTextView = (TextView) itemView.findViewById(R.id.list_item_post_author);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_post_date);
            mStatusTextView = (TextView)itemView.findViewById(R.id.list_item_post_status);
            mCategoryTextView = (TextView) itemView.findViewById(R.id.list_item_post_category);
        }

        // Binds the post's data to its view
        public void bindPost(Post post)
        {
            mPost = post;

            mTitleTextView.setText(mPost.getTitle());
            mAuthorTextView.setText(mPost.getAuthor().getUserName());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
            mDateTextView.setText(format.format(post.getDate()));
            mStatusTextView.setText(post.getCategory().printStatus());
            mStatusTextView.setTextColor(Color.parseColor(post.getCategory().getColor()));
            mCategoryTextView.setText(post.getCategory().getCategoryName());
            mCategoryTextView.setTextColor(Color.parseColor(post.getCategory().getColor()));
            /* Either display the status of the post if it has changed (the item was sold,
            * what was lost was found, ...) or the category of the post (question, found, event, ...)
            */
            if(post.getCategory().getStatus())
            {
                mStatusTextView.setVisibility(View.VISIBLE);
                mCategoryTextView.setVisibility(View.INVISIBLE);
            }
            else
            {
                mStatusTextView.setVisibility(View.INVISIBLE);
                mCategoryTextView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View view)
        {
            startActivity(PostPagerActivity.newIntent(getActivity(), mPost.getId()));
        }
    }

    // Creates PostHolders when needed and binds them to the corresponding Post.
    private class PostAdapter extends RecyclerView.Adapter<PostHolder>
    {
        private List<Post> mPosts;

        public PostAdapter(List<Post> posts)
        {
            mPosts = posts;
        }

        @Override
        public PostHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_post, parent, false);
            return new PostHolder(view);
        }

        @Override
        public void onBindViewHolder(PostHolder holder, int position)
        {
            Post post = mPosts.get(position);
            holder.bindPost(post);
        }

        @Override
        public int getItemCount()
        {
            return mPosts.size();
        }
    }
}
