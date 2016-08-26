package Fuentes;

import Modelo.Consultas_Generales;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes {

    Connection conexion;
    Statement instrucion;

    public Reportes() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Creamos un enlace hacia la base de datos
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swincomc_20140512_siventas", "root","");
            instrucion = conexion.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }

//METODO PARA EXPORTAR A PDF UN REPORTE   

    public void resportesPDF(String ruta, String archi,int id_empresa) {
        try {
            Map parametro = new HashMap(); 
            parametro.put("id_empresa", id_empresa);
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametro, conexion);
            JasperExportManager.exportReportToPdfFile(informe, archi);

            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("INFORME");
            ventanavisor.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
        }
    }

   
}
