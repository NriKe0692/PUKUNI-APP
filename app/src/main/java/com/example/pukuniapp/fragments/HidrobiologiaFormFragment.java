package com.example.pukuniapp.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CATEGORIA_ABUNDANCIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLASE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CLIMA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_CUENCA_HIDROGRAFICA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESPECIE;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FAMILIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_HIDROBIOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_QUIROPTEROS;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_ROEDORES;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_GENERO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_HABITAT;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_HABITO_ALIMENTICIO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_INDICADOR;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_METODOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_ORDEN;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_PUNTO_MUESTREO;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TEMPORADA_EVALUACION;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_TIPO_AMBIENTE_ACUATICO;
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
import com.example.pukuniapp.classes.CuencaHidrografica;
import com.example.pukuniapp.classes.Especie;
import com.example.pukuniapp.classes.Estacion;
import com.example.pukuniapp.classes.Estadio;
import com.example.pukuniapp.classes.EstadoConservacion;
import com.example.pukuniapp.classes.EtapaReproductiva;
import com.example.pukuniapp.classes.Familia;
import com.example.pukuniapp.classes.FormHidrobiologia;
import com.example.pukuniapp.classes.FormRoedor;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.Genero;
import com.example.pukuniapp.classes.GrupoTrofico;
import com.example.pukuniapp.classes.HabitatPeces;
import com.example.pukuniapp.classes.Habito;
import com.example.pukuniapp.classes.Indicador;
import com.example.pukuniapp.classes.Metodologia;
import com.example.pukuniapp.classes.Orden;
import com.example.pukuniapp.classes.PuntoMuestreo;
import com.example.pukuniapp.classes.TemporadaEvaluacion;
import com.example.pukuniapp.classes.TipoAmbienteAcuatico;
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
 * Use the {@link HidrobiologiaFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HidrobiologiaFormFragment extends Fragment {
    private Spinner spinner_cuenca_hidrografica;
    private Spinner spinner_temporada_evaluacion;
    private Spinner spinner_tipo_ambiente_acuatico;
    private Spinner spinner_estacion;
    private Spinner spinner_punto_muestreo;
    private Spinner spinner_pendiente;
    private Spinner spinner_clima;
    private Spinner spinner_tipo_orilla;
    private Spinner spinner_tipo_agua;
    private Spinner spinner_color_aparente_agua;
    private Spinner spinner_metodologia;
    private Spinner spinner_unidad_vegetacion;
    private Spinner spinner_vegetacion_circundante;
    private Spinner spinner_usos;
    private Spinner spinner_cat_x_abundancia;
    private Spinner spinner_indicador;
    private Spinner spinner_habitat_peces;
    private Spinner spinner_etapa_reproductiva;
    private EditText et_localidad;
    private EditText et_este;
    private EditText et_norte;
    private EditText et_altitud;
    private EditText et_ancho_cauce_sector;
    private EditText et_longitud_muestreo;
    private EditText et_ancho_muestreo;
    private EditText et_area_muestreo;
    private EditText et_velocidad_corriente;
    private EditText et_profundidad_maxima_muestreo;
    private EditText et_profundidad_maxima_sector;
    private EditText et_transparencia;
    private EditText et_vegetacion_emergente;
    private EditText et_vegetacion_sumergida;
    private EditText et_vegetacion_flotante;
    private EditText et_habitat_long_caida;
    private EditText et_habitat_long_rifle;
    private EditText et_habitat_long_corridas;
    private EditText et_habitat_long_pozos;
    private EditText et_habitat_long_remanso;
    private EditText et_sustrato_arena;
    private EditText et_sustrato_arcilla;
    private EditText et_sustrato_limo;
    private EditText et_sustrato_grava;
    private EditText et_sustrato_organico_hojarasca;
    private EditText et_sustrato_organico_ramas;
    private EditText et_sustrato_organico_enraizados;
    private EditText et_nombre_comun;
    private EditText et_individuos;
    private EditText et_uicn;
    private EditText et_cites;
    private EditText et_dsn;
    private EditText et_nivel_trofico_fishbase;
    private EditText et_habitos_alimenticios;
    private EditText et_endemismo;
    private EditText et_comportamiento;
    private EditText et_lt_cm;
    private EditText et_peso;
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
    private static int TIPO_FORM_ID = 69;
    FormHidrobiologia form = null;

    public HidrobiologiaFormFragment() {
        // Required empty public constructor
    }

    public static HidrobiologiaFormFragment newInstance(int estacionMuestreoId, int formId) {
        HidrobiologiaFormFragment fragment = new HidrobiologiaFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_hidrobiologia_form, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        if(label != null){
            label.setText("Formulario Hidrobioloía");
        }

        spinner_cuenca_hidrografica = view.findViewById(R.id.spinner_cuenca_hidrografica);
        spinner_temporada_evaluacion = view.findViewById(R.id.spinner_temporada_evaluacion);
        spinner_tipo_ambiente_acuatico = view.findViewById(R.id.spinner_tipo_ambiente_acuatico);
        spinner_estacion = view.findViewById(R.id.spinner_estacion);
        spinner_punto_muestreo = view.findViewById(R.id.spinner_punto_muestreo);
        spinner_pendiente = view.findViewById(R.id.spinner_pendiente);
        spinner_clima = view.findViewById(R.id.spinner_clima);
        spinner_tipo_orilla = view.findViewById(R.id.spinner_tipo_orilla);
        spinner_tipo_agua = view.findViewById(R.id.spinner_tipo_agua);
        spinner_color_aparente_agua = view.findViewById(R.id.spinner_color_aparente_agua);
        spinner_metodologia = view.findViewById(R.id.spinner_metodologia);
        spinner_unidad_vegetacion = view.findViewById(R.id.spinner_unidad_vegetacion);
        spinner_vegetacion_circundante = view.findViewById(R.id.spinner_vegetacion_circundante);
        spinner_usos = view.findViewById(R.id.spinner_usos);
        spinner_cat_x_abundancia = view.findViewById(R.id.spinner_cat_x_abundancia);
        spinner_indicador = view.findViewById(R.id.spinner_indicador);
        spinner_habitat_peces = view.findViewById(R.id.spinner_habitat_peces);
        spinner_etapa_reproductiva = view.findViewById(R.id.spinner_etapa_reproductiva);
        et_localidad = view.findViewById(R.id.et_localidad);
        et_este = view.findViewById(R.id.et_este);
        et_norte = view.findViewById(R.id.et_norte);
        et_altitud = view.findViewById(R.id.et_altitud);
        et_ancho_cauce_sector = view.findViewById(R.id.et_ancho_cauce_sector);
        et_longitud_muestreo = view.findViewById(R.id.et_longitud_muestreo);
        et_ancho_muestreo = view.findViewById(R.id.et_ancho_muestreo);
        et_area_muestreo = view.findViewById(R.id.et_area_muestreo);
        et_velocidad_corriente = view.findViewById(R.id.et_velocidad_corriente);
        et_profundidad_maxima_muestreo = view.findViewById(R.id.et_profundidad_maxima_muestreo);
        et_profundidad_maxima_sector = view.findViewById(R.id.et_profundidad_maxima_sector);
        et_transparencia = view.findViewById(R.id.et_transparencia);
        et_vegetacion_emergente = view.findViewById(R.id.et_vegetacion_emergente);
        et_vegetacion_sumergida = view.findViewById(R.id.et_vegetacion_sumergida);
        et_vegetacion_flotante = view.findViewById(R.id.et_vegetacion_flotante);
        et_habitat_long_caida = view.findViewById(R.id.et_habitat_long_caida);
        et_habitat_long_rifle = view.findViewById(R.id.et_habitat_long_rifle);
        et_habitat_long_corridas = view.findViewById(R.id.et_habitat_long_corridas);
        et_habitat_long_pozos = view.findViewById(R.id.et_habitat_long_pozos);
        et_habitat_long_remanso = view.findViewById(R.id.et_habitat_long_remanso);
        et_sustrato_arena = view.findViewById(R.id.et_sustrato_arena);
        et_sustrato_arcilla = view.findViewById(R.id.et_sustrato_arcilla);
        et_sustrato_limo = view.findViewById(R.id.et_sustrato_limo);
        et_sustrato_grava = view.findViewById(R.id.et_sustrato_grava);
        et_sustrato_organico_hojarasca = view.findViewById(R.id.et_sustrato_organico_hojarasca);
        et_sustrato_organico_ramas = view.findViewById(R.id.et_sustrato_organico_ramas);
        et_sustrato_organico_enraizados = view.findViewById(R.id.et_sustrato_organico_enraizados);
        et_nombre_comun = view.findViewById(R.id.et_nombre_comun);
        et_individuos = view.findViewById(R.id.et_individuos);
        et_uicn = view.findViewById(R.id.et_uicn);
        et_cites = view.findViewById(R.id.et_cites);
        et_dsn = view.findViewById(R.id.et_dsn);
        et_nivel_trofico_fishbase = view.findViewById(R.id.et_nivel_trofico_fishbase);
        et_habitos_alimenticios = view.findViewById(R.id.et_habitos_alimenticios);
        et_endemismo = view.findViewById(R.id.et_endemismo);
        et_comportamiento = view.findViewById(R.id.et_comportamiento);
        et_lt_cm = view.findViewById(R.id.et_lt_cm);
        et_peso = view.findViewById(R.id.et_peso);
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
                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_HIDROBIOLOGIA + " WHERE id = ?", new String[]{String.valueOf(formularioId)});
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                        int especialista_id = cursor.getInt(cursor.getColumnIndexOrThrow("especialista_id"));
                        int proyecto_id = cursor.getInt(cursor.getColumnIndexOrThrow("proyecto_id"));
                        String localidad = cursor.getString(cursor.getColumnIndexOrThrow("localidad"));
                        int cuenca_hidrografica_id = cursor.getInt(cursor.getColumnIndexOrThrow("cuenca_hidrografica_id"));
                        int estacion_muestreo_id = cursor.getInt(cursor.getColumnIndexOrThrow("estacion_muestreo_id"));
                        int temporada_evaluacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("temporada_evaluacion_id"));
                        int tipo_ambiente_acuatico_id = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_ambiente_acuatico_id"));
                        int estacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("estacion_id"));
                        int punto_muestreo_id = cursor.getInt(cursor.getColumnIndexOrThrow("punto_muestreo_id"));
                        float este = cursor.getFloat(cursor.getColumnIndexOrThrow("este"));
                        float norte = cursor.getFloat(cursor.getColumnIndexOrThrow("norte"));
                        float altitud = cursor.getFloat(cursor.getColumnIndexOrThrow("altitud"));
                        String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                        String hora = cursor.getString(cursor.getColumnIndexOrThrow("hora"));
                        String pendiente_cauce = cursor.getString(cursor.getColumnIndexOrThrow("pendiente_cauce"));
                        int clima_id = cursor.getInt(cursor.getColumnIndexOrThrow("clima_id"));
                        float ancho_cauce_sector = cursor.getFloat(cursor.getColumnIndexOrThrow("ancho_cauce_sector"));
                        String tipo_orilla = cursor.getString(cursor.getColumnIndexOrThrow("tipo_orilla"));
                        String tipo_agua = cursor.getString(cursor.getColumnIndexOrThrow("tipo_agua"));
                        String color_aparente_agua = cursor.getString(cursor.getColumnIndexOrThrow("color_aparente_agua"));
                        int metodologia_id = cursor.getInt(cursor.getColumnIndexOrThrow("metodologia_id"));
                        float longitud_muestreo = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_muestreo"));
                        float ancho_muestreo = cursor.getFloat(cursor.getColumnIndexOrThrow("ancho_muestreo"));
                        float area_muestreo = cursor.getFloat(cursor.getColumnIndexOrThrow("area_muestreo"));
                        float velocidad_corriente = cursor.getFloat(cursor.getColumnIndexOrThrow("velocidad_corriente"));
                        float profundidad_maxima_muestreo = cursor.getFloat(cursor.getColumnIndexOrThrow("profundidad_maxima_muestreo"));
                        float profundidad_maxima_sector = cursor.getFloat(cursor.getColumnIndexOrThrow("profundidad_maxima_sector"));
                        float transparencia = cursor.getFloat(cursor.getColumnIndexOrThrow("transparencia"));
                        String vegetacion_emergente = cursor.getString(cursor.getColumnIndexOrThrow("vegetacion_emergente"));
                        String vegetacion_sumergida = cursor.getString(cursor.getColumnIndexOrThrow("vegetacion_sumergida"));
                        String vegetacion_flotante = cursor.getString(cursor.getColumnIndexOrThrow("vegetacion_flotante"));
                        float habitat_porcentaje_long_caida = cursor.getFloat(cursor.getColumnIndexOrThrow("habitat_porcentaje_long_caida"));
                        float habitat_porcentaje_long_rifle = cursor.getFloat(cursor.getColumnIndexOrThrow("habitat_porcentaje_long_rifle"));
                        float habitat_porcentaje_long_corridas = cursor.getFloat(cursor.getColumnIndexOrThrow("habitat_porcentaje_long_corridas"));
                        float habitat_porcentaje_long_pozos = cursor.getFloat(cursor.getColumnIndexOrThrow("habitat_porcentaje_long_pozos"));
                        float habitat_porcentaje_long_remanso = cursor.getFloat(cursor.getColumnIndexOrThrow("habitat_porcentaje_long_remanso"));
                        float sustrato_porcentaje_arena = cursor.getFloat(cursor.getColumnIndexOrThrow("sustrato_porcentaje_arena"));
                        float sustrato_porcentaje_arcilla = cursor.getFloat(cursor.getColumnIndexOrThrow("sustrato_porcentaje_arcilla"));
                        float sustrato_porcentaje_limo = cursor.getFloat(cursor.getColumnIndexOrThrow("sustrato_porcentaje_limo"));
                        float sustrato_porcentaje_grava = cursor.getFloat(cursor.getColumnIndexOrThrow("sustrato_porcentaje_grava"));
                        float sustrato_porcentaje_organico_hojarasca = cursor.getFloat(cursor.getColumnIndexOrThrow("sustrato_porcentaje_organico_hojarasca"));
                        float sustrato_porcentaje_organico_ramas = cursor.getFloat(cursor.getColumnIndexOrThrow("sustrato_porcentaje_organico_ramas"));
                        float sustrato_porcentaje_organico_arbustos_enraizados = cursor.getFloat(cursor.getColumnIndexOrThrow("sustrato_porcentaje_organico_arbustos_enraizados"));
                        int unidad_vegetacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_vegetacion_id"));
                        String vegetacion_circundante = cursor.getString(cursor.getColumnIndexOrThrow("vegetacion_circundante"));
                        int clase_id = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));
                        int orden_id = cursor.getInt(cursor.getColumnIndexOrThrow("orden_id"));
                        int familia_id = cursor.getInt(cursor.getColumnIndexOrThrow("familia_id"));
                        int genero_id = cursor.getInt(cursor.getColumnIndexOrThrow("genero_id"));
                        int especie_id = cursor.getInt(cursor.getColumnIndexOrThrow("especie_id"));
                        String nombre_comun = cursor.getString(cursor.getColumnIndexOrThrow("nombre_comun"));
                        int individuos = cursor.getInt(cursor.getColumnIndexOrThrow("individuos"));
                        String uicn = cursor.getString(cursor.getColumnIndexOrThrow("uicn"));
                        String cites = cursor.getString(cursor.getColumnIndexOrThrow("cites"));
                        String dsn = cursor.getString(cursor.getColumnIndexOrThrow("dsn"));
                        float nivel_trofico_fishbase = cursor.getFloat(cursor.getColumnIndexOrThrow("nivel_trofico_fishbase"));
                        int habito_alimenticio_id = cursor.getInt(cursor.getColumnIndexOrThrow("habito_alimenticio_id"));
                        int uso_id = cursor.getInt(cursor.getColumnIndexOrThrow("uso_id"));
                        int categoria_abundancia_id = cursor.getInt(cursor.getColumnIndexOrThrow("categoria_abundancia_id"));
                        int indicador_id = cursor.getInt(cursor.getColumnIndexOrThrow("indicador_id"));
                        String endemismo = cursor.getString(cursor.getColumnIndexOrThrow("endemismo"));
                        String comportamiento = cursor.getString(cursor.getColumnIndexOrThrow("comportamiento"));
                        float longitud_total = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_total"));
                        float peso = cursor.getFloat(cursor.getColumnIndexOrThrow("peso"));
                        int habitat_id = cursor.getInt(cursor.getColumnIndexOrThrow("habitat_id"));
                        String etapa_reproductiva = cursor.getString(cursor.getColumnIndexOrThrow("etapa_reproductiva"));
                        String comentario = cursor.getString(cursor.getColumnIndexOrThrow("comentario"));
                        String img_uri = cursor.getString(cursor.getColumnIndexOrThrow("img_uri"));

                        form = new FormHidrobiologia();

                        form.setId(id);
                        form.setEspecialista_id(especialista_id);
                        form.setProyecto_id(proyecto_id);
                        form.setLocalidad(localidad);
                        form.setCuenca_hidrografica_id(cuenca_hidrografica_id);
                        form.setEstacion_muestreo_id(estacion_muestreo_id);
                        form.setTemporada_evaluacion_id(temporada_evaluacion_id);
                        form.setTipo_ambiente_acuatico_id(tipo_ambiente_acuatico_id);
                        form.setEstacion_id(estacion_id);
                        form.setPunto_muestreo_id(punto_muestreo_id);
                        form.setEste(este);
                        form.setNorte(norte);
                        form.setAltitud(altitud);
                        form.setFecha(fecha);
                        form.setHora(hora);
                        form.setPendiente_cauce(pendiente_cauce);
                        form.setClima_id(clima_id);
                        form.setAncho_cauce_sector(ancho_cauce_sector);
                        form.setTipo_orilla(tipo_orilla);
                        form.setTipo_agua(tipo_agua);
                        form.setColor_aparente_agua(color_aparente_agua);
                        form.setMetodologia_id(metodologia_id);
                        form.setLongitud_muestreo(longitud_muestreo);
                        form.setAncho_muestreo(ancho_muestreo);
                        form.setArea_muestreo(area_muestreo);
                        form.setVelocidad_corriente(velocidad_corriente);
                        form.setProfundidad_maxima_muestreo(profundidad_maxima_muestreo);
                        form.setProfundidad_maxima_sector(profundidad_maxima_sector);
                        form.setTransparencia(transparencia);
                        form.setVegetacion_emergente(vegetacion_emergente);
                        form.setVegetacion_sumergida(vegetacion_sumergida);
                        form.setVegetacion_flotante(vegetacion_flotante);
                        form.setHabitat_porcentaje_long_caida(habitat_porcentaje_long_caida);
                        form.setHabitat_porcentaje_long_rifle(habitat_porcentaje_long_rifle);
                        form.setHabitat_porcentaje_long_corridas(habitat_porcentaje_long_corridas);
                        form.setHabitat_porcentaje_long_pozos(habitat_porcentaje_long_pozos);
                        form.setHabitat_porcentaje_long_remanso(habitat_porcentaje_long_remanso);
                        form.setSustrato_porcentaje_arena(sustrato_porcentaje_arena);
                        form.setSustrato_porcentaje_arcilla(sustrato_porcentaje_arcilla);
                        form.setSustrato_porcentaje_limo(sustrato_porcentaje_limo);
                        form.setSustrato_porcentaje_grava(sustrato_porcentaje_grava);
                        form.setSustrato_porcentaje_organico_hojarasca(sustrato_porcentaje_organico_hojarasca);
                        form.setSustrato_porcentaje_organico_ramas(sustrato_porcentaje_organico_ramas);
                        form.setSustrato_porcentaje_organico_arbustos_enraizados(sustrato_porcentaje_organico_arbustos_enraizados);
                        form.setUnidad_vegetacion_id(unidad_vegetacion_id);
                        form.setVegetacion_circundante(vegetacion_circundante);
                        form.setClase_id(clase_id);
                        form.setOrden_id(orden_id);
                        form.setFamilia_id(familia_id);
                        form.setGenero_id(genero_id);
                        form.setEspecie_id(especie_id);
                        form.setNombre_comun(nombre_comun);
                        form.setIndividuos(individuos);
                        form.setUicn(uicn);
                        form.setCites(cites);
                        form.setDsn(dsn);
                        form.setNivel_trofico_fishbase(nivel_trofico_fishbase);
                        form.setHabito_alimenticio_id(habito_alimenticio_id);
                        form.setUso_id(uso_id);
                        form.setCategoria_abundancia_id(categoria_abundancia_id);
                        form.setIndicador_id(indicador_id);
                        form.setEndemismo(endemismo);
                        form.setComportamiento(comportamiento);
                        form.setLongitud_total(longitud_total);
                        form.setPeso(peso);
                        form.setHabitat_id(habitat_id);
                        form.setEtapa_reproductiva(etapa_reproductiva);
                        form.setComentario(comentario);
                        form.setImg_uri(img_uri);

                    } while (cursor.moveToNext());
                }
            }
        }

        setCuencaHidrograficaValues(db);
        setTemporadaEvaluacionValues(db);
        setClimaValues(db);
        setMetodologiaValues(db);
        setUnidadDeVegetacionValues(db);
        setUsosValues(db);
        setCategoriaXAbundanciaValues(db);
        setIndicadorValues(db);
        setTipoAmbienteAcuaticoValues(db);
        setEstacionValues(db);
        setPuntoMuestreoValues(db);
        setHabitatPecesValues(db);
        setEtapaReproductivaValues();
        setPendienteValues();
        setTipoOrillaValues();
        setTipoAguaValues();
        setColorAparenteAguaValues();
        setVegetacionCircundanteValues();

        setupAutocompleteTv(db);
        getCoordinates();

        if(form != null){
            et_localidad.setText(String.valueOf(form.getLocalidad()));
            et_este.setText(String.valueOf(form.getEste()));
            et_norte.setText(String.valueOf(form.getNorte()));
            et_altitud.setText(String.valueOf(form.getAltitud()));
            et_ancho_cauce_sector.setText(String.valueOf(form.getAncho_cauce_sector()));
            et_longitud_muestreo.setText(String.valueOf(form.getLongitud_muestreo()));
            et_ancho_muestreo.setText(String.valueOf(form.getAncho_muestreo()));
            et_area_muestreo.setText(String.valueOf(form.getArea_muestreo()));
            et_velocidad_corriente.setText(String.valueOf(form.getVelocidad_corriente()));
            et_profundidad_maxima_muestreo.setText(String.valueOf(form.getProfundidad_maxima_muestreo()));
            et_profundidad_maxima_sector.setText(String.valueOf(form.getProfundidad_maxima_sector()));
            et_transparencia.setText(String.valueOf(form.getTransparencia()));
            et_vegetacion_emergente.setText(String.valueOf(form.getVegetacion_emergente()));
            et_vegetacion_sumergida.setText(String.valueOf(form.getVegetacion_sumergida()));
            et_vegetacion_flotante.setText(String.valueOf(form.getVegetacion_flotante()));
            et_habitat_long_caida.setText(String.valueOf(form.getHabitat_porcentaje_long_caida()));
            et_habitat_long_rifle.setText(String.valueOf(form.getHabitat_porcentaje_long_rifle()));
            et_habitat_long_corridas.setText(String.valueOf(form.getHabitat_porcentaje_long_corridas()));
            et_habitat_long_pozos.setText(String.valueOf(form.getHabitat_porcentaje_long_pozos()));
            et_habitat_long_remanso.setText(String.valueOf(form.getHabitat_porcentaje_long_remanso()));
            et_sustrato_arena.setText(String.valueOf(form.getSustrato_porcentaje_arena()));
            et_sustrato_arcilla.setText(String.valueOf(form.getSustrato_porcentaje_arcilla()));
            et_sustrato_limo.setText(String.valueOf(form.getSustrato_porcentaje_limo()));
            et_sustrato_grava.setText(String.valueOf(form.getSustrato_porcentaje_grava()));
            et_sustrato_organico_hojarasca.setText(String.valueOf(form.getSustrato_porcentaje_organico_hojarasca()));
            et_sustrato_organico_ramas.setText(String.valueOf(form.getSustrato_porcentaje_organico_ramas()));
            et_sustrato_organico_enraizados.setText(String.valueOf(form.getSustrato_porcentaje_organico_arbustos_enraizados()));
            et_nombre_comun.setText(String.valueOf(form.getNombre_comun()));
            et_individuos.setText(String.valueOf(form.getIndividuos()));
            et_uicn.setText(String.valueOf(form.getUicn()));
            et_cites.setText(String.valueOf(form.getCites()));
            et_dsn.setText(String.valueOf(form.getDsn()));
            et_nivel_trofico_fishbase.setText(String.valueOf(form.getNivel_trofico_fishbase()));
            et_habitos_alimenticios.setText(String.valueOf(form.getHabito_alimenticio_id()));
            et_endemismo.setText(String.valueOf(form.getEndemismo()));
            et_comportamiento.setText(String.valueOf(form.getComportamiento()));
            et_lt_cm.setText(String.valueOf(form.getLongitud_total()));
            et_peso.setText(String.valueOf(form.getPeso()));
            et_observaciones.setText(String.valueOf(form.getComentario()));
            img_preview.setImageURI(Uri.parse(form.getImg_uri()));
        }

        save_form.setOnClickListener(v -> {
            saveForm(db);
        });

        return view;
    }

    private void saveForm(SQLiteDatabase db){
        // Recoger valores de los spinners
        CuencaHidrografica cuencaHidrografica = (CuencaHidrografica) spinner_cuenca_hidrografica.getSelectedItem();
        TemporadaEvaluacion temporadaEvaluacion = (TemporadaEvaluacion) spinner_temporada_evaluacion.getSelectedItem();
        TipoAmbienteAcuatico tipoAmbienteAcuatico = (TipoAmbienteAcuatico) spinner_tipo_ambiente_acuatico.getSelectedItem();
        Estacion estacion = (Estacion) spinner_estacion.getSelectedItem();
        PuntoMuestreo puntoMuestreo = (PuntoMuestreo) spinner_punto_muestreo.getSelectedItem();
        String pendiente_cauce = (String) spinner_pendiente.getSelectedItem();
        Clima clima = (Clima) spinner_clima.getSelectedItem();
        String tipo_orilla = (String) spinner_tipo_orilla.getSelectedItem();
        String tipo_agua = (String) spinner_tipo_agua.getSelectedItem();
        String color_aparente_agua = (String) spinner_color_aparente_agua.getSelectedItem();
        Metodologia metodologia = (Metodologia) spinner_metodologia.getSelectedItem();
        UnidadVegetacion unidadVegetacion = (UnidadVegetacion) spinner_unidad_vegetacion.getSelectedItem();
        String vegetacion_circundante = (String) spinner_vegetacion_circundante.getSelectedItem();
        TipoUsos uso = (TipoUsos) spinner_usos.getSelectedItem();
        CategoriaAbundancia categoriaAbundancia = (CategoriaAbundancia) spinner_cat_x_abundancia.getSelectedItem();
        Indicador indicador = (Indicador) spinner_indicador.getSelectedItem();
        HabitatPeces habitat = (HabitatPeces) spinner_habitat_peces.getSelectedItem();
        String etapaReproductiva = (String) spinner_etapa_reproductiva.getSelectedItem();

        // Recoger valores de los campos de texto
        String localidad = et_localidad.getText().toString();
        String este = et_este.getText().toString();
        String norte = et_norte.getText().toString();
        String altitud = et_altitud.getText().toString();
        String ancho_cauce_sector = et_ancho_cauce_sector.getText().toString();
        String longitud_muestreo = et_longitud_muestreo.getText().toString();
        String ancho_muestreo = et_ancho_muestreo.getText().toString();
        String area_muestreo = et_area_muestreo.getText().toString();
        String velocidad_corriente = et_velocidad_corriente.getText().toString();
        String profundidad_maxima_muestreo = et_profundidad_maxima_muestreo.getText().toString();
        String profundidad_maxima_sector = et_profundidad_maxima_sector.getText().toString();
        String transparencia = et_transparencia.getText().toString();
        String vegetacion_emergente = et_vegetacion_emergente.getText().toString();
        String vegetacion_sumergida = et_vegetacion_sumergida.getText().toString();
        String vegetacion_flotante = et_vegetacion_flotante.getText().toString();
        String habitat_long_caida = et_habitat_long_caida.getText().toString();
        String habitat_long_rifle = et_habitat_long_rifle.getText().toString();
        String habitat_long_corridas = et_habitat_long_corridas.getText().toString();
        String habitat_long_pozos = et_habitat_long_pozos.getText().toString();
        String habitat_long_remanso = et_habitat_long_remanso.getText().toString();
        String sustrato_arena = et_sustrato_arena.getText().toString();
        String sustrato_arcilla = et_sustrato_arcilla.getText().toString();
        String sustrato_limo = et_sustrato_limo.getText().toString();
        String sustrato_grava = et_sustrato_grava.getText().toString();
        String sustrato_organico_hojarasca = et_sustrato_organico_hojarasca.getText().toString();
        String sustrato_organico_ramas = et_sustrato_organico_ramas.getText().toString();
        String sustrato_organico_enraizados = et_sustrato_organico_enraizados.getText().toString();
        String nombre_comun = et_nombre_comun.getText().toString();
        String individuos = et_individuos.getText().toString();
        String uicn = et_uicn.getText().toString();
        String cites = et_cites.getText().toString();
        String dsn = et_dsn.getText().toString();
        String nivel_trofico_fishbase = et_nivel_trofico_fishbase.getText().toString();
        String habitos_alimenticios = et_habitos_alimenticios.getText().toString();
        String endemismo = et_endemismo.getText().toString();
        String comportamiento = et_comportamiento.getText().toString();
        String lt_cm = et_lt_cm.getText().toString();
        String peso = et_peso.getText().toString();
        String observaciones = et_observaciones.getText().toString();
        String uriString = (photoUri != null) ? photoUri.toString() : "";

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
        values.put("especialista_id", userId);
//        values.put("proyecto_id", proyecto_id);
        values.put("localidad", localidad);
        values.put("cuenca_hidrografica_id", cuencaHidrografica != null ? cuencaHidrografica.getCuenca_hidrografica_id() : null);
        values.put("estacion_muestreo_id", estacionId);
        values.put("temporada_evaluacion_id", temporadaEvaluacion != null ? temporadaEvaluacion.getTemporada_evaluacion_id() : null);
        values.put("tipo_ambiente_acuatico_id", tipoAmbienteAcuatico != null ? tipoAmbienteAcuatico.getTipo_ambiente_acuatico_id() : null);
        values.put("estacion_id", estacion != null ? estacion.getEstacion_id() : null);
        values.put("punto_muestreo_id", puntoMuestreo != null ? puntoMuestreo.getPunto_muestreo_id(): null);
        values.put("este", este);
        values.put("norte", norte);
        values.put("altitud", altitud);
        values.put("fecha", sdfFecha.format(ahora));
        values.put("hora", sdfHora.format(ahora));
        values.put("pendiente_cauce", pendiente_cauce);
        values.put("clima_id", clima != null ? clima.getClima_id() : null);
        values.put("ancho_cauce_sector", ancho_cauce_sector);
        values.put("tipo_orilla", tipo_orilla);
        values.put("tipo_agua", tipo_agua);
        values.put("color_aparente_agua", color_aparente_agua);
        values.put("metodologia_id", metodologia != null ? metodologia.getMetodologia_id() : null);
        values.put("longitud_muestreo", longitud_muestreo);
        values.put("ancho_muestreo", ancho_muestreo);
        values.put("area_muestreo", area_muestreo);
        values.put("velocidad_corriente", velocidad_corriente);
        values.put("profundidad_maxima_muestreo", profundidad_maxima_muestreo);
        values.put("profundidad_maxima_sector", profundidad_maxima_sector);
        values.put("transparencia", transparencia);
        values.put("vegetacion_emergente", vegetacion_emergente);
        values.put("vegetacion_sumergida", vegetacion_sumergida);
        values.put("vegetacion_flotante", vegetacion_flotante);
        values.put("habitat_porcentaje_long_caida", habitat_long_caida);
        values.put("habitat_porcentaje_long_rifle", habitat_long_rifle);
        values.put("habitat_porcentaje_long_corridas", habitat_long_corridas);
        values.put("habitat_porcentaje_long_pozos", habitat_long_pozos);
        values.put("habitat_porcentaje_long_remanso", habitat_long_remanso);
        values.put("sustrato_porcentaje_arena", sustrato_arena);
        values.put("sustrato_porcentaje_arcilla", sustrato_arcilla);
        values.put("sustrato_porcentaje_limo", sustrato_limo);
        values.put("sustrato_porcentaje_grava", sustrato_grava);
        values.put("sustrato_porcentaje_organico_hojarasca", sustrato_organico_hojarasca);
        values.put("sustrato_porcentaje_organico_ramas", sustrato_organico_ramas);
        values.put("sustrato_porcentaje_organico_arbustos_enraizados", sustrato_organico_enraizados);
        values.put("unidad_vegetacion_id", unidadVegetacion != null ? unidadVegetacion.getUnidad_vegetacion_id() : null);
        values.put("vegetacion_circundante", vegetacion_circundante);
        values.put("clase_id", clase != null ? clase.getClase_id() : null);
        values.put("orden_id", orden != null ? orden.getOrden_id() : null);
        values.put("familia_id", familia != null ? familia.getFamilia_id() : null);
        values.put("genero_id", genero != null ? genero.getGenero_id() : null);
        values.put("especie_id", especie != null ? especie.getEspecie_id() : null);
        values.put("nombre_comun", nombre_comun);
        values.put("individuos", individuos);
        values.put("uicn", uicn);
        values.put("cites", cites);
        values.put("dsn", dsn);
        values.put("nivel_trofico_fishbase", nivel_trofico_fishbase);
        values.put("habito_alimenticio_id", habitos_alimenticios);
        values.put("uso_id", uso != null ? uso.getUsos_id() : null);
        values.put("categoria_abundancia_id", categoriaAbundancia != null ? categoriaAbundancia.getCategoria_abundancia_id() : null);
        values.put("indicador_id", indicador != null ? indicador.getIndicador_id() : null);
        values.put("endemismo", endemismo);
        values.put("comportamiento", comportamiento);
        values.put("longitud_total", lt_cm);
        values.put("peso", peso);
        values.put("habitat_id", habitat != null ? habitat.getHabitat_peces_id() : null);
        values.put("etapa_reproductiva", etapaReproductiva);
        values.put("comentario", observaciones);
        values.put("img_uri", uriString);

        long newRowId;

        if(form != null){
            newRowId = db.update(TABLE_FORMULARIO_HIDROBIOLOGIA, values, " id = ?", new String[]{String.valueOf(form.getId())});

            if (newRowId != -1) {
                Toast.makeText(requireContext(), "Registro actualizado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Error al actualizar el registro", Toast.LENGTH_SHORT).show();
            }
        }else{
            newRowId = db.insert(TABLE_FORMULARIO_HIDROBIOLOGIA, null, values);

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

    private void setCuencaHidrograficaValues(SQLiteDatabase db){
        List<CuencaHidrografica> cuencaHidrograficaList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_CUENCA_HIDROGRAFICA,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("cuenta_hidrografica_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("cuenta_hidrografica_name"));

                CuencaHidrografica cuencaHidrografica = new CuencaHidrografica();
                cuencaHidrografica.setCuenca_hidrografica_id(id);
                cuencaHidrografica.setCuenca_hidrografica_name(name);

                cuencaHidrograficaList.add(cuencaHidrografica);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<CuencaHidrografica> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                cuencaHidrograficaList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_cuenca_hidrografica.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < cuencaHidrograficaList.size(); it++) {
                if (cuencaHidrograficaList.get(it).getCuenca_hidrografica_id() == form.getCuenca_hidrografica_id()) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_cuenca_hidrografica.setSelection(defaultPosition);
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

    private void setVegetacionCircundanteValues(){
        String[] opcionesVegetacionCircundante = {"Arbórea", "Arbórea, Arbustiva"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                opcionesVegetacionCircundante
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_vegetacion_circundante.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < opcionesVegetacionCircundante.length; it++) {
                if (opcionesVegetacionCircundante[it].equals(form.getVegetacion_circundante())) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_vegetacion_circundante.setSelection(defaultPosition);
            }
        }
    }

    private void setColorAparenteAguaValues(){
        String[] opcionesColorAparenteAgua = {"Marrón Claro", "Verde Oscuro"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                opcionesColorAparenteAgua
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_color_aparente_agua.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < opcionesColorAparenteAgua.length; it++) {
                if (opcionesColorAparenteAgua[it].equals(form.getColor_aparente_agua())) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_color_aparente_agua.setSelection(defaultPosition);
            }
        }
    }

    private void setTipoAguaValues(){
        String[] opcionesTipoAgua = {"Blanca", "Clara"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                opcionesTipoAgua
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_tipo_agua.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < opcionesTipoAgua.length; it++) {
                if (opcionesTipoAgua[it].equals(form.getTipo_agua())) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_tipo_agua.setSelection(defaultPosition);
            }
        }
    }

    private void setPendienteValues(){
        String[] opcionesPendiente = {"Nula", "Ligera", "Pronunciada"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                opcionesPendiente
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_pendiente.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < opcionesPendiente.length; it++) {
                if (opcionesPendiente[it].equals(form.getPendiente_cauce())) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_pendiente.setSelection(defaultPosition);
            }
        }
    }

    private void setTipoOrillaValues(){
        String[] opcionesTipoOrilla = {"Amplia", "Semi Amplia", "Estrecha", "Muy Estrecha"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                opcionesTipoOrilla
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_tipo_orilla.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < opcionesTipoOrilla.length; it++) {
                if (opcionesTipoOrilla[it].equals(form.getTipo_orilla())) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_tipo_orilla.setSelection(defaultPosition);
            }
        }
    }

    private void setPuntoMuestreoValues(SQLiteDatabase db){
        List<PuntoMuestreo> puntoMuestreoList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_PUNTO_MUESTREO ,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("punto_muestreo_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("punto_muestreo_name"));

                PuntoMuestreo puntoMuestreo = new PuntoMuestreo();
                puntoMuestreo.setPunto_muestreo_id(id);
                puntoMuestreo.setPunto_muestreo_name(name);

                puntoMuestreoList.add(puntoMuestreo);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<PuntoMuestreo> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                puntoMuestreoList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_punto_muestreo.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < puntoMuestreoList.size(); i++) {
                if (puntoMuestreoList.get(i).getPunto_muestreo_id() == form.getPunto_muestreo_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_punto_muestreo.setSelection(defaultPosition);
            }
        }
    }

    private void setEstacionValues(SQLiteDatabase db){
        List<Estacion> estacionList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_ESTACION ,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("estacion_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("estacion_name"));

                Estacion estacion = new Estacion();
                estacion.setEstacion_id(id);
                estacion.setEstacion_name(name);

                estacionList.add(estacion);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Estacion> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                estacionList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_estacion.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < estacionList.size(); i++) {
                if (estacionList.get(i).getEstacion_id() == form.getEstacion_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_estacion.setSelection(defaultPosition);
            }
        }
    }

    private void setEtapaReproductivaValues(){
        String[] opcionesEtapaReproductiva = {"Etapa 1", "Etapa 2"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                opcionesEtapaReproductiva
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_etapa_reproductiva.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int it = 0; it < opcionesEtapaReproductiva.length; it++) {
                if (opcionesEtapaReproductiva[it].equals(form.getEtapa_reproductiva())) {
                    defaultPosition = it;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_etapa_reproductiva.setSelection(defaultPosition);
            }
        }
    }

    private void setHabitatPecesValues(SQLiteDatabase db){
        List<HabitatPeces> habitatPecesList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_HABITAT,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("habitat_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("habitat_name"));

                HabitatPeces habitatPeces = new HabitatPeces();
                habitatPeces.setHabitat_peces_id(id);
                habitatPeces.setHabitat_peces_name(name);

                habitatPecesList.add(habitatPeces);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<HabitatPeces> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                habitatPecesList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_habitat_peces.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < habitatPecesList.size(); i++) {
                if (habitatPecesList.get(i).getHabitat_peces_id() == form.getHabitat_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_habitat_peces.setSelection(defaultPosition);
            }
        }
    }

    private void setIndicadorValues(SQLiteDatabase db){
        List<Indicador> indicadorList = new ArrayList<>();

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

                indicadorList.add(indicador);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<Indicador> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                indicadorList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_indicador.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < indicadorList.size(); i++) {
                if (indicadorList.get(i).getIndicador_id() == form.getIndicador_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_indicador.setSelection(defaultPosition);
            }
        }
    }

    private void setCategoriaXAbundanciaValues(SQLiteDatabase db){
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

            for (int i = 0; i < categoriaAbundanciaList.size(); i++) {
                if (categoriaAbundanciaList.get(i).getCategoria_abundancia_id() == form.getCategoria_abundancia_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_cat_x_abundancia.setSelection(defaultPosition);
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

    private void setTipoAmbienteAcuaticoValues(SQLiteDatabase db){
        List<TipoAmbienteAcuatico> tipoAmbienteAcuaticoList = new ArrayList<>();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_TIPO_AMBIENTE_ACUATICO,
            null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_ambiente_acuatico_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("tipo_ambiente_acuatico_name"));

                TipoAmbienteAcuatico tipoAmbienteAcuatico = new TipoAmbienteAcuatico();
                tipoAmbienteAcuatico.setTipo_ambiente_acuatico_id(id);
                tipoAmbienteAcuatico.setTipo_ambiente_acuatico_name(name);

                tipoAmbienteAcuaticoList.add(tipoAmbienteAcuatico);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<TipoAmbienteAcuatico> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                tipoAmbienteAcuaticoList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_tipo_ambiente_acuatico.setAdapter(adapter);

        if(form != null){
            int defaultPosition = -1;

            for (int i = 0; i < tipoAmbienteAcuaticoList.size(); i++) {
                if (tipoAmbienteAcuaticoList.get(i).getTipo_ambiente_acuatico_id() == form.getTipo_ambiente_acuatico_id()) {
                    defaultPosition = i;
                    break;
                }
            }

            if(defaultPosition != -1){
                spinner_tipo_ambiente_acuatico.setSelection(defaultPosition);
            }
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