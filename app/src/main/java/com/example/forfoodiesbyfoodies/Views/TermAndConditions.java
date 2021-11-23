package com.example.forfoodiesbyfoodies.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;

public class TermAndConditions extends AppCompatActivity {
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_conditions);


        Button go = findViewById(R.id.button_back_terms);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterUser.class);
                startActivity(i);
            }
        });


    }
}