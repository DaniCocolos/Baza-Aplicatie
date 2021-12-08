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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_streetfood_card_layout, viewGroup, false);
        view.setVisibility(View.VISIBLE);

        return new StreetFoodAdapter.Viewholder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull StreetFoodAdapter.Viewholder viewholder, int i) {
        StreetFoodData streetFoodData = list_of_streetfood.get(i);

        viewholder.et_Title.setText(streetFoodData.getName());
        viewholder.et_Description.setText(streetFoodData.getLocation());



        Picasso.get().load(list_of_streetfood.get(i).getImage()).fit().into(viewholder.im_street_food_view);

        viewholder.object = streetFoodData;
    }

    @Override
    public int getItemCount() {
        return list_of_streetfood.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView im_street_food_view;
        TextView et_Title, et_Description;
        View rootView;
        int position;
        StreetFoodData object;



        public Viewholder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            et_Title = itemView.findViewById(R.id.et_Title);
            im_street_food_view = itemView.findViewById(R.id.im_street_food_view); //photo
            et_Description = itemView.findViewById(R.id.et_Description);
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



                    Intent it = new Intent(v.getContext(), SelectedStreetFoodPage.class);



                   /* it.putExtra("Name", object.getRestaurant_name());
                    it.putExtra("Address", object.getRestaurant_address());
                    it.putExtra("URL", object.getImage_url());
                    it.putExtra("Description", object.getRestaurant_description());
                    it.putExtra("Rating", object.getStars());
                    it.putExtra("Type", object.getFood_type());
                    it.putExtra("URL_OPENTABLE", object.getUrl_opentable());

                    it.putExtra("id", object.getId());*/
                    //working with parcelable or serializable


                    it.putExtra("title", object.getName());
                    it.putExtra("location", object.getLocation());
                    it.putExtra("photo", object.getImage());
                    it.putExtra("description", object.getDescription());
                    it.putExtra("type", object.getType());
                    it.putExtra("id", object.getId());
                    v.getContext().startActivity(it);


                }

            });
        }
    }
}