package com.example.pukuniapp.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pukuniapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MamiferosMenoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MamiferosMenoresFragment extends Fragment {
    public static MamiferosMenoresFragment newInstance(String formName) {
        MamiferosMenoresFragment fragment = new MamiferosMenoresFragment();
        Bundle args = new Bundle();
        args.putString("form_name", formName);
        fragment.setArguments(args);
        return fragment;
    }

    public MamiferosMenoresFragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mamiferos_menores, container, false);
    }
}