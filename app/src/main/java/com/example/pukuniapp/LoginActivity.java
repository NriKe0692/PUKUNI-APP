package com.example.pukuniapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

            if (!password.equals("123456")) {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                return;
            }

            // Login correcto (puedes redirigir a MainActivity u otra)
            Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
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