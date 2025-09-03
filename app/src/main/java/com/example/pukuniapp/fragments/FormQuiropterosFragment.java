package com.example.pukuniapp.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CATEGORIA_ABUNDANCIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLASE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLIMA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CONDICION_REPRODUCTIVA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESPECIE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTADIO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTADO_CONSERVACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FAMILIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_QUIROPTEROS;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FRANJA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_GENERO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_GRUPO_TROFICO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_HABITO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_INDICADOR;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_METODOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ORDEN;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TEMPORADA_EVALUACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TIPO_TRAMPA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_UNIDAD_MUESTREAL;
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

import com.example.pukuniapp.R;
import com.example.pukuniapp.activities.HomeActivity;
import com.example.pukuniapp.classes.CategoriaAbundancia;
import com.example.pukuniapp.classes.Clase;
import com.example.pukuniapp.classes.Clima;
import com.example.pukuniapp.classes.CondicionReproductiva;
import com.example.pukuniapp.classes.Especie;
import com.example.pukuniapp.classes.Estadio;
import com.example.pukuniapp.classes.EstadoConservacion;
import com.example.pukuniapp.classes.Familia;
import com.example.pukuniapp.classes.FormQuiroptero;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.Genero;
import com.example.pukuniapp.classes.GrupoTrofico;
import com.example.pukuniapp.classes.Habito;
import com.example.pukuniapp.classes.Indicador;
import com.example.pukuniapp.classes.Metodologia;
import com.example.pukuniapp.classes.Orden;
import com.example.pukuniapp.classes.TemporadaEvaluacion;
import com.example.pukuniapp.classes.TipoTrampa;
import com.example.pukuniapp.classes.TipoUsos;
import com.example.pukuniapp.classes.UnidadMuestreal;
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
 * Use the {@link FormQuiropterosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormQuiropterosFragment extends Fragment {
    private Spinner spinner_temporada_evaluacion;
    private Spinner spinner_unidad_vegetacion;
    private Spinner spinner_clima;
    private Spinner spinner_metodologia;
    private Spinner spinner_tipo_trampa;
    private Spinner spinner_franja;
    private Spinner spinner_estadio;
    private Spinner spinner_sexo;
    private Spinner spinner_condicion_reproductiva;
    private Spinner spinner_cat_x_abundancia;
    private Spinner spinner_habito;
    private Spinner spinner_unidad_muestreal;
    private Spinner spinner_grupo_trofico;
    private Spinner spinner_indicador;
    private Spinner spinner_usos;
    private Spinner spinner_estado_conservacion_habitat;
    private EditText et_este;
    private EditText et_norte;
    private EditText et_altitud;
    private EditText et_nombre_comun;
    private EditText et_individuos;
    private EditText et_endemismo;
    private EditText et_longitud_antebrazo;
    private EditText et_longitud_cola;
    private EditText et_longitud_oreja;
    private EditText et_longitud_tibia;
    private EditText et_peso;
    private EditText et_uicn;
    private EditText et_cites;
    private EditText et_dsn;
    private EditText et_libro_rojo;
    private EditText et_distribucion_endemismo;
    private EditText et_ibas;
    private EditText et_ebas;
    private EditText et_observaciones;
    private AutoCompleteTextView et_clase;
    private AutoCompleteTextView et_orden;
    private AutoCompleteTextView et_familia;
    private AutoCompleteTextView et_genero;
    private AutoCompleteTextView et_especie;
    private TextView tv_este;
    private TextView tv_norte;
    private TextView tv_altitud;
    private ImageView img_preview;
    private Button save_form;
    private Uri photoUri;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<Uri> cameraLauncher;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final int REQUEST_GALLERY_PERMISSION = 101;
    List<Clase> clasesList;
    List<Orden> ordenesList;
    List<Familia> familiasList;
    List<Genero> generosList;
    List<Especie> especiesList;
    private static int TIPO_FORM_ID = 34;
    FormQuiroptero form = null;

    public FormQuiropterosFragment() {
        // Required empty public constructor
    }

    public static FormQuiropterosFragment newInstance(int estacionMuestreoId, int formId) {
        FormQuiropterosFragment fragment = new FormQuiropterosFragment();
        Bundle args = new Bundle();
        args.putInt("estacion_id", estacionMuestreoId);
        args.putInt("formulario_id", formId);
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
                        img_preview.setImageURI(photoUri);
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
                            img_preview.setImageURI(photoUri);
                        }
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_quiropteros, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        if(label != null){
            label.setText("Formulario Quirópteros");
        }

        spinner_temporada_evaluacion = view.findViewById(R.id.spinner_temporada_evaluacion);
        spinner_unidad_vegetacion = view.findViewById(R.id.spinner_unidad_vegetacion);
        spinner_clima = view.findViewById(R.id.spinner_clima);
        spinner_metodologia = view.findViewById(R.id.spinner_metodologia);
        spinner_tipo_trampa = view.findViewById(R.id.spinner_tipo_trampa);
        spinner_franja = view.findViewById(R.id.spinner_franja);
        spinner_estadio = view.findViewById(R.id.spinner_estadio);
        spinner_sexo = view.findViewById(R.id.spinner_sexo);
        spinner_condicion_reproductiva = view.findViewById(R.id.spinner_condicion_reproductiva);
        spinner_cat_x_abundancia = view.findViewById(R.id.spinner_cat_x_abundancia);
        spinner_habito = view.findViewById(R.id.spinner_habito);
        spinner_grupo_trofico = view.findViewById(R.id.spinner_grupo_trofico);
        spinner_indicador = view.findViewById(R.id.spinner_indicador);
        spinner_usos = view.findViewById(R.id.spinner_usos);
        spinner_estado_conservacion_habitat = view.findViewById(R.id.spinner_estado_conservacion_habitat);
        spinner_unidad_muestreal = view.findViewById(R.id.spinner_unidad_muestreal);
        et_endemismo = view.findViewById(R.id.et_endemismo);
        et_este = view.findViewById(R.id.et_este);
        et_norte = view.findViewById(R.id.et_norte);
        et_altitud = view.findViewById(R.id.et_altitud);
        et_nombre_comun = view.findViewById(R.id.et_nombre_comun);
        et_individuos = view.findViewById(R.id.et_individuos);
        et_longitud_antebrazo = view.findViewById(R.id.et_longitud_antebrazo);
        et_longitud_cola = view.findViewById(R.id.et_longitud_cola);
        et_longitud_oreja = view.findViewById(R.id.et_longitud_oreja);
        et_longitud_tibia = view.findViewById(R.id.et_longitud_tibia);
        et_peso = view.findViewById(R.id.et_peso);
        et_uicn = view.findViewById(R.id.et_uicn);
        et_cites = view.findViewById(R.id.et_cites);
        et_dsn = view.findViewById(R.id.et_dsn);
        et_libro_rojo = view.findViewById(R.id.et_libro_rojo);
        et_distribucion_endemismo = view.findViewById(R.id.et_distribucion_endemismo);
        et_ibas = view.findViewById(R.id.et_ibas);
        et_ebas = view.findViewById(R.id.et_ebas);
        et_observaciones = view.findViewById(R.id.et_observaciones);
        et_clase = view.findViewById(R.id.et_clase);
        et_orden = view.findViewById(R.id.et_orden);
        et_familia = view.findViewById(R.id.et_familia);
        et_genero = view.findViewById(R.id.et_genero);
        et_especie = view.findViewById(R.id.et_especie);
        tv_este = view.findViewById(R.id.tv_este);
        tv_norte = view.findViewById(R.id.tv_norte);
        tv_altitud = view.findViewById(R.id.tv_altitud);
        img_preview = view.findViewById(R.id.img_preview);
        save_form = view.findViewById(R.id.save_form);
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

        if(getArguments() != null){
            int formularioId = getArguments().getInt("formulario_id");

            if(formularioId != -1){
                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_QUIROPTEROS + " WHERE id = ?", new String[]{String.valueOf(formularioId)});
                if (cursor.moveToFirst()) {
                    do {
                        int estacion_muestreo_id = cursor.getInt(cursor.getColumnIndexOrThrow("estacion_muestreo_id"));
                        int temporada_evaluacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("temporada_evaluacion_id"));
                        int unidad_vegetacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_vegetacion_id"));
                        String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                        String hora = cursor.getString(cursor.getColumnIndexOrThrow("hora"));
                        int clima_id = cursor.getInt(cursor.getColumnIndexOrThrow("clima_id"));
                        int franja_id = cursor.getInt(cursor.getColumnIndexOrThrow("franja_id"));
                        int metodologia_id = cursor.getInt(cursor.getColumnIndexOrThrow("metodologia_id"));
                        int tipo_trampa_id = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_trampa_id"));
                        int unidad_muestreal_id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_muestreal_id"));
                        float este = cursor.getFloat(cursor.getColumnIndexOrThrow("este"));
                        float norte = cursor.getFloat(cursor.getColumnIndexOrThrow("norte"));
                        float altitud = cursor.getFloat(cursor.getColumnIndexOrThrow("altitud"));
                        int clase_id = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));
                        int orden_id = cursor.getInt(cursor.getColumnIndexOrThrow("orden_id"));
                        int familia_id = cursor.getInt(cursor.getColumnIndexOrThrow("familia_id"));
                        int genero_id = cursor.getInt(cursor.getColumnIndexOrThrow("genero_id"));
                        int especie_id = cursor.getInt(cursor.getColumnIndexOrThrow("especie_id"));
                        String nombre_comun = cursor.getString(cursor.getColumnIndexOrThrow("nombre_comun"));
                        int numero_individuos = cursor.getInt(cursor.getColumnIndexOrThrow("numero_individuos"));
                        int estadio_id = cursor.getInt(cursor.getColumnIndexOrThrow("estadio_id"));
                        String sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo"));
                        int condicion_reproductiva_id = cursor.getInt(cursor.getColumnIndexOrThrow("condicion_reproductiva_id"));
                        int categoria_abundancia_id = cursor.getInt(cursor.getColumnIndexOrThrow("categoria_abundancia_id"));
                        int habito_id = cursor.getInt(cursor.getColumnIndexOrThrow("habito_id"));
                        int grupo_trofico_id = cursor.getInt(cursor.getColumnIndexOrThrow("grupo_trofico_id"));
                        float longitud_antebrazo = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_antebrazo"));
                        float longitud_oreja = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_oreja"));
                        float longitud_cola = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_cola"));
                        float longitud_tibia = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_tibia"));
                        float peso = cursor.getFloat(cursor.getColumnIndexOrThrow("peso"));
                        int indicador_id = cursor.getInt(cursor.getColumnIndexOrThrow("indicador_id"));
                        String uicn = cursor.getString(cursor.getColumnIndexOrThrow("uicn"));
                        String cites = cursor.getString(cursor.getColumnIndexOrThrow("cites"));
                        String dsn = cursor.getString(cursor.getColumnIndexOrThrow("dsn"));
                        String libro_rojo = cursor.getString(cursor.getColumnIndexOrThrow("libro_rojo"));
                        String endemismo = cursor.getString(cursor.getColumnIndexOrThrow("endemismo"));
                        String distribucion_endemismo = cursor.getString(cursor.getColumnIndexOrThrow("distribucion_endemismo"));
                        String ibas = cursor.getString(cursor.getColumnIndexOrThrow("ibas"));
                        String ebas = cursor.getString(cursor.getColumnIndexOrThrow("ebas"));
                        String comentario = cursor.getString(cursor.getColumnIndexOrThrow("comentario"));
                        String image_uri = cursor.getString(cursor.getColumnIndexOrThrow("image_uri"));
                        int estado_conservacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("estado_conservacion_id"));

                        form = new FormQuiroptero();

                        form.setId(formularioId);
                        form.setEstacion_muestreo_id(estacion_muestreo_id);
                        form.setTemporada_evaluacion_id(temporada_evaluacion_id);
                        form.setUnidad_vegetacion_id(unidad_vegetacion_id);
                        form.setFecha(fecha);
                        form.setHora(hora);
                        form.setClima_id(clima_id);
                        form.setFranja_id(franja_id);
                        form.setMetodologia_id(metodologia_id);
                        form.setTipo_trampa_id(tipo_trampa_id);
                        form.setUnidad_muestreal_id(unidad_muestreal_id);
                        form.setEste(este);
                        form.setNorte(norte);
                        form.setAltitud(altitud);
                        form.setClase_id(clase_id);
                        form.setOrden_id(orden_id);
                        form.setFamilia_id(familia_id);
                        form.setGenero_id(genero_id);
                        form.setEspecie_id(especie_id);
                        form.setNombre_comun(nombre_comun);
                        form.setNumero_individuos(numero_individuos);
                        form.setEstadio_id(estadio_id);
                        form.setSexo(sexo);
                        form.setCondicion_reproductiva_id(condicion_reproductiva_id);
                        form.setCategoria_abundancia_id(categoria_abundancia_id);
                        form.setHabito_id(habito_id);
                        form.setGrupo_trofico_id(grupo_trofico_id);
                        form.setLongitud_antebrazo(longitud_antebrazo);
                        form.setLongitud_oreja(longitud_oreja);
                        form.setLongitud_cola(longitud_cola);
                        form.setLongitud_tibia(longitud_tibia);
                        form.setPeso(peso);
                        form.setIndicador_id(indicador_id);
                        form.setUicn(uicn);
                        form.setCites(cites);
                        form.setDsn(dsn);
                        form.setLibro_rojo(libro_rojo);
                        form.setEndemismo(endemismo);
                        form.setDistribucion_endemismo(distribucion_endemismo);
                        form.setIbas(ibas);
                        form.setEbas(ebas);
                        form.setComentario(comentario);
                        form.setImage_uri(image_uri);
                        form.setEstado_conservacion_id(estado_conservacion_id);
//                        form.setProyecto_id();

                    } while (cursor.moveToNext());
                }
            }
        }

        setTemporadaEvaluacionValues(db);
        setUnidadDeVegetacionValues(db);
        setClimaValues(db);
        setMetodologiaValues(db);
        setSubEstacionesValues(db);
        setEstadioValues(db);
        setEstadoConservacionValues(db);
        setSexoValues();
        setIndicadorValues(db);
        setCatXAbundanciaValues(db);
        setHabitosValues(db);
        setGruposTroficosValues(db);
        setTipoTrampaValues(db);
        setCondicionReproductivaValues(db);
        setUsosValues(db);

        setupAutocompleteTv(db);
        getCoordinates();

        if(form != null){
            et_endemismo.setText(String.valueOf(form.getEndemismo()));
            et_este.setText(String.valueOf(form.getEste()));
            et_norte.setText(String.valueOf(form.getNorte()));
            et_altitud.setText(String.valueOf(form.getAltitud()));
            et_nombre_comun.setText(String.valueOf(form.getNombre_comun()));
            et_individuos.setText(String.valueOf(form.getNumero_individuos()));
            et_longitud_antebrazo.setText(String.valueOf(form.getLongitud_antebrazo()));
            et_longitud_cola.setText(String.valueOf(form.getLongitud_cola()));
            et_longitud_oreja.setText(String.valueOf(form.getLongitud_oreja()));
            et_longitud_tibia.setText(String.valueOf(form.getLongitud_tibia()));
            et_peso.setText(String.valueOf(form.getPeso()));
            et_uicn.setText(String.valueOf(form.getUicn()));
            et_cites.setText(String.valueOf(form.getCites()));
            et_dsn.setText(String.valueOf(form.getDsn()));
            et_libro_rojo.setText(String.valueOf(form.getLibro_rojo()));
            et_distribucion_endemismo.setText(String.valueOf(form.getDistribucion_endemismo()));
            et_ibas.setText(String.valueOf(form.getIbas()));
            et_ebas.setText(String.valueOf(form.getEbas()));
            et_observaciones.setText(String.valueOf(form.getComentario()));
            img_preview.setImageURI(Uri.parse(form.getImage_uri()));
        }

        save_form.setOnClickListener(v -> {
            saveForm(db);
        });

        return view;
    }

    private void saveForm(SQLiteDatabase db){
        // Recoger valores de los spinners
        TemporadaEvaluacion temporadaEvaluacion = (TemporadaEvaluacion) spinner_temporada_evaluacion.getSelectedItem();
        UnidadVegetacion unidadVegetacion = (UnidadVegetacion) spinner_unidad_vegetacion.getSelectedItem();
        Clima clima = (Clima) spinner_clima.getSelectedItem();
        Metodologia metodologia = (Metodologia) spinner_metodologia.getSelectedItem();
        TipoTrampa tipoTrampa = (TipoTrampa) spinner_tipo_trampa.getSelectedItem();
        Franja franja = (Franja) spinner_franja.getSelectedItem();
        UnidadMuestreal unidadMuestreal = (UnidadMuestreal) spinner_unidad_muestreal.getSelectedItem();
        Estadio estadio = (Estadio) spinner_estadio.getSelectedItem();
        String sexo = spinner_sexo.getSelectedItem().toString();
        CondicionReproductiva condicionReproductiva = (CondicionReproductiva) spinner_condicion_reproductiva.getSelectedItem();
        CategoriaAbundancia categoriaAbundancia = (CategoriaAbundancia) spinner_cat_x_abundancia.getSelectedItem();
        Habito habito = (Habito) spinner_habito.getSelectedItem();
        GrupoTrofico grupoTrofico = (GrupoTrofico) spinner_grupo_trofico.getSelectedItem();
        Indicador indicador = (Indicador) spinner_indicador.getSelectedItem();
        TipoUsos uso = (TipoUsos) spinner_usos.getSelectedItem();
        EstadoConservacion estadoConservacion = (EstadoConservacion) spinner_estado_conservacion_habitat.getSelectedItem();

        // Recoger valores de los campos de texto
        String endemismo = et_endemismo.getText().toString();
        String este = et_este.getText().toString();
        String norte = et_norte.getText().toString();
        String altitud = et_altitud.getText().toString();
        String nombre_comun = et_nombre_comun.getText().toString();
        String individuos = et_individuos.getText().toString();
        String longitud_antebrazo = et_longitud_antebrazo.getText().toString();
        String longitud_cola = et_longitud_cola.getText().toString();
        String longitud_oreja = et_longitud_oreja.getText().toString();
        String longitud_tibia = et_longitud_tibia.getText().toString();
        String peso = et_peso.getText().toString();
        String uicn = et_uicn.getText().toString();
        String cites = et_cites.getText().toString();
        String dsn = et_dsn.getText().toString();
        String libro_rojo = et_libro_rojo.getText().toString();
        String distribucion_endemismo = et_distribucion_endemismo.getText().toString();
        String ibas = et_ibas.getText().toString();
        String ebas = et_ebas.getText().toString();
        String comentario = et_observaciones.getText().toString();

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

        String uriString = (photoUri != null) ? photoUri.toString() : "";

        SharedPreferences prefs = getActivity().getSharedPreferences("PukuniPrefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        int estacionId = -1;

        Bundle args = getArguments();
        if (args != null) {
            estacionId = args.getInt("estacion_id");
        }

        if(form != null){
            estacionId = form.getEstacion_muestreo_id();
        }

        SimpleDateFormat sdfFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        Date ahora = new Date();

        ContentValues values = new ContentValues();
        values.put("estacion_muestreo_id", estacionId);
        values.put("temporada_evaluacion_id", temporadaEvaluacion != null ? temporadaEvaluacion.getTemporada_evaluacion_id() : null);
        values.put("unidad_vegetacion_id", unidadVegetacion != null ? unidadVegetacion.getUnidad_vegetacion_id() : null);
        values.put("fecha", sdfFecha.format(ahora));
        values.put("hora", sdfHora.format(ahora));
        values.put("clima_id", clima != null ? clima.getClima_id() : null);
        values.put("franja_id", franja != null ? franja.getFranja_id() : null);
        values.put("metodologia_id", metodologia != null ? metodologia.getMetodologia_id() : null);
        values.put("tipo_trampa_id", tipoTrampa != null ? tipoTrampa.getTipo_trampa_id() : null);
        values.put("unidad_muestreal_id", unidadMuestreal != null ? unidadMuestreal.getUnidad_muestreal_id() : null);
        values.put("este", este);
        values.put("norte", norte);
        values.put("altitud", altitud);
        values.put("clase_id", clase != null ? clase.getClase_id() : null);
        values.put("orden_id", orden != null ? orden.getOrden_id() : null);
        values.put("familia_id", familia != null ? familia.getFamilia_id() : null);
        values.put("genero_id", genero != null ? genero.getGenero_id() : null);
        values.put("especie_id", especie != null ? especie.getEspecie_id() : null);
        values.put("nombre_comun", nombre_comun);
        values.put("numero_individuos", individuos);
        values.put("estadio_id", estadio != null ? estadio.getEstadio_id() : null);
        values.put("sexo", sexo);
        values.put("condicion_reproductiva_id", condicionReproductiva != null ? condicionReproductiva.getCondicion_reproductiva_id() : null);
        values.put("categoria_abundancia_id", categoriaAbundancia != null ? categoriaAbundancia.getCategoria_abundancia_id() : null);
        values.put("habito_id", habito != null ? habito.getHabito_id() : null);
        values.put("grupo_trofico_id", grupoTrofico != null ? grupoTrofico.getGrupo_trofico_id() : null);
        values.put("longitud_antebrazo", longitud_antebrazo);
        values.put("longitud_oreja", longitud_oreja);
        values.put("longitud_cola", longitud_cola);
        values.put("longitud_tibia", longitud_tibia);
        values.put("peso", peso);
        values.put("indicador_id", indicador != null ? indicador.getIndicador_id() : null);
        values.put("uicn", uicn);
        values.put("cites", cites);
        values.put("dsn", dsn);
        values.put("libro_rojo", libro_rojo);
        values.put("endemismo", endemismo);
        values.put("distribucion_endemismo", distribucion_endemismo);
        values.put("ibas", ibas);
        values.put("ebas", ebas);
        values.put("comentario", comentario);
        values.put("image_uri", uriString);
        values.put("uso_id", uso != null ? uso.getUsos_id() : null);
        values.put("especialista_id", userId);
//        values.put("proyecto_id", );
        values.put("estado_conservacion_id", estadoConservacion != null ? estadoConservacion.getEstado_conservacion_habitat_id() : null);

        long newRowId;

        if(form != null){
            newRowId = db.update(TABLE_FORMULARIO_QUIROPTEROS, values, " id = ?", new String[]{String.valueOf(form.getId())});

            if (newRowId != -1) {
                Toast.makeText(requireContext(), "Registro actualizado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Error al actualizar el registro", Toast.LENGTH_SHORT).show();
            }
        }else{
            newRowId = db.insert(TABLE_FORMULARIO_QUIROPTEROS, null, values);

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

        if(form != null){
            tv_este.setText("Este (" + form.getEste() + ")");
            tv_norte.setText("Norte (" + form.getNorte() + ")");
            tv_altitud.setText("Altitud (" + form.getAltitud() + ")");
        }else{
            tv_este.setText("Este (" + easting + ")");
            tv_norte.setText("Norte (" + northing + ")");
            tv_altitud.setText("Altitud (" + altitude + ")");
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

    private void setCondicionReproductivaValues(SQLiteDatabase db){
        List<CondicionReproductiva> condicionReproductivaList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_CONDICION_REPRODUCTIVA,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("condicion_reproductiva_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("condicion_reproductiva_name"));

                CondicionReproductiva condicionReproductiva = new CondicionReproductiva();
                condicionReproductiva.setCondicion_reproductiva_id(id);
                condicionReproductiva.setCondicion_reproductiva_name(name);

                condicionReproductivaList.add(condicionReproductiva);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<CondicionReproductiva> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                condicionReproductivaList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_condicion_reproductiva.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < condicionReproductivaList.size(); it++) {
                if (condicionReproductivaList.get(it).getCondicion_reproductiva_id() == form.getCondicion_reproductiva_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_condicion_reproductiva.setSelection(defaultPosition);
            }
        }
    }

    private void setTipoTrampaValues(SQLiteDatabase db){
        List<TipoTrampa> tipoTrampaList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_TIPO_TRAMPA,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_trampa_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("tipo_trampa_name"));

                TipoTrampa tipoTrampa = new TipoTrampa();
                tipoTrampa.setTipo_trampa_id(id);
                tipoTrampa.setTipo_trampa_name(name);

                tipoTrampaList.add(tipoTrampa);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<TipoTrampa> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                tipoTrampaList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_tipo_trampa.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < tipoTrampaList.size(); it++) {
                if (tipoTrampaList.get(it).getTipo_trampa_id() == form.getTipo_trampa_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_tipo_trampa.setSelection(defaultPosition);
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

        spinner_temporada_evaluacion.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < temporadaEvaluacionList.size(); it++) {
                if (temporadaEvaluacionList.get(it).getTemporada_evaluacion_id() == form.getTemporada_evaluacion_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_temporada_evaluacion.setSelection(defaultPosition);
            }
        }
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

        spinner_unidad_vegetacion.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < unidadVegetacionList.size(); it++) {
                if (unidadVegetacionList.get(it).getUnidad_vegetacion_id() == form.getUnidad_vegetacion_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_unidad_vegetacion.setSelection(defaultPosition);
            }
        }
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

        spinner_clima.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < climasList.size(); it++) {
                if (climasList.get(it).getClima_id() == form.getClima_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_clima.setSelection(defaultPosition);
            }
        }
    }

    private void setMetodologiaValues(SQLiteDatabase db){
        List<Metodologia> metodologiasList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_METODOLOGIA + " WHERE tipo_formulario_id = ?",
                new String[]{ String.valueOf(TIPO_FORM_ID) }
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

        spinner_metodologia.setAdapter(adapter);

        spinner_metodologia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<UnidadMuestreal> unidadMuestrealList = new ArrayList<>();

                Franja franja = (Franja) spinner_franja.getSelectedItem();
                Metodologia metodologia = (Metodologia) spinner_metodologia.getSelectedItem();

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

                    spinner_unidad_muestreal.setAdapter(adapter2);

                    if(form != null){
                        int defaultPosition = -1;

                        for (int it = 0; it < unidadMuestrealList.size(); it++) {
                            if (unidadMuestrealList.get(it).getUnidad_muestreal_id() == form.getUnidad_muestreal_id()) {
                                defaultPosition = it;
                                break;
                            }
                        }

                        if(defaultPosition != -1){
                            spinner_unidad_muestreal.setSelection(defaultPosition);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < metodologiasList.size(); it++) {
                if (metodologiasList.get(it).getMetodologia_id() == form.getMetodologia_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_metodologia.setSelection(defaultPosition);
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

                spinner_franja.setAdapter(adapter);

                spinner_franja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        List<UnidadMuestreal> unidadMuestrealList = new ArrayList<>();

                        Franja franja = (Franja) spinner_franja.getSelectedItem();
                        Metodologia metodologia = (Metodologia) spinner_metodologia.getSelectedItem();

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

                            spinner_unidad_muestreal.setAdapter(adapter2);

                            if(form != null){
                                int defaultPosition = -1;

                                for (int it = 0; it < unidadMuestrealList.size(); it++) {
                                    if (unidadMuestrealList.get(it).getUnidad_muestreal_id() == form.getUnidad_muestreal_id()) {
                                        defaultPosition = it;
                                        break;
                                    }
                                }

                                if(defaultPosition != -1){
                                    spinner_unidad_muestreal.setSelection(defaultPosition);
                                }
                            }
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
                        spinner_franja.setSelection(defaultPosition);
                    }
                }
            }
        }
    }

    private void setEstadioValues(SQLiteDatabase db){
        List<Estadio> estadioList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ESTADIO,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("estadio_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("estadio_name"));

                Estadio estadio = new Estadio();
                estadio.setEstadio_id(id);
                estadio.setEstadio_name(name);

                estadioList.add(estadio);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Estadio> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                estadioList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_estadio.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < estadioList.size(); it++) {
                if (estadioList.get(it).getEstadio_id() == form.getEstadio_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_estadio.setSelection(defaultPosition);
            }
        }
    }

    private void setEstadoConservacionValues(SQLiteDatabase db){
        List<EstadoConservacion> estadoConservacionList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ESTADO_CONSERVACION,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("estado_conservacion_habitat_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("estado_conservacion_habitat_name"));

                EstadoConservacion estadoConservacion = new EstadoConservacion();
                estadoConservacion.setEstado_conservacion_habitat_id(id);
                estadoConservacion.setEstado_conservacion_habitat_name(name);

                estadoConservacionList.add(estadoConservacion);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<EstadoConservacion> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                estadoConservacionList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_estado_conservacion_habitat.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < estadoConservacionList.size(); it++) {
                if (estadoConservacionList.get(it).getEstado_conservacion_habitat_id() == form.getEstado_conservacion_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_estado_conservacion_habitat.setSelection(defaultPosition);
            }
        }
    }

    private void setSexoValues(){
        String[] opcionesSexo = {"Indeterminado", "Macho", "Hembra"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                opcionesSexo
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_sexo.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < opcionesSexo.length; it++) {
                if (opcionesSexo[it].equals(form.getSexo())) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_sexo.setSelection(defaultPosition);
            }
        }
    }

    private void setIndicadorValues(SQLiteDatabase db){
        List<Indicador> indicadoresList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_INDICADOR,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("indicador_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("indicador_name"));

                Indicador indicador = new Indicador();
                indicador.setIndicador_id(id);
                indicador.setIndicador_name(name);

                indicadoresList.add(indicador);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Indicador> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                indicadoresList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_indicador.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < indicadoresList.size(); it++) {
                if (indicadoresList.get(it).getIndicador_id() == form.getIndicador_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_indicador.setSelection(defaultPosition);
            }
        }
    }

    private void setCatXAbundanciaValues(SQLiteDatabase db){
        List<CategoriaAbundancia> categoriaAbundanciaList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_CATEGORIA_ABUNDANCIA,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("categoria_abundancia_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("categoria_abundancia_name"));

                CategoriaAbundancia categoriaAbundancia = new CategoriaAbundancia();
                categoriaAbundancia.setCategoria_abundancia_id(id);
                categoriaAbundancia.setCategoria_abundancia_name(name);

                categoriaAbundanciaList.add(categoriaAbundancia);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<CategoriaAbundancia> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                categoriaAbundanciaList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_cat_x_abundancia.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < categoriaAbundanciaList.size(); it++) {
                if (categoriaAbundanciaList.get(it).getCategoria_abundancia_id() == form.getCategoria_abundancia_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_cat_x_abundancia.setSelection(defaultPosition);
            }
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

        spinner_habito.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < habitosList.size(); it++) {
                if (habitosList.get(it).getHabito_id() == form.getHabito_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_habito.setSelection(defaultPosition);
            }
        }
    }

    private void setGruposTroficosValues(SQLiteDatabase db){
        List<GrupoTrofico> grupoTroficoList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_GRUPO_TROFICO,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("grupo_trofico_id"));
                String habito_name = cursor.getString(cursor.getColumnIndexOrThrow("grupo_trofico_name"));

                GrupoTrofico grupoTrofico = new GrupoTrofico();
                grupoTrofico.setGrupo_trofico_id(id);
                grupoTrofico.setGrupo_trofico_name(habito_name);

                grupoTroficoList.add(grupoTrofico);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<GrupoTrofico> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                grupoTroficoList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_grupo_trofico.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < grupoTroficoList.size(); it++) {
                if (grupoTroficoList.get(it).getGrupo_trofico_id() == form.getGrupo_trofico_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_grupo_trofico.setSelection(defaultPosition);
            }
        }
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