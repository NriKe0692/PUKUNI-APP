package com.example.pukuniapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pukuniapp.LoginActivity;
import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.Clase;
import com.example.pukuniapp.classes.Especie;
import com.example.pukuniapp.classes.Familia;
import com.example.pukuniapp.classes.Forofito;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.Genero;
import com.example.pukuniapp.classes.Orden;
import com.example.pukuniapp.classes.Pais;
import com.example.pukuniapp.classes.Parcela;
import com.example.pukuniapp.classes.SubParcela;
import com.example.pukuniapp.classes.UnidadMuestreo;
import com.example.pukuniapp.classes.UnidadVegetacion;
import com.example.pukuniapp.retrofit.ApiClient;
import com.example.pukuniapp.retrofit.ApiService;

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
    TextView textViewEste;
    TextView textViewNorte;
    TextView textViewAltitud;
    AutoCompleteTextView et_clase;
    AutoCompleteTextView et_orden;
    AutoCompleteTextView et_familia;
    AutoCompleteTextView et_genero;
    AutoCompleteTextView et_especie;

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

        SharedPreferences prefs = requireActivity().getSharedPreferences("PukuniPrefs", android.content.Context.MODE_PRIVATE);
        String token = prefs.getString("auth_token", null);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);
        label.setText("Formulario Flora");

        spinnerSubEstacionMuestreo = view.findViewById(R.id.spinner_sub_estacion_muestreo);
        spinnerUnidadMuestreo = view.findViewById(R.id.spinner_unidad_muestreo);
        spinnerParcela = view.findViewById(R.id.spinner_parcela);
        spinnerForofito = view.findViewById(R.id.spinner_forofito);
        spinnerSubParcela = view.findViewById(R.id.spinner_sub_parcela);
        spinnerUnidadVegetacion = view.findViewById(R.id.spinner_unidad_vegetacion);
        textViewEste = view.findViewById(R.id.tv_este);
        textViewNorte = view.findViewById(R.id.tv_norte);
        textViewAltitud = view.findViewById(R.id.tv_altitud);
        et_clase= view.findViewById(R.id.et_clase);
        et_orden = view.findViewById(R.id.et_orden);
        et_familia = view.findViewById(R.id.et_familia);
        et_genero = view.findViewById(R.id.et_genero);
        et_especie = view.findViewById(R.id.et_especie);

        if (token == null) {
            irALogin();
        } else {
            ApiService api = ApiClient.getRetrofit().create(ApiService.class);

            setSubEstacionesValues(api, token);
            setUnidadeDeMuestreoValues(api, token);
            setUnidadDeVegetacionValues(api, token);
            getCoordinates();
            setupAutocompleteTv(api, token);
        }

        return view;
    }

    private void setupAutocompleteTv(ApiService api, String token){
        api.getClases("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Clase>> call, Response<List<Clase>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Clase> clasesList = response.body();

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

                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Clase>> call, Throwable t) {
                irALogin();
            }
        });

        api.getOrdenes("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Orden>> call, Response<List<Orden>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Orden> ordenesList = response.body();

                    List<String> ordenesNames = new ArrayList<>();

                    for (Orden orden : ordenesList) {
                        ordenesNames.add(orden.getOrden_name());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            ordenesNames
                    );

                    et_orden.setAdapter(adapter);
                    et_orden.setThreshold(1);

                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Orden>> call, Throwable t) {
                irALogin();
            }
        });

        api.getFamilias("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Familia>> call, Response<List<Familia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Familia> familiasList = response.body();

                    List<String> familiasNames = new ArrayList<>();

                    for (Familia familia : familiasList) {
                        familiasNames.add(familia.getFamilia_name());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            familiasNames
                    );

                    et_familia.setAdapter(adapter);
                    et_familia.setThreshold(1);

                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Familia>> call, Throwable t) {
                irALogin();
            }
        });

        api.getGeneros("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Genero>> call, Response<List<Genero>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Genero> generosList = response.body();

                    List<String> generosNames = new ArrayList<>();

                    for (Genero genero : generosList) {
                        generosNames.add(genero.getGenero_name());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            generosNames
                    );

                    et_genero.setAdapter(adapter);
                    et_genero.setThreshold(1);

                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Genero>> call, Throwable t) {
                irALogin();
            }
        });

        api.getEspecies("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Especie>> call, Response<List<Especie>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Especie> especiesList = response.body();

                    List<String> especieNames = new ArrayList<>();
                    for (Especie especie : especiesList) {
                        especieNames.add(especie.getEspecie_name());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            especieNames
                    );

                    et_especie.setAdapter(adapter);
                    et_especie.setThreshold(1);

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

    private void setSubEstacionesValues(ApiService api, String token){
        if (this.getArguments() != null) {
            int estacionMuestreoId = getArguments().getInt("estacion_id", -1);

            if(estacionMuestreoId != -1){
                api.getFranjas("Bearer " + token, estacionMuestreoId).enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<List<Franja>> call, Response<List<Franja>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Franja> subEstacionesList = response.body();

                            ArrayAdapter<Franja> adapter = new ArrayAdapter<>(
                                    requireContext(),
                                    android.R.layout.simple_spinner_item,
                                    subEstacionesList
                            );
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinnerSubEstacionMuestreo.setAdapter(adapter);

                            spinnerSubEstacionMuestreo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    Franja franja = (Franja) spinnerSubEstacionMuestreo.getSelectedItem();

                                    if (franja != null) {
                                        setParcelaValues(api, token);
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
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
        }

    }
    private void setUnidadeDeMuestreoValues(ApiService api, String token){
        api.getUnidadMuestreoList("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UnidadMuestreo>> call, Response<List<UnidadMuestreo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnidadMuestreo> unidadMuestreoList = response.body();

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
                                        String parcelaName = parcelaSeleccionada.getParcela_name();

                                        setForofitosValues(api, token, parcelaId, parcelaName);
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
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<UnidadMuestreo>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void setParcelaValues(ApiService api, String token){
        Franja franjaSeleccionada = (Franja) spinnerSubEstacionMuestreo.getSelectedItem();

        if(franjaSeleccionada != null){
            int franjaId = franjaSeleccionada.getFranja_id();
            String franjaName = franjaSeleccionada.getFranja_name();

            if(franjaId != -1){
                api.getParcelas("Bearer " + token, franjaId).enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<List<Parcela>> call, Response<List<Parcela>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Parcela> parcelaList = response.body();

                            for (Parcela p : parcelaList) {
                                p.setFranjaName(franjaName);
                            }

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
                                            String parcelaName = parcelaSeleccionada.getParcela_name();

                                            setForofitosValues(api, token, parcelaId, parcelaName);
                                        }
                                    }

                                    if(parcelaSeleccionada != null){
                                        int parcelaId = parcelaSeleccionada.getParcela_id();

                                        setSubparcelasValues(api, token, parcelaId);
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // No hacer nada
                                }
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
        }
    }

    private void setForofitosValues(ApiService api, String token, int parcelaId, String parcelaName){
        Franja franjaSeleccionada = (Franja) spinnerSubEstacionMuestreo.getSelectedItem();

        if(franjaSeleccionada != null){
            int franjaId = franjaSeleccionada.getFranja_id();
            String franjaName = franjaSeleccionada.getFranja_name();

            if(franjaId != -1){
                api.getForofitos("Bearer " + token, parcelaId).enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<List<Forofito>> call, Response<List<Forofito>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Forofito> forofitoList = response.body();

                            for (Forofito f : forofitoList) {
                                f.setParcela_name(franjaName + " (" + parcelaName + ") -");
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
                        } else {
                            Toast.makeText(getContext(), "Error obteniendo forofitos", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Forofito>> call, Throwable t) {
                        Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    private void setSubparcelasValues(ApiService api, String token, int parcelaId){
        api.getSubparcelas("Bearer " + token, parcelaId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<SubParcela>> call, Response<List<SubParcela>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SubParcela> subParcelaListList = response.body();

                    ArrayAdapter<SubParcela> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            subParcelaListList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerSubParcela.setAdapter(adapter);
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

    private void removeForofitosValues(){
        ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new ArrayList<>()
        );

        spinnerForofito.setAdapter(emptyAdapter);
    }

    private void setUnidadDeVegetacionValues(ApiService api, String token){
        api.getUnidadVegetacion("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<UnidadVegetacion>> call, Response<List<UnidadVegetacion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnidadVegetacion> unidadVegetacionList = response.body();

                    ArrayAdapter<UnidadVegetacion> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            unidadVegetacionList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerUnidadVegetacion.setAdapter(adapter);
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

        Toast.makeText(getContext(), "Este: " + easting, Toast.LENGTH_SHORT).show();

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