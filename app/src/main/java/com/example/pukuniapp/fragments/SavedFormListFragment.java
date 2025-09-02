package com.example.pukuniapp.fragments;

import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_FLORA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_HERPETOLOGIA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_ORNITOFAUNA;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_QUIROPTEROS;
import static com.example.pukuniapp.helpers.DBHelper.TABLE_FORMULARIO_ROEDORES;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pukuniapp.R;
import com.example.pukuniapp.adapters.CustomAdapter;
import com.example.pukuniapp.classes.FormFlora;
import com.example.pukuniapp.classes.FormHerpetologia;
import com.example.pukuniapp.classes.FormOrnitofauna;
import com.example.pukuniapp.classes.FormQuiroptero;
import com.example.pukuniapp.classes.FormRoedor;
import com.example.pukuniapp.classes.TipoUsos;
import com.example.pukuniapp.helpers.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedFormListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedFormListFragment extends Fragment {
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    List<FormFlora> formFloraList;
    List<FormOrnitofauna> formOrnitofaunaList;
    List<FormQuiroptero> quiropterosList;
    List<FormRoedor> roedoresList;
    List<FormHerpetologia> herpetologiaList;

    public SavedFormListFragment() {
        // Required empty public constructor
    }
    public static SavedFormListFragment newInstance(String param1, String param2) {
        SavedFormListFragment fragment = new SavedFormListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_form_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerFlora);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        formFloraList = getFloraListFromDB(db);
        formOrnitofaunaList = getOrnitofaunaListFromDB(db);
        quiropterosList = getQuiropterosListFromDB(db);
        roedoresList = getRoedoresListFromDB(db);
        herpetologiaList = getHerpetologiaListFromDB(db);
        db.close();

        customAdapter = new CustomAdapter(formFloraList, formOrnitofaunaList, quiropterosList, roedoresList, herpetologiaList);

        customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onFloraClick(FormFlora flora, int position) {
                FloraFormFragment newFragment = FloraFormFragment.newInstance(flora.getEstacion_muestreo_id(), flora.getId());

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, newFragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onOrnitofaunaClick(FormOrnitofauna ornito, int position) {
                Log.d("Ornito", ornito.toString());
                OrnitoFaunaFragment newFragment = OrnitoFaunaFragment.newInstance(ornito.getEstacion_muestreo_id(), ornito.getId());

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, newFragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onQuiropteroClick(FormQuiroptero quiroptero, int position) {
                Log.d("Quiroptero", quiroptero.toString());
                QuiropterosFragment newFragment = QuiropterosFragment.newInstance(quiroptero.getEstacion_muestreo_id(), quiroptero.getId());

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, newFragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onRoedorClick(FormRoedor roedor, int position) {
                Log.d("Roedor", roedor.toString());
                RoedoresFragment newFragment = RoedoresFragment.newInstance(roedor.getEstacion_muestreo_id(), roedor.getId());

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, newFragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onHerpetologiaClick(FormHerpetologia herpetologia, int position) {
                Log.d("Herpetologia", herpetologia.toString());
                HerpetologiaFragment newFragment = HerpetologiaFragment.newInstance(herpetologia.getEstacion_muestreo_id(), herpetologia.getId());

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, newFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        recyclerView.setAdapter(customAdapter);

        return view;
    }

    private List<FormFlora> getFloraListFromDB(SQLiteDatabase db) {
        List<FormFlora> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_FLORA, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int estacion_muestreo_id = cursor.getInt(cursor.getColumnIndexOrThrow("estacion_muestreo_id"));
                int lugar_id = cursor.getInt(cursor.getColumnIndexOrThrow("lugar_id"));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                int franja_id = cursor.getInt(cursor.getColumnIndexOrThrow("franja_id"));
                int unidad_muestreo_id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_muestreo_id"));
                int parcela_id = cursor.getInt(cursor.getColumnIndexOrThrow("parcela_id"));
                int forofito_id = cursor.getInt(cursor.getColumnIndexOrThrow("forofito_id"));
                int sub_parcela_id = cursor.getInt(cursor.getColumnIndexOrThrow("sub_parcela_id"));
                String tamanio = cursor.getString(cursor.getColumnIndexOrThrow("tamanio"));
                String codigo_placa = cursor.getString(cursor.getColumnIndexOrThrow("codigo_placa"));
                int unidad_vegetacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_vegetacion_id"));
                int pais_id = cursor.getInt(cursor.getColumnIndexOrThrow("pais_id"));
                int departamento_id = cursor.getInt(cursor.getColumnIndexOrThrow("departamento_id"));
                int provincia_id = cursor.getInt(cursor.getColumnIndexOrThrow("provincia_id"));
                int distrito_id = cursor.getInt(cursor.getColumnIndexOrThrow("distrito_id"));
                String localidad = cursor.getString(cursor.getColumnIndexOrThrow("localidad"));
                double este = cursor.getDouble(cursor.getColumnIndexOrThrow("este"));
                double norte = cursor.getDouble(cursor.getColumnIndexOrThrow("norte"));
                double altitud = cursor.getDouble(cursor.getColumnIndexOrThrow("altitud"));
                int clase_id = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));
                int orden_id = cursor.getInt(cursor.getColumnIndexOrThrow("orden_id"));
                int familia_id = cursor.getInt(cursor.getColumnIndexOrThrow("familia_id"));
                int genero_id = cursor.getInt(cursor.getColumnIndexOrThrow("genero_id"));
                int especie_id = cursor.getInt(cursor.getColumnIndexOrThrow("especie_id"));
                String nombre_comun = cursor.getString(cursor.getColumnIndexOrThrow("nombre_comun"));
                int autor_id = cursor.getInt(cursor.getColumnIndexOrThrow("autor_id"));
                int individuos = cursor.getInt(cursor.getColumnIndexOrThrow("individuos"));
                double dap = cursor.getDouble(cursor.getColumnIndexOrThrow("dap"));
                double altura = cursor.getDouble(cursor.getColumnIndexOrThrow("altura"));
                double valor_cobertura = cursor.getDouble(cursor.getColumnIndexOrThrow("valor_cobertura"));
                int habito_id = cursor.getInt(cursor.getColumnIndexOrThrow("habito_id"));
                int estadio_id = cursor.getInt(cursor.getColumnIndexOrThrow("estadio_id"));
                int fenologia_id = cursor.getInt(cursor.getColumnIndexOrThrow("fenologia_id"));
                int uso_id = cursor.getInt(cursor.getColumnIndexOrThrow("uso_id"));
                String image_uri = cursor.getString(cursor.getColumnIndexOrThrow("image_uri"));
                String observaciones = cursor.getString(cursor.getColumnIndexOrThrow("observaciones"));
                String datos_planta = cursor.getString(cursor.getColumnIndexOrThrow("datos_planta"));

                FormFlora formFloraTemp = new FormFlora();
                formFloraTemp.setId(id);
                formFloraTemp.setEstacion_muestreo_id(estacion_muestreo_id);
                formFloraTemp.setLugar_id(lugar_id);
                formFloraTemp.setFecha(fecha);
                formFloraTemp.setFranja_id(franja_id);
                formFloraTemp.setUnidad_muestreo_id(unidad_muestreo_id);
                formFloraTemp.setParcela_id(parcela_id);
                formFloraTemp.setForofito_id(forofito_id);
                formFloraTemp.setSub_parcela_id(sub_parcela_id);
                formFloraTemp.setTamanio(tamanio);
                formFloraTemp.setCodigo_placa(codigo_placa);
                formFloraTemp.setUnidad_vegetacion_id(unidad_vegetacion_id);
                formFloraTemp.setPais_id(pais_id);
                formFloraTemp.setDepartamento_id(departamento_id);
                formFloraTemp.setProvincia_id(provincia_id);
                formFloraTemp.setDistrito_id(distrito_id);
                formFloraTemp.setLocalidad(localidad);
                formFloraTemp.setEste(este);
                formFloraTemp.setNorte(norte);
                formFloraTemp.setAltitud(altitud);
                formFloraTemp.setClase_id(clase_id);
                formFloraTemp.setOrden_id(orden_id);
                formFloraTemp.setFamilia_id(familia_id);
                formFloraTemp.setGenero_id(genero_id);
                formFloraTemp.setEspecie_id(especie_id);
                formFloraTemp.setNombre_comun(nombre_comun);
                formFloraTemp.setAutor_id(autor_id);
                formFloraTemp.setIndividuos(individuos);
                formFloraTemp.setDap(dap);
                formFloraTemp.setAltura(altura);
                formFloraTemp.setValor_cobertura(valor_cobertura);
                formFloraTemp.setHabito_id(habito_id);
                formFloraTemp.setEstadio_id(estadio_id);
                formFloraTemp.setFenologia_id(fenologia_id);
                formFloraTemp.setUso_id(uso_id);
                formFloraTemp.setImageUri(image_uri);
                formFloraTemp.setObservaciones(observaciones);
                formFloraTemp.setDatosPlanta(datos_planta);

                list.add(formFloraTemp);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }

    private List<FormRoedor> getRoedoresListFromDB(SQLiteDatabase db){
        List<FormRoedor> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_ROEDORES, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int proyecto_id = cursor.getInt(cursor.getColumnIndexOrThrow("proyecto_id"));
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
                float longitud_cuerpo = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_cuerpo"));
                float longitud_oreja = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_oreja"));
                float longitud_cola = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_cola"));
                float longitud_pata = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_pata"));
                int indicador_id = cursor.getInt(cursor.getColumnIndexOrThrow("indicador_id"));
                String uicn = cursor.getString(cursor.getColumnIndexOrThrow("uicn"));
                String cites = cursor.getString(cursor.getColumnIndexOrThrow("cites"));
                String dsn = cursor.getString(cursor.getColumnIndexOrThrow("dsn"));
                String libro_rojo = cursor.getString(cursor.getColumnIndexOrThrow("libro_rojo"));
                String endemismo = cursor.getString(cursor.getColumnIndexOrThrow("endemismo"));
                String distribucion_endemismo = cursor.getString(cursor.getColumnIndexOrThrow("distribucion_endemismo"));
                String comentario = cursor.getString(cursor.getColumnIndexOrThrow("comentario"));
                String image_uri = cursor.getString(cursor.getColumnIndexOrThrow("image_uri"));
                int  uso_id = cursor.getInt(cursor.getColumnIndexOrThrow("uso_id"));
                int estado_conservacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("estado_conservacion_id"));

                FormRoedor formRoedorTemp = new FormRoedor();

                formRoedorTemp.setId(id);
                formRoedorTemp.setProyecto_id(proyecto_id);
                formRoedorTemp.setEstacion_muestreo_id(estacion_muestreo_id);
                formRoedorTemp.setTemporada_evaluacion_id(temporada_evaluacion_id);
                formRoedorTemp.setUnidad_vegetacion_id(unidad_vegetacion_id);
                formRoedorTemp.setFecha(fecha);
                formRoedorTemp.setHora(hora);
                formRoedorTemp.setClima_id(clima_id);
                formRoedorTemp.setFranja_id(franja_id);
                formRoedorTemp.setMetodologia_id(metodologia_id);
                formRoedorTemp.setTipo_trampa_id(tipo_trampa_id);
                formRoedorTemp.setUnidad_muestreal_id(unidad_muestreal_id);
                formRoedorTemp.setEste(este);
                formRoedorTemp.setNorte(norte);
                formRoedorTemp.setAltitud(altitud);
                formRoedorTemp.setClase_id(clase_id);
                formRoedorTemp.setOrden_id(orden_id);
                formRoedorTemp.setFamilia_id(familia_id);
                formRoedorTemp.setGenero_id(genero_id);
                formRoedorTemp.setEspecie_id(especie_id);
                formRoedorTemp.setNombre_comun(nombre_comun);
                formRoedorTemp.setNumero_individuos(numero_individuos);
                formRoedorTemp.setEstadio_id(estadio_id);
                formRoedorTemp.setSexo(sexo);
                formRoedorTemp.setCondicion_reproductiva_id(condicion_reproductiva_id);
                formRoedorTemp.setCategoria_abundancia_id(categoria_abundancia_id);
                formRoedorTemp.setHabito_id(habito_id);
                formRoedorTemp.setGrupo_trofico_id(grupo_trofico_id);
                formRoedorTemp.setLongitud_cuerpo(longitud_cuerpo);
                formRoedorTemp.setLongitud_oreja(longitud_oreja);
                formRoedorTemp.setLongitud_cola(longitud_cola);
                formRoedorTemp.setLongitud_pata(longitud_pata);
                formRoedorTemp.setIndicador_id(indicador_id);
                formRoedorTemp.setUicn(uicn);
                formRoedorTemp.setCites(cites);
                formRoedorTemp.setDsn(dsn);
                formRoedorTemp.setLibro_rojo(libro_rojo);
                formRoedorTemp.setEndemismo(endemismo);
                formRoedorTemp.setDistribucion_endemismo(distribucion_endemismo);
                formRoedorTemp.setComentario(comentario);
                formRoedorTemp.setImage_uri(image_uri);
                formRoedorTemp.setUso_id(uso_id);
                formRoedorTemp.setEstado_conservacion_id(estado_conservacion_id);

                list.add(formRoedorTemp);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }

    private List<FormQuiroptero> getQuiropterosListFromDB(SQLiteDatabase db){
        List<FormQuiroptero> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_QUIROPTEROS, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int proyecto_id = cursor.getInt(cursor.getColumnIndexOrThrow("proyecto_id"));
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
                int  uso_id = cursor.getInt(cursor.getColumnIndexOrThrow("uso_id"));
                int estado_conservacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("estado_conservacion_id"));

                FormQuiroptero formQuiropteroTemp = new FormQuiroptero();

                formQuiropteroTemp.setId(id);
                formQuiropteroTemp.setProyecto_id(proyecto_id);
                formQuiropteroTemp.setEstacion_muestreo_id(estacion_muestreo_id);
                formQuiropteroTemp.setTemporada_evaluacion_id(temporada_evaluacion_id);
                formQuiropteroTemp.setUnidad_vegetacion_id(unidad_vegetacion_id);
                formQuiropteroTemp.setFecha(fecha);
                formQuiropteroTemp.setHora(hora);
                formQuiropteroTemp.setClima_id(clima_id);
                formQuiropteroTemp.setFranja_id(franja_id);
                formQuiropteroTemp.setMetodologia_id(metodologia_id);
                formQuiropteroTemp.setTipo_trampa_id(tipo_trampa_id);
                formQuiropteroTemp.setUnidad_muestreal_id(unidad_muestreal_id);
                formQuiropteroTemp.setEste(este);
                formQuiropteroTemp.setNorte(norte);
                formQuiropteroTemp.setAltitud(altitud);
                formQuiropteroTemp.setClase_id(clase_id);
                formQuiropteroTemp.setOrden_id(orden_id);
                formQuiropteroTemp.setFamilia_id(familia_id);
                formQuiropteroTemp.setGenero_id(genero_id);
                formQuiropteroTemp.setEspecie_id(especie_id);
                formQuiropteroTemp.setNombre_comun(nombre_comun);
                formQuiropteroTemp.setNumero_individuos(numero_individuos);
                formQuiropteroTemp.setEstadio_id(estadio_id);
                formQuiropteroTemp.setSexo(sexo);
                formQuiropteroTemp.setCondicion_reproductiva_id(condicion_reproductiva_id);
                formQuiropteroTemp.setCategoria_abundancia_id(categoria_abundancia_id);
                formQuiropteroTemp.setHabito_id(habito_id);
                formQuiropteroTemp.setGrupo_trofico_id(grupo_trofico_id);
                formQuiropteroTemp.setLongitud_antebrazo(longitud_antebrazo);
                formQuiropteroTemp.setLongitud_oreja(longitud_oreja);
                formQuiropteroTemp.setLongitud_cola(longitud_cola);
                formQuiropteroTemp.setLongitud_tibia(longitud_tibia);
                formQuiropteroTemp.setPeso(peso);
                formQuiropteroTemp.setIndicador_id(indicador_id);
                formQuiropteroTemp.setUicn(uicn);
                formQuiropteroTemp.setCites(cites);
                formQuiropteroTemp.setDsn(dsn);
                formQuiropteroTemp.setLibro_rojo(libro_rojo);
                formQuiropteroTemp.setEndemismo(endemismo);
                formQuiropteroTemp.setDistribucion_endemismo(distribucion_endemismo);
                formQuiropteroTemp.setIbas(ibas);
                formQuiropteroTemp.setEbas(ebas);
                formQuiropteroTemp.setComentario(comentario);
                formQuiropteroTemp.setImage_uri(image_uri);
                formQuiropteroTemp.setUso_id(uso_id);
                formQuiropteroTemp.setEstado_conservacion_id(estado_conservacion_id);

                list.add(formQuiropteroTemp);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }

    private List<FormOrnitofauna> getOrnitofaunaListFromDB(SQLiteDatabase db){
        List<FormOrnitofauna> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_ORNITOFAUNA, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int franja_id = cursor.getInt(cursor.getColumnIndexOrThrow("franja_id"));
                int temporada_evaluacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("temporada_evaluacion_id"));
                int estacion_muestreo_id = cursor.getInt(cursor.getColumnIndexOrThrow("estacion_muestreo_id"));
                int metodologia_id = cursor.getInt(cursor.getColumnIndexOrThrow("metodologia_id"));
                int unidad_muestreal_id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_muestreal_id"));
                float este = cursor.getFloat(cursor.getColumnIndexOrThrow("este"));
                float norte = cursor.getFloat(cursor.getColumnIndexOrThrow("norte"));
                float altitud = cursor.getFloat(cursor.getColumnIndexOrThrow("altitud"));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow("hora"));
                int clima_id = cursor.getInt(cursor.getColumnIndexOrThrow("clima_id"));
                int clase_id = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));
                int orden_id = cursor.getInt(cursor.getColumnIndexOrThrow("orden_id"));
                int familia_id = cursor.getInt(cursor.getColumnIndexOrThrow("familia_id"));
                int genero_id = cursor.getInt(cursor.getColumnIndexOrThrow("genero_id"));
                int especie_id = cursor.getInt(cursor.getColumnIndexOrThrow("especie_id"));
                String nombre_comun = cursor.getString(cursor.getColumnIndexOrThrow("nombre_comun"));
                String nombre_local = cursor.getString(cursor.getColumnIndexOrThrow("nombre_local"));
                int numero_individuos = cursor.getInt(cursor.getColumnIndexOrThrow("numero_individuos"));
                int tipo_registro_id = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_registro_id"));
                int categoria_abundancia_id = cursor.getInt(cursor.getColumnIndexOrThrow("categoria_abundancia_id"));
                int habito_id = cursor.getInt(cursor.getColumnIndexOrThrow("habito_id"));
                int grupo_trofico_id = cursor.getInt(cursor.getColumnIndexOrThrow("grupo_trofico_id"));
                int indicador_id = cursor.getInt(cursor.getColumnIndexOrThrow("indicador_id"));
                float largo_cola = cursor.getFloat(cursor.getColumnIndexOrThrow("largo_cola"));
                float largo_pata = cursor.getFloat(cursor.getColumnIndexOrThrow("largo_pata"));
                float longitud_total = cursor.getFloat(cursor.getColumnIndexOrThrow("longitud_total"));
                float peso = cursor.getFloat(cursor.getColumnIndexOrThrow("peso"));
                int estadio_id = cursor.getInt(cursor.getColumnIndexOrThrow("estadio_id"));
                String sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo"));
                String uicn = cursor.getString(cursor.getColumnIndexOrThrow("uicn"));
                String cites = cursor.getString(cursor.getColumnIndexOrThrow("cites"));
                String ibas = cursor.getString(cursor.getColumnIndexOrThrow("ibas"));
                String usos = cursor.getString(cursor.getColumnIndexOrThrow("usos"));
                String comentario = cursor.getString(cursor.getColumnIndexOrThrow("comentario"));
                String image_uri = cursor.getString(cursor.getColumnIndexOrThrow("image_uri"));
                int especialista_id = cursor.getInt(cursor.getColumnIndexOrThrow("especialista_id"));
                int estado_conservacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("estado_conservacion_id"));

                FormOrnitofauna formFloraTemp = new FormOrnitofauna();
                formFloraTemp.setId(id);
                formFloraTemp.setFranja_id(franja_id);
                formFloraTemp.setTemporada_evaluacion_id(temporada_evaluacion_id);
                formFloraTemp.setEstacion_muestreo_id(estacion_muestreo_id);
                formFloraTemp.setMetodologia_id(metodologia_id);
                formFloraTemp.setUnidad_muestreal_id(unidad_muestreal_id);
                formFloraTemp.setEste(este);
                formFloraTemp.setNorte(norte);
                formFloraTemp.setAltitud(altitud);
                formFloraTemp.setFecha(fecha);
                formFloraTemp.setHora(hora);
                formFloraTemp.setClima_id(clima_id);
                formFloraTemp.setClase_id(clase_id);
                formFloraTemp.setOrden_id(orden_id);
                formFloraTemp.setFamilia_id(familia_id);
                formFloraTemp.setGenero_id(genero_id);
                formFloraTemp.setEspecie_id(especie_id);
                formFloraTemp.setNombre_comun(nombre_comun);
                formFloraTemp.setNombre_local(nombre_local);
                formFloraTemp.setNumero_individuos(numero_individuos);
                formFloraTemp.setTipo_registro_id(tipo_registro_id);
                formFloraTemp.setCategoria_abundancia_id(categoria_abundancia_id);
                formFloraTemp.setHabito_id(habito_id);
                formFloraTemp.setGrupo_trofico_id(grupo_trofico_id);
                formFloraTemp.setIndicador_id(indicador_id);
                formFloraTemp.setLargo_cola(largo_cola);
                formFloraTemp.setLargo_pata(largo_pata);
                formFloraTemp.setLongitud_total(longitud_total);
                formFloraTemp.setPeso(peso);
                formFloraTemp.setEstadio_id(estadio_id);
                formFloraTemp.setSexo(sexo);
                formFloraTemp.setUicn(uicn);
                formFloraTemp.setCites(cites);
                formFloraTemp.setIbas(ibas);
                formFloraTemp.setUsos(usos);
                formFloraTemp.setComentario(comentario);
                formFloraTemp.setImage_uri(image_uri);
                formFloraTemp.setEspecialista_id(especialista_id);
                formFloraTemp.setEstado_conservacion_id(estado_conservacion_id);

                list.add(formFloraTemp);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }
    private List<FormHerpetologia> getHerpetologiaListFromDB(SQLiteDatabase db){
        List<FormHerpetologia> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_HERPETOLOGIA, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int estacion_muestreo_id = cursor.getInt(cursor.getColumnIndexOrThrow("estacion_muestreo_id"));
                int temporada_evaluacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("temporada_evaluacion_id"));
                int unidad_vegetacion_id = cursor.getInt(cursor.getColumnIndexOrThrow("unidad_vegetacion_id"));
                int franja_id = cursor.getInt(cursor.getColumnIndexOrThrow("franja_id"));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));
                int clima_id = cursor.getInt(cursor.getColumnIndexOrThrow("clima_id"));
                int tipo_registro_id = cursor.getInt(cursor.getColumnIndexOrThrow("tipo_registro_id"));
                int metodologia_id = cursor.getInt(cursor.getColumnIndexOrThrow("metodologia_id"));
                String unidad_muestreal = cursor.getString(cursor.getColumnIndexOrThrow("unidad_muestreal"));
                int clase_id = cursor.getInt(cursor.getColumnIndexOrThrow("clase_id"));
                int orden_id = cursor.getInt(cursor.getColumnIndexOrThrow("orden_id"));
                int familia_id = cursor.getInt(cursor.getColumnIndexOrThrow("familia_id"));
                int genero_id = cursor.getInt(cursor.getColumnIndexOrThrow("genero_id"));
                int especie_id = cursor.getInt(cursor.getColumnIndexOrThrow("especie_id"));
                String nombre_comun = cursor.getString(cursor.getColumnIndexOrThrow("nombre_comun"));
                int numero_individuos = cursor.getInt(cursor.getColumnIndexOrThrow("numero_individuos"));
                float edad = cursor.getFloat(cursor.getColumnIndexOrThrow("edad"));
                String sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo"));
                int sustrato_id = cursor.getInt(cursor.getColumnIndexOrThrow("sustrato_id"));
                int microhabitat_id = cursor.getInt(cursor.getColumnIndexOrThrow("microhabitat_id"));
                int actividad_id = cursor.getInt(cursor.getColumnIndexOrThrow("actividad_id"));
                int categoria_abundancia_id = cursor.getInt(cursor.getColumnIndexOrThrow("categoria_abundancia_id"));
                int habito_id = cursor.getInt(cursor.getColumnIndexOrThrow("habito_id"));
                int grupo_trofico_id = cursor.getInt(cursor.getColumnIndexOrThrow("grupo_trofico_id"));
                float este = cursor.getFloat(cursor.getColumnIndexOrThrow("este"));
                float norte = cursor.getFloat(cursor.getColumnIndexOrThrow("norte"));
                float altitud = cursor.getFloat(cursor.getColumnIndexOrThrow("altitud"));
                int indicador_id = cursor.getInt(cursor.getColumnIndexOrThrow("indicador_id"));
                String uicn = cursor.getString(cursor.getColumnIndexOrThrow("uicn"));
                String cites = cursor.getString(cursor.getColumnIndexOrThrow("cites"));
                String dsn = cursor.getString(cursor.getColumnIndexOrThrow("dsn"));
                String libro_rojo = cursor.getString(cursor.getColumnIndexOrThrow("libro_rojo"));
                String endemismo = cursor.getString(cursor.getColumnIndexOrThrow("endemismo"));
                String distribucion_endemismo = cursor.getString(cursor.getColumnIndexOrThrow("distribucion_endemismo"));
                String usos = cursor.getString(cursor.getColumnIndexOrThrow("usos"));
                String comentario = cursor.getString(cursor.getColumnIndexOrThrow("comentario"));
                String image_uri = cursor.getString(cursor.getColumnIndexOrThrow("image_uri"));
                int estado_conservacion_habitat_id = cursor.getInt(cursor.getColumnIndexOrThrow("proyecto_id"));
                int proyecto_id = cursor.getInt(cursor.getColumnIndexOrThrow("estado_conservacion_habitat_id"));
                int especialista_id = cursor.getInt(cursor.getColumnIndexOrThrow("especialista_id"));

                FormHerpetologia formHerpetologia = new FormHerpetologia();

                formHerpetologia.setId(id);
                formHerpetologia.setEstacion_muestreo_id(estacion_muestreo_id);
                formHerpetologia.setTemporada_evaluacion_id(temporada_evaluacion_id);
                formHerpetologia.setUnidad_vegetacion_id(unidad_vegetacion_id);
                formHerpetologia.setFranja_id(franja_id);
                formHerpetologia.setFecha(fecha);
                formHerpetologia.setClima_id(clima_id);
                formHerpetologia.setTipo_registro_id(tipo_registro_id);
                formHerpetologia.setMetodologia_id(metodologia_id);
                formHerpetologia.setUnidad_muestreal(unidad_muestreal);
                formHerpetologia.setClase_id(clase_id);
                formHerpetologia.setOrden_id(orden_id);
                formHerpetologia.setFamilia_id(familia_id);
                formHerpetologia.setGenero_id(genero_id);
                formHerpetologia.setEspecie_id(especie_id);
                formHerpetologia.setNombre_comun(nombre_comun);
                formHerpetologia.setNumero_individuos(numero_individuos);
                formHerpetologia.setEdad(edad);
                formHerpetologia.setSexo(sexo);
                formHerpetologia.setSustrato_id(sustrato_id);
                formHerpetologia.setMicrohabitat_id(microhabitat_id);
                formHerpetologia.setActividad_id(actividad_id);
                formHerpetologia.setCategoria_abundancia_id(categoria_abundancia_id);
                formHerpetologia.setHabito_id(habito_id);
                formHerpetologia.setGrupo_trofico_id(grupo_trofico_id);
                formHerpetologia.setEste(este);
                formHerpetologia.setNorte(norte);
                formHerpetologia.setAltitud(altitud);
                formHerpetologia.setIndicador_id(indicador_id);
                formHerpetologia.setUicn(uicn);
                formHerpetologia.setCites(cites);
                formHerpetologia.setDsn(dsn);
                formHerpetologia.setLibro_rojo(libro_rojo);
                formHerpetologia.setEndemismo(endemismo);
                formHerpetologia.setDistribucion_endemismo(distribucion_endemismo);
                formHerpetologia.setUsos(usos);
                formHerpetologia.setComentario(comentario);
                formHerpetologia.setImage_uri(image_uri);
                formHerpetologia.setEstado_conservacion_habitat_id(estado_conservacion_habitat_id);
                formHerpetologia.setProyecto_id(proyecto_id);
                formHerpetologia.setEspecialista_id(especialista_id);


                list.add(formHerpetologia);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return list;
    }
}