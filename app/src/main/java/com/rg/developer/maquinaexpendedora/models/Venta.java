package com.rg.developer.maquinaexpendedora.models;

import java.util.Date;

public class Venta {

    private int id;
    private String fecha;
    private String hora;
    private MaquinaExpendedora maquinaExpendedora;
    private  Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public MaquinaExpendedora getMaquinaExpendedora() {
        return maquinaExpendedora;
    }

    public void setMaquinaExpendedora(MaquinaExpendedora maquinaExpendedora) {
        this.maquinaExpendedora = maquinaExpendedora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
