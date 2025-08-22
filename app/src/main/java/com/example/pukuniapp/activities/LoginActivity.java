package com.example.pukuniapp.activities;

import static com.example.pukuniapp.helpers.DBHelper.TABLE_AUTOR;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLASE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLIMA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESPECIE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTACION_MUESTREO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTADIO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FAMILIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FENOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FOROFITO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FRANJA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_GENERO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_HABITO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_METODOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ORDEN;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_PAIS;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_PARCELA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_SUB_PARCELA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TEMPORADA_EVALUACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_MUESTREAL;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_MUESTREO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_VEGETACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ZONA;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.Autor;
import com.example.pukuniapp.classes.Clase;
import com.example.pukuniapp.classes.Clima;
import com.example.pukuniapp.classes.Especie;
import com.example.pukuniapp.classes.EstacionMuestreo;
import com.example.pukuniapp.classes.Estadio;
import com.example.pukuniapp.classes.Familia;
import com.example.pukuniapp.classes.Fenologia;
import com.example.pukuniapp.classes.Forofito;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.Genero;
import com.example.pukuniapp.classes.Habito;
import com.example.pukuniapp.classes.Metodologia;
import com.example.pukuniapp.classes.Orden;
import com.example.pukuniapp.classes.Pais;
import com.example.pukuniapp.classes.Parcela;
import com.example.pukuniapp.classes.SubParcela;
import com.example.pukuniapp.classes.TemporadaEvaluacion;
import com.example.pukuniapp.classes.UnidadMuestreal;
import com.example.pukuniapp.classes.UnidadMuestreo;
import com.example.pukuniapp.classes.UnidadVegetacion;
import com.example.pukuniapp.classes.Zona;
import com.example.pukuniapp.retrofit.ApiClient;
import com.example.pukuniapp.retrofit.ApiService;
import com.example.pukuniapp.retrofit.LoginRequest;
import com.example.pukuniapp.retrofit.LoginResponse;
import com.example.pukuniapp.helpers.DBHelper;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                        loadSQLiteTables();

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

    private void loadSQLiteTables(){
        ApiService api = ApiClient.getRetrofit().create(ApiService.class);
        SharedPreferences prefs = this.getSharedPreferences("PukuniPrefs", android.content.Context.MODE_PRIVATE);
        String token = prefs.getString("auth_token", null);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d("tokeninfo", "token: " + token);

        if(token == null){
            irALogin();
        }else{
            loadAutores(db, api, token, TABLE_AUTOR);
            loadClases(db, api, token, TABLE_CLASE);
            loadEspecie(db, api, token, TABLE_ESPECIE);
            loadEstacionMuestreo(db, api, token, TABLE_ESTACION_MUESTREO);
            loadEstadio(db, api, token, TABLE_ESTADIO);
            loadFamilia(db, api, token, TABLE_FAMILIA);
            loadFenologia(db, api, token, TABLE_FENOLOGIA);
            loadForofito(db, api, token, TABLE_FOROFITO);
            loadFranja(db, api, token, TABLE_FRANJA);
            loadGenero(db, api, token, TABLE_GENERO);
            loadHabito(db, api, token, TABLE_HABITO);
            loadOrden(db, api, token, TABLE_ORDEN);
            loadPais(db, api, token, TABLE_PAIS);
            loadParcela(db, api, token, TABLE_PARCELA);
            loadSubParcela(db, api, token, TABLE_SUB_PARCELA);
            loadUnidadMuestreo(db, api, token, TABLE_UNIDAD_MUESTREO);
            loadUnidadVegetacion(db, api, token, TABLE_UNIDAD_VEGETACION);
            loadTemporadasEvaluacion(db, api, token, TABLE_TEMPORADA_EVALUACION);
            loadZonas(db, api, token, TABLE_ZONA);
            loadMetodologias(db, api, token, TABLE_METODOLOGIA);
            loadClimas(db, api, token, TABLE_CLIMA);
            loadUnidadesMuestreal(db, api, token, TABLE_UNIDAD_MUESTREAL);
        }
    }

    public void loadTemporadasEvaluacion(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME){
        api.getTemporadasEvaluacion("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<TemporadaEvaluacion>> call, Response<List<TemporadaEvaluacion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TemporadaEvaluacion> temporadaEvaluacionList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for(TemporadaEvaluacion temporadaEvaluacion : temporadaEvaluacionList){
                        ContentValues values = new ContentValues();
                        values.put("temporada_evaluacion_id", temporadaEvaluacion.getTemporada_evaluacion_id());
                        values.put("temporada_evaluacion_name", temporadaEvaluacion.getTemporada_evaluacion_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<TemporadaEvaluacion>> call, Throwable t) {
                irALogin();
            }
        });
    }

    public void loadZonas(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME){
        api.getZonas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Zona>> call, Response<List<Zona>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Zona> zonasList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for(Zona zona : zonasList){
                        ContentValues values = new ContentValues();
                        values.put("zona_id", zona.getZona_id());
                        values.put("zona_name", zona.getZona_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Zona>> call, Throwable t) {
                irALogin();
            }
        });
    }

    public void loadMetodologias(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME){
        api.getMetodologias("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Metodologia>> call, Response<List<Metodologia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Metodologia> metodologiaList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for(Metodologia metodologia : metodologiaList){
                        ContentValues values = new ContentValues();
                        values.put("metodologia_id", metodologia.getMetodologia_id());
                        values.put("metodologia_name", metodologia.getMetodologia_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Metodologia>> call, Throwable t) {
                irALogin();
            }
        });
    }

    public void loadClimas(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME){
        api.getClimas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Clima>> call, Response<List<Clima>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Clima> climaList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for(Clima clima : climaList){
                        ContentValues values = new ContentValues();
                        values.put("clima_id", clima.getClima_id());
                        values.put("clima_name", clima.getClima_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Clima>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadUnidadesMuestreal(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME){
        api.getUnidadesMuestreal("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UnidadMuestreal>> call, Response<List<UnidadMuestreal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnidadMuestreal> unidadMuestrealList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for(UnidadMuestreal unidadMuestreal : unidadMuestrealList){
                        ContentValues values = new ContentValues();
                        values.put("unidad_muestreal_id", unidadMuestreal.getUnidad_muestreal_id());
                        values.put("unidad_muestreal_name", unidadMuestreal.getUnidad_muestreal_name());
                        values.put("franja_id", unidadMuestreal.getFranja_id());
                        values.put("metodologia_id", unidadMuestreal.getMetodologia_id());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<UnidadMuestreal>> call, Throwable t) {
                irALogin();
            }
        });
    }

    public void loadAutores(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getAutores("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Autor> autoresList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for(Autor autor : autoresList){
                        ContentValues values = new ContentValues();
                        values.put("autor_id", autor.getAutor_id());
                        values.put("autor_name", autor.getAutor_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Autor>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadClases(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME){
        api.getClases("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Clase>> call, Response<List<Clase>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Clase> clasesList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (Clase clase : clasesList) {
                        ContentValues values = new ContentValues();
                        values.put("clase_id", clase.getClase_id());
                        values.put("clase_name", clase.getClase_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Clase>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadEspecie(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME){
        api.getEspecies("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Especie>> call, Response<List<Especie>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Especie> especiesList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (Especie especie : especiesList) {
                        ContentValues values = new ContentValues();
                        values.put("especie_id", especie.getEspecie_id());
                        values.put("especie_name", especie.getEspecie_name());
                        values.put("genero_id", especie.getGenero_id());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Especie>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadEstacionMuestreo(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME){
        api.getEstacionMuestreoList("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<EstacionMuestreo>> call, Response<List<EstacionMuestreo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<EstacionMuestreo> estacionMuestreoList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (EstacionMuestreo estacionMuestreo : estacionMuestreoList) {
                        ContentValues values = new ContentValues();
                        values.put("estacion_muestreo_id", estacionMuestreo.getEstacion_muestreo_id());
                        values.put("estacion_muestreo_name", estacionMuestreo.getEstacion_muestreo_name());
                        values.put("pais_id", estacionMuestreo.getPais_id());
                        values.put("pais_name", estacionMuestreo.getPais_name());
                        values.put("departamento_id", estacionMuestreo.getDepartamento_id());
                        values.put("departamento_name", estacionMuestreo.getDepartamento_name());
                        values.put("provincia_id", estacionMuestreo.getProvincia_id());
                        values.put("provincia_name", estacionMuestreo.getProvincia_name());
                        values.put("distrito_id", estacionMuestreo.getDistrito_id());
                        values.put("distrito_name", estacionMuestreo.getDistrito_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<EstacionMuestreo>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadEstadio(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME){
        api.getEstadios("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Estadio>> call, Response<List<Estadio>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Estadio> estdiosList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (Estadio estadio : estdiosList) {
                        ContentValues values = new ContentValues();
                        values.put("estadio_id", estadio.getEstadio_id());
                        values.put("estadio_name", estadio.getEstadio_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Estadio>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadFamilia(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getFamilias("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Familia>> call, Response<List<Familia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Familia> familiasList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (Familia familia : familiasList) {
                        ContentValues values = new ContentValues();
                        values.put("familia_id", familia.getFamilia_id());
                        values.put("familia_name", familia.getFamilia_name());
                        values.put("orden_id", familia.getOrden_id());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Familia>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadFenologia(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getFenologias("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Fenologia>> call, Response<List<Fenologia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Fenologia> fenologiasList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for(Fenologia fenologia : fenologiasList){
                        ContentValues values = new ContentValues();
                        values.put("fenologia_id", fenologia.getFenologia_id());
                        values.put("fenologia_name", fenologia.getFenologia_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Fenologia>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadForofito(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getForofitos("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Forofito>> call, Response<List<Forofito>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Forofito> forofitoList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for(Forofito forofito : forofitoList){
                        ContentValues values = new ContentValues();
                        values.put("forofito_id", forofito.getForofito_id());
                        values.put("forofito_name", forofito.getForofito_name());
                        values.put("parcela_id", forofito.getParcela_id());
                        values.put("parcela_name", forofito.getParcela_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Forofito>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadFranja(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getFranjas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Franja>> call, Response<List<Franja>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Franja> subEstacionesList = response.body();

                    for (Franja franja : subEstacionesList) {
                        Log.d("API_RESPONSE", String.valueOf(franja.getEstacion_muestreo_id()));
                    }

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for(Franja franja : subEstacionesList){
                        ContentValues values = new ContentValues();
                        values.put("franja_id", franja.getFranja_id());
                        values.put("franja_name", franja.getFranja_name());
                        values.put("estacion_muestreo_id", franja.getEstacion_muestreo_id());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Franja>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadGenero(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getGeneros("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Genero>> call, Response<List<Genero>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Genero> generosList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (Genero genero : generosList) {
                        ContentValues values = new ContentValues();
                        values.put("genero_id", genero.getGenero_id());
                        values.put("genero_name", genero.getGenero_name());
                        values.put("familia_id", genero.getFamilia_id());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Genero>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadHabito(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getHabitos("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Habito>> call, Response<List<Habito>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Habito> habitosList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (Habito habito : habitosList) {
                        ContentValues values = new ContentValues();
                        values.put("habito_id", habito.getHabito_id());
                        values.put("habito_name", habito.getHabito_id());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Habito>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadOrden(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getOrdenes("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Orden>> call, Response<List<Orden>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Orden> ordenesList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (Orden orden : ordenesList) {
                        ContentValues values = new ContentValues();
                        values.put("orden_id", orden.getOrden_id());
                        values.put("orden_name", orden.getOrden_name());
                        values.put("clase_id", orden.getClase_id());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Orden>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadPais(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getPaises("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pais> paisList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (Pais pais : paisList) {
                        ContentValues values = new ContentValues();
                        values.put("pais_id", pais.getPais_id());
                        values.put("pais_name", pais.getPais_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Pais>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadParcela(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getParcelas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Parcela>> call, Response<List<Parcela>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try{
                        List<Parcela> parcelaList = response.body();

                        for (Parcela parcela : parcelaList) {
                            Log.d("PARCELA", parcela.getParcela_name());
                        }

                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Parcela parcela : parcelaList) {
                            ContentValues values = new ContentValues();
                            values.put("parcela_id", parcela.getParcela_id());
                            values.put("parcela_name", parcela.getParcela_name());
                            values.put("franja_id", parcela.getFranja_id());
                            db.insert(TABLE_NAME, null, values);
                        }
                    } catch (Exception e) {
                        Log.e("ERROR!!!", e.getMessage());
                        throw new RuntimeException(e);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Parcela>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadSubParcela(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getSubparcelas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<SubParcela>> call, Response<List<SubParcela>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SubParcela> subParcelaListList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (SubParcela subParcela : subParcelaListList) {
                        ContentValues values = new ContentValues();
                        values.put("subparcela_id", subParcela.getSubparcela_id());
                        values.put("subparcela_name", subParcela.getSubparcela_name());
                        values.put("parcela_id", subParcela.getParcela_id());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<SubParcela>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadUnidadMuestreo(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getUnidadMuestreoList("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UnidadMuestreo>> call, Response<List<UnidadMuestreo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnidadMuestreo> unidadMuestreoList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (UnidadMuestreo unidadMuestreo : unidadMuestreoList) {
                        ContentValues values = new ContentValues();
                        values.put("unidad_muestreo_id", unidadMuestreo.getUnidad_muestreo_id());
                        values.put("unidad_muestreo_name", unidadMuestreo.getUnidad_muestreo_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UnidadMuestreo>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadUnidadVegetacion(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME) {
        api.getUnidadVegetacion("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UnidadVegetacion>> call, Response<List<UnidadVegetacion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnidadVegetacion> unidadVegetacionList = response.body();

                    db.delete(TABLE_NAME, null, null);
                    db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                    for (UnidadVegetacion unidadVegetacion : unidadVegetacionList) {
                        ContentValues values = new ContentValues();
                        values.put("unidad_vegetacion_id", unidadVegetacion.getUnidad_vegetacion_id());
                        values.put("unidad_vegetacion_name", unidadVegetacion.getUnidad_vegetacion_name());
                        db.insert(TABLE_NAME, null, values);
                    }
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<UnidadVegetacion>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void irALogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}