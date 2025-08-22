package com.example.pukuniapp.fragments;

import static com.example.pukuniapp.helpers.DBHelper.TABLE_FRANJA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TEMPORADA_EVALUACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_VEGETACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ZONA;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.TemporadaEvaluacion;
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
    private Spinner spinnerUnidadMuestreal;
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
        spinnerUnidadMuestreal = view.findViewById(R.id.spinner_unidad_muestreal);
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
//        setMetodologiaValues(db);
//        setUnidadMuestrealValues(db);
//        setClimaValues(db);
//        setTipoRegristroValues(db);
//        setCatXAbundanciaValues(db);
//        setHabitoValues(db);
//        setGrupoTroficoValues(db);
//        setIndicadorValues(db);
//        setEstadioValues(db);
//        setSexoValues(db);
//        setCondicionReproductivaValues(db);
//        setUICN20213Values(db);

        return view;
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