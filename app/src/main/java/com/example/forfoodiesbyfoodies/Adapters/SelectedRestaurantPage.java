package com.example.forfoodiesbyfoodies.Adapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.Reviews.add_review;
import com.example.forfoodiesbyfoodies.Reviews.ReviewsList;
import com.example.forfoodiesbyfoodies.Views.BookTable;
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
    ImageView restaurant_image;
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
        button_view_reviews = findViewById(R.id.srp_restaurant_reviews);
        button_add_reviews = findViewById(R.id.srp_restaurant_add_review);
        button_reservation = findViewById(R.id.srp_restaurant_reservation);
        //Button declaration end here --------------------------------------------------------------
        button_reservation.setOnClickListener(this);
        button_view_reviews.setOnClickListener(this);
        button_add_reviews.setOnClickListener(this);


        Intent it = getIntent();
        String name = it.getStringExtra("Name");
        String address = it.getStringExtra("Address");
        String photo = it.getStringExtra("URL");
        String url_rest = it.getStringExtra("URL_OPENTABLE");

        //TODO url_rest este url pentru opentable, si modifica ce este in interiorul linkul cu data aleasa de user
        // url_rest = url_rest.replace("thisshouldbecanged", "data_selected_by_user");
        Log.d("demo", "URL-> " + url_rest);
        //--------------------------------------------------------------------------------------------------------

        //Integer stars = it.getIntArrayExtra("Rating");
        String stars = it.getStringExtra("Rating");
        // TODO SI AICI AM SA VAD CUM PLM FAC CA SA FAC RATING-UL
        float rating = Float.parseFloat(stars);
        float g = -5 - rating;

        String description = it.getStringExtra("Description");
        String food_type = it.getStringExtra("Type");


        restaurant_name.setText(name);
        restaurant_address.setText(address);
        restaurant_description.setText(description);
        restaurant_stars.setRating(rating);//TODO vorbeste cu ovidiu de problema asta
        restaurant_type.setText(food_type);
        Picasso.get().load(photo).into(restaurant_image);  // restaurant picture
        //restaurant_stars.setNumStars(rating);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.srp_restaurant_reservation:
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
                startActivity(new Intent(SelectedRestaurantPage.this, ReviewsList.class));
                Intent i = getIntent();
                String url =i.getStringExtra("URL");
                String desc = i.getStringExtra("Description");
                String na = i.getStringExtra("Name");
                Intent a = new Intent(SelectedRestaurantPage.this, ReviewsList.class);
                a.putExtra("url", url);
                a.putExtra("desc", desc);
                a.putExtra("name", na);
                break;
        }
    }
}