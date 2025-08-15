package com.example.pukuniapp;

import static com.example.pukuniapp.sqlite.DBHelper.TABLE_FORMULARIO_FLORA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pukuniapp.sqlite.DBHelper;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Opcional: ocultar título por defecto
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        final Drawable overflowIcon = toolbar.getOverflowIcon();
        if (overflowIcon != null) {
            overflowIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }

        SharedPreferences prefs = getSharedPreferences("PukuniPrefs", MODE_PRIVATE);
        String userName = prefs.getString("user_name", "Usuario");
        String lastName = prefs.getString("user_last_name", "");

        TextView tvUserName = findViewById(R.id.toolbar_name);
        tvUserName.setText(userName + " " + lastName);

        LinearLayout formBtn = findViewById(R.id.form_click);

        formBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
        });

        LinearLayout viewFormsBtn = findViewById(R.id.view_forms_click);

        viewFormsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewFormsActivity.class);
            startActivity(intent);
        });

        // For testing only
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_FLORA, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int lugarId = cursor.getInt(cursor.getColumnIndexOrThrow("lugar_id"));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                int franjaId = cursor.getInt(cursor.getColumnIndexOrThrow("franja_id"));
                int unidadMuestreoId = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_muestreo_id"));
                int parcelaId = cursor.getInt(cursor.getColumnIndexOrThrow("parcela_id"));
                int forofitoId = cursor.getInt(cursor.getColumnIndexOrThrow("forofito_id"));
                int subParcelaId = cursor.getInt(cursor.getColumnIndexOrThrow("sub_parcela_id"));
                String tamanio = cursor.getString(cursor.getColumnIndexOrThrow("tamanio"));
                int unidadVegetacionId = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_vegetacion_id"));
                int paisId = cursor.getInt(cursor.getColumnIndexOrThrow("pais_id"));
                int departamentoId = cursor.getInt(cursor.getColumnIndexOrThrow("departamento_id"));
                int provinciaId = cursor.getInt(cursor.getColumnIndexOrThrow("provincia_id"));
                int distritoId = cursor.getInt(cursor.getColumnIndexOrThrow("distrito_id"));
                String localidad = cursor.getString(cursor.getColumnIndexOrThrow("localidad"));
                double este = cursor.getDouble(cursor.getColumnIndexOrThrow("este"));
                double norte = cursor.getDouble(cursor.getColumnIndexOrThrow("norte"));
                double altitud = cursor.getDouble(cursor.getColumnIndexOrThrow("altitud"));
                int claseId = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));
                int ordenId = cursor.getInt(cursor.getColumnIndexOrThrow("orden_id"));
                int familiaId = cursor.getInt(cursor.getColumnIndexOrThrow("familia_id"));
                int generoId = cursor.getInt(cursor.getColumnIndexOrThrow("genero_id"));
                int especieId = cursor.getInt(cursor.getColumnIndexOrThrow("especie_id"));
                String nombreComun = cursor.getString(cursor.getColumnIndexOrThrow("nombre_comun"));
                int autorId = cursor.getInt(cursor.getColumnIndexOrThrow("autor_id"));
                int individuos = cursor.getInt(cursor.getColumnIndexOrThrow("individuos"));
                double dap = cursor.getDouble(cursor.getColumnIndexOrThrow("dap"));
                double altura = cursor.getDouble(cursor.getColumnIndexOrThrow("altura"));
                double valorCobertura = cursor.getDouble(cursor.getColumnIndexOrThrow("valor_cobertura"));
                int habitoId = cursor.getInt(cursor.getColumnIndexOrThrow("habito_id"));
                int estadioId = cursor.getInt(cursor.getColumnIndexOrThrow("estadio_id"));
                int fenologiaId = cursor.getInt(cursor.getColumnIndexOrThrow("fenologia_id"));
                String usos = cursor.getString(cursor.getColumnIndexOrThrow("usos"));
                String observaciones = cursor.getString(cursor.getColumnIndexOrThrow("observaciones"));
                String datosPlanta = cursor.getString(cursor.getColumnIndexOrThrow("datos_planta"));

                Log.d("FloraData",
                        "ID: " + id +
                                ", Lugar: " + lugarId +
                                ", Fecha: " + fecha +
                                ", Franja: " + franjaId +
                                ", UnidadMuestreo: " + unidadMuestreoId +
                                ", Parcela: " + parcelaId +
                                ", Forofito: " + forofitoId +
                                ", SubParcela: " + subParcelaId +
                                ", Tamaño: " + tamanio +
                                ", UnidadVegetacion: " + unidadVegetacionId +
                                ", País: " + paisId +
                                ", Departamento: " + departamentoId +
                                ", Provincia: " + provinciaId +
                                ", Distrito: " + distritoId +
                                ", Localidad: " + localidad +
                                ", Este: " + este +
                                ", Norte: " + norte +
                                ", Altitud: " + altitud +
                                ", Clase: " + claseId +
                                ", Orden: " + ordenId +
                                ", Familia: " + familiaId +
                                ", Género: " + generoId +
                                ", Especie: " + especieId +
                                ", NombreComún: " + nombreComun +
                                ", Autor: " + autorId +
                                ", Individuos: " + individuos +
                                ", DAP: " + dap +
                                ", Altura: " + altura +
                                ", ValorCobertura: " + valorCobertura +
                                ", Hábito: " + habitoId +
                                ", Estadio: " + estadioId +
                                ", Fenología: " + fenologiaId +
                                ", Usos: " + usos +
                                ", Observaciones: " + observaciones +
                                ", DatosPlanta: " + datosPlanta
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            // Lógica de logout
            SharedPreferences prefs = getSharedPreferences("PukuniPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}