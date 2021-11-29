package com.example.forfoodiesbyfoodies.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.Models.User;
import com.example.forfoodiesbyfoodies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    DatabaseReference dbref;
    EditText editTextFirstName, editTextLastName, editTextUserName, editTextemail, editTextpw, editTextcpw;
    ProgressBar progressBar;
    ImageView logo2back;
    TextView registerUser, textview_term;
    Button button_agree;
    private final String usertype = "normal";
    private final String profilePicture = "https://www.pngfind.com/pngs/m/676-6764065_default-profile-picture-transparent-hd-png-download.png";
    CheckBox reg_cb;
    Handler handler;
    String time = "1500"; // 1500 milliseconds = 1.5 seconds
    List<User> user_details_array = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        // redirect to home page if pressed the logo
        logo2back = (ImageView) findViewById(R.id.im_logo2);        //Logo + redirect
        logo2back.setOnClickListener(this);
        //------------------------------------------------------------------------------------------
        registerUser = (Button) findViewById(R.id.btn_register_user); //Register button
        registerUser.setOnClickListener(this);

        editTextFirstName = (EditText) findViewById(R.id.et_fn);    //FirstName
        editTextLastName = (EditText) findViewById(R.id.et_sn);     //LastName
        editTextUserName = (EditText) findViewById(R.id.et_user);   //Username
        editTextemail = (EditText) findViewById(R.id.et_em);        //Email

        editTextpw = (EditText) findViewById(R.id.et_pw);           //Password
        editTextcpw = (EditText) findViewById(R.id.et_cpw);         //Password confirmation

        progressBar = (ProgressBar) findViewById(R.id.progressBar); //Progress bar
        reg_cb = (CheckBox) findViewById(R.id.reg_cb);              //Checkbox


        //------------------------------------------------------------------------------------------


        //---------------------Terms of Service  functions and model--------------------------------
        TextView textView6 = findViewById(R.id.textView6);
        String text = "I accept the Terms of Service and Privacy Policy";
        SpannableString ss1 = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View v) {

            }

        };
        ss1.setSpan(clickableSpan, 13, 48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView6.setText(ss1);
        textView6.setMovementMethod(LinkMovementMethod.getInstance());

        //------------------------------------------------------------------------------------------
        textView6.setOnClickListener(this);
    }

    // popout for term and conditions-----------------------------------------------------------
    private void showInfo() {
        @SuppressLint("ResourceType") AlertDialog.Builder builder = new AlertDialog.Builder(this, R.layout.term_conditions);
        // builder.setMessage("Term and conditions MULTE LINII");

        // Create and show the AlertDialog
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    //------------------------------------------------------------------------------------------


    // method to move to login activity // Maybe we don't need this-----------------------------
    public void move_to_next_activity() {
        handler = new Handler();
        // .postDelayed is a function that run after a specific time format  )
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RegisterUser.this, Login.class);
                startActivity(intent); //start intent
                finish();
            }
        }, Long.parseLong(time));
    }

    //------------------------------------------------------------------------------------------

    public void registerUser() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_logo2:
                startActivity(new Intent(this, User.class));
                break;
            case R.id.btn_register_user:
                RegisterUser();
                break;
            case R.id.textView6:
                term_and_conditions();
                break;
        }
    }

    //method to register user in the auth and realtime, firstly, validation of input texts
    public void RegisterUser() {

        String email = editTextemail.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String username = editTextUserName.getText().toString().trim();
        String password = editTextpw.getText().toString().trim();
        String confirmPassword = editTextcpw.getText().toString().trim();

        //Checking if first name is empty or it length is less than 3 letters
        if (firstName.isEmpty() | firstName.length() < 3) {
            editTextFirstName.setError("First name is required. Minimum 3 characters");
            editTextFirstName.requestFocus();
            return;
        }

        //Checking if last name is empty or it length is less than 3 letters
        if (lastName.isEmpty() | lastName.length() < 3) {
            editTextLastName.setError("Last name is required. Minimum 3 characters");
            editTextLastName.requestFocus();
            return;
        }

        //Checking if username is empty or it length is less than 3 letters
        if (username.isEmpty() | username.length() < 3) {
            editTextUserName.setError("Username is required. Minimum 3 characters");
            editTextUserName.requestFocus();
            return;
        }

        //Checking if email is empty or it length is less than 3 letters
        if (email.isEmpty()) {
            editTextemail.setError("Email is required!");
            editTextemail.requestFocus();
            return;
        }
        //Checking if the email is valid
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Please provide a valid email. Ex. gmail");
            editTextemail.requestFocus();
            return;
        }

        if (password.isEmpty() | password.length() < 6) {
            editTextpw.setError("Password is required. Minimum 6 characters");
            editTextpw.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty() | confirmPassword.length() < 6) {
            editTextcpw.setError("Password is required. Minimum 6 characters");
            editTextcpw.requestFocus();
            return;
        }
        if (!(password.compareTo(confirmPassword) == 0)) {
            editTextcpw.setError("Password are not the same. Please try again");
            editTextcpw.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(firstName, lastName, email, password, username, usertype, profilePicture);
                    FirebaseDatabase.getInstance().getReference("_users_").child(FirebaseAuth.
                            getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterUser.this, "Successfully registered.", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(RegisterUser.this, Login.class));

                            } else {
                                Toast.makeText(RegisterUser.this, "Error occured: " + task.toString(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });


                }
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e.toString().contains("already")) {
                    Toast.makeText(RegisterUser.this, "Email already in use, please reset your password", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                } else {                                                                                  // + e catch the error from firebase auth and is printed in the toast// should be disabled after.
                    Toast.makeText(RegisterUser.this, "An error occured, please try again later! : " + e, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


    }

    //the end of RegisterUser()
    public void term_and_conditions() {

        //startActivity(new Intent(RegisterUser.this, TermAndConditions.class));

                /*Intent go = new Intent(getApplicationContext(), TermAndConditions.class);
                startActivity(go);*/
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.term_conditions, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        // boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        textview_term = popupView.findViewById(R.id.textview_term);
        textview_term.setText(R.string.textview_term);
        textview_term.setMovementMethod(new ScrollingMovementMethod());
        button_agree = popupView.findViewById(R.id.button_agree);
        button_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reg_cb = RegisterUser.this.findViewById(R.id.reg_cb);
                if (!reg_cb.isChecked()) {
                    //reg_cb.performClick();
                    reg_cb.performClick();
                    popupWindow.dismiss();
                } else {
                    popupWindow.dismiss();

                }
            }
        });
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);


    }


} // this is for the begin




