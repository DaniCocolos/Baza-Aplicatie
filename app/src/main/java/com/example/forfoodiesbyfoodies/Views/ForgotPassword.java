package com.example.forfoodiesbyfoodies.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.forfoodiesbyfoodies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    Button sendButon;
    ProgressBar prog_bar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email = (EditText) findViewById(R.id.fp_email);
        sendButon = (Button) findViewById(R.id.fp_send_password);
        prog_bar = (ProgressBar) findViewById(R.id.progressBar2);
        sendButon.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();



    }

    public void reset_passwod(){
        String email_password = email.getText().toString().trim();
        if (email_password.isEmpty())
        {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email_password).matches()){
            email.setError("Please use an valid email");
            email.requestFocus();
        }

        mAuth.sendPasswordResetEmail(email_password).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(ForgotPassword.this, "Please check your email for instructions", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotPassword.this, Login.class));
                }
                else {
                    //Toast.makeText(ForgotPassword.this, "There was an error. Please try again later", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ForgotPassword.this, "This email address is not registered in our database", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(ForgotPassword.this, Login.class));
                }
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fp_send_password:
                reset_passwod();
                break;
        }
    }
}