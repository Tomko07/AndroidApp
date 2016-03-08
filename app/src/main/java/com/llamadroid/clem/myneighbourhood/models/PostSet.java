package com.llamadroid.clem.myneighbourhood.models;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.UUID;


/**
 * Singleton class storing the different messages posted by users.
 */
public class PostSet
{
    private static PostSet sPostList;
    // Keeping posts sorted by date.
    private TreeSet<Post> mPosts;

    public static PostSet get(Context context)
    {
        if(sPostList == null)
            sPostList = new PostSet(context);
        return sPostList;
    }

    // Extracting hard coded posts from txt file.
    private PostSet(Context context)
    {
        mPosts = new TreeSet<>();

        InputStreamReader reader = null;
        Scanner scanner = null;

        try
        {
            try
            {
                reader = new InputStreamReader(context.getResources().getAssets().open("Posts.txt"));
                scanner = new Scanner(reader);

                while (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();

                    Post post = new Post();
                    post.setAuthor(UserMap.get(context).getUser(line.substring(0, line.indexOf(" "))));
                    post.setTitle(line.substring(line.indexOf('[') + 1, line.indexOf(']')));
                    post.setContent(line.substring(line.indexOf('{') + 1, line.indexOf('}')));
                    String category = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
                    post.setCategory(new Category(CategoryType.getCategory(category)));
                    String date = line.substring(line.indexOf('\\') + 1, line.indexOf('/'));
                    try
                    {
                        post.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(date));
                    }
                    catch (ParseException pe)
                    {
                        Log.e("UserMap", "ParseException caught", pe);
                    }

                    mPosts.add(post);
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

    public boolean contains(Post post)
    {
        return mPosts.contains(post);
    }

    public List<Post> getAllPosts()
    {
        return new ArrayList<>(mPosts);
    }

    public Post getPost(UUID postId)
    {
        for(Post post : mPosts)
            if(post.getId().equals(postId))
                return post;
        return null;
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
