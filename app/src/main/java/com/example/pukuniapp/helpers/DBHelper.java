package com.example.pukuniapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pukuni_db.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_FORMULARIO_FLORA = "flora";
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
    public static final String TABLE_LOCALIDAD = "localidad";
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
        createFormFloraTable(db);
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
    }

    private void createAutorTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_AUTOR + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "autor_id INTEGER, " +
            "autor_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createClaseTable(SQLiteDatabase db){
        String sqlQuery = "CREATE TABLE " + TABLE_CLASE + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "clase_id INTEGER, " +
            "clase_name TEXT)";
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
            "especie_name TEXT)";
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
            "familia_name TEXT)";
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
            "genero_name TEXT)";
        db.execSQL(sqlQuery);
    }
    private void createHabitoTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_HABITO + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "habito_id INTEGER, " +
            "habito_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createNombreComunTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NOMBRE_COMUN + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre_comun_id INTEGER, " +
            "nombre_comun_name TEXT)";
        db.execSQL(sqlQuery);
    }

    private void createOrdenTable(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_ORDEN + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "orden_id INTEGER, " +
            "clase_id INTEGER, " +
            "orden_name TEXT)";
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
        String createTableFloraForm = "CREATE TABLE " + TABLE_FORMULARIO_FLORA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lugar_id INTEGER, " +
                "fecha DATE," +
                "franja_id INTEGER," +
                "unidad_muestreo_id INTEGER," +
                "parcela_id INTEGER," +
                "forofito_id INTEGER," +
                "sub_parcela_id INTEGER," +
                "tamanio TEXT," +
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
                "usos TEXT," +
                "image_uri TEXT," +
                "observaciones TEXT," +
                "datos_planta TEXT)";
        db.execSQL(createTableFloraForm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMULARIO_FLORA);
        onCreate(db);
    }
}
