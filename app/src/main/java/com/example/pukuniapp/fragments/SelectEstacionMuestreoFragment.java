package com.example.pukuniapp.fragments;

import static com.example.pukuniapp.helpers.DBHelper.TABLE_ESTACION_MUESTREO;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pukuniapp.activities.LoginActivity;
import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.EstacionMuestreo;
import com.example.pukuniapp.helpers.DBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectEstacionMuestreoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectEstacionMuestreoFragment extends Fragment {

    public SelectEstacionMuestreoFragment() {
        // Required empty public constructor
    }

    public static SelectEstacionMuestreoFragment newInstance(String formName) {
        SelectEstacionMuestreoFragment fragment = new SelectEstacionMuestreoFragment();
        Bundle args = new Bundle();
        args.putString("form_name", formName);
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
        View view = inflater.inflate(R.layout.fragment_select_estacion_muestreo, container, false);

        String formName = null;

        if (getArguments() != null) {
            formName = getArguments().getString("form_name");
        }

        String finalFormName = formName;

        TextView label = getActivity().findViewById(R.id.tv_fragment_title);
        label.setText("Seleccione una Estación de Muestreo");

        LinearLayout btnContainer = view.findViewById(R.id.btn_container);

        DBHelper dbHelper = new DBHelper(requireContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
            "SELECT * FROM " + TABLE_ESTACION_MUESTREO,
            null
        );

        LinearLayout currentRow = null;
        int count = 0;

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("estacion_muestreo_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("estacion_muestreo_name"));
                String departamento = cursor.getString(cursor.getColumnIndexOrThrow("departamento_name"));
                String provincia = cursor.getString(cursor.getColumnIndexOrThrow("provincia_name"));
                String distrito = cursor.getString(cursor.getColumnIndexOrThrow("distrito_name"));

                EstacionMuestreo estacion = new EstacionMuestreo();
                estacion.setEstacion_muestreo_id(id);
                estacion.setEstacion_muestreo_name(name);
                estacion.setDepartamento_name(departamento);
                estacion.setProvincia_name(provincia);
                estacion.setDistrito_name(distrito);

                if (count % 2 == 0) {
                    currentRow = new LinearLayout(requireContext());
                    currentRow.setOrientation(LinearLayout.HORIZONTAL);
                    currentRow.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    currentRow.setGravity(Gravity.CENTER_HORIZONTAL);
                    btnContainer.addView(currentRow);
                }

                Button btn = new Button(requireContext());
                btn.setText(estacion.getEstacion_muestreo_name() + "\n\n(" +
                        estacion.getDepartamento_name() + ", " +
                        estacion.getProvincia_name() + ", " +
                        estacion.getDistrito_name() + ")");
                btn.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
                btn.setBackgroundResource(R.drawable.rounded_background);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btn.getLayoutParams();
                params.setMargins(50, 50, 50, 50);
                btn.setLayoutParams(params);

                btn.setOnClickListener(v -> {
                    switch (finalFormName){
                        case "Botánica": {
                            FloraFormFragment newFragment = FloraFormFragment.newInstance(estacion.getEstacion_muestreo_id(), -1);
                            requireActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, newFragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        }
                        case "Líquenes": {
                            Toast.makeText(getContext(), "Líquenes", Toast.LENGTH_SHORT).show();
//                            FloraFormFragment newFragment = FloraFormFragment.newInstance(estacion.getEstacion_muestreo_id(), -1);
//                            requireActivity().getSupportFragmentManager()
//                                    .beginTransaction()
//                                    .replace(R.id.fragment_container, newFragment)
//                                    .addToBackStack(null)
//                                    .commit();
                            break;
                        }
                        case "Ornitofauna": {
                            OrnitoFaunaFragment newFragment = OrnitoFaunaFragment.newInstance(estacion.getEstacion_muestreo_id(), -1);
                            requireActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, newFragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        }
                        case "Herpetología": {
                            HerpetologiaFragment newFragment = HerpetologiaFragment.newInstance(estacion.getEstacion_muestreo_id(), -1);
                            requireActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, newFragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        }
                        case "Mamíferos Mayores": {
                            MamiferosMayoresFragment newFragment = MamiferosMayoresFragment.newInstance(estacion.getEstacion_muestreo_id(), -1);
                            requireActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, newFragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        }
                        case "Quiropteros": {
                            QuiropterosFragment newFragment = QuiropterosFragment.newInstance(estacion.getEstacion_muestreo_id(), -1);
                            requireActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, newFragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        }
                        case "Roedores y Marsupiales": {
                            RoedoresFragment newFragment = RoedoresFragment.newInstance(estacion.getEstacion_muestreo_id(), -1);
                            requireActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, newFragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        }
                        case "Hidrobiología": {
                            HidrobiologiaFormFragment newFragment = HidrobiologiaFormFragment.newInstance(estacion.getEstacion_muestreo_id(), -1);
                            requireActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, newFragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        }
                    }
                });

                if (currentRow != null) {
                    currentRow.addView(btn);
                }

                count++;
            } while (cursor.moveToNext());
        }

        cursor.close();

        return view;
    }

    private void irALogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}