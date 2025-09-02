package com.example.pukuniapp.activities;

import static com.example.pukuniapp.helpers.DBHelper.TABLE_ACTIVIDAD;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_AUTOR;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CATEGORIA_ABUNDANCIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLASE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLIMA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CONDICION_REPRODUCTIVA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESPECIE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTACION_MUESTREO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTADIO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTADO_CONSERVACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FAMILIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FENOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FOROFITO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FRANJA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_GENERO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_GRUPO_TROFICO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_HABITO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_INDICADOR;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_METODOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_MICROHABITAT;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ORDEN;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_PAIS;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_PARCELA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_SUB_PARCELA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_SUSTRATO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TEMPORADA_EVALUACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TIPO_REGISTRO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TIPO_TRAMPA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_MUESTREAL;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_MUESTREO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_VEGETACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_USOS;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ZONA;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.Actividad;
import com.example.pukuniapp.classes.Autor;
import com.example.pukuniapp.classes.CategoriaAbundancia;
import com.example.pukuniapp.classes.Clase;
import com.example.pukuniapp.classes.Clima;
import com.example.pukuniapp.classes.CondicionReproductiva;
import com.example.pukuniapp.classes.Especie;
import com.example.pukuniapp.classes.EstacionMuestreo;
import com.example.pukuniapp.classes.Estadio;
import com.example.pukuniapp.classes.EstadoConservacion;
import com.example.pukuniapp.classes.Familia;
import com.example.pukuniapp.classes.Fenologia;
import com.example.pukuniapp.classes.Forofito;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.Genero;
import com.example.pukuniapp.classes.GrupoTrofico;
import com.example.pukuniapp.classes.Habito;
import com.example.pukuniapp.classes.Indicador;
import com.example.pukuniapp.classes.Metodologia;
import com.example.pukuniapp.classes.Microhabitat;
import com.example.pukuniapp.classes.Orden;
import com.example.pukuniapp.classes.Pais;
import com.example.pukuniapp.classes.Parcela;
import com.example.pukuniapp.classes.SubParcela;
import com.example.pukuniapp.classes.Sustrato;
import com.example.pukuniapp.classes.TemporadaEvaluacion;
import com.example.pukuniapp.classes.TipoRegistro;
import com.example.pukuniapp.classes.TipoTrampa;
import com.example.pukuniapp.classes.TipoUsos;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                        editor.putInt("user_id", response.body().user.id);
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

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        if(token == null){
            irALogin();
        }else{
            loadAutores(db, api, token, TABLE_AUTOR, executor, handler, () -> {
                Log.d("TESTING!", "FIN 1");
                loadClases(db, api, token, TABLE_CLASE, executor, handler, () -> {
                    Log.d("TESTING!", "FIN 2");
                    loadEspecie(db, api, token, TABLE_ESPECIE, executor, handler, () -> {
                        Log.d("TESTING!", "FIN 3");
                        loadEstacionMuestreo(db, api, token, TABLE_ESTACION_MUESTREO, executor, handler, () -> {
                            Log.d("TESTING!", "FIN 4");
                            loadEstadio(db, api, token, TABLE_ESTADIO, executor, handler, () -> {
                                Log.d("TESTING!", "FIN 5");
                                loadFamilia(db, api, token, TABLE_FAMILIA, executor, handler, () -> {
                                    Log.d("TESTING!", "FIN 6");
                                    loadFenologia(db, api, token, TABLE_FENOLOGIA, executor, handler, () -> {
                                        Log.d("TESTING!", "FIN 7");
                                        loadForofito(db, api, token, TABLE_FOROFITO, executor, handler, () -> {
                                            Log.d("TESTING!", "FIN 8");
                                            loadFranja(db, api, token, TABLE_FRANJA, executor, handler, () -> {
                                                Log.d("TESTING!", "FIN 9");
                                                loadGenero(db, api, token, TABLE_GENERO, executor, handler, () -> {
                                                    Log.d("TESTING!", "FIN 10");
                                                    loadHabito(db, api, token, TABLE_HABITO, executor, handler, () -> {
                                                        Log.d("TESTING!", "FIN 11");
                                                        loadOrden(db, api, token, TABLE_ORDEN, executor, handler, () -> {
                                                            Log.d("TESTING!", "FIN 12");
                                                            loadPais(db, api, token, TABLE_PAIS, executor, handler, () -> {
                                                                Log.d("TESTING!", "FIN 13");
                                                                loadParcela(db, api, token, TABLE_PARCELA, executor, handler, () -> {
                                                                    Log.d("TESTING!", "FIN 14");
                                                                    loadUnidadMuestreo(db, api, token, TABLE_UNIDAD_MUESTREO, executor, handler, () -> {
                                                                        Log.d("TESTING!", "FIN 15");
                                                                        loadUnidadVegetacion(db, api, token, TABLE_UNIDAD_VEGETACION, executor, handler, () -> {
                                                                            Log.d("TESTING!", "FIN 16");
                                                                            loadTemporadasEvaluacion(db, api, token, TABLE_TEMPORADA_EVALUACION, executor, handler, () -> {
                                                                                Log.d("TESTING!", "FIN 17");
                                                                                loadZonas(db, api, token, TABLE_ZONA, executor, handler, () -> {
                                                                                    Log.d("TESTING!", "FIN 18");
                                                                                    loadMetodologias(db, api, token, TABLE_METODOLOGIA, executor, handler, () -> {
                                                                                        Log.d("TESTING!", "FIN 19");
                                                                                        loadClimas(db, api, token, TABLE_CLIMA, executor, handler, () -> {
                                                                                            Log.d("TESTING!", "FIN 20");
                                                                                            loadUnidadesMuestreal(db, api, token, TABLE_UNIDAD_MUESTREAL, executor, handler, () -> {
                                                                                                Log.d("TESTING!", "FIN 21");
                                                                                                loadTipoRegistros(db, api, token, TABLE_TIPO_REGISTRO, executor, handler, () -> {
                                                                                                    Log.d("TESTING!", "FIN 22");
                                                                                                    loadCategoriaAbundancia(db, api, token, TABLE_CATEGORIA_ABUNDANCIA, executor, handler, () -> {
                                                                                                        Log.d("TESTING!", "FIN 23");
                                                                                                        loadGruposTroficos(db, api, token, TABLE_GRUPO_TROFICO, executor, handler, () -> {
                                                                                                            Log.d("TESTING!", "FIN 24");
                                                                                                            loadIndicadores(db, api, token, TABLE_INDICADOR, executor, handler, () -> {
                                                                                                                Log.d("TESTING!", "FIN 25");
                                                                                                                loadEstadosConservacion(db, api, token, TABLE_ESTADO_CONSERVACION, executor, handler, () -> {
                                                                                                                    Log.d("TESTING!", "FIN 26");
                                                                                                                    loadSubParcela(db, api, token, TABLE_SUB_PARCELA, executor, handler, () -> {
                                                                                                                        Log.d("TESTING!", "FIN 27");
                                                                                                                        loadCondicionReproductiva(db, api, token, TABLE_CONDICION_REPRODUCTIVA, executor, handler, () -> {
                                                                                                                            Log.d("TESTING!", "FIN 28");
                                                                                                                            loadTipoTrampa(db, api, token, TABLE_TIPO_TRAMPA, executor, handler, () -> {
                                                                                                                                Log.d("TESTING!", "FIN 29");
                                                                                                                                loadUsosValues(db, api, token, TABLE_USOS, executor, handler, () -> {
                                                                                                                                    Log.d("TESTING!", "FIN 30");
                                                                                                                                    loadActividadesValues(db, api, token, TABLE_ACTIVIDAD, executor, handler, () -> {
                                                                                                                                        Log.d("TESTING!", "FIN 31");
                                                                                                                                        loadSustratosValues(db, api, token, TABLE_SUSTRATO, executor, handler, () -> {
                                                                                                                                            Log.d("TESTING!", "FIN 32");
                                                                                                                                            loadMicrohabitatValues(db, api, token, TABLE_MICROHABITAT, executor, handler, () -> {
                                                                                                                                                Log.d("TESTING!", "FIN 33");
                                                                                                                                            });
                                                                                                                                        });
                                                                                                                                    });
                                                                                                                                });
                                                                                                                            });
                                                                                                                        });
                                                                                                                    });
                                                                                                                });
                                                                                                            });
                                                                                                        });
                                                                                                    });
                                                                                                });
                                                                                            });
                                                                                        });
                                                                                    });
                                                                                });
                                                                            });
                                                                        });
                                                                    });
                                                                });
                                                            });
                                                        });
                                                    });
                                                });
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });


        }
    }

    public void loadUsosValues(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getUsos("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<TipoUsos>> call, Response<List<TipoUsos>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TipoUsos> usosList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(TipoUsos uso : usosList){
                            ContentValues values = new ContentValues();
                            values.put("usos_id", uso.getUsos_id());
                            values.put("usos_name", uso.getUsos_name());
                            values.put("tipo_form_id", uso.getTipo_form_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<TipoUsos>> call, Throwable t) {
                irALogin();
            }
        });
    }

    public void loadActividadesValues(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getActividades("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Actividad> actividadList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(Actividad actividad : actividadList){
                            ContentValues values = new ContentValues();
                            values.put("actividad_id", actividad.getActividad_id());
                            values.put("actividad_name", actividad.getActividad_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                irALogin();
            }
        });
    }

    public void loadSustratosValues(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getSustratos("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Sustrato>> call, Response<List<Sustrato>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Sustrato> sustratoList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(Sustrato sustrato : sustratoList){
                            ContentValues values = new ContentValues();
                            values.put("sustrato_id", sustrato.getSustrato_id());
                            values.put("sustrato_name", sustrato.getSustrato_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Sustrato>> call, Throwable t) {
                irALogin();
            }
        });
    }

    public void loadMicrohabitatValues(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getMicrohabitat("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Microhabitat>> call, Response<List<Microhabitat>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Microhabitat> microhabitatList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(Microhabitat microhabitat : microhabitatList){
                            ContentValues values = new ContentValues();
                            values.put("microhabitat_id", microhabitat.getMicrohabitat_id());
                            values.put("microhabitat_name", microhabitat.getMicrohabitat_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Microhabitat>> call, Throwable t) {
                irALogin();
            }
        });
    }

    public void loadTemporadasEvaluacion(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getTemporadasEvaluacion("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<TemporadaEvaluacion>> call, Response<List<TemporadaEvaluacion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TemporadaEvaluacion> temporadaEvaluacionList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(TemporadaEvaluacion temporadaEvaluacion : temporadaEvaluacionList){
                            ContentValues values = new ContentValues();
                            values.put("temporada_evaluacion_id", temporadaEvaluacion.getTemporada_evaluacion_id());
                            values.put("temporada_evaluacion_name", temporadaEvaluacion.getTemporada_evaluacion_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    public void loadZonas(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getZonas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Zona>> call, Response<List<Zona>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Zona> zonasList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(Zona zona : zonasList){
                            ContentValues values = new ContentValues();
                            values.put("zona_id", zona.getZona_id());
                            values.put("zona_name", zona.getZona_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    public void loadMetodologias(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getMetodologias("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Metodologia>> call, Response<List<Metodologia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Metodologia> metodologiaList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(Metodologia metodologia : metodologiaList){
                            ContentValues values = new ContentValues();
                            values.put("metodologia_id", metodologia.getMetodologia_id());
                            values.put("metodologia_name", metodologia.getMetodologia_name());
                            values.put("tipo_formulario_id", metodologia.getTipo_formulario_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    public void loadClimas(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getClimas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Clima>> call, Response<List<Clima>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Clima> climaList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(Clima clima : climaList){
                            ContentValues values = new ContentValues();
                            values.put("clima_id", clima.getClima_id());
                            values.put("clima_name", clima.getClima_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    private void loadUnidadesMuestreal(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getUnidadesMuestreal("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UnidadMuestreal>> call, Response<List<UnidadMuestreal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnidadMuestreal> unidadMuestrealList = response.body();

                    executor.execute(() -> {
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

                        onComplete.run();
                    });
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

    private void loadTipoRegistros(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getTipoRegistros("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<TipoRegistro>> call, Response<List<TipoRegistro>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TipoRegistro> tipoRegistroList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(TipoRegistro tipoRegistro : tipoRegistroList){
                            ContentValues values = new ContentValues();
                            values.put("tipo_registro_id", tipoRegistro.getTipo_registro_id());
                            values.put("tipo_registro_name", tipoRegistro.getTipo_registro_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<TipoRegistro>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadCategoriaAbundancia(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getCategoriaXAbundancia("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<CategoriaAbundancia>> call, Response<List<CategoriaAbundancia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CategoriaAbundancia> categoriaAbundanciaList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(CategoriaAbundancia categoriaAbundancia : categoriaAbundanciaList){
                            ContentValues values = new ContentValues();
                            values.put("categoria_abundancia_id", categoriaAbundancia.getCategoria_abundancia_id());
                            values.put("categoria_abundancia_name", categoriaAbundancia.getCategoria_abundancia_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<CategoriaAbundancia>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadGruposTroficos(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getGruposTroficos("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<GrupoTrofico>> call, Response<List<GrupoTrofico>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GrupoTrofico> grupoTroficoList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(GrupoTrofico grupoTrofico : grupoTroficoList){
                            ContentValues values = new ContentValues();
                            values.put("grupo_trofico_id", grupoTrofico.getGrupo_trofico_id());
                            values.put("grupo_trofico_name", grupoTrofico.getGrupo_trofico_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<GrupoTrofico>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadIndicadores(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getIndicadores("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Indicador>> call, Response<List<Indicador>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Indicador> indicadoresList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(Indicador indicador : indicadoresList){
                            ContentValues values = new ContentValues();
                            values.put("indicador_id", indicador.getIndicador_id());
                            values.put("indicador_name", indicador.getIndicador_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Indicador>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadEstadosConservacion(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getEstadosConservacion("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<EstadoConservacion>> call, Response<List<EstadoConservacion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<EstadoConservacion> estadoConservacionList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(EstadoConservacion estadoConservacion : estadoConservacionList){
                            ContentValues values = new ContentValues();
                            values.put("estado_conservacion_habitat_id", estadoConservacion.getEstado_conservacion_habitat_id());
                            values.put("estado_conservacion_habitat_name", estadoConservacion.getEstado_conservacion_habitat_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<EstadoConservacion>> call, Throwable t) {
                irALogin();
            }
        });
    }

    public void loadAutores(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getAutores("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Autor> autoresList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Autor autor : autoresList) {
                            ContentValues values = new ContentValues();
                            values.put("autor_id", autor.getAutor_id());
                            values.put("autor_name", autor.getAutor_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    private void loadClases(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getClases("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Clase>> call, Response<List<Clase>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Clase> clasesList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Clase clase : clasesList) {
                            ContentValues values = new ContentValues();
                            values.put("clase_id", clase.getClase_id());
                            values.put("clase_name", clase.getClase_name());
                            values.put("tipo_form_id", clase.getTipo_form_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Clase>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al cargar las clases", Toast.LENGTH_SHORT).show();
                irALogin();
            }
        });
    }

    private void loadEspecie(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getEspecies("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Especie>> call, Response<List<Especie>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Especie> especiesList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Especie especie : especiesList) {
                            ContentValues values = new ContentValues();
                            values.put("especie_id", especie.getEspecie_id());
                            values.put("especie_name", especie.getEspecie_name());
                            values.put("genero_id", especie.getGenero_id());
                            values.put("tipo_form_id", especie.getTipo_form_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Especie>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al cargar las especies", Toast.LENGTH_SHORT).show();
                irALogin();
            }
        });
    }

    private void loadEstacionMuestreo(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getEstacionMuestreoList("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<EstacionMuestreo>> call, Response<List<EstacionMuestreo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<EstacionMuestreo> estacionMuestreoList = response.body();

                    executor.execute(() -> {
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

                        onComplete.run();
                    });
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

    private void loadEstadio(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete){
        api.getEstadios("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Estadio>> call, Response<List<Estadio>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Estadio> estdiosList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Estadio estadio : estdiosList) {
                            ContentValues values = new ContentValues();
                            values.put("estadio_id", estadio.getEstadio_id());
                            values.put("estadio_name", estadio.getEstadio_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });

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

    private void loadFamilia(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getFamilias("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Familia>> call, Response<List<Familia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Familia> familiasList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Familia familia : familiasList) {
                            ContentValues values = new ContentValues();
                            values.put("familia_id", familia.getFamilia_id());
                            values.put("familia_name", familia.getFamilia_name());
                            values.put("orden_id", familia.getOrden_id());
                            values.put("tipo_form_id", familia.getTipo_form_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Familia>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al cargar las familias", Toast.LENGTH_SHORT).show();
                irALogin();
            }
        });
    }

    private void loadFenologia(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getFenologias("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Fenologia>> call, Response<List<Fenologia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Fenologia> fenologiasList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(Fenologia fenologia : fenologiasList){
                            ContentValues values = new ContentValues();
                            values.put("fenologia_id", fenologia.getFenologia_id());
                            values.put("fenologia_name", fenologia.getFenologia_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    private void loadForofito(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getForofitos("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Forofito>> call, Response<List<Forofito>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Forofito> forofitoList = response.body();

                    executor.execute(() -> {
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

                        onComplete.run();
                    });
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

    private void loadFranja(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getFranjas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Franja>> call, Response<List<Franja>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Franja> subEstacionesList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for(Franja franja : subEstacionesList){
                            ContentValues values = new ContentValues();
                            values.put("franja_id", franja.getFranja_id());
                            values.put("franja_name", franja.getFranja_name());
                            values.put("estacion_muestreo_id", franja.getEstacion_muestreo_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    private void loadGenero(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getGeneros("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Genero>> call, Response<List<Genero>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Genero> generosList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Genero genero : generosList) {
                            ContentValues values = new ContentValues();
                            values.put("genero_id", genero.getGenero_id());
                            values.put("genero_name", genero.getGenero_name());
                            values.put("familia_id", genero.getFamilia_id());
                            values.put("tipo_form_id", genero.getTipo_form_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Genero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al cargar los géneros", Toast.LENGTH_SHORT).show();
                irALogin();
            }
        });
    }

    private void loadHabito(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getHabitos("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Habito>> call, Response<List<Habito>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Habito> habitosList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Habito habito : habitosList) {
                            ContentValues values = new ContentValues();
                            values.put("habito_id", habito.getHabito_id());
                            values.put("habito_name", habito.getHabito_name());
                            values.put("tipo_form_id", habito.getTipo_form_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    private void loadOrden(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getOrdenes("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Orden>> call, Response<List<Orden>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Orden> ordenesList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Orden orden : ordenesList) {
                            ContentValues values = new ContentValues();
                            values.put("orden_id", orden.getOrden_id());
                            values.put("orden_name", orden.getOrden_name());
                            values.put("clase_id", orden.getClase_id());
                            values.put("tipo_form_id", orden.getTipo_form_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Orden>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error al cargar las ordenes", Toast.LENGTH_SHORT).show();
                irALogin();
            }
        });
    }

    private void loadPais(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getPaises("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pais> paisList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Pais pais : paisList) {
                            ContentValues values = new ContentValues();
                            values.put("pais_id", pais.getPais_id());
                            values.put("pais_name", pais.getPais_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    private void loadParcela(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getParcelas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Parcela>> call, Response<List<Parcela>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Parcela> parcelaList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (Parcela parcela : parcelaList) {
                            ContentValues values = new ContentValues();
                            values.put("parcela_id", parcela.getParcela_id());
                            values.put("parcela_name", parcela.getParcela_name());
                            values.put("franja_id", parcela.getFranja_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    private void loadSubParcela(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getSubparcelas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<SubParcela>> call, Response<List<SubParcela>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SubParcela> subParcelaListList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (SubParcela subParcela : subParcelaListList) {
                            ContentValues values = new ContentValues();
                            values.put("subparcela_id", subParcela.getSubparcela_id());
                            values.put("subparcela_name", subParcela.getSubparcela_name());
                            values.put("parcela_id", subParcela.getParcela_id());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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

    private void loadCondicionReproductiva(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getCondicionesReproductivas("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<CondicionReproductiva>> call, Response<List<CondicionReproductiva>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CondicionReproductiva> condicionReproductivaList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (CondicionReproductiva condicionReproductiva : condicionReproductivaList) {
                            ContentValues values = new ContentValues();
                            values.put("condicion_reproductiva_id", condicionReproductiva.getCondicion_reproductiva_id());
                            values.put("condicion_reproductiva_name", condicionReproductiva.getCondicion_reproductiva_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<CondicionReproductiva>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadTipoTrampa(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getTiposTrampa("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<TipoTrampa>> call, Response<List<TipoTrampa>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TipoTrampa> tipoTrampaList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (TipoTrampa tipoTrampa : tipoTrampaList) {
                            ContentValues values = new ContentValues();
                            values.put("tipo_trampa_id", tipoTrampa.getTipo_trampa_id());
                            values.put("tipo_trampa_name", tipoTrampa.getTipo_trampa_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<TipoTrampa>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadUnidadMuestreo(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getUnidadMuestreoList("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UnidadMuestreo>> call, Response<List<UnidadMuestreo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnidadMuestreo> unidadMuestreoList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (UnidadMuestreo unidadMuestreo : unidadMuestreoList) {
                            ContentValues values = new ContentValues();
                            values.put("unidad_muestreo_id", unidadMuestreo.getUnidad_muestreo_id());
                            values.put("unidad_muestreo_name", unidadMuestreo.getUnidad_muestreo_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
                }
            }

            @Override
            public void onFailure(Call<List<UnidadMuestreo>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void loadUnidadVegetacion(SQLiteDatabase db, ApiService api, String token, String TABLE_NAME, ExecutorService executor, Handler handler, Runnable onComplete) {
        api.getUnidadVegetacion("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UnidadVegetacion>> call, Response<List<UnidadVegetacion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnidadVegetacion> unidadVegetacionList = response.body();

                    executor.execute(() -> {
                        db.delete(TABLE_NAME, null, null);
                        db.execSQL("DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'");

                        for (UnidadVegetacion unidadVegetacion : unidadVegetacionList) {
                            ContentValues values = new ContentValues();
                            values.put("unidad_vegetacion_id", unidadVegetacion.getUnidad_vegetacion_id());
                            values.put("unidad_vegetacion_name", unidadVegetacion.getUnidad_vegetacion_name());
                            db.insert(TABLE_NAME, null, values);
                        }

                        onComplete.run();
                    });
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