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
public class Constructor_Cantidad_Categoria_Vendido {
 
    private String[] categoria;
    private int []id_categoria;
    private float[] cantidad;

    public Constructor_Cantidad_Categoria_Vendido() {
    }

    public String[] getCategoria() {
        return categoria;
    }

    public void setCategoria(String[] categoria) {
        this.categoria = categoria;
    }

    public int[] getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int[] id_categoria) {
        this.id_categoria = id_categoria;
    }

    public float[] getCantidad() {
        return cantidad;
    }

    public void setCantidad(float[] cantidad) {
        this.cantidad = cantidad;
    }
    
    
}