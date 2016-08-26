/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Constructores.Contructor_Cliente_Seleccionado;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_factura;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
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
public class frm_Ganancias_Facturacion extends javax.swing.JInternalFrame {

    
    public String []columnas_inventario = new String[31];
    public int[] ancho_columnas = new int[15],columnas_eliminar= new int[4],columnas_eliminar_inventario =new int[17];;
    Date now = new Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo = new Constructor_Usuario();
    DecimalFormat formateador = new DecimalFormat("###,###");
    Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
    Constructores.Contructor_Cliente_Seleccionado cliente = new Contructor_Cliente_Seleccionado();
    private String user = null;
      public String []columnas = new String[22],columnas_articulos = new String[9];
        
        
        String fecha= new String(),fecha2= new String();
        
         private SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
        
        SimpleDateFormat formato_date = new SimpleDateFormat();

    /**
     * Creates new form frm_Cotizar
     */
    public frm_Ganancias_Facturacion(Object usua) {

        user = usua.toString();
        this.setTitle("GANANCIAS EN FACTURACION");
        initComponents();
        inicializarForm();
        cargarTableFactura(false);
        totalFacturas();
        descuentoFacturas();
        ivaFacturas();
        gananciaFactura();
    }
     private void totalFacturas()
    {
        Funciones_frm_factura fun = new Funciones_frm_factura();
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        lbl_facturacion_total.setText( String.valueOf(fun.funcionTotalFacturas(mi_empresa.getId_empresa(),fecha,fecha2)));
    }
    private void descuentoFacturas()
    {
        Funciones_frm_factura fun = new Funciones_frm_factura();
          consultarDatosUsuario();
        consultarDatosMiEmpresa();
        lbl_descuento_total.setText( String.valueOf(fun.funcionDescuentoFacturas(mi_empresa.getId_empresa(),fecha,fecha2)));
    }
     private void ivaFacturas()
    {
        Funciones_frm_factura fun = new Funciones_frm_factura();
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        lbl_iva_total.setText( String.valueOf(fun.funcionIvaFacturas(mi_empresa.getId_empresa(),fecha,fecha2)));
    }
     
      private void gananciaFactura()
    {
        Funciones_frm_factura fun = new Funciones_frm_factura();
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        lbl_ganancias_total.setText( String.valueOf(fun.funcionGananciaFacturas(mi_empresa.getId_empresa(),fecha,fecha2)));
    }
    private void inicializarForm()
    {
        Date fecha_ultima;
        formato_date.applyPattern("yyyy-MM-dd");
        String fecha1;
        String fecha_hastaa = new SimpleDateFormat("yyyy-MM-dd").format(now); 
        Funciones_frm_factura fun = new Funciones_frm_factura();
        TitledBorder borde2 = new TitledBorder(new EtchedBorder(),"Facturas");
       
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
        columnas[16] = "HORA";
        columnas[17] = "UTILIDAD";
        columnas[18] = "SUCURSAL";
        columnas[19] = "BORRAR";
        columnas[20] = "BORRAR2";
        columnas[21] = "BORRAR3";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        columnas_eliminar[0] = 19;
        columnas_eliminar[1] = 19;
        columnas_eliminar[2] = 19;
        columnas_eliminar[3] = 19;
       
    }

   
    
   
    private void consultarDatosUsuario() {
        Controlador.Funciones_Generales funciones_producto = new Funciones_Generales();
        usuario_activo = funciones_producto.usuario(user);
    }

    private void consultarDatosMiEmpresa() {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa = fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }

  
    
     private void cargarTableFactura(boolean ban) 
    {
       
        fecha = String.format("%1$tY-%1$tm-%1$td", Date_Facturacion_Desde.getDate());
        fecha2 = String.format("%1$tY-%1$tm-%1$td", Date_Facturacion_Hasta.getDate());
       
        
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_factura n = new Funciones_frm_factura();
        
        try{
            consultarDatosUsuario();
            consultarDatosMiEmpresa();
            parametrosTabla();
            //fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(Date_Facturacion_Hasta.getDate());
            //fecha = new SimpleDateFormat("yyyy-MM-dd").format(Date_Facturacion_Desde.getDate());   
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }  
        try{
        tbl_facutracion.setModel(n.llenarTablaFacturas(false,mi_empresa.getId_empresa(),fecha,fecha2,columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_facutracion.getColumnModel().removeColumn(tbl_facutracion.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         
         
         
    }
     
      private void evento_buscar_facturaPor()
    {
        Funciones_frm_factura fun = new Funciones_frm_factura();
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

        dlg_abonar = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbl_numero_cuota = new javax.swing.JLabel();
        lbl_estado_credito = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_cuotas_pendientes = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_id_credito = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_total_credito = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_cuota_mensual = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_historial = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txt_cambio_abono = new javax.swing.JTextField();
        txt_recibe_abono = new javax.swing.JTextField();
        txt_cliente_abono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_accionar_abono = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_cc_cliente = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_facutracion = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        txt_facturacion_busccar_por = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cmb_facturacion_buscar_por = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        Date_Facturacion_Desde = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        Date_Facturacion_Hasta = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        btn_aplicar_fecchas = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbl_facturacion_total = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_iva_total = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_descuento_total = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_ganancias_total = new javax.swing.JLabel();

        dlg_abonar.setMinimumSize(new java.awt.Dimension(718, 399));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informe Credito"));

        jLabel7.setText("Cuota #:");

        lbl_numero_cuota.setText("...");

        lbl_estado_credito.setText("...");

        jLabel4.setText("Estado Credito:");

        jLabel8.setText("Cuotas Pendt.");

        lbl_cuotas_pendientes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_cuotas_pendientes.setForeground(new java.awt.Color(255, 0, 0));
        lbl_cuotas_pendientes.setText("...");

        jLabel9.setText("Credito #:");

        lbl_id_credito.setText("...");

        jLabel10.setText("Total Credito:");

        lbl_total_credito.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_total_credito.setForeground(new java.awt.Color(255, 0, 0));
        lbl_total_credito.setText("...");

        jLabel2.setText("Cuota Mensual:");

        lbl_cuota_mensual.setText("...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10))
                            .addComponent(jLabel9))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lbl_id_credito, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_total_credito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_estado_credito, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_numero_cuota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_cuotas_pendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_cuota_mensual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbl_numero_cuota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbl_estado_credito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbl_cuotas_pendientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbl_id_credito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_cuota_mensual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbl_total_credito)))
        );

        tbl_historial.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbl_historial);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Abono"));

        txt_cambio_abono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cambio_abonoActionPerformed(evt);
            }
        });

        txt_recibe_abono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_recibe_abonoActionPerformed(evt);
            }
        });
        txt_recibe_abono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_recibe_abonoKeyPressed(evt);
            }
        });

        txt_cliente_abono.setEnabled(false);
        txt_cliente_abono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cliente_abonoActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente:");

        jLabel5.setText("Recibe:");

        jLabel6.setText("Cambio:");

        btn_accionar_abono.setText("Abonar");
        btn_accionar_abono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_accionar_abonoActionPerformed(evt);
            }
        });

        jLabel11.setText("Cc Cliente:");

        txt_cc_cliente.setEnabled(false);
        txt_cc_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cc_clienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_accionar_abono)
                        .addGap(0, 143, Short.MAX_VALUE))
                    .addComponent(txt_recibe_abono)
                    .addComponent(txt_cliente_abono)
                    .addComponent(txt_cc_cliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_cambio_abono, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_cliente_abono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_cc_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_recibe_abono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_cambio_abono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_accionar_abono)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dlg_abonarLayout = new javax.swing.GroupLayout(dlg_abonar.getContentPane());
        dlg_abonar.getContentPane().setLayout(dlg_abonarLayout);
        dlg_abonarLayout.setHorizontalGroup(
            dlg_abonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_abonarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        dlg_abonarLayout.setVerticalGroup(
            dlg_abonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_abonarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ganancias"));

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
        tbl_facutracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_facutracionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_facutracion);

        jLabel18.setText("Buscar:");

        txt_facturacion_busccar_por.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_facturacion_busccar_porKeyPressed(evt);
            }
        });

        jLabel19.setText("Por:");

        cmb_facturacion_buscar_por.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Folio", "Forma Pago", "Nombre Cliente", "Apellido Cliente", "Cedula Cliente", "Fecha", "Sucursal" }));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Mostrar Facturacion"));

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

        jLabel3.setText("Desde");

        Date_Facturacion_Hasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Date_Facturacion_HastaPropertyChange(evt);
            }
        });

        jLabel29.setText("Hasta:");

        btn_aplicar_fecchas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634415_preview.png"))); // NOI18N
        btn_aplicar_fecchas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aplicar_fecchasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_Facturacion_Hasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_aplicar_fecchas)
                            .addComponent(Date_Facturacion_Desde, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(Date_Facturacion_Desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Date_Facturacion_Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_aplicar_fecchas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Facturacion Total:");

        lbl_facturacion_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_facturacion_total.setText("000.000.000");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("IVA:");

        lbl_iva_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_iva_total.setText("000.000.000");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Descuento:");

        lbl_descuento_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_descuento_total.setText("000.000.000");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Ganancias:");

        lbl_ganancias_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_ganancias_total.setForeground(new java.awt.Color(102, 204, 0));
        lbl_ganancias_total.setText("000.000.000");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_ganancias_total))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_descuento_total))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_iva_total))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_facturacion_total)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbl_facturacion_total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lbl_iva_total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lbl_descuento_total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbl_ganancias_total))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_facturacion_busccar_por, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_facturacion_buscar_por, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_facturacion_busccar_por, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cmb_facturacion_buscar_por, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cliente_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cliente_abonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cliente_abonoActionPerformed

    private void txt_recibe_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_recibe_abonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_recibe_abonoActionPerformed

    private void txt_cambio_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cambio_abonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cambio_abonoActionPerformed

    private void tbl_facutracionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_facutracionKeyPressed
        // TODO add your handling code here:
          if(evt.getKeyChar()==KeyEvent.VK_ENTER)
          {
             
          }
    }//GEN-LAST:event_tbl_facutracionKeyPressed

    private void txt_cc_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cc_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cc_clienteActionPerformed

    private void btn_accionar_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_accionar_abonoActionPerformed
        // TODO add your handling code here:
        
       
                
    }//GEN-LAST:event_btn_accionar_abonoActionPerformed

    private void txt_recibe_abonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_recibe_abonoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            
        }
        else
        {
            
        }
    }//GEN-LAST:event_txt_recibe_abonoKeyPressed

    private void txt_facturacion_busccar_porKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_facturacion_busccar_porKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==evt.VK_ENTER )
         {
            evento_buscar_facturaPor();
         }
        
    }//GEN-LAST:event_txt_facturacion_busccar_porKeyPressed

    private void Date_Facturacion_DesdeHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdeHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Date_Facturacion_DesdeHierarchyChanged

    private void Date_Facturacion_DesdeCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdeCaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_Date_Facturacion_DesdeCaretPositionChanged

    private void Date_Facturacion_DesdeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdeInputMethodTextChanged
        // TODO add your handling code here:
        cargarTableFactura(false);

    }//GEN-LAST:event_Date_Facturacion_DesdeInputMethodTextChanged

    private void Date_Facturacion_DesdePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_Date_Facturacion_DesdePropertyChange

    private void Date_Facturacion_DesdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Date_Facturacion_DesdeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            cargarTableFactura(false);
        }

    }//GEN-LAST:event_Date_Facturacion_DesdeKeyPressed

    private void Date_Facturacion_HastaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Date_Facturacion_HastaPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_Date_Facturacion_HastaPropertyChange

    private void btn_aplicar_fecchasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aplicar_fecchasActionPerformed
        // TODO add your handling code here:

        cargarTableFactura(false);
        totalFacturas();
        ivaFacturas();
        descuentoFacturas();
        gananciaFactura();
    }//GEN-LAST:event_btn_aplicar_fecchasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Date_Facturacion_Desde;
    private com.toedter.calendar.JDateChooser Date_Facturacion_Hasta;
    private javax.swing.JButton btn_accionar_abono;
    private javax.swing.JButton btn_aplicar_fecchas;
    private javax.swing.JComboBox cmb_facturacion_buscar_por;
    private javax.swing.JDialog dlg_abonar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_cuota_mensual;
    private javax.swing.JLabel lbl_cuotas_pendientes;
    private javax.swing.JLabel lbl_descuento_total;
    private javax.swing.JLabel lbl_estado_credito;
    private javax.swing.JLabel lbl_facturacion_total;
    private javax.swing.JLabel lbl_ganancias_total;
    private javax.swing.JLabel lbl_id_credito;
    private javax.swing.JLabel lbl_iva_total;
    private javax.swing.JLabel lbl_numero_cuota;
    private javax.swing.JLabel lbl_total_credito;
    private javax.swing.JTable tbl_facutracion;
    private javax.swing.JTable tbl_historial;
    private javax.swing.JTextField txt_cambio_abono;
    private javax.swing.JTextField txt_cc_cliente;
    private javax.swing.JTextField txt_cliente_abono;
    private javax.swing.JTextField txt_facturacion_busccar_por;
    private javax.swing.JTextField txt_recibe_abono;
    // End of variables declaration//GEN-END:variables
}
