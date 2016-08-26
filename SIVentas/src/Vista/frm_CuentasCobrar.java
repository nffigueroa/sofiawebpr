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
public class frm_CuentasCobrar extends javax.swing.JInternalFrame {

    public String[] columnas = new String[15],columnasAbono = new String[9];
    public int[] ancho_columnas = new int[15], columnas_eliminar = new int[6],colum_eliminar= new int[2];
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
    public frm_CuentasCobrar(Object usua) {

        user = usua.toString();
        this.setTitle("CUENTAS POR COBRAR");
        initComponents();
        cargarTable();
    }

    private void parametrosTabla() {
        columnas[0] = "IDENTIFICACION";
        columnas[1] = "VALOR/MENSUAL";
        columnas[2] = "TOTAL";
        columnas[3] = "SUBTOTAL";
        columnas[4] = "IVA";
        columnas[5] = "DESCUENTO";
        columnas[6] = "% INTERES";
        columnas[7] = "CUOTAS";
        columnas[8] = "C. PAGAS";
        columnas[9] = "C. PENDIENTES";
        columnas[10] = "ESTADO";
        columnas[11] = "USUARIO";
        columnas[12] = "FECHA";
        columnas[13] = "HORA";
        columnas[14] = "CLIENTE";

        ancho_columnas[0] = 200;
        ancho_columnas[1] = 0;
        ancho_columnas[2] = 0;
        ancho_columnas[3] = 0;
        ancho_columnas[4] = 0;
        ancho_columnas[5] = 0;
        ancho_columnas[6] = 100;
        columnas_eliminar[0] = 15;
        columnas_eliminar[1] = 15;
        columnas_eliminar[2] = 15;
        columnas_eliminar[3] = 15;
        columnas_eliminar[4] = 15;
        columnas_eliminar[5] = 15;

    }

    private void parametrosTablaHistorial() {
        columnasAbono[0] = "ID CUOTA";
        columnasAbono[1] = "ID CREDITO";
        columnasAbono[2] = "# CUOTA";
        columnasAbono[3] = "RECIBE";
        columnasAbono[4] = "CAMBIO";
        columnasAbono[5] = "FECHA PAGAR";
        columnasAbono[6] = "FECHA PAGO";
        columnasAbono[7] = "HORA PAGO";
        columnasAbono[8] = "USUARIO";

        ancho_columnas[0] = 200;
        ancho_columnas[1] = 0;
        ancho_columnas[2] = 0;
        ancho_columnas[3] = 0;
        ancho_columnas[4] = 0;
        ancho_columnas[5] = 0;
        ancho_columnas[6] = 100;
//        colum_eliminar[0] = 9;
//        colum_eliminar[1] = 9;
        
//        columnas_eliminar[2] = 15;
//        columnas_eliminar[3] = 15;
//        columnas_eliminar[4] = 15;
//        columnas_eliminar[5] = 15;

    }
    
    private void operacionCambio()
      {
          int id_credito = 0;
          id_credito = Integer.parseInt(lbl_id_credito.getText());
          Controlador.Funciones_frm_cuentasCobrar cun = new Funciones_frm_cuentasCobrar();
          float recibe=0,cuota = (Float.parseFloat(String.valueOf(cun.funcionValorMensualCredito(id_credito))));
          Float cambio;   
          Object aux2 ;
          recibe = (Float.parseFloat(txt_recibe_abono.getText()));
          cambio = recibe - cuota;
          aux2= cambio;
          aux2=formateador.format(aux2);
          txt_cambio_abono.setText(aux2.toString());
      }

    private void consultarDatosUsuario() {
        Controlador.Funciones_Generales funciones_producto = new Funciones_Generales();
        usuario_activo = funciones_producto.usuario(user);
    }

    private void consultarDatosMiEmpresa() {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa = fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }

    private void cargarTable() {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Controlador.Funciones_frm_cuentasCobrar n = new Controlador.Funciones_frm_cuentasCobrar();

        try {
            parametrosTabla();
            consultarDatosUsuario();
            consultarDatosMiEmpresa();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            tbl_creditos.setModel(n.funcionLlenarCredito(mi_empresa.getId_empresa(), columnas, ancho_columnas));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < columnas_eliminar.length - 1; i++) {

            tbl_creditos.getColumnModel().removeColumn(tbl_creditos.getColumnModel().getColumn(columnas_eliminar[i]));
        }

    }

    private void buscarCredito() {
        String comboBox = "";
        comboBox = cmb_buscar_por.getSelectedItem().toString();
        Controlador.Funciones_frm_cuentasCobrar n = new Controlador.Funciones_frm_cuentasCobrar();
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        parametrosTabla();
        try {
            tbl_creditos.setModel(n.funcionBuscarCredito(mi_empresa.getId_empresa(), comboBox, txt_buscar_credito.getText(), columnas, ancho_columnas));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_creditos.getColumnModel().removeColumn(tbl_creditos.getColumnModel().getColumn(columnas_eliminar[i]));
        }
    }

    private void abonarCredito() {
        Controlador.Funciones_Generales fun = new Funciones_Generales();
        cliente = fun.llenarConstructorClientePorId(Integer.parseInt(tbl_creditos.getModel().getValueAt(tbl_creditos.getSelectedRow(), 14).toString()));
        txt_cliente_abono.setText(cliente.getNombre()+" "+cliente.getApellido());
        Controlador.Funciones_frm_cuentasCobrar cun = new Funciones_frm_cuentasCobrar();
        dlg_abonar.setLocationRelativeTo(null);
        dlg_abonar.setVisible(true);
        dlg_abonar.setTitle("Abonar");
        txt_cc_cliente.setText(cliente.getCodigo().toString());
        parametrosTablaHistorial();
        int id_credito = 0;
        id_credito = Integer.parseInt(tbl_creditos.getModel().getValueAt(tbl_creditos.getSelectedRow(), 0).toString());
        lbl_total_credito.setText(String.valueOf(formateador.format(cun.funcionValorTotalCredito(id_credito))));
        lbl_cuota_mensual.setText(String.valueOf(formateador.format(cun.funcionValorMensualCredito(id_credito))));
        lbl_estado_credito.setText(cun.funcionDatosCredito(id_credito));
        if(lbl_estado_credito.getText().equals("Finalizado"))
        {
            btn_accionar_abono.setEnabled(false);
        }
        lbl_cuotas_pendientes.setText(String.valueOf(cun.funcionNumeroCuotas(id_credito)));
        lbl_id_credito.setText(String.valueOf(id_credito));
        lbl_numero_cuota.setText(String.valueOf(cun.funcionSiguienteCuota(id_credito)));
        tbl_historial.setModel(cun.funcionLlenarHistorialCredito(id_credito, columnasAbono, ancho_columnas));
        for (int i = 0; i < colum_eliminar.length; i++) {

            tbl_historial.getColumnModel().removeColumn(tbl_historial.getColumnModel().getColumn(colum_eliminar[i]));
        }
    }
    
    private void registrarAbonoCuota()
    {
        Funciones_frm_cuentasCobrar fun = new Funciones_frm_cuentasCobrar();
        if(fun.funcionRegistrarAbonoCredito(Integer.parseInt(lbl_id_credito.getText()),
                Integer.parseInt(lbl_numero_cuota.getText()), txt_recibe_abono.getText(), txt_cambio_abono.getText(),
                date.format(now), hora.format(now), usuario_activo.getId_usuario(), Integer.parseInt(lbl_cuotas_pendientes.getText())))
        {
            JOptionPane.showMessageDialog(null, "SE REGISTRO PAGO!");
            dlg_abonar.dispose();
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
        txt_buscar_credito = new javax.swing.JTextField();
        cmb_buscar_por = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_creditos = new javax.swing.JTable();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuentas Por Cobrar"));

        jLabel3.setText("Buscar :");

        txt_buscar_credito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_creditoKeyPressed(evt);
            }
        });

        cmb_buscar_por.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "IDENTIFICACION", "VALOR/MENSUAL", "TOTAL", "SUBTOTAL", "IVA", "DESCUENTO", "% INTERES", "CUOTAS", "CUOTAS/PAGAS", "CUOTAS/PENDIENTES", "ESTADO", "USUARIO", "FECHA", "HORA" }));

        tbl_creditos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_creditos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_creditosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_creditos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar_credito, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_buscar_por, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_buscar_credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_buscar_por, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscar_creditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_creditoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == evt.VK_ENTER) {
            buscarCredito();
        } else {

        }

    }//GEN-LAST:event_txt_buscar_creditoKeyPressed

    private void txt_cliente_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cliente_abonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cliente_abonoActionPerformed

    private void txt_recibe_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_recibe_abonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_recibe_abonoActionPerformed

    private void txt_cambio_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cambio_abonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cambio_abonoActionPerformed

    private void tbl_creditosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_creditosKeyPressed
        // TODO add your handling code here:
          if(evt.getKeyChar()==KeyEvent.VK_ENTER)
          {
              abonarCredito();
          }
    }//GEN-LAST:event_tbl_creditosKeyPressed

    private void txt_cc_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cc_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cc_clienteActionPerformed

    private void btn_accionar_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_accionar_abonoActionPerformed
        // TODO add your handling code here:
        
        registrarAbonoCuota();
        cargarTable();
                
    }//GEN-LAST:event_btn_accionar_abonoActionPerformed

    private void txt_recibe_abonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_recibe_abonoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            operacionCambio();
        }
        else
        {
            
        }
    }//GEN-LAST:event_txt_recibe_abonoKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_accionar_abono;
    private javax.swing.JComboBox cmb_buscar_por;
    private javax.swing.JDialog dlg_abonar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel lbl_id_credito;
    private javax.swing.JLabel lbl_numero_cuota;
    private javax.swing.JLabel lbl_total_credito;
    private javax.swing.JTable tbl_creditos;
    private javax.swing.JTable tbl_historial;
    private javax.swing.JTextField txt_buscar_credito;
    private javax.swing.JTextField txt_cambio_abono;
    private javax.swing.JTextField txt_cc_cliente;
    private javax.swing.JTextField txt_cliente_abono;
    private javax.swing.JTextField txt_recibe_abono;
    // End of variables declaration//GEN-END:variables
}
