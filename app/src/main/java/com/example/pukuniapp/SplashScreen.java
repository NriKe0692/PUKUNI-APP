package com.example.pukuniapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pukuniapp.retrofit.ApiClient;
import com.example.pukuniapp.retrofit.ApiService;

import retrofit2.Call;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            validarToken();
        }, 2000);
    }

    private void validarToken() {
        SharedPreferences prefs = getSharedPreferences("PukuniPrefs", MODE_PRIVATE);
        String token = prefs.getString("auth_token", null);

        if (token == null) {
            irALogin();
        } else {
            ApiService api = ApiClient.getRetrofit().create(ApiService.class);
            api.verifyToken("Bearer " + token).enqueue(new retrofit2.Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                    if (response.isSuccessful()) {
                        irAHome();
                    } else {
                        irALogin();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    irALogin();
                }
            });
        }
    }

    private void irALogin() {
        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void irAHome() {
        Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}