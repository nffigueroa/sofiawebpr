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
public class Constructor_Proveedor {
    
    private String empresa;
    private int id_proveedor;
    private String contacto_empresa;
    private String telefono;
    private String direccion_proveedor;
    private String mail_Proveedor;
    private String ciudad_Proveedor;
    private String nit_proveedor;
    private int id_usuario;
    private String fecha_creacion;
    private int id_sucursal;

    public Constructor_Proveedor() {
    }

    public Constructor_Proveedor(String empresa, int id_proveedor, String contacto_empresa, String telefono, String direccion_proveedor, String mail_Proveedor, String ciudad_Proveedor, String nit_proveedor, int id_usuario, String fecha_creacion, int id_sucursal) {
        this.empresa = empresa;
        this.id_proveedor = id_proveedor;
        this.contacto_empresa = contacto_empresa;
        this.telefono = telefono;
        this.direccion_proveedor = direccion_proveedor;
        this.mail_Proveedor = mail_Proveedor;
        this.ciudad_Proveedor = ciudad_Proveedor;
        this.nit_proveedor = nit_proveedor;
        this.id_usuario = id_usuario;
        this.fecha_creacion = fecha_creacion;
        this.id_sucursal = id_sucursal;
    }

    
    
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getContacto_empresa() {
        return contacto_empresa;
    }

    public void setContacto_empresa(String contacto_empresa) {
        this.contacto_empresa = contacto_empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public String getMail_Proveedor() {
        return mail_Proveedor;
    }

    public void setMail_Proveedor(String mail_Proveedor) {
        this.mail_Proveedor = mail_Proveedor;
    }

    public String getCiudad_Proveedor() {
        return ciudad_Proveedor;
    }

    public void setCiudad_Proveedor(String ciudad_Proveedor) {
        this.ciudad_Proveedor = ciudad_Proveedor;
    }

    public String getNit_proveedor() {
        return nit_proveedor;
    }

    public void setNit_proveedor(String nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }
    
    
    
}
