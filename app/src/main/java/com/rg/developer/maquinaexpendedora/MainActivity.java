package com.rg.developer.maquinaexpendedora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rg.developer.maquinaexpendedora.interfaces.ProductoAPI;
import com.rg.developer.maquinaexpendedora.models.Producto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    // Para guardar un producto
    EditText nombreProducto;
    EditText precioProducto;
    Button botonGuardar;

    // Para editar un producto
    EditText txtIdBuscar;
    EditText txtNombreEdit;
    EditText txtPrecioEdit;
    Button btnBuscar;
    Button btnEditarProducto;

    // Ir a ver todos los productos
    FloatingActionButton fabProductos;
    // Ir a ventas
    FloatingActionButton fabVentas;

    List<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Para guardar un producto
        nombreProducto = (EditText) findViewById(R.id.nombreText);
        precioProducto = (EditText)  findViewById(R.id.precioText);
        botonGuardar = (Button) findViewById(R.id.guardarProducto);

        // Para editar un producto
        txtIdBuscar = (EditText) findViewById(R.id.txtIdProductoBuscar);
        txtNombreEdit = (EditText)  findViewById(R.id.txtNombreEdit);
        txtPrecioEdit = (EditText)  findViewById(R.id.txtPrecioEdit);
        btnBuscar = (Button) findViewById(R.id.btnBuscarProducto);
        btnEditarProducto = (Button) findViewById(R.id.btnEditarProducto);

       fabProductos = findViewById(R.id.fabProductos);
       fabVentas = findViewById(R.id.fabVentas);

       fabVentas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, VentasActivity.class);
               startActivity(intent);
           }
       });


       fabProductos.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, ProductosActivity.class);
               startActivity(intent);

           }
       });


       // Guarda un produco en la base de datos
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarProducto();
            }
        });

        // Busca un producto
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarProducto();
            }
        });

        btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarProducto();
            }
        });
    }



    public void guardarProducto(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://expendedora.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoAPI producto = retrofit.create(ProductoAPI.class);

        // Obtenemos los datos ingresados
        String nombre = nombreProducto.getText().toString();
        String precio = precioProducto.getText().toString();

        Producto productonew = new Producto();
        productonew.setNombre(nombre);
        productonew.setPrecio_venta(Float.parseFloat(precio));

        Call<Producto> call = producto.crearProducto(productonew);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                // Si la respuesta es correcta
                try {

                    if(response.isSuccessful()){
                        Producto pro = response.body();
                        nombreProducto.setText("");
                        precioProducto.setText("");
                        Toast.makeText(MainActivity.this, "Producto creado", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                // Si ocurre un error
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void buscarProducto(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://expendedora.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoAPI producto = retrofit.create(ProductoAPI.class);

        // Obtenemos el id del producto a buscar
        int id_producto = Integer.parseInt(txtIdBuscar.getText().toString());


        Call<Producto> call = producto.findById(id_producto);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                // Si la respuesta es correcta
                try {

                    if(response.isSuccessful()){
                        Producto pro = response.body();
                        txtNombreEdit.setText(pro.getNombre());
                        txtPrecioEdit.setText(""+pro.getPrecio_venta());

                    }else{
                        Toast.makeText(MainActivity.this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                // Si ocurre un error
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editarProducto(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://expendedora.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductoAPI producto = retrofit.create(ProductoAPI.class);

        // Obtenemos el id del producto a editar
        int id_producto = Integer.parseInt(txtIdBuscar.getText().toString());

        Producto productoUpdate = new Producto();
        productoUpdate.setId(id_producto);
        productoUpdate.setNombre(txtNombreEdit.getText().toString());
        productoUpdate.setPrecio_venta(Float.parseFloat(txtPrecioEdit.getText().toString()));

        Call<Producto> call = producto.editarProducto(productoUpdate);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                // Si la respuesta es correcta
                try {

                    if(response.isSuccessful()){
                        Producto pro = response.body();
                        txtNombreEdit.setText(pro.getNombre());
                        txtPrecioEdit.setText(""+pro.getPrecio_venta());
                        Toast.makeText(MainActivity.this, "Producto editado correctamente", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Producto no editado", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                // Si ocurre un error
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
