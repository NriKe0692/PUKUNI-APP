package com.example.pukuniapp.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.FormFlora;

import java.util.List;

public class FormFloraAdapter extends RecyclerView.Adapter<FormFloraAdapter.FormFloraViewHolder> {
    private List<FormFlora> floraList;

    public FormFloraAdapter(List<FormFlora> floraList) {
        this.floraList = floraList;
    }

    @NonNull
    @Override
    public FormFloraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_form, parent, false);
        return new FormFloraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormFloraViewHolder holder, int position) {
        FormFlora flora = floraList.get(position);
        holder.tv_form_type.setText("Tipo de Formulario: Flora");
        holder.tv_localidad.setText("Localidad: " + flora.getLocalidad());
        holder.tv_especie.setText("Especie: " + flora.getEspecie_id());
        holder.tv_altura.setText("Altura: " + flora.getAltura() + " cm");
        holder.tv_usos.setText("Usos: " + flora.getUsos());
        holder.img_preview.setImageURI(Uri.parse(flora.getImageUri()));
    }

    @Override
    public int getItemCount() {
        return floraList.size();
    }

    public static class FormFloraViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;

        public FormFloraViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
        }
    }
}
