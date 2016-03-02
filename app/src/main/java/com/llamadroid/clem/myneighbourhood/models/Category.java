package com.llamadroid.clem.myneighbourhood.models;


public class Category
{
    private PostType.Category mCategory;
    private boolean mStatus;


    public Category(PostType.Category category)
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
        switch(mCategory)
        {
            case Borrow:
            case Buy:
            case ServiceRequest:
                return "No Longer Required";
            case Event:
                return "Full";
            case Found:
                return "Retrieved";
            case Give:
            case Sell:
            case ServiceOffer:
                return "No Longer Available";
            case Lost:
                return "Found";
            case Question:
                return "Answered";
            default:
                return "";
        }
    }

}
