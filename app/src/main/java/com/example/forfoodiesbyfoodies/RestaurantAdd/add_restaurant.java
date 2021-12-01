package com.example.forfoodiesbyfoodies.RestaurantAdd;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class add_restaurant extends AppCompatActivity implements View.OnClickListener {


    private static final Object P = "TAG";
    private DatabaseReference dbref;
    private FirebaseUser user;
    private StorageReference refStorage;

    Button add_restaurant,upload;
    EditText et_add_restaurant_name,et_add_restaurant_address;
    ImageView add_restaurant_image;
    private static final int IMAGERQ = 1;
    private String userUid;
    Uri imageUrl;
    ProgressBar pbar;

    private static final String TAG = "add_restaurant";
    private boolean image_checker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        //initialize the firebase reference - storage and database where the restaurant details and photo are stored
        dbref = FirebaseDatabase.getInstance().getReference("Restaurants");
        refStorage = FirebaseStorage.getInstance().getReference("restaurant_photos");
        //-----------------------------------------------------------------------------------------------------------

        et_add_restaurant_name = findViewById(R.id.et_add_restaurant_name); // restaurant name
        et_add_restaurant_address = findViewById(R.id.et_add_restaurant_address); // restaurant address
        add_restaurant = findViewById(R.id.add_restaurant); // button to add to the database the record
        upload = findViewById(R.id.upload); //invisible button
        add_restaurant_image = findViewById(R.id.add_restaurant_image); // restaurant image
        pbar = findViewById(R.id.progressBar3);
        //-----------------------------------------------------------------------------------------------------------

        //userUid = user.getUid();

        pbar.setVisibility(View.GONE);
        add_restaurant_image.setOnClickListener(this);
        add_restaurant.setOnClickListener(new View.OnClickListener() {
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
                StorageReference reference = refStorage.child(id+"." + getExtension(imageUrl));
                reference.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
                        pbar.setVisibility(View.GONE);
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String restaurant_name = et_add_restaurant_name.getText().toString();
                                String restaurant_address = et_add_restaurant_address.getText().toString();
                                //String restaurant_type = et_add_restaurant_type.getText().toString();
                                // String restaurant_description = et_add_restaurant_description.getText().toString();
                                //TODO de adaugat in design pentru description si VEGETARIAN SAU NU o face tataie

                                String restaurant_description = "this is a very and complex description";
                                String restaurant_type = "VEGETARIAN";

                                final String restaurant_rating = "0";
                                String url = uri.toString();
                                add_restaurant_object obj = new add_restaurant_object( restaurant_name, restaurant_address, url, restaurant_description,restaurant_type,restaurant_rating);

                                dbref.child(id).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(add_restaurant.this, "Succesfully added", Toast.LENGTH_LONG).show();
                                        Intent i = getIntent();
                                        finish();
                                        startActivity(i);
                                    }
                                }).addOnFailureListener(e -> Toast.makeText(add_restaurant.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show());
                            }
                        }).addOnFailureListener(e -> Toast.makeText(add_restaurant.this,"Error", Toast.LENGTH_LONG).show());

                    }
                }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "error: getDownloadUrl -> " +e.toString(), Toast.LENGTH_LONG).show());
            }
        });







    }



    // check if the restaura END HERE---------------------------------------------------------------------------------------------
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
            Picasso.get().load(imageUrl).into(add_restaurant_image);
            //upload.callOnClick(); this upload to account
            image_checker = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add_restaurant_image:

                choose_and_upload_profile_picture();
                break;
        }
    }
    // Method which is executed at runtime after the image was selected END HERE--------------------------------------------------


}
