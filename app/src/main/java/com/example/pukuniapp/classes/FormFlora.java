package com.example.pukuniapp.classes;

import java.util.Date;

public class FormFlora {
    private int id;
    private int lugar_id;
    private String fecha;
    private int franja_id;
    private int unidad_muestreo_id;
    private int parcela_id;
    private int forofito_id;
    private int sub_parcela_id;
    private String tamanio;
    private String codigo_placa;
    private int unidad_vegetacion_id;
    private int pais_id;
    private int departamento_id;
    private int provincia_id;
    private int distrito_id;
    private String localidad;
    private Double este;
    private Double norte;
    private Double altitud;
    private int clase_id;
    private int orden_id;
    private int familia_id;
    private int genero_id;
    private int especie_id;
    private String nombre_comun;
    private int autor_id;
    private int individuos;
    private Double dap;
    private Double altura;
    private Double valor_cobertura;
    private int habito_id;
    private int estadio_id;
    private int fenologia_id;
    private String usos;
    private String observaciones;
    private String datosPlanta;
    private boolean formularioEnviado;
    private int estacion_muestreo_id;
    private String imageUri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public int getLugar_id() {
        return lugar_id;
    }

    public void setLugar_id(int lugar_id) {
        this.lugar_id = lugar_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getFranja_id() {
        return franja_id;
    }

    public void setFranja_id(int franja_id) {
        this.franja_id = franja_id;
    }

    public int getUnidad_muestreo_id() {
        return unidad_muestreo_id;
    }

    public void setUnidad_muestreo_id(int unidad_muestreo_id) {
        this.unidad_muestreo_id = unidad_muestreo_id;
    }

    public int getParcela_id() {
        return parcela_id;
    }

    public void setParcela_id(int parcela_id) {
        this.parcela_id = parcela_id;
    }

    public int getForofito_id() {
        return forofito_id;
    }

    public void setForofito_id(int forofito_id) {
        this.forofito_id = forofito_id;
    }

    public int getSub_parcela_id() {
        return sub_parcela_id;
    }

    public void setSub_parcela_id(int sub_parcela_id) {
        this.sub_parcela_id = sub_parcela_id;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public int getUnidad_vegetacion_id() {
        return unidad_vegetacion_id;
    }

    public void setUnidad_vegetacion_id(int unidad_vegetacion_id) {
        this.unidad_vegetacion_id = unidad_vegetacion_id;
    }

    public int getPais_id() {
        return pais_id;
    }

    public void setPais_id(int pais_id) {
        this.pais_id = pais_id;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public int getProvincia_id() {
        return provincia_id;
    }

    public void setProvincia_id(int provincia_id) {
        this.provincia_id = provincia_id;
    }

    public int getDistrito_id() {
        return distrito_id;
    }

    public void setDistrito_id(int distrito_id) {
        this.distrito_id = distrito_id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Double getEste() {
        return este;
    }

    public void setEste(Double este) {
        this.este = este;
    }

    public Double getNorte() {
        return norte;
    }

    public void setNorte(Double norte) {
        this.norte = norte;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
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

    public int getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }

    public int getIndividuos() {
        return individuos;
    }

    public void setIndividuos(int individuos) {
        this.individuos = individuos;
    }

    public Double getDap() {
        return dap;
    }

    public void setDap(Double dap) {
        this.dap = dap;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getValor_cobertura() {
        return valor_cobertura;
    }

    public void setValor_cobertura(Double valor_cobertura) {
        this.valor_cobertura = valor_cobertura;
    }

    public int getHabito_id() {
        return habito_id;
    }

    public void setHabito_id(int habito_id) {
        this.habito_id = habito_id;
    }

    public int getEstadio_id() {
        return estadio_id;
    }

    public void setEstadio_id(int estadio_id) {
        this.estadio_id = estadio_id;
    }

    public int getFenologia_id() {
        return fenologia_id;
    }

    public void setFenologia_id(int fenologia_id) {
        this.fenologia_id = fenologia_id;
    }

    public String getUsos() {
        return usos;
    }

    public void setUsos(String usos) {
        this.usos = usos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDatosPlanta() {
        return datosPlanta;
    }

    public void setDatosPlanta(String datosPlanta) {
        this.datosPlanta = datosPlanta;
    }

    public boolean isFormularioEnviado() {
        return formularioEnviado;
    }

    public void setFormularioEnviado(boolean formularioEnviado) {
        this.formularioEnviado = formularioEnviado;
    }

    public int getEstacion_muestreo_id() {
        return estacion_muestreo_id;
    }

    public void setEstacion_muestreo_id(int estacion_muestreo_id) {
        this.estacion_muestreo_id = estacion_muestreo_id;
    }

    public String getCodigo_placa() {
        return codigo_placa;
    }

    public void setCodigo_placa(String codigo_placa) {
        this.codigo_placa = codigo_placa;
    }
}
