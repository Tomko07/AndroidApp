package com.llamadroid.clem.myneighbourhood.models;


/**
 * Class keeping a reference to the logged in user
 * (instead of passing User objects as extras between activities.
 */
public class CurrentUser
{
    private static User mUser;

    public static User getCurrentUser()
    {
        return mUser;
    }

    public static void logUserIn(User user)
    {
        user.setIsLoggedOn(true);
        mUser = user;
    }

    public static void logUserOut()
    {
        mUser.setIsLoggedOn(false);
        mUser = null;
    }
}
