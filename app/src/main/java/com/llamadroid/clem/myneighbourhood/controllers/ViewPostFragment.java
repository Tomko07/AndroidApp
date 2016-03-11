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
import java.util.Locale;
import java.util.UUID;


/**
 * Class handling a single post view.
 */
public class ViewPostFragment extends Fragment
{
    private Post mPost;

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
        TextView titleView = (TextView) view.findViewById(R.id.view_post_title);
        titleView.setText(mPost.getTitle());

        TextView categoryView = (TextView) view.findViewById(R.id.view_post_category);
        categoryView.setText(mPost.getCategory().getCategoryName());

        TextView statusView = (TextView) view.findViewById(R.id.view_post_status);
        statusView.setText(mPost.getCategory().printStatus());

        /* Either display the status of the post if it has changed (the item was sold,
         * what was lost was found, ...) or the category of the post (question, found, event, ...)
         */
        if(mPost.getCategory().getStatus())
            statusView.setVisibility(View.VISIBLE);
        else
            statusView.setVisibility(View.GONE);

        TextView contentView = (TextView) view.findViewById(R.id.view_post_content);
        contentView.setText(mPost.getContent());

        TextView authorView = (TextView) view.findViewById(R.id.view_post_author);
        authorView.setText(mPost.getAuthor().getUserName());
        authorView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(ViewProfileActivity.newIntent(getContext(), mPost.getAuthor()));
            }
        });

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        TextView dateView = (TextView) view.findViewById(R.id.view_post_date);
        dateView.setText(format.format(mPost.getDate()));
    }

    private void inflateCommentButton(View view)
    {
        Button commentButton = (Button) view.findViewById(R.id.button_add_comment);
        commentButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO
            }
        });
    }

    private void inflateReportButton(View view)
    {
        Button reportButton = (Button) view.findViewById(R.id.button_report);
        reportButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO
            }
        });
    }

    private void inflateContactButton(View view)
    {
        Button contactButton = (Button) view.findViewById(R.id.button_contact);
        contactButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO
            }
        });
    }

    private void inflateLocateButton(View view)
    {
        Button locateButton = (Button) view.findViewById(R.id.button_locate);
        locateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO
            }
        });
    }
}
