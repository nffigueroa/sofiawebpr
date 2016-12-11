/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.Funciones_frm_CortesCaja;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
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
public class Funciones_CorteCaja_BF extends HttpServlet {
Constructores.Contructor_CortesCaja corte_caja = new Constructores.Contructor_CortesCaja();
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
//            out.println("<title>Servlet Funciones_CorteCaja_BF</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Funciones_CorteCaja_BF at " + request.getContextPath() + "</h1>");
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
            case 1: {
                try {
                    BL.Funciones_frm_CortesCaja fun = new Funciones_frm_CortesCaja();
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,fun.llenarFacturas(obJ.getInt("idSucursal")));
                    response.getWriter().print(resultJson);
                    break;
                } catch (Exception e) {
                }
  
            
            }
            case 2: {
                try {
                BL.Funciones_frm_CortesCaja fun = new Funciones_frm_CortesCaja();
                JSONArray resultJson = new JSONArray();
                    resultJson.put(0,fun.llenarImportes(obJ.getInt("idSucursal")));
                    response.getWriter().print(resultJson);
                } catch (Exception e) {
                }
                break;
            }
            case 3 : {
                try {
                    BL.Funciones_Generales fun = new BL.Funciones_Generales();
                    corte_caja = fun.corte_caja_totales(obJ.getInt("idSucursal"));
                    JSONArray resultJson = new JSONArray();
                    ArrayList lista = new ArrayList();
                    lista.add(0,corte_caja.getTotal());
                    lista.add(1,corte_caja.getTotal_cheque());
                    lista.add(2,corte_caja.getTotal_efectivo());
                    lista.add(3,corte_caja.getTotal_importe_egreso());
                    lista.add(4,corte_caja.getTotal_importe_ingreso());
                    lista.add(5,corte_caja.getTotal_nota_credito());
                    lista.add(6,corte_caja.getTotal_tarjeta_credito());
                    lista.add(7,corte_caja.getTotal_tarjeta_debito());
                    lista.add(8,corte_caja.getTotal_transaccion());
                    lista.add(9,fun.efectivoInicial(obJ.getInt("idSucursal")));
                    resultJson.put(0,lista);
                    response.getWriter().print(resultJson);
                    
                } catch (Exception e) {
                }
                break;
            }
            case 4: {
                try {
                    corte_caja.setTotal(Float.parseFloat(obJ.get("totalVentas").toString()));
                    corte_caja.setTotal_cheque(Float.parseFloat(obJ.get("cheques").toString()));
                    corte_caja.setTotal_efectivo(Float.parseFloat(obJ.get("efectivoTotal").toString()));
                    corte_caja.setTotal_importe_egreso(Float.parseFloat(obJ.get("otrosEgresos").toString()));
                    corte_caja.setTotal_importe_ingreso(Float.parseFloat(obJ.get("otrosIngresos").toString()));
                    corte_caja.setTotal_nota_credito(Float.parseFloat(obJ.get("credito").toString()));
                    corte_caja.setTotal_tarjeta_credito(Float.parseFloat(obJ.get("tarjetaCredito").toString()));
                    corte_caja.setTotal_tarjeta_debito(Float.parseFloat(obJ.get("tarjetaDebito").toString()));
                    corte_caja.setTotal_transaccion(Float.parseFloat(obJ.get("transacciones").toString()));
                    BL.Funciones_frm_CortesCaja fun = new BL.Funciones_frm_CortesCaja();
                    fun.registrarCorte(Float.parseFloat(obJ.get("efectivoReal").toString()) , 
                                        obJ.getInt("idSucursal"), 
                                        obJ.getInt("idUsuario"), 
                                        corte_caja, 
                                        obJ.getJSONObject("facturas"), 
                                        obJ.getJSONObject("importes"));
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
