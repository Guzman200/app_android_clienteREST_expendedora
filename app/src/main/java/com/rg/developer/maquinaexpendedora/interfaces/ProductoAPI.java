package com.rg.developer.maquinaexpendedora.interfaces;

import com.rg.developer.maquinaexpendedora.models.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProductoAPI {

    @GET("productos/{producto_id}")
    public Call<Producto> findById(@Path("producto_id") int producto_id);

    @POST("productos")
    public Call<Producto> crearProducto(@Body Producto producto);

    @PUT("productos")
    public Call<Producto> editarProducto(@Body Producto producto);

    @GET("productos")
    public  Call<List<Producto>> getProductos();



}
