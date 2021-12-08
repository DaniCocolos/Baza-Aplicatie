package com.example.forfoodiesbyfoodies.Forum;


import static java.text.DateFormat.getDateTimeInstance;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class AddTopic extends AppCompatActivity {

    EditText et_topic_description,et_topic_title;
    Button button_add_topics;
    DatabaseReference dbref,dbref_user;
    FirebaseDatabase mFB;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topic);

        et_topic_description = findViewById(R.id.et_topic_description);
        et_topic_title = findViewById(R.id.et_topic_title);
        button_add_topics = findViewById(R.id.button_add_topics);


        et_topic_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() < 10 )
                    {
                        Toast.makeText(AddTopic.this, "Please enter minimum 10 characters!", Toast.LENGTH_SHORT).show();
                        button_add_topics.setClickable(false);
                    }else
                    {
                        button_add_topics.setClickable(true);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_topic_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() < 10)
                        {
                            button_add_topics.setClickable(false);
                            Toast.makeText(AddTopic.this, "Please enter minimum 10 characters!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            button_add_topics.setClickable(true);
                        }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //it should follow the model -> forum / id_topic (date, topic description ,id, title, username)
        dbref = FirebaseDatabase.getInstance().getReference("forum");
        dbref_user = FirebaseDatabase.getInstance().getReference("_users_");

        mAuth = FirebaseAuth.getInstance();


        button_add_topics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_user = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                dbref_user.child(id_user).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String username = (String) snapshot.getValue();



                        String desc = et_topic_description.getText().toString();
                        String title = et_topic_title.getText().toString();
                        String id = dbref.push().getKey();

                        ForumObject object = new ForumObject();
                        object.setUsername(username);
                        object.setTopic_description(desc);
                        object.setTopic_id(id);
                        //object.setTimestamp(String.valueOf(ServerValue.TIMESTAMP));
                        object.setTopic_title(title);
                        dbref.child(Objects.requireNonNull(id)).setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(AddTopic.this, "You have succesfully created a topic! Check it out!", Toast.LENGTH_SHORT).show();
                                    //TODO intent catre topicul lui
                                    //finish(); //ending activity and getting back to previous
                                    startActivity(new Intent(AddTopic.this, ForumList.class));

                                }
                                else
                                {
                                    Toast.makeText(AddTopic.this, "There was an error, please try again later: -> " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });












    }
    public static String getTimeDate(long timestamp){
        try{
            DateFormat dateFormat = getDateTimeInstance();
            Date netDate = (new Date(timestamp));
            return dateFormat.format(netDate);
        } catch(Exception e) {
            return "date";
        }
    }
}