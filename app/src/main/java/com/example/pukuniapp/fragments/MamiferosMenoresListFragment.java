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
 * Use the {@link MamiferosMenoresListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MamiferosMenoresListFragment extends Fragment {
    public static MamiferosMenoresListFragment newInstance(String formName) {
        MamiferosMenoresListFragment fragment = new MamiferosMenoresListFragment();
        Bundle args = new Bundle();
        args.putString("form_name", formName);
        fragment.setArguments(args);
        return fragment;
    }

    public MamiferosMenoresListFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_mamiferos_menores, container, false);

        // Quiropteros Click
        View.OnClickListener quiropterosClickListener = v -> {
            TextView quiropterosFormText = v.findViewById(R.id.form_quiropteros_text);
            String formName = quiropterosFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_quiropteros_click).setOnClickListener(quiropterosClickListener);

        // Roedores Click
        View.OnClickListener roedoresClickListener = v -> {
            TextView roedoresFormText = v.findViewById(R.id.form_roedores_text);
            String formName = roedoresFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_roedores_click).setOnClickListener(roedoresClickListener);

        return view;
    }
}