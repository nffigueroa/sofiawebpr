/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.Funciones_Entrada_Inventario;
import BL.Funciones_frm_Proveedores;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "Funciones_EntradaInventario_BF", urlPatterns = {"/Funciones_EntradaInventario_BF"})
public class Funciones_EntradaInventario_BF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Funciones_EntradaInventario_BF</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Funciones_EntradaInventario_BF at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        JSONObject obJ = new JSONObject(sb.toString());
        int opcion = obJ.getInt("accion");
        JSONObject result = new JSONObject();
        switch(opcion){
            case 1 : { // Llenar inventario de una sucursal seleccionada
                BL.Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
                JSONArray resultJson = new JSONArray();
                resultJson.put(0,fun.llenarTablaInventarioFunciones(obJ.getInt("idSucursal")));
                response.getWriter().print(resultJson);
                break;
            }
            case 2: {
                BL.Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
                JSONArray resultJson = new JSONArray();
                resultJson.put(0,fun.llenarComboProveedor());
                response.getWriter().print(resultJson);
                break;
            }
            case 3: {
                BL.Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
                JSONArray resultJson = new JSONArray();
                resultJson.put(0,fun.llenarComboSucursal(obJ.getInt("idSucursal")));
                response.getWriter().print(resultJson);
                break;
            }
            case 4 : { // modificar ProductoInventario
                try {
                    BL.Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
               fun.actualizarProductoInventario(obJ.get("cantidad"),
                                                obJ.get("stock"), 
                                                obJ.get("idSucursal"), 
                                                obJ.get("proveedor"), 
                                                obJ.get("barras"), 
                                                obJ.get("precio1"), 
                                                obJ.get("precio2"), 
                                                obJ.get("iva").toString(), 
                                                obJ.getString("date"), 
                                                obJ.getInt("idProductoInventario"), 
                                                obJ.getInt("idUsuario"));
                JSONArray resultJson = new JSONArray();
                resultJson.put(0,"1");
                response.getWriter().print(resultJson);
                } catch (Exception e) {
                }
               
                break;
            }
            case 5:{
                try {
                    BL.Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
                   Object consecutivo = fun.registrarProductoInventario(obJ.getJSONObject("listadoProducto"),
                                                    obJ.getInt("idSucursal"),
                                                    obJ.getInt("idUsuario"));
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,consecutivo);
                    response.getWriter().print(resultJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 6 : {
                try
                {
                BL.Funciones_Entrada_Inventario fun = new BL.Funciones_Entrada_Inventario();
                Object consecutivo= fun.agregarCantidadProductoExistente(obJ.getInt("idProductoInventario"), 
                                                    Float.parseFloat(obJ.get("cantidad").toString()), // cantidad nueva
                                                    obJ.getInt("idUsuario"),
                                                    Float.parseFloat(obJ.get("cantidad2").toString()),
                                                    obJ.getInt("idSucursal")); // Cantidad nueva
                JSONArray resultJson = new JSONArray();
                resultJson.put(0,consecutivo);
                response.getWriter().print(resultJson);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;
            }
            
            case 7: {
                try {
                    BL.Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
                    Object consecutivo = fun.descontarCantidad(obJ.getInt("idProductoInventario"), 
                                                                Float.parseFloat(obJ.get("cantidad").toString()), 
                                                                obJ.get("motivo"), 
                                                                obJ.getInt("idUsuario"),
                                                                obJ.getInt("idSucursal"));
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,consecutivo);
                    response.getWriter().print(resultJson);
                } catch (Exception e) {
                }
                break;
            }
            case 8 : {
                try {
                    BL.Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,fun.motivoEliminacionCombo());
                    response.getWriter().print(resultJson);
                } catch (Exception e) {
                }
                break;
            }
            case 9: {
                try {
                    BL.Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
                    Object consecutivo =fun.eliminarProductoInventario(obJ.getInt("idProductoInventario"), 
                                                    obJ.get("motivo"), 
                                                    obJ.getInt("idSucursal"),
                                                    obJ.getInt("idUsuario"));
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,consecutivo);
                    response.getWriter().print(resultJson);
                } catch (Exception e) {
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
