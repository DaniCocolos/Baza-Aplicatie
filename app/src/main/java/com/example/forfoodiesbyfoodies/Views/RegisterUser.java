package com.example.forfoodiesbyfoodies.Views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
     FirebaseAuth mAuth;
    DatabaseReference dbref;
    private EditText editTextFirstName, editTextLastName, editTextUserName, editTextemail, editTextpw, editTextcpw;
    private ProgressBar progressBar;
    private ImageView logo2back;
    private TextView registerUser;
    private String usertype = "normal";
    private CheckBox reg_cb;
    Integer x = 1;
    Handler handler;
    String time = "1500" ; // 1500 milliseconds = 1.5 seconds
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //String x is used to get the checkbox status when the user press Register


    List<User> user_details_array = new ArrayList<>();




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //--------------------------------------------------------------------------------
        TextView textView6 = findViewById(R.id.textView6);
        String text = "I accept the Terms of Service and Privacy Policy";

        SpannableString ss1 = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick( View v) {

            }

        };

        ss1.setSpan(clickableSpan, 13,48, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textView6.setText(ss1);
        textView6.setMovementMethod(LinkMovementMethod.getInstance());

        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(RegisterUser.this, TermAndConditions.class));
                Intent go = new Intent(getApplicationContext(), TermAndConditions.class);
                startActivity(go);
            }
        });
        //--------------------------------------------------------------------------------






        dbref = FirebaseDatabase.getInstance().getReference("_users_");
        mAuth = FirebaseAuth.getInstance();

        logo2back = (ImageView) findViewById(R.id.im_logo2);
        logo2back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterUser.this,Login.class));
            }
        });
      // logo2back.setOnContextClickListener((View.OnContextClickListener) RegisterUser.this);
       // logo2back.setOnContextClickListener((View.OnContextClickListener)this);
        // this line above crash the app


        registerUser = (Button) findViewById(R.id.btn_register_user);
//        registerUser.setOnContextClickListener((View.OnContextClickListener) this);
        // this line above crash the app

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // de aici






                editTextFirstName = (EditText) findViewById(R.id.et_fn);
                editTextLastName = (EditText) findViewById(R.id.et_sn);
                editTextUserName = (EditText) findViewById(R.id.et_user);
                editTextemail = (EditText) findViewById(R.id.et_em);

                editTextpw = (EditText) findViewById(R.id.et_pw);
                editTextcpw = (EditText) findViewById(R.id.et_cpw);

                progressBar = (ProgressBar) findViewById(R.id.progressBar);

                editTextFirstName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (editTextFirstName.getText().length() <= 3)
                    {
                        editTextFirstName.setError("First name should have minimum 3 letters");
                        editTextFirstName.requestFocus();
                    }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                /*
                //////////////////////////////////////////////////////////////////////////////////////////////
                TODO de rezolvat cu checkbox sa fie required !!!!!!!
                */
                reg_cb = (CheckBox) findViewById(R.id.reg_cb);
////////////////////////////////////////////////////////////////////////////////////


                // to enable the ico
                //@SuppressLint("UseCompatLoadingForDrawables") Drawable img = editTextFirstName.getContext().getResources().getDrawable( R.drawable.ic_baseline_person_24 );
                //editTextFirstName.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);


                
                String email = editTextemail.getText().toString().trim();
                String firstName = editTextFirstName.getText().toString().trim();
                String lastName = editTextLastName.getText().toString().trim();
                String username = editTextUserName.getText().toString().trim();
                String password = editTextpw.getText().toString().trim();
                String confirmPassword = editTextcpw.getText().toString().trim();

                // to remove the icon when text change
                // de lucrat la asta pentru a sterge iconita de la editTextFirstName dupa ce userul a inceput sa introduca text

                if (firstName.length() <= 3)
                {
                    editTextFirstName.setError("First name should contain minimum 3 letters");
                    editTextFirstName.requestFocus();

                }else if (lastName.length() == 0) {
                    editTextLastName.setError("Last name should contain minimum 3 letters");
                    editTextLastName.requestFocus();

                } else if (email.isEmpty() && !email.matches(emailPattern)) {
                    editTextemail.setError("Email is required!");
                    editTextemail.requestFocus();


                } else if (username.isEmpty()){
                    editTextUserName.setText(Arrays.toString(email.split("@")));
                    //reg_cb.requestFocus();  //reg_cb.requestFocus();

                }else if ((password.compareTo(confirmPassword) == 0) && password.length() >= 4)
                {
                    //reg_cb.requestFocus();  //reg_cb.requestFocus();
                    if (!reg_cb.isChecked()) {
                        reg_cb.setError("You have to agree the Term and Conditions");
                        //reg_cb.requestFocus();
                        //reg_cb.requestFocus();  //reg_cb.requestFocus();
                    }  else

                    {
                        User user = new User(firstName,lastName,email,password,username,usertype);










                        dbref.child(dbref.push().getKey()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                user_details_array.add(user);
                                String email1 = user_details_array.get(0).getEmail().toString();
                                String password1 = user_details_array.get(0).getPassword().toString();
                                mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                                                 @Override
                                                                                                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                                                     // created user with email and passwor/ working just with gmail

                                                                                                                     // //Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();
                                                                                                                     move_to_next_activity();
                                                                                                                 }
                                                                                                             }
                                ).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "There is an error, please try again later! If you are in a hurry contact our customer support department" + e, Toast.LENGTH_SHORT).show();
                                        move_to_next_activity();

                                    }
                                });
                                //user details saved in realtime database
                                Toast.makeText(getApplicationContext(), "User registered succesfully - " +email1 + "  /  " + password1, Toast.LENGTH_LONG).show();
                            }
                        });


                } }
        }});







        }



    public void move_to_next_activity(){
        handler=new Handler();
        // .postDelayed is a function that run after a specific time format  )
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(RegisterUser.this,Login.class);
                startActivity(intent); //start intent
                finish();
            }
        }, Long.parseLong(time)); }

    //create method for registerUser


    @Override
    public void onClick(View v) {

    }
}