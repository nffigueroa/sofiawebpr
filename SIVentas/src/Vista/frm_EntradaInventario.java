/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Usuario;
import Controlador.Funciones_frm_producto;
import Controlador.*;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Nestor1
 */
public class frm_EntradaInventario extends javax.swing.JInternalFrame {
    
    public String []columnas_inventario = new String[30];
    public String []columnas = new String[14];
    public int[]ancho_columnas = new int[15],columnas_eliminar = new int[9], columnas_eliminar_inventario =new int[18];
    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
        private String user= null;
        Object[] opciones;

    /**
     * Creates new form frm_Producto
     * @param usuario
     */
    public frm_EntradaInventario(Object usuario) {
        initComponents();
        user=usuario.toString();
        inicializarFormEntradaInventario();
        cargarTablaProductos();
        cargarTablaInventario();
        this.setTitle("ENTRADA/SALIDA INVENTARIO");
        
    }
    
    
    
    
    
     private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
        
    }
    private void parametrosTabla()
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
    
    private void inicializarFormEntradaInventario()
    {
        consultarDatosUsuario();
        txt_nombre_entrada_producto.setEnabled(false);
        txt_marca_entrada_producto.setEnabled(false);
        txt_medicion_entrada_producto.setEnabled(false);
        txt_presentacion_entrada_producto.setEnabled(false);
        txt_categoria_producto.setEnabled(false);
        txt_id_categoria.setVisible(false);
        txt_id_marca.setVisible(false);
        txt_id_medicion.setVisible(false);
        txt_id_presentacion.setVisible(false);
        txt_id_producto.setVisible(false);
        txt_id_entrada_inventario.setVisible(false);
        TitledBorder borde2 = new TitledBorder(new EtchedBorder(),"Formulario Entrada Inventario");
        pan_arriba.setBorder(borde2);
        TitledBorder borde3 = new TitledBorder(new EtchedBorder(),"Productos");
        pan_abajo.setBorder(borde3);
        Object [] items_proveedor,items_sucursal;
        Funciones_Entrada_Inventario funciones_inventario= new Funciones_Entrada_Inventario();
        
        items_proveedor= funciones_inventario.llenarComboProveedor();
        for (int t = 0; t < items_proveedor.length; t++) {
            cmb_proveedor_entrada_producto.addItem(items_proveedor[t]);
        }
        items_sucursal= funciones_inventario.llenarComboSucursal(usuario_activo.getId_sucursal());
        for (int t = 0; t < items_sucursal.length; t++) {
            cmb_sucursal_entrada_producto.addItem(items_sucursal[t]);
        }
        
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
        columnas_inventario[12] = "PRECIO_2";
        columnas_inventario[13] = "SUCURSAL";
        columnas_inventario[14] = "PROVEEDOR";
        columnas_inventario[15] = "id_producto";
        columnas_inventario[16] = "nombre_producto";
        columnas_inventario[17] = "id_categoria";
        columnas_inventario[18] = "id_presentacion";
        columnas_inventario[19] = "id_marca";
        columnas_inventario[20] = "id_categoria";
        columnas_inventario[21] = "CATEGORIA";
        columnas_inventario[22] = "id_medicion";
        columnas_inventario[23] = "MEDICION";
        columnas_inventario[24] = "ID_PREENTACION";
        columnas_inventario[25] = "PRESENTACION";
        columnas_inventario[26] = "ELIMINALIMI1R1";
        columnas_inventario[27] = "ELISI";
        columnas_inventario[28] = "ELIMINARA";
        columnas_inventario[29] = "nueba";
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
            columnas_eliminar_inventario[i] = 15;
        }
//        columnas_eliminar_inventario[0] = 15;
//        columnas_eliminar_inventario[1] = 15;
//        columnas_eliminar_inventario[2] = 15;
//        columnas_eliminar_inventario[3] = 15;
//        columnas_eliminar_inventario[4] = 15;
//        columnas_eliminar_inventario[5] = 15;
//        columnas_eliminar_inventario[6] = 15;     
//        columnas_eliminar_inventario[7] = 15;     
//        columnas_eliminar_inventario[8] = 15;     
//        columnas_eliminar_inventario[9] = 15;     
//        columnas_eliminar_inventario[10] = 15;     
//        columnas_eliminar_inventario[11] = 15;     
//        columnas_eliminar_inventario[12] = 15;     
//        columnas_eliminar_inventario[13] = 15;     
//        columnas_eliminar_inventario[14] = 15;        
//        columnas_eliminar_inventario[15] = 15;        
//        columnas_eliminar_inventario[16] = 15;        
//        columnas_eliminar_inventario[17] = 15;        
    }
    
    private void cargarTablaProductos()
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_producto n = new Funciones_frm_producto();
        
        try{
            consultarDatosUsuario();
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
    
    private void click_en_tbl_producto()
    {
    btn_agregar_entrada_producto.setEnabled(true);
    txt_id_producto.setText(tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 0).toString());
    txt_barras_entrada_producto.setText("");
    txt_cantidad_entrada_producto.setText("");
    txt_marca_entrada_producto.setText((tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(),13).toString()));
    txt_id_categoria.setText((tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 2).toString()));
    txt_categoria_producto.setText((tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 7).toString()));
    txt_nombre_entrada_producto.setText((tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 1).toString()));
    txt_id_medicion.setText((tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 3).toString()));
    txt_medicion_entrada_producto.setText((tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 9).toString()));
    txt_id_presentacion.setText((tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 4).toString()));
    txt_presentacion_entrada_producto.setText((tbl_producto.getModel().getValueAt(tbl_producto.getSelectedRow(), 11).toString()));
    txt_precio1_entrada_producto.setText("");
    spin_stock_entrada_inventario.setValue(1);
    txt_iva_entrada_producto.setText("");
    cmb_proveedor_entrada_producto.setSelectedIndex(0);
    cmb_sucursal_entrada_producto.setSelectedIndex(0);
    }
    
    private void click_en_tbl_entrada_inventario()
    {
        txt_cantidad_entrada_producto.setEnabled(false);
        btn_agregar_entrada_producto.setEnabled(false);
        txt_id_entrada_inventario.setText(tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 0).toString());
        txt_nombre_entrada_producto.setText(tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 1).toString());
        txt_iva_entrada_producto.setText(tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 2).toString());
        txt_marca_entrada_producto.setText(tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 3).toString());
        txt_categoria_producto.setText(tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 4).toString());
        txt_presentacion_entrada_producto.setText((tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(),5).toString()));
        jdate_expiracion_entrada_prodcuto.setDate((Date) (tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 6)));
        txt_medicion_entrada_producto.setText((tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 7).toString()));
        txt_cantidad_entrada_producto.setText((tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 8).toString()));
        spin_stock_entrada_inventario.setValue((tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 9)));
        txt_barras_entrada_producto.setText((tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 10).toString()));
        txt_precio1_entrada_producto.setText((tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 11).toString()));
        txt_precio2_entrada_producto.setText((tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 12).toString()));
        cmb_sucursal_entrada_producto.setSelectedItem(tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 13).toString());
        cmb_proveedor_entrada_producto.setSelectedItem(tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 14).toString());
    }
    
    private void evento_txt_tecla_presionada()
    {
        String comboBox = "";
        comboBox= cmb_buscar_por_producto.getSelectedItem().toString();
        Funciones_frm_producto n = new Funciones_frm_producto();        
        parametrosTabla();
        consultarDatosUsuario();
        try{
        tbl_producto.setModel(n.buscarProducto(usuario_activo.getId_sucursal(),txt_buscar_producto.getText(),comboBox,columnas, ancho_columnas));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_producto.getColumnModel().removeColumn(tbl_producto.getColumnModel().getColumn(columnas_eliminar[i]));
        }
    }
    
    private void cargarTablaInventario()
    {
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_Entrada_Inventario n = new Funciones_Entrada_Inventario();
        consultarDatosUsuario();
        
        try{
            parametrosTablaInventario();
//           lbl_valor_inventario.setText(String.valueOf(n.funcionCuantoDineroInventario(usuario_activo.getId_sucursal())));
//           lbl_ganacia_inventario.setText(String.valueOf(n.funcionCuantoGanaciaInventario(usuario_activo.getId_sucursal())));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_inventario.setModel(n.llenarTablaInventarioFunciones(usuario_activo.getId_sucursal(),columnas_inventario, ancho_columnas));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar_inventario.length; i++) {
            tbl_inventario.getColumnModel().removeColumn(tbl_inventario.getColumnModel().getColumn(columnas_eliminar_inventario[i]));
        }
    }
    
    private void actualizarEntradaInventario()
    {
        consultarDatosUsuario();
       String fecha = new SimpleDateFormat("yyyy-MM-dd").format(jdate_expiracion_entrada_prodcuto.getDate());
       Funciones_Entrada_Inventario funciones_inventario = new Funciones_Entrada_Inventario();
       funciones_inventario.actualizarProductoInventario(txt_cantidad_entrada_producto.getText(),spin_stock_entrada_inventario.getValue(), cmb_sucursal_entrada_producto.getSelectedItem(), cmb_proveedor_entrada_producto.getSelectedItem(),txt_barras_entrada_producto.getText(), txt_precio1_entrada_producto.getText(),txt_precio2_entrada_producto.getText(), txt_iva_entrada_producto.getText(), fecha,txt_id_entrada_inventario.getText(),usuario_activo.getId_usuario());
       cargarTablaInventario();
       JOptionPane.showMessageDialog(null, "Se Actualizo "+txt_nombre_entrada_producto.getText()+" exitosamente!");
    }
    
    private void registrarProductoInventario()
    {
        String fecha=null;
        consultarDatosUsuario();
        if(jdate_expiracion_entrada_prodcuto.getDate().equals(""))
        {
            
        }
        else
        {
            fecha = new SimpleDateFormat("yyyy-MM-dd").format(jdate_expiracion_entrada_prodcuto.getDate());
        }
        
        Funciones_Entrada_Inventario funciones_inventario = new Funciones_Entrada_Inventario();
        if(funciones_inventario.registrarProductoInventario(usuario_activo.getId_sucursal(),txt_id_producto.getText(), txt_cantidad_entrada_producto.getText(),spin_stock_entrada_inventario.getValue(), cmb_sucursal_entrada_producto.getSelectedItem(), cmb_proveedor_entrada_producto.getSelectedItem(),txt_barras_entrada_producto.getText(), Float.parseFloat(txt_precio1_entrada_producto.getText()),Float.parseFloat(txt_precio2_entrada_producto.getText()), txt_iva_entrada_producto.getText(), fecha,usuario_activo.getId_usuario())==true)
        {
            JOptionPane.showMessageDialog(null, "Se Registro "+txt_nombre_entrada_producto.getText()+" exitosamente!");
        }
        cargarTablaInventario();
    }
    
    private void limpiarFormInventario()
    {
        txt_cantidad_entrada_producto.setEnabled(true);
        spin_stock_entrada_inventario.setValue(0);
        btn_agregar_entrada_producto.setEnabled(true);
        txt_barras_entrada_producto.setText("");
        txt_buscar_producto.setText("");
        txt_cantidad_entrada_producto.setText("");
        txt_categoria_producto.setText("");
        txt_id_categoria.setText("");
        txt_id_entrada_inventario.setText("");
        txt_id_marca.setText("");
        txt_id_medicion.setText("");
        txt_id_presentacion.setText("");
        txt_id_producto.setText("");
        txt_iva_entrada_producto.setText("");
        txt_marca_entrada_producto.setText("");
        txt_medicion_entrada_producto.setText("");
        txt_nombre_entrada_producto.setText("");
        txt_precio1_entrada_producto.setText("");
        txt_precio2_entrada_producto.setText("");
        txt_presentacion_entrada_producto.setText("");
        cmb_sucursal_entrada_producto.setSelectedIndex(0);
        cmb_proveedor_entrada_producto.setSelectedIndex(0);
        jdate_expiracion_entrada_prodcuto.setDate(null);
    }
            
    private void evento_txt_tecla_presionada_buscar_producto_inventario()
    {
        
        String comboBox = "";
        comboBox= cmb_buscar_por_inventario_producto.getSelectedItem().toString();
        Funciones_Entrada_Inventario n = new Funciones_Entrada_Inventario();     
//        lbl_valor_inventario.setText(n.funcionCuantoDineroInventarioPorBusqueda(txt_buscar_producto_en_inventario.getText(), comboBox, usuario_activo.getId_sucursal()).toString());
//        lbl_ganacia_inventario.setText(n.funcionCuantoGanaciaInventarioPorBusqueda(txt_buscar_producto_en_inventario.getText(), comboBox, usuario_activo.getId_sucursal()).toString());
        parametrosTabla();
        consultarDatosUsuario();
        try{
        tbl_inventario.setModel(n.buscarProductoInventario(usuario_activo.getId_sucursal(),txt_buscar_producto_en_inventario.getText(),comboBox,columnas_inventario, ancho_columnas));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar_inventario.length; i++) {

            tbl_inventario.getColumnModel().removeColumn(tbl_inventario.getColumnModel().getColumn(columnas_eliminar_inventario[i]));
        }
    }
    
    private void eliminar_producto_inventario()
    {
        Funciones_Entrada_Inventario func = new Funciones_Entrada_Inventario();
        llenarOpciones();
        Object id_producto_inventario,nombre,id_motivo;
        id_producto_inventario=tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 0).toString();
        nombre=tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 1).toString();
        Object seleccion = JOptionPane.showInputDialog(null,"Seleccione opcion","ELIMINAR "+nombre+" de INVENTARIO",JOptionPane.QUESTION_MESSAGE,null,  // null para icono defecto
        opciones,opciones[0]);
        id_motivo= func.funcionConsultarIdMotivo(seleccion);
        Funciones_Entrada_Inventario n = new Funciones_Entrada_Inventario();
        n.eliminarProductoInventario(txt_id_entrada_inventario.getText(),date.format(now),hora.format(now),id_motivo);
        cargarTablaInventario();
    }
    
    private void llenarOpciones()
    {
         Funciones_Entrada_Inventario func = new Funciones_Entrada_Inventario();
        opciones= func.motivoEliminacionCombo();
    }
    
    private void descontar_producto()
    {
        consultarDatosUsuario();
        llenarOpciones();
        Controlador.Funciones_Entrada_Inventario descontarCantidad = new Funciones_Entrada_Inventario();
        Object seleccion = new Object();
        seleccion = JOptionPane.showInputDialog(null,"Seleccione opcion","Descontar Producto",JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        float cantidad = Float.parseFloat(JOptionPane.showInputDialog("INGRESE CANTIDAD"));
        descontarCantidad.descontarCantidad(tbl_inventario.getValueAt(tbl_inventario.getSelectedRow(), 0),cantidad,seleccion,usuario_activo.getId_usuario());
    }
    
    private void agregarCantidadProductoEx()
    {
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de "+tbl_inventario.getValueAt(tbl_inventario.getSelectedRow(), 1)+""));
        consultarDatosUsuario();
        Funciones_Entrada_Inventario fun = new Funciones_Entrada_Inventario();
        float cantidadTotal= cantidad +Math.round(Float.parseFloat(tbl_inventario.getValueAt(tbl_inventario.getSelectedRow(), 8).toString()));
        fun.agregarCantidadProductoExistente(Integer.parseInt(tbl_inventario.getValueAt(tbl_inventario.getSelectedRow(), 0).toString()), cantidadTotal, usuario_activo.getId_usuario(),cantidad);
        cargarTablaInventario();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan_abajo = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_producto = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_buscar_producto = new javax.swing.JTextField();
        cmb_buscar_por_producto = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txt_id_entrada_inventario = new javax.swing.JTextField();
        txt_id_categoria = new javax.swing.JTextField();
        txt_id_presentacion = new javax.swing.JTextField();
        txt_id_producto = new javax.swing.JTextField();
        txt_id_marca = new javax.swing.JTextField();
        txt_id_medicion = new javax.swing.JTextField();
        pan_arriba = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_nombre_entrada_producto = new javax.swing.JTextField();
        txt_cantidad_entrada_producto = new javax.swing.JTextField();
        txt_medicion_entrada_producto = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cmb_sucursal_entrada_producto = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        cmb_proveedor_entrada_producto = new javax.swing.JComboBox();
        txt_barras_entrada_producto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jtexfield = new javax.swing.JLabel();
        txt_precio1_entrada_producto = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_marca_entrada_producto = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_iva_entrada_producto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_inventario = new javax.swing.JTable();
        btn_eliminar_entrada_inventario = new javax.swing.JButton();
        btn_agregar_entrada_producto = new javax.swing.JButton();
        btn_limpiar_form_entrada_producto = new javax.swing.JButton();
        btn_actualizar_entrada_producto = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jdate_expiracion_entrada_prodcuto = new com.toedter.calendar.JDateChooser();
        jtexfield1 = new javax.swing.JLabel();
        txt_precio2_entrada_producto = new javax.swing.JTextField();
        spin_stock_entrada_inventario = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        txt_presentacion_entrada_producto = new javax.swing.JTextField();
        txt_categoria_producto = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        spin_cod_poroveedor = new javax.swing.JSpinner();
        btn_salir_entrada_inventario = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmb_buscar_por_inventario_producto = new javax.swing.JComboBox();
        txt_buscar_producto_en_inventario = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btn_agregar_cantidad_producto = new javax.swing.JButton();

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_productoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_productoMousePressed(evt);
            }
        });
        tbl_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_productoKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_producto);

        javax.swing.GroupLayout pan_abajoLayout = new javax.swing.GroupLayout(pan_abajo);
        pan_abajo.setLayout(pan_abajoLayout);
        pan_abajoLayout.setHorizontalGroup(
            pan_abajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_abajoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        pan_abajoLayout.setVerticalGroup(
            pan_abajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_abajoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jLabel6.setText("Buscar Producto:");

        txt_buscar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_productoKeyPressed(evt);
            }
        });

        cmb_buscar_por_producto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Categoria", "Marca", "Presentacion", "Medicion" }));

        jLabel7.setText("Por:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_buscar_por_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_id_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id_medicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_id_entrada_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id_presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_id_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_id_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_medicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(cmb_buscar_por_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_entrada_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jLabel12.setText("Nombre:");

        jLabel13.setText("Cantidad:");

        jLabel15.setText("Medicion:");

        jLabel16.setText("Stock:");

        jLabel17.setText("Suc.:");

        jLabel18.setText("Proveedor:");

        jLabel19.setText("Cogido:");

        jtexfield.setText("Precio V:");

        jLabel22.setText("Marca:");

        jLabel23.setText("IVA:");

        tbl_inventario.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_inventarioMousePressed(evt);
            }
        });
        tbl_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_inventarioKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_inventario);

        btn_eliminar_entrada_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634317_delete.png"))); // NOI18N
        btn_eliminar_entrada_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_entrada_inventarioActionPerformed(evt);
            }
        });

        btn_agregar_entrada_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634362_add.png"))); // NOI18N
        btn_agregar_entrada_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_entrada_productoActionPerformed(evt);
            }
        });

        btn_limpiar_form_entrada_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634912_delete-notes.png"))); // NOI18N
        btn_limpiar_form_entrada_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiar_form_entrada_productoActionPerformed(evt);
            }
        });

        btn_actualizar_entrada_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634328_reload.png"))); // NOI18N
        btn_actualizar_entrada_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_entrada_productoActionPerformed(evt);
            }
        });

        jLabel14.setText("Expiracion:");

        jtexfield1.setText("Precio C:");

        spin_stock_entrada_inventario.setModel(new javax.swing.SpinnerNumberModel(1.0d, 1.0d, 100.0d, 1.0d));

        jLabel20.setText("Presen.:");

        jLabel21.setText("Categoria:");

        spin_cod_poroveedor.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        spin_cod_poroveedor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin_cod_poroveedorStateChanged(evt);
            }
        });
        spin_cod_poroveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spin_cod_poroveedorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                spin_cod_poroveedorMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                spin_cod_poroveedorMousePressed(evt);
            }
        });
        spin_cod_poroveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spin_cod_poroveedorKeyPressed(evt);
            }
        });

        btn_salir_entrada_inventario.setText("Descontar Prod.");
        btn_salir_entrada_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salir_entrada_inventarioActionPerformed(evt);
            }
        });

        jLabel8.setText("Buscar:");

        jLabel9.setText("Por:");

        cmb_buscar_por_inventario_producto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Identificacion", "Nombre", "Iva", "Marca", "Categoria", "Presentacion", "Expiracion", "Medicion", "Cantidad", "Stock", "Barras", "Precio1", "Precio2", "Sucursal", "Proveedor" }));

        txt_buscar_producto_en_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_producto_en_inventarioKeyPressed(evt);
            }
        });

        btn_agregar_cantidad_producto.setText("Agregar Cant.");
        btn_agregar_cantidad_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_cantidad_productoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_arribaLayout = new javax.swing.GroupLayout(pan_arriba);
        pan_arriba.setLayout(pan_arribaLayout);
        pan_arribaLayout.setHorizontalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_arribaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_salir_entrada_inventario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pan_arribaLayout.createSequentialGroup()
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btn_limpiar_form_entrada_producto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(btn_eliminar_entrada_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_agregar_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_actualizar_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btn_agregar_cantidad_producto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0))
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pan_arribaLayout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_presentacion_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pan_arribaLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_buscar_producto_en_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmb_buscar_por_inventario_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(203, 203, 203)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_medicion_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createSequentialGroup()
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_nombre_entrada_producto, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                    .addComponent(txt_barras_entrada_producto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jtexfield))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_categoria_producto, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(txt_precio1_entrada_producto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtexfield1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_precio2_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cantidad_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addComponent(spin_stock_entrada_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22))
                            .addComponent(cmb_sucursal_entrada_producto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addComponent(txt_marca_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_iva_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spin_cod_poroveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmb_proveedor_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdate_expiracion_entrada_prodcuto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 126, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pan_arribaLayout.setVerticalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_arribaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txt_nombre_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(txt_categoria_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(txt_presentacion_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(txt_medicion_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(spin_stock_entrada_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(txt_marca_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)
                        .addComponent(txt_iva_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(jdate_expiracion_entrada_prodcuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_barras_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtexfield)
                    .addComponent(txt_precio1_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtexfield1)
                    .addComponent(txt_precio2_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txt_cantidad_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cmb_sucursal_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cmb_proveedor_entrada_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spin_cod_poroveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_buscar_producto_en_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cmb_buscar_por_inventario_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_eliminar_entrada_inventario)
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addComponent(btn_agregar_entrada_producto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_limpiar_form_entrada_producto)
                                    .addComponent(btn_actualizar_entrada_producto))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_salir_entrada_inventario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_agregar_cantidad_producto)
                        .addGap(68, 68, 68))
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pan_arriba, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pan_abajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pan_arriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_abajo, javax.swing.GroupLayout.PREFERRED_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productoMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tbl_productoMouseClicked

    private void tbl_productoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_productoMousePressed
        // TODO add your handling code here:
        
       click_en_tbl_producto();
    }//GEN-LAST:event_tbl_productoMousePressed

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

    private void spin_cod_poroveedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spin_cod_poroveedorMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_spin_cod_poroveedorMousePressed
    private void spin_cod_poroveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spin_cod_poroveedorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_spin_cod_poroveedorMouseClicked

    private void spin_cod_poroveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spin_cod_poroveedorMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_spin_cod_poroveedorMouseEntered

    private void spin_cod_poroveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spin_cod_poroveedorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_spin_cod_poroveedorKeyPressed

    private void spin_cod_poroveedorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin_cod_poroveedorStateChanged
        // TODO add your handling code here:
        
        int texto;
        
        texto = Integer.parseInt(spin_cod_poroveedor.getValue().toString());
        
        cmb_proveedor_entrada_producto.setSelectedIndex(texto);
    }//GEN-LAST:event_spin_cod_poroveedorStateChanged

    private void btn_agregar_entrada_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_entrada_productoActionPerformed
        // TODO add your handling code here:
        registrarProductoInventario();
    }//GEN-LAST:event_btn_agregar_entrada_productoActionPerformed

    private void btn_salir_entrada_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salir_entrada_inventarioActionPerformed
        // TODO add your handling code here:
        descontar_producto();
        cargarTablaInventario();
    }//GEN-LAST:event_btn_salir_entrada_inventarioActionPerformed

    private void txt_buscar_producto_en_inventarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_producto_en_inventarioKeyPressed
        // TODO add your handling code here:
          if(evt.getKeyChar()==evt.VK_ENTER)
        {
            evento_txt_tecla_presionada_buscar_producto_inventario();
        }
        else
        {
            
        }
        
    }//GEN-LAST:event_txt_buscar_producto_en_inventarioKeyPressed

    private void tbl_inventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_inventarioMousePressed
        // TODO add your handling code here:
        click_en_tbl_entrada_inventario();
    }//GEN-LAST:event_tbl_inventarioMousePressed

    private void btn_actualizar_entrada_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_entrada_productoActionPerformed
        // TODO add your handling code here:
        actualizarEntradaInventario();
        
    }//GEN-LAST:event_btn_actualizar_entrada_productoActionPerformed

    private void btn_limpiar_form_entrada_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiar_form_entrada_productoActionPerformed
        // TODO add your handling code here:
        limpiarFormInventario();
        
    }//GEN-LAST:event_btn_limpiar_form_entrada_productoActionPerformed

    private void btn_eliminar_entrada_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_entrada_inventarioActionPerformed
        // TODO add your handling code here:
        eliminar_producto_inventario();
    }//GEN-LAST:event_btn_eliminar_entrada_inventarioActionPerformed

    private void btn_agregar_cantidad_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_cantidad_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_agregar_cantidad_productoActionPerformed

    private void tbl_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_productoKeyPressed
        // TODO add your handling code here:
    
        
    }//GEN-LAST:event_tbl_productoKeyPressed

    private void tbl_inventarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_inventarioKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==evt.VK_ENTER)
        {
            agregarCantidadProductoEx();
        }
        else
        {
            
        }
    }//GEN-LAST:event_tbl_inventarioKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar_entrada_producto;
    private javax.swing.JButton btn_agregar_cantidad_producto;
    private javax.swing.JButton btn_agregar_entrada_producto;
    private javax.swing.JButton btn_eliminar_entrada_inventario;
    private javax.swing.JButton btn_limpiar_form_entrada_producto;
    private javax.swing.JButton btn_salir_entrada_inventario;
    private javax.swing.JComboBox cmb_buscar_por_inventario_producto;
    private javax.swing.JComboBox cmb_buscar_por_producto;
    private javax.swing.JComboBox cmb_proveedor_entrada_producto;
    private javax.swing.JComboBox cmb_sucursal_entrada_producto;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdate_expiracion_entrada_prodcuto;
    private javax.swing.JLabel jtexfield;
    private javax.swing.JLabel jtexfield1;
    private javax.swing.JPanel pan_abajo;
    private javax.swing.JPanel pan_arriba;
    private javax.swing.JSpinner spin_cod_poroveedor;
    private javax.swing.JSpinner spin_stock_entrada_inventario;
    private javax.swing.JTable tbl_inventario;
    private javax.swing.JTable tbl_producto;
    private javax.swing.JTextField txt_barras_entrada_producto;
    private javax.swing.JTextField txt_buscar_producto;
    private javax.swing.JTextField txt_buscar_producto_en_inventario;
    private javax.swing.JTextField txt_cantidad_entrada_producto;
    private javax.swing.JTextField txt_categoria_producto;
    private javax.swing.JTextField txt_id_categoria;
    private javax.swing.JTextField txt_id_entrada_inventario;
    private javax.swing.JTextField txt_id_marca;
    private javax.swing.JTextField txt_id_medicion;
    private javax.swing.JTextField txt_id_presentacion;
    private javax.swing.JTextField txt_id_producto;
    private javax.swing.JTextField txt_iva_entrada_producto;
    private javax.swing.JTextField txt_marca_entrada_producto;
    private javax.swing.JTextField txt_medicion_entrada_producto;
    private javax.swing.JTextField txt_nombre_entrada_producto;
    private javax.swing.JTextField txt_precio1_entrada_producto;
    private javax.swing.JTextField txt_precio2_entrada_producto;
    private javax.swing.JTextField txt_presentacion_entrada_producto;
    // End of variables declaration//GEN-END:variables
}
