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

    public boolean getStatus()
    {
        return mStatus;
    }

    public void setStatus(boolean status)
    {
        mStatus = status;
    }

    public String printStatus()
    {
        return mCategory.getStatus();
    }

}
