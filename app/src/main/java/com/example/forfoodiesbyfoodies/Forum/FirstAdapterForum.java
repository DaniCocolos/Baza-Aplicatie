package com.example.forfoodiesbyfoodies.Forum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Adapters.RestaurantAdapter;
import com.example.forfoodiesbyfoodies.Adapters.RestaurantsData;
import com.example.forfoodiesbyfoodies.R;

import java.util.ArrayList;


public class FirstAdapterForum extends RecyclerView.Adapter<FirstAdapterForum.Viewholder> {
    Context context;
    ArrayList<ForumObject> list_of_topics;
    ForumObject object;



    //adaptor constructor

    public FirstAdapterForum(Context context, ArrayList<ForumObject> list_of_topics)
    {
        this.list_of_topics = list_of_topics;

    }
    @NonNull
    @Override
    public FirstAdapterForum.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_topic, parent,false );
            return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstAdapterForum.Viewholder viewholder, int i) {
            //setting the input textes

        ForumObject object = list_of_topics.get(i);

        viewholder.textView12.setText(object.getUsername());
        viewholder.tv_topicBoddy.setText(object.getTopic_description());
        viewholder.tv_TopicTitle.setText(object.getTopic_title());
        viewholder.object = object;

    }



    @Override
    public int getItemCount() {
        return list_of_topics.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        TextView topic;
        View rootView;
        TextView tv_TopicTitle, tv_topicBoddy,textView12;
        ForumObject object;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            tv_TopicTitle = itemView.findViewById(R.id.tv_TopicTitle);
            tv_topicBoddy = itemView.findViewById(R.id.tv_topicBoddy);
            textView12 = itemView.findViewById(R.id.textView12);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AnwersForumList.class);
                    intent.putExtra("title", object.getTopic_title());
                   // Log.d("title", "title is -> " +object.getTopic_title());

                    intent.putExtra("desc", object.getTopic_description());
                    intent.putExtra("id", object.getTopic_id());
                    intent.putExtra("username", object.getUsername());

                    // sending the title,descriptiona and id to the next recycler

                    v.getContext().startActivity(intent);
                }
            });








        }
    }
}