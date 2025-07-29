package com.example.pukuniapp.retrofit;

public class RegisterRequest {
    String user_name;
    String user_lastname;
    int role_id;
    boolean status;
    String email;
    String password_hash;

    public RegisterRequest(String user_name, String user_lastname, String email, String password_hash) {
        this.user_name = user_name;
        this.user_lastname = user_lastname;
        this.email = email;
        this.password_hash = password_hash;
        this.role_id = 2;   // 2: encuestador
        this.status = false;
    }
}