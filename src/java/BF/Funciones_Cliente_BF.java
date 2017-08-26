/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.*;
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
 * @author Nestor1
 */
public class Funciones_Cliente_BF extends HttpServlet {

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
            out.println("<title>Servlet Funciones_Cliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Funciones_Cliente at " + request.getContextPath() + "</h1>");
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
        switch (opcion) {
            case 1: {
                Funciones_frm_clientes pro = new Funciones_frm_clientes();
                try {
                     boolean ban;
                     String auxIca,auxDv;
                    if(obJ.getString("declaraIca").equals('1'))
                        auxIca = null;
                    else
                        auxIca = obJ.getString("milesIca");
                    if(obJ.getString("tipoCliente").equals('1'))
                        auxDv = null;
                    else
                        auxDv = obJ.getString("dv");
                    ban = pro.insertarCliente(obJ.getInt("idUsuario"),
                            obJ.getInt("idSucursal"),
                            obJ.getString("nombreCliente"),
                            null,
                            obJ.getString("telefonoCliente"),
                            obJ.getString("direccionCliente"),
                            obJ.getString("emailCliente"),
                            obJ.getInt("idCiudad"),
                            obJ.getString("iden"),
                            obJ.getString("tipoCliente"),
                            obJ.getString("declaraIva"),
                            obJ.getString("declaraIca"),
                            obJ.getString("reteFuente"),
                            auxIca,
                            auxDv);
               
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
            case 2:
            {
                Funciones_frm_clientes pro = new Funciones_frm_clientes();
                pro.eliminarCliente(obJ.getInt("idCliente"));
                result.put("resultado","si");
                break;
            }
            case 3:
            {
                Funciones_frm_clientes pro = new Funciones_frm_clientes();
                try{
                boolean ban = pro.actualizarCliente(
                        obJ.getInt("idUsuarioMod"),
                        obJ.getInt("idCliente"),
                        obJ.getString("nombreCliente"),
                        obJ.getString("apellidoCliente"),
                        obJ.getString("emailCliente"),
                        obJ.getString("direccionCliente"),
                        obJ.getString("telefonoCliente"),
                        obJ.getString("idCiudad"));
                
                
                if (ban) {

                        result.put("resultado", "si");
                    } else {
                        result.put("resultado", "no");
                    }
                break;
            }
                catch(Exception e)
                {e.printStackTrace(); break;}
            }
            case 4 :
            {
                    Funciones_frm_clientes pro = new Funciones_frm_clientes();
                    JSONArray resultJson = new JSONArray();
                    ArrayList lista = pro.llenarCliente(obJ.getInt("idSucursal"));
                    resultJson.put(0,lista);
                    response.getWriter().print(resultJson);
                break;
            }
            case 5 :
            {
                    Funciones_frm_clientes pro = new Funciones_frm_clientes();
                    JSONArray resultJson = new JSONArray();
                    ArrayList lista = pro.llenarImpuesto(obJ.getInt("idCliente"));
                    resultJson.put(0,lista);
                    response.getWriter().print(resultJson);
                break;
            }
            case 6 :
            {
                try{
                    Funciones_frm_clientes pro = new Funciones_frm_clientes();
                    JSONArray resultJson = new JSONArray();
                    pro.actualizarImpuestos(obJ.getInt("idCliente"),
                                                                obJ.getString("milesIca"),
                                                                obJ.getInt("declaraIva"),
                                                                obJ.getInt("declaraIca"),
                                                                obJ.getInt("retefuente"));
                    resultJson.put(0,true);
                    response.getWriter().print(resultJson);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
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
