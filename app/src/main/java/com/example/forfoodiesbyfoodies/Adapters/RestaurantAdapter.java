package com.example.forfoodiesbyfoodies.Adapters;



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

import com.example.forfoodiesbyfoodies.R;
import com.squareup.picasso.Picasso;
import com.example.forfoodiesbyfoodies.Adapters.RestaurantsData;
import java.util.ArrayList;
import java.util.zip.Inflater;


public class RestaurantAdapter extends  RecyclerView.Adapter<RestaurantAdapter.Viewholder> {

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
                    Log.d("demo" , "Line - 74 - > URL-> " + object.getImage_url());
                    Log.d("demo" , "Line - 73 - > NAME-> " + object.getRestaurant_name());
                    Log.d("demo" , "Line - 73 - > ADDRESS-> " + object.getRestaurant_address());
                    Log.d("demo" , "Line - 73 - > Rating-> " + object.getStars());
                    Log.d("demo" , "Line - 73 - > Description-> " + object.getRestaurant_description());
                    Log.d("demo" , "Line - 73 - > Type-> " + object.getFood_type());

                    // Intent vi = new Intent( , SelectedRestaurantPage.class);

                    //TODO AICI AM DEZACTIVAT TOT CE VEZI DEDESUPT
                   Intent it = new Intent(v.getContext() , SelectedRestaurantPage.class);
                    it.putExtra("Name", object.getRestaurant_name());
                    it.putExtra("Address", object.getRestaurant_address());
                    it.putExtra("URL", object.getImage_url());
                    it.putExtra("Description", object.getRestaurant_description());
                    it.putExtra("Rating", object.getStars());
                    it.putExtra("Type", object.getFood_type());

                   // it.putExtra("Obj", object.getClass());
                    v.getContext().startActivity(it);



                }

            });
            //Aici definim unde sunt locatiile de setText.
        }
    }

}
