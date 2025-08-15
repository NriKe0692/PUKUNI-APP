package com.example.pukuniapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pukuniapp.classes.FormFlora;

import java.util.ArrayList;
import java.util.List;

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
            "pais_id INTEGER, " +
            "departamento_id INTEGER, " +
            "provincia_id INTEGER, " +
            "distrito_id INTEGER, " +
            "estacion_muestreo_name TEXT)";
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
                "observaciones TEXT," +
                "datos_planta TEXT)";
        db.execSQL(createTableFloraForm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMULARIO_FLORA);
        onCreate(db);
    }

    // Guardar una persona
    public long insertarFormularioFlora(FormFlora formularioFlora) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lugar_id", formularioFlora.getLugar_id());
        values.put("fecha", formularioFlora.getFecha());
        values.put("franja_id", formularioFlora.getFranja_id());
        values.put("unidad_muestreo_id", formularioFlora.getUnidad_muestreo_id());
        values.put("parcela_id", formularioFlora.getParcela_id());
        values.put("forofito_id", formularioFlora.getForofito_id());
        values.put("sub_parcela_id", formularioFlora.getSub_parcela_id());
        values.put("tamanio", formularioFlora.getTamanio());
        values.put("unidad_vegetacion_id", formularioFlora.getUnidad_vegetacion_id());
        values.put("pais_id", formularioFlora.getPais_id());
        values.put("departamento_id", formularioFlora.getDepartamento_id());
        values.put("provincia_id", formularioFlora.getProvincia_id());
        values.put("distrito_id", formularioFlora.getDistrito_id());
        values.put("localidad", formularioFlora.getLocalidad());
        values.put("este", formularioFlora.getEste());
        values.put("norte", formularioFlora.getNorte());
        values.put("altitud", formularioFlora.getAltitud());
        values.put("clase_id", formularioFlora.getClase_id());
        values.put("orden_id", formularioFlora.getOrden_id());
        values.put("familia_id", formularioFlora.getFamilia_id());
        values.put("genero_id", formularioFlora.getGenero_id());
        values.put("especie_id", formularioFlora.getEspecie_id());
        values.put("nombre_comun", formularioFlora.getNombre_comun());
        values.put("autor_id", formularioFlora.getAutor_id());
        values.put("individuos", formularioFlora.getIndividuos());
        values.put("dap", formularioFlora.getDap());
        values.put("altura", formularioFlora.getAltura());
        values.put("valor_cobertura", formularioFlora.getValor_cobertura());
        values.put("habito_id", formularioFlora.getHabito_id());
        values.put("estadio_id", formularioFlora.getEstadio_id());
        values.put("fenologia_id", formularioFlora.getFenologia_id());
        values.put("usos", formularioFlora.getUsos());
        values.put("observaciones", formularioFlora.getObservaciones());
        values.put("datos_planta", formularioFlora.getDatosPlanta());
        values.put("formulario_enviado", false);
        return db.insert(TABLE_FORMULARIO_FLORA, null, values);
    }

    // Leer todas las personas
    public List<FormFlora> obtenerFormularios() {
        List<FormFlora> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORMULARIO_FLORA, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                int edad = cursor.getInt(cursor.getColumnIndexOrThrow("edad"));
                lista.add(new FormFlora());
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}
