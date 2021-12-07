package com.example.forfoodiesbyfoodies.AdapterStreetFood;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Adapters.RestaurantsList;
import com.example.forfoodiesbyfoodies.Models.UserPage;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.StreetFoodAdd.add_street_food;
import com.example.forfoodiesbyfoodies.Views.Login;
import com.google.firebase.auth.FirebaseAuth;
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
    ImageView menu;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_food_list);


        rec_view2 = findViewById(R.id.rec_view_street_food);
        rec_view2.setLayoutManager(new LinearLayoutManager(this));

        menu = findViewById(R.id.menu_hamburger);
        menu.setOnClickListener(v -> open_menu());
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
    public void open_menu() {

        //startActivity(new Intent(RegisterUser.this, TermAndConditions.class));
        /*Intent go = new Intent(getApplicationContext(), TermAndConditions.class);
        tartActivity(go);*/
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.menu_header, null);

        // create the popup window

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;


        //int position = LinearLayout.FOCUS_RIGHT;
        // boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, 600, 2192, true);
        // TextView textview_term = popupView.findViewById(R.id.textview_term);
        //textview_term.setText("Test");
        //textview_term.setMovementMethod(new ScrollingMovementMethod());
        TextView tv_profile = popupView.findViewById(R.id.tv_profile);
        TextView tv_logout = popupView.findViewById(R.id.tv_logout);
        TextView tv_reservation = popupView.findViewById(R.id.tv_reservation);
        TextView tv_reviews =   popupView.findViewById(R.id.tv_reviews);
        TextView tv_restaurants = popupView.findViewById(R.id.tv_restaurants);
        TextView tv_street_food = popupView.findViewById(R.id.tv_street_food);
        TextView tv_add_restaurant = popupView.findViewById(R.id.tv_add_restaurant);
        TextView tv_add_street_food = popupView.findViewById(R.id.tv_add_street_food);


        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserPage.class));
            }
        });
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
        tv_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature will be implemented in future! THanks for understanding", Toast.LENGTH_SHORT).show();

            }
        });
        tv_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature will be implemented in future! THanks for understanding", Toast.LENGTH_SHORT).show();

            }
        });
        tv_restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RestaurantsList.class));
            }
        });
        tv_street_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StreetFoodList.class));
            }
        });
        tv_add_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is accesible just for admins: admin@gmail.com / admin", Toast.LENGTH_SHORT).show();

            }
        });
        tv_add_street_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), add_street_food.class));
            }
        });


        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(popupView, Gravity.RIGHT, 0, 0);


    }

}