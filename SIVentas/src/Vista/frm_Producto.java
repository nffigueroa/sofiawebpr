/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Usuario;
import Controlador.Funciones_frm_producto;
import javax.swing.table.TableModel;
import java.lang.*;
import javax.swing.JOptionPane;
import Controlador.*;
import groovy.xml.Entity;
import java.sql.ResultSet;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Nestor1
 */
public class frm_Producto extends javax.swing.JInternalFrame {
    
        public String []columnas = new String[14],columnas2 = new String[2],columnas3 = new String[2];
        public int[]ancho_columnas = new int[15],columnas_eliminar = new int[9];
        Constructor_Usuario usuario_activo= new Constructor_Usuario();
        private String user= null;
    /**
     * Creates new form frm_Producto
     */
    public frm_Producto(Object usuari) {
        initComponents();
        user= usuari.toString();
        inicializarForm();
        cargarTable();
        this.setTitle("PRODUCTO");
        cargarTableCategoria();
        
    }
    private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
        
    }
    
//    Oculta el campo donde estara el id_producto, llena los comboBox directamente de la base de datos
//    Por medio del controlador
    private void inicializarForm() 
    {
        TitledBorder borde3 = new TitledBorder(new EtchedBorder(),"Productos");
        pan_formulario_producto.setBorder(borde3);
        
        TitledBorder borde2 = new TitledBorder(new EtchedBorder(),"Buscar Productos");
        pan_buscar_producto.setBorder(borde2);
        
        TitledBorder borde1 = new TitledBorder(new EtchedBorder(),"Tabla Productos");
        pan_tabla_producto.setBorder(borde1);
        
        txt_id_producto.setVisible(false);
        Object [] items_categoria,items_medicion,items_marca,items_presentacion;
        Controlador.Funciones_frm_producto funciones_producto= new Funciones_frm_producto();
        
        items_categoria= funciones_producto.llenarComboCategoria();
        for (int t = 0; t < items_categoria.length; t++) {
            cmb_tipo_producto.addItem(items_categoria[t]);
        }
        items_medicion= funciones_producto.llenarComboMedicion();
        for (int t = 0; t < items_medicion.length; t++) {
            cmb_medicion_producto.addItem(items_medicion[t]);
        }
        items_marca= funciones_producto.llenarComboMarca();
        for (int t = 0; t < items_marca.length; t++) {
            cmb_marca_producto.addItem(items_marca[t]);
        }
        items_presentacion= funciones_producto.llenarComboPresentacion();
        for (int t = 0; t < items_presentacion.length; t++) {
            cmb_presentacion_producto.addItem(items_presentacion[t]);
        }
    }
   
    private void actualizarComboCategoria()
    {
         Object[] items_categoria, items_medicion, items_marca, items_presentacion;
        Controlador.Funciones_frm_producto funciones_producto = new Funciones_frm_producto();

        items_categoria = funciones_producto.llenarComboCategoria();
        for (int t = 0; t < items_categoria.length; t++) {
            cmb_tipo_producto.addItem(items_categoria[t]);
        }
    }
    
    private void actualizarComboMarca()
    {
         Object[] items_categoria, items_medicion, items_marca, items_presentacion;
        Controlador.Funciones_frm_producto funciones_producto = new Funciones_frm_producto();

         items_marca= funciones_producto.llenarComboMarca();
        for (int t = 0; t < items_marca.length; t++) {
            cmb_marca_producto.addItem(items_marca[t]);
        }
    }
    
    public void parametrosTabla()
    {
        columnas[0] = "IDENTIFICACION";
        columnas[1] = "NOMBRE";
        columnas[2] = "id_categoria";
        columnas[3] = "idme";
        columnas[4] = "id_pre";
        columnas[5] = "id_marc";
        columnas[6] = "id_categ";
        columnas[7] = "CATEGORIA";
        columnas[8] = "id_medi";
        columnas[9] = "MEDICION";
        columnas[10] = "id_pre";
        columnas[11] = "PRESENTACION";
        columnas[12] = "id_Marca";
        columnas[13] = "MARCA";
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
        columnas_eliminar[0] = 2;
        columnas_eliminar[1] = 2;
        columnas_eliminar[2] = 2;
        columnas_eliminar[3] = 2;
        columnas_eliminar[4] = 2;
        columnas_eliminar[5] = 3;
        columnas_eliminar[6] = 4;
        columnas_eliminar[7] = 5;     
        columnas_eliminar[8] = 6;     
    }
    
    private void cargarTable() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_producto n = new Funciones_frm_producto();
        consultarDatosUsuario();
        try{
            parametrosTabla();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_producto.setModel(n.llenarProductos(usuario_activo.getId_sucursal(),columnas, ancho_columnas));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_producto.getColumnModel().removeColumn(tbl_producto.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         
    }

    private void btn_eliminar_producto()
    {
        int aux= Integer.parseInt((tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 0).toString()));
        Funciones_frm_producto eliminar = new Funciones_frm_producto();
        eliminar.eliminarProducto(aux);
        cargarTable();
    }
    
    private void btn_agregar_producto()
    {
       String nombre,fecha,usuario_creacion,hora;
       Object categoria,medicion,marca,presentacion;
       
       usuario_creacion=user;
       fecha=null;
       nombre=txt_nombre_producto.getText();
       categoria = cmb_tipo_producto.getSelectedItem();
       medicion = cmb_medicion_producto.getSelectedItem();
       marca = cmb_marca_producto.getSelectedItem();
       presentacion= cmb_presentacion_producto.getSelectedItem();
if(validarForm(nombre, cmb_tipo_producto.getSelectedIndex(), cmb_medicion_producto.getSelectedIndex(), cmb_marca_producto.getSelectedIndex(), cmb_presentacion_producto.getSelectedIndex())==true){
       Funciones_frm_producto producto_funciones = new Funciones_frm_producto();
       producto_funciones.insertarProducto(nombre, fecha, usuario_creacion, categoria, marca, medicion, presentacion);
       cargarTable();
       JOptionPane.showMessageDialog(null, "Se registro "+nombre+" exitosamente!");
        }
       
    }
    
    private boolean validarForm(String nombre,Object categoria, Object medicion, Object marca, Object presentacion)
    {
        if(nombre.equals(""))
        {
            JOptionPane.showMessageDialog(null, "El campo NOMBRE no puede estar vacio!");
            txt_nombre_producto.requestFocus();
            return false;
        }
        else
            {
                if(Integer.parseInt(categoria.toString())==0)
                {
                    JOptionPane.showMessageDialog(null, "Seleccione una CATEGORIA");
                    return false;
                }
                else
                {
                    if(Integer.parseInt(medicion.toString())==0)
                    {
                        JOptionPane.showMessageDialog(null, "Seleccione una MEDICION");
                        return false;
                    }
                    else
                    {
                        if(Integer.parseInt(marca.toString())==0)
                        {
                            JOptionPane.showMessageDialog(null, "Seleccione una MARCA");
                            return false;
                        }
                        else
                        {
                            if(Integer.parseInt(presentacion.toString())==0)
                            {
                                JOptionPane.showMessageDialog(null, "Seleccione una PRESENTACION");
                                return false;
                            }
                            else
                            {
                                return true;
                            }
                        }
                    }
                }
            }
    }
    
    
    //Devuelve todos los campos del formulario a sus valores por defecto, y habilita el boton de agregar.
    private void btn_limpiar_campos()
    {
        cmb_agregar_producto.setEnabled(true);
        txt_nombre_producto.setText(null);
        cmb_tipo_producto.setSelectedIndex(0);
        cmb_marca_producto.setSelectedIndex(0);
        cmb_medicion_producto.setSelectedIndex(0);
        cmb_presentacion_producto.setSelectedIndex(0);
        txt_nombre_producto.requestFocus();
    }
    
    //Al seleccionar una final de la tabla con el mouse, se llena automaticamente el formulario inicial con sus comboBox
    //Ademas deshabilita el boton de agregar lo cual no esta permitido, se entiende que este proceso es para actualizar
    private void tecla_enter_buscar()
    {
    cmb_agregar_producto.setEnabled(false);
    txt_id_producto.setText(tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 0).toString());
    txt_nombre_producto.setText(tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 1).toString());
    cmb_tipo_producto.setSelectedIndex(Integer.parseInt(tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 2).toString()));
    cmb_marca_producto.setSelectedIndex(Integer.parseInt(tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 5).toString()));
    cmb_medicion_producto.setSelectedIndex(Integer.parseInt(tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 3).toString()));
    cmb_presentacion_producto.setSelectedIndex(Integer.parseInt(tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 4).toString()));
        
    }
    
    private void actualizar_producto()
    {
       String nombre;
       Object categoria,medicion,marca,presentacion,id_producto;
       id_producto= txt_id_producto.getText();
       nombre=txt_nombre_producto.getText();
       categoria = cmb_tipo_producto.getSelectedItem();
       medicion = cmb_medicion_producto.getSelectedItem();
       marca = cmb_marca_producto.getSelectedItem();
       presentacion= cmb_presentacion_producto.getSelectedItem();
       Funciones_frm_producto producto_funciones = new Funciones_frm_producto();
       producto_funciones.actualizarProducto(id_producto,nombre, categoria, marca, medicion, presentacion);
       cargarTable();
       JOptionPane.showMessageDialog(null, "Se Actualizo "+nombre+" exitosamente!");
    }
    
    private void evento_txt_tecla_presionada()
    {
        String comboBox = "";
        comboBox= cmb_por_producto.getSelectedItem().toString();
        Funciones_frm_producto n = new Funciones_frm_producto();        
        parametrosTabla();
        try{
        tbl_producto.setModel(n.buscarProducto(usuario_activo.getId_sucursal(),txt_buscar_producto.getText(),comboBox,columnas, ancho_columnas));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length-1; i++) {

            tbl_producto.getColumnModel().removeColumn(tbl_producto.getColumnModel().getColumn(columnas_eliminar[i]));
        }
    }
    
    
    private void inicializarForm2()
    {
        tbl_categoria.getColumnModel().getColumn(0).setPreferredWidth(5);
    }
    
     private void parametrosTabla2()
    {
        columnas2[0] = "IDENTIFICACION";
        columnas2[1] = "CATEGORIA";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
    }
    
    private void cargarTableCategoria() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
           Funciones_frm_categoria categoria= new Funciones_frm_categoria();
        
        try{
            parametrosTabla2();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_categoria.setModel(categoria.llenarFormaPago(columnas2, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         
    }
    
     private void btn_agregar_categoria()
    {
       consultarDatosUsuario();
       try
       {
       Funciones_frm_categoria categoria = new Funciones_frm_categoria();
       categoria.InsertarCategoria(txt_categoria1.getText(),usuario_activo.getId_usuario());
       cargarTableCategoria();
       JOptionPane.showMessageDialog(null, "Se Registro Exitosamente!");
  
       }
       catch(Exception ex)
       {
           JOptionPane.showMessageDialog(null, ex.getMessage());
           ex.printStackTrace();
       }

       
    }
     
      private void parametrosTabla3()
    {
        columnas3[0] = "IDENTIFICACION";
        columnas3[1] = "MARCA";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
       
    }
    
    private void cargarTableMarcas() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
         Funciones_forma_pago  pago= new Funciones_forma_pago();
        
        try{
            parametrosTabla3();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_forma_pago.setModel(pago.llenarFormaPago(columnas3, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         
    }
    
     private void btn_agregar_Forma_Pago()
    {
       String marca; //Los campos dde texto del formulario
       consultarDatosUsuario();
       try
       {
       
       marca=txt_marca.getText();
       
       Funciones_forma_pago forma_pago = new Funciones_forma_pago();
       forma_pago.insertarFormaPago(marca,usuario_activo.getId_usuario());
       cargarTableMarcas();
       JOptionPane.showMessageDialog(null, "Se registro a "+marca+" exitosamente!");
  
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
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

        dlg_agregar_categoria = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_categoria = new javax.swing.JTable();
        pan_abajo1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_categoria1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        dlg_agregar_marca = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_marca = new javax.swing.JTextField();
        btn_guardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_forma_pago = new javax.swing.JTable();
        pan_formulario_producto = new javax.swing.JPanel();
        lbl_nombre_producto = new javax.swing.JLabel();
        lbl_tipo_producto = new javax.swing.JLabel();
        txt_nombre_producto = new javax.swing.JTextField();
        cmb_tipo_producto = new javax.swing.JComboBox();
        lbl_medicion_producto = new javax.swing.JLabel();
        cmb_medicion_producto = new javax.swing.JComboBox();
        lbl_marca = new javax.swing.JLabel();
        cmb_marca_producto = new javax.swing.JComboBox();
        cmb_presentacion_producto = new javax.swing.JComboBox();
        lbl_presentacion_producto = new javax.swing.JLabel();
        cmd_agregar_categoria = new javax.swing.JButton();
        cmb_agregar_marca = new javax.swing.JButton();
        pan_tabla_producto = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_producto = new javax.swing.JTable();
        pan_buscar_producto = new javax.swing.JPanel();
        lbl_buscar_producto = new javax.swing.JLabel();
        txt_buscar_producto = new javax.swing.JTextField();
        cmb_por_producto = new javax.swing.JComboBox();
        lbl_por_produccto = new javax.swing.JLabel();
        cmb_agregar_producto = new javax.swing.JButton();
        cmb_limpiar_producto = new javax.swing.JButton();
        btn_eliminar_producto = new javax.swing.JButton();
        btn_actualizar_producto = new javax.swing.JButton();
        txt_id_producto = new javax.swing.JTextField();

        dlg_agregar_categoria.setMaximumSize(new java.awt.Dimension(322, 379));
        dlg_agregar_categoria.setMinimumSize(new java.awt.Dimension(322, 379));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Categoria"));

        tbl_categoria.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_categoria.setShowHorizontalLines(false);
        jScrollPane2.setViewportView(tbl_categoria);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        pan_abajo1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingresar"));

        jLabel2.setText("Categoria:");

        jButton6.setText("Guardar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_abajo1Layout = new javax.swing.GroupLayout(pan_abajo1);
        pan_abajo1.setLayout(pan_abajo1Layout);
        pan_abajo1Layout.setHorizontalGroup(
            pan_abajo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_abajo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pan_abajo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_abajo1Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_categoria1))
                .addContainerGap())
        );
        pan_abajo1Layout.setVerticalGroup(
            pan_abajo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_abajo1Layout.createSequentialGroup()
                .addGroup(pan_abajo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_categoria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dlg_agregar_categoriaLayout = new javax.swing.GroupLayout(dlg_agregar_categoria.getContentPane());
        dlg_agregar_categoria.getContentPane().setLayout(dlg_agregar_categoriaLayout);
        dlg_agregar_categoriaLayout.setHorizontalGroup(
            dlg_agregar_categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_agregar_categoriaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(dlg_agregar_categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan_abajo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlg_agregar_categoriaLayout.setVerticalGroup(
            dlg_agregar_categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_agregar_categoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_abajo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dlg_agregar_marca.setMaximumSize(new java.awt.Dimension(274, 380));
        dlg_agregar_marca.setMinimumSize(new java.awt.Dimension(274, 380));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Ingresar"), "Ingresar"));

        jLabel3.setText("Marca:");

        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_guardar)
                        .addGap(0, 134, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_marca)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_guardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Marcas"));

        tbl_forma_pago.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_forma_pago.setShowHorizontalLines(false);
        jScrollPane3.setViewportView(tbl_forma_pago);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dlg_agregar_marcaLayout = new javax.swing.GroupLayout(dlg_agregar_marca.getContentPane());
        dlg_agregar_marca.getContentPane().setLayout(dlg_agregar_marcaLayout);
        dlg_agregar_marcaLayout.setHorizontalGroup(
            dlg_agregar_marcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_agregar_marcaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlg_agregar_marcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dlg_agregar_marcaLayout.setVerticalGroup(
            dlg_agregar_marcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_agregar_marcaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbl_nombre_producto.setText("Nombre:");

        lbl_tipo_producto.setText("Categoria:");

        txt_nombre_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombre_productoKeyPressed(evt);
            }
        });

        cmb_tipo_producto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar.." }));

        lbl_medicion_producto.setText("Medicion");

        cmb_medicion_producto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "..." }));
        cmb_medicion_producto.setToolTipText("");

        lbl_marca.setText("Marca");

        cmb_marca_producto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));

        cmb_presentacion_producto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));

        lbl_presentacion_producto.setText("Presentacion");

        cmd_agregar_categoria.setText("+");
        cmd_agregar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd_agregar_categoriaActionPerformed(evt);
            }
        });

        cmb_agregar_marca.setText("+");
        cmb_agregar_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_agregar_marcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_formulario_productoLayout = new javax.swing.GroupLayout(pan_formulario_producto);
        pan_formulario_producto.setLayout(pan_formulario_productoLayout);
        pan_formulario_productoLayout.setHorizontalGroup(
            pan_formulario_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_formulario_productoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_nombre_producto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_tipo_producto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_formulario_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_formulario_productoLayout.createSequentialGroup()
                        .addComponent(cmb_tipo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_medicion_producto))
                    .addComponent(cmd_agregar_categoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_medicion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_marca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_formulario_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_formulario_productoLayout.createSequentialGroup()
                        .addComponent(cmb_marca_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_presentacion_producto))
                    .addComponent(cmb_agregar_marca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_presentacion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pan_formulario_productoLayout.setVerticalGroup(
            pan_formulario_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_formulario_productoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pan_formulario_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombre_producto)
                    .addComponent(lbl_tipo_producto)
                    .addComponent(txt_nombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_tipo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_medicion_producto)
                    .addComponent(cmb_medicion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_marca)
                    .addComponent(cmb_marca_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_presentacion_producto)
                    .addComponent(cmb_presentacion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(pan_formulario_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmd_agregar_categoria)
                    .addComponent(cmb_agregar_marca)))
        );

        tbl_producto.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_productoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_producto);

        javax.swing.GroupLayout pan_tabla_productoLayout = new javax.swing.GroupLayout(pan_tabla_producto);
        pan_tabla_producto.setLayout(pan_tabla_productoLayout);
        pan_tabla_productoLayout.setHorizontalGroup(
            pan_tabla_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_tabla_productoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pan_tabla_productoLayout.setVerticalGroup(
            pan_tabla_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_tabla_productoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lbl_buscar_producto.setText("Buscar:");

        txt_buscar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_productoKeyPressed(evt);
            }
        });

        cmb_por_producto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Categoria", "Marca", "Presentacion", "Medicion" }));

        lbl_por_produccto.setText("Por:");

        cmb_agregar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634362_add.png"))); // NOI18N
        cmb_agregar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_agregar_productoActionPerformed(evt);
            }
        });

        cmb_limpiar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634912_delete-notes.png"))); // NOI18N
        cmb_limpiar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_limpiar_productoActionPerformed(evt);
            }
        });

        btn_eliminar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634317_delete.png"))); // NOI18N
        btn_eliminar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_productoActionPerformed(evt);
            }
        });

        btn_actualizar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634328_reload.png"))); // NOI18N
        btn_actualizar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_productoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_buscar_productoLayout = new javax.swing.GroupLayout(pan_buscar_producto);
        pan_buscar_producto.setLayout(pan_buscar_productoLayout);
        pan_buscar_productoLayout.setHorizontalGroup(
            pan_buscar_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_buscar_productoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lbl_buscar_producto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_por_produccto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_por_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_eliminar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_actualizar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_limpiar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_agregar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pan_buscar_productoLayout.setVerticalGroup(
            pan_buscar_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_buscar_productoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_buscar_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_agregar_producto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_actualizar_producto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmb_limpiar_producto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_eliminar_producto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_buscar_productoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pan_buscar_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_buscar_producto)
                            .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_por_produccto)
                            .addComponent(cmb_por_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan_buscar_producto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan_formulario_producto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan_tabla_producto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pan_formulario_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_tabla_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_eliminar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_productoActionPerformed
        // TODO add your handling code here:
        btn_eliminar_producto();
        
    }//GEN-LAST:event_btn_eliminar_productoActionPerformed

    private void cmb_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_agregar_productoActionPerformed
        // TODO add your handling code here:
      btn_agregar_producto();
       
    }//GEN-LAST:event_cmb_agregar_productoActionPerformed

    private void cmb_limpiar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_limpiar_productoActionPerformed
        // TODO add your handling code here:
        btn_limpiar_campos();
       
    }//GEN-LAST:event_cmb_limpiar_productoActionPerformed

    private void tbl_productoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productoMousePressed
        // TODO add your handling code here:
      tecla_enter_buscar();
      
    }//GEN-LAST:event_tbl_productoMousePressed

    private void btn_actualizar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_productoActionPerformed
        // TODO add your handling code here:
        actualizar_producto();
        
    }//GEN-LAST:event_btn_actualizar_productoActionPerformed

    private void txt_buscar_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_productoKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyChar()==evt.VK_ENTER)
        {
            evento_txt_tecla_presionada();
        }
        else
        {
            
        }
        
    }//GEN-LAST:event_txt_buscar_productoKeyPressed

    private void txt_nombre_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_productoKeyPressed
        // TODO add your handling code here:
        txt_nombre_producto.setText(txt_nombre_producto.getText().toUpperCase());
    }//GEN-LAST:event_txt_nombre_productoKeyPressed

    private void cmd_agregar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd_agregar_categoriaActionPerformed
        // TODO add your handling code here:
        dlg_agregar_categoria.setVisible(true);
        cargarTableCategoria();
    }//GEN-LAST:event_cmd_agregar_categoriaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        btn_agregar_categoria();
        actualizarComboCategoria();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        btn_agregar_Forma_Pago();
        actualizarComboMarca();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void cmb_agregar_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_agregar_marcaActionPerformed
        // TODO add your handling code here:
        dlg_agregar_marca.setVisible(true);
        cargarTableMarcas();
        
        
    }//GEN-LAST:event_cmb_agregar_marcaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar_producto;
    private javax.swing.JButton btn_eliminar_producto;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton cmb_agregar_marca;
    private javax.swing.JButton cmb_agregar_producto;
    private javax.swing.JButton cmb_limpiar_producto;
    private javax.swing.JComboBox cmb_marca_producto;
    private javax.swing.JComboBox cmb_medicion_producto;
    private javax.swing.JComboBox cmb_por_producto;
    private javax.swing.JComboBox cmb_presentacion_producto;
    private javax.swing.JComboBox cmb_tipo_producto;
    private javax.swing.JButton cmd_agregar_categoria;
    private javax.swing.JDialog dlg_agregar_categoria;
    private javax.swing.JDialog dlg_agregar_marca;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_buscar_producto;
    private javax.swing.JLabel lbl_marca;
    private javax.swing.JLabel lbl_medicion_producto;
    private javax.swing.JLabel lbl_nombre_producto;
    private javax.swing.JLabel lbl_por_produccto;
    private javax.swing.JLabel lbl_presentacion_producto;
    private javax.swing.JLabel lbl_tipo_producto;
    private javax.swing.JPanel pan_abajo;
    private javax.swing.JPanel pan_abajo1;
    private javax.swing.JPanel pan_buscar_producto;
    private javax.swing.JPanel pan_formulario_producto;
    private javax.swing.JPanel pan_tabla_producto;
    private javax.swing.JTable tbl_categoria;
    private javax.swing.JTable tbl_forma_pago;
    private javax.swing.JTable tbl_producto;
    private javax.swing.JTextField txt_buscar_producto;
    private javax.swing.JTextField txt_categoria;
    private javax.swing.JTextField txt_categoria1;
    private javax.swing.JTextField txt_id_producto;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_nombre_producto;
    // End of variables declaration//GEN-END:variables
}
