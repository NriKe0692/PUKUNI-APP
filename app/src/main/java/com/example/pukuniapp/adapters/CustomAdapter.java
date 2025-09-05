package com.example.pukuniapp.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pukuniapp.R;
import com.example.pukuniapp.classes.FormFlora;
import com.example.pukuniapp.classes.FormHerpetologia;
import com.example.pukuniapp.classes.FormHidrobiologia;
import com.example.pukuniapp.classes.FormMamiferosGrandes;
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
    private static final int TYPE_ENTOMOLOGIA = 5;
    private static final int TYPE_LIQUENES = 6;
    private static final int TYPE_MAMIFEROS_MYG = 7;
    private static final int TYPE_FORESTAL = 8;
    private static final int TYPE_HIDROBIOLOGIA = 9;
    private List<Object> allItems;

    public CustomAdapter(List<FormFlora> floraList, List<FormOrnitofauna> ornitofaunaList, List<FormQuiroptero> quiropteroList,
                         List<FormRoedor> roedoresList, List<FormHerpetologia> herpetologiaList, List<FormHidrobiologia> hidrobiologiaList,
                         List<FormMamiferosGrandes> mamiferosGrandesList) {
        allItems = new ArrayList<>();
        if (floraList != null) allItems.addAll(floraList);
        if (ornitofaunaList != null) allItems.addAll(ornitofaunaList);
        if (quiropteroList != null) allItems.addAll(quiropteroList);
        if (roedoresList != null) allItems.addAll(roedoresList);
        if (herpetologiaList != null) allItems.addAll(herpetologiaList);
        if (hidrobiologiaList != null) allItems.addAll(hidrobiologiaList);
        if (mamiferosGrandesList != null) allItems.addAll(mamiferosGrandesList);
    }

    public interface OnItemClickListener {
        void onFloraClick(FormFlora flora, int position);
        void onFloraSendClick(FormFlora flora);
        void onOrnitofaunaClick(FormOrnitofauna ornito, int position);
        void onOrnitofaunaSendClick(FormOrnitofauna ornito);
        void onQuiropteroClick(FormQuiroptero quiroptero, int position);
        void onQuiropteroSendClick(FormQuiroptero quiroptero);
        void onRoedorClick(FormRoedor roedor, int position);
        void onRoedorSendClick(FormRoedor roedor);
        void onHerpetologiaClick(FormHerpetologia herpetologia, int position);
        void onHerpetologiaSendClick(FormHerpetologia herpetologia);
        void onHidrobiologiaClick(FormHidrobiologia hidrobiologia, int position);
        void onHidrobiologiaSendClick(FormHidrobiologia hidrobiologia);
        void onMamiferosGrandesClick(FormMamiferosGrandes mamiferosGrandes, int position);
        void onMamiferosGrandesSendClick(FormMamiferosGrandes mamiferosGrandes);
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
        } else if (item instanceof FormHidrobiologia) {
            return TYPE_HIDROBIOLOGIA;
        } else if (item instanceof FormMamiferosGrandes) {
            return TYPE_MAMIFEROS_MYG;
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
        } else if(viewType == TYPE_HERPETOLOGIA){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_form, parent, false);
            return new HerpetologiaViewHolder(view);
        } else if(viewType == TYPE_HIDROBIOLOGIA){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_form, parent, false);
            return new HidrobiologiaViewHolder(view);
        } else if(viewType == TYPE_MAMIFEROS_MYG){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_form, parent, false);
            return new MamiferosGrandesViewHolder(view);
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

            floraHolder.send_form.setOnClickListener(v -> {
                if(listener != null){
                    listener.onFloraSendClick(flora);
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

            ornitoHolder.send_form.setOnClickListener(v -> {
                if(listener != null){
                    listener.onOrnitofaunaSendClick(ornito);
                }
            });
        } else if(holder.getItemViewType() == TYPE_QUIROPTERO && item instanceof FormQuiroptero) {
            FormQuiroptero quiroptero = (FormQuiroptero) item;
            QuiropterosViewHolder quiropteroHolder = (QuiropterosViewHolder) holder;

            quiropteroHolder.tv_form_type.setText("Quiróptero");
            quiropteroHolder.tv_localidad.setText("Especialista: " + quiroptero.getEspecialista_id());
            quiropteroHolder.tv_especie.setText("Especie: " + quiroptero.getEspecie_id());
            quiropteroHolder.tv_altura.setVisibility(View.GONE);
            quiropteroHolder.tv_usos.setVisibility(View.GONE);
            quiropteroHolder.img_preview.setImageURI(Uri.parse(quiroptero.getImage_uri()));

            quiropteroHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onQuiropteroClick(quiroptero, position);
                }
            });

            quiropteroHolder.send_form.setOnClickListener(v -> {
                if(listener != null){
                    listener.onQuiropteroSendClick(quiroptero);
                }
            });
        } else if(holder.getItemViewType() == TYPE_ROEDOR && item instanceof FormRoedor) {
            FormRoedor roedor = (FormRoedor) item;
            RoedoresViewHolder roedorHolder = (RoedoresViewHolder) holder;

            roedorHolder.tv_form_type.setText("Roedor");
            roedorHolder.tv_localidad.setText("Especialista: " + roedor.getEspecialista_id());
            roedorHolder.tv_especie.setText("Especie: " + roedor.getEspecie_id());
            roedorHolder.tv_altura.setVisibility(View.GONE);
            roedorHolder.tv_usos.setVisibility(View.GONE);
            roedorHolder.img_preview.setImageURI(Uri.parse(roedor.getImage_uri()));

            roedorHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRoedorClick(roedor, position);
                }
            });

            roedorHolder.send_form.setOnClickListener(v -> {
                if(listener != null){
                    listener.onRoedorSendClick(roedor);
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

            herpetologiaHolder.send_form.setOnClickListener(v -> {
                if(listener != null){
                    listener.onHerpetologiaSendClick(herpetologia);
                }
            });
        } else if(holder.getItemViewType() == TYPE_HIDROBIOLOGIA && item instanceof FormHidrobiologia) {
            FormHidrobiologia hidrobiologia = (FormHidrobiologia) item;
            HidrobiologiaViewHolder hidrobiologiaHolder = (HidrobiologiaViewHolder) holder;

            hidrobiologiaHolder.tv_form_type.setText("Hidrobioloía");
            hidrobiologiaHolder.tv_localidad.setText("Metodología: " + hidrobiologia.getMetodologia_id());
            hidrobiologiaHolder.tv_especie.setText("Especie: " + hidrobiologia.getEspecie_id());
            hidrobiologiaHolder.tv_altura.setVisibility(View.GONE);
            hidrobiologiaHolder.tv_usos.setVisibility(View.GONE);
            hidrobiologiaHolder.img_preview.setImageURI(Uri.parse(hidrobiologia.getImg_uri()));

            hidrobiologiaHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onHidrobiologiaClick(hidrobiologia, position);
                }
            });

            hidrobiologiaHolder.send_form.setOnClickListener(v -> {
                if(listener != null){
                    listener.onHidrobiologiaSendClick(hidrobiologia);
                }
            });
        } else if(holder.getItemViewType() == TYPE_MAMIFEROS_MYG && item instanceof FormMamiferosGrandes) {
            FormMamiferosGrandes mamiferosGrandes = (FormMamiferosGrandes) item;
            MamiferosGrandesViewHolder mamiferosGrandesViewHolder = (MamiferosGrandesViewHolder) holder;

            mamiferosGrandesViewHolder.tv_form_type.setText("Mamíferos Medianos y Grandes");
            mamiferosGrandesViewHolder.tv_localidad.setText("Metodología: " + mamiferosGrandes.getMetodologia_id());
            mamiferosGrandesViewHolder.tv_especie.setText("Especie: " + mamiferosGrandes.getEspecie_id());
            mamiferosGrandesViewHolder.tv_altura.setVisibility(View.GONE);
            mamiferosGrandesViewHolder.tv_usos.setVisibility(View.GONE);
            mamiferosGrandesViewHolder.img_preview.setImageURI(Uri.parse(mamiferosGrandes.getImg_uri()));

            mamiferosGrandesViewHolder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMamiferosGrandesClick(mamiferosGrandes, position);
                }
            });

            mamiferosGrandesViewHolder.send_form.setOnClickListener(v -> {
                if(listener != null){
                    listener.onMamiferosGrandesSendClick(mamiferosGrandes);
                }
            });
        }
    }

    // ViewHolder para Flora
    public static class FloraViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;
        Button send_form;

        public FloraViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
            send_form = itemView.findViewById(R.id.send_form);
        }
    }

    // ViewHolder para Ornitofauna
    public static class OrnitofaunaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;
        Button send_form;

        public OrnitofaunaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
            send_form = itemView.findViewById(R.id.send_form);
        }
    }

    // ViewHolder para Quiropteros
    public static class QuiropterosViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;
        Button send_form;

        public QuiropterosViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
            send_form = itemView.findViewById(R.id.send_form);
        }
    }

    // ViewHolder para Roedores
    public static class RoedoresViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;
        Button send_form;

        public RoedoresViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
            send_form = itemView.findViewById(R.id.send_form);
        }
    }

    // ViewHolder para Herpetologia
    public static class HerpetologiaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;
        Button send_form;

        public HerpetologiaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
            send_form = itemView.findViewById(R.id.send_form);
        }
    }

    // ViewHolder para Hidrobiologia
    public static class HidrobiologiaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;
        Button send_form;

        public HidrobiologiaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
            send_form = itemView.findViewById(R.id.send_form);
        }
    }

    // ViewHolder para Mamiferos Medianos y Grandes
    public static class MamiferosGrandesViewHolder extends RecyclerView.ViewHolder {
        TextView tv_form_type, tv_localidad, tv_especie, tv_altura, tv_usos;
        ImageView img_preview;
        Button send_form;

        public MamiferosGrandesViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_form_type = itemView.findViewById(R.id.tv_form_type);
            tv_localidad = itemView.findViewById(R.id.tv_localidad);
            tv_especie = itemView.findViewById(R.id.tv_especie);
            tv_altura = itemView.findViewById(R.id.tv_altura);
            tv_usos = itemView.findViewById(R.id.tv_usos);
            img_preview = itemView.findViewById(R.id.img_preview);
            send_form = itemView.findViewById(R.id.send_form);
        }
    }
}
