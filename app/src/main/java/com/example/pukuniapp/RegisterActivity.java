package com.example.pukuniapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextNombre, editTextApellidos, editTextEmail, editTextPassword;
    CheckBox checkBoxTerms;
    Button buttonCrearCuenta;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vincular vistas
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidos = findViewById(R.id.editTextApellidos);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        buttonCrearCuenta = findViewById(R.id.buttonCrearCuenta);
        loginText = findViewById(R.id.textViewLogin);

        // Manejar clic del botón
        buttonCrearCuenta.setOnClickListener(v -> {
            String nombre = editTextNombre.getText().toString().trim();
            String apellidos = editTextApellidos.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            boolean aceptaTerminos = checkBoxTerms.isChecked();

            // Validación básica
            if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!aceptaTerminos) {
                Toast.makeText(this, "Debes aceptar los Términos y Condiciones", Toast.LENGTH_SHORT).show();
                return;
            }

            // Aquí puedes hacer lo que quieras con los datos (guardar, enviar al servidor, etc.)
            Toast.makeText(this, "Cuenta creada para: " + nombre + " " + apellidos, Toast.LENGTH_LONG).show();
        });

        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}