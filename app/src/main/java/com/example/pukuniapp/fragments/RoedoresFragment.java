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
 * Use the {@link RoedoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoedoresFragment extends Fragment {

    public RoedoresFragment() {
        // Required empty public constructor
    }

    public static RoedoresFragment newInstance(int estacionMuestreoId, int formId) {
        RoedoresFragment fragment = new RoedoresFragment();
        Bundle args = new Bundle();
        args.putInt("estacion_id", estacionMuestreoId);
        args.putInt("formulario_id", formId);
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
        View view = inflater.inflate(R.layout.fragment_roedores, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        if(label != null){
            label.setText("Formulario Roedores");
        }

        return view;
    }
}