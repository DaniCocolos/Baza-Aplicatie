package com.example.forfoodiesbyfoodies.Adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.forfoodiesbyfoodies.Models.User;
import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.nio.charset.StandardCharsets;

public class SelectedRestaurantPage extends AppCompatActivity {
    TextView restaurant_name, restaurant_description,restaurant_type, restaurant_address;
    ImageView restaurant_image;
    Button button_view_reviews, button_add_reviews, button_reservation;
    RatingBar restaurant_stars;

    RestaurantsData restaurant;


    //TODO think about this, having the dbs here maybe not neccesary here and maybe is in the next page calendar after pressing button
    FirebaseDatabase dbref;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_restaurant_page);

        //Textviews for restaurant details start here-----------------------------------------------
        restaurant_name = findViewById(R.id.srp_restaurant_name);
        restaurant_description = findViewById(R.id.srp_restaurant_description);
        restaurant_address = findViewById(R.id.srp_restaurant_address);
        restaurant_type = findViewById(R.id.srp_restaurant_type);
        //Textviews for restaurant details end here-------------------------------------------------

        //Image views and ratingbar start here------------------------------------------------------
        restaurant_image = findViewById(R.id.srp_image_restaurant);
        restaurant_stars = findViewById(R.id.srp_restaurant_rating);
        //Image views and ratingbar end here--------------------------------------------------------


        //Button declaration start here ------------------------------------------------------------
        button_view_reviews= findViewById(R.id.srp_restaurant_reviews);
        button_add_reviews = findViewById(R.id.srp_restaurant_add_review);
        button_reservation = findViewById(R.id.srp_restaurant_reservation);
        //Button declaration end here --------------------------------------------------------------




        Intent it = getIntent();
        String name = it.getStringExtra("Name");
        String address = it.getStringExtra("Address");
        String photo = it.getStringExtra("URL");
        String url_rest = it.getStringExtra("URL_OPENTABLE");


        //Integer stars = it.getIntArrayExtra("Rating");
        String stars = it.getStringExtra("Rating");
        // TODO SI AICI AM SA VAD CUM PLM FAC CA SA FAC RATING-UL
        Integer rating = Integer.parseInt(stars);
        String description = it.getStringExtra("Description");
        String food_type = it.getStringExtra("Type");


        restaurant_name.setText(name);
        restaurant_address.setText(address);
        restaurant_description.setText(description);


        restaurant_type.setText(food_type);
        Picasso.get().load(photo).into(restaurant_image);
        //restaurant_stars.setNumStars(rating);
        restaurant_stars.setRating(Float.parseFloat(stars));


    }

}