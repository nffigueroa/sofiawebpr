/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_factura;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

/**
 *
 * @author Nestor1
 */
public class frm_GestionarFactura extends javax.swing.JInternalFrame {
    
     public String []columnas = new String[19],columnas_articulos = new String[9];
        public int[]ancho_columnas = new int[15],columnas_eliminar = new int[4],columnas_eliminar_articulos = new int[13];
        java.util.Date now = new Date(System.currentTimeMillis());
        String fecha= new String(),fecha2= new String();
        Constructor_Usuario usuario_activo= new Constructor_Usuario();
         private SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
        private String user= null;
        SimpleDateFormat formato_date = new SimpleDateFormat();
        Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();

    /**
     * Creates new form frm_Producto
     * @param usuario
     */
    public frm_GestionarFactura(Object usuario) {
        user=usuario.toString();
        initComponents();
        inicializarForm();
        cargarTableFactura(false);
//        totalFacturas();
//        descuentoFacturas();
//        ivaFacturas();
        this.setTitle("GESTIONAR FACTURAS");
    }
    private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
    }
    private void inicializarForm()
    {
        Date fecha_ultima;
        formato_date.applyPattern("yyyy-MM-dd");
        String fecha1;
        String fecha_hastaa = new SimpleDateFormat("yyyy-MM-dd").format(now); 
        Funciones_frm_factura fun = new Funciones_frm_factura();
        TitledBorder borde2 = new TitledBorder(new EtchedBorder(),"Facturas");
        pan_arriba.setBorder(borde2);
        TitledBorder borde1 = new TitledBorder(new EtchedBorder(),"General");
//        pan_mas_arriba.setBorder(borde1);
        TitledBorder borde = new TitledBorder(new EtchedBorder(),"Articulos Factura");
        pan_abajo.setBorder(borde);
        fecha1= fun.primerFecha();
         try {
            fecha_ultima = formato_date.parse(fecha_hastaa);
            
            Date fecha_poner_1 = formato_date.parse((fecha1));
            //Date_Facturacion_Desde.setDateFormatString(fecha1);
            Date_Facturacion_Desde.setDate((fecha_poner_1));
            //Date_Facturacion_Hasta.setDateFormatString(fecha_poner_2);
            Date_Facturacion_Hasta.setDate(fecha_ultima);
            
            fecha = String.format("%1$td-%1$tm-%1$tY", Date_Facturacion_Desde.getDate());
            fecha2 = String.format("%1$td-%1$tm-%1$tY", Date_Facturacion_Hasta.getDate());
         } catch (Exception ex) {
             Logger.getLogger(frm_GestionarFactura.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       
    }
    
//    private void totalFacturas()
//    {
//        Funciones_frm_factura fun = new Funciones_frm_factura();
//        txt_facturacion_total.setText( String.valueOf(fun.funcionTotalFacturas(fecha,fecha2)));
//    }
//    private void descuentoFacturas()
//    {
//        Funciones_frm_factura fun = new Funciones_frm_factura();
//        txt_facturacion_descuento_total.setText( String.valueOf(fun.funcionDescuentoFacturas(fecha,fecha2)));
//    }
//     private void ivaFacturas()
//    {
//        Funciones_frm_factura fun = new Funciones_frm_factura();
//        txt_facturacion_iva_total.setText( String.valueOf(fun.funcionIvaFacturas(fecha,fecha2)));
//    }
    
    
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
      private void parametrosTablaArticulo()
    {
        
        columnas_articulos[0] = "ITEM";
        columnas_articulos[1] = "PRODUCTO";
        columnas_articulos[2] = "PRESEN.";
        columnas_articulos[3] = "MED.";
        columnas_articulos[4] = "MARCA";
        columnas_articulos[5] = "VLR. UNID.";
        columnas_articulos[6] = "CANT";
        columnas_articulos[7] = "DSCTO";
        columnas_articulos[8] = "SUBTOTAL";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        columnas_eliminar_articulos[0] = 9;
        columnas_eliminar_articulos[1] = 9;
        columnas_eliminar_articulos[2] = 9;
        columnas_eliminar_articulos[3] = 9;
        columnas_eliminar_articulos[4] = 9;
        columnas_eliminar_articulos[5] = 9;
        columnas_eliminar_articulos[6] = 9;
        columnas_eliminar_articulos[7] = 9;
        columnas_eliminar_articulos[8] = 9;
        columnas_eliminar_articulos[9] = 9;
        columnas_eliminar_articulos[10] = 9;
        columnas_eliminar_articulos[11] = 9;
        columnas_eliminar_articulos[12] = 9;
//        columnas_eliminar_articulos[13] = 9;
       
    }
    
    private void cargarTableFactura(boolean ban) 
    {
       
        fecha = String.format("%1$tY-%1$tm-%1$td", Date_Facturacion_Desde.getDate());
        fecha2 = String.format("%1$tY-%1$tm-%1$td", Date_Facturacion_Hasta.getDate());
       
        
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_factura n = new Funciones_frm_factura();
        
        try{
            consultarDatosUsuario();
            parametrosTabla();
            //fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(Date_Facturacion_Hasta.getDate());
            //fecha = new SimpleDateFormat("yyyy-MM-dd").format(Date_Facturacion_Desde.getDate());   
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }  
        try{
        tbl_facutracion.setModel(n.llenarTablaFacturas(true,usuario_activo.getId_sucursal(),fecha,fecha2,columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_facutracion.getColumnModel().removeColumn(tbl_facutracion.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         
         
         
    }
     private void consultarDatosMiEmpresa() {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa = fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }
     private void cargarTableArticulos() 
    {
        Object id_factura= tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 0).toString();
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_factura n = new Funciones_frm_factura();
        try{
            parametrosTablaArticulo();
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_articuluos.setModel(n.llenarArticulos(id_factura,columnas_articulos, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar_articulos.length; i++) {

            tbl_articuluos.getColumnModel().removeColumn(tbl_articuluos.getColumnModel().getColumn(columnas_eliminar_articulos[i]));
        }
         
    }
     
     private void anularFactura(Object id_factura,Object total)
     {
         consultarDatosUsuario();
         Funciones_frm_factura fun = new Funciones_frm_factura();
         Object[] opciones;
         opciones=fun.llenarComboMotivoAnulacion();
         if(JOptionPane.showConfirmDialog(null, "Desea Anular la Factura #"+id_factura+" \n Se descontará "+total+" de su contabilidad")==0)
         {
             Object seleccion = JOptionPane.showInputDialog(null,"Seleccione opcion","¡ANULAR FACTURA!",JOptionPane.QUESTION_MESSAGE,null,  // null para icono defecto
        opciones,opciones[0]);
             if(seleccion != null)
             {
                 fun.funcionAnularFactura(id_factura, seleccion,usuario_activo.getId_usuario());
                 
             }
         }
     }
     private void funcionImprimirTirilla()
      {
          int id_factura =0;
          id_factura = Integer.parseInt(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 0).toString());
          consultarDatosUsuario();
          Funciones_Generales fun = new Funciones_Generales();
          fun.imprimirTirilla(usuario_activo.getId_sucursal(),id_factura,usuario_activo.getId_usuario());
      }
     private void switch_btn_accionar(String opcion)
     {
         switch (opcion){
             case "Imprimir":
                 funcionImprimirTirilla();
                     break;
             case "Mostrar Articulos":
                 cargarTableArticulos();
                 break;
             case "Anular":
                 anularFactura(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 0) ,txt_total_facturacion.getText());
                 cargarTableFactura(false);
                 break;
         }
     }
    
    
    private void evento_mouse_presionado_faccturacion(String tecla)
    {
        try
        {
        switch(tecla){
            case "ABAJO":
                txt_identificacion_cliente.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()+1, 13).toString());
                txtAr_descripcion_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()+1, 10).toString());
                txt_descuento_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()+1, 4).toString());
                txt_iva_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()+1, 5).toString());
                txt_subtotal_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()+1, 3).toString());
                txt_total_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()+1, 2).toString());
            break;
            case"ARRIBA":
                txt_identificacion_cliente.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()-1, 13).toString());
                txtAr_descripcion_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()-1, 10).toString());
                txt_descuento_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()-1, 4).toString());
                txt_iva_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()-1, 5).toString());
                txt_subtotal_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()-1, 3).toString());
                txt_total_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow()-1, 2).toString());
            break;
            case"":
                txt_identificacion_cliente.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 13).toString());
                txtAr_descripcion_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 10).toString());
                txt_descuento_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 4).toString());
                txt_iva_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 5).toString());
                txt_subtotal_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 3).toString());
                txt_total_facturacion.setText(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 2).toString());
            break;
        }
         
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         
    }
    
    private void evento_buscar_facturaPor()
    {
        Funciones_frm_factura fun = new Funciones_frm_factura();
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        TableModel modelo = fun.buscarFactura(mi_empresa.getId_empresa(),fecha, fecha2, txt_facturacion_busccar_por.getText(), cmb_facturacion_buscar_por.getSelectedItem().toString(), columnas, ancho_columnas);
        tbl_facutracion.setModel(modelo);
         for (int i = 0; i < columnas_eliminar.length; i++) 
            tbl_facutracion.getColumnModel().removeColumn(tbl_facutracion.getColumnModel().getColumn(columnas_eliminar[i]));
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_articuluos = new javax.swing.JTable();
        pan_arriba = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_facutracion = new javax.swing.JTable();
        btn_accionar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txt_facturacion_busccar_por = new javax.swing.JTextField();
        cmb_facturacion_buscar_por = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAr_descripcion_facturacion = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txt_identificacion_cliente = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_subtotal_facturacion = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txt_descuento_facturacion = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txt_iva_facturacion = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_total_facturacion = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        cmb_accionar_facturacion = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        Date_Facturacion_Desde = new com.toedter.calendar.JDateChooser();
        Date_Facturacion_Hasta = new com.toedter.calendar.JDateChooser();
        btn_aplicar_fecchas = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();

        tbl_articuluos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_articuluos.setShowHorizontalLines(false);
        jScrollPane2.setViewportView(tbl_articuluos);

        javax.swing.GroupLayout pan_abajoLayout = new javax.swing.GroupLayout(pan_abajo);
        pan_abajo.setLayout(pan_abajoLayout);
        pan_abajoLayout.setHorizontalGroup(
            pan_abajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_abajoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pan_abajoLayout.setVerticalGroup(
            pan_abajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_abajoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_facutracion.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_facutracion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tbl_facutracionFocusGained(evt);
            }
        });
        tbl_facutracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_facutracionMousePressed(evt);
            }
        });
        tbl_facutracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_facutracionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_facutracion);

        btn_accionar.setText("Accionar");
        btn_accionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_accionarActionPerformed(evt);
            }
        });

        jLabel18.setText("Buscar:");

        txt_facturacion_busccar_por.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_facturacion_busccar_porKeyPressed(evt);
            }
        });

        cmb_facturacion_buscar_por.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Folio", "Forma Pago", "Nombre Cliente", "Apellido Cliente", "Cedula Cliente", "Fecha" }));

        jLabel19.setText("Por:");

        txtAr_descripcion_facturacion.setColumns(20);
        txtAr_descripcion_facturacion.setRows(5);
        jScrollPane3.setViewportView(txtAr_descripcion_facturacion);

        jLabel1.setText("Descipcion:");

        txt_identificacion_cliente.setEditable(false);

        jLabel23.setText("Ide. Cliente:");

        jLabel25.setText("SubTotal:");

        txt_subtotal_facturacion.setEditable(false);

        jLabel26.setText("Descuento:");

        txt_descuento_facturacion.setEditable(false);
        txt_descuento_facturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descuento_facturacionActionPerformed(evt);
            }
        });

        jLabel27.setText("Iva:");

        txt_iva_facturacion.setEditable(false);

        jLabel24.setText("TOTAL:");

        txt_total_facturacion.setEditable(false);
        txt_total_facturacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel28.setText("Acciones:");

        cmb_accionar_facturacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mostrar Articulos", "Anular", "Imprimir" }));

        jLabel3.setText("Mostar Facturacion desde:");

        Date_Facturacion_Desde.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                Date_Facturacion_DesdeHierarchyChanged(evt);
            }
        });
        Date_Facturacion_Desde.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                Date_Facturacion_DesdeCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                Date_Facturacion_DesdeInputMethodTextChanged(evt);
            }
        });
        Date_Facturacion_Desde.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Date_Facturacion_DesdePropertyChange(evt);
            }
        });
        Date_Facturacion_Desde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Date_Facturacion_DesdeKeyPressed(evt);
            }
        });

        Date_Facturacion_Hasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Date_Facturacion_HastaPropertyChange(evt);
            }
        });

        btn_aplicar_fecchas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634415_preview.png"))); // NOI18N
        btn_aplicar_fecchas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aplicar_fecchasActionPerformed(evt);
            }
        });

        jLabel29.setText("Hasta:");

        javax.swing.GroupLayout pan_arribaLayout = new javax.swing.GroupLayout(pan_arriba);
        pan_arriba.setLayout(pan_arribaLayout);
        pan_arribaLayout.setHorizontalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_arribaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_facturacion_busccar_por, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_facturacion_buscar_por, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_Facturacion_Desde, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_Facturacion_Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btn_aplicar_fecchas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel25))
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_identificacion_cliente)
                            .addComponent(txt_subtotal_facturacion)
                            .addComponent(txt_descuento_facturacion)))
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addGap(1039, 1039, 1039)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel1))
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pan_arribaLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGroup(pan_arribaLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_total_facturacion)
                                            .addComponent(txt_iva_facturacion)))))
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pan_arribaLayout.createSequentialGroup()
                                        .addComponent(btn_accionar)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cmb_accionar_facturacion, 0, 142, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        pan_arribaLayout.setVerticalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_arribaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Date_Facturacion_Desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txt_facturacion_busccar_por, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(cmb_facturacion_buscar_por, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addComponent(jLabel29)
                        .addComponent(Date_Facturacion_Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_aplicar_fecchas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txt_identificacion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_subtotal_facturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_descuento_facturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_iva_facturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_total_facturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(cmb_accionar_facturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_accionar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan_arriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan_abajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pan_arriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_abajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_accionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_accionarActionPerformed
        // TODO add your handling code here:
        
        switch_btn_accionar(cmb_accionar_facturacion.getSelectedItem().toString());
    }//GEN-LAST:event_btn_accionarActionPerformed

    private void tbl_facutracionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_facutracionMousePressed
        // TODO add your handling code here:
        
        evento_mouse_presionado_faccturacion("");
    }//GEN-LAST:event_tbl_facutracionMousePressed

    private void tbl_facutracionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbl_facutracionFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tbl_facutracionFocusGained

    private void tbl_facutracionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_facutracionKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE ||evt.getKeyCode()==evt.VK_DELETE)
        {
            anularFactura(tbl_facutracion.getModel().getValueAt(tbl_facutracion.getSelectedRow(), 0) ,txt_total_facturacion.getText());
            cargarTableFactura(false);
//            totalFacturas();
//            descuentoFacturas();
//            ivaFacturas();
        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER )
            cargarTableArticulos();
        
        if(evt.getKeyCode()==KeyEvent.VK_UP  )
            {
                evento_mouse_presionado_faccturacion("ARRIBA");
            }
               else
                { 
                    if(evt.getKeyCode()==KeyEvent.VK_DOWN  )
                    {
                        evento_mouse_presionado_faccturacion("ABAJO");
                    }
                    else
                    {
                        evento_mouse_presionado_faccturacion("");
                    }
                        
                }
    }//GEN-LAST:event_tbl_facutracionKeyPressed

    private void Date_Facturacion_DesdeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdeInputMethodTextChanged
        // TODO add your handling code here:
            cargarTableFactura(false);
        
    }//GEN-LAST:event_Date_Facturacion_DesdeInputMethodTextChanged

    private void Date_Facturacion_DesdeCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdeCaretPositionChanged
        // TODO add your handling code here:
    
    }//GEN-LAST:event_Date_Facturacion_DesdeCaretPositionChanged

    private void Date_Facturacion_DesdeHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdeHierarchyChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_Date_Facturacion_DesdeHierarchyChanged

    private void Date_Facturacion_DesdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdeKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            cargarTableFactura(false);
        }
        
    }//GEN-LAST:event_Date_Facturacion_DesdeKeyPressed

    private void Date_Facturacion_DesdePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_Date_Facturacion_DesdePropertyChange

    private void Date_Facturacion_HastaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Date_Facturacion_HastaPropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_Date_Facturacion_HastaPropertyChange

    private void txt_facturacion_busccar_porKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_facturacion_busccar_porKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==evt.VK_ENTER )
         {
            evento_buscar_facturaPor();
         }
        
    }//GEN-LAST:event_txt_facturacion_busccar_porKeyPressed

    private void btn_aplicar_fecchasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aplicar_fecchasActionPerformed
        // TODO add your handling code here:
        
          cargarTableFactura(false);
    }//GEN-LAST:event_btn_aplicar_fecchasActionPerformed

    private void txt_descuento_facturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descuento_facturacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descuento_facturacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date_Facturacion_Desde;
    private com.toedter.calendar.JDateChooser Date_Facturacion_Hasta;
    private javax.swing.JButton btn_accionar;
    private javax.swing.JButton btn_aplicar_fecchas;
    private javax.swing.JComboBox cmb_accionar_facturacion;
    private javax.swing.JComboBox cmb_facturacion_buscar_por;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pan_abajo;
    private javax.swing.JPanel pan_arriba;
    private javax.swing.JTable tbl_articuluos;
    private javax.swing.JTable tbl_facutracion;
    private javax.swing.JTextArea txtAr_descripcion_facturacion;
    private javax.swing.JTextField txt_descuento_facturacion;
    private javax.swing.JTextField txt_facturacion_busccar_por;
    private javax.swing.JTextField txt_identificacion_cliente;
    private javax.swing.JTextField txt_iva_facturacion;
    private javax.swing.JTextField txt_subtotal_facturacion;
    private javax.swing.JTextField txt_total_facturacion;
    // End of variables declaration//GEN-END:variables
}
