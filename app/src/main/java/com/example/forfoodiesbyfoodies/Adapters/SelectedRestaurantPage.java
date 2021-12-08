package com.example.forfoodiesbyfoodies.Adapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forfoodiesbyfoodies.AdapterStreetFood.StreetFoodList;
import com.example.forfoodiesbyfoodies.Models.UserPage;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.Reviews.add_review;
import com.example.forfoodiesbyfoodies.Reviews.ReviewsList;
import com.example.forfoodiesbyfoodies.StreetFoodAdd.add_street_food;
import com.example.forfoodiesbyfoodies.Views.BookTable;
import com.example.forfoodiesbyfoodies.Views.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class SelectedRestaurantPage extends AppCompatActivity implements View.OnClickListener {
    TextView restaurant_name, restaurant_description, restaurant_type, restaurant_address;
    ImageView restaurant_image,menu;
    Button button_view_reviews, button_add_reviews, button_reservation;
    RatingBar restaurant_stars;

    DatabaseReference dbref;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_restaurant_page);

        //getting the current user ID
        mAuth = FirebaseAuth.getInstance();

        dbref = FirebaseDatabase.getInstance().getReference("_users_");
        String userid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid(); //
        //Log.d("userid", "userid is -->> " + userid);

        dbref.child(userid).child("usertype").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (Objects.requireNonNull(snapshot.getValue()).toString().equals("normal")) {
                    button_add_reviews.setVisibility(View.GONE);
                    button_view_reviews.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Textviews for restaurant details start here-----------------------------------------------
        restaurant_name = findViewById(R.id.sf_name);
        restaurant_description = findViewById(R.id.sf_desc);
        restaurant_address = findViewById(R.id.sf_location);
        restaurant_type = findViewById(R.id.sf_type);
        //Textviews for restaurant details end here-------------------------------------------------

        //Image views and ratingbar start here------------------------------------------------------
        restaurant_image = findViewById(R.id.sf_image);
        restaurant_stars = findViewById(R.id.srp_restaurant_rating);
        //Image views and ratingbar end here--------------------------------------------------------


        //Button declaration start here ------------------------------------------------------------
        button_view_reviews = findViewById(R.id.srp_restaurant_reviews);
        button_add_reviews = findViewById(R.id.srp_restaurant_add_review);
        button_reservation = findViewById(R.id.button_getdirectons);
        menu = findViewById(R.id.menu_hamburger);
        //Button declaration end here --------------------------------------------------------------
        button_reservation.setOnClickListener(this);
        button_view_reviews.setOnClickListener(this);
        button_add_reviews.setOnClickListener(this);
        menu.setOnClickListener(this);

        Intent it = getIntent();
        String name = it.getStringExtra("Name");
        String address = it.getStringExtra("Address");
        String photo = it.getStringExtra("URL");
        String url_rest = it.getStringExtra("URL_OPENTABLE");
        String description = it.getStringExtra("Description");
        String food_type = it.getStringExtra("Type");
        String stars = it.getStringExtra("Rating");

        float rating = Float.parseFloat(stars);
        //float g = -5 - rating;




        restaurant_name.setText(name);
        restaurant_address.setText(address);
        restaurant_description.setText(description);
        restaurant_stars.setRating(rating);//
        restaurant_type.setText(food_type);
        Picasso.get().load(photo).into(restaurant_image);  // restaurant picture



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_getdirectons:
                Intent it = getIntent();
                String photo = it.getStringExtra("URL");
                String url_rest = it.getStringExtra("URL_OPENTABLE");
                Intent new1 = new Intent(SelectedRestaurantPage.this, BookTable.class);
                new1.putExtra("url_image", photo);
                new1.putExtra("url_opentable", url_rest);
                startActivity(new1);
                break;
            case R.id.srp_restaurant_add_review:
                Intent getIntent = getIntent();
                String p1 = getIntent.getStringExtra("URL");
                String id = getIntent.getStringExtra("id");
                String stars = getIntent.getStringExtra("Rating");
                String critic_username = getIntent.getStringExtra("username");
                Intent in = new Intent(this, add_review.class);
                 in.putExtra("id_restaurant", id); //restaurant id
                 in.putExtra("url_image", p1);// restaurant image url
                in.putExtra("current_rating", stars);

                startActivity(in);
                break;
            case R.id.srp_restaurant_reviews:
                //startActivity(new Intent(SelectedRestaurantPage.this, ReviewsList.class));
                Intent get = getIntent();
                String idrest = get.getStringExtra("id");
                Intent review = new Intent(SelectedRestaurantPage.this, ReviewsList.class);
                review.putExtra("id", idrest);
                startActivity(review);
             /*   a.putExtra("url", url);
                a.putExtra("desc", desc);
                a.putExtra("name", na);*/
                break;
            case R.id.menu_hamburger:
                open_menu();
                break;

        }
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