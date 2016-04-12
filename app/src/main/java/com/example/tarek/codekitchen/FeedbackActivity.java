package com.example.tarek.codekitchen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById( R.id.commentsList);
        listView.setTextFilterEnabled(true);


        String[] FRUITS = new String[] {
                "This shop is great",
                "Coffee is too hot",
                "Cappuccino is perfecto",
                "Wait lines too long"
        };

        List<String> coffeeComments = new ArrayList<String>();
        coffeeComments.add("This shop is great");
        coffeeComments.add("Coffee is too hot");
        coffeeComments.add("Cappuccino is perfecto");
        coffeeComments.add("Wait lines too long");



        final ArrayAdapter<String> commentsAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                coffeeComments);
        commentsAdapter.setNotifyOnChange(true);

        listView.setAdapter(commentsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton saveComment = (FloatingActionButton) findViewById(R.id.save_comment);
        saveComment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //grab the new comment from the view
                EditText commentBox = (EditText) findViewById(R.id.editText);
                String commentText = commentBox.getText().toString();

                //save to the list
                commentsAdapter.add(commentText);

                //clear the edittext
                commentBox.setText("");
            }
        });


    }

}
