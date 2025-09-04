package com.example.pukuniapp.retrofit;

import com.example.pukuniapp.classes.Actividad;
import com.example.pukuniapp.classes.Autor;
import com.example.pukuniapp.classes.CategoriaAbundancia;
import com.example.pukuniapp.classes.Clase;
import com.example.pukuniapp.classes.Clima;
import com.example.pukuniapp.classes.CondicionReproductiva;
import com.example.pukuniapp.classes.CuencaHidrografica;
import com.example.pukuniapp.classes.Departamento;
import com.example.pukuniapp.classes.Distrito;
import com.example.pukuniapp.classes.Especie;
import com.example.pukuniapp.classes.Estacion;
import com.example.pukuniapp.classes.EstacionMuestreo;
import com.example.pukuniapp.classes.Estadio;
import com.example.pukuniapp.classes.EstadoConservacion;
import com.example.pukuniapp.classes.Familia;
import com.example.pukuniapp.classes.Fenologia;
import com.example.pukuniapp.classes.FormFlora;
import com.example.pukuniapp.classes.FormHidrobiologia;
import com.example.pukuniapp.classes.FormMamiferosGrandes;
import com.example.pukuniapp.classes.Forofito;
import com.example.pukuniapp.classes.Franja;
import com.example.pukuniapp.classes.Genero;
import com.example.pukuniapp.classes.GrupoTrofico;
import com.example.pukuniapp.classes.HabitatPeces;
import com.example.pukuniapp.classes.Habito;
import com.example.pukuniapp.classes.Indicador;
import com.example.pukuniapp.classes.Location;
import com.example.pukuniapp.classes.Metodologia;
import com.example.pukuniapp.classes.Microhabitat;
import com.example.pukuniapp.classes.Orden;
import com.example.pukuniapp.classes.Pais;
import com.example.pukuniapp.classes.Parcela;
import com.example.pukuniapp.classes.Provincia;
import com.example.pukuniapp.classes.PuntoMuestreo;
import com.example.pukuniapp.classes.SubParcela;
import com.example.pukuniapp.classes.Sustrato;
import com.example.pukuniapp.classes.TemporadaEvaluacion;
import com.example.pukuniapp.classes.TipoAmbienteAcuatico;
import com.example.pukuniapp.classes.TipoRegistro;
import com.example.pukuniapp.classes.TipoTrampa;
import com.example.pukuniapp.classes.TipoUsos;
import com.example.pukuniapp.classes.UnidadMuestreal;
import com.example.pukuniapp.classes.UnidadMuestreo;
import com.example.pukuniapp.classes.UnidadVegetacion;
import com.example.pukuniapp.classes.Zona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/users")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @GET("/verify")
    Call<Void> verifyToken(@Header("Authorization") String token);

    @GET("/locations")
    Call<List<Location>> getLocations(@Header("Authorization") String token);

    @GET("/unidades_muestreo")
    Call<List<UnidadMuestreo>> getUnidadMuestreoList(@Header("Authorization") String token);

    @GET("/estaciones_muestreo")
    Call<List<EstacionMuestreo>> getEstacionMuestreoList(@Header("Authorization") String token);

    @GET("/clases")
    Call<List<Clase>> getClases(@Header("Authorization") String token);

    @GET("/ordenes")
    Call<List<Orden>> getOrdenes(@Header("Authorization") String token);

    @GET("/familias")
    Call<List<Familia>> getFamilias(@Header("Authorization") String token);

    @GET("/generos")
    Call<List<Genero>> getGeneros(@Header("Authorization") String token);

    @GET("/especies")
    Call<List<Especie>> getEspecies(@Header("Authorization") String token);

    @GET("/franjas")
    Call<List<Franja>> getFranjas(@Header("Authorization") String token);

    @GET("/parcelas")
    Call<List<Parcela>> getParcelas(@Header("Authorization") String token);

    @GET("/forofitos")
    Call<List<Forofito>> getForofitos(@Header("Authorization") String token);

    @GET("/subparcelas")
    Call<List<SubParcela>> getSubparcelas(@Header("Authorization") String token);

    @GET("/unidad_vegetacion")
    Call<List<UnidadVegetacion>> getUnidadVegetacion(@Header("Authorization") String token);

    @GET("/paises")
    Call<List<Pais>> getPaises(@Header("Authorization") String token);

    @GET("/departamentos")
    Call<List<Departamento>> getDepartamentos(@Header("Authorization") String token);

    @GET("/provincias")
    Call<List<Provincia>> getProvincias(@Header("Authorization") String token);

    @GET("/distritos")
    Call<List<Distrito>> getDistritos(@Header("Authorization") String token);

    @GET("/autores")
    Call<List<Autor>> getAutores(@Header("Authorization") String token);

    @GET("/habitos")
    Call<List<Habito>> getHabitos(@Header("Authorization") String token);

    @GET("/estadios")
    Call<List<Estadio>> getEstadios(@Header("Authorization") String token);

    @GET("/fenologias")
    Call<List<Fenologia>> getFenologias(@Header("Authorization") String token);

    @GET("/temporadas_evaluacion")
    Call<List<TemporadaEvaluacion>> getTemporadasEvaluacion(@Header("Authorization") String token);

    @GET("/zonas")
    Call<List<Zona>> getZonas(@Header("Authorization") String token);

    @GET("/metodologias")
    Call<List<Metodologia>> getMetodologias(@Header("Authorization") String token);

    @GET("/climas")
    Call<List<Clima>> getClimas(@Header("Authorization") String token);

    @GET("/unidad_muestreal")
    Call<List<UnidadMuestreal>> getUnidadesMuestreal(@Header("Authorization") String token);

    @GET("/tipo_registros")
    Call<List<TipoRegistro>> getTipoRegistros(@Header("Authorization") String token);

    @GET("/categoria_abundancia")
    Call<List<CategoriaAbundancia>> getCategoriaXAbundancia(@Header("Authorization") String token);

    @GET("/grupos_troficos")
    Call<List<GrupoTrofico>> getGruposTroficos(@Header("Authorization") String token);

    @GET("/indicadores")
    Call<List<Indicador>> getIndicadores(@Header("Authorization") String token);

    @GET("/estados_conservacion")
    Call<List<EstadoConservacion>> getEstadosConservacion(@Header("Authorization") String token);

    @GET("/condiciones_reproductivas")
    Call<List<CondicionReproductiva>> getCondicionesReproductivas(@Header("Authorization") String token);

    @GET("/tipos_trampa")
    Call<List<TipoTrampa>> getTiposTrampa(@Header("Authorization") String token);

    @GET("/usos")
    Call<List<TipoUsos>> getUsos(@Header("Authorization") String token);

    @GET("/actividades")
    Call<List<Actividad>> getActividades(@Header("Authorization") String token);

    @GET("/sustratos")
    Call<List<Sustrato>> getSustratos(@Header("Authorization") String token);

    @GET("/microhabitats")
    Call<List<Microhabitat>> getMicrohabitat(@Header("Authorization") String token);

    @GET("/tipo_ambientes_acuaticos")
    Call<List<TipoAmbienteAcuatico>> getTipoAmbientesAcuaticos(@Header("Authorization") String token);

    @GET("/estaciones")
    Call<List<Estacion>> getEstaciones(@Header("Authorization") String token);

    @GET("/puntos_muestreo")
    Call<List<PuntoMuestreo>> getPuntosMuestreo(@Header("Authorization") String token);

    @GET("/habitats_peces")
    Call<List<HabitatPeces>> getHabitatsPeces(@Header("Authorization") String token);

    @GET("/cuencas_hidrograficas")
    Call<List<CuencaHidrografica>> getCuencasHidrograficas(@Header("Authorization") String token);

    @POST("/form_mamiferos_grandes")
    Call<PostResponse> postMamiferosGrandes(@Header("Authorization") String token, @Body FormMamiferosGrandes request);

    @POST("/form_hidrobiologia")
    Call<PostResponse> postHidrobiologia(@Header("Authorization") String token, @Body FormHidrobiologia request);
}
