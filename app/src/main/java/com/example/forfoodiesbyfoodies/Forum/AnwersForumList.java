package com.example.forfoodiesbyfoodies.Forum;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Models.User;
import com.example.forfoodiesbyfoodies.R;
import com.example.forfoodiesbyfoodies.Reviews.ReviewsList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AnwersForumList extends AppCompatActivity {

    RecyclerView rc_chats;
    TextView tv_selected_topic, tv_selected_description, a_forum_user;
    EditText input_text;
    Button button5;
    DatabaseReference dbref,dbref2;
    FirebaseAuth mAuth;

    ArrayList<ForumObject>list_of_answers;
    SecondAdapterForum secondAdapterForum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anwers_forum_list);

        Intent get = getIntent();
        //TODO need to get THE TOPIC ID FROM THE PAST RECYCLER
        String ref_for_db = get.getStringExtra("ref_for_db");

        String title = get.getStringExtra("title");
        String description = get.getStringExtra("desc");
        String username = get.getStringExtra("username");
        String id = get.getStringExtra("id");

        //set topic question
        tv_selected_topic = findViewById(R.id.tv_selected_topic);
        tv_selected_topic.setText(title);

        //set topic description
        tv_selected_description = findViewById(R.id.tv_selected_description);
        tv_selected_description.setText(description);

        //set topic usernme -- is the user who opened the topic
        a_forum_user = findViewById(R.id.a_forum_user);
        a_forum_user.setText("by: " + username);

        //find recycler view
        rc_chats = findViewById(R.id.rc_forum2);

        //dbs initialization
        input_text = findViewById(R.id.input_text);

        dbref = FirebaseDatabase.getInstance().getReference("topicAnswers");
        dbref2 = FirebaseDatabase.getInstance().getReference("_users_");

        mAuth = FirebaseAuth.getInstance();
        String user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();




        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    dbref2.child(user_id).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String username = (String) snapshot.getValue();

                            //String username = (String) snapshot.getValue();
                            String answer = input_text.getText().toString();
                            ForumObject obj = new ForumObject();
                            obj.setUsername(username);
                           // Log.d("username", username);
                            obj.setAnswerText(answer);
                           // Log.d("answer", answer);

                            //Log.d("photo", photo);
                            dbref.child(id).child(Objects.requireNonNull(dbref.push().getKey())).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(AnwersForumList.this, "Succesfully added a comment to this topic!", Toast.LENGTH_LONG).show();

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






        rc_chats.setLayoutManager(new LinearLayoutManager(this));

        list_of_answers = new ArrayList<>();
        secondAdapterForum = new SecondAdapterForum(this, list_of_answers);
        rc_chats.setAdapter(secondAdapterForum);
        dbref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    ForumObject object = dataSnapshot.getValue(ForumObject.class);
                    list_of_answers.add(object);
                }
                if (list_of_answers.size() == 0 )
                {
                    Toast.makeText(AnwersForumList.this,"There are no reviews for this restaurant", Toast.LENGTH_LONG).show();

                }
                secondAdapterForum.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AnwersForumList.this, "There was an error from database: please try again later -> " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });






    }
}