package com.example.forfoodiesbyfoodies.Models;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.Views.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserPage extends AppCompatActivity implements View.OnClickListener {
    private FirebaseUser user;
    private DatabaseReference dbref;
    private String userId;
    private Button up_fn_set_btn;
    //ArrayList <User.User> lista;
    private User user1;



    Button buton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        buton =  findViewById(R.id.up_logout);
        buton.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        dbref = FirebaseDatabase.getInstance().getReference();
        userId = user.getUid();
        Toast.makeText(UserPage.this, " Userobject value : " + user.getEmail(), Toast.LENGTH_LONG).show();

        final TextView user_fn = findViewById(R.id.up_user_fn);
        final TextView user_ln = findViewById(R.id.up_user_ln);
        final TextView user_email = findViewById(R.id.up_user_email);
        final TextView up_account_type = findViewById(R.id.up_account_type);
        final ImageView up_fn_set_btn = findViewById(R.id.up_fn_set_btn);


        //setOnClickListener group start here
        up_fn_set_btn.setOnClickListener(this);
        //setOnClickListener group end here


        dbref.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    String fn = user.getFirstname();
                    String ln = user.getLastname();
                    String email = user.getEmail();
                    String userType = user.getUsertype();


                    user_fn.setText(user.getFirstname());
                    user_ln.setText(ln);
                    user_email.setText(email);
                    up_account_type.setText(userType);
                    // Toast.makeText(getApplicationContext(), "User-type: " + userType, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserPage.this, "There was an error: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //-----CHANGING FIRST NAME START--------------------------------------------------------------------------------------------
    public void change_fn() {
        EditText user_fn = findViewById(R.id.up_user_fn);

        dbref.child("_users_").child(userId).child("firstname").setValue(user_fn.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(UserPage.this, "You've just updated your firstname ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UserPage.this, "There was an error. Please try again in 5 minutes", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //-----CHANGING FIRST NAME END--------------------------------------------------------------------------------------------


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserPage.this, Login.class));
                break;
            case R.id.up_fn_set_btn:
                change_fn();
                break;

        }
    }

}