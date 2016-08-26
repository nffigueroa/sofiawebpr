/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructo_Cantidad_Productos_Vendido;
import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Controlador.Funciones_Entrada_Inventario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_MasVendido;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



/**
 *
 * @author Nestor1
 */
public class frm_MasVendidoInforme extends javax.swing.JInternalFrame {

    public String []columnas_inventario = new String[30];
    public String []columnas = new String[14];
    public int[]ancho_columnas = new int[15],columnas_eliminar = new int[9], columnas_eliminar_inventario =new int[17];
    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
     Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
        private String user= null;
        String[] producto;
    private float[] cantidad ;
      int [] id_producto ;
        Constructores.Constructo_Cantidad_Productos_Vendido producto_vendido = new Constructo_Cantidad_Productos_Vendido();
    /**
     * Creates new form frm_MasVendidoInforme
     */
    public frm_MasVendidoInforme(Object usuario) {
        user= usuario.toString();
        initComponents();
        cargarTable();
        graficarDatos();
        inicializarForm();
        this.setTitle("MAS VENDIDO");
    }
    private void consultarDatosMiEmpresa()
    {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa=fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }
    private void inicializarForm()
    {
        Funciones_frm_MasVendido funciones= new Funciones_frm_MasVendido();
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
   
   
    private void cargarCantidadProductos(Object sucursal)
    {
        consultarDatosUsuario();
        Controlador.Funciones_frm_MasVendido fun = new Funciones_frm_MasVendido();
        int id_sucur= Integer.parseInt(fun.consultaIdSucursal(sucursal).toString());
        producto_vendido = fun.llenarMasVendido(id_sucur);
    }
     private void cargarCantidadProductosXEmpresa()
    {
        Controlador.Funciones_frm_MasVendido fun = new Funciones_frm_MasVendido();
        producto_vendido = fun.llenarMasVendidoXEmpresa(mi_empresa.getId_empresa());
    }
    
    private void cargarTable()
    {
        Controlador.Funciones_frm_MasVendido fun = new Funciones_frm_MasVendido();
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        cargarCantidadProductosXEmpresa();
        try{
            tbl_datos.setModel(fun.modeloTablaCantidadProductosXEmpresa(mi_empresa.getId_empresa()));
            producto = producto_vendido.getProducto() ;
            cantidad = producto_vendido.getCantidad();
            id_producto = producto_vendido.getId_producto_inventario();
            for (int i = 0; i < id_producto.length; i++) {
                 tbl_datos.getModel().setValueAt(id_producto[i], i, 0);
                 tbl_datos.getModel().setValueAt(producto[i], i, 1);
                 tbl_datos.getModel().setValueAt(cantidad[i], i, 2);
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    private void cargarTableXSucursal(Object sucursal)
    {
        Controlador.Funciones_frm_MasVendido fun = new Funciones_frm_MasVendido();
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        cargarCantidadProductos(sucursal);
        consultarDatosMiEmpresa();
        try{
            tbl_datos.removeAll();
            tbl_datos.setModel(fun.modeloTablaCantidadProductos(sucursal));
            producto = producto_vendido.getProducto() ;
            cantidad = producto_vendido.getCantidad();
            id_producto = producto_vendido.getId_producto_inventario();
            for (int i = 0; i < id_producto.length; i++) {
                 tbl_datos.getModel().setValueAt(id_producto[i], i, 0);
                 tbl_datos.getModel().setValueAt(producto[i], i, 1);
                 tbl_datos.getModel().setValueAt(cantidad[i], i, 2);
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
        Controlador.Funciones_frm_MasVendido fun = new Funciones_frm_MasVendido();
            try{
                DefaultPieDataset data = new DefaultPieDataset();
                for (int i = 0; i < id_producto.length; i++) {
                    data.setValue(producto[i], fun.procentajeMasVendido(cantidad[i], sumatoriaCantidades()));
                }
                ChartPanel panel ;
                JFreeChart chart = ChartFactory.createPieChart3D("PASTEL", data, true, true, true);
                panel = new ChartPanel(chart);
                panel.setBounds(0,30,450,450);
                pan_derecha.add(panel);
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
        }
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
        cargarTableXSucursal(cmb_sucursal.getSelectedItem());
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

        pan_derecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafica"));

        javax.swing.GroupLayout pan_derechaLayout = new javax.swing.GroupLayout(pan_derecha);
        pan_derecha.setLayout(pan_derechaLayout);
        pan_derechaLayout.setHorizontalGroup(
            pan_derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
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

        javax.swing.GroupLayout pan_izquierdaLayout = new javax.swing.GroupLayout(pan_izquierda);
        pan_izquierda.setLayout(pan_izquierdaLayout);
        pan_izquierdaLayout.setHorizontalGroup(
            pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_izquierdaLayout.createSequentialGroup()
                .addGroup(pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_izquierdaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(pan_izquierdaLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_sucursal, 0, 90, Short.MAX_VALUE)
                        .addGap(137, 137, 137)))
                .addContainerGap())
        );
        pan_izquierdaLayout.setVerticalGroup(
            pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_izquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void cmb_sucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_sucursalItemStateChanged
        // TODO add your handling code here:
        
        evento_cambiar_sucursal();
    }//GEN-LAST:event_cmb_sucursalItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_sucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pan_derecha;
    private javax.swing.JPanel pan_izquierda;
    private javax.swing.JTable tbl_datos;
    // End of variables declaration//GEN-END:variables
}
