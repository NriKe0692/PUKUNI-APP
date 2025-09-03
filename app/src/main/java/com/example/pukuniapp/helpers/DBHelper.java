package com.example.pukuniapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pukuni_db.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_FORMULARIO_FLORA = "form_flora";
    public static final String TABLE_FORMULARIO_ORNITOFAUNA = "form_ornitofauna";
    public static final String TABLE_FORMULARIO_QUIROPTEROS = "form_quiropteros";
    public static final String TABLE_FORMULARIO_ROEDORES = "form_roedores";
    public static final String TABLE_FORMULARIO_HERPETOLOGIA = "form_herpetologia";
    public static final String TABLE_FORMULARIO_HIDROBIOLOGIA = "form_hidrobiologia";
    public static final String TABLE_AUTOR = "autor";
    public static final String TABLE_CLASE = "clase";
    public static final String TABLE_DEPARTAMENTO = "departamento";
    public static final String TABLE_DISTRITO = "distrito";
    public static final String TABLE_ESPECIE = "especie";
    public static final String TABLE_ESTACION_MUESTREO = "estacion_muestreo";
    public static final String TABLE_ESTADIO = "estadio";
    public static final String TABLE_FAMILIA = "familia";
    public static final String TABLE_FENOLOGIA = "fenologia";
    public static final String TABLE_FOROFITO = "forofito";
    public static final String TABLE_FRANJA = "franja";
    public static final String TABLE_GENERO = "genero";
    public static final String TABLE_HABITO = "habito";
    public static final String TABLE_NOMBRE_COMUN = "nombre_comun";
    public static final String TABLE_ORDEN = "orden";
    public static final String TABLE_PAIS = "pais";
    public static final String TABLE_PARCELA = "parcela";
    public static final String TABLE_PROVINCIA = "provincia";
    public static final String TABLE_PROYECTO = "proyecto";
    public static final String TABLE_SUB_PARCELA = "subparcela";
    public static final String TABLE_UNIDAD_MUESTREO = "unidad_muestreo";
    public static final String TABLE_UNIDAD_VEGETACION = "unidad_vegetacion";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_TEMPORADA_EVALUACION = "temporada_evaluacion";
    public static final String TABLE_ZONA = "zona";
    public static final String TABLE_METODOLOGIA = "metodologia";
    public static final String TABLE_CLIMA = "clima";
    public static final String TABLE_UNIDAD_MUESTREAL = "unidad_muestreal";
    public static final String TABLE_TIPO_REGISTRO = "tipo_registro";
    public static final String TABLE_CATEGORIA_ABUNDANCIA = "categoria_abundancia";
    public static final String TABLE_GRUPO_TROFICO = "grupo_trofico";
    public static final String TABLE_INDICADOR = "indicador";
    public static final String TABLE_ESTADO_CONSERVACION = "estado_conservacion";
    public static final String TABLE_CONDICION_REPRODUCTIVA = "condicion_reproductiva";
    public static final String TABLE_TIPO_TRAMPA = "tipo_trampa";
    public static final String TABLE_USOS = "usos";
    public static final String TABLE_ACTIVIDAD = "actividad";
    public static final String TABLE_SUSTRATO = "sustrato";
    public static final String TABLE_MICROHABITAT = "microhabitat";
    public static final String TABLE_CUENCA_HIDROGRAFICA = "cuenca_hidrografica";
    public static final String TABLE_TIPO_AMBIENTE_ACUATICO = "tipo_ambiente_acuatico";
    public static final String TABLE_ESTACION = "estacion";
    public static final String TABLE_PUNTO_MUESTREO = "punto_muestreo";
    public static final String TABLE_VEGETACION_CIRCUNDANTE = "vegetacion_circundante";
    public static final String TABLE_HABITO_ALIMENTICIO = "habito_alimenticio";
    public static final String TABLE_HABITAT = "habitat";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAutorTable(db);
        createClaseTable(db);
        createDepartamentoTable(db);
        createDistritoTable(db);
        createEspecieTable(db);
        createEstacionMuestreoTable(db);
        createEstadioTable(db);
        createFamiliaTable(db);
        createFenologiaTable(db);
        createForofitoTable(db);
        createFranjaTable(db);
        createGeneroTable(db);
        createHabitoTable(db);
        createNombreComunTable(db);
        createOrdenTable(db);
        createPaisTable(db);
        createParcelaTable(db);
        createProvinciaTable(db);
        createProyectoTable(db);
        createSubParcelaTable(db);
        createUnidadMuestreoTable(db);
        createUnidadVegetacionTable(db);
        createUserTable(db);
        createTemporadaEvaluacionTable(db);
        createZonaTable(db);
        createMetodologiaTable(db);
        createClimaTable(db);
        createUnidadMuestrealTable(db);
        createTipoRegistroTable(db);
        createCategoriaAbundanciaTable(db);
        createGruposTroficosTable(db);
        createIndicadorTable(db);
        createEstadoConservacionTable(db);
        createCondicionReproductivaTable(db);
        createTipoTrampaTable(db);
        createUsosTable(db);
        createActividadTable(db);
        createSustratoTable(db);
        createMicrohabitatTable(db);
        createCuencaHidrograficaTable(db);
        createTipoAmbienteAcuaticoTable(db);
        createEstacionTable(db);
        createPuntoMuestreoTable(db);
        createHabitoAlimenticioTable(db);
        createHabitatTable(db);

        createFormFloraTable(db);
        createFormOrnitofaunaTable(db);
        createFormQuiropterosTable(db);
        createFormRoedoresTable(db);
        createFormHerpetologiaTable(db);
        createFormHidrobiologiaTable(db);
    }

    private void createAutorTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_AUTOR + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "autor_id INTEGER, " +
            "autor_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createUsosTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_USOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usos_id INTEGER, " +
                "tipo_form_id INTEGER, " +
                "usos_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createActividadTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_ACTIVIDAD + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "actividad_id INTEGER, " +
                "actividad_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createSustratoTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_SUSTRATO + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sustrato_id INTEGER, " +
                "sustrato_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createMicrohabitatTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_MICROHABITAT + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "microhabitat_id INTEGER, " +
            "microhabitat_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createCuencaHidrograficaTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_CUENCA_HIDROGRAFICA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cuenta_hidrografica_id INTEGER, " +
                "cuenta_hidrografica_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createTipoAmbienteAcuaticoTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_TIPO_AMBIENTE_ACUATICO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tipo_ambiente_acuatico_id INTEGER, " +
            "tipo_ambiente_acuatico_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createEstacionTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_ESTACION + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "estacion_id INTEGER, " +
                "estacion_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createPuntoMuestreoTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_PUNTO_MUESTREO + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "punto_muestreo_id INTEGER, " +
                "estacion_id INTEGER, " +
                "punto_muestreo_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createVegetacionCircundanteTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_VEGETACION_CIRCUNDANTE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "vegetacion_circundante_id INTEGER, " +
                "vegetacion_circundante_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createHabitoAlimenticioTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_HABITO_ALIMENTICIO + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "habito_alimenticio_id INTEGER, " +
                "habito_alimenticio_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createHabitatTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_HABITAT + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "habitat_id INTEGER, " +
                "habitat_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createEstadoConservacionTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_ESTADO_CONSERVACION + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "estado_conservacion_habitat_id INTEGER, " +
            "estado_conservacion_habitat_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createCondicionReproductivaTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_CONDICION_REPRODUCTIVA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "condicion_reproductiva_id INTEGER, " +
            "condicion_reproductiva_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createTipoTrampaTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_TIPO_TRAMPA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tipo_trampa_id INTEGER, " +
            "tipo_trampa_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createClaseTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_CLASE + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "clase_id INTEGER, " +
            "clase_name TEXT," +
            "tipo_form_id INTEGER)";
        db.execSQL(sqlQuery);
    }

    private void createDepartamentoTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_DEPARTAMENTO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "departamento_id INTEGER, " +
            "pais_id INTEGER, " +
            "departamento_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createDistritoTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_DISTRITO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "distrito_id INTEGER, " +
            "provincia_id INTEGER, " +
            "distrito_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createEspecieTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_ESPECIE + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "especie_id INTEGER, " +
            "genero_id INTEGER, " +
            "especie_name TEXT," +
            "tipo_form_id INTEGER)";
        db.execSQL(sqlQuery);
    }

    private void createEstacionMuestreoTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_ESTACION_MUESTREO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "estacion_muestreo_id INTEGER, " +
            "estacion_muestreo_name TEXT, " +
            "pais_id INTEGER, " +
            "pais_name TEXT, " +
            "departamento_id INTEGER, " +
            "provincia_id INTEGER, " +
            "distrito_id INTEGER, " +
            "departamento_name TEXT, " +
            "provincia_name TEXT, " +
            "distrito_name TEXT) ";
        db.execSQL(sqlQuery);
    }

    private void createEstadioTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_ESTADIO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "estadio_id INTEGER, " +
            "estadio_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createFamiliaTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_FAMILIA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "familia_id INTEGER, " +
            "orden_id INTEGER, " +
            "familia_name TEXT, " +
            "tipo_form_id INTEGER)";
        db.execSQL(sqlQuery);
    }

    private void createFenologiaTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_FENOLOGIA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "fenologia_id INTEGER, " +
            "fenologia_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createForofitoTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_FOROFITO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "forofito_id INTEGER, " +
            "parcela_id INTEGER, " +
            "parcela_name TEXT, " +
            "forofito_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createFranjaTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_FRANJA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "franja_id INTEGER, " +
            "estacion_muestreo_id INTEGER, " +
            "franja_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createGeneroTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_GENERO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "genero_id INTEGER, " +
            "familia_id INTEGER, " +
            "genero_name TEXT, " +
            "tipo_form_id INTEGER)";
        db.execSQL(sqlQuery);
    }
    private void createHabitoTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_HABITO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "habito_id INTEGER, " +
            "habito_name TEXT, " +
            "tipo_form_id INTEGER)";
        db.execSQL(sqlQuery);
    }

    private void createNombreComunTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NOMBRE_COMUN + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre_comun_id INTEGER, " +
                "nombre_comun_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createGruposTroficosTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_GRUPO_TROFICO + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "grupo_trofico_id INTEGER, " +
                "grupo_trofico_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createIndicadorTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_INDICADOR + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "indicador_id INTEGER, " +
                "indicador_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createOrdenTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_ORDEN + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "orden_id INTEGER, " +
            "clase_id INTEGER, " +
            "orden_name TEXT, " +
            "tipo_form_id INTEGER)";
        db.execSQL(sqlQuery);
    }

    private void createPaisTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_PAIS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "pais_id INTEGER, " +
            "pais_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createParcelaTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_PARCELA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "parcela_id INTEGER, " +
            "franja_id INTEGER, " +
            "franja_name TEXT, " +
            "parcela_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createProvinciaTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_PROVINCIA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "provincia_id INTEGER, " +
            "departamento_id INTEGER, " +
            "provincia_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createProyectoTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_PROYECTO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "proyecto_id INTEGER, " +
            "pais_id INTEGER, " +
            "departamento_id INTEGER, " +
            "provincia_id INTEGER, " +
            "distrito_id INTEGER, " +
            "proyecto_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createSubParcelaTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_SUB_PARCELA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "subparcela_id INTEGER, " +
            "parcela_id INTEGER, " +
            "subparcela_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createUnidadMuestreoTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_UNIDAD_MUESTREO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "unidad_muestreo_id INTEGER, " +
            "unidad_muestreo_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createUnidadVegetacionTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_UNIDAD_VEGETACION + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "unidad_vegetacion_id INTEGER, " +
            "unidad_vegetacion_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createUserTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_USERS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_id INTEGER, " +
            "user_name TEXT, " +
            "user_lastname TEXT, " +
            "password_hash TEXT, " +
            "status BOOLEAN, " +
            "date_created DATE, " +
            "email TEXT, " +
            "role_id INTEGER)";
        db.execSQL(sqlQuery);
    }

    private void createTemporadaEvaluacionTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_TEMPORADA_EVALUACION + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "temporada_evaluacion_id INTEGER, " +
            "temporada_evaluacion_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createZonaTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_ZONA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "zona_id INTEGER, " +
            "zona_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createMetodologiaTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_METODOLOGIA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "metodologia_id INTEGER, " +
            "tipo_formulario_id INTEGER, " +
            "metodologia_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createClimaTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_CLIMA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "clima_id INTEGER, " +
            "clima_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createTipoRegistroTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_TIPO_REGISTRO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tipo_registro_id INTEGER, " +
            "tipo_registro_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createCategoriaAbundanciaTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_CATEGORIA_ABUNDANCIA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "categoria_abundancia_id INTEGER, " +
                "categoria_abundancia_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createUnidadMuestrealTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_UNIDAD_MUESTREAL + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "unidad_muestreal_id INTEGER, " +
            "unidad_muestreal_name TEXT, " +
            "franja_id INTEGER, " +
            "metodologia_id INTEGER)";
        db.execSQL(sqlQuery);
    }

    private void createFormFloraTable(SQLiteDatabase db){
        String createTableForm = "CREATE TABLE " + TABLE_FORMULARIO_FLORA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "estacion_muestreo_id INTEGER," +
            "lugar_id INTEGER, " +
            "fecha DATE," +
            "franja_id INTEGER," +
            "unidad_muestreo_id INTEGER," +
            "parcela_id INTEGER," +
            "forofito_id INTEGER," +
            "sub_parcela_id INTEGER," +
            "tamanio TEXT," +
            "codigo_placa TEXT," +
            "unidad_vegetacion_id INTEGER," +
            "pais_id INTEGER," +
            "departamento_id INTEGER," +
            "provincia_id INTEGER," +
            "distrito_id INTEGER," +
            "localidad TEXT," +
            "este REAL," +
            "norte REAL," +
            "altitud REAL," +
            "clase_id INTEGER," +
            "orden_id INTEGER," +
            "familia_id INTEGER," +
            "genero_id INTEGER," +
            "especie_id INTEGER," +
            "nombre_comun TEXT," +
            "autor_id INTEGER," +
            "individuos INTEGER," +
            "dap REAL," +
            "altura REAL," +
            "valor_cobertura REAL," +
            "habito_id INTEGER," +
            "estadio_id INTEGER," +
            "fenologia_id INTEGER," +
            "uso_id INTEGER," +
            "image_uri TEXT," +
            "observaciones TEXT," +
            "especialista_id INTEGER," +
            "proyecto_id INTEGER," +
            "datos_planta TEXT)";
        db.execSQL(createTableForm);
    }

    private void createFormOrnitofaunaTable(SQLiteDatabase db){
        String createTableForm = "CREATE TABLE " + TABLE_FORMULARIO_ORNITOFAUNA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "franja_id INTEGER, " +
                "temporada_evaluacion_id INTEGER," +
                "estacion_muestreo_id INTEGER," +
                "metodologia_id INTEGER," +
                "unidad_muestreal_id INTEGER," +
                "unidad_vegetacion_id INTEGER," +
                "zona_id INTEGER," +
                "este REAL," +
                "norte REAL ," +
                "altitud REAL ," +
                "fecha DATE," +
                "hora TIME," +
                "clima_id INTEGER," +
                "clase_id INTEGER," +
                "orden_id INTEGER," +
                "familia_id INTEGER," +
                "genero_id INTEGER," +
                "especie_id INTEGER," +
                "nombre_comun TEXT," +
                "nombre_local TEXT," +
                "numero_individuos INTEGER," +
                "tipo_registro_id INTEGER," +
                "categoria_abundancia_id INTEGER ," +
                "habito_id INTEGER ," +
                "grupo_trofico_id INTEGER ," +
                "indicador_id INTEGER ," +
                "largo_cola REAL," +
                "largo_pata REAL," +
                "longitud_total REAL," +
                "peso REAL," +
                "estadio_id INTEGER," +
                "sexo TEXT," +
                "uicn TEXT," +
                "cites TEXT," +
                "ibas TEXT," +
                "usos TEXT," +
                "comentario TEXT," +
                "image_uri TEXT," +
                "especialista_id INTEGER," +
                "proyecto_id INTEGER," +
                "estado_conservacion_id INTEGER)";
        db.execSQL(createTableForm);
    }

    private void createFormHidrobiologiaTable(SQLiteDatabase db){
        String createTableForm = "CREATE TABLE " + TABLE_FORMULARIO_HIDROBIOLOGIA + " (" +
                "id INTEGER," +
                "especialista_id INTEGER," +
                "proyecto_id INTEGER," +
                "localidad TEXT," +
                "cuenca_hidrografica_id INTEGER," +             // THIS
                "estacion_muestreo_id INTEGER," +
                "temporada_evaluacion_id INTEGER," +
                "tipo_ambiente_acuatico_id INTEGER," +          // THIS
                "estacion_id INTEGER," +                        // THIS
                "punto_muestreo_id INTEGER," +                  // THIS
                "este REAL," +
                "norte REAL," +
                "altitud REAL," +
                "fecha TEXT," +
                "hora TEXT," +
                "pendiente_cauce TEXT," +
                "clima_id INTEGER," +
                "ancho_cauce_sector REAL," +
                "tipo_orilla TEXT," +
                "tipo_agua TEXT," +
                "color_aparente_agua TEXT," +
                "metodologia_id INTEGER," +
                "longitud_muestreo REAL," +
                "ancho_muestreo REAL," +
                "area_muestreo REAL," +
                "velocidad_corriente REAL," +
                "profundidad_maxima_muestreo REAL," +
                "profundidad_maxima_sector REAL," +
                "transparencia REAL," +
                "vegetacion_emergente TEXT," +
                "vegetacion_sumergida TEXT," +
                "vegetacion_flotante TEXT," +
                "habitat_porcentaje_long_caida REAL," +
                "habitat_porcentaje_long_rifle REAL," +
                "habitat_porcentaje_long_corridas REAL," +
                "habitat_porcentaje_long_pozos REAL," +
                "habitat_porcentaje_long_remanso REAL," +
                "sustrato_porcentaje_arena REAL," +
                "sustrato_porcentaje_arcilla REAL," +
                "sustrato_porcentaje_limo REAL," +
                "sustrato_porcentaje_grava REAL," +
                "sustrato_porcentaje_organico_hojarasca REAL," +
                "sustrato_porcentaje_organico_ramas REAL," +
                "sustrato_porcentaje_organico_arbustos_enraizados REAL," +
                "unidad_vegetacion_id INTEGER," +
                "vegetacion_circundante TEXT," +
                "clase_id INTEGER," +
                "orden_id INTEGER," +
                "familia_id INTEGER," +
                "genero_id INTEGER," +
                "especie_id INTEGER," +
                "nombre_comun INTEGER," +
                "individuos INTEGER," +
                "uicn TEXT," +
                "cites TEXT," +
                "dsn TEXT," +
                "nivel_trofico_fishbase REAL," +
                "habito_alimenticio_id INTEGER," +              // THIS
                "uso_id INTEGER," +
                "categoria_abundancia_id INTEGER," +
                "indicador_id INTEGER," +
                "endemismo TEXT," +
                "comportamiento TEXT," +
                "longitud_total REAL," +
                "peso REAL," +
                "habitat_id INTEGER," +                         // THIS
                "etapa_reproductiva TEXT," +
                "comentario TEXT," +
                "img_uri TEXT)";
        db.execSQL(createTableForm);
    }

    private void createFormHerpetologiaTable(SQLiteDatabase db){
        String createTableForm = "CREATE TABLE " + TABLE_FORMULARIO_HERPETOLOGIA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "estacion_muestreo_id INTEGER," +
            "temporada_evaluacion_id INTEGER," +
            "unidad_vegetacion_id INTEGER," +
            "franja_id INTEGER," +
            "fecha DATE," +
            "clima_id INTEGER," +
            "tipo_registro_id INTEGER," +
            "metodologia_id INTEGER," +
            "unidad_muestreal TEXT," +
            "clase_id INTEGER," +
            "orden_id INTEGER," +
            "familia_id INTEGER," +
            "genero_id INTEGER," +
            "especie_id INTEGER," +
            "nombre_comun TEXT," +
            "numero_individuos INTEGER," +
            "edad REAL," +
            "sexo TEXT," +
            "sustrato_id INTEGER," +
            "microhabitat_id INTEGER," +
            "actividad_id INTEGER," +
            "categoria_abundancia_id INTEGER ," +
            "habito_id INTEGER ," +
            "grupo_trofico_id INTEGER ," +
            "este REAL," +
            "norte REAL ," +
            "altitud REAL ," +
            "indicador_id INTEGER ," +
            "uicn TEXT," +
            "cites TEXT," +
            "dsn TEXT," +
            "libro_rojo TEXT," +
            "endemismo TEXT," +
            "distribucion_endemismo TEXT," +
            "usos TEXT," +
            "comentario TEXT," +
            "image_uri TEXT," +
            "proyecto_id INTEGER," +
            "estado_conservacion_habitat_id INTEGER," +
            "especialista_id INTEGER)";
        db.execSQL(createTableForm);
    }

    private void createFormRoedoresTable(SQLiteDatabase db){
        String createTableForm = "CREATE TABLE " + TABLE_FORMULARIO_ROEDORES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "estacion_muestreo_id INTEGER," +
                "temporada_evaluacion_id INTEGER," +
                "unidad_vegetacion_id INTEGER," +
                "fecha DATE," +
                "hora TIME," +
                "clima_id INTEGER," +
                "franja_id INTEGER," +
                "metodologia_id INTEGER," +
                "tipo_trampa_id INTEGER," +
                "unidad_muestreal_id INTEGER," +
                "este REAL," +
                "norte REAL," +
                "altitud REAL," +
                "clase_id INTEGER," +
                "orden_id INTEGER," +
                "familia_id INTEGER," +
                "genero_id INTEGER," +
                "especie_id INTEGER," +
                "nombre_comun TEXT," +
                "numero_individuos INTEGER," +
                "estadio_id INTEGER," +
                "sexo TEXT," +
                "condicion_reproductiva_id INTEGER," +
                "categoria_abundancia_id INTEGER," +
                "habito_id INTEGER," +
                "grupo_trofico_id INTEGER," +
                "longitud_cuerpo REAL," +
                "longitud_oreja REAL," +
                "longitud_cola REAL," +
                "longitud_pata REAL," +
                "indicador_id INTEGER," +
                "uicn TEXT," +
                "cites TEXT," +
                "dsn TEXT," +
                "libro_rojo TEXT," +
                "endemismo TEXT," +
                "distribucion_endemismo TEXT," +
                "comentario TEXT," +
                "image_uri TEXT," +
                "uso_id INTEGER," +
                "estado_conservacion_id INTEGER," +
                "proyecto_id INTEGER," +
                "especialista_id INTEGER)";
        db.execSQL(createTableForm);
    }

    private void createFormQuiropterosTable(SQLiteDatabase db){
        String createTableForm = "CREATE TABLE " + TABLE_FORMULARIO_QUIROPTEROS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "estacion_muestreo_id INTEGER, " +
                "temporada_evaluacion_id INTEGER, " +
                "unidad_vegetacion_id INTEGER, " +
                "fecha DATE," +
                "hora TIME," +
                "clima_id INTEGER, " +
                "franja_id INTEGER, " +
                "metodologia_id INTEGER, " +
                "tipo_trampa_id INTEGER, " +
                "unidad_muestreal_id INTEGER, " +
                "este REAL," +
                "norte REAL ," +
                "altitud REAL ," +
                "clase_id INTEGER," +
                "orden_id INTEGER," +
                "familia_id INTEGER," +
                "genero_id INTEGER," +
                "especie_id INTEGER," +
                "nombre_comun TEXT," +
                "numero_individuos INTEGER," +
                "estadio_id INTEGER," +
                "sexo TEXT," +
                "condicion_reproductiva_id INTEGER," +
                "categoria_abundancia_id INTEGER," +
                "habito_id INTEGER," +
                "grupo_trofico_id INTEGER," +
                "longitud_antebrazo REAL," +
                "longitud_oreja REAL," +
                "longitud_cola REAL," +
                "longitud_tibia REAL," +
                "peso REAL," +
                "indicador_id INTEGER," +
                "uicn TEXT," +
                "cites TEXT," +
                "dsn TEXT," +
                "libro_rojo TEXT," +
                "endemismo TEXT," +
                "distribucion_endemismo TEXT," +
                "ibas TEXT," +
                "ebas TEXT," +
                "comentario TEXT," +
                "image_uri TEXT," +
                "proyecto_id INTEGER," +
                "uso_id INTEGER," +
                "especialista_id INTEGER," +
                "estado_conservacion_id INTEGER)";
        db.execSQL(createTableForm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMULARIO_FLORA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMULARIO_ORNITOFAUNA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMULARIO_QUIROPTEROS);
        onCreate(db);
    }
}
