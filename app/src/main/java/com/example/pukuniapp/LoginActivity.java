package com.example.pukuniapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pukuniapp.retrofit.ApiClient;
import com.example.pukuniapp.retrofit.ApiService;
import com.example.pukuniapp.retrofit.LoginRequest;
import com.example.pukuniapp.retrofit.LoginResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button btnLogin;
    TextView forgotPassword, registerTextClickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Vincula los elementos del layout
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        btnLogin = findViewById(R.id.btnLogin);
        forgotPassword = findViewById(R.id.forgotPassword);
        registerTextClickable = findViewById(R.id.registerTextClickable);

        btnLogin.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            ApiService api = ApiClient.getRetrofit().create(ApiService.class);
            LoginRequest request = new LoginRequest(email, password);

            api.login(request).enqueue(new retrofit2.Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        LoginResponse loginResponse = response.body();
                        Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                        Gson gson = new Gson();
                        String json = gson.toJson(response.body().user);
                        System.out.println("response JSON: " + json);

                        SharedPreferences sharedPreferences = getSharedPreferences("PukuniPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("auth_token", response.body().token);
                        editor.putString("user_name", response.body().user.name);
                        editor.putString("user_last_name", response.body().user.lastname);
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        try {
                            String errorJson = response.errorBody().string();
                            System.out.println("response ERROR JSON: " + errorJson);

                            // Puedes parsearlo si quieres algo más detallado
                            JSONObject jsonObject = new JSONObject(errorJson);
                            String errorMsg = jsonObject.optString("error", "Error desconocido");
                            Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error desconocido", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


        // Ir a forgot password
        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        // Ir a registro
        registerTextClickable.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}