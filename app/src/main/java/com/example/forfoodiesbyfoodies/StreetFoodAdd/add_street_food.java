package com.example.forfoodiesbyfoodies.StreetFoodAdd;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.RestaurantAdd.add_restaurant;
import com.example.forfoodiesbyfoodies.RestaurantAdd.add_restaurant_object;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.EventListener;

public class add_street_food extends AppCompatActivity implements View.OnClickListener {

    ImageView add_streetfood_image;
    EditText et_add_street_food_name,et_add_street_food_location, et_add_street_food_description,et_add_street_food_type;
    Button add_streetfood,upload;
    CheckBox chip4;


    Uri imageUrl;
    ProgressBar pbar;

    private StorageReference refStorage;
    private DatabaseReference dbref;

    private FirebaseUser user;
    private FirebaseAuth mAuth;


    private static final int IMAGERQ = 1;
    private String type = "Non-vegetarian";
    Boolean image_checker= false;
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_street_food);
        //mAuth = FirebaseAuth.getInstance();
        //mAuth.signInWithEmailAndPassword("koko@yahoo.com", "123123123").addOnSuccessListener(authResult -> Log.d("Login", "authResult: " +authResult.toString()));
        dbref = FirebaseDatabase.getInstance().getReference("StreetFood");
        refStorage = FirebaseStorage.getInstance().getReference("street_food_photos");

        et_add_street_food_name = findViewById(R.id.et_add_street_food_name); //name of street food
        et_add_street_food_location = findViewById(R.id.et_add_street_food_address); //address
        et_add_street_food_description = findViewById(R.id.et_add_street_food_description); //description
       // et_add_street_food_type = findViewById(R.id.et_add_street_food_type); //checbkox veg or not
        chip4 = findViewById(R.id.chip4);

        chip4.setOnClickListener(v -> type = "vegetarian");

        upload = findViewById(R.id.upload); //invisible button
        add_streetfood = findViewById(R.id.add_street_food);//orange button, add to dbs

        add_streetfood_image = findViewById(R.id.add_street_food_image); // streetfood image
        add_streetfood_image.setOnClickListener(this);

        pbar = findViewById(R.id.progressBar3);
        pbar.setVisibility(View.GONE);

        add_streetfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            pbar.setVisibility(View.VISIBLE);
            upload.performClick();
            }
        });




        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = dbref.push().getKey();
                StorageReference reference = refStorage.child(id + "." + getExtension(imageUrl));
                reference.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Ok + " , Toast.LENGTH_SHORT).show();
                        //Log.d("URL", "URL: "+ refStorage.getDownloadUrl());

                        Log.d("URL2", "URLfrom download: " +taskSnapshot.getError());
                        pbar.setVisibility(View.GONE);
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String name = et_add_street_food_name.getText().toString();
                                String description = et_add_street_food_description.getText().toString();
                                String location = et_add_street_food_location.getText().toString();
                                String url = uri.toString();
                                String userid = "1928uduasjhd21iasdads";
                               // String name, String location, String image, String type, String description,  String userid


                                add_street_food_object obj = new add_street_food_object(name,location,url,type,description,userid);
                                dbref.child(id).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(add_street_food.this, "Succesfully added:" + task.toString(), Toast.LENGTH_LONG).show();
                                        }else
                                        {
                                            Toast.makeText(add_street_food.this,"THere was an error: " + task.getException(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        });//sad
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error reference.putFile-> " + e.toString(), Toast.LENGTH_LONG).show();
                        pbar.setVisibility(View.GONE);
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(getApplicationContext(), "cancel reference.putFile ", Toast.LENGTH_LONG).show();
                        pbar.setVisibility(View.GONE);
                    }
                });




            }});

    }





    // Script to choose photo from phone library START HERE-----------------------------------------------------------------------
    public void choose_and_upload_profile_picture() {
        Intent i =new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, IMAGERQ);
        }
    // Script to choose photo from phone library END HERE-----------------------------------------------------------------------



    // return Extension part from image selected by user (jpeg,png etc) START HERE----------------------------------------------
    private String getExtension(Uri uri)
    {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        //        if (imageUrl != null)
        //        {
        //        StorageReference sref =  refStorage.child(userId.toString()+mime.getExtensionFromMimeType(resolver.getType(uri)));
        //
        //        }
        return mime.getExtensionFromMimeType(resolver.getType(uri));
    }
    //return Extension part from image selected by user END HERE-----------------------------------------------------------------


    // Method which is executed at runtime after the image was selected START HERE-----------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == IMAGERQ &&  resultCode == RESULT_OK && data!=null && data.getData() != null){
            imageUrl = data.getData();
            Picasso.get().load(imageUrl).into(add_streetfood_image);
            //upload.callOnClick(); this upload to account
            image_checker = true;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_street_food_image) {
            choose_and_upload_profile_picture();
        }

    }
}