package com.llamadroid.clem.myneighbourhood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.llamadroid.clem.myneighbourhood.controllers.EditProfileActivity;
import com.llamadroid.clem.myneighbourhood.controllers.HomeActivity;
import com.llamadroid.clem.myneighbourhood.controllers.WelcomeActivity;
import com.llamadroid.clem.myneighbourhood.models.CurrentUser;


public class ToolbarOptions
{
    public static boolean getOption(int itemID, Context context)
    {
        switch(itemID)
        {
            case R.id.menu_item_log_out:
                CurrentUser.logUserOut();
                SaveSharedPreference.clearUserName(context);
                context.startActivity(new Intent(context, WelcomeActivity.class));
                ((Activity)context).finish();
            case R.id.menu_item_profile:
                if(!((Activity)context).getClass().equals(EditProfileActivity.class))
                    context.startActivity(new Intent(context, EditProfileActivity.class));
                return true;
            case R.id.menu_item_messages:
                // Start messages activity
                return true;
            case R.id.menu_item_home:
                if(!((Activity)context).getClass().equals(HomeActivity.class))
                {
                    context.startActivity(new Intent(context, HomeActivity.class));
                    ((Activity) context).finish();
                }
                return true;
            case R.id.menu_item_posts:
                // Start posts activity
                return true;
            case R.id.menu_item_settings:
                // Start settings activity
                return true;
            case R.id.menu_item_request_category:
                // Start request category activity
                return true;
            default:
                return false;
        }
    }
}
