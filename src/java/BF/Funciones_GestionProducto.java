/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BF;

import BL.Funciones_frm_producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import BL.Funciones_frm_producto;
import java.io.BufferedReader;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 *
 * @author Nestor1
 */
public class Funciones_GestionProducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                Funciones_frm_producto pro = new Funciones_frm_producto();
                try {
                    boolean ban = pro.insertarProducto(obJ.getString("nombreProducto1"), null, obJ.getInt("usuario"), obJ.getString("categoria1"),
                            obJ.getString("marca1"),
                            obJ.getString("medicion1"), obJ.getString("presentacion1"));

                    
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
                Funciones_frm_producto pro = new Funciones_frm_producto();
                pro.eliminarProducto(obJ.getInt("idProducto"));
                result.put("resultado","si");
            }
            case 3:
            {
                Funciones_frm_producto pro = new Funciones_frm_producto();
                try{
                boolean ban = pro.actualizarProducto(obJ.getInt("idPro"), obJ.getString("nombreProducto1"),obJ.getString("categoria1"),obJ.getString("marca1"),
                        obJ.getString("medicion1"), obJ.getString("presentacion1"));
                
                
                if (ban) {

                        result.put("resultado", "si");
                    } else {
                        result.put("resultado", "no");
                    }
                break;
            }
                catch(Exception e)
                {e.printStackTrace();
                break;}
            }
            case 4:
            {
                Funciones_frm_producto pro = new Funciones_frm_producto();
                JSONArray resultJson = new JSONArray();
                resultJson.put(0, pro.llenarComboCategoria(obJ.getInt("idSucursal")));
                resultJson.put(1, pro.llenarComboMarca(obJ.getInt("idSucursal")));
                resultJson.put(2, pro.llenarComboMedicion());
                resultJson.put(3, pro.llenarComboPresentacion());
                ArrayList lista = pro.llenarProductos(obJ.getInt("idSucursal"));
                resultJson.put(4, lista);
                response.getWriter().print(resultJson);
                break;
            }
            case 5:
            {
                Funciones_frm_producto pro = new Funciones_frm_producto();
                JSONArray resultJson = new JSONArray();
                ArrayList lista = pro.llenarProductos(obJ.getInt("idSucursal"));
                resultJson.put(4, lista);
                response.getWriter().print(resultJson);
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
