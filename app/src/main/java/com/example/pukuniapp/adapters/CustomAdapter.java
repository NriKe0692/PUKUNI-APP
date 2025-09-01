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
import com.example.pukuniapp.classes.FormOrnitofauna;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FLORA = 0;
    private static final int TYPE_ORNITOFAUNA = 1;
    private List<FormFlora> floraList;
    private List<FormOrnitofauna> ornitofaunaList;

    public CustomAdapter(List<FormFlora> floraList, List<FormOrnitofauna> ornitofaunaList) {
        this.floraList = floraList;
        this.ornitofaunaList = ornitofaunaList;
    }

    public interface OnItemClickListener {
        void onFloraClick(FormFlora flora, int position);
        void onOrnitofaunaClick(FormOrnitofauna ornito, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        // Si está en el rango de flora → TYPE_FLORA
        if (position < floraList.size()) {
            return TYPE_FLORA;
        } else {
            return TYPE_ORNITOFAUNA;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FLORA) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_form, parent, false);
            return new FloraViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_form, parent, false); // puedes usar otro layout si quieres
            return new OrnitofaunaViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_FLORA) {
            FormFlora flora = floraList.get(position);
            FloraViewHolder floraHolder = (FloraViewHolder) holder;

            floraHolder.tv_form_type.setText("Flora");
            floraHolder.tv_localidad.setText("Localidad: " + flora.getLocalidad());
            floraHolder.tv_especie.setText("Especie: " + flora.getEspecie_id());
            floraHolder.tv_altura.setText("Altura: " + flora.getAltura() + " cm");
            floraHolder.tv_usos.setText("Usos: " + flora.getUsos());
            floraHolder.img_preview.setImageURI(Uri.parse(flora.getImageUri()));

            floraHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onFloraClick(flora, position);
                }
            });
        } else {
            int ornitoIndex = position - floraList.size();
            FormOrnitofauna ornito = ornitofaunaList.get(ornitoIndex);
            OrnitofaunaViewHolder ornitoHolder = (OrnitofaunaViewHolder) holder;

            ornitoHolder.tv_form_type.setText("Ornitofauna");
            ornitoHolder.tv_localidad.setText("Zona: " + ornito.getZona_id());
            ornitoHolder.tv_especie.setText("Especie: " + ornito.getEspecie_id());
            ornitoHolder.tv_altura.setVisibility(View.GONE);
            ornitoHolder.tv_usos.setVisibility(View.GONE);
            ornitoHolder.img_preview.setImageURI(Uri.parse(ornito.getImage_uri()));

            ornitoHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onOrnitofaunaClick(ornito, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return floraList.size() + ornitofaunaList.size();
    }

    // 🔹 ViewHolder para Flora
    public static class FloraViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;

        public FloraViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
        }
    }

    // 🔹 ViewHolder para Ornitofauna
    public static class OrnitofaunaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;

        public OrnitofaunaViewHolder(@NonNull View itemView) {
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
