/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Usuario;
import Constructores.Contructor_CortesCaja;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_CajonDinero;
import Controlador.Funciones_frm_CortesCaja;
import Controlador.Funciones_frm_clientes;
import Controlador.Funciones_frm_factura;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Nestor1
 */
public class frm_CorteCaja extends javax.swing.JInternalFrame {
     public String []columnas = new String[20],columnas_importes = new String[10];
        public int[]ancho_columnas = new int[15],columnas_eliminar = new int[6],columnas_eliminar_importes = new int[6];
        Date now = new Date(System.currentTimeMillis());
        private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
        Constructor_Usuario usuario_activo= new Constructor_Usuario();
        private String user= null;
        Contructor_CortesCaja corte_caja = new Contructor_CortesCaja();
        DecimalFormat formateador = new DecimalFormat("###,###");
        float efetivo_ini=0,efectivo_ventas=0;
        float efectivo_total=0;
        int id_corte=0;
    /**
     * Creates new form frm_Producto
     */
    public frm_CorteCaja(Object usuario) {
        initComponents();
        user=usuario.toString();
        cargarTable();
        inicializarForm();
        cargarTableImportes();
        this.setTitle("CORTES CAJA");
    }
    
    
    
    private void inicializarForm()
    {
        llenarTotales();
        float efectivo =0;
       
        efectivo_ventas = corte_caja.getTotal_efectivo();
        TitledBorder borde2 = new TitledBorder(new EtchedBorder(),"Facturas sin Cortes");
        pan_arriba.setBorder(borde2);
        TitledBorder borde1 = new TitledBorder(new EtchedBorder(),"Improtes EGRESOS/INGRESOS");
        pan_abajo.setBorder(borde1);
        TitledBorder borde = new TitledBorder(new EtchedBorder(),"DETALLES");
        pan_detalles.setBorder(borde);
        txt_total_ventas.setText(String.valueOf(formateador.format(corte_caja.getTotal())));
        txt_tarjeta_credito.setText(String.valueOf(formateador.format(corte_caja.getTotal_tarjeta_credito())));
        txt_tarjeta_debito.setText(String.valueOf(formateador.format(corte_caja.getTotal_tarjeta_debito())));
        txt_total_cheques.setText(String.valueOf(formateador.format(corte_caja.getTotal_cheque())));
        txt_total_credito.setText(String.valueOf(formateador.format(corte_caja.getTotal_nota_credito())));
        txt_total_transaccion.setText(String.valueOf(formateador.format(corte_caja.getTotal_transaccion())));
        txt_total_efectivo.setText(String.valueOf(formateador.format(corte_caja.getTotal_efectivo())));
        llenarTotalesImporte();
        txt_total_egresos.setText(String.valueOf(formateador.format(corte_caja.getTotal_importe_egreso())));
        txt_total_ingresos.setText(String.valueOf(formateador.format(corte_caja.getTotal_importe_ingreso())));
        Controlador.Funciones_Generales fun = new Funciones_Generales();
        efectivo=fun.efectivoInicial(usuario_activo.getId_sucursal());
        id_corte=fun.IdCorteInicial(usuario_activo.getId_sucursal());
        efetivo_ini=efectivo;
        txt_efectivo_inicial.setText(String.valueOf(formateador.format(efectivo)));
        evento_cambio_total_inicial();
        
    }
    
     private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
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
        columnas[19] = "BORRAR4";
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
        columnas_eliminar[4] = 16; 
        columnas_eliminar[5] = 16; 
       
    }
      private void parametrosTablaImportes()
    {
         columnas_importes[0] = "IDENTIFICACION";
        columnas_importes[1] = "VALOR";
        columnas_importes[2] = "DESCRIPCION";
        columnas_importes[3] = "TIPO";
        columnas_importes[4] = "MOTIVO";
        columnas_importes[5] = "IVA";
        columnas_importes[6] = "RECIBE";
        columnas_importes[7] = "CAMBIO";
        columnas_importes[8] = "PAGO CON:";
        columnas_importes[9] = "TARJETA";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        columnas_eliminar_importes[0] = 6;
        columnas_eliminar_importes[1] = 6;
        columnas_eliminar_importes[2] = 6;
        columnas_eliminar_importes[3] = 6;        
        columnas_eliminar_importes[4] = 5;        
        columnas_eliminar_importes[5] = 5;        
    }
    
      private void llenarTotales()
      {
          consultarDatosUsuario();
          Controlador.Funciones_Generales fun = new Funciones_Generales();
          corte_caja=fun.corte_caja_totales(usuario_activo.getId_sucursal());
      }
      private void llenarTotalesImporte()
      {
          consultarDatosUsuario();
          Controlador.Funciones_Generales fun = new Funciones_Generales();
          corte_caja=fun.importes_totales(usuario_activo.getId_sucursal());
      }
    
    private void cargarTable() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_CortesCaja n = new Funciones_frm_CortesCaja();
        
        try{
            parametrosTabla();
            consultarDatosUsuario();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_facturas.setModel(n.llenarFacturas(usuario_activo.getId_sucursal(),columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_facturas.getColumnModel().removeColumn(tbl_facturas.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         
    }
      private void cargarTableImportes() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_CortesCaja n = new Funciones_frm_CortesCaja();
        
        try{
            parametrosTablaImportes();
            consultarDatosUsuario();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_importes.setModel(n.llenarImportes(usuario_activo.getId_sucursal(),columnas_importes, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar_importes.length; i++) {

            tbl_importes.getColumnModel().removeColumn(tbl_importes.getColumnModel().getColumn(columnas_eliminar_importes[i]));
        }
         
    }
    
      private void evento_cambio_total_inicial()
      {
          llenarTotales();
          
          efectivo_total = efetivo_ini+efectivo_ventas+corte_caja.getTotal_importe_ingreso();
          txt_efectivo_total.setText(formateador.format(efectivo_total));
      }
      
      private void registrarCorte()
      {
          consultarDatosUsuario();
          llenarTotales();
          float efectivo_real=0,efectivo_faltante=0;
          efectivo_real=Float.parseFloat(JOptionPane.showInputDialog(null,"EFECTIVO REAL? $"));
          efectivo_faltante= efectivo_real - efectivo_total;
          JOptionPane.showMessageDialog(null, "EFECTIVO FALTANTE : "+efectivo_faltante);
          Funciones_frm_CortesCaja fun = new Funciones_frm_CortesCaja();
          Funciones_frm_CortesCaja corte= new  Funciones_frm_CortesCaja();
          if(fun.registraCorteCaja(corte_caja, usuario_activo.getId_sucursal(), efectivo_faltante,efectivo_real,id_corte,usuario_activo.getId_usuario())){
              
              for (int i = 0; i < tbl_facturas.getRowCount(); i++) {
              corte.registrarIdCorteCajaFactura(tbl_facturas.getModel().getValueAt(i, 0),id_corte , usuario_activo.getId_sucursal());    
              }
              for (int i = 0; i < tbl_importes.getRowCount(); i++) {
              fun.registrarIdCorteCajaImporte(tbl_importes.getModel().getValueAt(i, 0),id_corte , usuario_activo.getId_sucursal(),usuario_activo.getId_usuario());    
              }
              
              JOptionPane.showMessageDialog(null, "SE REALIZO CORTE DE CAJA EXITOSAMENTE");}
          else
              JOptionPane.showMessageDialog(null, "Ups! Algo sucedio vuelta a intentarlo");
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan_arriba = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_facturas = new javax.swing.JTable();
        pan_abajo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_importes = new javax.swing.JTable();
        pan_detalles = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_total_ventas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_total_efectivo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_total_credito = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_total_ingresos = new javax.swing.JTextField();
        txt_efectivo_inicial = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_efectivo_total = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_total_egresos = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_tarjeta_credito = new javax.swing.JTextField();
        txt_tarjeta_debito = new javax.swing.JTextField();
        txt_total_cheques = new javax.swing.JTextField();
        txt_total_transaccion = new javax.swing.JTextField();

        tbl_facturas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_facturas);

        javax.swing.GroupLayout pan_arribaLayout = new javax.swing.GroupLayout(pan_arriba);
        pan_arriba.setLayout(pan_arribaLayout);
        pan_arribaLayout.setHorizontalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_arribaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pan_arribaLayout.setVerticalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_arribaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        tbl_importes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_importes);

        javax.swing.GroupLayout pan_abajoLayout = new javax.swing.GroupLayout(pan_abajo);
        pan_abajo.setLayout(pan_abajoLayout);
        pan_abajoLayout.setHorizontalGroup(
            pan_abajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_abajoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pan_abajoLayout.setVerticalGroup(
            pan_abajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_abajoLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel7.setText("Total Ventas:");

        txt_total_ventas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_total_ventas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_total_ventas.setPreferredSize(new java.awt.Dimension(10, 23));

        jLabel11.setText("Efectivo:");

        txt_total_efectivo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_total_efectivo.setMinimumSize(new java.awt.Dimension(10, 20));

        jLabel12.setText("Credito:");

        txt_total_credito.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setText("Otros Ingresos:");

        txt_total_ingresos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_total_ingresos.setPreferredSize(new java.awt.Dimension(10, 20));

        txt_efectivo_inicial.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_efectivo_inicial.setPreferredSize(new java.awt.Dimension(10, 20));
        txt_efectivo_inicial.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txt_efectivo_inicialCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_efectivo_inicialInputMethodTextChanged(evt);
            }
        });

        jLabel8.setText("Efectivo Inicial:");

        jLabel9.setText("Efectivo Total:");

        txt_efectivo_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_efectivo_total.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_efectivo_total.setPreferredSize(new java.awt.Dimension(10, 23));

        jLabel10.setText("Otros Egresos:");

        txt_total_egresos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_total_egresos.setMinimumSize(new java.awt.Dimension(10, 20));
        txt_total_egresos.setPreferredSize(new java.awt.Dimension(10, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634336_save.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Tarjetas Debito:");

        jLabel15.setText("Tarjetas Credito:");

        jLabel16.setText("Cheques:");

        jLabel17.setText("Transaccion:");

        txt_tarjeta_credito.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_tarjeta_credito.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_tarjeta_credito.setPreferredSize(new java.awt.Dimension(6, 20));

        txt_tarjeta_debito.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_tarjeta_debito.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_tarjeta_debito.setMinimumSize(new java.awt.Dimension(10, 20));
        txt_tarjeta_debito.setPreferredSize(new java.awt.Dimension(6, 20));

        txt_total_cheques.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_total_cheques.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_total_cheques.setMinimumSize(new java.awt.Dimension(10, 20));
        txt_total_cheques.setPreferredSize(new java.awt.Dimension(6, 20));

        txt_total_transaccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_total_transaccion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_total_transaccion.setPreferredSize(new java.awt.Dimension(10, 20));

        javax.swing.GroupLayout pan_detallesLayout = new javax.swing.GroupLayout(pan_detalles);
        pan_detalles.setLayout(pan_detallesLayout);
        pan_detallesLayout.setHorizontalGroup(
            pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_detallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_detallesLayout.createSequentialGroup()
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan_detallesLayout.createSequentialGroup()
                                .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pan_detallesLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel10))
                                    .addGroup(pan_detallesLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel16)))
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_detallesLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_detallesLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan_detallesLayout.createSequentialGroup()
                                .addComponent(txt_total_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel15))
                            .addGroup(pan_detallesLayout.createSequentialGroup()
                                .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_efectivo_total, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(txt_total_cheques, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_tarjeta_debito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pan_detallesLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_total_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_total_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pan_detallesLayout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(11, 11, 11)
                            .addComponent(txt_total_transaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(jLabel11)
                            .addGap(28, 28, 28)
                            .addComponent(txt_total_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pan_detallesLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel8)
                        .addGap(5, 5, 5)
                        .addComponent(txt_efectivo_inicial, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel12)
                        .addGap(31, 31, 31)
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_tarjeta_credito, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(txt_total_credito))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pan_detallesLayout.setVerticalGroup(
            pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_detallesLayout.createSequentialGroup()
                .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_detallesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan_detallesLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_total_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pan_detallesLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txt_total_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_total_transaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_total_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pan_detallesLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel11))))
                .addGap(6, 6, 6)
                .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_efectivo_inicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pan_detallesLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txt_total_credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(6, 6, 6)
                .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_total_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pan_detallesLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(txt_tarjeta_credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pan_detallesLayout.createSequentialGroup()
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_total_cheques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_efectivo_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pan_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tarjeta_debito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addComponent(jButton1))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pan_arriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pan_abajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pan_detalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan_arriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pan_detalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan_abajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        registrarCorte();
        cargarTable();
        cargarTableImportes();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_efectivo_inicialInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_efectivo_inicialInputMethodTextChanged
        // TODO add your handling code here:
        evento_cambio_total_inicial();
    }//GEN-LAST:event_txt_efectivo_inicialInputMethodTextChanged

    private void txt_efectivo_inicialCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_efectivo_inicialCaretPositionChanged
        // TODO add your handling code here:
        evento_cambio_total_inicial();
    }//GEN-LAST:event_txt_efectivo_inicialCaretPositionChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pan_abajo;
    private javax.swing.JPanel pan_arriba;
    private javax.swing.JPanel pan_detalles;
    private javax.swing.JTable tbl_facturas;
    private javax.swing.JTable tbl_importes;
    private javax.swing.JTextField txt_efectivo_inicial;
    private javax.swing.JTextField txt_efectivo_total;
    private javax.swing.JTextField txt_tarjeta_credito;
    private javax.swing.JTextField txt_tarjeta_debito;
    private javax.swing.JTextField txt_total_cheques;
    private javax.swing.JTextField txt_total_credito;
    private javax.swing.JTextField txt_total_efectivo;
    private javax.swing.JTextField txt_total_egresos;
    private javax.swing.JTextField txt_total_ingresos;
    private javax.swing.JTextField txt_total_transaccion;
    private javax.swing.JTextField txt_total_ventas;
    // End of variables declaration//GEN-END:variables
}
