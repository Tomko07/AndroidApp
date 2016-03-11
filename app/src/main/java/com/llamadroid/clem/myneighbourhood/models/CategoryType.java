package com.llamadroid.clem.myneighbourhood.models;


/**
 * Different types of posts.
 */
public enum CategoryType
{
    Announcement("Announcement", "", "#7D1E49"),
    Borrow("Borrow", "No Longer Required", "#B5A4EB"),
    Buy("Buy", "No Longer Required", "#4d6464"),
    Event("Event", "Full", "#0B7D7E"),
    Found("Found", "Claimed", "#ba121f"),
    Give("Give", "No Longer Available", "#88c921"),
    Lost("Lost", "Retrieved", "#1409BF"),
    Offer_Service("Offer Service", "No Longer Provided", "#6ACEC8"),
    Question("Question", "Answered", "#d520b1"),
    Request_Service("Request Service", "No Longer Required", "#458B00"),
    Sell("Sell", "Sold", "#FF7F24");

    private String mName;
    private String mStatus;
    private String mColor;

    CategoryType(String name, String status, String color)
    {
        this.mName = name;
        this.mStatus = status;
        this.mColor = color;
    }

    public String getName()
    {
        return mName;
    }

    public String getStatus()
    {
        return mStatus;
    }

    public String getColor()
    {
        return mColor;
    }

    @Override
    public String toString()
    {
        return mName;
    }

    public static CategoryType getCategory(String name)
    {
        for(CategoryType type : values())
        {
            if(name.equals(type.getName()))
                return type;
        }
        throw new IllegalArgumentException(name + " does not match any category.");
    }
}
