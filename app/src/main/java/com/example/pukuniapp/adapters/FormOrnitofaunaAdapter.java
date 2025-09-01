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
import com.example.pukuniapp.classes.FormOrnitofauna;

import java.util.List;

public class FormOrnitofaunaAdapter  extends RecyclerView.Adapter<FormOrnitofaunaAdapter.FormOrnitofaunaViewHolder>{
    private List<FormOrnitofauna> ornitofaunaList;

    public FormOrnitofaunaAdapter(List<FormOrnitofauna> ornitofaunaList) {
        this.ornitofaunaList = ornitofaunaList;
    }

    @NonNull
    @Override
    public FormOrnitofaunaAdapter.FormOrnitofaunaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_form, parent, false);
        return new FormOrnitofaunaAdapter.FormOrnitofaunaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormOrnitofaunaAdapter.FormOrnitofaunaViewHolder holder, int position) {
        FormOrnitofauna ornitofauna = ornitofaunaList.get(position);
        holder.tv_form_type.setText("Tipo de Formulario: Flora");
        holder.tv_localidad.setText("Zona: " + ornitofauna.getZona_id());
        holder.tv_especie.setText("Especie: " + ornitofauna.getEspecie_id());
        holder.tv_altura.setText("Clima: " + ornitofauna.getClima_id());
        holder.tv_usos.setText("Usos: " + ornitofauna.getUsos());
        holder.img_preview.setImageURI(Uri.parse(ornitofauna.getImage_uri()));
    }

    @Override
    public int getItemCount() {
        return ornitofaunaList.size();
    }

    public static class FormOrnitofaunaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;

        public FormOrnitofaunaViewHolder(@NonNull View itemView) {
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
