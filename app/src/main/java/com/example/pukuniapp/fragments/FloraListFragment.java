package com.example.pukuniapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pukuniapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FloraListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FloraListFragment extends Fragment {

    public FloraListFragment() {
        // Required empty public constructor
    }

    public static FloraListFragment newInstance() {
        FloraListFragment fragment = new FloraListFragment();
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
        View view = inflater.inflate(R.layout.fragment_flora_list, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        label.setText("Seleccione un Formulario");

        // Botanica Click
        View.OnClickListener botanicaClickListener = v -> {
            TextView botanicaFormText = v.findViewById(R.id.form_botanica_text);
            String formName = botanicaFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_botanica_click).setOnClickListener(botanicaClickListener);

        // Liquenes Click
        View.OnClickListener liquenesClickListener = v -> {
            TextView liquenesFormText = v.findViewById(R.id.form_liquenes_text);
            String formName = liquenesFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_liquenes_click).setOnClickListener(liquenesClickListener);

        return view;
    }
}