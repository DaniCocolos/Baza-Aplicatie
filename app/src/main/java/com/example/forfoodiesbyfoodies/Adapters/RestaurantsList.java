package com.example.forfoodiesbyfoodies.Adapters;

import static com.example.forfoodiesbyfoodies.R.layout.activity_restaurantslist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestaurantsList extends AppCompatActivity {
    // recyclerview
    RecyclerView rec_view;

    // Arraylist for storing data
    ArrayList<RestaurantsData> list_of_restaurants;
    RestaurantAdapter myAdapter;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_restaurantslist);

        rec_view = findViewById(R.id.rec_view1);
        dbref = FirebaseDatabase.getInstance().getReference("Restaurants");
        rec_view.setLayoutManager(new LinearLayoutManager(this));


        list_of_restaurants = new ArrayList<>();
        myAdapter = new RestaurantAdapter(this, list_of_restaurants);
        rec_view.setAdapter(myAdapter);

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    RestaurantsData obj = dataSnapshot.getValue(RestaurantsData.class);
                    list_of_restaurants.add(obj);
                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}