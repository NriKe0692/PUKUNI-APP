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
 * Use the {@link FormLiquenesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormLiquenesFragment extends Fragment {

    public FormLiquenesFragment() {
        // Required empty public constructor
    }

    public static FormLiquenesFragment newInstance(int estacionId, int formularioId) {
        FormLiquenesFragment fragment = new FormLiquenesFragment();
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
        View view = inflater.inflate(R.layout.fragment_liquenes, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        if(label != null){
            label.setText("Formulario Líquenes");
        }

        return view;
    }
}