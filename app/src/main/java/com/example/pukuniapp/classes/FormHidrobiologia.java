package com.example.pukuniapp.classes;

public class FormHidrobiologia {
    private int id;
    private int especialista_id;
    private int proyecto_id;
    private String localidad;
    private int cuenca_hidrografica_id;
    private int estacion_muestreo_id;
    private int temporada_evaluacion_id;
    private int tipo_ambiente_acuatico_id;
    private int estacion_id;
    private int punto_muestreo_id;
    private float este;
    private float norte;
    private float altitud;
    private String fecha;
    private String hora;
    private String pendiente_cauce;
    private int clima_id;
    private float ancho_cauce_sector;
    private String tipo_orilla;
    private String tipo_agua;
    private String color_aparente_agua;
    private int metodologia_id;
    private float longitud_muestreo;
    private float ancho_muestreo;
    private float area_muestreo;
    private float velocidad_corriente;
    private float profundidad_maxima_muestreo;
    private float profundidad_maxima_sector;
    private float transparencia;
    private String vegetacion_emergente;
    private String vegetacion_sumergida;
    private String vegetacion_flotante;
    private float habitat_porcentaje_long_caida;
    private float habitat_porcentaje_long_rifle;
    private float habitat_porcentaje_long_corridas;
    private float habitat_porcentaje_long_pozos;
    private float habitat_porcentaje_long_remanso;
    private float sustrato_porcentaje_arena;
    private float sustrato_porcentaje_arcilla;
    private float sustrato_porcentaje_limo;
    private float sustrato_porcentaje_grava;
    private float sustrato_porcentaje_organico_hojarasca;
    private float sustrato_porcentaje_organico_ramas;
    private float sustrato_porcentaje_organico_arbustos_enraizados;
    private int unidad_vegetacion_id;
    private String vegetacion_circundante;
    private int clase_id;
    private int orden_id;
    private int familia_id;
    private int genero_id;
    private int especie_id;
    private String nombre_comun;
    private int individuos;
    private String uicn;
    private String cites;
    private String dsn;
    private float nivel_trofico_fishbase;
    private int habito_alimenticio_id;
    private int uso_id;
    private int categoria_abundancia_id;
    private int indicador_id;
    private String endemismo;
    private String comportamiento;
    private float longitud_total;
    private float peso;
    private int habitat_id;
    private String etapa_reproductiva;
    private String comentario;
    private String img_uri;
    private String imgBase64;

    public String getImgBase64() {
        return imgBase64;
    }
    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }
    public String getEtapa_reproductiva() {
        return etapa_reproductiva;
    }

    public void setEtapa_reproductiva(String etapa_reproductiva) {
        this.etapa_reproductiva = etapa_reproductiva;
    }

    public float getTransparencia() {
        return transparencia;
    }

    public void setTransparencia(float transparencia) {
        this.transparencia = transparencia;
    }

    public String getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(String img_uri) {
        this.img_uri = img_uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getCuenca_hidrografica_id() {
        return cuenca_hidrografica_id;
    }

    public void setCuenca_hidrografica_id(int cuenca_hidrografica_id) {
        this.cuenca_hidrografica_id = cuenca_hidrografica_id;
    }

    public int getEstacion_muestreo_id() {
        return estacion_muestreo_id;
    }

    public void setEstacion_muestreo_id(int estacion_muestreo_id) {
        this.estacion_muestreo_id = estacion_muestreo_id;
    }

    public int getTemporada_evaluacion_id() {
        return temporada_evaluacion_id;
    }

    public void setTemporada_evaluacion_id(int temporada_evaluacion_id) {
        this.temporada_evaluacion_id = temporada_evaluacion_id;
    }

    public int getTipo_ambiente_acuatico_id() {
        return tipo_ambiente_acuatico_id;
    }

    public void setTipo_ambiente_acuatico_id(int tipo_ambiente_acuatico_id) {
        this.tipo_ambiente_acuatico_id = tipo_ambiente_acuatico_id;
    }

    public int getEstacion_id() {
        return estacion_id;
    }

    public void setEstacion_id(int estacion_id) {
        this.estacion_id = estacion_id;
    }

    public int getPunto_muestreo_id() {
        return punto_muestreo_id;
    }

    public void setPunto_muestreo_id(int punto_muestreo_id) {
        this.punto_muestreo_id = punto_muestreo_id;
    }

    public float getEste() {
        return este;
    }

    public void setEste(float este) {
        this.este = este;
    }

    public float getNorte() {
        return norte;
    }

    public void setNorte(float norte) {
        this.norte = norte;
    }

    public float getAltitud() {
        return altitud;
    }

    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPendiente_cauce() {
        return pendiente_cauce;
    }

    public void setPendiente_cauce(String pendiente_cauce) {
        this.pendiente_cauce = pendiente_cauce;
    }

    public int getClima_id() {
        return clima_id;
    }

    public void setClima_id(int clima_id) {
        this.clima_id = clima_id;
    }

    public float getAncho_cauce_sector() {
        return ancho_cauce_sector;
    }

    public void setAncho_cauce_sector(float ancho_cauce_sector) {
        this.ancho_cauce_sector = ancho_cauce_sector;
    }

    public String getTipo_orilla() {
        return tipo_orilla;
    }

    public void setTipo_orilla(String tipo_orilla) {
        this.tipo_orilla = tipo_orilla;
    }

    public String getTipo_agua() {
        return tipo_agua;
    }

    public void setTipo_agua(String tipo_agua) {
        this.tipo_agua = tipo_agua;
    }

    public String getColor_aparente_agua() {
        return color_aparente_agua;
    }

    public void setColor_aparente_agua(String color_aparente_agua) {
        this.color_aparente_agua = color_aparente_agua;
    }

    public int getMetodologia_id() {
        return metodologia_id;
    }

    public void setMetodologia_id(int metodologia_id) {
        this.metodologia_id = metodologia_id;
    }

    public float getLongitud_muestreo() {
        return longitud_muestreo;
    }

    public void setLongitud_muestreo(float longitud_muestreo) {
        this.longitud_muestreo = longitud_muestreo;
    }

    public float getAncho_muestreo() {
        return ancho_muestreo;
    }

    public void setAncho_muestreo(float ancho_muestreo) {
        this.ancho_muestreo = ancho_muestreo;
    }

    public float getArea_muestreo() {
        return area_muestreo;
    }

    public void setArea_muestreo(float area_muestreo) {
        this.area_muestreo = area_muestreo;
    }

    public float getVelocidad_corriente() {
        return velocidad_corriente;
    }

    public void setVelocidad_corriente(float velocidad_corriente) {
        this.velocidad_corriente = velocidad_corriente;
    }

    public float getProfundidad_maxima_muestreo() {
        return profundidad_maxima_muestreo;
    }

    public void setProfundidad_maxima_muestreo(float profundidad_maxima_muestreo) {
        this.profundidad_maxima_muestreo = profundidad_maxima_muestreo;
    }

    public float getProfundidad_maxima_sector() {
        return profundidad_maxima_sector;
    }

    public void setProfundidad_maxima_sector(float profundidad_maxima_sector) {
        this.profundidad_maxima_sector = profundidad_maxima_sector;
    }

    public String getVegetacion_emergente() {
        return vegetacion_emergente;
    }

    public void setVegetacion_emergente(String vegetacion_emergente) {
        this.vegetacion_emergente = vegetacion_emergente;
    }

    public String getVegetacion_sumergida() {
        return vegetacion_sumergida;
    }

    public void setVegetacion_sumergida(String vegetacion_sumergida) {
        this.vegetacion_sumergida = vegetacion_sumergida;
    }

    public String getVegetacion_flotante() {
        return vegetacion_flotante;
    }

    public void setVegetacion_flotante(String vegetacion_flotante) {
        this.vegetacion_flotante = vegetacion_flotante;
    }

    public float getHabitat_porcentaje_long_caida() {
        return habitat_porcentaje_long_caida;
    }

    public void setHabitat_porcentaje_long_caida(float habitat_porcentaje_long_caida) {
        this.habitat_porcentaje_long_caida = habitat_porcentaje_long_caida;
    }

    public float getHabitat_porcentaje_long_rifle() {
        return habitat_porcentaje_long_rifle;
    }

    public void setHabitat_porcentaje_long_rifle(float habitat_porcentaje_long_rifle) {
        this.habitat_porcentaje_long_rifle = habitat_porcentaje_long_rifle;
    }

    public float getHabitat_porcentaje_long_corridas() {
        return habitat_porcentaje_long_corridas;
    }

    public void setHabitat_porcentaje_long_corridas(float habitat_porcentaje_long_corridas) {
        this.habitat_porcentaje_long_corridas = habitat_porcentaje_long_corridas;
    }

    public float getHabitat_porcentaje_long_pozos() {
        return habitat_porcentaje_long_pozos;
    }

    public void setHabitat_porcentaje_long_pozos(float habitat_porcentaje_long_pozos) {
        this.habitat_porcentaje_long_pozos = habitat_porcentaje_long_pozos;
    }

    public float getHabitat_porcentaje_long_remanso() {
        return habitat_porcentaje_long_remanso;
    }

    public void setHabitat_porcentaje_long_remanso(float habitat_porcentaje_long_remanso) {
        this.habitat_porcentaje_long_remanso = habitat_porcentaje_long_remanso;
    }

    public float getSustrato_porcentaje_arena() {
        return sustrato_porcentaje_arena;
    }

    public void setSustrato_porcentaje_arena(float sustrato_porcentaje_arena) {
        this.sustrato_porcentaje_arena = sustrato_porcentaje_arena;
    }

    public float getSustrato_porcentaje_arcilla() {
        return sustrato_porcentaje_arcilla;
    }

    public void setSustrato_porcentaje_arcilla(float sustrato_porcentaje_arcilla) {
        this.sustrato_porcentaje_arcilla = sustrato_porcentaje_arcilla;
    }

    public float getSustrato_porcentaje_limo() {
        return sustrato_porcentaje_limo;
    }

    public void setSustrato_porcentaje_limo(float sustrato_porcentaje_limo) {
        this.sustrato_porcentaje_limo = sustrato_porcentaje_limo;
    }

    public float getSustrato_porcentaje_grava() {
        return sustrato_porcentaje_grava;
    }

    public void setSustrato_porcentaje_grava(float sustrato_porcentaje_grava) {
        this.sustrato_porcentaje_grava = sustrato_porcentaje_grava;
    }

    public float getSustrato_porcentaje_organico_hojarasca() {
        return sustrato_porcentaje_organico_hojarasca;
    }

    public void setSustrato_porcentaje_organico_hojarasca(float sustrato_porcentaje_organico_hojarasca) {
        this.sustrato_porcentaje_organico_hojarasca = sustrato_porcentaje_organico_hojarasca;
    }

    public float getSustrato_porcentaje_organico_ramas() {
        return sustrato_porcentaje_organico_ramas;
    }

    public void setSustrato_porcentaje_organico_ramas(float sustrato_porcentaje_organico_ramas) {
        this.sustrato_porcentaje_organico_ramas = sustrato_porcentaje_organico_ramas;
    }

    public float getSustrato_porcentaje_organico_arbustos_enraizados() {
        return sustrato_porcentaje_organico_arbustos_enraizados;
    }

    public void setSustrato_porcentaje_organico_arbustos_enraizados(float sustrato_porcentaje_organico_arbustos_enraizados) {
        this.sustrato_porcentaje_organico_arbustos_enraizados = sustrato_porcentaje_organico_arbustos_enraizados;
    }

    public int getUnidad_vegetacion_id() {
        return unidad_vegetacion_id;
    }

    public void setUnidad_vegetacion_id(int unidad_vegetacion_id) {
        this.unidad_vegetacion_id = unidad_vegetacion_id;
    }

    public String getVegetacion_circundante() {
        return vegetacion_circundante;
    }

    public void setVegetacion_circundante(String vegetacion_circundante) {
        this.vegetacion_circundante = vegetacion_circundante;
    }

    public int getClase_id() {
        return clase_id;
    }

    public void setClase_id(int clase_id) {
        this.clase_id = clase_id;
    }

    public int getOrden_id() {
        return orden_id;
    }

    public void setOrden_id(int orden_id) {
        this.orden_id = orden_id;
    }

    public int getFamilia_id() {
        return familia_id;
    }

    public void setFamilia_id(int familia_id) {
        this.familia_id = familia_id;
    }

    public int getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(int genero_id) {
        this.genero_id = genero_id;
    }

    public int getEspecie_id() {
        return especie_id;
    }

    public void setEspecie_id(int especie_id) {
        this.especie_id = especie_id;
    }

    public String getNombre_comun() {
        return nombre_comun;
    }

    public void setNombre_comun(String nombre_comun) {
        this.nombre_comun = nombre_comun;
    }

    public int getIndividuos() {
        return individuos;
    }

    public void setIndividuos(int individuos) {
        this.individuos = individuos;
    }

    public String getUicn() {
        return uicn;
    }

    public void setUicn(String uicn) {
        this.uicn = uicn;
    }

    public String getCites() {
        return cites;
    }

    public void setCites(String cites) {
        this.cites = cites;
    }

    public String getDsn() {
        return dsn;
    }

    public void setDsn(String dsn) {
        this.dsn = dsn;
    }

    public float getNivel_trofico_fishbase() {
        return nivel_trofico_fishbase;
    }

    public void setNivel_trofico_fishbase(float nivel_trofico_fishbase) {
        this.nivel_trofico_fishbase = nivel_trofico_fishbase;
    }

    public int getHabito_alimenticio_id() {
        return habito_alimenticio_id;
    }

    public void setHabito_alimenticio_id(int habito_alimenticio_id) {
        this.habito_alimenticio_id = habito_alimenticio_id;
    }

    public int getUso_id() {
        return uso_id;
    }

    public void setUso_id(int uso_id) {
        this.uso_id = uso_id;
    }

    public int getCategoria_abundancia_id() {
        return categoria_abundancia_id;
    }

    public void setCategoria_abundancia_id(int categoria_abundancia_id) {
        this.categoria_abundancia_id = categoria_abundancia_id;
    }

    public int getIndicador_id() {
        return indicador_id;
    }

    public void setIndicador_id(int indicador_id) {
        this.indicador_id = indicador_id;
    }

    public String getEndemismo() {
        return endemismo;
    }

    public void setEndemismo(String endemismo) {
        this.endemismo = endemismo;
    }

    public String getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public float getLongitud_total() {
        return longitud_total;
    }

    public void setLongitud_total(float longitud_total) {
        this.longitud_total = longitud_total;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getHabitat_id() {
        return habitat_id;
    }

    public void setHabitat_id(int habitat_id) {
        this.habitat_id = habitat_id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getEspecialista_id() {
        return especialista_id;
    }

    public void setEspecialista_id(int especialista_id) {
        this.especialista_id = especialista_id;
    }

    public int getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(int proyecto_id) {
        this.proyecto_id = proyecto_id;
    }
}
