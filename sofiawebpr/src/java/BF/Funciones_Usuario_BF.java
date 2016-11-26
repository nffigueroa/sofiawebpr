/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.*;
import BL.Funciones_frm_producto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Funciones_Usuario_BF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Funciones_Proveedor_BF</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Funciones_Proveedor_BF at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //@Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        return null;
//    }

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
        JSONObject obJ = new JSONObject(sb.toString());
        int opcion = obJ.getInt("accion");
        JSONObject result = new JSONObject();
        switch (opcion) {
            case 1: { // Inserta los permisos seleccionados en el formulario al usuario indicado
                Funciones_frm_Usuario pro = new Funciones_frm_Usuario();
               try {
                    boolean ban = pro.insertarUsuario(obJ.getString("nombreUsuario"),
                                                        obJ.getString("apellidoUsuario"),
                                                        obJ.getString("idenUsuario"),
                                                        obJ.getString("telefonoUsuario"),
                                                        obJ.getString("direccionUsuario"),
                                                        obJ.getString("cargoUsuario"),
                                                        obJ.getString("descripcionUsuario"),
                                                        obJ.getInt("idUsuario"),
                                                        obJ.getInt("idSucursal"),
                                                            obJ.getString("usuarioNombre"),
                                                        obJ.getString("psw"));
                    if (ban) {

                        result.put("resultado", "si");
                    } else {
                        result.put("resultado", "no");
                    }
                    response.getWriter().print(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 2: { // Elimina proveedor
                Funciones_frm_Usuario pro = new Funciones_frm_Usuario();
                pro.eliminarUsuario(obJ.getInt("idUsuarioAEliminar"), obJ.getInt("idUsuarioLogueado"));
                result.put("resultado", "si");
                break;
            }
            case 3: { // Modifica usuario
                Funciones_frm_Proveedores pro = new Funciones_frm_Proveedores();
                try {
                    boolean ban = pro.modificarProveedor(obJ.getInt("idProveedor"),
                                                        obJ.getString("empresa"), 
                                                        obJ.getString("contacto"),
                                                        obJ.getString("telefono"), 
                                                        obJ.getString("direccion"),
                                                        obJ.getString("mail"),
                                                        obJ.getString("ciudad"),
                                                        obJ.getInt("idSucursal"),
                                                        obJ.getString("usuario"), 
                                                        obJ.getString("nit"));
                    result.put("resultado","si");
//                    boolean ban = pro.modificarProveedor(obJ.getInt("idPro"), obJ.getString("nombreProducto1"),
//                            obJ.getString("categoria1"), obJ.getString("marca1"),
//                            obJ.getString("medicion1"), null, null, null, null, null, null);
//
//                    if (ban) {
//
//                        result.put("resultado", "si");
//                    } else {
//                   
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("resultado", "no");
                }
                break;
            }
            case 4: { // consulta lista de usuarios con datos basicos de acuerdo a la sucursal logueada
                Funciones_frm_Usuario pro = new Funciones_frm_Usuario();
                JSONArray resultJson = new JSONArray();
                ArrayList lista = pro.llenarUsuario(obJ.getInt("idSucursal"));
                resultJson.put(0, lista);
                response.getWriter().print(resultJson);
                break;
            }
            case 5 : { // Consultar Los permisos del usuario
                JSONArray resultJson = new JSONArray();
                Funciones_frm_Usuario pro = new Funciones_frm_Usuario();
                resultJson.put(0,pro.permisosUsuario(obJ.getInt("idUsuario")));
                response.getWriter().print(resultJson);
                break;
            }
            case 6 : { // Modificar Permisos Usuario
                try{
                JSONArray resultJson = new JSONArray();
                Funciones_frm_Usuario pro = new Funciones_frm_Usuario();
//                ArrayList<JSONObject> myarray = new ArrayList<JSONObject>();
                //myarray.add(obJ.getJSONObject("categorias"));
                //objetoJson = obJ.getJSONObject("categorias");
                resultJson.put(pro.insertarPermisosUsuario(obJ.getJSONObject("permisos"),
                                                            obJ.getString("ccUsuario")));
                response.getWriter().print(resultJson);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    break;
                }
                break;
            }
            case 7 :  {
                try {
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
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
