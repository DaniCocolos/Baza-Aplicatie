package com.example.forfoodiesbyfoodies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Adapters.RestaurantAdapter;
import com.example.forfoodiesbyfoodies.Models.RestaurantsData;

import java.util.ArrayList;

public class Restaurants extends AppCompatActivity {
    // recyclerview
    private RecyclerView rec_view;
    // Arraylist for storing data
    private ArrayList<RestaurantsData> RestaurantDataArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        rec_view = findViewById(R.id.rec_view1);
        RestaurantDataArray = new ArrayList<>();
       //TODO continuare

        RestaurantDataArray.add(new RestaurantsData("DSA in Java", "Adresa", "www.google.com"));
        RestaurantDataArray.add(new RestaurantsData("Java Course", "Adresa", "www.google.com"));
        RestaurantDataArray.add(new RestaurantsData("C++ COurse", "Adresa", "www.google.com"));
        RestaurantDataArray.add(new RestaurantsData("DSA in C++", "Adresa", "www.google.com"));

        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this, RestaurantDataArray);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        rec_view.setLayoutManager(linearLayoutManager);
        rec_view.setAdapter(restaurantAdapter);

    }
}