package com.example.pukuniapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pukuniapp.LoginActivity;
import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.EstacionMuestreo;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.retrofit.ApiClient;
import com.example.pukuniapp.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectEstacionMuestreoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectEstacionMuestreoFragment extends Fragment {

    public SelectEstacionMuestreoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SelectEstacionMuestreoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectEstacionMuestreoFragment newInstance(String formName) {
        SelectEstacionMuestreoFragment fragment = new SelectEstacionMuestreoFragment();
        Bundle args = new Bundle();
        args.putString("form_name", formName);
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
        View view = inflater.inflate(R.layout.fragment_select_estacion_muestreo, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);
        label.setText("Seleccione una Estación de Muestreo");

        LinearLayout btnContainer = view.findViewById(R.id.btn_container);

        SharedPreferences prefs = requireActivity().getSharedPreferences("PukuniPrefs", android.content.Context.MODE_PRIVATE);
        String token = prefs.getString("auth_token", null);

        if (token == null) {
            irALogin();
        } else {
            ApiService api = ApiClient.getRetrofit().create(ApiService.class);

            api.getEstacionMuestreoList("Bearer " + token).enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<List<EstacionMuestreo>> call, Response<List<EstacionMuestreo>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        LinearLayout currentRow = null;
                        int count = 0;

                        for (EstacionMuestreo estacion : response.body()) {
                            if (count % 2 == 0) {
                                currentRow = new LinearLayout(requireContext());
                                currentRow.setOrientation(LinearLayout.HORIZONTAL);
                                currentRow.setLayoutParams(new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                ));
                                currentRow.setGravity(Gravity.CENTER_HORIZONTAL);
                                btnContainer.addView(currentRow);
                            }

                            Button btn = new Button(requireContext());
                            btn.setText(estacion.getEstacion_muestreo_name() + "\n\n(" +
                                    estacion.getDepartamento_name() + ", " +
                                    estacion.getProvincia_name() + ", " +
                                    estacion.getDistrito_name() + ")");
                            btn.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
                            btn.setBackgroundResource(R.drawable.rounded_background);
                            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btn.getLayoutParams();
                            params.setMargins(50, 50, 50, 50);
                            btn.setLayoutParams(params);

                            btn.setOnClickListener(v -> {
                                FloraFormFragment newFragment = FloraFormFragment.newInstance(estacion.getEstacion_muestreo_name(), estacion.getEstacion_muestreo_id());
                                requireActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragment_container, newFragment)
                                        .addToBackStack(null)
                                        .commit();
                            });

                            if (currentRow != null) {
                                currentRow.addView(btn);
                            }

                            count++;
                        }
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

        return view;
    }

    private void irALogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}