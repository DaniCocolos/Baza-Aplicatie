package com.example.forfoodiesbyfoodies.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.Models.UserPage;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.StreetFoodAdd.add_street_food;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//this class has an interface which allow the user to enter an email and password to ->
//  -> login in order to get access to the application
//  action possible on this ,login, redirect to forgot password and redirect to register page

public class Login extends AppCompatActivity implements View.OnClickListener {

    private ImageView logo;
    private TextView register, forgot_password;
    EditText email, password;
    Button button_login;
    ProgressBar progressbar_login;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = (EditText) findViewById(R.id.et_em);
        password = (EditText) findViewById(R.id.et_pw);


        forgot_password = (TextView) findViewById(R.id.et_fpw);
        forgot_password.setOnClickListener(this);

        button_login = (Button) findViewById(R.id.button);
        button_login.setOnClickListener(this);

        logo = (ImageView) findViewById(R.id.imageView3);
        logo.setOnClickListener(this);

        //progressbar_login = (ProgressBar) findViewById(R.id.progressbar_login);

        mAuth = FirebaseAuth.getInstance();

        TextView textView = findViewById(R.id.et_text_reg);
        String text = "You don't have an account? Sign up, it's free!";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View v) {

            }

        };

        ss.setSpan(clickableSpan, 27, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        register = (TextView) findViewById(R.id.et_text_reg);
        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_text_reg:
                startActivity(new Intent(Login.this, RegisterUser.class));
                break;
            case R.id.button:
                login();
                break;
            case R.id.et_fpw:
                startActivity(new Intent(Login.this, ForgotPassword.class));
                break;
            case R.id.imageView3:
                startActivity(new Intent(Login.this, RegisterUser.class));
                break;


        }
    }


    public void login() {
        String email_login = email.getText().toString().trim();
        String password_login = password.getText().toString().trim();

        if (email_login.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (!(Patterns.EMAIL_ADDRESS.matcher(email_login).matches())) {
            email.setError("Please use a valid email address");
            email.requestFocus();
            return;
        }
        if (password_login.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if (password_login.length() < 4) {
            password.setError("Password is 4 minimum letters");
            password.requestFocus();
            return;
        }
        // progressbar_login.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email_login, password_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "You will be redirected to home page", Toast.LENGTH_SHORT).show();
                    // redirect to user profile or another page
                    startActivity(new Intent(Login.this, SelectPage.class));
                }//TODO aici sunt pentru a afisa eroare daca email exista in baza noastra de date
                else {
                    Toast.makeText(Login.this, "Email / password incorrect, please try again: " + task.getException(), Toast.LENGTH_SHORT).show();
                    //progressbar_login.setVisibility(View.GONE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //if (e.toString().contains("not registered"))
                Toast.makeText(Login.this, "Error is ->  " + e, Toast.LENGTH_LONG).show();
            }
        });


    }

}