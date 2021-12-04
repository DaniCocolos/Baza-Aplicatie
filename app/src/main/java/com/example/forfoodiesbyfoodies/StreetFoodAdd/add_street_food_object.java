package com.example.forfoodiesbyfoodies.StreetFoodAdd;

public class add_street_food_object {
    private String name;
    private String location;
    private String image;
    private String type;
    private String description;
    private String url;
    private String userid;

    public add_street_food_object(String name, String location, String image, String type, String description,  String userid) {
        this.name = name;
        this.location = location;
        this.image = image;
        this.type = type;
        this.description = description;
        this.userid= userid;

    }
    //empty constructor
    public add_street_food_object(){}

    public String getId() {
        return url;
    }

    public void setId(String id_street_food) {
        this.url = id_street_food;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }























}
