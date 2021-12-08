package com.example.forfoodiesbyfoodies.Reviews;

public class ReviewsData {


    String id_restaurant;
    String id_critic;

    String stars;

    //TEST works!!!! AMAZING!!!!!!!!!!!!!!
    String up;
    String down;
    String critic_name;
    String review_text;
    //TEST END





    //cosntructor to get exactly the review details
    public ReviewsData(String down,String up, String review_text,String critic_name, String stars)
    {
        this.down = down;
        this.up = up;
        this.review_text = review_text;
        this.critic_name = critic_name;
        this.stars = stars;

    }
    //default constructor-> making the getter and setter available whenver i will call and implement the object method
    public ReviewsData(){}


    public String getCritic_name() {
        return critic_name;
    }

    public void setCritic_name(String critic_name) {
        this.critic_name = critic_name;
    }

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

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }
}
