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
public class Constructor_Mi_empresa {
    private int id_empresa;
    private String empresa;
    private String gerente;
    private String tipo_regimen_empresa;
    private String resolucion_facturacion_fecha;
    private String resolucion;
    private String resolucion_facturacion_desde;
    private String resolucion_facturacion_hasta;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String correo;
    private int ultimaFactura;

    public int getUltimaFactura() {
        return ultimaFactura;
    }

    public void setUltimaFactura(int ultimaFactura) {
        this.ultimaFactura = ultimaFactura;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    private String nit;
    private String[] categoria;

    public Constructor_Mi_empresa(int id_empresa, String empresa, String gerente, String tipo_regimen_empresa, String resolucion_facturacion_fecha, String resolucion_facturacion_desde, String resolucion_facturacion_hasta, String nit, String[] categoria) {
        this.id_empresa = id_empresa;
        this.empresa = empresa;
        this.gerente = gerente;
        this.tipo_regimen_empresa = tipo_regimen_empresa;
        this.resolucion_facturacion_fecha = resolucion_facturacion_fecha;
        this.resolucion_facturacion_desde = resolucion_facturacion_desde;
        this.resolucion_facturacion_hasta = resolucion_facturacion_hasta;
        this.nit = nit;
        this.categoria = categoria;
    }

    public Constructor_Mi_empresa() {
    }
    
    
    

    public String getResolucion_facturacion_fecha() {
        return resolucion_facturacion_fecha;
    }

    public void setResolucion_facturacion_fecha(String resolucion_facturacion_fecha) {
        this.resolucion_facturacion_fecha = resolucion_facturacion_fecha;
    }

    public String getResolucion_facturacion_desde() {
        return resolucion_facturacion_desde;
    }

    public void setResolucion_facturacion_desde(String resolucion_facturacion_desde) {
        this.resolucion_facturacion_desde = resolucion_facturacion_desde;
    }

    public String getResolucion_facturacion_hasta() {
        return resolucion_facturacion_hasta;
    }

    public void setResolucion_facturacion_hasta(String resolucion_facturacion_hasta) {
        this.resolucion_facturacion_hasta = resolucion_facturacion_hasta;
    }
       
    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public String getTipo_regimen_empresa() {
        return tipo_regimen_empresa;
    }

    public void setTipo_regimen_empresa(String tipo_regimen_empresa) {
        this.tipo_regimen_empresa = tipo_regimen_empresa;
    }

 

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String[] getCategoria() {
        return categoria;
    }

    public void setCategoria(String[] categoria) {
        this.categoria = categoria;
    }
    
}
