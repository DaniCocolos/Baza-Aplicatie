package com.example.forfoodiesbyfoodies.Forum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Models.Image;
import com.example.forfoodiesbyfoodies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SecondAdapterForum extends RecyclerView.Adapter<SecondAdapterForum.Viewholder> {
    Context context;
    ArrayList<ForumObject>list_of_chats;
    ForumObject object;




    public SecondAdapterForum(Context context, ArrayList<ForumObject> list_of_chats)
    {
        this.list_of_chats = list_of_chats;
    }

    @NonNull
    @Override
    public SecondAdapterForum.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_chats, parent,false );
        return new SecondAdapterForum.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondAdapterForum.Viewholder viewHolder, int i) {
            ForumObject object = list_of_chats.get(i);
            viewHolder.answerText.setText(object.getAnswerText());
            viewHolder.chat_username.setText(object.getUsername());
            if (object.getUrl() != null)
            {
                Picasso.get().load(object.getUrl()).into(viewHolder.photo);
            }

    }

    @Override
    public int getItemCount() {
        return list_of_chats.size();
    }



    public class Viewholder extends RecyclerView.ViewHolder {
        TextView topic;
        View rootView;
        TextView answerText, chat_username;
        ImageView photo;
        ForumObject object;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            answerText = itemView.findViewById(R.id.tv_comment);
            chat_username = itemView.findViewById(R.id.chat_username);
            photo = itemView.findViewById(R.id.photo_username);
            //username = itemView.findViewById(R.id.chat_username);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    *//*Intent intent = new Intent(v.getContext(), AnwersForumList.class);
                    intent.putExtra("id", object.getTopic_id());
                    intent.putExtra("title", object.getTopic_title());
                    intent.putExtra("desc", object.getTopic_description());
                    intent.putExtra("username", object.getUsername());*//*
*//*

                    Log.d("id", "id->" +object.getTopic_id());
                    Log.d("title", "title->" + object.getTopic_title());
                    Log.d("desc", "desc->" + object.getTopic_description());
                    Log.d("username", "username-> " + object.getUsername());

*//*


                    //v.getContext().startActivity(intent);
                }
            });*/
        }
    }
}
