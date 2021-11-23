package com.example.forfoodiesbyfoodies.Views;

import static com.example.forfoodiesbyfoodies.R.id.home_restaurant_photo;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.Restaurants;
import com.example.forfoodiesbyfoodies.Street_food;

public class SelectPage extends AppCompatActivity implements View.OnClickListener {

    ImageView home_restaurant_photo, home_streefood_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        home_restaurant_photo = findViewById(R.id.home_restaurant_photo);
        home_streefood_photo = findViewById(R.id.home_streetfood_photo);


        home_restaurant_photo.setOnClickListener(this);
        home_streefood_photo.setOnClickListener(this);


        //---------------------Terms of Service  functions and model--------------------------------
        TextView textView8 = findViewById(R.id.textView8);
        String text = "Please visit our forum";
        SpannableString ss1 = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View v) {

            }

        };
        ss1.setSpan(clickableSpan, 17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView8.setText(ss1);
        textView8.setMovementMethod(LinkMovementMethod.getInstance());

        textView8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_restaurant_photo:
                startActivity(new Intent(SelectPage.this, Restaurants.class));
                break;
            case R.id.home_streetfood_photo:
                startActivity(new Intent(SelectPage.this, Street_food.class));
                break;
            case R.id.textView8:
                //need to create this forum class, layout and functionalities
                //startActivity(new Intent(SelectPage.this, Forum.class));
                break;
        }
    }
}