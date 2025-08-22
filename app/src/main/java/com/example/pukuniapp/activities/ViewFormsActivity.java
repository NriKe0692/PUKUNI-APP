package com.example.pukuniapp.activities;

import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_FLORA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pukuniapp.R;
import com.example.pukuniapp.adapters.FormFloraAdapter;
import com.example.pukuniapp.classes.FormFlora;
import com.example.pukuniapp.helpers.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ViewFormsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FormFloraAdapter floraAdapter;
    List<FormFlora> formFloraList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_forms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerFlora);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DBHelper(this);
        formFloraList = getFloraListFromDB();

        floraAdapter = new FormFloraAdapter(formFloraList);
        recyclerView.setAdapter(floraAdapter);

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

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        }
    }

    private List<FormFlora> getFloraListFromDB() {
        List<FormFlora> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_FLORA, null);

        if (cursor.moveToFirst()) {
            do {
                String localidad = cursor.getString(cursor.getColumnIndexOrThrow("localidad"));
                int especie = cursor.getInt(cursor.getColumnIndexOrThrow("especie_id"));
                double altura = cursor.getDouble(cursor.getColumnIndexOrThrow("altura"));
                String usos = cursor.getString(cursor.getColumnIndexOrThrow("usos"));
                String imageURI = cursor.getString(cursor.getColumnIndexOrThrow("image_uri"));

                FormFlora formFloraTemp = new FormFlora();
                formFloraTemp.setLocalidad(localidad);
                formFloraTemp.setEspecie_id(especie);
                formFloraTemp.setAltura(altura);
                formFloraTemp.setUsos(usos);
                formFloraTemp.setImageUri(imageURI);

                list.add(formFloraTemp);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}