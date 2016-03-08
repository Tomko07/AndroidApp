package com.llamadroid.clem.myneighbourhood.models;


/**
 * Class representing the category of a post.
 */
public class Category
{
    private CategoryType mCategory;
    private boolean mStatus;


    public Category(CategoryType category)
    {
        mCategory = category;
        mStatus = false;
    }

    public String getCategoryName()
    {
        return mCategory.getName();
    }

    public boolean getStatus()
    {
        return mStatus;
    }

    public void setStatus(boolean status)
    {
        mStatus = status;
    }

    public String getColor()
    {
        return mCategory.getColor();
    }

    public String printStatus()
    {
        return mCategory.getStatus();
    }

}
