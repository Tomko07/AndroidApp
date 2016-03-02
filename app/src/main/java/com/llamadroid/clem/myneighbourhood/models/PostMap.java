package com.llamadroid.clem.myneighbourhood.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class PostMap
{
    private static PostMap sPostMap;
    private HashMap<UUID, Post> mPosts;

    public static PostMap get(Context context)
    {
        if(sPostMap == null)
            sPostMap = new PostMap(context);
        return sPostMap;
    }

    private PostMap(Context context)
    {
        mPosts = new HashMap<>();

        // Populating the map with fake posts
    }


    public boolean contains(UUID id)
    {
        return mPosts.containsKey(id);
    }

    public List<Post> getAllPosts()
    {
        return new ArrayList<Post>(mPosts.values());
    }

    public Post getPost(UUID id)
    {
        return mPosts.get(id);
    }

    public void addPost(Post post)
    {
        if(post != null && mPosts.get(post.getId()) == null)
            mPosts.put(post.getId(), post);
    }

    public void deletePost(Post post)
    {
        if(post != null && this.contains(post.getId()))
            mPosts.remove(post.getId());
    }
}
