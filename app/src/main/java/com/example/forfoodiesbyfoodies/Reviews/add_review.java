package com.example.forfoodiesbyfoodies.Reviews;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class add_review extends AppCompatActivity {

    ImageView rest_pics;
    EditText review_text;
    Button button;
    DatabaseReference dbref,dbref_restaurant;
    FirebaseAuth mAuth;
    RatingBar stars;
    Boolean starsSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        Intent it = getIntent();
        String id_restaurant = it.getStringExtra("id_restaurant");
        String url_photo_restaurant = it.getStringExtra("url_image");
        String current_rating = it.getStringExtra("current_rating");

        //------------------------------------------------------------------------------------------------------

        //getting current user ID;
        mAuth = FirebaseAuth.getInstance();
        String critic_user_ID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        //------------------------------------------------------------------------------------------------------
        dbref = FirebaseDatabase.getInstance().getReference("reviews");
        dbref_restaurant = FirebaseDatabase.getInstance().getReference("Restaurants");
        //load image restaurant in the image View
        rest_pics = findViewById(R.id.imageView5); // restaurant image
        Picasso.get().load(url_photo_restaurant).into(rest_pics);
        //------------------------------------------------------------------------------------------------------
        review_text = findViewById(R.id.input_text); // review input text
        button = findViewById(R.id.button2); // button add review

        stars = findViewById(R.id.srp_restaurant_rating);


        //text changed listener for minimum number of characters from review description to be minimum 16
        review_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (count < 16 )
                        {
                            Toast.makeText(add_review.this, "Minimum number of characters is 16", Toast.LENGTH_SHORT).show();
                            button.setClickable(false);
                        }
                        else
                        {
                            button.setClickable(true);
                        }
            }

            @Override
            public void afterTextChanged(Editable s) {
                        if (s.length() > 16)
                        {
                            button.setClickable(true);
                        }
            }
        });
        //------------------------------------------------------------------------------------------------------

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (!review_text.getText().toString().isEmpty())
                    {
                       // Integer new_rating = Integer.parseInt(Integer.parseInt(current_rating) + stars.getRating())/2;
                        Integer c = Integer.parseInt(current_rating);
                        float s = stars.getRating();
                        float s1 = (c + s)/2;
                        String rate = String.valueOf(Math.round(s1));

                        ReviewsData obj = new ReviewsData(critic_user_ID, review_text.getText().toString(), String.valueOf(stars.getRating()));


                        dbref.child(id_restaurant).setValue(obj).addOnCompleteListener(
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful())
                                        {
                                            //Toast.makeText(add_review.this, "You have succesfully addded your review!", Toast.LENGTH_LONG).show();
                                            //setting the new rating to the restaurant
                                            dbref_restaurant.child(id_restaurant).child("stars").setValue(rate).addOnCompleteListener(
                                                    new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful())
                                                            {
                                                                Toast.makeText(add_review.this, "You have succesfully addded your review!", Toast.LENGTH_LONG).show();
                                                                onBackPressed();
                                                            }
                                                        }
                                                    }
                                            );

                                        }else
                                        {
                                            Toast.makeText(add_review.this, "There was an error  -> " + Objects.requireNonNull(task.getException()).toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                        );
                    }

        }


//TODO de bagat meniu !!


    });}
}
