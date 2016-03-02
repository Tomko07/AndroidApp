package com.llamadroid.clem.myneighbourhood.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserMap
{
    private static UserMap sUserMap;
    private HashMap<String, User> mUsers;


    public static UserMap get(Context context)
    {
        if(sUserMap == null)
           sUserMap = new UserMap(context);
        return sUserMap;
    }

    private UserMap(Context context)
    {
        mUsers = new HashMap<>();

        // Populating the map with fake users
        for(int i = 1; i <= 10; i++)
        {
            User user = new User();
            user.setEmail("" + i+i+i + "@gmail.com");
            user.setFirstName("" + i + "_firstName");
            user.setLastName("" + i + "_lastName");
            user.setPassword("password");
            if(i % 2 == 0)
                user.setPostcode("G12 8PG");
            else
                user.setPostcode("G76 8JB");

            mUsers.put(user.getEmail(), user);
        }
    }


    public boolean contains(String email)
    {
        return mUsers.containsKey(email);
    }

    public List<User> getAllUsers()
    {
        return new ArrayList<User>(mUsers.values());
    }

    public User getUser(String email)
    {
        return mUsers.get(email);
    }

    public void addUser(User user) {
        if(user != null && mUsers.get(user.getEmail()) == null)
            mUsers.put(user.getEmail(), user);
    }

    public void deleteUser(User user)
    {
        if(user != null && this.contains(user.getEmail()))
            mUsers.remove(user.getEmail());
    }
}
