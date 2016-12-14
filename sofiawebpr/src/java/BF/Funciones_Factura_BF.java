/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.Funciones_frm_CortesCaja;
import BL.Funciones_frm_factura;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
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
public class Funciones_Factura_BF extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Funciones_Factura_BF</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Funciones_Factura_BF at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
//        processRequest(request, response);
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
//        processRequest(request, response);
         StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        try{
        JSONObject obJ = new JSONObject(sb.toString());
        int opcion = obJ.getInt("accion");
        
       
        switch(opcion){
            case 1: { // Llenar tabla factura
                try {
                    BL.Funciones_frm_factura fun = new Funciones_frm_factura();
                    JSONArray respuesta = new JSONArray();
                    respuesta.put(0,fun.llenarTablaFacturas((obJ.getBoolean("ban")), 
                                                            obJ.getInt("idSucursal"), 
                                                            obJ.getString("fechaInicio"), 
                                                            obJ.getString("fechaHasta")));
                    response.getWriter().print(respuesta);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
  
            
            }
            case 2: {
                try {
                        BL.Funciones_frm_factura fun = new Funciones_frm_factura();
                        JSONArray resultJson = new JSONArray();
                        resultJson.put(0,fun.primerFecha(obJ.getInt("idSucursal")));
                        response.getWriter().print(resultJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            } // Para mostrar detalle factura
            case 3 : {
                try {
                  BL.Funciones_frm_factura fun = new Funciones_frm_factura();
                  JSONArray resultJson = new JSONArray();
                  resultJson.put(0,fun.llenarArticulos(obJ.getInt("idFactura")));
                  response.getWriter().print(resultJson);                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 4: {
                try {
                   BL.Funciones_frm_factura fun = new Funciones_frm_factura();
                   fun.funcionAnularFactura(obJ.get("idFactura"),
                                            obJ.get("idMotivo"),
                                            obJ.getInt("idUsuario"));
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
    
}


