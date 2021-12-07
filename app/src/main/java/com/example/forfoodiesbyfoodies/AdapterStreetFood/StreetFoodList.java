package com.example.forfoodiesbyfoodies.AdapterStreetFood;


import android.os.Bundle;
import android.widget.Toast;

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

public class StreetFoodList extends AppCompatActivity {

    RecyclerView rec_view2;
    // Arraylist for storing data
    ArrayList<StreetFoodData> list_of_streetfood;
    StreetFoodAdapter myAdapter1;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_food_list);


        rec_view2 = findViewById(R.id.rec_view_street_food);
        rec_view2.setLayoutManager(new LinearLayoutManager(this));

        list_of_streetfood = new ArrayList<>();
        myAdapter1 = new StreetFoodAdapter(this, list_of_streetfood);
        rec_view2.setAdapter(myAdapter1);
        dbref = FirebaseDatabase.getInstance().getReference("StreetFood");

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    StreetFoodData obj = dataSnapshot.getValue(StreetFoodData.class);
                    if (!(obj ==null))
                    {
                        list_of_streetfood.add(obj);
                    }
                }
                myAdapter1.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "There was an error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}