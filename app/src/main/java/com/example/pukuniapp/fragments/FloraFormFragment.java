package com.example.pukuniapp.fragments;

import static com.example.pukuniapp.sqlite.DBHelper.TABLE_AUTOR;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_CLASE;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_ESPECIE;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_ESTADIO;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_FAMILIA;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_FENOLOGIA;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_FOROFITO;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_FRANJA;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_GENERO;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_HABITO;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_ORDEN;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_PARCELA;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_SUB_PARCELA;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_UNIDAD_MUESTREO;
import static com.example.pukuniapp.sqlite.DBHelper.TABLE_UNIDAD_VEGETACION;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pukuniapp.HomeActivity;
import com.example.pukuniapp.LoginActivity;
import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.Autor;
import com.example.pukuniapp.classes.Clase;
import com.example.pukuniapp.classes.Especie;
import com.example.pukuniapp.classes.Estadio;
import com.example.pukuniapp.classes.Familia;
import com.example.pukuniapp.classes.Fenologia;
import com.example.pukuniapp.classes.Forofito;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.Genero;
import com.example.pukuniapp.classes.Habito;
import com.example.pukuniapp.classes.Orden;
import com.example.pukuniapp.classes.Pais;
import com.example.pukuniapp.classes.Parcela;
import com.example.pukuniapp.classes.SubParcela;
import com.example.pukuniapp.classes.UnidadMuestreo;
import com.example.pukuniapp.classes.UnidadVegetacion;
import com.example.pukuniapp.retrofit.ApiService;
import com.example.pukuniapp.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FloraFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FloraFormFragment extends Fragment {
    Spinner spinnerSubEstacionMuestreo;
    Spinner spinnerUnidadMuestreo;
    Spinner spinnerParcela;
    Spinner spinnerForofito;
    Spinner spinnerSubParcela;
    Spinner spinnerUnidadVegetacion;
    Spinner spinnerAutores;
    Spinner spinnerHabito;
    Spinner spinnerEstadio;
    Spinner spinnerFenologia;
    TextView textViewEste;
    TextView textViewNorte;
    TextView textViewAltitud;
    AutoCompleteTextView et_clase;
    AutoCompleteTextView et_orden;
    AutoCompleteTextView et_familia;
    AutoCompleteTextView et_genero;
    AutoCompleteTextView et_especie;
    EditText et_nombre_comun;
    EditText et_individuos;
    EditText et_dap;
    EditText et_altura;
    EditText et_valor_cobertura;
    EditText et_usos;
    EditText et_valor_observaciones;
    EditText et_valor_datos_planta;
    Button saveForm;
    List<Clase> clasesList;
    List<Orden> ordenesList;
    List<Familia> familiasList;
    List<Genero> generosList;
    List<Especie> especiesList;

    public FloraFormFragment() {
        // Required empty public constructor
    }
    public static FloraFormFragment newInstance(String estacionName, int estacionId) {
        FloraFormFragment fragment = new FloraFormFragment();
        Bundle args = new Bundle();
        args.putInt("estacion_id", estacionId);
        args.putString("estacion_name", estacionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flora_form, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);
        label.setText("Formulario Flora");

        spinnerSubEstacionMuestreo = view.findViewById(R.id.spinner_sub_estacion_muestreo);
        spinnerUnidadMuestreo = view.findViewById(R.id.spinner_unidad_muestreo);
        spinnerParcela = view.findViewById(R.id.spinner_parcela);
        spinnerForofito = view.findViewById(R.id.spinner_forofito);
        spinnerSubParcela = view.findViewById(R.id.spinner_sub_parcela);
        spinnerUnidadVegetacion = view.findViewById(R.id.spinner_unidad_vegetacion);
        spinnerAutores = view.findViewById(R.id.spinner_autor);
        spinnerHabito = view.findViewById(R.id.spinner_habito);
        spinnerEstadio = view.findViewById(R.id.spinner_estadio);
        spinnerFenologia = view.findViewById(R.id.spinner_fenologia);
        textViewEste = view.findViewById(R.id.tv_este);
        textViewNorte = view.findViewById(R.id.tv_norte);
        textViewAltitud = view.findViewById(R.id.tv_altitud);
        saveForm = view.findViewById(R.id.save_form);
        et_clase= view.findViewById(R.id.et_clase);
        et_orden = view.findViewById(R.id.et_orden);
        et_familia = view.findViewById(R.id.et_familia);
        et_genero = view.findViewById(R.id.et_genero);
        et_especie = view.findViewById(R.id.et_especie);
        et_nombre_comun = view.findViewById(R.id.et_nombre_comun);
        et_individuos = view.findViewById(R.id.et_individuos);
        et_dap = view.findViewById(R.id.et_dap);
        et_altura = view.findViewById(R.id.et_altura);
        et_valor_cobertura = view.findViewById(R.id.et_valor_cobertura);
        et_usos = view.findViewById(R.id.et_usos);
        et_valor_observaciones = view.findViewById(R.id.et_valor_observaciones);
        et_valor_datos_planta = view.findViewById(R.id.et_valor_datos_planta);

        DBHelper dbHelper = new DBHelper(requireContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        setSubEstacionesValues(db);
        setUnidadeDeMuestreoValues(db);
        setUnidadDeVegetacionValues(db);
        getCoordinates();
        setupAutocompleteTv(db);
        setAutoresValues(db);
        setHabitosValues(db);
        setEstadiosValues(db);
        setFenologiasValues(db);

        saveForm.setOnClickListener(v -> {
            // Recoger valores de los spinners
            Franja subEstacion = (Franja) spinnerSubEstacionMuestreo.getSelectedItem();
            UnidadMuestreo unidadMuestreo = (UnidadMuestreo) spinnerUnidadMuestreo.getSelectedItem();
            Parcela parcela = (Parcela) spinnerParcela.getSelectedItem();
            Forofito forofito = (Forofito) spinnerForofito.getSelectedItem();
            SubParcela subParcela = (SubParcela) spinnerSubParcela.getSelectedItem();
            UnidadVegetacion unidadVegetacion = (UnidadVegetacion) spinnerUnidadVegetacion.getSelectedItem();
            Autor autor = (Autor) spinnerAutores.getSelectedItem();
            Habito habito = (Habito) spinnerHabito.getSelectedItem();
            Estadio estadio = (Estadio) spinnerEstadio.getSelectedItem();
            Fenologia fenologia = (Fenologia) spinnerFenologia.getSelectedItem();

            // Recoger valores de los campos de texto
            String clase = et_clase.getText().toString();
            String orden = et_orden.getText().toString();
            String familia = et_familia.getText().toString();
            String genero = et_genero.getText().toString();
            String especie = et_especie.getText().toString();
            String este = textViewEste.getText().toString();
            String norte = textViewNorte.getText().toString();
            String altitud = textViewAltitud.getText().toString();

            // Ejemplo de campos adicionales (los tendrías que tener en tu layout)
            String nombreComun = et_nombre_comun.getText().toString();
            String numIndividuos = et_individuos.getText().toString();
            String dap = et_dap.getText().toString();
            String altura = et_altura.getText().toString();
            String valorCobertura = et_valor_cobertura.getText().toString();
            String usos = et_usos.getText().toString();
            String observaciones = et_valor_observaciones.getText().toString();
            String datosPlanta = et_valor_datos_planta.getText().toString();

            ContentValues values = new ContentValues();
            values.put("franja_id", subEstacion != null ? subEstacion.getFranja_id() : null);
            values.put("unidad_muestreo_id", unidadMuestreo != null ? unidadMuestreo.getUnidad_muestreo_id() : null);
            values.put("parcela_id", parcela != null ? parcela.getParcela_id() : null);
            values.put("forofito_id", forofito != null ? forofito.getForofito_id() : null);
            values.put("sub_parcela_id", subParcela != null ? subParcela.getSubparcela_id() : null);
            values.put("unidad_vegetacion_id", unidadVegetacion != null ? unidadVegetacion.getUnidad_vegetacion_id() : null);
            values.put("autor_id", autor != null ? autor.getAutor_id() : null);
            values.put("habito_id", habito != null ? habito.getHabito_id() : null);
            values.put("estadio_id", estadio != null ? estadio.getEstadio_id() : null);
            values.put("fenologia_id", fenologia != null ? fenologia.getFenologia_id() : null);

            values.put("clase_id", clase);
            values.put("orden_id", orden);
            values.put("familia_id", familia);
            values.put("genero_id", genero);
            values.put("especie_id", especie);
            values.put("nombre_comun", nombreComun);
            values.put("este", este);
            values.put("norte", norte);
            values.put("altitud", altitud);
            values.put("individuos", numIndividuos);
            values.put("dap", dap);
            values.put("altura", altura);
            values.put("valor_cobertura", valorCobertura);
            values.put("usos", usos);
            values.put("observaciones", observaciones);
            values.put("datos_planta", datosPlanta);

            long newRowId = db.insert("flora", null, values);

            if (newRowId != -1) {
                Toast.makeText(requireContext(), "Registro guardado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Error al guardar el registro", Toast.LENGTH_SHORT).show();
            }

            db.close();

            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }

    private void setupAutocompleteTv(SQLiteDatabase db){
        // Start set clases values
        clasesList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_CLASE,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));
                String clase_name = cursor.getString(cursor.getColumnIndexOrThrow("clase_name"));

                Clase clase = new Clase();
                clase.setClase_id(id);
                clase.setClase_name(clase_name);

                clasesList.add(clase);
            } while (cursor.moveToNext());
        }

        cursor.close();

        List<String> clasesNames = new ArrayList<>();

        for (Clase clase : clasesList) {
            clasesNames.add(clase.getClase_name());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            clasesNames
        );

        et_clase.setAdapter(adapter);
        et_clase.setThreshold(1);
        // End set clases values

        // Start set ordenes values
        ordenesList = new ArrayList<>();

        cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_ORDEN,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("orden_id"));
                String clase_name = cursor.getString(cursor.getColumnIndexOrThrow("orden_name"));
                int clase_id = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));

                Orden orden = new Orden();
                orden.setOrden_id(id);
                orden.setOrden_name(clase_name);
                orden.setClase_id(clase_id);

                ordenesList.add(orden);
            } while (cursor.moveToNext());
        }

        cursor.close();

        List<String> ordenesNames = new ArrayList<>();

        for (Orden orden : ordenesList) {
            ordenesNames.add(orden.getOrden_name());
        }

        adapter = new ArrayAdapter<>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            ordenesNames
        );

        et_orden.setAdapter(adapter);
        et_orden.setThreshold(1);

        et_orden.setOnItemClickListener((parent, view, position, id) -> {
            String ordenName = (String) parent.getItemAtPosition(position);

            Orden ordenSeleccionada = null;
            for (Orden orden : ordenesList) {
                if (orden.getOrden_name().equalsIgnoreCase(ordenName)) {
                    ordenSeleccionada = orden;
                    break;
                }
            }

            if (ordenSeleccionada != null) {
                int claseId = ordenSeleccionada.getClase_id();
                for (Clase clase : clasesList) {
                    if (clase.getClase_id() == claseId) {
                        et_clase.setText(clase.getClase_name());
                        break;
                    }
                }
            }
        });
        // End set ordenes values

        // Start set familias values
        familiasList = new ArrayList<>();

        cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_FAMILIA,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("familia_id"));
                String familia_name = cursor.getString(cursor.getColumnIndexOrThrow("familia_name"));
                int orden_id = cursor.getInt(cursor.getColumnIndexOrThrow("orden_id"));

                Familia familia = new Familia();
                familia.setFamilia_id(id);
                familia.setFamilia_name(familia_name);
                familia.setOrden_id(orden_id);

                familiasList.add(familia);
            } while (cursor.moveToNext());
        }

        cursor.close();

        List<String> familiasNames = new ArrayList<>();

        for (Familia familia : familiasList) {
            familiasNames.add(familia.getFamilia_name());
        }

        adapter = new ArrayAdapter<>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            familiasNames
        );

        et_familia.setAdapter(adapter);
        et_familia.setThreshold(1);

        et_familia.setOnItemClickListener((parent, view, position, id) -> {
            String familiaName = (String) parent.getItemAtPosition(position);

            Familia familiaSeleccionada = null;
            for (Familia familia : familiasList) {
                if (familia.getFamilia_name().equalsIgnoreCase(familiaName)) {
                    familiaSeleccionada = familia;
                    break;
                }
            }

            if (familiaSeleccionada != null) {
                int ordenId = familiaSeleccionada.getOrden_id();
                Orden ordenSeleccionado = null;

                for (Orden orden : ordenesList) {
                    if (orden.getOrden_id() == ordenId) {
                        ordenSeleccionado = orden;
                        break;
                    }
                }

                if (ordenSeleccionado != null) {
                    et_orden.setText(ordenSeleccionado.getOrden_name());

                    int claseId = ordenSeleccionado.getClase_id();
                    for (Clase clase : clasesList) {
                        if (clase.getClase_id() == claseId) {
                            et_clase.setText(clase.getClase_name());
                            break;
                        }
                    }
                }
            }
        });
        // End set familias values

        // Start set generos values
        generosList = new ArrayList<>();

        cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_GENERO,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("genero_id"));
                String genero_name = cursor.getString(cursor.getColumnIndexOrThrow("genero_name"));
                int familia_id = cursor.getInt(cursor.getColumnIndexOrThrow("familia_id"));

                Genero genero = new Genero();
                genero.setGenero_id(id);
                genero.setGenero_name(genero_name);
                genero.setFamilia_id(familia_id);

                generosList.add(genero);
            } while (cursor.moveToNext());
        }

        cursor.close();

        List<String> generosNames = new ArrayList<>();

        for (Genero genero : generosList) {
            generosNames.add(genero.getGenero_name());
        }

        adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                generosNames
        );

        et_genero.setAdapter(adapter);
        et_genero.setThreshold(1);

        et_genero.setOnItemClickListener((parent, view, position, id) -> {
            String generoName = (String) parent.getItemAtPosition(position);

            Genero generoSeleccionado = null;
            for (Genero genero : generosList) {
                if (genero.getGenero_name().equalsIgnoreCase(generoName)) {
                    generoSeleccionado = genero;
                    break;
                }
            }

            if (generoSeleccionado != null) {
                int familiaId = generoSeleccionado.getFamilia_id();
                Familia familiaSeleccionada = null;

                for (Familia familia : familiasList) {
                    if (familia.getFamilia_id() == familiaId) {
                        familiaSeleccionada = familia;
                        break;
                    }
                }

                if (familiaSeleccionada != null) {
                    et_familia.setText(familiaSeleccionada.getFamilia_name());

                    int ordenId = familiaSeleccionada.getOrden_id();
                    Orden ordenSeleccionado = null;

                    for (Orden orden : ordenesList) {
                        if (orden.getOrden_id() == ordenId) {
                            ordenSeleccionado = orden;
                            break;
                        }
                    }

                    if (ordenSeleccionado != null) {
                        et_orden.setText(ordenSeleccionado.getOrden_name());

                        int claseId = ordenSeleccionado.getClase_id();
                        for (Clase clase : clasesList) {
                            if (clase.getClase_id() == claseId) {
                                et_clase.setText(clase.getClase_name());
                                break;
                            }
                        }
                    }
                }
            }
        });
        // End set generos values

        // Start set especies values
        especiesList = new ArrayList<>();

        cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_ESPECIE,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("especie_id"));
                String especie_name = cursor.getString(cursor.getColumnIndexOrThrow("especie_name"));
                int genero_id = cursor.getInt(cursor.getColumnIndexOrThrow("genero_id"));

                Especie especie = new Especie();
                especie.setEspecie_id(id);
                especie.setEspecie_name(especie_name);
                especie.setGenero_id(genero_id);

                especiesList.add(especie);
            } while (cursor.moveToNext());
        }

        cursor.close();

        List<String> especieNames = new ArrayList<>();
        for (Especie especie : especiesList) {
            especieNames.add(especie.getEspecie_name());
        }

        adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                especieNames
        );

        et_especie.setAdapter(adapter);
        et_especie.setThreshold(1);

        et_especie.setOnItemClickListener((parent, view, position, id) -> {
            String especieName = (String) parent.getItemAtPosition(position);

            Especie especieSeleccionada = null;
            for (Especie especie : especiesList) {
                if (especie.getEspecie_name().equalsIgnoreCase(especieName)) {
                    especieSeleccionada = especie;
                    break;
                }
            }

            if (especieSeleccionada != null) {
                int generoId = especieSeleccionada.getGenero_id();
                Genero generoSeleccionado = null;

                for (Genero genero : generosList) {
                    if (genero.getGenero_id() == generoId) {
                        generoSeleccionado = genero;
                        break;
                    }
                }

                if (generoSeleccionado != null) {
                    et_genero.setText(generoSeleccionado.getGenero_name());

                    int familiaId = generoSeleccionado.getFamilia_id();
                    Familia familiaSeleccionada = null;

                    for (Familia familia : familiasList) {
                        if (familia.getFamilia_id() == familiaId) {
                            familiaSeleccionada = familia;
                            break;
                        }
                    }

                    if (familiaSeleccionada != null) {
                        et_familia.setText(familiaSeleccionada.getFamilia_name());

                        int ordenId = familiaSeleccionada.getOrden_id();
                        Orden ordenSeleccionado = null;

                        for (Orden orden : ordenesList) {
                            if (orden.getOrden_id() == ordenId) {
                                ordenSeleccionado = orden;
                                break;
                            }
                        }

                        if (ordenSeleccionado != null) {
                            et_orden.setText(ordenSeleccionado.getOrden_name());

                            int claseId = ordenSeleccionado.getClase_id();
                            for (Clase clase : clasesList) {
                                if (clase.getClase_id() == claseId) {
                                    et_clase.setText(clase.getClase_name());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });
        // End set especies values
    }

    private void getCoordinates(){
        LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Solicita permisos
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            double alt = location.getAltitude();

            UTMConverter(lat, lon, alt);
        }
    }

    private void setHabitosValues(SQLiteDatabase db){
        List<Habito> habitosList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_HABITO,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("habito_id"));
                String habito_name = cursor.getString(cursor.getColumnIndexOrThrow("habito_name"));

                Habito habito = new Habito();
                habito.setHabito_id(id);
                habito.setHabito_name(habito_name);

                habitosList.add(habito);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Habito> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                habitosList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerHabito.setAdapter(adapter);
    }

    private void setEstadiosValues(SQLiteDatabase db){
        List<Estadio> estdiosList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ESTADIO,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("estadio_id"));
                String estadio_name = cursor.getString(cursor.getColumnIndexOrThrow("estadio_name"));

                Estadio estadio = new Estadio();
                estadio.setEstadio_id(id);
                estadio.setEstadio_name(estadio_name);

                estdiosList.add(estadio);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Estadio> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                estdiosList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerEstadio.setAdapter(adapter);
    }

    private void setFenologiasValues(SQLiteDatabase db){
        List<Fenologia> fenologiaList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_FENOLOGIA,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("fenologia_id"));
                String fenologia_name = cursor.getString(cursor.getColumnIndexOrThrow("fenologia_name"));

                Fenologia fenologia = new Fenologia();
                fenologia.setFenologia_id(id);
                fenologia.setFenologia_name(fenologia_name);

                fenologiaList.add(fenologia);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Fenologia> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                fenologiaList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFenologia.setAdapter(adapter);
    }

    private void setAutoresValues(SQLiteDatabase db){
        List<Autor> autoresList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_AUTOR,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("autor_id"));
                String autor_name = cursor.getString(cursor.getColumnIndexOrThrow("autor_name"));

                Autor autor = new Autor();
                autor.setAutor_id(id);
                autor.setAutor_name(autor_name);

                autoresList.add(autor);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Autor> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                autoresList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAutores.setAdapter(adapter);
    }

    private void setSubEstacionesValues(SQLiteDatabase db) {
        if (this.getArguments() != null) {
            int estacionMuestreoId = getArguments().getInt("estacion_id", -1);

            if (estacionMuestreoId != -1) {
                List<Franja> franjasList = new ArrayList<>();

                Cursor cursor = db.rawQuery(
                    "SELECT * FROM " + TABLE_FRANJA + " WHERE estacion_muestreo_id = ?",
                    new String[]{String.valueOf(estacionMuestreoId)}
                );

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("franja_id"));
                        String nombre = cursor.getString(cursor.getColumnIndexOrThrow("franja_name"));

                        Franja franja = new Franja();
                        franja.setFranja_id(id);
                        franja.setFranja_name(nombre);
                        franjasList.add(franja);
                    } while (cursor.moveToNext());
                }

                cursor.close();
//                db.close();

                ArrayAdapter<Franja> adapter = new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        franjasList
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerSubEstacionMuestreo.setAdapter(adapter);

                spinnerSubEstacionMuestreo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Franja franja = (Franja) spinnerSubEstacionMuestreo.getSelectedItem();

                        if (franja != null) {
                            setParcelaValues(db, franja);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }
    }

    private void setUnidadeDeMuestreoValues(SQLiteDatabase db){
        List<UnidadMuestreo> unidadMuestreoList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_UNIDAD_MUESTREO,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_muestreo_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("unidad_muestreo_name"));

                UnidadMuestreo unidadMuestreo = new UnidadMuestreo();
                unidadMuestreo.setUnidad_muestreo_id(id);
                unidadMuestreo.setUnidad_muestreo_name(name);

                unidadMuestreoList.add(unidadMuestreo);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<UnidadMuestreo> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                unidadMuestreoList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerUnidadMuestreo.setAdapter(adapter);

        spinnerUnidadMuestreo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                UnidadMuestreo unidadSeleccionada = (UnidadMuestreo) spinnerUnidadMuestreo.getSelectedItem();

                if (unidadSeleccionada != null) {
                    if(unidadSeleccionada.getUnidad_muestreo_name().equalsIgnoreCase("Forofito")){
                        Parcela parcelaSeleccionada = (Parcela) spinnerParcela.getSelectedItem();

                        if(parcelaSeleccionada != null){
                            int parcelaId = parcelaSeleccionada.getParcela_id();

                          setForofitosValues(parcelaId, db);
                        }
                    }else{
                        removeForofitosValues();
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setParcelaValues(SQLiteDatabase db, Franja franjaSeleccionada){
        int franjaId = franjaSeleccionada.getFranja_id();
        String franjaName = franjaSeleccionada.getFranja_name();

        List<Parcela> parcelaList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_PARCELA + " WHERE franja_id = ?",
            new String[]{String.valueOf(franjaId)}
        );

        if (cursor.moveToFirst()) {
            do {
                int parcela_id = cursor.getInt(cursor.getColumnIndexOrThrow("parcela_id"));
                String parcela_name = cursor.getString(cursor.getColumnIndexOrThrow("parcela_name"));
                String franja_name = cursor.getString(cursor.getColumnIndexOrThrow("franja_name"));
                int franja_id = cursor.getInt(cursor.getColumnIndexOrThrow("franja_id"));

                Parcela parcela = new Parcela();
                parcela.setParcela_id(parcela_id);
                parcela.setParcela_name(parcela_name);
                parcela.setFranja_id(franja_id);
                parcela.setFranjaName(franja_name);
                parcelaList.add(parcela);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Parcela> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, parcelaList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView label = (TextView) super.getView(position, convertView, parent);
                label.setText(franjaName + " (" + parcelaList.get(position).getParcela_name() + ")");
                return label;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView label = (TextView) super.getDropDownView(position, convertView, parent);
                label.setText(franjaName + " (" + parcelaList.get(position).getParcela_name() + ")");
                return label;
            }
        };

        spinnerParcela.setAdapter(adapter);

        spinnerParcela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UnidadMuestreo unidadSeleccionada = (UnidadMuestreo) spinnerUnidadMuestreo.getSelectedItem();
                Parcela parcelaSeleccionada = (Parcela) spinnerParcela.getSelectedItem();

                if (unidadSeleccionada != null && unidadSeleccionada.getUnidad_muestreo_name().equalsIgnoreCase("Forofito")) {
                    if (parcelaSeleccionada != null) {
                        int parcelaId = parcelaSeleccionada.getParcela_id();

                        setForofitosValues(parcelaId, db);
                    }
                }

                if(parcelaSeleccionada != null){
                    int parcelaId = parcelaSeleccionada.getParcela_id();

                    setSubparcelasValues(db, parcelaId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });
    }

    private void setForofitosValues(int parcelaId, SQLiteDatabase db){
        List<Forofito> forofitoList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
        "SELECT * FROM " + TABLE_FOROFITO + " WHERE parcela_id = ?",
            new String[]{String.valueOf(parcelaId)}
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("forofito_id"));
                int parcela_id = cursor.getInt(cursor.getColumnIndexOrThrow("parcela_id"));
                String parcela_name = cursor.getString(cursor.getColumnIndexOrThrow("parcela_name"));
                String forofito_name = cursor.getString(cursor.getColumnIndexOrThrow("forofito_name"));


                Forofito forofito = new Forofito();
                forofito.setForofito_id(id);
                forofito.setParcela_id(parcela_id);
                forofito.setParcela_name(parcela_name);
                forofito.setForofito_name(forofito_name);

                forofitoList.add(forofito);
            } while (cursor.moveToNext());
        }

        cursor.close();

        Franja franjaSeleccionada = (Franja) spinnerSubEstacionMuestreo.getSelectedItem();

        for (Forofito f : forofitoList) {
            f.setParcela_name(franjaSeleccionada.getFranja_name() + " (" + f.getParcela_name() + ") -");
        }

        ArrayAdapter<Forofito> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, forofitoList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView label = (TextView) super.getView(position, convertView, parent);
                label.setText(forofitoList.get(position).getParcela_name() + " " + forofitoList.get(position).getForofito_name());
                return label;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView label = (TextView) super.getDropDownView(position, convertView, parent);
                label.setText(forofitoList.get(position).getParcela_name() + " " + forofitoList.get(position).getForofito_name());
                return label;
            }
        };

        spinnerForofito.setAdapter(adapter);
    }

    private void setSubparcelasValues(SQLiteDatabase db, int parcelaId){
        List<SubParcela> subParcelaList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_SUB_PARCELA + " WHERE parcela_id = ?",
                new String[]{String.valueOf(parcelaId)}
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("subparcela_id"));
                int parcela_id = cursor.getInt(cursor.getColumnIndexOrThrow("parcela_id"));
                String subparcela_name = cursor.getString(cursor.getColumnIndexOrThrow("subparcela_name"));

                SubParcela subParcela = new SubParcela();
                subParcela.setSubparcela_id(id);
                subParcela.setSubparcela_name(subparcela_name);
                subParcela.setParcela_id(parcela_id);

                subParcelaList.add(subParcela);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<SubParcela> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                subParcelaList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSubParcela.setAdapter(adapter);
    }

    private void removeForofitosValues(){
        ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new ArrayList<>()
        );

        spinnerForofito.setAdapter(emptyAdapter);
    }

    private void setUnidadDeVegetacionValues(SQLiteDatabase db){
        List<UnidadVegetacion> unidadVegetacionList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_UNIDAD_VEGETACION,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_vegetacion_id"));
                String unidad_vegetacion_name = cursor.getString(cursor.getColumnIndexOrThrow("unidad_vegetacion_id"));

                UnidadVegetacion unidadVegetacion = new UnidadVegetacion();
                unidadVegetacion.setUnidad_vegetacion_id(id);
                unidadVegetacion.setUnidad_vegetacion_name(unidad_vegetacion_name);

                unidadVegetacionList.add(unidadVegetacion);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<UnidadVegetacion> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                unidadVegetacionList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerUnidadVegetacion.setAdapter(adapter);
    }

    private void setPaisValues(ApiService api, String token, Spinner spinnerPais){
        api.getPaises("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pais> paisList = response.body();

                    ArrayAdapter<Pais> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            paisList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerPais.setAdapter(adapter);
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

    private void UTMConverter(double latitude, double longitude, double altitude) {
        int zone = (int) Math.floor(longitude / 6 + 31);
        char hemisphere = (latitude < 0) ? 'S' : 'N';

        double a = 6378137; // WGS84 major axis
        double e = 0.081819191; // WGS84 eccentricity

        double latRad = Math.toRadians(latitude);
        double lonRad = Math.toRadians(longitude);
        double lonOrigin = (zone - 1) * 6 - 180 + 3;  // +3 puts origin in middle of zone
        double lonOriginRad = Math.toRadians(lonOrigin);

        double N = a / Math.sqrt(1 - Math.pow(e * Math.sin(latRad), 2));
        double T = Math.pow(Math.tan(latRad), 2);
        double C = Math.pow(e, 2) / (1 - Math.pow(e, 2)) * Math.pow(Math.cos(latRad), 2);
        double A = Math.cos(latRad) * (lonRad - lonOriginRad);

        double M = a * ((1 - Math.pow(e, 2) / 4 - 3 * Math.pow(e, 4) / 64 - 5 * Math.pow(e, 6) / 256) * latRad
                - (3 * Math.pow(e, 2) / 8 + 3 * Math.pow(e, 4) / 32 + 45 * Math.pow(e, 6) / 1024) * Math.sin(2 * latRad)
                + (15 * Math.pow(e, 4) / 256 + 45 * Math.pow(e, 6) / 1024) * Math.sin(4 * latRad)
                - (35 * Math.pow(e, 6) / 3072) * Math.sin(6 * latRad));

        double easting = (0.9996 * N * (A + (1 - T + C) * Math.pow(A, 3) / 6
                + (5 - 18 * T + T * T + 72 * C - 58 * Math.pow(e, 2) / (1 - Math.pow(e, 2)))
                * Math.pow(A, 5) / 120) + 500000);

        double northing = (0.9996 * (M + N * Math.tan(latRad) * (Math.pow(A, 2) / 2
                + (5 - T + 9 * C + 4 * C * C) * Math.pow(A, 4) / 24
                + (61 - 58 * T + T * T + 600 * C - 330 * Math.pow(e, 2) / (1 - Math.pow(e, 2)))
                * Math.pow(A, 6) / 720)));

        if (latitude < 0) {
            northing += 10000000; // Offset for southern hemisphere
        }

        textViewEste.setText("Este (" + easting + ")");
        textViewNorte.setText("Norte (" + northing + ")");
        textViewAltitud.setText("Altitud (" + altitude + ")");
    }
    private void irALogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}