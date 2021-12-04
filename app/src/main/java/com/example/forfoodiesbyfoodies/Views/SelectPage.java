package com.example.forfoodiesbyfoodies.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.Adapters.RestaurantsList;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.Street_food;
import com.google.firebase.auth.FirebaseAuth;

public class SelectPage extends AppCompatActivity implements View.OnClickListener, FirebaseAuth.AuthStateListener {

    ImageView home_restaurant_photo, home_streefood_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        home_restaurant_photo = findViewById(R.id.home_restaurant_photo);
        home_streefood_photo = findViewById(R.id.home_streetfood_photo);

        //Listener implemented in View.OnClickListener
        home_restaurant_photo.setOnClickListener(this);
        home_streefood_photo.setOnClickListener(this);


        //---------------------Please visit our forum--------------------------------
        TextView textView8 = findViewById(R.id.textView8);
        String text = "Please visit our forum";
        SpannableString ss1 = new SpannableString(text);
        textView8.setTextSize(18);
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
       //----------------------------------------------------------------------------



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_restaurant_photo:
                startActivity(new Intent(SelectPage.this, RestaurantsList.class));
                break;
            case R.id.home_streetfood_photo:
                startActivity(new Intent(SelectPage.this, Street_food.class));
                break;
            case R.id.textView8:
                FirebaseAuth.getInstance().signOut();
                //need to create this forum class, layout and functionalities
                //startActivity(new Intent(SelectPage.this, Forum.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if (firebaseAuth.getCurrentUser() == null)
            {
                startActivity(new Intent(SelectPage.this, Login.class));
            }
    }
}