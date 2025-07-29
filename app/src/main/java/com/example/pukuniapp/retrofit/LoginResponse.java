package com.example.pukuniapp.retrofit;

import com.example.pukuniapp.classes.User;

public class LoginResponse {
    public String token;
    public User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
