package com.example.forfoodiesbyfoodies.AdapterStreetFood;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class SelectedStreetFoodPage extends AppCompatActivity implements View.OnClickListener {

    TextView streetfood_name, streetfood_location,streetfood_description,streetfood_type;
    ImageView streetfood_image;

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

        streetfood_name.setText(name);
        streetfood_description.setText(desc);
        streetfood_location.setText(location);
        streetfood_type.setText(type);
        Picasso.get().load(photo).into(streetfood_image);

        //button_getdirectons =  findViewById(R.id.button_getdirectons);
        button_getdirectons = findViewById(R.id.button_getdirectons);
        button_getdirectons.setOnClickListener(this);








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
        }
    }
}