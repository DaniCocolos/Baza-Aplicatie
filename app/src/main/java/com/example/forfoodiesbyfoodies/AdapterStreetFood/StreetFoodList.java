package com.example.forfoodiesbyfoodies.AdapterStreetFood;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Adapters.RestaurantAdapter;
import com.example.forfoodiesbyfoodies.Adapters.RestaurantsData;
import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class StreetFoodList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_food_list);

        RecyclerView rec_view;

        // Arraylist for storing data
        ArrayList<StreetFoodData> list_of_streetfood;
        StreetFoodAdapter myAdapter;
        DatabaseReference dbref;



    }
}