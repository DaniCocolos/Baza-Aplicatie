package com.example.forfoodiesbyfoodies.Adapters;

import android.graphics.drawable.Drawable;

public class RestaurantsData {
     String restaurant_name;
     String restaurant_address;
     String image_url;
     String restaurant_description;
     String food_type;
     String stars;
     String url_opentable;


    public RestaurantsData(String restaurant_name, String restaurant_address, String image_url, String restaurant_description, String food_type, String stars,String url_opentable) {
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.image_url = image_url;
        this.restaurant_description = restaurant_description;
        this.food_type = food_type;
        this.stars = stars;
        this.url_opentable = url_opentable;
    }
    //default constructor
    public RestaurantsData(){

    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_address() {
        return restaurant_address;
    }

    public void setRestaurant_address(String restaurant_address) {
        this.restaurant_address = restaurant_address;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }



    public String getRestaurant_description() {
        return restaurant_description;
    }

    public void setRestaurant_description(String restaurant_description) {
        this.restaurant_description = restaurant_description;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getUrl_opentable() {
        return url_opentable;
    }

    public void setUrl_opentable(String url_opentable) {
        this.url_opentable = url_opentable;
    }

}
