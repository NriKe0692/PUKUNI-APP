package com.example.pukuniapp.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_AUTOR;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLASE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESPECIE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTADIO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FAMILIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FENOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_FLORA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FOROFITO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FRANJA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_GENERO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_HABITO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ORDEN;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_PARCELA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_SUB_PARCELA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_MUESTREO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_VEGETACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_USOS;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pukuniapp.activities.HomeActivity;
import com.example.pukuniapp.activities.LoginActivity;
import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.Autor;
import com.example.pukuniapp.classes.Clase;
import com.example.pukuniapp.classes.Especie;
import com.example.pukuniapp.classes.Estadio;
import com.example.pukuniapp.classes.Familia;
import com.example.pukuniapp.classes.Fenologia;
import com.example.pukuniapp.classes.FormFlora;
import com.example.pukuniapp.classes.Forofito;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.Genero;
import com.example.pukuniapp.classes.Habito;
import com.example.pukuniapp.classes.Orden;
import com.example.pukuniapp.classes.Parcela;
import com.example.pukuniapp.classes.SubParcela;
import com.example.pukuniapp.classes.TipoUsos;
import com.example.pukuniapp.classes.UnidadMuestreo;
import com.example.pukuniapp.classes.UnidadVegetacion;
import com.example.pukuniapp.helpers.DBHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FloraFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FloraFormFragment extends Fragment {
    private static int TIPO_FORM_ID = 1;
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
    Spinner spinner_usos;
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
    EditText et_valor_observaciones;
    EditText et_valor_datos_planta;
    EditText et_tamanio_unidad;
    EditText et_localidad;
    EditText et_codigo_placa;
    EditText et_este;
    EditText et_norte;
    EditText et_altitud;
    Button saveForm;
    List<Clase> clasesList;
    List<Orden> ordenesList;
    List<Familia> familiasList;
    List<Genero> generosList;
    List<Especie> especiesList;

    private ImageView imgPreview;
    private Uri photoUri;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<Uri> cameraLauncher;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final int REQUEST_GALLERY_PERMISSION = 101;
    FormFlora form = null;

    public FloraFormFragment() {
        // Required empty public constructor
    }
    public static FloraFormFragment newInstance(int estacionId, int formularioId) {
        FloraFormFragment fragment = new FloraFormFragment();
        Bundle args = new Bundle();
        args.putInt("formulario_id", formularioId);
        args.putInt("estacion_id", estacionId);

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
        View view = inflater.inflate(R.layout.fragment_flora_form, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        if(label != null){
            label.setText("Formulario Flora");
        }

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
        spinner_usos = view.findViewById(R.id.spinner_usos);
        textViewEste = view.findViewById(R.id.tv_este);
        textViewNorte = view.findViewById(R.id.tv_norte);
        textViewAltitud = view.findViewById(R.id.tv_altitud);
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
        et_valor_observaciones = view.findViewById(R.id.et_valor_observaciones);
        et_valor_datos_planta = view.findViewById(R.id.et_valor_datos_planta);
        et_tamanio_unidad = view.findViewById(R.id.et_tamanio_unidad);
        et_localidad = view.findViewById(R.id.et_localidad);
        et_codigo_placa = view.findViewById(R.id.et_codigo_placa);
        et_este = view.findViewById(R.id.et_este);
        et_norte = view.findViewById(R.id.et_norte);
        et_altitud = view.findViewById(R.id.et_altitud);

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

        saveForm = view.findViewById(R.id.save_form);

        DBHelper dbHelper = new DBHelper(requireContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if(getArguments() != null) {
            int formularioId = getArguments().getInt("formulario_id");

            if(formularioId != -1){
                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_FLORA + " WHERE id = ?", new String[]{String.valueOf(formularioId)});

                if (cursor.moveToFirst()) {
                    do {
                        int estacion_muestreo_id = cursor.getInt(cursor.getColumnIndexOrThrow("estacion_muestreo_id"));
                        int lugar_id = cursor.getInt(cursor.getColumnIndexOrThrow("lugar_id"));
                        String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                        int franja_id = cursor.getInt(cursor.getColumnIndexOrThrow("franja_id"));
                        int unidad_muestreo_id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_muestreo_id"));
                        int parcela_id = cursor.getInt(cursor.getColumnIndexOrThrow("parcela_id"));
                        int forofito_id = cursor.getInt(cursor.getColumnIndexOrThrow("forofito_id"));
                        int sub_parcela_id = cursor.getInt(cursor.getColumnIndexOrThrow("sub_parcela_id"));
                        String tamanio = cursor.getString(cursor.getColumnIndexOrThrow("tamanio"));
                        int unidad_vegetacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_vegetacion_id"));
                        int pais_id = cursor.getInt(cursor.getColumnIndexOrThrow("pais_id"));
                        int departamento_id = cursor.getInt(cursor.getColumnIndexOrThrow("departamento_id"));
                        int provincia_id = cursor.getInt(cursor.getColumnIndexOrThrow("provincia_id"));
                        int distrito_id = cursor.getInt(cursor.getColumnIndexOrThrow("distrito_id"));
                        String localidad = cursor.getString(cursor.getColumnIndexOrThrow("localidad"));
                        Double este = cursor.getDouble(cursor.getColumnIndexOrThrow("este"));
                        Double norte = cursor.getDouble(cursor.getColumnIndexOrThrow("norte"));
                        Double altitud = cursor.getDouble(cursor.getColumnIndexOrThrow("altitud"));
                        int clase_id = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));
                        int orden_id = cursor.getInt(cursor.getColumnIndexOrThrow("orden_id"));
                        int familia_id = cursor.getInt(cursor.getColumnIndexOrThrow("familia_id"));
                        int genero_id = cursor.getInt(cursor.getColumnIndexOrThrow("genero_id"));
                        int especie_id = cursor.getInt(cursor.getColumnIndexOrThrow("especie_id"));
                        String nombre_comun = cursor.getString(cursor.getColumnIndexOrThrow("nombre_comun"));
                        int autor_id = cursor.getInt(cursor.getColumnIndexOrThrow("autor_id"));
                        int individuos = cursor.getInt(cursor.getColumnIndexOrThrow("individuos"));
                        Double dap = cursor.getDouble(cursor.getColumnIndexOrThrow("dap"));
                        Double altura = cursor.getDouble(cursor.getColumnIndexOrThrow("altura"));
                        Double valor_cobertura = cursor.getDouble(cursor.getColumnIndexOrThrow("valor_cobertura"));
                        int habito_id = cursor.getInt(cursor.getColumnIndexOrThrow("habito_id"));
                        int estadio_id = cursor.getInt(cursor.getColumnIndexOrThrow("estadio_id"));
                        int fenologia_id = cursor.getInt(cursor.getColumnIndexOrThrow("fenologia_id"));
                        int uso_id = cursor.getInt(cursor.getColumnIndexOrThrow("uso_id"));
                        String image_uri = cursor.getString(cursor.getColumnIndexOrThrow("image_uri"));
                        String observaciones = cursor.getString(cursor.getColumnIndexOrThrow("observaciones"));
                        String datos_planta = cursor.getString(cursor.getColumnIndexOrThrow("datos_planta"));
                        String codigo_placa = cursor.getString(cursor.getColumnIndexOrThrow("codigo_placa"));

                        Log.d("setUnidad_muestreo_id", String.valueOf(unidad_muestreo_id));

                        form = new FormFlora();

                        form.setId(formularioId);
                        form.setEstacion_muestreo_id(estacion_muestreo_id);
                        form.setLugar_id(lugar_id);
                        form.setFecha(fecha);
                        form.setFranja_id(franja_id);
                        form.setUnidad_muestreo_id(unidad_muestreo_id);
                        form.setParcela_id(parcela_id);
                        form.setForofito_id(forofito_id);
                        form.setSub_parcela_id(sub_parcela_id);
                        form.setTamanio(tamanio);
                        form.setUnidad_vegetacion_id(unidad_vegetacion_id);
                        form.setPais_id(pais_id);
                        form.setDepartamento_id(departamento_id);
                        form.setProvincia_id(provincia_id);
                        form.setDistrito_id(distrito_id);
                        form.setLocalidad(localidad);
                        form.setEste(este);
                        form.setNorte(norte);
                        form.setAltitud(altitud);
                        form.setClase_id(clase_id);
                        form.setOrden_id(orden_id);
                        form.setFamilia_id(familia_id);
                        form.setGenero_id(genero_id);
                        form.setEspecie_id(especie_id);
                        form.setNombre_comun(nombre_comun);
                        form.setAutor_id(autor_id);
                        form.setIndividuos(individuos);
                        form.setDap(dap);
                        form.setAltura(altura);
                        form.setValor_cobertura(valor_cobertura);
                        form.setHabito_id(habito_id);
                        form.setEstadio_id(estadio_id);
                        form.setFenologia_id(fenologia_id);
                        form.setUso_id(uso_id);
                        form.setImageUri(image_uri);
                        form.setObservaciones(observaciones);
                        form.setDatosPlanta(datos_planta);
                        form.setCodigo_placa(codigo_placa);
                    } while (cursor.moveToNext());
                }
            }
        }

        setSubEstacionesValues(db);
        setUnidadeDeMuestreoValues(db);
        setUnidadDeVegetacionValues(db);
        setAutoresValues(db);
        setHabitosValues(db);
        setEstadiosValues(db);
        setFenologiasValues(db);
        setUsosValues(db);
        setupAutocompleteTv(db);
        getCoordinates();

        if(form != null){
            et_tamanio_unidad.setText(String.valueOf(form.getTamanio()));
            et_codigo_placa.setText(String.valueOf(form.getCodigo_placa()));
            et_localidad.setText(String.valueOf(form.getLocalidad()));
            et_este.setText(String.valueOf(form.getEste()));
            et_norte.setText(String.valueOf(form.getNorte()));
            et_altitud.setText(String.valueOf(form.getAltitud()));
            et_nombre_comun.setText(String.valueOf(form.getNombre_comun()));
            et_individuos.setText(String.valueOf(form.getIndividuos()));
            et_dap.setText(String.valueOf(form.getDap()));
            et_altura.setText(String.valueOf(form.getAltura()));
            et_valor_cobertura.setText(String.valueOf(form.getValor_cobertura()));
            et_valor_observaciones.setText(String.valueOf(form.getObservaciones()));
            et_valor_datos_planta.setText(String.valueOf(form.getDatosPlanta()));
            imgPreview.setImageURI(Uri.parse(form.getImageUri()));
        }

        saveForm.setOnClickListener(v -> {
            saveForm(db);
        });

        return view;
    }

    private void saveForm(SQLiteDatabase db){
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
        TipoUsos uso = (TipoUsos) spinner_usos.getSelectedItem();

        Log.d("FOROFITO", forofito.getForofito_name());

        String este = et_este.getText().toString();
        String norte = et_norte.getText().toString();
        String altitud = et_altitud.getText().toString();

        Clase clase = null;
        Orden orden = null;
        Familia familia = null;
        Genero genero = null;
        Especie especie = null;

        for(Clase c: clasesList){
            if(c.getClase_name().equals(et_clase.getText().toString())){
                clase = c;
                break;
            }
        }

        for(Orden o: ordenesList){
            if(o.getOrden_name().equals(et_orden.getText().toString())){
                orden = o;
                break;
            }
        }

        for(Familia f: familiasList){
            if(f.getFamilia_name().equals(et_familia.getText().toString())){
                familia = f;
                break;
            }
        }

        for(Genero g: generosList){
            if(g.getGenero_name().equals(et_genero.getText().toString())){
                genero = g;
                break;
            }
        }

        for(Especie e: especiesList){
            if(e.getEspecie_name().equals(et_especie.getText().toString())){
                especie = e;
                break;
            }
        }

        String nombreComun = et_nombre_comun.getText().toString();
        String numIndividuos = et_individuos.getText().toString();
        String dap = et_dap.getText().toString();
        String altura = et_altura.getText().toString();
        String valorCobertura = et_valor_cobertura.getText().toString();
        String observaciones = et_valor_observaciones.getText().toString();
        String datosPlanta = et_valor_datos_planta.getText().toString();
        String uriString = (photoUri != null) ? photoUri.toString() : "";
        String tamanioUnidad = et_tamanio_unidad.getText().toString();
        String localidad = et_localidad.getText().toString();
        String codigoPlaca = et_codigo_placa.getText().toString();


        int estacionId = -1;

        Bundle args = getArguments();
        if (args != null) {
            estacionId = args.getInt("estacion_id");
        }

        SimpleDateFormat sdfFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Date ahora = new Date();

        SharedPreferences prefs = getActivity().getSharedPreferences("PukuniPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        ContentValues values = new ContentValues();
        values.put("estacion_muestreo_id", estacionId != -1 ? estacionId : null);
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
        values.put("clase_id", clase != null ? clase.getClase_id() : null);
        values.put("orden_id", orden != null ? orden.getOrden_id() : null);
        values.put("familia_id", familia != null ? familia.getFamilia_id() : null);
        values.put("genero_id", genero != null ? genero.getGenero_id() : null);
        values.put("especie_id", especie != null ? especie.getEspecie_id() : null);
        values.put("nombre_comun", nombreComun);
        values.put("este", este);
        values.put("norte", norte);
        values.put("altitud", altitud);
        values.put("individuos", numIndividuos);
        values.put("dap", dap);
        values.put("altura", altura);
        values.put("valor_cobertura", valorCobertura);
        values.put("uso_id", uso.getUsos_id());
        values.put("observaciones", observaciones);
        values.put("datos_planta", datosPlanta);
        values.put("image_uri", uriString);
        values.put("fecha", sdfFecha.format(ahora));
        values.put("tamanio", tamanioUnidad);
        values.put("localidad", localidad);
        values.put("codigo_placa", codigoPlaca);
        values.put("especialista_id", userId);

        long newRowId;

        if(form != null){
            Log.d("UPDATE!", String.valueOf(form.getId()));
            newRowId = db.update(TABLE_FORMULARIO_FLORA, values, " id = ?", new String[]{String.valueOf(form.getId())});

            if (newRowId != -1) {
                Toast.makeText(requireContext(), "Registro actualizado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Error al actualizar el registro", Toast.LENGTH_SHORT).show();
            }
        }else{
            newRowId = db.insert(TABLE_FORMULARIO_FLORA, null, values);

            if (newRowId != -1) {
                Toast.makeText(requireContext(), "Registro guardado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Error al guardar el registro", Toast.LENGTH_SHORT).show();
            }
        }

        db.close();

        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
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

    private void setupAutocompleteTv(SQLiteDatabase db){
        // Start set clases values
        clasesList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_CLASE + " WHERE tipo_form_id = ?",
                new String[]{String.valueOf(TIPO_FORM_ID)}
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));
                String clase_name = cursor.getString(cursor.getColumnIndexOrThrow("clase_name"));
                int tipo_form_id = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_form_id"));

                Clase clase = new Clase();
                clase.setClase_id(id);
                clase.setClase_name(clase_name);
                clase.setTipo_form_id(tipo_form_id);

                clasesList.add(clase);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Clase> claseAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                clasesList
        );

        et_clase.setAdapter(claseAdapter);
        et_clase.setThreshold(1);

        if(form != null){
            String claseSelected = "";

            for (int it = 0; it < clasesList.size(); it++) {
                if(clasesList.get(it).getClase_id() == form.getClase_id()){
                    claseSelected = clasesList.get(it).getClase_name();
                    break;
                }
            }

            if(!claseSelected.isEmpty()){
                et_clase.setText(claseSelected);
            }
        }
        // End set clases values

        // Start set ordenes values
        ordenesList = new ArrayList<>();

        cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ORDEN + " WHERE tipo_form_id = ?",
                new String[]{String.valueOf(TIPO_FORM_ID)}
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

        ArrayAdapter<Orden> ordenAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                ordenesList
        );

        et_orden.setAdapter(ordenAdapter);
        et_orden.setThreshold(1);

        et_orden.setOnItemClickListener((parent, view, position, id) -> {
            String ordenName = parent.getItemAtPosition(position).toString();

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

        if(form != null){
            String ordenSelected = "";

            for (int it = 0; it < ordenesList.size(); it++) {
                if(ordenesList.get(it).getOrden_id() == form.getOrden_id()){
                    ordenSelected = ordenesList.get(it).getOrden_name();
                    break;
                }
            }

            if(!ordenSelected.isEmpty()){
                et_orden.setText(ordenSelected);
            }
        }
        // End set ordenes values

        // Start set familias values
        familiasList = new ArrayList<>();

        cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_FAMILIA + " WHERE tipo_form_id = ?",
                new String[]{String.valueOf(TIPO_FORM_ID)}
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

        ArrayAdapter<Familia> familiaAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                familiasList
        );

        et_familia.setAdapter(familiaAdapter);
        et_familia.setThreshold(1);

        et_familia.setOnItemClickListener((parent, view, position, id) -> {
            String familiaName = parent.getItemAtPosition(position).toString();

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

        if(form != null){
            String familiaSelected = "";

            for (int it = 0; it < familiasList.size(); it++) {
                if(familiasList.get(it).getFamilia_id() == form.getFamilia_id()){
                    familiaSelected = familiasList.get(it).getFamilia_name();
                    break;
                }
            }

            if(!familiaSelected.isEmpty()){
                et_familia.setText(familiaSelected);
            }
        }
        // End set familias values

        // Start set generos values
        generosList = new ArrayList<>();

        cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_GENERO + " WHERE tipo_form_id = ?",
                new String[]{String.valueOf(TIPO_FORM_ID)}
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

        ArrayAdapter<Genero> generoAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                generosList
        );

        et_genero.setAdapter(generoAdapter);
        et_genero.setThreshold(1);

        et_genero.setOnItemClickListener((parent, view, position, id) -> {
            String generoName = parent.getItemAtPosition(position).toString();

            Toast.makeText(getActivity(), generoName, Toast.LENGTH_SHORT).show();

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

        if(form != null){
            String generoSelected = "";

            for (int it = 0; it < generosList.size(); it++) {
                if(generosList.get(it).getGenero_id() == form.getGenero_id()){
                    generoSelected = generosList.get(it).getGenero_name();
                    break;
                }
            }

            if(!generoSelected.isEmpty()){
                et_genero.setText(generoSelected);
            }
        }
        // End set generos values

        // Start set especies values
        especiesList = new ArrayList<>();

        cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ESPECIE + " WHERE tipo_form_id = ?",
                new String[]{String.valueOf(TIPO_FORM_ID)}
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

        ArrayAdapter<Especie> especieAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                especiesList
        );

        et_especie.setAdapter(especieAdapter);
        et_especie.setThreshold(1);

        et_especie.setOnItemClickListener((parent, view, position, id) -> {
            String especieName = parent.getItemAtPosition(position).toString();

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

        if(form != null){
            String especieSelected = "";

            for (int it = 0; it < especiesList.size(); it++) {
                if(especiesList.get(it).getEspecie_id() == form.getEspecie_id()){
                    especieSelected = especiesList.get(it).getEspecie_name();
                    break;
                }
            }

            if(!especieSelected.isEmpty()){
                et_especie.setText(especieSelected);
            }
        }
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
            "SELECT * FROM " + TABLE_HABITO + " WHERE tipo_form_id = ?",
            new String[]{String.valueOf(TIPO_FORM_ID)}
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

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < habitosList.size(); i++) {
                if (habitosList.get(i).getHabito_id() == form.getHabito_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinnerHabito.setSelection(defaultPosition);
            }
        }
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

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < estdiosList.size(); i++) {
                if (estdiosList.get(i).getEstadio_id() == form.getEstadio_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinnerEstadio.setSelection(defaultPosition);
            }
        }
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

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < fenologiaList.size(); i++) {
                if (fenologiaList.get(i).getFenologia_id() == form.getFenologia_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinnerFenologia.setSelection(defaultPosition);
            }
        }
    }

    private void setUsosValues(SQLiteDatabase db){
        List<TipoUsos> usosList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_USOS + " WHERE tipo_form_id = ?",
                new String[]{String.valueOf(TIPO_FORM_ID)}
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("usos_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("usos_name"));
                int tipo_form_id = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_form_id"));

                TipoUsos uso = new TipoUsos();
                uso.setUsos_id(id);
                uso.setUsos_name(name);
                uso.setTipo_form_id(tipo_form_id);

                usosList.add(uso);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<TipoUsos> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                usosList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_usos.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < usosList.size(); i++) {
                if (usosList.get(i).getUsos_id() == form.getUso_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_usos.setSelection(defaultPosition);
            }
        }
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

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < autoresList.size(); i++) {
                if (autoresList.get(i).getAutor_id() == form.getAutor_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinnerAutores.setSelection(defaultPosition);
            }
        }
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
                        Franja franja = (Franja) spinnerSubEstacionMuestreo.getSelectedItem();

                        if (franja != null) {
                            setParcelaValues(db, franja);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                if(form != null){
                    int defaultPosition = -1;

                    for (int i = 0; i < franjasList.size(); i++) {
                        if (franjasList.get(i).getFranja_id() == form.getFranja_id()) {
                            defaultPosition = i;
                            break;
                        }
                    }

                    if(defaultPosition != -1){
                        spinnerSubEstacionMuestreo.setSelection(defaultPosition);
                    }
                }
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

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < unidadMuestreoList.size(); i++) {
                if (unidadMuestreoList.get(i).getUnidad_muestreo_id() == form.getUnidad_muestreo_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinnerUnidadMuestreo.setSelection(defaultPosition);
            }
        }
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

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < parcelaList.size(); i++) {
                if (parcelaList.get(i).getParcela_id() == form.getParcela_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinnerParcela.setSelection(defaultPosition);
            }
        }
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

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < forofitoList.size(); i++) {
                if (forofitoList.get(i).getForofito_id() == form.getForofito_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinnerForofito.setSelection(defaultPosition);
            }
        }
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
                String subparcela_name = cursor.getString(cursor.getColumnIndexOrThrow("subparcela_name"));
                int parcela_id = cursor.getInt(cursor.getColumnIndexOrThrow("parcela_id"));

                SubParcela subParcela = new SubParcela();
                subParcela.setSubparcela_id(id);
                subParcela.setSubparcela_name(subparcela_name);
                subParcela.setParcela_id(parcelaId);

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

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < subParcelaList.size(); i++) {
                if (subParcelaList.get(i).getSubparcela_id() == form.getSub_parcela_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinnerSubParcela.setSelection(defaultPosition);
            }
        }
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

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < unidadVegetacionList.size(); i++) {
                if (unidadVegetacionList.get(i).getUnidad_vegetacion_id() == form.getUnidad_vegetacion_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinnerUnidadVegetacion.setSelection(defaultPosition);
            }
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

        if(form != null){
            textViewEste.setText("Este (" + form.getEste() + ")");
            textViewNorte.setText("Norte (" + form.getNorte() + ")");
            textViewAltitud.setText("Altitud (" + form.getAltitud() + ")");
        }else{
            textViewEste.setText("Este (" + easting + ")");
            textViewNorte.setText("Norte (" + northing + ")");
            textViewAltitud.setText("Altitud (" + altitude + ")");
        }
    }
    private void irALogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}