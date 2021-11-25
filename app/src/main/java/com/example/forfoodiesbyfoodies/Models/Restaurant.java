package com.example.forfoodiesbyfoodies.Models;

public class Restaurant {
    private String restaurant_name;
    private String restaurant_address;
    private String image_url;

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

    public Restaurant(String restaurant_name, String restaurant_address, String image_url){
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.image_url = image_url;

    }
}
