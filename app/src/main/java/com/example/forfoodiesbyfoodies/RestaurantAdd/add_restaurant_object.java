package com.example.forfoodiesbyfoodies.RestaurantAdd;


//class object created for adding a restaurant to the database, this class is just for the admin, no other users can see it
public class add_restaurant_object {
    private String restaurant_name;
    private String restaurant_address;
    private String image_url;
    private String restaurant_description;
    private String food_type;
    private String stars;


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


    public String getRestaurant_description() {
        return restaurant_description;
    }

    public void setRestaurant_description(String restaurant_description) {
        this.restaurant_description = restaurant_description;
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

    public add_restaurant_object(String restaurant_name, String restaurant_address, String image_url, String restaurant_description, String food_type, String stars) {
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.image_url = image_url;
        this.food_type = food_type;
        this.stars = stars;
        this.restaurant_description = restaurant_description;

    }
}
