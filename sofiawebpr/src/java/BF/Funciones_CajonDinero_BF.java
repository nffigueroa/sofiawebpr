/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.Funciones_frm_CajonDinero;
import BL.Funciones_frm_CortesCaja;
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
public class Funciones_CajonDinero_BF extends HttpServlet {

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
//            out.println("<title>Servlet Funciones_CajonDinero_BF</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Funciones_CajonDinero_BF at " + request.getContextPath() + "</h1>");
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
            case 1: { // Consulta tipos de importe
                try {
                    BL.Funciones_frm_CajonDinero fun = new Funciones_frm_CajonDinero();
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,fun.llenarComboTpoimporte());
                    response.getWriter().print(resultJson);
                    break;
                } catch (Exception e) {
                }
  
            
            }
            case 2: {
                try {
                    BL.Funciones_frm_CajonDinero fun = new Funciones_frm_CajonDinero();
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,fun.llenarComboMotivoImporte(obJ.get("tipoImporte")));
                    response.getWriter().print(resultJson);
                } catch (Exception e) {
                }
                break;
            }
            case 3 : {
                try {
                   BL.Funciones_frm_CajonDinero fun = new Funciones_frm_CajonDinero();
                   fun.registarImporte(obJ.getInt("idSucursal"), 
                                       obJ.get("tipoImporte"),
                                       obJ.get("motivoImporte"), 
                                       obJ.get("importe"), 
                                       obJ.get("descripcion"), 
                                       obJ.getInt("idUsuario"));
                    
                } catch (Exception e) {
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
