/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Usuario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_Proveedores;
import Controlador.Funciones_frm_Usuario;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nestor1
 */
public class frm_Proveedores extends javax.swing.JInternalFrame {
    public String []columnas = new String[10];
        public int[]ancho_columnas = new int[15],columnas_eliminar = new int[3];
        Date now = new Date(System.currentTimeMillis());
        private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
         Constructor_Usuario usuario_activo= new Constructor_Usuario();
        private String user= null;

    /**
     * Creates new form frm_Producto
     */
    public frm_Proveedores(Object usuario) {
        user= usuario.toString();
        initComponents();
        llenarTablaCategoria();
        inicializarForm();
        cargarTable();
        this.setTitle("PROVEEDORES");
    }
    
    private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
    }
    
    private void inicializarForm()
    {
        Object[]items_ciudad = null;
        Controlador.Funciones_frm_Proveedores fun = new Funciones_frm_Proveedores();
        fun.llenarComboCiudad();
        TitledBorder borde = new TitledBorder(new EtchedBorder(),"Buscar");
        pan_medio.setBorder(borde);
        TitledBorder borde1 = new TitledBorder(new EtchedBorder(),"Formulario Proveedor");
        Panel_Arriba.setBorder(borde1);
          items_ciudad= fun.llenarComboCiudad();
        for (int t = 0; t < items_ciudad.length; t++) {
            cmb_ciudad_empresa.addItem(items_ciudad[t]);
        }
       
    }
    
    private void llenarTablaCategoria()
    {
        Controlador.Funciones_frm_Proveedores con = new Controlador.Funciones_frm_Proveedores();
        tbl_categoria.setModel(con.llenarTablaCategoria());
        JCheckBox che = new JCheckBox();
        tbl_categoria.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(che) );
        tbl_categoria.getColumnModel().getColumn(2).setCellRenderer(new Fuentes.Clase_CellRender() );
        tbl_categoria.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbl_categoria.getColumnModel().getColumn(2).setPreferredWidth(10);
    }
    
     private void parametrosTabla()
    {
        columnas[0] = "IDENTIFICACION";
        columnas[1] = "EMPRESA";
        columnas[2] = "CONTACTO";
        columnas[3] = "TELEFONO";
        columnas[4] = "DIRECCION";
        columnas[5] = "MAIL";
        columnas[6] = "NIT";
        columnas[7] = "CIUDAD";
        columnas[8] = "id_ciudad_2";
        columnas[9] = "CIUDAD2";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        columnas_eliminar[0] = 8;
        columnas_eliminar[1] = 8;
        columnas_eliminar[2] = 8;
       
    }
     
     private void cargarTable() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_Proveedores n = new Funciones_frm_Proveedores();
        
        try{
            consultarDatosUsuario();
            parametrosTabla();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_proveedor.setModel(n.llenarProveedor(usuario_activo.getId_sucursal(),columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_proveedor.getColumnModel().removeColumn(tbl_proveedor.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         
    }
     
      private void btn_agregar_Proveedor()
    {
       String empresa,contacto,telefono,direccion,email,nit; //Los campos dde texto del formulario
       Object ciudad;       
       consultarDatosUsuario();
       //Los combobox del formulario
       try
       {
       empresa=txt_nombre_empresa.getText();
       contacto = txt_nombre_contacto_empresa.getText();
       telefono = txt_telefono_empresa.getText();
       direccion = txt_direccion_empresa.getText();
       email= txt_email_empresa.getText();
       ciudad= cmb_ciudad_empresa.getSelectedItem();
       nit= txt_nit_empresa.getText();
       Funciones_frm_Proveedores clientes_funciones = new Funciones_frm_Proveedores();
           int aux=0,filas_llenas=0;
                     DefaultTableModel modelo = new DefaultTableModel();
                     for (int j = 0; j < tbl_categoria.getModel().getRowCount(); j++) 
                    {
                      modelo = (DefaultTableModel) tbl_categoria.getModel();
                        if((Boolean.valueOf(modelo.getValueAt(j, 2).toString()))==true)
                        {
                            filas_llenas++;
                        }
                    }
              Object[]categorias = new Object[filas_llenas];
              for (int i = 0; i < tbl_categoria.getModel().getRowCount(); i++) 
                {
                        modelo = (DefaultTableModel) tbl_categoria.getModel();
                        if((Boolean.valueOf(modelo.getValueAt(i, 2).toString()))==true)
                        {                            
                            categorias[aux]=modelo.getValueAt(i, 0);
                            aux++;
                        }
                    
                }

       if(clientes_funciones.insertarProveedor(usuario_activo.getId_sucursal(),usuario_activo.getId_usuario(),categorias,empresa, contacto, telefono, direccion, email, ciudad,nit)){
       cargarTable();
       JOptionPane.showMessageDialog(null, "Se registro a "+empresa+" exitosamente!");
       }
        
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
    }
      
      private void btn_eliminar_cliente()
    {
        consultarDatosUsuario();
        int aux= Integer.parseInt((tbl_proveedor.getModel().getValueAt(tbl_proveedor.getSelectedRow(), 0).toString()));
        Funciones_frm_Proveedores eliminar = new Funciones_frm_Proveedores();
        eliminar.eliminarProveedor(aux,usuario_activo.getId_usuario());
        cargarTable();
    }
      
      private void evento_click_tabla_proveedores()
      {
          txt_nombre_empresa.setText(tbl_proveedor.getValueAt(tbl_proveedor.getSelectedRow(), 1).toString());
          txt_nombre_contacto_empresa.setText(tbl_proveedor.getValueAt(tbl_proveedor.getSelectedRow(), 2).toString());
          txt_telefono_empresa.setText(tbl_proveedor.getValueAt(tbl_proveedor.getSelectedRow(), 3).toString());
          txt_direccion_empresa.setText(tbl_proveedor.getValueAt(tbl_proveedor.getSelectedRow(), 4).toString());
          txt_email_empresa.setText(tbl_proveedor.getValueAt(tbl_proveedor.getSelectedRow(), 5).toString());
          txt_nit_empresa.setText(tbl_proveedor.getValueAt(tbl_proveedor.getSelectedRow(), 6).toString());
          cmb_ciudad_empresa.setSelectedItem(tbl_proveedor.getValueAt(tbl_proveedor.getSelectedRow(), 6).toString());
      }
      
      private void mostrarCategoriasTabla()
      {
          try{
          Object[]permisos = null;
          consultarDatosUsuario();
          Controlador.Funciones_frm_Proveedores fun = new Funciones_frm_Proveedores();
          permisos=fun.categoriasProveedor(tbl_proveedor.getValueAt(tbl_proveedor.getSelectedRow(), 0));
           for (int i = 0; i < tbl_categoria.getRowCount(); i++) {
              for (int j = 0; j < permisos.length; j++) {
                if(tbl_categoria.getValueAt(i, 1).equals(permisos[j]))
                  tbl_categoria.setValueAt(true, i, 2);
              }
              
          }
          }
          catch(Exception ex)
          {
              JOptionPane.showMessageDialog(null, "SELECCIONE UN PROVEEDOR DE LA TABLA!");
          }
         
      }
      
      private void limpiarForm()
      {
           txt_nombre_empresa.setText(null);
          txt_nombre_contacto_empresa.setText(null);
          txt_telefono_empresa.setText(null);
          txt_direccion_empresa.setText(null);
          txt_email_empresa.setText(null);
          txt_nit_empresa.setText(null);
          cmb_ciudad_empresa.setSelectedItem(0);
          for (int i = 0; i < tbl_categoria.getRowCount(); i++) {
              tbl_categoria.setValueAt(false, i, 2);
          }
      }
      
      private void evento_presion_enter_buscar()
      {
         String comboBox = " ";
        comboBox= cmb_Buscar_Por.getSelectedItem().toString();
        Funciones_frm_Proveedores n = new Funciones_frm_Proveedores();        
        parametrosTabla();
        consultarDatosUsuario();
        try{
        tbl_proveedor.setModel(n.BuscarProveedor(usuario_activo.getId_sucursal(),columnas, ancho_columnas,txt_buscar_Proveedor.getText(),comboBox));
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_proveedor.getColumnModel().removeColumn(tbl_proveedor.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         tbl_proveedor.setRowSelectionInterval(0, 0);
      }
      
       private void actualizarProveedor()
       {
           consultarDatosUsuario();
        //bject[]Permisos,String nombre,String apellido,String cc, String telefono , String dir, Object id_cargo ,
//             Object descri,String fecha_cre,Object id_usuario_cre,Object id_sucursal,Object usuario, Object psw)
       String nit=txt_nit_empresa.getText().toString(),empresa = txt_nombre_empresa.getText().toString(),contacto=txt_nombre_contacto_empresa.getText().toString()
               ,tel=txt_telefono_empresa.getText().toString(),direccion=txt_direccion_empresa.getText().toString(),
               mail=txt_email_empresa.getText().toString(),ciudad=cmb_ciudad_empresa.getSelectedItem().toString(); //Los campos dde texto del formulario
       int id_usuario=usuario_activo.getId_usuario(); 
       Object id_sucursal=usuario_activo.getId_sucursal();
       //Los combobox del formulario
                        
                    Funciones_frm_Proveedores proveedores_funciones = new Funciones_frm_Proveedores();
                    int aux=0,filas_llenas=0;
                     DefaultTableModel modelo = new DefaultTableModel();
                     for (int j = 0; j < tbl_categoria.getModel().getRowCount(); j++) 
                    {
                      modelo = (DefaultTableModel) tbl_categoria.getModel();
                        if((Boolean.valueOf(modelo.getValueAt(j, 2).toString()))==true)
                        {
                            filas_llenas++;
                        }
                    }
                    Object[]permisos = new Object[filas_llenas];
                    for (int i = 0; i < tbl_categoria.getModel().getRowCount(); i++) 
                        {
                            modelo = (DefaultTableModel) tbl_categoria.getModel();
                            if((Boolean.valueOf(modelo.getValueAt(i, 2).toString()))==true)
                            {                            
                                permisos[aux]=modelo.getValueAt(i, 0);
                                aux++;
                            }                    
                        }
                            if(proveedores_funciones.modificarProveedor(Integer.parseInt(tbl_proveedor.getModel().getValueAt
        (tbl_proveedor.getSelectedRow(), 0).toString()),permisos,empresa.toString(), contacto.toString(), tel.toString(), direccion.toString(),
        mail.toString(),ciudad,id_sucursal,id_usuario,nit))
                            {   
                                cargarTable();
                                JOptionPane.showMessageDialog(null, "Se modifico a "+empresa+" exitosamente!");
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

        Panel_Arriba = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nombre_empresa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_nombre_contacto_empresa = new javax.swing.JTextField();
        txt_telefono_empresa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_email_empresa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmb_ciudad_empresa = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_categoria = new javax.swing.JTable();
        txt_direccion_empresa = new javax.swing.JTextField();
        txt_nit_empresa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_mostrar_categoria = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_proveedor = new javax.swing.JTable();
        pan_medio = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_buscar_Proveedor = new javax.swing.JTextField();
        cmb_Buscar_Por = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        btn_agregar_proveedor = new javax.swing.JButton();
        btn_limpiar_form_proveedor = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_actualizar_proveedor = new javax.swing.JButton();

        jLabel1.setText("Empresa:");

        jLabel8.setText("Contacto:");

        jLabel9.setText("Telefono:");

        txt_email_empresa.setText("@");

        jLabel11.setText("E-Mail:");

        jLabel12.setText("Categoria:");

        jLabel13.setText("Ciudad:");

        cmb_ciudad_empresa.setToolTipText("");

        tbl_categoria.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbl_categoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_categoria.setShowHorizontalLines(false);
        jScrollPane2.setViewportView(tbl_categoria);

        jLabel10.setText("Direccion:");

        jLabel14.setText("Nit:");

        btn_mostrar_categoria.setText("Mostrar Categorias");
        btn_mostrar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostrar_categoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_ArribaLayout = new javax.swing.GroupLayout(Panel_Arriba);
        Panel_Arriba.setLayout(Panel_ArribaLayout);
        Panel_ArribaLayout.setHorizontalGroup(
            Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ArribaLayout.createSequentialGroup()
                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ArribaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_email_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nombre_contacto_empresa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_direccion_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ArribaLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nit_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_mostrar_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_telefono_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_ciudad_empresa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Panel_ArribaLayout.setVerticalGroup(
            Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ArribaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(65, 65, 65))
            .addGroup(Panel_ArribaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_ArribaLayout.createSequentialGroup()
                                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_nombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_telefono_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txt_email_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(cmb_ciudad_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Panel_ArribaLayout.createSequentialGroup()
                                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txt_direccion_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txt_nit_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_nombre_contacto_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_mostrar_categoria))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Proveedores"));

        tbl_proveedor.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_proveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_proveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_proveedor);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel6.setText("Buscar:");

        txt_buscar_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_ProveedorKeyPressed(evt);
            }
        });

        cmb_Buscar_Por.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Identificacion", "Empresa", "Contacto", "Telefono", "Direccion", "Mail", "Nit", "Ciudad" }));

        jLabel7.setText("Por:");

        btn_agregar_proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634362_add.png"))); // NOI18N
        btn_agregar_proveedor.setToolTipText("");
        btn_agregar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_proveedorActionPerformed(evt);
            }
        });

        btn_limpiar_form_proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634912_delete-notes.png"))); // NOI18N
        btn_limpiar_form_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiar_form_proveedorActionPerformed(evt);
            }
        });

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634317_delete.png"))); // NOI18N
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_actualizar_proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634328_reload.png"))); // NOI18N
        btn_actualizar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_proveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_medioLayout = new javax.swing.GroupLayout(pan_medio);
        pan_medio.setLayout(pan_medioLayout);
        pan_medioLayout.setHorizontalGroup(
            pan_medioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_medioLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_Buscar_Por, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_actualizar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_limpiar_form_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_agregar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pan_medioLayout.setVerticalGroup(
            pan_medioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_medioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pan_medioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_agregar_proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_limpiar_form_proveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_actualizar_proveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_medioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txt_buscar_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(cmb_Buscar_Por, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pan_medio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Panel_Arriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Arriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pan_medio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_proveedorActionPerformed
        // TODO add your handling code here:
        btn_agregar_Proveedor();
        cargarTable();
    }//GEN-LAST:event_btn_agregar_proveedorActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        btn_eliminar_cliente();
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void tbl_proveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_proveedorMouseClicked
        // TODO add your handling code here:
        evento_click_tabla_proveedores();
    }//GEN-LAST:event_tbl_proveedorMouseClicked

    private void btn_mostrar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrar_categoriaActionPerformed
        // TODO add your handling code here:
        mostrarCategoriasTabla();
        
    }//GEN-LAST:event_btn_mostrar_categoriaActionPerformed

    private void btn_limpiar_form_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiar_form_proveedorActionPerformed
        // TODO add your handling code here:
        
        limpiarForm();
    }//GEN-LAST:event_btn_limpiar_form_proveedorActionPerformed

    private void txt_buscar_ProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_ProveedorKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            evento_presion_enter_buscar();
        }
        else
        {
        }
    }//GEN-LAST:event_txt_buscar_ProveedorKeyPressed

    private void btn_actualizar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_proveedorActionPerformed
        // TODO add your handling code here:
        actualizarProveedor();
        cargarTable();
    }//GEN-LAST:event_btn_actualizar_proveedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Arriba;
    private javax.swing.JButton btn_actualizar_proveedor;
    private javax.swing.JButton btn_agregar_proveedor;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_limpiar_form_proveedor;
    private javax.swing.JButton btn_mostrar_categoria;
    private javax.swing.JComboBox cmb_Buscar_Por;
    private javax.swing.JComboBox cmb_ciudad_empresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pan_medio;
    private javax.swing.JTable tbl_categoria;
    private javax.swing.JTable tbl_proveedor;
    private javax.swing.JTextField txt_buscar_Proveedor;
    private javax.swing.JTextField txt_direccion_empresa;
    private javax.swing.JTextField txt_email_empresa;
    private javax.swing.JTextField txt_nit_empresa;
    private javax.swing.JTextField txt_nombre_contacto_empresa;
    private javax.swing.JTextField txt_nombre_empresa;
    private javax.swing.JTextField txt_telefono_empresa;
    // End of variables declaration//GEN-END:variables
}
