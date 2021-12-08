package com.example.forfoodiesbyfoodies.Reviews;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.AdapterStreetFood.StreetFoodList;
import com.example.forfoodiesbyfoodies.Adapters.RestaurantsList;
import com.example.forfoodiesbyfoodies.Models.User;
import com.example.forfoodiesbyfoodies.Models.UserPage;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.StreetFoodAdd.add_street_food;
import com.example.forfoodiesbyfoodies.Views.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class add_review extends AppCompatActivity {

    ImageView rest_pics,menu;
    EditText review_text;
    Button button;
    DatabaseReference dbref,dbref_restaurant,dbref2;
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
            menu = findViewById(R.id.menu_hamburger);
            menu.setOnClickListener(v -> open_menu());
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
                        if (!(count > 16))
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
                        String up = "0";
                        String down = "0";

                        //String sc = FirebaseDatabase.getInstance().getReference("_users_").child(critic_user_ID).getKey(); //return just firstname not the actual firstname
                        dbref2 = FirebaseDatabase.getInstance().getReference("_users_");
                        dbref2.child(critic_user_ID).child("username").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String name = (String) snapshot.getValue();
                                Log.d("name", "user " + name);
                                //String down,String up, String review_text,String critic_name, String stars
                                String desc = review_text.getText().toString();
                                String star = String.valueOf(stars.getRating());
                                ReviewsData obj = new ReviewsData(down, up ,desc, name, star);
                                obj.setCritic_name(name);

                                dbref.child(id_restaurant).child(dbref.push().getKey()).setValue(obj).addOnCompleteListener(
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
                                                                        finish();//TODO modificat onBackPress() cu finish()
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

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });









                    }

        }


//TODO de bagat meniu !!


    });}

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
