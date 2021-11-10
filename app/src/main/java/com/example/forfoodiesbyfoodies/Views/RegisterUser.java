package com.example.forfoodiesbyfoodies.Views;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    //String x is used to get the checkbox status when the user press Register


    List<User> user_details_array = new ArrayList<>();




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        dbref = FirebaseDatabase.getInstance().getReference("_users_");
        mAuth = FirebaseAuth.getInstance();

        logo2back = (ImageView) findViewById(R.id.im_logo2);
        logo2back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
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
                // object for user (saving the user details and user type client//

               //User user = new User( editTextFirstName.toString(),  editTextLastName.toString(),  editTextemail.toString(),
                        //editTextpw.toString(),editTextUserName.toString(), usertype);

                //String xa = dbref.getUid();
                //dbref.child(dbref.push().getKey()).setValue(user)
                dbref.child(dbref.push().getKey()).setValue(RegisterUser()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        user_details_array.add(RegisterUser());
                        String email1 = user_details_array.get(0).getEmail().toString();
                        String password1 = user_details_array.get(0).getPassword().toString();
                        mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                                         @Override
                                                                                                         public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                                             // created user with email and passwor/ working just with gmail

                                                                                                             //Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();
                                                                                                             move_to_next_activity();
                                                                                                         }
                                                                                                     }
                        ).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "There is an error, please try again later! If you are in a hurry contact our customer support department", Toast.LENGTH_SHORT).show();
                                move_to_next_activity();

                            }
                        });
                        //user details saved in realtime database
                        Toast.makeText(getApplicationContext(), "User registered succesfully - " +email1 + "  /  " + password1, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


        editTextFirstName = (EditText) findViewById(R.id.et_fn);
        editTextLastName = (EditText) findViewById(R.id.et_sn);
        editTextUserName = (EditText) findViewById(R.id.et_user);
        editTextemail = (EditText) findViewById(R.id.et_em);

        editTextpw = (EditText) findViewById(R.id.et_pw);
        editTextcpw = (EditText) findViewById(R.id.et_cpw);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

////////////////////////////////////////////////////////////////////////////////////////////////
       //de rezolvat cu checkbox sa fie required !!!!!!!
        reg_cb = (CheckBox) findViewById(R.id.reg_cb);
////////////////////////////////////////////////////////////////////////////////////


        }


    // asta nu se executa
    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_logo2:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn_register_user:
                registerUser();
                break;
        }

    }

     */
    //asta nu se executa dezactivata pentru ca se poate modela altfel si tot aceiasi modalitate poate fi folosita in Activitatile unde sunt mult mai
    // multe functii


    public void move_to_next_activity(){
        handler=new Handler();
        // .postDelayed is a function that run after a specific time format  )
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent); //start intent
                finish();
            }
        }, Long.parseLong(time)); }

    //create method for registerUser
    private User RegisterUser() {
        String email = editTextemail.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String username = editTextUserName.getText().toString().trim();
        String password = editTextpw.getText().toString().trim();
        String confirmPassword = editTextcpw.getText().toString().trim();



        if (firstName.isEmpty()){
            editTextFirstName.setError("First name is required!");
            editTextFirstName.requestFocus();
            finish();
            return null;


        }else
        if (lastName.isEmpty()){
            editTextLastName.setError("Last name is required!");
            editTextLastName.requestFocus();
            finish();
            return null;
        }else

        if (email.isEmpty()){
            editTextemail.setError("Email is requiered!");
            editTextemail.requestFocus();
            finish();
            return null;
        }else
        if (username.isEmpty()){
            editTextUserName.setError("Username is requiered!");
            editTextUserName.requestFocus();
            finish();
            return null;
        }else
        if (password.isEmpty()){
            editTextpw.setError("Password is requiered!");
            editTextpw.requestFocus();
            finish();
            return null;
        }else
        if (confirmPassword.isEmpty()){
            editTextcpw.setError("Password is requiered!");
            editTextcpw.requestFocus();
            finish();
            return null;

        }else
        if (!(password.compareTo(confirmPassword) == 0)){
            editTextcpw.setError("Password is not the same!");
            editTextpw.setError("Password is not the same!");
            editTextpw.requestFocus();
            editTextcpw.requestFocus();
            finish();
            return null;
        }else
            if (!reg_cb.isChecked()) {
                reg_cb.setError("Check me");
                reg_cb.requestFocus();
                finish();
                return null;
            }




        User user = new User(firstName, lastName, email, password, username, usertype);

        return user;

    }

    @Override
    public void onClick(View v) {

    }
}