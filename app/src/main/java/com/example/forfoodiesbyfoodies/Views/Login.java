package com.example.forfoodiesbyfoodies.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;

public class Login extends AppCompatActivity implements View.OnClickListener{


    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Try to make sign up (words not function) clickable

        TextView textView = findViewById(R.id.et_text_reg);
        String text = "You don't have an account? Sign up, it's free!";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick( View v) {
                // nu uita de facut legatura cu Register
                //Toast.makeText(Login.this, "OK", Toast.LENGTH_SHORT).show();
                //gonext();
            }

        };

        ss.setSpan(clickableSpan, 27,34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        register = (TextView) findViewById(R.id.et_text_reg);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getApplicationContext(), RegisterUser.class);
                startActivity(go);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_text_reg:
                startActivity(new Intent(Login.this, RegisterUser.class));
                break;
        }
    }
    public void gonext(){
        Intent i = new Intent(getApplicationContext(), RegisterUser.class);
        startActivity(i);
    }
}