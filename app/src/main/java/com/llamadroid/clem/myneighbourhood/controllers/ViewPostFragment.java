package com.llamadroid.clem.myneighbourhood.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.models.Post;
import com.llamadroid.clem.myneighbourhood.models.PostSet;

import java.text.SimpleDateFormat;
import java.util.UUID;


public class ViewPostFragment extends Fragment
{
    private Post mPost;

    private TextView mTitleView, mCategoryView, mStatusView, mContentView, mAuthorView, mDateView;
    private Button mCommentButton, mReportButton, mContactButton, mLocateButton;

    private static final String ARG_POST_ID = "post_id";
    
    public static ViewPostFragment newInstance(UUID postId)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_POST_ID, postId);
        ViewPostFragment fragment = new ViewPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        UUID postId = (UUID) getArguments().getSerializable(ARG_POST_ID);
        mPost = PostSet.get(getActivity()).getPost(postId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);
        inflateTextViews(view);
        inflateCommentButton(view);
        inflateReportButton(view);
        inflateContactButton(view);
        inflateLocateButton(view);
        return view;
    }

    private void inflateTextViews(View view)
    {
        mTitleView = (TextView) view.findViewById(R.id.view_post_title);
        mTitleView.setText(mPost.getTitle());

        mCategoryView = (TextView) view.findViewById(R.id.view_post_category);
        mCategoryView.setText(mPost.getCategory().getCategoryName());

        mStatusView = (TextView) view.findViewById(R.id.view_post_status);
        mStatusView.setText(mPost.getCategory().printStatus());
        if(mPost.getCategory().getStatus())
            mStatusView.setVisibility(View.VISIBLE);
        else
            mStatusView.setVisibility(View.GONE);

        mContentView = (TextView) view.findViewById(R.id.view_post_content);
        mContentView.setText(mPost.getContent());

        mAuthorView = (TextView) view.findViewById(R.id.view_post_author);
        mAuthorView.setText(mPost.getAuthor().getUserName());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        mDateView = (TextView) view.findViewById(R.id.view_post_date);
        mDateView.setText(format.format(mPost.getDate()));
    }

    private void inflateCommentButton(View view)
    {
        mCommentButton = (Button) view.findViewById(R.id.button_add_comment);
        mCommentButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateReportButton(View view)
    {
        mReportButton = (Button) view.findViewById(R.id.button_report);
        mReportButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateContactButton(View view)
    {
        mContactButton = (Button) view.findViewById(R.id.button_contact);
        mContactButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void inflateLocateButton(View view)
    {
        mLocateButton = (Button) view.findViewById(R.id.button_locate);
        mLocateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}
