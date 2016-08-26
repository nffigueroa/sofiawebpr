/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Proveedor;
import Constructores.Constructor_Usuario;
import Controlador.Funciones_Entrada_Inventario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_entradaSalidaInventarioInforme;
import Controlador.Funciones_frm_Proveedores;
import Fuentes.MiRender;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Nestor1
 */
public class frm_Informe_Inventario extends javax.swing.JInternalFrame {
public String []columnas_inventario = new String[30];
    public String []columnas = new String[11];
    public int[]ancho_columnas = new int[15],columnas_eliminar = new int[16], columnas_eliminar_inventario =new int[17];
    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
    Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
        private String user= null;
        
    /**
     * Creates new form frm_Informe_Inventario
     */
    public frm_Informe_Inventario(Object usuario) {
        user= usuario.toString();
        initComponents();
        cargarTablaInventario();
        cargarTablaSalidaProducto();
        this.setTitle("INFORME INVENTARIO");
    }
    private void consultarDatosMiEmpresa()
    {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa=fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }
     private void parametrosTablaEntradaProducto()
    {
        columnas[0]="CODIGO";
        columnas[1]="PRODUCTO";
        columnas[2]="CANTIDAD";
        columnas[3]="CATEGORIA";
        columnas[4]="PRESENTACAION";
        columnas[5]="MEDICION";
        columnas[6]="MARCA";
        columnas[7]="FECHA";
        columnas[8]="HORA";
        columnas[9]="USUARIO";
        columnas[10]="SUCURSAL";
        columnas_eliminar[0]=12;
        columnas_eliminar[1]=12;
        columnas_eliminar[2]=12;
        columnas_eliminar[3]=12;
        columnas_eliminar[4]=12;
        columnas_eliminar[5]=12;
        columnas_eliminar[6]=12;
        columnas_eliminar[7]=12;
        columnas_eliminar[8]=12;
        columnas_eliminar[9]=12;
        columnas_eliminar[10]=12;
        columnas_eliminar[11]=12;
        columnas_eliminar[12]=12;
        columnas_eliminar[13]=12;
        columnas_eliminar[14]=11;
        columnas_eliminar[15]=11;
    }
      private void parametrosTablaSalidaProducto()
    {
        columnas_eliminar_inventario[0]=12;
        columnas_eliminar_inventario[1]=12;
        columnas_eliminar_inventario[2]=12;
        columnas_eliminar_inventario[3]=12;
        columnas_eliminar_inventario[4]=12;
        columnas_eliminar_inventario[5]=12;
        columnas_eliminar_inventario[6]=12;
        columnas_eliminar_inventario[7]=12;
        columnas_eliminar_inventario[8]=12;
        columnas_eliminar_inventario[9]=12;
        columnas_eliminar_inventario[10]=12;
        columnas_eliminar_inventario[11]=12;
        columnas_eliminar_inventario[12]=12;
        columnas_eliminar_inventario[13]=12;
        columnas_eliminar_inventario[14]=12;
        columnas_eliminar_inventario[15]=12;
        columnas_eliminar_inventario[16]=11;
    }
     
      private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
        
    }
      
      
      private void cargarTablaInventario()
    {
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Controlador.Funciones_entradaSalidaInventarioInforme n = new Funciones_entradaSalidaInventarioInforme();
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        try{
            parametrosTablaEntradaProducto();
            parametrosTablaSalidaProducto();
        }
        catch(Exception ex)
        {
             JOptionPane.showMessageDialog(null, ex);
        }  
        try{
        tbl_entrada_Productos.setModel(n.llenarTablaEntradaProducto(mi_empresa.getId_empresa(),columnas, ancho_columnas));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {
            tbl_entrada_Productos.getColumnModel().removeColumn(tbl_entrada_Productos.getColumnModel().getColumn(columnas_eliminar[i]));
        }
    }
      
       private void cargarTablaSalidaProducto()
    {
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Controlador.Funciones_entradaSalidaInventarioInforme n = new Funciones_entradaSalidaInventarioInforme();
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        try{
            parametrosTablaEntradaProducto();
        }
        catch(Exception ex)
        {
             JOptionPane.showMessageDialog(null, ex);
        }  
        try{
        tbl_salida_producto.setModel(n.llenarTablaSalidaProducto(mi_empresa.getId_empresa(),columnas, ancho_columnas));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar_inventario.length; i++) {
            tbl_salida_producto.getColumnModel().removeColumn(tbl_salida_producto.getColumnModel().getColumn(columnas_eliminar_inventario[i]));
        }
    }
       
        private void evento_presion_enter_buscar()
      {
         
        Funciones_entradaSalidaInventarioInforme n = new Funciones_entradaSalidaInventarioInforme();        
        
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        try{
        tbl_entrada_Productos.setModel(n.BuscarProducto(mi_empresa.getId_empresa(),columnas, ancho_columnas,txt_buscar_por.getText().toString()));
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_entrada_Productos.getColumnModel().removeColumn(tbl_entrada_Productos.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         tbl_entrada_Productos.setRowSelectionInterval(0, 0);
      }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_entrada_Productos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar_por = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_salida_producto = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Entrada Productos"));

        tbl_entrada_Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_entrada_Productos);

        jLabel1.setText("Producto:");

        txt_buscar_por.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_porKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar_por, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_buscar_por, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Venta Producto"));

        tbl_salida_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbl_salida_producto);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscar_porKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_porKeyPressed
        // TODO add your handling code here:
        
         if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            evento_presion_enter_buscar();
        }
        else
        {
        }
        
    }//GEN-LAST:event_txt_buscar_porKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_entrada_Productos;
    private javax.swing.JTable tbl_salida_producto;
    private javax.swing.JTextField txt_buscar_por;
    // End of variables declaration//GEN-END:variables
}
