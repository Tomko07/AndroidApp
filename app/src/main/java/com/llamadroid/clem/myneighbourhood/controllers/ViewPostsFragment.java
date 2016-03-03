package com.llamadroid.clem.myneighbourhood.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


public class ViewPostsFragment extends Fragment
{
    private RecyclerView mPostRecyclerView;
    private PostAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_view_posts,
                container, false);
        mPostRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_posts);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI()
    {
        PostSet postSet = PostSet.get(getActivity());
        List<Post> posts = new ArrayList<>(postSet.getAllPosts());

        mAdapter = new PostAdapter(posts);
        mPostRecyclerView.setAdapter(mAdapter);
    }

    private class PostHolder extends RecyclerView.ViewHolder
    {
        private TextView mTitleTextView;
        private TextView mAuthorTextView;
        private TextView mDateTextView;
        private TextView mStatusTextView;

        private Post mPost;

        public PostHolder(View itemView)
        {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_post_title);
            mAuthorTextView = (TextView) itemView.findViewById(R.id.list_item_post_author);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_post_date);
            mStatusTextView = (TextView)itemView.findViewById(R.id.list_item_post_status);
        }

        public void bindPost(Post post)
        {
            mPost = post;
            mTitleTextView.setText(mPost.getTitle());
            mAuthorTextView.setText(mPost.getAuthor().getEmail()); // CHANGE TO USERNAME
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            mDateTextView.setText(format.format(post.getDate()));
            mStatusTextView.setText(post.getCategory().printStatus());
            if(post.getCategory().getStatus())
                mStatusTextView.setVisibility(View.VISIBLE);
        }
    }

    private class PostAdapter extends RecyclerView.Adapter<PostHolder>
    {
        private List<Post> mPosts;

        public PostAdapter(List<Post> posts)
        {
            mPosts = posts;
            Log.d("LIST", "" + mPosts.size());
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
