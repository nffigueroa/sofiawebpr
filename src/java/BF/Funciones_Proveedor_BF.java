/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.Funciones_frm_Proveedores;
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
public class Funciones_Proveedor_BF extends HttpServlet {

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
            case 1: { // Agrega Proveedor
                Funciones_frm_Proveedores pro = new Funciones_frm_Proveedores();
               try {
                    boolean ban = pro.insertarProveedor(
                            obJ.getInt("idSucursal"),
                            obJ.getInt("idUsuario"),
                            obJ.getString("empresa"),
                            obJ.getString("contacto"), 
                            obJ.getString("telefono"), 
                            obJ.getString("direccion"),
                            obJ.getString("email"),
                            obJ.getString("ciudad"),
                            obJ.getString("nit"));
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
                Funciones_frm_Proveedores pro = new Funciones_frm_Proveedores();
                pro.eliminarProveedor(obJ.getInt("id_proveedor"), obJ.getInt("idUsuario"));
                result.put("resultado", "si");
            }
            case 3: { // Modifica
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
            case 4: { // consulta listado de proveedores
                Funciones_frm_Proveedores pro = new Funciones_frm_Proveedores();
                JSONArray resultJson = new JSONArray();
                ArrayList lista = pro.llenarProveedor(obJ.getInt("idSucursal"));
                resultJson.put(0, lista);
                response.getWriter().print(resultJson);
                break;
            }
            case 5 : { // Consultar las categorias del proveedor
                JSONArray resultJson = new JSONArray();
                Funciones_frm_Proveedores pro = new Funciones_frm_Proveedores();
                resultJson.put(0,pro.categoriasProveedorSeleccionadas(obJ.getInt("idProveedor")));
                response.getWriter().print(resultJson);
                break;
            }
            case 6 : { // Modificar Categoria Proveedor
                try{
                JSONArray resultJson = new JSONArray();
                Funciones_frm_Proveedores pro = new Funciones_frm_Proveedores();
//                ArrayList<JSONObject> myarray = new ArrayList<JSONObject>();
                //myarray.add(obJ.getJSONObject("categorias"));
                //objetoJson = obJ.getJSONObject("categorias");
                resultJson.put(pro.insertarCategoriaProeedor(obJ.getString("nitProveedor"),
                                                               obJ.getJSONObject("categorias")));
                response.getWriter().print(resultJson);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    break;
                }
                break;
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
