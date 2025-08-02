package com.example.pukuniapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pukuniapp.classes.Departamento;
import com.example.pukuniapp.classes.Distrito;
import com.example.pukuniapp.classes.Forofito;
import com.example.pukuniapp.classes.Location;
import com.example.pukuniapp.classes.Pais;
import com.example.pukuniapp.classes.Parcela;
import com.example.pukuniapp.classes.Provincia;
import com.example.pukuniapp.classes.SubParcela;
import com.example.pukuniapp.classes.UnidadMuestreo;
import com.example.pukuniapp.classes.UnidadVegetacion;
import com.example.pukuniapp.retrofit.ApiClient;
import com.example.pukuniapp.retrofit.ApiService;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.KFunction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FloraFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FloraFormFragment extends Fragment {
    public FloraFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FloraFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FloraFormFragment newInstance(String nombreFranja, int franjaId) {
        FloraFormFragment fragment = new FloraFormFragment();
        Bundle args = new Bundle();
        args.putInt("franja_id", franjaId);
        args.putString("franja_name", nombreFranja);
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

        EditText etFecha = view.findViewById(R.id.et_fecha);
        etFecha.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting;
            private int previousLength;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previousLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No hacemos nada aquí
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isFormatting) return;

                isFormatting = true;

                String input = s.toString().replaceAll("[^\\d]", ""); // solo números
                StringBuilder formatted = new StringBuilder();

                for (int i = 0; i < input.length() && i < 8; i++) {
                    formatted.append(input.charAt(i));
                    if ((i == 1 || i == 3) && i != input.length() - 1) {
                        formatted.append('/');
                    }
                }

                etFecha.removeTextChangedListener(this);
                etFecha.setText(formatted.toString());
                etFecha.setSelection(formatted.length()); // mover cursor al final
                etFecha.addTextChangedListener(this);

                isFormatting = false;

                // ✅ Validar si ya hay 10 caracteres (dd/MM/yyyy)
                if (formatted.length() == 10) {
                    String fecha = formatted.toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false);

                    try {
                        sdf.parse(fecha); // lanza excepción si la fecha no es válida
                        etFecha.setError(null); // fecha válida, quitar error si existía
                    } catch (ParseException e) {
                        etFecha.setError("Fecha inválida");
                        Toast.makeText(getContext(), "La fecha ingresada no es válida", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Spinner spinnerLocation = view.findViewById(R.id.spinner_lugar);
        Spinner spinnerUnidadMuestreo = view.findViewById(R.id.spinner_unidad_muestreo);
        Spinner spinnerParcela = view.findViewById(R.id.spinner_parcela);
        Spinner spinnerForofito = view.findViewById(R.id.spinner_forofito);
        Spinner spinnerSubParcela = view.findViewById(R.id.spinner_sub_parcela);
        Spinner spinnerUnidadVegetacion = view.findViewById(R.id.spinner_unidad_vegetacion);
        Spinner spinnerPais = view.findViewById(R.id.spinner_pais);
        Spinner spinnerDepartamento = view.findViewById(R.id.spinner_departamento);
        Spinner spinnerProvincia = view.findViewById(R.id.spinner_provincia);
        Spinner spinnerDistrito = view.findViewById(R.id.spinner_distrito);

        if (token == null) {
            irALogin();
        } else {
            ApiService api = ApiClient.getRetrofit().create(ApiService.class);

            setLocationValues(api, token, spinnerLocation);
            setUnidadeDeMuestreoValues(api, token, spinnerUnidadMuestreo, spinnerParcela, spinnerForofito);
            setParcelaValues(api, token, spinnerParcela, spinnerUnidadMuestreo, spinnerForofito, spinnerSubParcela);
            setUnidadDeVegetacionValues(api, token, spinnerUnidadVegetacion);
            setPaisValues(api, token, spinnerPais);
            setDepartamentosValues(api, token, spinnerDepartamento, spinnerProvincia, spinnerDistrito);
        }

        return view;
    }

    private void setDepartamentosValues(ApiService api, String token, Spinner spinnerDepartamento, Spinner spinnerProvincia, Spinner spinnerDistrito){
        api.getDepartamentos("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Departamento>> call, Response<List<Departamento>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Departamento> departamentoList = response.body();

                    ArrayAdapter<Departamento> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            departamentoList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDepartamento.setAdapter(adapter);

                    spinnerDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Departamento departamentoSeleccionado = (Departamento) spinnerDepartamento.getSelectedItem();

                            if (departamentoSeleccionado != null) {
                                int departamentoId = departamentoSeleccionado.getDepartamento_id();

                                setProvinciasValues(api, token, departamentoId, spinnerProvincia, spinnerDistrito);
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
            public void onFailure(Call<List<Departamento>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void setProvinciasValues(ApiService api, String token, int departamentoId, Spinner spinnerProvincia, Spinner spinnerDistrito){
        api.getProvincias("Bearer " + token, departamentoId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Provincia>> call, Response<List<Provincia>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Provincia> provinciaList = response.body();

                    Log.d("API_RESPONSE", "Provincias recibidas: " + new Gson().toJson(provinciaList));

                    ArrayAdapter<Provincia> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            provinciaList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProvincia.setAdapter(adapter);

                    spinnerProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Provincia provinciaSeleccionada = (Provincia) spinnerProvincia.getSelectedItem();

                            if (provinciaSeleccionada != null) {
                                int provinciaId = provinciaSeleccionada.getPronvicia_id();
                                Toast.makeText(getContext(), "Provincia seleccionada: " + provinciaId, Toast.LENGTH_SHORT).show();

                                setDistritosValues(api, token, provinciaId, spinnerDistrito);
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
            public void onFailure(Call<List<Provincia>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void setDistritosValues(ApiService api, String token, int provinciaId, Spinner spinnerDistrito){
        api.getDistritos("Bearer " + token, provinciaId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Distrito>> call, Response<List<Distrito>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Distrito> distritoList = response.body();

                    ArrayAdapter<Distrito> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            distritoList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDistrito.setAdapter(adapter);
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Distrito>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void setLocationValues(ApiService api, String token, Spinner spinnerLocation) {
        api.getLocations("Bearer " + token).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Location> locationList = response.body();

                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            locationList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerLocation.setAdapter(adapter);
                } else {
                    irALogin();
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                irALogin();
            }
        });
    }

    private void setUnidadeDeMuestreoValues(ApiService api, String token, Spinner spinnerUnidadMuestreo, Spinner spinnerParcela, Spinner spinnerForofito){
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

                                        setForofitosValues(api, token, spinnerForofito, parcelaId, parcelaName);
                                    }
                                }else{
                                    removeForofitosValues(spinnerForofito);
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

    private void setParcelaValues(ApiService api, String token, Spinner spinnerParcela, Spinner spinnerUnidadMuestreo, Spinner spinnerForofito, Spinner spinnerSubParcela){
        if (this.getArguments() != null) {
            int franjaId = getArguments().getInt("franja_id", -1);
            String franjaName = getArguments().getString("franja_name", "");

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

                                            setForofitosValues(api, token, spinnerForofito, parcelaId, parcelaName);
                                        }
                                    }

                                    if(parcelaSeleccionada != null){
                                        int parcelaId = parcelaSeleccionada.getParcela_id();

                                        setSubparcelasValues(api, token, spinnerSubParcela, parcelaId);
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

    private void setForofitosValues(ApiService api, String token, Spinner spinnerForofito, int parcelaId, String parcelaName){
        if (this.getArguments() != null) {
            int franjaId = getArguments().getInt("franja_id", -1);
            String franjaName = getArguments().getString("franja_name", "");

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

    private void setSubparcelasValues(ApiService api, String token, Spinner spinnerSubParcela, int parcelaId){
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

    private void removeForofitosValues(Spinner spinnerForofito){
        ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new ArrayList<>()
        );

        spinnerForofito.setAdapter(emptyAdapter);
    }

    private void setUnidadDeVegetacionValues(ApiService api, String token, Spinner spinnerUnidadVegetacion){
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


    private void irALogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}