package com.example.forfoodiesbyfoodies.Views;



public class User {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String username;
    private String usertype;
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }



    public User(String firstname, String lastname, String email, String password, String username, String usertype) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email =email;
    this.password = password;
    this.username = username;
    this.usertype = usertype;



    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public User(){

    }
}
