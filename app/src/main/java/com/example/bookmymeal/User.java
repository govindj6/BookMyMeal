package com.example.bookmymeal;

public class User {
    int id;
    String username;
    String email;
    String mobile;
    String password;

    public User(int id, String username, String email, String mobile, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }
    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
