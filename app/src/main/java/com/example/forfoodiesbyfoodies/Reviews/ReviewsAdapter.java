package com.example.forfoodiesbyfoodies.Reviews;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Adapters.RestaurantAdapter;
import com.example.forfoodiesbyfoodies.Adapters.RestaurantsData;
import com.example.forfoodiesbyfoodies.Adapters.SelectedRestaurantPage;
import com.example.forfoodiesbyfoodies.Models.User;
import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;


public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.Viewholder> {
    ArrayList<ReviewsData> list_of_reviews;



    public ReviewsAdapter( Context context, ArrayList<ReviewsData> list_of_reviews) {
        this.list_of_reviews = list_of_reviews;
    }

    @NonNull
    @Override
    public ReviewsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view. //TODO Aici trebuie sa fac card review
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_card_layout, parent, false);
        view.setVisibility(View.VISIBLE);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.Viewholder viewholder, int i) {
        //here we are setting text and images and all on each card
        ;

        ReviewsData obj = list_of_reviews.get(i);
        viewholder.textView10.setText(obj.getUp());//up
        viewholder.textView11.setText(obj.getDown());//down
       /* viewholder.textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int c = Integer.parseInt(viewholder.textView11.getText().toString());
                viewholder.textView11.setText("2231");
            }
        });*/
        viewholder.et_Description.setText(obj.getReview_text());



        }

    @Override
    public int getItemCount() {
        return list_of_reviews.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        View rootView;
        ReviewsData object;
        TextView et_Description; //review

        TextView textView10; //down
        TextView textView11; // up
        TextView textView12;  //critic username
        ImageView imageButton, imageButton2;
        Integer down = 0;
        Integer up = 0;



        public Viewholder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;

            et_Description = itemView.findViewById(R.id.et_Description);
            textView10 = itemView.findViewById(R.id.textView10); //up number
            textView11 = itemView.findViewById(R.id.textView11);//down number
            //textView12 = itemView.findViewById(R.id.textView12);//critic username
            imageButton2 = itemView.findViewById(R.id.imageButton2);//up button
            imageButton = itemView.findViewById(R.id.imageButton);
            imageButton.setClickable(true);
            imageButton2.setClickable(true);

            //TODO still, the button thumbs up and down are working but just on app, is not saving in the database:(
            imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    textView10.setText(new StringBuilder().append("").append(++down));
                }
            });
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView11.setText(new StringBuilder().append("").append(++up));
                }
            });
            //--------------------------------------------------------------------------------------


        }
    }



}
