package com.rg.developer.maquinaexpendedora;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rg.developer.maquinaexpendedora.interfaces.ProductoAPI;
import com.rg.developer.maquinaexpendedora.models.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductosActivity extends AppCompatActivity {

    private RecyclerView rcListaProductos;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        rcListaProductos = findViewById(R.id.rvListaProductos);
        init();
    }

    public void init(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://expendedora.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoAPI producto = retrofit.create(ProductoAPI.class);




        Call<List<Producto>> call = producto.getProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                // Si la respuesta es correcta
                try {

                    if(response.isSuccessful()){

                        List<Producto> misProductos = response.body();

                        ListAdapter listAdapter = new ListAdapter(misProductos, ProductosActivity.this);


                        rcListaProductos.setHasFixedSize(true);
                        rcListaProductos.setLayoutManager(new LinearLayoutManager(ProductosActivity.this));
                        rcListaProductos.setAdapter(listAdapter);

                    }else{
                        Toast.makeText(ProductosActivity.this, "Error al cargar los productos", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception ex){
                    Toast.makeText(ProductosActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                // Si ocurre un error
                Toast.makeText(ProductosActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}
