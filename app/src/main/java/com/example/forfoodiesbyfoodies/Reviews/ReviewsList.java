package com.example.forfoodiesbyfoodies.Reviews;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.forfoodiesbyfoodies.AdapterStreetFood.StreetFoodList;
import com.example.forfoodiesbyfoodies.Adapters.RestaurantAdapter;
import com.example.forfoodiesbyfoodies.Adapters.RestaurantsData;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReviewsList extends AppCompatActivity {


    RecyclerView rc;
    ArrayList<ReviewsData> list_of_reviews;
    DatabaseReference dbref;
    ReviewsAdapter myAdapter;
    TextView et_Title,et_Description;
    ImageView imageView;
    ImageView menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        //bandit initialization

        dbref = FirebaseDatabase.getInstance().getReference("reviews");

        et_Title = findViewById(R.id.et_Title);
        et_Description = findViewById(R.id.et_Description);
        imageView = findViewById(R.id.imageView);

        menu = findViewById(R.id.menu_icon);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_menu();
            }
        });


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