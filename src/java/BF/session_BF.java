/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import BL.Funciones_Generales;
import Constructores.Constructor_Usuario;


/**
 *
 * @author Nestor1
 */
public class session_BF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
     

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jObj = new JSONObject(sb.toString());
        String usuario = jObj.getString("usuario");
        Funciones_Generales gen = new Funciones_Generales();
        Constructores.Constructor_Usuario user = new Constructor_Usuario();
        user.setApellido(gen.usuario(usuario).getApellido());
        user.setCc(gen.usuario(usuario).getCc());
        user.setFecha(gen.usuario(usuario).getFecha());
        user.setHora(gen.usuario(usuario).getHora());
        user.setId_cargo(gen.usuario(usuario).getId_cargo());
        user.setId_sucursal(gen.usuario(usuario).getId_sucursal());
        user.setId_usuario(gen.usuario(usuario).getId_usuario());
        user.setNombre(gen.usuario(usuario).getNombre());
        user.setPassword(gen.usuario(usuario).getPassword());
        user.setTelefono(gen.usuario(usuario).getTelefono());
        user.setUsuario(gen.usuario(usuario).getUsuario());
        user.setUsuario_nombre(gen.usuario(usuario).getUsuario_nombre());
        JSONObject respuesta = new JSONObject();
        respuesta.put("nombre", user.getNombre());
        respuesta.put("apellido", user.getApellido());
        respuesta.put("telefono", user.getTelefono());
        respuesta.put("usuario", user.getUsuario());
        respuesta.put("password", user.getPassword());
        respuesta.put("idUsuario", user.getId_usuario());
        respuesta.put("idSucursal", user.getId_sucursal());
        respuesta.put("idCargo", user.getId_cargo());
        respuesta.put("hora", user.getHora());
        respuesta.put("fecha", user.getFecha());
        respuesta.put("cc", user.getCc());
        response.getWriter().print(respuesta);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
