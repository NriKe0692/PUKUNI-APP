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
import com.example.pukuniapp.classes.FormHerpetologia;
import com.example.pukuniapp.classes.FormOrnitofauna;
import com.example.pukuniapp.classes.FormQuiroptero;
import com.example.pukuniapp.classes.FormRoedor;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FLORA = 0;
    private static final int TYPE_ORNITOFAUNA = 1;
    private static final int TYPE_QUIROPTERO = 2;
    private static final int TYPE_ROEDOR = 3;
    private static final int TYPE_HERPETOLOGIA = 4;
    private List<Object> allItems;

    public CustomAdapter(List<FormFlora> floraList, List<FormOrnitofauna> ornitofaunaList, List<FormQuiroptero> quiropteroList, List<FormRoedor> roedoresList, List<FormHerpetologia> herpetologiaList) {
        allItems = new ArrayList<>();
        if (floraList != null) allItems.addAll(floraList);
        if (ornitofaunaList != null) allItems.addAll(ornitofaunaList);
        if (quiropteroList != null) allItems.addAll(quiropteroList);
        if (roedoresList != null) allItems.addAll(roedoresList);
        if (herpetologiaList != null) allItems.addAll(herpetologiaList);
    }

    public interface OnItemClickListener {
        void onFloraClick(FormFlora flora, int position);
        void onOrnitofaunaClick(FormOrnitofauna ornito, int position);
        void onQuiropteroClick(FormQuiroptero quiroptero, int position);
        void onRoedorClick(FormRoedor roedor, int position);
        void onHerpetologiaClick(FormHerpetologia herpetologia, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = allItems.get(position);

        if (item instanceof FormFlora) {
            return TYPE_FLORA;
        } else if (item instanceof FormOrnitofauna) {
            return TYPE_ORNITOFAUNA;
        } else if (item instanceof FormQuiroptero) {
            return TYPE_QUIROPTERO;
        } else if (item instanceof FormRoedor) {
            return TYPE_ROEDOR;
        } else if (item instanceof FormHerpetologia) {
            return TYPE_HERPETOLOGIA;
        } else {
            throw new IllegalArgumentException("Tipo desconocido en la lista");
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
        } else if(viewType == TYPE_ORNITOFAUNA){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_form, parent, false);
            return new OrnitofaunaViewHolder(view);
        } else if(viewType == TYPE_QUIROPTERO){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_form, parent, false);
            return new QuiropterosViewHolder(view);
        }  else if(viewType == TYPE_HERPETOLOGIA){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_form, parent, false);
            return new HerpetologiaViewHolder(view);
        } else{
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_form, parent, false);
            return new RoedoresViewHolder(view);
        }
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object item = allItems.get(position);

        if (holder.getItemViewType() == TYPE_FLORA && item instanceof FormFlora) {
            FormFlora flora = (FormFlora) item;
            FloraViewHolder floraHolder = (FloraViewHolder) holder;

            floraHolder.tv_form_type.setText("Flora");
            floraHolder.tv_localidad.setText("Localidad: " + flora.getLocalidad());
            floraHolder.tv_especie.setText("Especie: " + flora.getEspecie_id());
            floraHolder.tv_altura.setText("Altura: " + flora.getAltura() + " cm");
            floraHolder.tv_usos.setText("Usos: " + flora.getUso_id());
            floraHolder.img_preview.setImageURI(Uri.parse(flora.getImageUri()));

            floraHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onFloraClick(flora, position);
                }
            });
        } else if(holder.getItemViewType() == TYPE_ORNITOFAUNA && item instanceof FormOrnitofauna) {
            FormOrnitofauna ornito = (FormOrnitofauna) item;
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
        } else if(holder.getItemViewType() == TYPE_QUIROPTERO && item instanceof FormQuiroptero) {
            FormQuiroptero quiroptero = (FormQuiroptero) item;
            QuiropterosViewHolder quiropteroHolder = (QuiropterosViewHolder) holder;

            quiropteroHolder.tv_form_type.setText("Quiróptero");
            quiropteroHolder.tv_localidad.setText("Metodología: " + quiroptero.getMetodologia_id());
            quiropteroHolder.tv_especie.setText("Especie: " + quiroptero.getEspecie_id());
            quiropteroHolder.tv_altura.setVisibility(View.GONE);
            quiropteroHolder.tv_usos.setVisibility(View.GONE);
            quiropteroHolder.img_preview.setImageURI(Uri.parse(quiroptero.getImage_uri()));

            quiropteroHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onQuiropteroClick(quiroptero, position);
                }
            });
        } else if(holder.getItemViewType() == TYPE_ROEDOR && item instanceof FormRoedor) {
            FormRoedor roedor = (FormRoedor) item;
            RoedoresViewHolder roedorHolder = (RoedoresViewHolder) holder;

            roedorHolder.tv_form_type.setText("Roedor");
            roedorHolder.tv_localidad.setText("Metodología: " + roedor.getMetodologia_id());
            roedorHolder.tv_especie.setText("Especie: " + roedor.getEspecie_id());
            roedorHolder.tv_altura.setVisibility(View.GONE);
            roedorHolder.tv_usos.setVisibility(View.GONE);
            roedorHolder.img_preview.setImageURI(Uri.parse(roedor.getImage_uri()));

            roedorHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRoedorClick(roedor, position);
                }
            });
        } else if(holder.getItemViewType() == TYPE_HERPETOLOGIA && item instanceof FormHerpetologia) {
            FormHerpetologia herpetologia = (FormHerpetologia) item;
            HerpetologiaViewHolder herpetologiaHolder = (HerpetologiaViewHolder) holder;

            herpetologiaHolder.tv_form_type.setText("Herpetología");
            herpetologiaHolder.tv_localidad.setText("Metodología: " + herpetologia.getMetodologia_id());
            herpetologiaHolder.tv_especie.setText("Especie: " + herpetologia.getEspecie_id());
            herpetologiaHolder.tv_altura.setVisibility(View.GONE);
            herpetologiaHolder.tv_usos.setVisibility(View.GONE);
            herpetologiaHolder.img_preview.setImageURI(Uri.parse(herpetologia.getImage_uri()));

            herpetologiaHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onHerpetologiaClick(herpetologia, position);
                }
            });
        }
    }

    // ViewHolder para Flora
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

    // ViewHolder para Ornitofauna
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

    // ViewHolder para Quiropteros
    public static class QuiropterosViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;

        public QuiropterosViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
        }
    }

    // ViewHolder para Roedores
    public static class RoedoresViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;

        public RoedoresViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
        }
    }

    // ViewHolder para Herpetologia
    public static class HerpetologiaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;

        public HerpetologiaViewHolder(@NonNull View itemView) {
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
