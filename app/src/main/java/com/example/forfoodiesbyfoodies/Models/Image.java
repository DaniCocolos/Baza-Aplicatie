package com.example.forfoodiesbyfoodies.Models;

//class object created for user profile picture
public class Image {

    private String name, url;

    public Image(){

    }
    public Image( String url){
        // this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
