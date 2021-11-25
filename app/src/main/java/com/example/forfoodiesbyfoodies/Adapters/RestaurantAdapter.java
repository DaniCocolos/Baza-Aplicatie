package com.example.forfoodiesbyfoodies.Adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forfoodiesbyfoodies.Models.RestaurantsData;
import com.example.forfoodiesbyfoodies.R;

import java.util.ArrayList;




public class RestaurantAdapter extends  RecyclerView.Adapter<RestaurantAdapter.Viewholder>{

    private Context context;
    private ArrayList<RestaurantsData> RestaurantDataArray;

    // Constructor
    public RestaurantAdapter(Context context, ArrayList<RestaurantsData> RestaurantDataArray) {
        this.context = context;
        this.RestaurantDataArray = RestaurantDataArray;
    }

    @NonNull
    @Override
    public RestaurantAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.Viewholder holder, int position) {
        RestaurantsData details = RestaurantDataArray.get(position);
        holder.restaurant_name.setText(details.getRestaurant_name());
        holder.restaurant_address.setText(details.getrestaurant_address());
        holder.restaurant_image.setBackground(details.getImage_url());


    }

    @Override
    public int getItemCount() {
        return RestaurantDataArray.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView restaurant_image;
        private TextView restaurant_name, restaurant_address;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            restaurant_address = itemView.findViewById(R.id.restaurant_address);
            restaurant_image = itemView.findViewById(R.id.restaurant_image);
        }
    }
}
