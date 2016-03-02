package com.llamadroid.clem.myneighbourhood.models;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Post
{
    private UUID mId;
    private Category mCategory;
    private String mTitle;
    private String mContent;
    //private Set<PostType.Tags> mTags;
    private List<File> mPhotos;
    private Date mDate;


    public Post(Category category, String title, String content, List<File> photos,
                User author)
    {
        mId = UUID.randomUUID();
        mCategory = category;
        mTitle = title;
        mContent = content;
        //mTags = tags;
        mPhotos = photos;
        mDate = new Date();
    }

    public UUID getId()
    {
        return mId;
    }

    public Category getCategory()
    {
        return mCategory;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public String getContent()
    {
        return mContent;
    }

    /*public Set<PostType.Tags> getTags()
    {
        return mTags;
    }*/

    public List<File> getPhotos()
    {
        return mPhotos;
    }

    public Date getDate()
    {
        return mDate;
    }
}
