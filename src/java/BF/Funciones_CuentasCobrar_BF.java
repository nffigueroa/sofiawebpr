/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.Funciones_Entrada_Inventario;
import BL.Funciones_Generales;
import BL.Funciones_frm_cuentasCobrar;
import Constructores.Contructor_Cliente_Seleccionado;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import static java.time.Instant.now;
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
public class Funciones_CuentasCobrar_BF extends HttpServlet {

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
            out.println("<title>Servlet Funciones_CuentasCobrar_BF</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Funciones_CuentasCobrar_BF at " + request.getContextPath() + "</h1>");
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
        Date now = new Date(System.currentTimeMillis());
         SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
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
                try {
                    BL.Funciones_frm_cuentasCobrar fun = new Funciones_frm_cuentasCobrar();
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,fun.funcionLlenarCredito(obJ.getInt("idSucursal")));
                    response.getWriter().print(resultJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 2: {
                try {
                    BL.Funciones_frm_cuentasCobrar fun = new Funciones_frm_cuentasCobrar();
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,fun.funcionLlenarHistorialCredito(obJ.getInt("idCredito")));
                    response.getWriter().print(resultJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 3: { // Consulta datos de cliente
                try {
                    Constructores.Contructor_Cliente_Seleccionado cliente = new Contructor_Cliente_Seleccionado();
                    BL.Funciones_frm_cuentasCobrar fun = new Funciones_frm_cuentasCobrar();
                    BL.Funciones_Generales fun2 = new Funciones_Generales();
                    cliente = fun2.llenarConstructorClientePorId(obJ.getInt("idCliente"));
                    Object totalCredito,cuotaMensual,estadoCredito,cuotasPendientes,numeroCuota,siguienteCuota;
                    totalCredito = fun.funcionValorTotalCredito(obJ.getInt("idCredito"));
                    cuotaMensual = fun.funcionValorMensualCredito(obJ.getInt("idCredito"));
                    estadoCredito = fun.funcionDatosCredito(obJ.getInt("idCredito"));
                    cuotasPendientes = fun.funcionNumeroCuotas(obJ.getInt("idCredito"));
                    numeroCuota = fun.funcionNumeroCuotas(obJ.getInt("idCredito"));
                    siguienteCuota = fun.funcionSiguienteCuota(obJ.getInt("idCredito"));
                    JSONArray resultJson = new JSONArray();
                    resultJson.put(0,cliente.getNombre().toString() + cliente.getApellido().toString());
                    resultJson.put(1,cliente.getCodigo());
                    resultJson.put(2,totalCredito);
                    resultJson.put(3,cuotaMensual);
                    resultJson.put(4,estadoCredito);
                    resultJson.put(5,cuotasPendientes);
                    resultJson.put(6,numeroCuota);
                    resultJson.put(7,siguienteCuota);
                    response.getWriter().print(resultJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 4 : { // modificar ProductoInventario
                
                try {
                    BL.Funciones_frm_cuentasCobrar fun = new Funciones_frm_cuentasCobrar();
                    fun.funcionRegistrarAbonoCredito(obJ.getInt("idCredito"),
                                                    obJ.getInt("idCuota"), 
                                                    obJ.get("recibe"),
                                                    obJ.get("cambioAbono"),
                                                    date.format(now), hora.format(now),
                                                    obJ.getInt("idUsuario"),
                                                    obJ.getInt("cuotasPendientes"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 5:{
                try {
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 6 : {
                try
                {
                
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;
            }
            
            case 7: {
                try {
                    
                } catch (Exception e) {
                }
                break;
            }
            case 8 : {
                try {
                    
                } catch (Exception e) {
                }
                break;
            }
            case 9: {
                try {
                    
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
