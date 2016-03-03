package com.llamadroid.clem.myneighbourhood.models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;


public class PostSet
{
    private static PostSet sPostList;
    private HashSet<Post> mPosts;

    public static PostSet get(Context context)
    {
        if(sPostList == null)
            sPostList = new PostSet(context);
        return sPostList;
    }

    private PostSet(Context context)
    {
        mPosts = new HashSet<>();

        User[] users = new User[3];
        users[0] = UserMap.get(context).getUser("111@gmail.com");
        users[1] = UserMap.get(context).getUser("222@gmail.com");
        users[2] = UserMap.get(context).getUser("333@gmail.com");

        Category[] categories = new Category[3];
        categories[0] = new Category(CategoryType.Announcement);
        categories[1] = new Category(CategoryType.RequestService);
        categories[2] = new Category(CategoryType.Found);

        // Populating the map with fake posts
        for(int i = 1; i <= 10; i++)
        {
            Post post = new Post();
            post.setAuthor(users[i % 3]);
            post.setCategory(categories[i % 3]);
            post.setTitle("Title " + i);
            post.setContent("Content " + i);

            mPosts.add(post);
        }
    }

    public boolean contains(Post post)
    {
        return mPosts.contains(post);
    }

    public List<Post> getAllPosts()
    {
        return new ArrayList<>(mPosts);
    }


    public void addPost(Post post)
    {
        if(post != null)
        {
            mPosts.add(post);
            post.getAuthor().addPost(post);
        }
    }

    public void deletePost(Post post)
    {
        if(post != null && this.contains(post))
            mPosts.remove(post);
    }
}
