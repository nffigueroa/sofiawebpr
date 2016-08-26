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
public class Constructor_usuario_permiso {
    
    private int id_usuario;
    private int[] id_permiso;

    public Constructor_usuario_permiso() {
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int[] getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int[] id_permiso) {
        this.id_permiso = id_permiso;
    }
    
}
