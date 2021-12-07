package com.example.forfoodiesbyfoodies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RestaurantAdapter extends  RecyclerView.Adapter<RestaurantAdapter.Viewholder> {

    Context context;
    ArrayList<RestaurantsData> list_of_restaurants;
    RestaurantsData object;



    // Constructor
    public RestaurantAdapter( Context context, ArrayList<RestaurantsData> list_of_restaurants) {
        this.list_of_restaurants = list_of_restaurants;

    }

    @NonNull
    @Override
    public RestaurantAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_card_layout, parent, false);
        view.setVisibility(View.VISIBLE);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        RestaurantsData rest= list_of_restaurants.get(position);
        holder.restaurant_name.setText(rest.getRestaurant_name());
        holder.restaurant_address.setText(rest.getRestaurant_address());
        holder.restaurant_type.setText(rest.getFood_type());



        float rating = Float.parseFloat(rest.getStars());
        //float g = 4 - rating;
        holder.restaurant_rating.setRating(rating);
        holder.restaurant_rating.setStepSize(1);
        Picasso.get().load(list_of_restaurants.get(position).getImage_url()).fit().into(holder.restaurant_image);
        //Aici adaugam setText daca mai avem alte campuri, gen Vegetarian sau nu
        holder.object =  rest;


    }


    @Override
    public int getItemCount() {
        return list_of_restaurants.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
         ImageView restaurant_image;
         TextView restaurant_name, restaurant_address, restaurant_type;
         View rootView;
         int position;
         RestaurantsData object;
         RatingBar restaurant_rating;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            restaurant_address = itemView.findViewById(R.id.restaurant_address);
            restaurant_image = itemView.findViewById(R.id.restaurant_image);
            restaurant_type = itemView.findViewById(R.id.cl_restaurant_type2);
            restaurant_rating = itemView.findViewById(R.id.restaurant_rating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo" , "Line - 74 - > URL-> " + object.getImage_url());
                    Log.d("demo" , "Line - 73 - > NAME-> " + object.getRestaurant_name());
                    Log.d("demo" , "Line - 73 - > ADDRESS-> " + object.getRestaurant_address());
                    Log.d("demo" , "Line - 73 - > Rating-> " + object.getStars());
                    Log.d("demo" , "Line - 73 - > Description-> " + object.getRestaurant_description());
                    Log.d("demo" , "Line - 73 - > Type-> " + object.getFood_type());
                    Log.d("demo ", "STARS normal -> " + object.getStars());
                    Log.d("demo ", "STARS float -> " + Float.parseFloat(object.getStars()));
                    Log.d("demo", "URL OPENTABLE -> " +object.getUrl_opentable());

                    // Intent vi = new Intent( , SelectedRestaurantPage.class);

                    //TODO AICI AM DEZACTIVAT TOT CE VEZI DEDESUPT

                   Intent it = new Intent(v.getContext() , SelectedRestaurantPage.class);


                    it.putExtra("Name", object.getRestaurant_name());
                    it.putExtra("Address", object.getRestaurant_address());
                    it.putExtra("URL", object.getImage_url());
                    it.putExtra("Description", object.getRestaurant_description());
                    it.putExtra("Rating", object.getStars());
                    it.putExtra("Type", object.getFood_type());
                    it.putExtra("URL_OPENTABLE", object.getUrl_opentable());
                    it.putExtra("id", object.getId());

                            //working with parcelable or serializable
                            //it.putExtra("Obj", object);
                    v.getContext().startActivity(it);



                }

            });
            //Aici definim unde sunt locatiile de setText.
        }
    }

}
