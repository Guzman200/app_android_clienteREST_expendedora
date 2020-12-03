package com.rg.developer.maquinaexpendedora.models;

public class Producto {

    private int id;
    private String nombre;
    private float precio_venta;

    public Producto(int id, String nombre, float precio_venta) {
        this.id = id;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
    }

    public Producto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }
}
