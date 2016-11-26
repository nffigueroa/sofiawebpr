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
public class Contructor_Cliente_Seleccionado {
    
    private Object nombre;
    private Object idUsuario;
    private Object direccion;
    private Object codigo;
    private Object ciudad;
    private Object email;
    private Object telefono;
    private Object apellido;

    public Contructor_Cliente_Seleccionado() {
    }
    
    

    public Contructor_Cliente_Seleccionado(Object nombre, Object idUsuario, Object direccion, Object codigo, Object ciudad, Object email, Object telefono, Object apellido) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.codigo = codigo;
        this.ciudad = ciudad;
        this.email = email;
        this.telefono = telefono;
        this.apellido = apellido;
    }

    
    
    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    public Object getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Object idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Object getDireccion() {
        return direccion;
    }

    public void setDireccion(Object direccion) {
        this.direccion = direccion;
    }

    public Object getCodigo() {
        return codigo;
    }

    public void setCodigo(Object codigo) {
        this.codigo = codigo;
    }

    public Object getCiudad() {
        return ciudad;
    }

    public void setCiudad(Object ciudad) {
        this.ciudad = ciudad;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getTelefono() {
        return telefono;
    }

    public void setTelefono(Object telefono) {
        this.telefono = telefono;
    }

    public Object getApellido() {
        return apellido;
    }

    public void setApellido(Object apellido) {
        this.apellido = apellido;
    }
    
    
    
}
