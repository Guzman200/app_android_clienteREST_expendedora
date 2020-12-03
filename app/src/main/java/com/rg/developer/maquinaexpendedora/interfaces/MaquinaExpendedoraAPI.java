package com.rg.developer.maquinaexpendedora.interfaces;

import com.rg.developer.maquinaexpendedora.ListAdapter;
import com.rg.developer.maquinaexpendedora.models.MaquinaExpendedora;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface MaquinaExpendedoraAPI {

    @GET("maquinas_expendedoras")
    public Call<List<MaquinaExpendedora>> getMaquinas();

    @POST("maquinas_expendedoras")
    public  Call<MaquinaExpendedora> crearMaquinaExpendedora(@Body MaquinaExpendedora maquinaExpendedora);
}
