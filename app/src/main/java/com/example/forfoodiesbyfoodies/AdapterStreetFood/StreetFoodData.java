package com.example.forfoodiesbyfoodies.AdapterStreetFood;

public class StreetFoodData {

    private String name;
    private String location;
    private String image;
    private String type;
    private String description;
    private String id;
    private String userid;


    public StreetFoodData(String name, String location, String image, String type, String description,  String userid, String id) {
        this.name = name;
        this.location = location;
        this.image = image;
        this.type = type;
        this.description = description;
        this.userid = userid;
        this.id= id;
    }
    public StreetFoodData(){}

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



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
