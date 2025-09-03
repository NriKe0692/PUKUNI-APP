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
 * Use the {@link FaunaListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FaunaListFragment extends Fragment {

    public FaunaListFragment() {
        // Required empty public constructor
    }

    public static FaunaListFragment newInstance() {
        FaunaListFragment fragment = new FaunaListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fauna_list, container, false);
        // Inflate the layout for this fragment
        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        label.setText("Seleccione un Formulario");

        // Ornitofauna Click
        View.OnClickListener ornitofaunaClickListener = v -> {
            TextView ornitofaunaFormText = v.findViewById(R.id.form_ornitofauna_text);
            String formName = ornitofaunaFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .addToBackStack(null)
                .commit();
        };

        view.findViewById(R.id.form_ornitofauna_click).setOnClickListener(ornitofaunaClickListener);

        // Herpetologia Click
        View.OnClickListener herpetologiaClickListener = v -> {
            TextView herpetologiaFormText = v.findViewById(R.id.form_herpetologia_text);
            String formName = herpetologiaFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_herpetologia_click).setOnClickListener(herpetologiaClickListener);

        // Mamiferos Menores Click
        View.OnClickListener mamiferosMenoresClickListener = v -> {
            TextView mastoFormText = v.findViewById(R.id.form_mamiferos_menores_text);
            String formName = mastoFormText.getText().toString();

            MamiferosMenoresListFragment newFragment = MamiferosMenoresListFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_mamiferos_menores_click).setOnClickListener(mamiferosMenoresClickListener);

        // Mamiferos Mayores Click
        View.OnClickListener mamiferosMayoresClickListener = v -> {
            TextView mastoFormText = v.findViewById(R.id.form_mamiferos_mayores_text);
            String formName = mastoFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_mamiferos_mayores_click).setOnClickListener(mamiferosMayoresClickListener);

        // Entomologia Click
        View.OnClickListener entomologiaClickListener = v -> {
            TextView entomologiaFormText = v.findViewById(R.id.form_entomologia_text);
            String formName = entomologiaFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_entomologia_click).setOnClickListener(entomologiaClickListener);

        return view;
    }
}