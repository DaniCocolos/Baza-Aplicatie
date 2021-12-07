package com.example.forfoodiesbyfoodies.AdapterStreetFood;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Adapters.RestaurantAdapter;
import com.example.forfoodiesbyfoodies.Adapters.RestaurantsData;
import com.example.forfoodiesbyfoodies.Adapters.SelectedRestaurantPage;
import com.example.forfoodiesbyfoodies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StreetFoodAdapter extends RecyclerView.Adapter<StreetFoodAdapter.Viewholder> {

    Context context;
    ArrayList<StreetFoodData> list_of_streetfood;
    StreetFoodData object;

    // Constructor
    public StreetFoodAdapter(Context context, ArrayList<StreetFoodData> list_of_streetfood) {
        this.list_of_streetfood = list_of_streetfood;

    }

    @NonNull
    @Override
    public StreetFoodAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.restaurants_card_layout, viewGroup, false);
        view.setVisibility(View.VISIBLE);

        return new StreetFoodAdapter.Viewholder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull StreetFoodAdapter.Viewholder viewholder, int i) {
        StreetFoodData streetFoodData = list_of_streetfood.get(i);
        viewholder.restaurant_name.setText(streetFoodData.getName());
        viewholder.restaurant_address.setText(streetFoodData.getLocation());
        viewholder.restaurant_type.setText(streetFoodData.getDescription());


        Picasso.get().load(list_of_streetfood.get(i).getUrl()).fit().into(viewholder.restaurant_image);
        //Aici adaugam setText daca mai avem alte campuri, gen Vegetarian sau nu
        viewholder.object = streetFoodData;
    }

    @Override
    public int getItemCount() {
        return list_of_streetfood.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView restaurant_image;
        TextView restaurant_name, restaurant_address, restaurant_type;
        View rootView;
        int position;
        StreetFoodData object;
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
                    /*Log.d("demo", "Line - 74 - > URL-> " + object.getImage_url());
                    Log.d("demo", "Line - 73 - > NAME-> " + object.getRestaurant_name());
                    Log.d("demo", "Line - 73 - > ADDRESS-> " + object.getRestaurant_address());
                    Log.d("demo", "Line - 73 - > Rating-> " + object.getStars());
                    Log.d("demo", "Line - 73 - > Description-> " + object.getRestaurant_description());
                    Log.d("demo", "Line - 73 - > Type-> " + object.getFood_type());
                    Log.d("demo ", "STARS normal -> " + object.getStars());
                    Log.d("demo ", "STARS float -> " + Float.parseFloat(object.getStars()));
                    Log.d("demo", "URL OPENTABLE -> " + object.getUrl_opentable());*/

                    // Intent vi = new Intent( , SelectedRestaurantPage.class);



                    Intent it = new Intent(v.getContext(), SelectedRestaurantPage.class);

                    it.putExtra("object", (Parcelable) object);

                   /* it.putExtra("Name", object.getRestaurant_name());
                    it.putExtra("Address", object.getRestaurant_address());
                    it.putExtra("URL", object.getImage_url());
                    it.putExtra("Description", object.getRestaurant_description());
                    it.putExtra("Rating", object.getStars());
                    it.putExtra("Type", object.getFood_type());
                    it.putExtra("URL_OPENTABLE", object.getUrl_opentable());
                    it.putExtra("id", object.getId());*/

                    //working with parcelable or serializable
                    //it.putExtra("Obj", object);
                    v.getContext().startActivity(it);


                }

            });
        }
    }
}