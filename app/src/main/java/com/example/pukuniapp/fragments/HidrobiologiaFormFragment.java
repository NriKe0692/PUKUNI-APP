package com.example.pukuniapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pukuniapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HidrobiologiaFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HidrobiologiaFormFragment extends Fragment {
    public HidrobiologiaFormFragment() {
        // Required empty public constructor
    }

    public static HidrobiologiaFormFragment newInstance(String param1, String param2) {
        HidrobiologiaFormFragment fragment = new HidrobiologiaFormFragment();
        Bundle args = new Bundle();
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
        return inflater.inflate(R.layout.fragment_hidrobiologia_form, container, false);
    }
}