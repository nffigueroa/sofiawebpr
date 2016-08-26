/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructo_Cantidad_Productos_Vendido;
import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Constructores.Constructor_venta_Diaria;
import Controlador.Funciones_Entrada_Inventario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_MasVendido;
import Controlador.Funciones_frm_factura;
import Controlador.Funciones_frm_venta_diaria;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



/**
 *
 * @author Nestor1
 */
public class frm_venta_diaria_informe extends javax.swing.JInternalFrame {

    public String []columnas_inventario = new String[30];
    public String []columnas = new String[19];
    public int[]ancho_columnas = new int[15],columnas_eliminar = new int[9], columnas_eliminar_inventario =new int[17];
    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
     Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
        private String user= null, fecha= new String(),fecha2= new String();
        String[] producto;
    private float[] cantidad ;
      int [] id_producto ;
      SimpleDateFormat formato_date = new SimpleDateFormat();
        Constructores.Constructor_venta_Diaria venta_diaria = new Constructor_venta_Diaria();
    /**
     * Creates new form frm_MasVendidoInforme
     */
    public frm_venta_diaria_informe(Object usuario) {
        user= usuario.toString();
        initComponents();
        inicializarForm();
        cargarTable();
        graficarDatos();
        this.setTitle("VENTA DIARIA");
    }
    private void consultarDatosMiEmpresa()
    {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa=fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }
    private void inicializarForm()
    {
        Funciones_frm_factura fun = new Funciones_frm_factura();
        Date fecha_ultima;
        formato_date.applyPattern("yyyy-MM-dd");
        String fecha1;
        String fecha_hastaa = new SimpleDateFormat("yyyy-MM-dd").format(now); 
        Funciones_frm_MasVendido funciones= new Funciones_frm_MasVendido();
         fecha1= fun.primerFecha();
         try {
            fecha_ultima = formato_date.parse(fecha_hastaa);
            Date fecha_poner_1 = formato_date.parse((fecha1));
            Date_fecha_desde.setDate((fecha_poner_1));
            Date_fecha_Hasta.setDate(fecha_ultima);
            fecha = String.format("%1$td-%1$tm-%1$tY", Date_fecha_desde.getDate());
            fecha2 = String.format("%1$td-%1$tm-%1$tY", Date_fecha_Hasta.getDate());
         } catch (Exception ex) {
             Logger.getLogger(frm_GestionarFactura.class.getName()).log(Level.SEVERE, null, ex);
         }
         consultarDatosUsuario();
          Object [] items_sucursal;
         items_sucursal= funciones.llenarComboSucursal(usuario_activo.getId_sucursal());
            for (int t = 0; t < items_sucursal.length; t++) {
            cmb_sucursal.addItem(items_sucursal[t]);
        }
            
    }
    private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
        
    }
   
    private void llenarVentaDiaria(int id_empresa)
    {
        Funciones_Generales fun = new Funciones_Generales();
        venta_diaria=fun.llenarVentaDiariaConstructor(id_empresa, fecha, fecha2);
    }
    
    private void llenarVentaDiariaXSucursal(String id_sucursal)
    {
        Funciones_Generales fun = new Funciones_Generales();
        venta_diaria=fun.llenarVentaDiariaConstructorXSucursal(id_sucursal, fecha, fecha2);
    }
   
   
     private void parametrosTabla()
    {
        columnas[0] = "IDENTIFICACION";
        columnas[1] = "FOLIO";
        columnas[2] = "TOTAL";
        columnas[3] = "SUBTOTAL";
        columnas[4] = "DESCUENTO";
        columnas[5] = "IVA";
        columnas[6] = "RECIBE";
        columnas[7] = "CAMBIO";
        columnas[8] = "PAGO CON:";
        columnas[9] = "TARJETA";
        columnas[10] = "DESCRIPCION";
        columnas[11] = "NOM. CLIENTE";
        columnas[12] = "APE. CLIENTE";
        columnas[13] = "CEDULA";
        columnas[14] = "DIRECCION";
        columnas[15] = "FECHA";
        columnas[16] = "BORRAR";
        columnas[17] = "BORRAR2";
        columnas[18] = "BORRAR3";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        columnas_eliminar[0] = 16;
        columnas_eliminar[1] = 16;
        columnas_eliminar[2] = 16;
        columnas_eliminar[3] = 16;
       
    }
    private void cargarTable()
    {
        Controlador.Funciones_frm_venta_diaria fun = new Funciones_frm_venta_diaria();
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        try{
            parametrosTabla();
            fecha = String.format("%1$tY-%1$tm-%1$td", Date_fecha_desde.getDate());
            fecha2 = String.format("%1$tY-%1$tm-%1$td", Date_fecha_Hasta.getDate());
            consultarDatosUsuario();
            consultarDatosMiEmpresa();
            llenarVentaDiaria(mi_empresa.getId_empresa());
            tbl_datos.setModel(fun.llenarTablaVentasDiarias(mi_empresa.getId_empresa(),fecha,fecha2,columnas,ancho_columnas));
            float[]totalTotal = venta_diaria.getTotalVenta();
            String[] fechaMostrar = venta_diaria.getFecha();
            for (int i = 0; i < venta_diaria.getFecha().length; i++) {
                tbl_datos.setValueAt(fechaMostrar[i], i, 0);
                tbl_datos.setValueAt(totalTotal[i], i, 1);
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
   
    private float sumatoriaCantidades()
    {
         float resultado=0;
        for(int i = 0; i < id_producto.length; i++) {
            resultado=  resultado + cantidad[i];
                    
            }
        return resultado;
    }
    private void graficarDatos()
    {
       ChartPanel panel ;
       DefaultCategoryDataset data = new DefaultCategoryDataset();
       String [] dias = venta_diaria.getFecha();
       float[] total= venta_diaria.getTotalVenta();
       int fila = 0;
        for (int i = 0; i < venta_diaria.getFecha().length; i++) {
            if(dias[i].equalsIgnoreCase(""))
            {}
            else
            {
                fila++;
            }
        }
        for (int i = 0; i < fila; i++) {
            if(total[i]==0)
            {}
            else{
            data.addValue(total[i], "", dias[i]);}
        }
        JFreeChart chart = null;
        chart = ChartFactory.createBarChart("VENTA DIARIA", "VENTAS", "CANTIDAD", data, 
                PlotOrientation.VERTICAL, 
                true, true, true);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        panel = new ChartPanel(chart);
        panel.setBounds(0,30,450,450);
        pan_derecha.add(panel);
    }
    
    private void evento_cambiar_sucursal()
    {
        if(cmb_sucursal.getSelectedIndex()==0)
        {
            cargarTable();
            pan_derecha.removeAll();
            graficarDatos();
            pan_derecha.repaint();
        }
        else
        {
      Controlador.Funciones_frm_venta_diaria fun = new Funciones_frm_venta_diaria();
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        try{
            parametrosTabla();
            fecha = String.format("%1$tY-%1$tm-%1$td", Date_fecha_desde.getDate());
            fecha2 = String.format("%1$tY-%1$tm-%1$td", Date_fecha_Hasta.getDate());
            consultarDatosUsuario();
            consultarDatosMiEmpresa();
            llenarVentaDiariaXSucursal((cmb_sucursal.getSelectedItem().toString()));
            tbl_datos.setModel(fun.llenarTablaVentasDiariasXSucursal((cmb_sucursal.getSelectedItem().toString()),fecha,fecha2,columnas,ancho_columnas));
            float[]totalTotal = venta_diaria.getTotalVenta();
            String[] fechaMostrar = venta_diaria.getFecha();
            for (int i = 0; i < venta_diaria.getFecha().length; i++) {
                tbl_datos.setValueAt(fechaMostrar[i], i, 0);
                tbl_datos.setValueAt(totalTotal[i], i, 1);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        }
       
    }
    
    private void click_boton_aplicar_fechas()
    {
        if(cmb_sucursal.getSelectedIndex()==0)
        {
            fecha = String.format("%1$tY-%1$tm-%1$td", Date_fecha_desde.getDate());
            fecha2 = String.format("%1$tY-%1$tm-%1$td", Date_fecha_Hasta.getDate()); 
            cargarTable();
            pan_derecha.removeAll();
            graficarDatos();
            pan_derecha.repaint();
        }
        else
        {
            fecha = String.format("%1$tY-%1$tm-%1$td", Date_fecha_desde.getDate());
            fecha2 = String.format("%1$tY-%1$tm-%1$td", Date_fecha_Hasta.getDate()); 
            evento_cambiar_sucursal();
            pan_derecha.removeAll();
            graficarDatos();
            pan_derecha.repaint();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan_derecha = new javax.swing.JPanel();
        pan_izquierda = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_datos = new javax.swing.JTable();
        cmb_sucursal = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        Date_fecha_desde = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        Date_fecha_Hasta = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        pan_derecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafica"));

        javax.swing.GroupLayout pan_derechaLayout = new javax.swing.GroupLayout(pan_derecha);
        pan_derecha.setLayout(pan_derechaLayout);
        pan_derechaLayout.setHorizontalGroup(
            pan_derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );
        pan_derechaLayout.setVerticalGroup(
            pan_derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pan_izquierda.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        tbl_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Producto", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_datos);
        if (tbl_datos.getColumnModel().getColumnCount() > 0) {
            tbl_datos.getColumnModel().getColumn(0).setResizable(false);
            tbl_datos.getColumnModel().getColumn(1).setResizable(false);
            tbl_datos.getColumnModel().getColumn(2).setResizable(false);
        }

        cmb_sucursal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "..." }));
        cmb_sucursal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_sucursalItemStateChanged(evt);
            }
        });

        jLabel1.setText("Ver por:");

        jLabel2.setText("Desde:");

        jLabel3.setText("Hasta:");

        jButton2.setText("Aplicar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_izquierdaLayout = new javax.swing.GroupLayout(pan_izquierda);
        pan_izquierda.setLayout(pan_izquierdaLayout);
        pan_izquierdaLayout.setHorizontalGroup(
            pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_izquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pan_izquierdaLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_fecha_desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_fecha_Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_izquierdaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_sucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pan_izquierdaLayout.setVerticalGroup(
            pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_izquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Date_fecha_desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(Date_fecha_Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan_izquierda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pan_derecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan_derecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan_izquierda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        click_boton_aplicar_fechas();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmb_sucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_sucursalItemStateChanged
        // TODO add your handling code here:
        evento_cambiar_sucursal();
        pan_derecha.removeAll();
        graficarDatos();
        pan_derecha.repaint();
    }//GEN-LAST:event_cmb_sucursalItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date_fecha_Hasta;
    private com.toedter.calendar.JDateChooser Date_fecha_desde;
    private javax.swing.JComboBox cmb_sucursal;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pan_derecha;
    private javax.swing.JPanel pan_izquierda;
    private javax.swing.JTable tbl_datos;
    // End of variables declaration//GEN-END:variables
}
