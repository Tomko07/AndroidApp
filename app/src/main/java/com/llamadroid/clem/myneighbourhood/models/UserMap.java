package com.llamadroid.clem.myneighbourhood.models;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * Singleton class storing the different registered users.
 */
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

    // Extracting hard coded users from txt file.
    private UserMap(Context context)
    {
        mUsers = new HashMap<>();

        InputStreamReader reader = null;
        Scanner scanner = null;

        try
        {
            try {
                reader = new InputStreamReader(context.getResources().getAssets().open("Users.txt"));
                scanner = new Scanner(reader);

                while (scanner.hasNextLine())
                {
                    String[] info = scanner.nextLine().split(" ");
                    int index = 0;
                    User user = new User();
                    user.setFirstName(info[index++]);
                    user.setLastName(info[index++]);
                    user.setEmail(info[index++]);
                    user.setPassword(info[index++]);
                    user.setPostcode(info[index++] + " " + info[index]);
                    user.setIsLoggedOn(false);
                    mUsers.put(user.getEmail(), user);
                }
            }
            finally
            {
                if(scanner != null)
                    scanner.close();
                if(reader != null)
                    reader.close();
            }
        }
        catch(IOException ioe)
        {
            Log.e("UserMap", "IOException caught", ioe);
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
