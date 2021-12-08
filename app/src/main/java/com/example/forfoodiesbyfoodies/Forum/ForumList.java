package com.example.forfoodiesbyfoodies.Forum;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumList extends AppCompatActivity  {
    // recyclerview
    RecyclerView rc_forum;
    Button button_add_topic;


    // Arraylist for storing data
    ArrayList<ForumObject> list_of_topics;
    FirstAdapterForum AdaptorForum;
    DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        rc_forum = findViewById(R.id.rc_forum);
        button_add_topic = findViewById(R.id.button_add_topic);
        button_add_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForumList.this, AddTopic.class));
            }
        });
        rc_forum.setLayoutManager(new LinearLayoutManager(this));
        dbref = FirebaseDatabase.getInstance().getReference("forum");
        list_of_topics = new ArrayList<>();
        AdaptorForum = new FirstAdapterForum(this, list_of_topics);
        rc_forum.setAdapter(AdaptorForum);
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    ForumObject object = dataSnapshot.getValue(ForumObject.class);
                    list_of_topics.add(object);
                    Log.d("id", "id->" + object.getTopic_id());
                    Log.d("id", "id->" + "Asd");
                 }
                AdaptorForum.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ForumList.this, "There was an error from database: please try again later -> " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }

}