package com.llamadroid.clem.myneighbourhood.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.llamadroid.clem.myneighbourhood.R;
import com.llamadroid.clem.myneighbourhood.models.Category;
import com.llamadroid.clem.myneighbourhood.models.CategoryType;
import com.llamadroid.clem.myneighbourhood.models.CurrentUser;
import com.llamadroid.clem.myneighbourhood.models.Post;
import com.llamadroid.clem.myneighbourhood.models.PostSet;
import com.llamadroid.clem.myneighbourhood.models.User;


/**
 * Class handling user interactions with the new post screen.
 */
public class NewPostActivity extends AppCompatActivity
{
    /** Spinner widget to select the post category. */
    private Spinner mSpinner;
    /** Text fields to add the title and the content of the post. */
    private EditText mTitleField, mContentField;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        // Add toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.app_bar));
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inflate widgets and add listener to button.
        inflateSpinner();
        inflateTextFields();
        inflateSubmitButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_item_profile:
                // Start profile activity
                return true;
            case R.id.menu_item_messages:
                // Start messages activity
                return true;
            case R.id.menu_item_home:
                // Already on the home screen -> do nothing.
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
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }


    private void inflateSpinner()
    {
        mSpinner = (Spinner)findViewById(R.id.spinner_post_category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items_post_category,
                R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    private void inflateTextFields()
    {
        mTitleField = (EditText)findViewById(R.id.field_new_post_title);
        mContentField = (EditText)findViewById(R.id.field_new_post_content);
    }

    /**
     * When submit button is pressed:
     * - check that a category was selected,
     * - check the validity of the title (not empty)
     * - check the validity of the content (not empty)
     */
    private void inflateSubmitButton()
    {
        Button submitButton = (Button)findViewById(R.id.button_submit_post);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String selectedCategory = mSpinner.getSelectedItem().toString();
                if("Category".equals(selectedCategory))
                {
                    printErrorToast("Select the category of the post.");
                    return;
                }
                Category category = new Category(CategoryType.valueOf(selectedCategory));

                String title = mTitleField.getText().toString();
                if("".equals(title))
                {
                    printErrorToast("Add a title to the post.");
                    return;
                }

                String content = mContentField.getText().toString();
                if("".equals(content))
                {
                    printErrorToast("The post is empty.");
                    return;
                }

                // Create new Post object
                User user = CurrentUser.getCurrentUser();
                Post post = new Post(category, title, content, user);

                // Add the post to the global Post set.
                PostSet.get(NewPostActivity.this).addPost(post);
                finish();
            }
        });
    }

    private void printErrorToast(String errorMessage)
    {
        Toast.makeText(this, errorMessage,
                Toast.LENGTH_SHORT).show();
    }
}
