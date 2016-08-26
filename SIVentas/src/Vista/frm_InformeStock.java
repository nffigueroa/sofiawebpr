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
import Controlador.Funciones_frm_informeStock;
import Fuentes.MiRender;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author Nestor1
 */
public class frm_InformeStock extends javax.swing.JInternalFrame {
  public String []columnas_inventario = new String[33];
    public String []columnas = new String[5];
    public int[]ancho_columnas = new int[15],columnas_eliminar = new int[4], columnas_eliminar_inventario =new int[18];
    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
        private String user= null;
        Constructores.Constructor_Proveedor proveedor = new Constructor_Proveedor();
        Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
    /**
     * Creates new form frm_InformeExistencias
     */
    public frm_InformeStock(Object usuario) {
        
        user= usuario.toString();
        initComponents();
        cargarTablaInventario();
        this.setTitle("INFORME STOCK");
    }
    private void consultarDatosMiEmpresa()
    {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa=fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }
    private void parametrosTablaCoinsidenciasProveedor()
    {
        columnas[0]="CODIGO";
        columnas[1]="EMPRESA";
        columnas[2]="DIRECCION";
        columnas[3]="NIT";
        columnas[4]="CIUDAD";
        columnas_eliminar[0]=5;
        columnas_eliminar[1]=5;
        columnas_eliminar[2]=5;
        columnas_eliminar[3]=5;
        
    }
    
    private void parametrosTablaInventario()
    {
        columnas_inventario[0] = "IDENTIFICACION";
        columnas_inventario[1] = "NOMBRE";
        columnas_inventario[2] = "IVA";
        columnas_inventario[3] = "MARCA";
        columnas_inventario[4] = "CATEGORIA";
        columnas_inventario[5] = "PRESENTACION";
        columnas_inventario[6] = "EXPIRACION";
        columnas_inventario[7] = "MEDICION";
        columnas_inventario[8] = "CANT";
        columnas_inventario[9] = "STOCK";
        columnas_inventario[10] = "BARRAS";
        columnas_inventario[11] = "PRECIO";
        columnas_inventario[12] = "PRE. IN";
        columnas_inventario[13] = "SUCURSAL";
        columnas_inventario[14] = "UTILIDAD C/U";
        columnas_inventario[15] = "PROVEEDOR";
        columnas_inventario[16] = "UTILIDAD";
        columnas_inventario[17] = "id_producto";
        columnas_inventario[18] = "nombre_producto";
        columnas_inventario[19] = "id_categoria";
        columnas_inventario[20] = "id_presentacion";
        columnas_inventario[21] = "id_marca";
        columnas_inventario[22] = "id_categoria";
        columnas_inventario[23] = "CATEGORIA";
        columnas_inventario[24] = "id_medicion";
        columnas_inventario[25] = "MEDICION";
        columnas_inventario[26] = "ID_PREENTACION";
        columnas_inventario[27] = "PRESENTACION";
        columnas_inventario[28] = "ELIMINALIMI1R1";
        columnas_inventario[29] = "ELISI";
        columnas_inventario[30] = "ELIMINARA";
        columnas_inventario[31] = "nueba";
        columnas_inventario[32] = "UTILIDAD2";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        ancho_columnas[7]=0;
        ancho_columnas[8]=10;
        ancho_columnas[9]=0;
        ancho_columnas[10]=50;
        ancho_columnas[11]=0;
        ancho_columnas[12]=50;
        
        for (int i = 0; i < columnas_eliminar_inventario.length; i++) {
            columnas_eliminar_inventario[i] = 16;
        }
         
        
    }
     private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
        
    }
    private void cargarTablaInventario()
    {
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_informeStock n = new Funciones_frm_informeStock();
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        try{
            parametrosTablaInventario();
        }
        catch(Exception ex)
        {
             JOptionPane.showMessageDialog(null, ex);
        }  
        try{
        tbl_productos_inventario.setModel(n.llenarTablaInventarioEmpresa(mi_empresa.getId_empresa(),columnas_inventario, ancho_columnas));
        tbl_productos_inventario.setDefaultRenderer(Object.class, new MiRender());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar_inventario.length; i++) {
            tbl_productos_inventario.getColumnModel().removeColumn(tbl_productos_inventario.getColumnModel().getColumn(columnas_eliminar_inventario[i]));
        }
         
         for (int i = 0; i < tbl_posiblees_proveedores.getModel().getRowCount(); i++) {
            
             
        }
    }
    
    private void conincidenciaProveedor()
    {
        parametrosTablaCoinsidenciasProveedor();
        Funciones_frm_informeStock fun = new Funciones_frm_informeStock();
        int id_producto_inventario=Integer.parseInt(tbl_productos_inventario.getModel().getValueAt(tbl_productos_inventario.getSelectedRow(), 0).toString());
        if(id_producto_inventario==0)
        {
            JOptionPane.showMessageDialog(null, "¡POR FAVOR SELECCIONA UNA FILA!");
        }
        else{
            tbl_posiblees_proveedores.setModel(fun.llenarCoincidenciasProveedor(id_producto_inventario, columnas, ancho_columnas));
            for (int i = 0; i < columnas_eliminar.length; i++) {
                tbl_posiblees_proveedores.getColumnModel().removeColumn(tbl_posiblees_proveedores.getColumnModel().getColumn(columnas_eliminar[i]));
            }
        }
    }
    
    private void datosProveedor()
    {
        Controlador.Funciones_frm_informeStock fun = new Funciones_frm_informeStock();
        proveedor = fun.datosProveedor(tbl_posiblees_proveedores.getValueAt(tbl_posiblees_proveedores.getSelectedRow(), 0));
        JOptionPane.showMessageDialog(null, "CONTACTO: "+proveedor.getContacto_empresa()+" \n MAIL: "
                + ""+proveedor.getMail_Proveedor()+"\n DIRECCION: "+proveedor.getDireccion_proveedor()+"\n TELEFONO: "+proveedor.getTelefono()," \n DATOS DE CONTACTO " ,1);
    }
    
     private void evento_txt_tecla_presionada_buscar_producto_inventario()
    {
        String comboBox = "";
        comboBox= cmb_opcion_Buscar.getSelectedItem().toString();
        Funciones_Entrada_Inventario n = new Funciones_Entrada_Inventario();        
        parametrosTablaInventario();
        consultarDatosUsuario();
        try{
        tbl_productos_inventario.setModel(n.buscarProductoInventario(usuario_activo.getId_sucursal(),txt_busccar_producto.getText(),comboBox,columnas_inventario, ancho_columnas));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar_inventario.length; i++) {

            tbl_productos_inventario.getColumnModel().removeColumn(tbl_productos_inventario.getColumnModel().getColumn(columnas_eliminar_inventario[i]));
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

        PopupMenu = new javax.swing.JPopupMenu();
        BuscarProveedor = new javax.swing.JMenuItem();
        BuscarproveedorMostrar = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_posiblees_proveedores = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_productos_inventario = new javax.swing.JTable();
        txt_busccar_producto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmb_opcion_Buscar = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        BuscarProveedor.setText("Buscar Proveedor");
        BuscarProveedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarProveedorActionPerformed(evt);
            }
        });
        PopupMenu.add(BuscarProveedor);
        BuscarProveedor.getAccessibleContext().setAccessibleName("BuscarProveedor");

        BuscarproveedorMostrar.setTitle("Buscarproveedor");
        BuscarproveedorMostrar.setMinimumSize(new java.awt.Dimension(504, 458));
        BuscarproveedorMostrar.setName("BuscarproveedorMostrar"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Posibles Proveedores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tbl_posiblees_proveedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_posiblees_proveedores.setToolTipText("Presiona Enter para Ver sus Datos!");
        tbl_posiblees_proveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_posiblees_proveedoresKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_posiblees_proveedores);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout BuscarproveedorMostrarLayout = new javax.swing.GroupLayout(BuscarproveedorMostrar.getContentPane());
        BuscarproveedorMostrar.getContentPane().setLayout(BuscarproveedorMostrarLayout);
        BuscarproveedorMostrarLayout.setHorizontalGroup(
            BuscarproveedorMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarproveedorMostrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        BuscarproveedorMostrarLayout.setVerticalGroup(
            BuscarproveedorMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarproveedorMostrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos en Stock", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tbl_productos_inventario.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_productos_inventario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_productos_inventario.setShowHorizontalLines(false);
        tbl_productos_inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_productos_inventarioMouseClicked(evt);
            }
        });
        tbl_productos_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_productos_inventarioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_productos_inventario);

        txt_busccar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busccar_productoKeyPressed(evt);
            }
        });

        jLabel1.setText("Buscar:");

        cmb_opcion_Buscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Identificacion", "Nombre", "Iva", "Marca", "Categoria", "Presentacion", "Expiracion", "Medicion", "Cantidad", "Stock", "Barra", "Precio", "Precio 2", "Sucursal", "Proveedor" }));

        jLabel2.setText("Por:");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634415_preview.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(11, 11, 11)
                        .addComponent(txt_busccar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_opcion_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_busccar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(cmb_opcion_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_productos_inventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productos_inventarioMouseClicked
        // TODO add your handling code here:
 
    }//GEN-LAST:event_tbl_productos_inventarioMouseClicked

    private void BuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarProveedorActionPerformed
        // TODO add your handling code here:
        conincidenciaProveedor();
        BuscarproveedorMostrar.setLocationRelativeTo(null);
        BuscarproveedorMostrar.setVisible(true);
    }//GEN-LAST:event_BuscarProveedorActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        conincidenciaProveedor();
        BuscarproveedorMostrar.setLocationRelativeTo(null);
        BuscarproveedorMostrar.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl_productos_inventarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_productos_inventarioKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER  ){
        conincidenciaProveedor();
        BuscarproveedorMostrar.setLocationRelativeTo(null);
        BuscarproveedorMostrar.setVisible(true);
        }
    }//GEN-LAST:event_tbl_productos_inventarioKeyPressed

    private void tbl_posiblees_proveedoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_posiblees_proveedoresKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER  ){
        datosProveedor();
        }
    }//GEN-LAST:event_tbl_posiblees_proveedoresKeyPressed

    private void txt_busccar_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busccar_productoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER  ){
        evento_txt_tecla_presionada_buscar_producto_inventario();
        }
    }//GEN-LAST:event_txt_busccar_productoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BuscarProveedor;
    private javax.swing.JDialog BuscarproveedorMostrar;
    private javax.swing.JPopupMenu PopupMenu;
    private javax.swing.JComboBox cmb_opcion_Buscar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_posiblees_proveedores;
    private javax.swing.JTable tbl_productos_inventario;
    private javax.swing.JTextField txt_busccar_producto;
    // End of variables declaration//GEN-END:variables
}
