package com.example.forfoodiesbyfoodies.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Integer time = 1500; //time declaration 1500 ms = 1.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        // .postDelayed is a function that run after a specific time format  )
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent); //start intent
                finish();
            }
        }, Long.parseLong(String.valueOf(time)));

    }
}