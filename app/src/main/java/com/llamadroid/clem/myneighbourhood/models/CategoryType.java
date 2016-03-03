package com.llamadroid.clem.myneighbourhood.models;


public enum CategoryType
{
    Announcement("Announcement", ""),
    Borrow("Borrow", "No Longer Required"),
    Buy("Buy", "No Longer Required"),
    Event("Event", "Full"),
    Found("Found", "Retrieved"),
    Give("Give", "No Longer Available"),
    Lost("Lost", "Found"),
    OfferService("Offer Service", "No Longer Provided"),
    Question("Question", "Answered"),
    RequestService("Request Service", "No Longer Required"),
    Sell("Sell", "Sold");

    private String mName;
    private String mStatus;

    CategoryType(String name, String status)
    {
        this.mName = name;
        this.mStatus = status;
    }

    public String getName()
    {
        return mName;
    }

    public String getStatus()
    {
        return mStatus;
    }
}
