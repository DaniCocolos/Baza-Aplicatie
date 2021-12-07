package com.example.forfoodiesbyfoodies.AdapterStreetFood;


import android.content.Intent;
import android.net.Uri;
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

import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.Adapters.RestaurantsList;
import com.example.forfoodiesbyfoodies.Models.UserPage;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.StreetFoodAdd.add_street_food;
import com.example.forfoodiesbyfoodies.Views.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class SelectedStreetFoodPage extends AppCompatActivity implements View.OnClickListener {

    TextView streetfood_name, streetfood_location,streetfood_description,streetfood_type;
    ImageView streetfood_image, menu;

    Button button_view_reviews, button_add_reviews, button_getdirectons;
    RatingBar restaurant_stars;

    DatabaseReference dbref;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_street_food_page);



        Intent it = getIntent();
        String name = it.getStringExtra("title");
        String location = it.getStringExtra("location");
        String photo = it.getStringExtra("photo");
        String desc = it.getStringExtra("description");
        String type = it.getStringExtra("type");


        streetfood_image = findViewById(R.id.sf_image);
        streetfood_name = findViewById(R.id.sf_name);
        streetfood_location = findViewById(R.id.sf_location);
        streetfood_description = findViewById(R.id.sf_desc);
        streetfood_type = findViewById(R.id.sf_type);
        menu = findViewById(R.id.menu_hamburger);
        streetfood_name.setText(name);
        streetfood_description.setText(desc);
        streetfood_location.setText(location);
        streetfood_type.setText(type);
        Picasso.get().load(photo).into(streetfood_image);

        //button_getdirectons =  findViewById(R.id.button_getdirectons);
        button_getdirectons = findViewById(R.id.button_getdirectons);
        button_getdirectons.setOnClickListener(this);
        menu.setOnClickListener(this);







    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_getdirectons:
                Intent i = getIntent();

                String go_to_maps = "https://www.google.com/maps/search/" + i.getStringExtra("location") ;
                Uri uri = Uri.parse(go_to_maps);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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