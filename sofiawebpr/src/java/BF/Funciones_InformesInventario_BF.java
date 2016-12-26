/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.Funciones_entradaSalidaInventarioInforme;
import BL.Funciones_frm_MasVendido;
import BL.Funciones_frm_factura;
import BL.Funciones_frm_informeStock;
import Constructores.Constructo_Cantidad_Productos_Vendido;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class Funciones_InformesInventario_BF extends HttpServlet {

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
            out.println("<title>Servlet Funciones_InformesInventario_BF</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Funciones_InformesInventario_BF at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException { StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        Constructores.Constructo_Cantidad_Productos_Vendido masVendido = new Constructo_Cantidad_Productos_Vendido();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        try{
        JSONObject obJ = new JSONObject(sb.toString());
        int opcion = obJ.getInt("accion");
        
       
        switch(opcion){
            case 1: { // Llenar tabla inventario Stock
                try {
                    BL.Funciones_frm_informeStock fun = new Funciones_frm_informeStock();
                    JSONArray jsonResult = new JSONArray();
                    jsonResult.put(0,fun.llenarTablaInventarioEmpresa(obJ.getInt("idSucursal")));
                    response.getWriter().print(jsonResult);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
  
            
            }
            case 2: { // Llenar tabla mas vendido
                try {
                     BL.Funciones_frm_MasVendido fun = new Funciones_frm_MasVendido();
                     JSONArray jsonResult = new JSONArray();
                     jsonResult.put(fun.llenarTablaMasVendido(obJ.getInt("idSucursal"),
                                                        obJ.getString("fechaIni"),
                                                        obJ.getString("fechaFin")));
                     response.getWriter().print(jsonResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            } // Para mostrar detalle factura
            case 3 : {
                try {
                    BL.Funciones_entradaSalidaInventarioInforme fun = new Funciones_entradaSalidaInventarioInforme();
                    JSONArray jsonResult = new JSONArray();
                    jsonResult.put(0,fun.llenarTablaEntradaProducto(obJ.getInt("idSucursal")));
                    jsonResult.put(1,fun.llenarTablaSalidaProducto(obJ.getInt("idSucursal")));
                    response.getWriter().print(jsonResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 4: {
                try {
                 
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 5 : {
                try {
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        }
         catch(Exception e)
        {
            e.printStackTrace();
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
