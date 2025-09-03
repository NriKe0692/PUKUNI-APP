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
 * Use the {@link FormListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormListFragment extends Fragment {
    public FormListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormListFragment newInstance(String param1, String param2) {
        FormListFragment fragment = new FormListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_list, container, false);

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);

        label.setText("Seleccione un Tipo de Formulario");

        View.OnClickListener floraClickListener = v -> {
            FloraListFragment newFragment = FloraListFragment.newInstance();

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_flora_click).setOnClickListener(floraClickListener);

        View.OnClickListener faunaClickListener = v -> {
            FaunaListFragment newFragment = FaunaListFragment.newInstance();

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_fauna_click).setOnClickListener(faunaClickListener);

        // Hidrobiologia Click
        View.OnClickListener hidrobiologiaClickListener = v -> {
            TextView hidrobiologiaFormText = v.findViewById(R.id.form_hidrobiologia_text);
            String formName = hidrobiologiaFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_hidrobiologia_click).setOnClickListener(hidrobiologiaClickListener);

        // Forestal Click
        View.OnClickListener forestalClickListener = v -> {
            TextView forestalFormText = v.findViewById(R.id.form_forestal_text);
            String formName = forestalFormText.getText().toString();

            SelectEstacionMuestreoFragment newFragment = SelectEstacionMuestreoFragment.newInstance(formName);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        };

        view.findViewById(R.id.form_forestal_click).setOnClickListener(forestalClickListener);

        return view;
    }
}