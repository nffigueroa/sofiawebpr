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
public class Constructor_venta_Diaria {
    
    private String[] fecha;
    private float[] totalVenta;
    private float[] totalIva;
    private float[] totalDescuento;

    public Constructor_venta_Diaria() {
    }

    public String[] getFecha() {
        return fecha;
    }

    public void setFecha(String[] fecha) {
        this.fecha = fecha;
    }

    public float[] getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(float[] totalVenta) {
        this.totalVenta = totalVenta;
    }

    public float[] getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(float[] totalIva) {
        this.totalIva = totalIva;
    }

    public float[] getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(float[] totalDescuento) {
        this.totalDescuento = totalDescuento;
    }
    
}
