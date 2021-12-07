package com.example.forfoodiesbyfoodies.Models;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class View_Users extends AppCompatActivity {


    int x = 0;
    DatabaseReference dbref;
    TextView email, username, fn,ln,type;
    ImageView picture;
    Button previus, next, goback;
    List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        dbref = FirebaseDatabase.getInstance().getReference("_users_");

        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        fn = findViewById(R.id.fn);
        ln = findViewById(R.id.ln);
        picture = findViewById(R.id.picture);
        next = findViewById(R.id.next);
        type = findViewById(R.id.usertype);
        previus = findViewById(R.id.previous);
        goback = findViewById(R.id.goback);
        goback.setOnClickListener(v -> finish());



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref.addListenerForSingleValueEvent(listener);
                x++;
            }

        });
        previus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (x == 0) {
                    Toast.makeText(View_Users.this, "THere are no more records in the left side", Toast.LENGTH_SHORT).show();
                    previus.setClickable(false);
                }else
                    dbref.addListenerForSingleValueEvent(listener);
                    previus.setClickable(true);
                x--;
            }
        });


    }


    ValueEventListener listener = new ValueEventListener() {


        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for (DataSnapshot dss: snapshot.getChildren())
            {
                User us = dss.getValue(User.class);
                list.add(us);
            }
            All(x);

        }
        public  void All(int x)
        {
            fn.setText(list.get(x).getFirstname());
            email.setText(list.get(x).getEmail());
            ln.setText(list.get(x).getLastname());
            username.setText(list.get(x).getUsername());
            Picasso.get().load(list.get(x).getProfilePicture()).into(picture);
            type.setText(list.get(x).getUsertype());
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(View_Users.this, "There was an error communicating with database, please try again later", Toast.LENGTH_SHORT).show();
        }
    };




    }
