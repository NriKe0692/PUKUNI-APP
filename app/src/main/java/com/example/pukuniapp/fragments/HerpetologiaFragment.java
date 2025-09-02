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
 * Use the {@link HerpetologiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HerpetologiaFragment extends Fragment {
    public HerpetologiaFragment() {
        // Required empty public constructor
    }

    public static HerpetologiaFragment newInstance(int estacionMuestreoId, int formId) {
        HerpetologiaFragment fragment = new HerpetologiaFragment();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_herpetologia, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        if(label != null){
            label.setText("Formulario Herpetología");
        }

        return view;
    }
}