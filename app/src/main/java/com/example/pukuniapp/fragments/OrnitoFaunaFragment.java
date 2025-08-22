package com.example.pukuniapp.fragments;

import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLIMA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FRANJA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_METODOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TEMPORADA_EVALUACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_MUESTREAL;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_VEGETACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ZONA;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.Clima;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.Metodologia;
import com.example.pukuniapp.classes.TemporadaEvaluacion;
import com.example.pukuniapp.classes.UnidadMuestreal;
import com.example.pukuniapp.classes.UnidadVegetacion;
import com.example.pukuniapp.classes.Zona;
import com.example.pukuniapp.helpers.DBHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrnitoFaunaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrnitoFaunaFragment extends Fragment {
    private Spinner spinnerSubEstacionMuestreo;
    private Spinner spinnerTemporadaEvaluacion;
    private Spinner spinnerUnidadVegetacion;
    private Spinner spinnerZona;
    private Spinner spinnerMetodologia;
    private Spinner spinnerClima;
    private Spinner spinnerTipoRegistro;
    private Spinner spinnerCatXAbundancia;
    private Spinner spinnerHabito;
    private Spinner spinnerGrupoTrofico;
    private Spinner spinnerIndicador;
    private Spinner spinnerEstadio;
    private Spinner spinnerSexo;
    private Spinner spinnerCondicionReproductiva;
    private Spinner spinnerUICN20213;
    private Spinner spinnerUnidadMuestreal;
    private TextView textViewEste;
    private TextView textViewNorte;
    private TextView textViewAltitud;
    private ImageView imgPreview;
    private Uri photoUri;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<Uri> cameraLauncher;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final int REQUEST_GALLERY_PERMISSION = 101;


    public OrnitoFaunaFragment() {
        // Required empty public constructor
    }

    public static OrnitoFaunaFragment newInstance(String estacionName, int estacionId) {
        OrnitoFaunaFragment fragment = new OrnitoFaunaFragment();
        Bundle args = new Bundle();
        args.putInt("estacion_id", estacionId);
        args.putString("estacion_name", estacionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                result -> {
                    if (result) {
                        imgPreview.setImageURI(photoUri);
                    }
                }
        );

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            photoUri = data.getData();
                            imgPreview.setImageURI(photoUri);
                        }
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ornito_fauna, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);
        label.setText("Formulario Ornitofauna");

        spinnerSubEstacionMuestreo = view.findViewById(R.id.spinner_sub_estacion_muestreo);
        spinnerTemporadaEvaluacion = view.findViewById(R.id.spinner_temporada_evaluacion);
        spinnerUnidadVegetacion = view.findViewById(R.id.spinner_unidad_vegetacion);
        spinnerZona = view.findViewById(R.id.spinner_zona);
        spinnerMetodologia = view.findViewById(R.id.spinner_metodologia);
        spinnerClima = view.findViewById(R.id.spinner_clima);
        spinnerTipoRegistro = view.findViewById(R.id.spinner_tipo_registro);
        spinnerCatXAbundancia = view.findViewById(R.id.spinner_cat_x_abundancia);
        spinnerHabito = view.findViewById(R.id.spinner_habito);
        spinnerGrupoTrofico = view.findViewById(R.id.spinner_grupo_trofico);
        spinnerIndicador = view.findViewById(R.id.spinner_indicador);
        spinnerEstadio = view.findViewById(R.id.spinner_estadio);
        spinnerSexo = view.findViewById(R.id.spinner_sexo);
        spinnerCondicionReproductiva = view.findViewById(R.id.spinner_condicion_reproductiva);
        spinnerUICN20213 = view.findViewById(R.id.spinner_uicn_2021_3);
        spinnerUnidadMuestreal = view.findViewById(R.id.spinner_unidad_muestreal);

        textViewEste = view.findViewById(R.id.tv_este);
        textViewNorte = view.findViewById(R.id.tv_norte);
        textViewAltitud = view.findViewById(R.id.tv_altitud);

        imgPreview = view.findViewById(R.id.img_preview);
        Button btnCamera = view.findViewById(R.id.btnCamera);
        Button btnGallery = view.findViewById(R.id.btnGallery);

        btnCamera.setOnClickListener(v -> {
            if (hasPermission(Manifest.permission.CAMERA)) {
                File file = new File(requireContext().getFilesDir(), "flora_" + System.currentTimeMillis() + ".jpg");
                photoUri = FileProvider.getUriForFile(requireContext(),
                        requireContext().getPackageName() + ".fileprovider", file);
                cameraLauncher.launch(photoUri);
            } else {
                requestPermission(Manifest.permission.CAMERA, REQUEST_CAMERA_PERMISSION);
            }
        });

        btnGallery.setOnClickListener(v -> {
            if (hasGalleryPermission()) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(intent);
            } else {
                requestGalleryPermission();
            }
        });

        DBHelper dbHelper = new DBHelper(requireContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        setSubEstacionesValues(db);
        setTemporadaEvaluacionValues(db);
        setUnidadDeVegetacionValues(db);
        setZonaValues(db);
        setMetodologiaValues(db);
        setClimaValues(db);
//        setTipoRegristroValues(db);
//        setCatXAbundanciaValues(db);
//        setHabitoValues(db);
//        setGrupoTroficoValues(db);
//        setIndicadorValues(db);
//        setEstadioValues(db);
//        setSexoValues(db);
//        setCondicionReproductivaValues(db);
//        setUICN20213Values(db);

        getCoordinates();

        return view;
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
                        List<UnidadMuestreal> unidadMuestrealList = new ArrayList<>();

                        Franja franja = (Franja) spinnerSubEstacionMuestreo.getSelectedItem();
                        Metodologia metodologia = (Metodologia) spinnerMetodologia.getSelectedItem();

                        if(franja != null && metodologia != null){
                            Cursor cursor2 = db.rawQuery(
                                    "SELECT * FROM " + TABLE_UNIDAD_MUESTREAL + " WHERE franja_id = ? AND metodologia_id = ?",
                                    new String[]{ String.valueOf(franja.getFranja_id()), String.valueOf(metodologia.getMetodologia_id()) }
                            );

                            if (cursor2.moveToFirst()) {
                                do {
                                    int id = cursor2.getInt(cursor2.getColumnIndexOrThrow("unidad_muestreal_id"));
                                    String unidad_muestreal_name = cursor2.getString(cursor2.getColumnIndexOrThrow("unidad_muestreal_name"));
                                    int franja_id = cursor2.getInt(cursor2.getColumnIndexOrThrow("franja_id"));
                                    int metodologia_id = cursor2.getInt(cursor2.getColumnIndexOrThrow("metodologia_id"));

                                    UnidadMuestreal unidadMuestreal = new UnidadMuestreal();
                                    unidadMuestreal.setUnidad_muestreal_id(id);
                                    unidadMuestreal.setUnidad_muestreal_name(unidad_muestreal_name);
                                    unidadMuestreal.setFranja_id(franja_id);
                                    unidadMuestreal.setMetodologia_id(metodologia_id);

                                    unidadMuestrealList.add(unidadMuestreal);
                                } while (cursor2.moveToNext());
                            }

                            cursor2.close();

                            ArrayAdapter<UnidadMuestreal> adapter2 = new ArrayAdapter<>(
                                    requireContext(),
                                    android.R.layout.simple_spinner_item,
                                    unidadMuestrealList
                            );
                            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinnerUnidadMuestreal.setAdapter(adapter2);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }
    }

    private void setTemporadaEvaluacionValues(SQLiteDatabase db) {
        List<TemporadaEvaluacion> temporadaEvaluacionList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_TEMPORADA_EVALUACION,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("temporada_evaluacion_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("temporada_evaluacion_name"));

                TemporadaEvaluacion temporadaEvaluacion = new TemporadaEvaluacion();
                temporadaEvaluacion.setTemporada_evaluacion_id(id);
                temporadaEvaluacion.setTemporada_evaluacion_name(name);

                temporadaEvaluacionList.add(temporadaEvaluacion);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<TemporadaEvaluacion> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                temporadaEvaluacionList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTemporadaEvaluacion.setAdapter(adapter);
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
                String unidad_vegetacion_name = cursor.getString(cursor.getColumnIndexOrThrow("unidad_vegetacion_name"));

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

    private void setZonaValues(SQLiteDatabase db) {
        List<Zona> zonasList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ZONA,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("zona_id"));
                String zona_name = cursor.getString(cursor.getColumnIndexOrThrow("zona_name"));

                Zona zona = new Zona();
                zona.setZona_id(id);
                zona.setZona_name(zona_name);

                zonasList.add(zona);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Zona> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                zonasList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerZona.setAdapter(adapter);
    }

    private void setMetodologiaValues(SQLiteDatabase db){
        List<Metodologia> metodologiasList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_METODOLOGIA,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("metodologia_id"));
                String metodologia_name = cursor.getString(cursor.getColumnIndexOrThrow("metodologia_name"));

                Metodologia metodologia = new Metodologia();
                metodologia.setMetodologia_id(id);
                metodologia.setMetodologia_name(metodologia_name);

                metodologiasList.add(metodologia);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Metodologia> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                metodologiasList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMetodologia.setAdapter(adapter);

        spinnerMetodologia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<UnidadMuestreal> unidadMuestrealList = new ArrayList<>();

                Franja franja = (Franja) spinnerSubEstacionMuestreo.getSelectedItem();
                Metodologia metodologia = (Metodologia) spinnerMetodologia.getSelectedItem();

                if(franja != null && metodologia != null){
                    Cursor cursor2 = db.rawQuery(
                            "SELECT * FROM " + TABLE_UNIDAD_MUESTREAL + " WHERE franja_id = ? AND metodologia_id = ?",
                            new String[]{ String.valueOf(franja.getFranja_id()), String.valueOf(metodologia.getMetodologia_id()) }
                    );

                    if (cursor2.moveToFirst()) {
                        do {
                            int id = cursor2.getInt(cursor2.getColumnIndexOrThrow("unidad_muestreal_id"));
                            String unidad_muestreal_name = cursor2.getString(cursor2.getColumnIndexOrThrow("unidad_muestreal_name"));
                            int franja_id = cursor2.getInt(cursor2.getColumnIndexOrThrow("franja_id"));
                            int metodologia_id = cursor2.getInt(cursor2.getColumnIndexOrThrow("metodologia_id"));

                            UnidadMuestreal unidadMuestreal = new UnidadMuestreal();
                            unidadMuestreal.setUnidad_muestreal_id(id);
                            unidadMuestreal.setUnidad_muestreal_name(unidad_muestreal_name);
                            unidadMuestreal.setFranja_id(franja_id);
                            unidadMuestreal.setMetodologia_id(metodologia_id);

                            unidadMuestrealList.add(unidadMuestreal);
                        } while (cursor2.moveToNext());
                    }

                    cursor2.close();

                    ArrayAdapter<UnidadMuestreal> adapter2 = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            unidadMuestrealList
                    );
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerUnidadMuestreal.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setClimaValues(SQLiteDatabase db){
        List<Clima> climasList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_CLIMA,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("clima_id"));
                String clima_name = cursor.getString(cursor.getColumnIndexOrThrow("clima_name"));

                Clima clima = new Clima();
                clima.setClima_id(id);
                clima.setClima_name(clima_name);

                climasList.add(clima);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Clima> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                climasList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerClima.setAdapter(adapter);
    }

    private boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{permission}, requestCode);
    }

    private boolean hasGalleryPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestGalleryPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                    REQUEST_GALLERY_PERMISSION);
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_GALLERY_PERMISSION);
        }
    }
}