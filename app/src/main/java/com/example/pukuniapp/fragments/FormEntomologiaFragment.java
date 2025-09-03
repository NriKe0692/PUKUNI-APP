package com.example.pukuniapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pukuniapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormEntomologiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormEntomologiaFragment extends Fragment {

    public FormEntomologiaFragment() {
        // Required empty public constructor
    }

    public static FormEntomologiaFragment newInstance(int estacionId, int formularioId) {
        FormEntomologiaFragment fragment = new FormEntomologiaFragment();
        Bundle args = new Bundle();
        args.putInt("formulario_id", formularioId);
        args.putInt("estacion_id", estacionId);
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entomologia, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        if(label != null){
            label.setText("Formulario Entomología");
        }

        return view;
    }
}