package com.example.forfoodiesbyfoodies.Reviews;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Adapters.RestaurantAdapter;
import com.example.forfoodiesbyfoodies.Adapters.RestaurantsData;
import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReviewsList extends AppCompatActivity {


    RecyclerView rc;
    ArrayList<ReviewsData> list_of_reviews;
    DatabaseReference dbref;
    ReviewsAdapter myAdapter;
    TextView et_Title,et_Description;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        //bandit initialization

        dbref = FirebaseDatabase.getInstance().getReference("reviews");

        et_Title = findViewById(R.id.et_Title);
        et_Description = findViewById(R.id.et_Description);
        imageView = findViewById(R.id.imageView);

       /* Intent i =  getIntent();
        String title = i.getStringExtra("name");

        Log.d("title", "title" + title);
        String description = i.getStringExtra("desc");
        Log.d("desc", "desc" + description);*/

        //et_Description.setText("description");
        //et_Title.setText("Restaurant title");
        et_Description.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        et_Title.setVisibility(View.GONE);
        //Picasso.get().load("https://cdn.dribbble.com/users/3641896/screenshots/14547182/media/664c0364d8df08028b392a980b4f2b4a.jpg").into(imageView);






        rc = findViewById(R.id.recyclerView2);
        rc.setLayoutManager( new LinearLayoutManager(this));


        list_of_reviews = new ArrayList<>();
        myAdapter = new ReviewsAdapter(this, list_of_reviews);
        rc.setAdapter(myAdapter);
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ReviewsData obj = dataSnapshot.getValue(ReviewsData.class);

                    list_of_reviews.add(obj);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReviewsList.this, "Error communicating with database ->  " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}