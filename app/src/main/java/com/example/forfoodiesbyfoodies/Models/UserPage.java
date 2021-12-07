package com.example.forfoodiesbyfoodies.Models;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.Views.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class UserPage extends AppCompatActivity implements View.OnClickListener {
    private FirebaseUser user;
    private DatabaseReference dbref;
    private StorageReference refStorage;


    private String userId;
    private Button up_fn_set_btn;

    private User user1;

    private  int x = 0;
    ImageView profilePicture;
    TextView view_users;
    private static final int IMAGERQ = 1;
    Button choose_image,up_button_upload;
    Uri imageUrl;
    ProgressBar pbar;


    Button buton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        buton =  findViewById(R.id.up_logout);
        buton.setOnClickListener(this);

        view_users = findViewById(R.id.view_users);
        view_users.setOnClickListener(v -> startActivity(new Intent(UserPage.this, View_Users.class)));
        user = FirebaseAuth.getInstance().getCurrentUser();
        dbref = FirebaseDatabase.getInstance().getReference("_users_");
        userId = user.getUid();


        refStorage = FirebaseStorage.getInstance().getReference("profile_pictures");

        //Toast.makeText(UserPage.this, " Userobject value : " + user.getEmail(), Toast.LENGTH_LONG).show();

        final TextView user_fn = findViewById(R.id.up_user_fn);
        final TextView user_ln = findViewById(R.id.up_user_ln);
        final TextView user_email = findViewById(R.id.up_user_email);
        final TextView up_account_type = findViewById(R.id.user_type);
        final ImageView up_fn_set_btn = findViewById(R.id.up_fn_set_btn);
        final ImageView user_profile_picture = findViewById(R.id.profilePicture);
        final ImageView up_ln_set_button = findViewById(R.id.up_ln_set_button);
        final ImageView up_email_button = findViewById(R.id.up_email_button);

        //choose and upload  -> 2 in 1 <-
        choose_image =  findViewById(R.id.choose_image);
        profilePicture = findViewById(R.id.profilePicture);
        pbar = findViewById(R.id.up_progressBar);
        up_button_upload = findViewById(R.id.up_button_upload);


        //setOnClickListener group start here
        up_fn_set_btn.setOnClickListener(this);
        up_ln_set_button.setOnClickListener(this);
        up_email_button.setOnClickListener(this);
        choose_image.setOnClickListener(this);
        //setOnClickListener group end here



        pbar.setVisibility(View.INVISIBLE); // we don't need it until we click the upload button


        // UPLOAD IMAGE TO THE STORAGE AND UPDATE THE profilePicture in the realtime dbs START HERE ----------------------------
        up_button_upload.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                pbar.setVisibility(View.VISIBLE); // let the progress bar to be visible
                //pbar.setProgressTintList(ColorStateList.valueOf (Color.GREEN));
                pbar.setBackgroundColor(Color.GREEN);
                if (x == 0)
                {
                    x++;
                    up_button_upload.callOnClick();
                    return;
                }
                StorageReference reference = refStorage.child(userId.toString() +getExtension(imageUrl));
                reference.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(UserPage.this, "Your profile picture was updated!", Toast.LENGTH_LONG).show();
                        pbar.setVisibility(View.GONE);
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //Image image = new Image( uri.toString());
                                //dbref.child(userId).setValue(image);
                                dbref.child(userId).child("profilePicture").setValue(uri.toString()).addOnCompleteListener(
                                        new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(UserPage.this, "Your picture was succesfully uploaded", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                ).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(UserPage.this, "There was an error: " + e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserPage.this, "Error for getting the download url: " + e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserPage.this, "Error: " +e.toString(), Toast.LENGTH_LONG).show();
                        pbar.setVisibility(View.GONE);
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = (100 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        pbar.setProgress((int)progress);
                    }
                });


            }
        });
        // UPLOAD IMAGE TO THE STORAGE AND UPDATE THE profilePicture in the realtime dbs END HERE -------------------------------------------------


        //showing User details: fn, ln, email and profile pic START HERE-------------------------------------------------------------------
        dbref.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    String fn = user.getFirstname();
                    String ln = user.getLastname();
                    String email = user.getEmail();
                    String userType = user.getUsertype();
                    String profilePicture = user.getProfilePicture();

                    Picasso.get().load(profilePicture).into(user_profile_picture);
                    user_fn.setText(fn);
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
        //showing User details END HERE-----------------------------------------------------------------------------------------


    } //  <-- this is for onCreate


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
            Picasso.get().load(imageUrl).into(profilePicture);
            up_button_upload.callOnClick();
        }
    }
    // Method which is executed at runtime after the image was selected END HERE--------------------------------------------------



    // Script to choose photo from phone library START HERE-----------------------------------------------------------------------
    public void choose_and_upload_profile_picture() {
        Intent i =new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, IMAGERQ);

    }
    // Script to choose photo from phone library END HERE-----------------------------------------------------------------------

    //-----CHANGING FIRST NAME START--------------------------------------------------------------------------------------------
    public void change_fn() {
        EditText user_fn = findViewById(R.id.up_user_fn);

        dbref.child(userId).child("firstname").setValue(user_fn.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    //-----CHANGING LAST NAME START HERE--------------------------------------------------------------------------------------
    public void change_ln() {
        EditText user_ln = findViewById(R.id.up_user_ln);
        dbref.child(userId).child("lastname").setValue(user_ln.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(UserPage.this, "You've just updated your lastname",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(UserPage.this, "There was an error. Please try again in 5 minutes", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //----CHANGING LAST NAME END HERE-------------------------------------------------------------------------------

    //-- CHANGING EMAIL START HERE----------------------------------------------------------------------------------
    public void change_email(){
        EditText user_em = findViewById(R.id.up_user_email);
        user.updateEmail(user_em.getText().toString().trim());
        dbref.child(userId).child("email").setValue(user_em.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(UserPage.this, "You've just updated your email, please login again", Toast.LENGTH_LONG).show();
                    Button log_out = findViewById(R.id.up_logout);
                    log_out.performClick();
                    startActivity(new Intent(UserPage.this, Login.class));
                }
            }
        });

    }
    //-- CHANGING EMAIL END HERE----------------------------------------------------------------------------------

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
            case R.id.up_fn_set_btn:
                change_fn();
                break;
            case R.id.choose_image:
                choose_and_upload_profile_picture();
                break;
            case R.id.up_email_button:
                change_email();
                break;
            case R.id.up_ln_set_button:
                change_ln();
                break;
        }
    }

}