/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructo_Cantidad_Productos_Vendido;
import Constructores.Constructor_Cantidad_Categoria_Vendido;
import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Controlador.Funciones_Entrada_Inventario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_MasVendido;
import Controlador.Funciones_frm_VentasCategoria;
import java.text.SimpleDateFormat;
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
public class frm_MasVendidoCategoriaInforme extends javax.swing.JInternalFrame {

    public String []columnas_inventario = new String[30];
    public String []columnas = new String[14];
    public int[]ancho_columnas = new int[15],columnas_eliminar = new int[9], columnas_eliminar_inventario =new int[17];
    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
    Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
        private String user= null;
        String[] categoria;
    private float[] cantidad ;
      int [] id_categoria ;
        Constructores.Constructor_Cantidad_Categoria_Vendido producto_vendido = new Constructor_Cantidad_Categoria_Vendido();
    /**
     * Creates new form frm_MasVendidoInforme
     */
    public frm_MasVendidoCategoriaInforme(Object usuario) {
        user= usuario.toString();
        initComponents();
        cargarTable();
        graficarDatos();
        this.setTitle("MAS VENDIDO POR CATEGORIA");
    }
    private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
        
    }
   
   private void consultarDatosMiEmpresa()
    {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa=fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }
    private void cargarCantidadProductos()
    {
        consultarDatosUsuario();
        Controlador.Funciones_frm_VentasCategoria fun = new Funciones_frm_VentasCategoria();
        producto_vendido = fun.llenarMasVendido(usuario_activo.getId_sucursal());
    }
    
    private void cargarTable()
    {
        Controlador.Funciones_frm_VentasCategoria fun = new Funciones_frm_VentasCategoria();
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        cargarCantidadProductos();
        try{
            consultarDatosMiEmpresa();
            tbl_datos.setModel(fun.modeloTablaCantidadCategoria(usuario_activo.getId_sucursal()));
           producto_vendido = fun.llenarMasVendido(mi_empresa.getId_empresa());
            categoria = producto_vendido.getCategoria();
            cantidad = producto_vendido.getCantidad();
            id_categoria = producto_vendido.getId_categoria();
            for (int i = 0; i < id_categoria.length; i++) {
                 tbl_datos.getModel().setValueAt(id_categoria[i], i, 0);
                 tbl_datos.getModel().setValueAt(categoria[i], i, 1);
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
        for(int i = 0; i < id_categoria.length; i++) {
            resultado=  resultado + cantidad[i];
                    
            }
        return resultado;
    }
    private void graficarDatos()
    {
        Controlador.Funciones_frm_MasVendido fun = new Funciones_frm_MasVendido();
            try{
                DefaultPieDataset data = new DefaultPieDataset();
                for (int i = 0; i < id_categoria.length; i++) {
                    data.setValue(categoria[i], fun.procentajeMasVendido(cantidad[i], sumatoriaCantidades()));
                }
                ChartPanel panel ;
                JFreeChart chart = ChartFactory.createPieChart3D("PASTEL", data, true, true, true);
                panel = new ChartPanel(chart);
                panel.setBounds(0,30,450,450);
                pan_derecha.add(panel);
        } catch (Exception e) {
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

        pan_derecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafica"));

        javax.swing.GroupLayout pan_derechaLayout = new javax.swing.GroupLayout(pan_derecha);
        pan_derecha.setLayout(pan_derechaLayout);
        pan_derechaLayout.setHorizontalGroup(
            pan_derechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 422, Short.MAX_VALUE)
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

        javax.swing.GroupLayout pan_izquierdaLayout = new javax.swing.GroupLayout(pan_izquierda);
        pan_izquierda.setLayout(pan_izquierdaLayout);
        pan_izquierdaLayout.setHorizontalGroup(
            pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_izquierdaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );
        pan_izquierdaLayout.setVerticalGroup(
            pan_izquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pan_derecha;
    private javax.swing.JPanel pan_izquierda;
    private javax.swing.JTable tbl_datos;
    // End of variables declaration//GEN-END:variables
}
