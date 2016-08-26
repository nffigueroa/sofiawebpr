/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Clientes;
import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Constructores.Contructor_Cliente_Seleccionado;
import Controlador.Funciones_Entrada_Inventario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_clientes;
import Controlador.Funciones_frm_cuentasCobrar;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Nestor1
 */
public class frm_Gestionar_Ganacias extends javax.swing.JInternalFrame {

    
    public String []columnas_inventario = new String[31];
    public int[] ancho_columnas = new int[15],colum_eliminar= new int[2],columnas_eliminar_inventario =new int[17];;
    Date now = new Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo = new Constructor_Usuario();
    DecimalFormat formateador = new DecimalFormat("###,###");
    Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
    Constructores.Contructor_Cliente_Seleccionado cliente = new Contructor_Cliente_Seleccionado();
    private String user = null;

    /**
     * Creates new form frm_Cotizar
     */
    public frm_Gestionar_Ganacias(Object usua) {

        user = usua.toString();
        this.setTitle("GANANCIAS INVENTARIO ACTUAL");
        initComponents();
        cargarTablaInventario();
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
        columnas_inventario[15] = "UTILIDAD";
        columnas_inventario[16] = "id_producto";
        columnas_inventario[17] = "nombre_producto";
        columnas_inventario[18] = "id_categoria";
        columnas_inventario[19] = "id_presentacion";
        columnas_inventario[20] = "id_marca";
        columnas_inventario[21] = "id_categoria";
        columnas_inventario[22] = "CATEGORIA";
        columnas_inventario[23] = "id_medicion";
        columnas_inventario[24] = "MEDICION";
        columnas_inventario[25] = "ID_PREENTACION";
        columnas_inventario[26] = "PRESENTACION";
        columnas_inventario[27] = "ELIMINALIMI1R1";
        columnas_inventario[28] = "ELISI";
        columnas_inventario[29] = "ELIMINARA";
        columnas_inventario[30] = "nueba";
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
            columnas_eliminar_inventario[i]=16;
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
//        //columnas_eliminar_inventario[17] = 15;        
    }

   
    
   
    private void consultarDatosUsuario() {
        Controlador.Funciones_Generales funciones_producto = new Funciones_Generales();
        usuario_activo = funciones_producto.usuario(user);
    }

    private void consultarDatosMiEmpresa() {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa = fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }

  
    
     private void cargarTablaInventario()
    {
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_Entrada_Inventario n = new Funciones_Entrada_Inventario();
        Funciones_Generales fun = new Funciones_Generales();
        consultarDatosUsuario();
        
        try{
            parametrosTablaInventario();
           lbl_producto_mas_ganancia.setText(fun.funcionProductoMasGanacia(usuario_activo.getId_sucursal()));
           lbl_valor_inventario.setText(String.valueOf(n.funcionCuantoDineroInventario(usuario_activo.getId_sucursal())));
           lbl_ganacia_inventario.setText(String.valueOf(n.funcionCuantoGanaciaInventario(usuario_activo.getId_sucursal())));
           lbl_inversion_inventario.setText(String.valueOf(n.totalInversionInventario(usuario_activo.getId_sucursal())));
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
     
     private void evento_txt_tecla_presionada_buscar_producto_inventario()
    {
        
        String comboBox = "";
        comboBox= cmb_buscar_por_inventario_producto.getSelectedItem().toString();
        Funciones_Entrada_Inventario n = new Funciones_Entrada_Inventario();     
        lbl_valor_inventario.setText(n.funcionCuantoDineroInventarioPorBusqueda(txt_buscar_producto_en_inventario.getText(), comboBox, usuario_activo.getId_sucursal()).toString());
        lbl_ganacia_inventario.setText(n.funcionCuantoGanaciaInventarioPorBusqueda(txt_buscar_producto_en_inventario.getText(), comboBox, usuario_activo.getId_sucursal()).toString());
        parametrosTablaInventario();
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

//    private void buscarCredito() {
//        String comboBox = "";
//        comboBox = cmb_buscar_por.getSelectedItem().toString();
//        Controlador.Funciones_frm_cuentasCobrar n = new Controlador.Funciones_frm_cuentasCobrar();
//        consultarDatosUsuario();
//        consultarDatosMiEmpresa();
//        parametrosTabla();
//        try {
//            tbl_creditos.setModel(n.funcionBuscarCredito(mi_empresa.getId_empresa(), comboBox, txt_buscar_credito.getText(), columnas, ancho_columnas));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        for (int i = 0; i < columnas_eliminar.length; i++) {
//
//            tbl_creditos.getColumnModel().removeColumn(tbl_creditos.getColumnModel().getColumn(columnas_eliminar[i]));
//        }
//    }

    
   

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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_inventario = new javax.swing.JTable();
        cmb_buscar_por_inventario_producto = new javax.swing.JComboBox();
        txt_buscar_producto_en_inventario = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lbl_valor_inventario = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbl_ganacia_inventario = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_producto_mas_ganancia = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_inversion_inventario = new javax.swing.JLabel();

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

        jLabel3.setText("Buscar :");

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
        tbl_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_inventarioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_inventario);

        cmb_buscar_por_inventario_producto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Identificacion", "Nombre", "Iva", "Marca", "Categoria", "Presentacion", "Expiracion", "Medicion", "Cantidad", "Stock", "Barras", "Precio1", "Precio2", "Sucursal", "Proveedor" }));

        txt_buscar_producto_en_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_producto_en_inventarioKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Ganancias Potenciales");

        lbl_valor_inventario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_valor_inventario.setText("000.000.000");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Valor Venta Inventario.: $");

        lbl_ganacia_inventario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_ganacia_inventario.setForeground(new java.awt.Color(0, 204, 0));
        lbl_ganacia_inventario.setText("000.000.000");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Producto Con Mas Ganancia:");

        lbl_producto_mas_ganancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_producto_mas_ganancia.setText(".............................................");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Inversion Inventario:");

        lbl_inversion_inventario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_inversion_inventario.setText("000.000.000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(1, 1, 1)
                                .addComponent(txt_buscar_producto_en_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_buscar_por_inventario_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(333, 333, 333)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_ganacia_inventario)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_valor_inventario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_producto_mas_ganancia))
                            .addComponent(lbl_inversion_inventario))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbl_valor_inventario)
                    .addComponent(lbl_producto_mas_ganancia)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cmb_buscar_por_inventario_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_buscar_producto_en_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lbl_ganacia_inventario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_inversion_inventario)
                            .addComponent(jLabel15))))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tbl_inventarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_inventarioKeyPressed
        // TODO add your handling code here:
          if(evt.getKeyChar()==KeyEvent.VK_ENTER)
          {
             
          }
    }//GEN-LAST:event_tbl_inventarioKeyPressed

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

    private void txt_buscar_producto_en_inventarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_producto_en_inventarioKeyPressed
        if(evt.getKeyChar()==evt.VK_ENTER)
        {
            evento_txt_tecla_presionada_buscar_producto_inventario();
        }
        else
        {
            
        }
    }//GEN-LAST:event_txt_buscar_producto_en_inventarioKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_accionar_abono;
    private javax.swing.JComboBox cmb_buscar_por_inventario_producto;
    private javax.swing.JDialog dlg_abonar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_cuota_mensual;
    private javax.swing.JLabel lbl_cuotas_pendientes;
    private javax.swing.JLabel lbl_estado_credito;
    private javax.swing.JLabel lbl_ganacia_inventario;
    private javax.swing.JLabel lbl_id_credito;
    private javax.swing.JLabel lbl_inversion_inventario;
    private javax.swing.JLabel lbl_numero_cuota;
    private javax.swing.JLabel lbl_producto_mas_ganancia;
    private javax.swing.JLabel lbl_total_credito;
    private javax.swing.JLabel lbl_valor_inventario;
    private javax.swing.JTable tbl_historial;
    private javax.swing.JTable tbl_inventario;
    private javax.swing.JTextField txt_buscar_producto_en_inventario;
    private javax.swing.JTextField txt_cambio_abono;
    private javax.swing.JTextField txt_cc_cliente;
    private javax.swing.JTextField txt_cliente_abono;
    private javax.swing.JTextField txt_recibe_abono;
    // End of variables declaration//GEN-END:variables
}
