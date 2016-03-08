package com.llamadroid.clem.myneighbourhood.models;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Class representing a post.
 */
public class Post implements Comparable<Post>
{
    /** Random unique id. */
    private UUID mId;
    private Category mCategory;
    private String mTitle;
    private String mContent;
    private Date mDate;
    private User mAuthor;


    public Post()
    {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public Post(Category category, String title, String content, User author)
    {
        mId = UUID.randomUUID();
        mCategory = category;
        mTitle = title;
        mContent = content;
        mDate = new Date();
        mAuthor = author;
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

    public Date getDate()
    {
        return mDate;
    }

    public User getAuthor()
    {
        return mAuthor;
    }


    public void setCategory(Category category)
    {
        mCategory = category;
    }

    public void setTitle(String title)
    {
        mTitle = title;
    }

    public void setContent(String content)
    {
        mContent = content;
    }

    public void setDate(Date date)
    {
        mDate = date;
    }

    public void setAuthor(User author)
    {
        mAuthor = author;
    }


    public int compareTo(@NonNull Post other)
    {
        if(this.mDate.after(other.mDate))
            return -1;
        else if(this.mDate.before(other.mDate))
            return 1;
        else
            return this.getTitle().compareTo(other.getTitle());
    }
}
