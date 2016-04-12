package com.example.tarek.codekitchen;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class CoffeeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_list);

        ListView customListView = (ListView)findViewById(R.id.listView);
        String [] listViewAdapterContent = {
                "Cafe-0",
                "Cafe-1",
                "Cafe-2",
                "Cafe-3",
                "Cafe-4"
        };
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listViewAdapterContent
        );
        customListView.setAdapter(listAdapter);
        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // make Toast when click
                Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();

                //flip in card when click instead...

                setContentView(R.layout.activity_card_flip);
//                if (CoffeeListActivity.this.savedInstanceState == null) {
                    getFragmentManager()
                            .beginTransaction()
                            .add(R.id.card_container, new CardFrontFragment())
                            .commit();
//                }

                FrameLayout cardContainer = (FrameLayout) findViewById(R.id.card_container);
                cardContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flipCard();
                    }
                });


            }

        });


    }

    public boolean mShowingBack = false;
    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        getFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.card_container, new CardBackFragment())

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();
    }

}

/**
 * A fragment representing the front of the card.
 */
@SuppressLint("ValidFragment")
class CardFrontFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card_front, container, false);

        Button leaveCommentBtn = (Button) v.findViewById(R.id.leaveCommentBtn);
        leaveCommentBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(i);
            }
        });


        return v;
    }
}

/**
 * A fragment representing the back of the card.
 */
@SuppressLint("ValidFragment")
class CardBackFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_back, container, false);
    }
}
