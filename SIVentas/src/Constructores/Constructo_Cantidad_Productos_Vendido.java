/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constructores;

/**
 *
 * @author Nestor1
 */
public class Constructo_Cantidad_Productos_Vendido {
    
    private int []id_producto_inventario;
    private int[] id_producto;
    private String[] producto;
    private float[] cantidad;

    public int[] getId_producto_inventario() {
        return id_producto_inventario;
    }

    public void setId_producto_inventario(int[] id_producto_inventario) {
        this.id_producto_inventario = id_producto_inventario;
    }

    public Constructo_Cantidad_Productos_Vendido() {
    }

    public int[] getId_producto() {
        return id_producto;
    }

    public void setId_producto(int[] id_producto) {
        this.id_producto = id_producto;
    }

    public String[] getProducto() {
        return producto;
    }

    public void setProducto(String[] producto) {
        this.producto = producto;
    }

    public float[] getCantidad() {
        return cantidad;
    }

    public void setCantidad(float[] cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
