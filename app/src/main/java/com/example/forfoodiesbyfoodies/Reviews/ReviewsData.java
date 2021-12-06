package com.example.forfoodiesbyfoodies.Reviews;

public class ReviewsData {


    String id_restaurant;
    String id_critic;
    String review_text;
    String stars;

    public ReviewsData (String id_restaurant ,String id_critic, String review_text,String stars)
    {
        this.id_restaurant = id_restaurant;
        this.id_critic = id_critic;
        this.review_text = review_text;
        this.stars = stars;
    }
    //cosntructor to get exactly the review details
    public ReviewsData(String id_critic, String review_text,String stars)
    {
        this.id_critic = id_critic;
        this.review_text = review_text;
        this.stars = stars;
    }
    //default constructor-> making the getter and setter available whenver i will call and implement the object method
    public ReviewsData(){}

    public String getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(String id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getId_critic() {
        return id_critic;
    }

    public void setId_critic(String id_critic) {
        this.id_critic = id_critic;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
