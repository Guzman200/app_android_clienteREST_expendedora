package com.rg.developer.maquinaexpendedora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rg.developer.maquinaexpendedora.interfaces.ProductoAPI;
import com.rg.developer.maquinaexpendedora.interfaces.VentaAPI;
import com.rg.developer.maquinaexpendedora.models.Producto;
import com.rg.developer.maquinaexpendedora.models.Venta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VentasActivity extends AppCompatActivity {

    Button btnGenerarVenta;
    EditText txtIdMaquina, txtIdProducto;

    Button btnVerVentas;
    EditText txtIdMaquinaVentas, txtFecha;

    RecyclerView rcListaVentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        // Generar venta
        btnGenerarVenta = (Button) findViewById(R.id.btnGenerarVenta);
        txtIdMaquina = (EditText) findViewById(R.id.txtIdMaquina);
        txtIdProducto = (EditText) findViewById(R.id.txtIdProducto);

        // Ver ventas de una maquina en una fecha
        btnVerVentas = (Button) findViewById(R.id.btnVerVentas);
        txtIdMaquinaVentas = (EditText) findViewById(R.id.txtIdMaquinaVenta);
        txtFecha = (EditText) findViewById(R.id.txtFecha);

        rcListaVentas = (RecyclerView) findViewById(R.id.rcListaVentas);


        // Cuando generen una venta
        btnGenerarVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarVenta();
            }
        });

        // Cuando se mandas a buscar las ventas
        btnVerVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verVentas();
            }
        });


    }

    public void generarVenta(){

        // Obtenemos los id
        int producto_id = Integer.parseInt(txtIdProducto.getText().toString());
        int maquina_id = Integer.parseInt(txtIdMaquina.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://expendedora.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final VentaAPI ventaAPI = retrofit.create(VentaAPI.class);


        Call<Venta> call = ventaAPI.generarVenta(producto_id,maquina_id);

        call.enqueue(new Callback<Venta>() {
            @Override
            public void onResponse(Call<Venta> call, Response<Venta> response) {
                if(response.isSuccessful()){
                    Toast.makeText(VentasActivity.this, "Venta generada exitosamente", Toast.LENGTH_SHORT).show();
                    txtIdMaquina.setText("");
                    txtIdProducto.setText("");
                }else{
                    Toast.makeText(VentasActivity.this, "Maquina expendedora o producto no encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Venta> call, Throwable t) {
                Toast.makeText(VentasActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void verVentas(){

        int maquina_id = Integer.parseInt(txtIdMaquinaVentas.getText().toString());
        String fecha = txtFecha.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://expendedora.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final VentaAPI ventaAPI = retrofit.create(VentaAPI.class);

        Call<List<Venta>> call = ventaAPI.getVentasByFecha(maquina_id,fecha);

        call.enqueue(new Callback<List<Venta>>() {
            @Override
            public void onResponse(Call<List<Venta>> call, Response<List<Venta>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(VentasActivity.this, "Petición exitosa", Toast.LENGTH_SHORT).show();

                    List<Venta> miListaVentas = response.body();

                    ListAdapterVentas listAdapter = new ListAdapterVentas(miListaVentas, VentasActivity.this);


                    rcListaVentas.setHasFixedSize(true);
                    rcListaVentas.setLayoutManager(new LinearLayoutManager(VentasActivity.this));
                    rcListaVentas.setAdapter(listAdapter);

                    /*
                    for(int i = 0; i<miListaVentas.size(); i++){
                        System.out.println("-------------------------------");
                        System.out.println(miListaVentas.get(i).getId());
                        System.out.println(miListaVentas.get(i).getHora());
                        System.out.println(miListaVentas.get(i).getFecha());
                        System.out.println(miListaVentas.get(i).getMaquinaExpendedora().getUbicacion());
                        System.out.println(miListaVentas.get(i).getProducto().getNombre());
                        System.out.println("-------------------------------");
                    }

                     */

                }else{
                    Toast.makeText(VentasActivity.this, "Maquina expendedora no encontrada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Venta>> call, Throwable t) {
                Toast.makeText(VentasActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
