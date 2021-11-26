package com.example.forfoodiesbyfoodies.Adapters;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RestaurantAdapter extends  RecyclerView.Adapter<RestaurantAdapter.Viewholder>{

    Context context;
    ArrayList<RestaurantsData> list_of_restaurants;

    // Constructor
    public RestaurantAdapter( Context context, ArrayList<RestaurantsData> list_of_restaurants) {
        this.list_of_restaurants = list_of_restaurants;
    }

    @NonNull
    @Override
    public RestaurantAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        RestaurantsData rest= list_of_restaurants.get(position);
        holder.restaurant_name.setText(rest.getRestaurant_name());
        holder.restaurant_address.setText(rest.getRestaurant_address());
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
         TextView restaurant_name, restaurant_address;
         View rootView;
         int position;
         RestaurantsData object;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            restaurant_address = itemView.findViewById(R.id.restaurant_address);
            restaurant_image = itemView.findViewById(R.id.restaurant_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo" , "root.onClickListenerr: URL-> " + object.getRestaurant_address());

                }
            });
            //Aici definim unde sunt locatiile de setText.
        }
    }
}
