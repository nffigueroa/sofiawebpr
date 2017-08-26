/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.Funciones_Entrada_Inventario;
import BL.Funciones_Generales;
import BL.Funciones_frm_MasVendido;
import Constructores.Constructor_Mi_empresa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author fabian
 */
public class Funciones_InformeContable_BF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date(System.currentTimeMillis());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Funciones_InformeContable</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Funciones_InformeContable at " + request.getContextPath() + "</h1>");
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
        try{
        JSONObject obJ = new JSONObject(sb.toString());
        int opcion = obJ.getInt("accion");
       
        switch(opcion){
            case 1: { // Llenar tabla ventasDiarias
                try {
                    BL.Funciones_frm_venta_diaria fun = new BL.Funciones_frm_venta_diaria();
                    BL.Funciones_Generales gen = new BL.Funciones_Generales();
                    Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
                   
                    String primerFecha = null;
                    String segundaFecha = null;
                    primerFecha = obJ.getString("fechaIni").isEmpty() ? calcularPrimerFecha() : date.format(Date.parse(obJ.getString("fechaIni"))) ;
                    segundaFecha = obJ.getString("fechaHasta").isEmpty() ? date.format(now): date.format(Date.parse(obJ.getString("fechaHasta")));
                    mi_empresa = gen.datosMiEmpresa(obJ.getInt("idSucursal"));
                    JSONArray jsonResult = new JSONArray();
                    jsonResult.put(0,fun.llenarTablaVentasDiarias(mi_empresa.getId_empresa(),primerFecha
                                                                    ,segundaFecha));
                    response.getWriter().print(jsonResult);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
  
            
            }
            case 2: { // Llenar tabla mas vendido
                try {
                    BL.Funciones_frm_venta_diaria fun = new BL.Funciones_frm_venta_diaria();
                    JSONArray jsonResult = new JSONArray();
                    String fechaPrimera = null;
                    String segundaFecha = null;
                    fechaPrimera = obJ.getString("fechaIni").isEmpty() ? calcularPrimerFecha() : date.format(Date.parse(obJ.getString("fechaIni"))) ;
                    segundaFecha = obJ.getString("fechaHasta").isEmpty() ? date.format(now): date.format(Date.parse(obJ.getString("fechaHasta")));
                    jsonResult.put(0,fun.llenarTablaVentasDiariasXSucursal(obJ.getString("idSucursal"),
                            fechaPrimera,segundaFecha));
                    response.getWriter().print(jsonResult);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                break;
            } // Llenar combo sucursales
            case 3 : {
                try {
                    JSONArray jsonResult = new JSONArray();
                    Funciones_frm_MasVendido fun = new Funciones_frm_MasVendido();
                    jsonResult.put(0,fun.llenarComboSucursal(obJ.getInt("idSucursal")));
                    response.getWriter().print(jsonResult);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                break;
            }
            case 4: {
                try {
                    BL.Funciones_Generales fun = new BL.Funciones_Generales();
                    JSONArray jsonResult = new JSONArray();
                    Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
                    BL.Funciones_Generales gen = new BL.Funciones_Generales();
                    String fechaPrimera = null;
                    String segundaFecha = null;
                    fechaPrimera = obJ.getString("fechaIni").isEmpty() ? calcularPrimerFecha() : date.format(Date.parse(obJ.getString("fechaIni"))) ;
                    segundaFecha = obJ.getString("fechaHasta").isEmpty() ? date.format(now): date.format(Date.parse(obJ.getString("fechaHasta")));
                    mi_empresa = gen.datosMiEmpresa(obJ.getInt("idSucursal"));
                    jsonResult.put(0,fun.llenarVentaMensualConstructoXEmpresa(mi_empresa.getId_empresa(),
                            fechaPrimera,segundaFecha));
                    response.getWriter().print(jsonResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 5 : {
                try {
                    BL.Funciones_Generales fun = new Funciones_Generales();
                    JSONArray array = new JSONArray();
                    array.put(0,fun.historialCorteCaja(obJ.getInt("idSucursal")));
                    response.getWriter().print(array);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 6: {
                try {
                    BL.Funciones_Entrada_Inventario n = new Funciones_Entrada_Inventario();
                    BL.Funciones_Generales fun = new Funciones_Generales();
                    JSONArray json = new JSONArray();
                    json.put(0,fun.funcionProductoMasGanacia(obJ.getInt("idSucursal")));
                    json.put(1,n.funcionCuantoDineroInventario(obJ.getInt("idSucursal")));
                    json.put(2,n.funcionCuantoGanaciaInventario(obJ.getInt("idSucursal")));
                    json.put(3,n.totalInversionInventario(obJ.getInt("idSucursal")));
                    json.put(4,n.llenarTablaInventarioFunciones(obJ.getInt("idSucursal")));
                    response.getWriter().print(json);
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
    
    private String calcularPrimerFecha()
    {
        Calendar cal =Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        String primerFecha = date.format(cal.getTime());
        return primerFecha;
    }
}
