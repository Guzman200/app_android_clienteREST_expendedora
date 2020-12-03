package com.rg.developer.maquinaexpendedora.interfaces;

import com.rg.developer.maquinaexpendedora.models.Venta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VentaAPI {

    @POST("ventas/{producto_id}/{maquina_id}")
    public Call<Venta> generarVenta(@Path("producto_id") int producto_id, @Path("maquina_id") int maquina_id);

    @GET("ventas/{maquina_id}/{fecha}")
    public Call<List<Venta>> getVentasByFecha(@Path("maquina_id") int maquina_id, @Path("fecha") String fecha);
}
