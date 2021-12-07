package com.example.forfoodiesbyfoodies.AdapterStreetFood;

public class StreetFoodData {

    private String name;
    private String location;
    private String image;
    private String type;
    private String description;
    private String url;
    private String userid;


    public StreetFoodData(String name, String location, String image, String type, String description,  String userid) {
        this.name = name;
        this.location = location;
        this.image = image;
        this.type = type;
        this.description = description;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
