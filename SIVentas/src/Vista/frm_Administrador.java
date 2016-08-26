/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Usuario;
import Constructores.Constructor_usuario_permiso;
import Controlador.Funciones_Cambio_Clave;
import Controlador.Funciones_Generales;
import Controlador.Funciones_Login;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
/**
 *
 * @author Nestor1
 */
public class frm_Administrador extends javax.swing.JFrame{
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
    private String user= null,hora_now =null;
    Constructor_usuario_permiso permisos = new Constructor_usuario_permiso();
    Date now = new Date(System.currentTimeMillis());
   private ResultSet rs= null;
   private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");

    /**
     * Creates new form frm_Administrador
     * @param usuario
     */
    public frm_Administrador(Object usuario) {
        hora_now= hora.format(now);
        user=usuario.toString();
        
//        JOptionPane.showMessageDialog(null, "CONSULTANDO DATOS USUARIOS");
        consultarDatosUsuario();
        
        this.setTitle("BIENVENIDO "+usuario_activo.getNombre()+" ! \t HORA INICIO SESION:"+hora_now+"");
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/sources/1444021490_vector_65_12.png")).getImage());
        inicioSesion();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        lbl_usuario.setText(usuario.toString());
//        JOptionPane.showMessageDialog(null, "Registrar Efectivo Inicial");
        
//        JOptionPane.showMessageDialog(null, "Validdar Permisos");
        
//        JOptionPane.showMessageDialog(null, "Registrar inicio de sesion");
        
        this.setDefaultCloseOperation(0);
        pan_Principal.setBorder(new Fuentes.ImagenFondo("/sources/2.jpg"));
        lbl_usuario.setVisible(false);
    }
    private void inicioSesion()
    {
        registrarEfectivoInicial();
        validarPermisos();
        registrarInicioSesion();
    }
    
    private void registrarInicioSesion()
    {
        consultarDatosUsuario();
        Controlador.Funciones_Generales fun= new Funciones_Generales();
        fun.registrarSesion(usuario_activo.getId_usuario());
    }
     private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
    }
   
    private void registrarEfectivoInicial()
    {
        float efectivo=0;
        Controlador.Funciones_Generales fun = new Funciones_Generales();
        if(fun.efectivoInicial(usuario_activo.getId_sucursal())==0)
        {
            efectivo= Float.parseFloat(JOptionPane.showInputDialog(null, "EFECTIVO INICIAL? $"));
            fun.registrarEfectivoInicio(usuario_activo.getId_sucursal(), efectivo);
        }
        else
        {
            
        }
    }
       
    private void centrarFrames(JInternalFrame form)
    {
        pan_Principal.removeAll();
        pan_Principal.repaint();
        pan_Principal.add(form);
        form.setVisible(true);
        int x =pan_Principal.getWidth()/2 - form.getWidth()/2,y = pan_Principal.getHeight()/2 - form.getHeight()/2;
        form.setLocation(x, y);
        form.setResizable(false);
    }
    
    private void validarPermisos()
    {
        item_balance_general_informe.setVisible(false);
        item_cajon_dinero.setVisible(false);
        item_ganancia_facturacion.setVisible(false);
        item_ganancia_inventario.setVisible(false);
        item_cortes_caja.setVisible(false);
//        item_cambio_clave.setVisible(false);
        item_categoria.setVisible(false);
//        item_cerrar_sesion_usuario.setVisible(false);
        item_cliente.setVisible(false);
//        item_configuracion_usuario.setVisible(false);
        item_corte_caja.setVisible(false);
//        item_entrada_salida_informe.setVisible(false);
        item_entrada_salida_inventario.setVisible(false);
        item_productos_stock.setVisible(false);
        item_facturar.setVisible(false);
        item_facturas_anuladas_informe.setVisible(false);
        item_marcas.setVisible(false);
        item_mas_vendido_informe.setVisible(false);
//        item_mi_empresa.setVisible(false);
//        item_mi_sucursal.setVisible(false);
        item_producto.setVisible(false);
        item_proveedor.setVisible(false);
        item_reimprimir_factura.setVisible(false);
//        item_salir.setVisible(false);
        item_medicion.setVisible(false);
        item_traspaso.setVisible(false);
        item_usuario.setVisible(false);
        item_venta_categoria_informe.setVisible(false);
        item_venta_diaria_informe.setVisible(false);
//        item_venta_empleado_informe.setVisible(false);
        item_venta_mensual_informe.setVisible(false);
        jMenu4.setVisible(false);
        int[]permis = null;
        consultarDatosUsuario();
        Funciones_Generales fun = new Funciones_Generales();
        permisos=fun.permisosUsuario(usuario_activo.getId_usuario());
        permis = permisos.getId_permiso();
        for (int i = 0; i < permis.length; i++) {
            if(permis[i]==1)
            {
                item_producto.setVisible(true);
            }
            if(permis[i]==2)
            {
                item_cliente.setVisible(true);
            }
            if(permis[i]==3)
            {
                item_proveedor.setVisible(true);
            }
            if(permis[i]==4)
            {
                item_categoria.setVisible(true);
            }
            if(permis[i]==5)
            {
                item_marcas.setVisible(true);
            }
            if(permis[i]==6)
            {
                item_medicion.setVisible(true);
            }
            if(permis[i]==7)
            {
                item_usuario.setVisible(true);
            }
            if(permis[i]==8)
            {
                item_facturar.setVisible(true);
            }
            if(permis[i]==9)
            {
                item_entrada_salida_inventario.setVisible(true);
            }
            if(permis[i]==10)
            {
                item_traspaso.setVisible(true);
            }
            if(permis[i]==11)
            {
                item_reimprimir_factura.setVisible(true);
            }
            if(permis[i]==12)
            {
                item_cajon_dinero.setVisible(true);
            }
            if(permis[i]==13)
            {
                item_corte_caja.setVisible(true);
            }
            if(permis[i]==14)
            {
                item_cuentas_pagar.setVisible(true);
            }
            if(permis[i]==15)
            {
                item_productos_stock.setVisible(true);
            }
            if(permis[i]==16)
            {
                item_entrada_salida_informe.setVisible(true);
            }
            if(permis[i]==17)
            {
                item_mas_vendido_informe.setVisible(true);
            }
            if(permis[i]==18)
            {
                item_venta_categoria_informe.setVisible(true);
            }
            if(permis[i]==19)
            {
                item_venta_diaria_informe.setVisible(true);
            }
            if(permis[i]==20)
            {
                item_venta_mensual_informe.setVisible(true);
            }
//            if(permis[i]==21)
//            {
//                item_venta_empleado_informe.setVisible(true);
//            }
            if(permis[i]==22)
            {
                item_balance_general_informe.setVisible(true);
            }
            if(permis[i]==23)
            {
                item_facturas_anuladas_informe.setVisible(true);
            }
            if(permis[i]==24)
            {
                item_historial_informe.setVisible(true);
            }
            if(permis[i]==25)
            {
                item_cuentas_cobrar.setVisible(true);
            }
            if(permis[i]==26)
            {
                item_cuentas_pagar.setVisible(true);
            }
            
            if(permis[i]==27)
            {
                jMenu4.setVisible(true);
            }
            if(permis[i]==28)
            {
                item_cortes_caja.setVisible(true);
            }
            if(permis[i]==29)
            {
                item_ganancia_inventario.setVisible(true);
            }
            if(permis[i]==30)
            {
                item_ganancia_facturacion.setVisible(true);
            }
            if(permis[i]==31)
            {
                item_control_sesion.setVisible(true);
            }
        }
        
        
        
    }
    
    private void cerrarSesion()
    {
        Funciones_Generales fun = new Funciones_Generales();
        consultarDatosUsuario();
        fun.registrarCierreSesion(usuario_activo.getId_usuario());
        this.setVisible(false);
        frm_Login log = new frm_Login();
        log.setVisible(true);
        log.setLocationRelativeTo(null);
        
    }
    
    private void cerrarPrograma()
    {
        Funciones_Generales fun = new Funciones_Generales();
        consultarDatosUsuario();
        fun.registrarCierreSesion(usuario_activo.getId_usuario());
        System.exit(0);
    }
    
    private void cambio_clave()
    {
       consultarDatosUsuario();
       Controlador.Funciones_Cambio_Clave fun = new Funciones_Cambio_Clave();
       fun.funcionCambiar_Clave(usuario_activo.getId_usuario());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jDialog2 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        pan_Principal = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        lbl_usuario = new javax.swing.JLabel();
        Menu_Principal = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        item_producto = new javax.swing.JMenuItem();
        item_cliente = new javax.swing.JMenuItem();
        item_proveedor = new javax.swing.JMenuItem();
        item_categoria = new javax.swing.JMenuItem();
        item_marcas = new javax.swing.JMenuItem();
        item_medicion = new javax.swing.JMenuItem();
        item_usuario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        item_facturar = new javax.swing.JMenuItem();
        item_entrada_salida_inventario = new javax.swing.JMenuItem();
        item_traspaso = new javax.swing.JMenuItem();
        item_reimprimir_factura = new javax.swing.JMenuItem();
        item_cajon_dinero = new javax.swing.JMenuItem();
        item_corte_caja = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        item_cuentas_pagar = new javax.swing.JMenuItem();
        item_cuentas_cobrar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        Menu7 = new javax.swing.JMenu();
        item_productos_stock = new javax.swing.JMenuItem();
        item_mas_vendido_informe = new javax.swing.JMenuItem();
        item_venta_categoria_informe = new javax.swing.JMenuItem();
        item_entrada_salida_informe = new javax.swing.JMenuItem();
        menu8 = new javax.swing.JMenu();
        item_venta_diaria_informe = new javax.swing.JMenuItem();
        item_venta_mensual_informe = new javax.swing.JMenuItem();
        item_balance_general_informe = new javax.swing.JMenuItem();
        item_facturas_anuladas_informe = new javax.swing.JMenuItem();
        item_cortes_caja = new javax.swing.JMenuItem();
        item_ganancia_inventario = new javax.swing.JMenuItem();
        item_ganancia_facturacion = new javax.swing.JMenuItem();
        menu9 = new javax.swing.JMenu();
        item_historial_informe = new javax.swing.JMenuItem();
        item_control_sesion = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        item_mi_empresa = new javax.swing.JMenuItem();
        item_mi_sucursal = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        item_cambio_clave = new javax.swing.JMenuItem();
        item_cerrar_sesion_usuario = new javax.swing.JMenuItem();
        item_salir = new javax.swing.JMenuItem();

        jDialog2.setMinimumSize(new java.awt.Dimension(300, 200));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Usuario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel2.add(jLabel3, gridBagConstraints);

        txt_usuario.setPreferredSize(new java.awt.Dimension(120, 20));
        txt_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel2.add(txt_usuario, gridBagConstraints);

        txt_password.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel2.add(txt_password, gridBagConstraints);

        jButton1.setText("Enviar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel2.add(jButton1, gridBagConstraints);

        jLabel4.setText("Olvidate la Contraseña?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel2.add(jLabel4, gridBagConstraints);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 12, 1, 12);
        jPanel2.add(jDesktopPane1, gridBagConstraints);

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pan_Principal.setPreferredSize(new java.awt.Dimension(650, 444));

        lbl_usuario.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_usuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pan_PrincipalLayout = new javax.swing.GroupLayout(pan_Principal);
        pan_Principal.setLayout(pan_PrincipalLayout);
        pan_PrincipalLayout.setHorizontalGroup(
            pan_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_PrincipalLayout.createSequentialGroup()
                .addGap(0, 1065, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pan_PrincipalLayout.setVerticalGroup(
            pan_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_PrincipalLayout.createSequentialGroup()
                .addContainerGap(628, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        pan_Principal.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu1.setText("Indices");

        item_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632760_vector_65_13.png"))); // NOI18N
        item_producto.setText("Productos");
        item_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_productoActionPerformed(evt);
            }
        });
        jMenu1.add(item_producto);

        item_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632760_vector_65_13.png"))); // NOI18N
        item_cliente.setText("Clientes");
        item_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_clienteActionPerformed(evt);
            }
        });
        jMenu1.add(item_cliente);

        item_proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632760_vector_65_13.png"))); // NOI18N
        item_proveedor.setText("Proveedores");
        item_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_proveedorActionPerformed(evt);
            }
        });
        jMenu1.add(item_proveedor);

        item_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632760_vector_65_13.png"))); // NOI18N
        item_categoria.setText("Categorias");
        item_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_categoriaActionPerformed(evt);
            }
        });
        jMenu1.add(item_categoria);

        item_marcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632760_vector_65_13.png"))); // NOI18N
        item_marcas.setText("Marcas");
        item_marcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_marcasActionPerformed(evt);
            }
        });
        jMenu1.add(item_marcas);

        item_medicion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632760_vector_65_13.png"))); // NOI18N
        item_medicion.setText("Medicion");
        item_medicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_medicionActionPerformed(evt);
            }
        });
        jMenu1.add(item_medicion);

        item_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632737_vector_65_02.png"))); // NOI18N
        item_usuario.setText("Usuarios");
        item_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_usuarioActionPerformed(evt);
            }
        });
        jMenu1.add(item_usuario);

        Menu_Principal.add(jMenu1);

        jMenu2.setText("Procesos");

        item_facturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632767_vector_65_04.png"))); // NOI18N
        item_facturar.setText("Facturar / Vender");
        item_facturar.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444022267_vector_65_04.png"))); // NOI18N
        item_facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_facturarActionPerformed(evt);
            }
        });
        jMenu2.add(item_facturar);

        item_entrada_salida_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632760_vector_65_13.png"))); // NOI18N
        item_entrada_salida_inventario.setText("Entrada/Salida Inventiario");
        item_entrada_salida_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_entrada_salida_inventarioActionPerformed(evt);
            }
        });
        jMenu2.add(item_entrada_salida_inventario);

        item_traspaso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632775_vector_65_01.png"))); // NOI18N
        item_traspaso.setText("Traspaso Productos");
        item_traspaso.setEnabled(false);
        item_traspaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_traspasoActionPerformed(evt);
            }
        });
        jMenu2.add(item_traspaso);

        item_reimprimir_factura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632809_vector_65_10.png"))); // NOI18N
        item_reimprimir_factura.setText("Reimprimir Factura");
        item_reimprimir_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_reimprimir_facturaActionPerformed(evt);
            }
        });
        jMenu2.add(item_reimprimir_factura);

        item_cajon_dinero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632819_vector_65_05.png"))); // NOI18N
        item_cajon_dinero.setText("Cajon Dinero");
        item_cajon_dinero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_cajon_dineroActionPerformed(evt);
            }
        });
        jMenu2.add(item_cajon_dinero);

        item_corte_caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444633096_vector_65_08.png"))); // NOI18N
        item_corte_caja.setText("Cortes de Caja");
        item_corte_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_corte_cajaActionPerformed(evt);
            }
        });
        jMenu2.add(item_corte_caja);

        Menu_Principal.add(jMenu2);

        jMenu6.setText("Cartera");

        item_cuentas_pagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632831_vector_65_07.png"))); // NOI18N
        item_cuentas_pagar.setText("Cuentas por Pagar");
        item_cuentas_pagar.setEnabled(false);
        item_cuentas_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_cuentas_pagarActionPerformed(evt);
            }
        });
        jMenu6.add(item_cuentas_pagar);

        item_cuentas_cobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632752_vector_65_04.png"))); // NOI18N
        item_cuentas_cobrar.setText("Cuentas por Cobrar");
        item_cuentas_cobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_cuentas_cobrarActionPerformed(evt);
            }
        });
        jMenu6.add(item_cuentas_cobrar);

        Menu_Principal.add(jMenu6);

        jMenu3.setText("Reportes");

        Menu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444633085_vector_65_03.png"))); // NOI18N
        Menu7.setText("Inventario");

        item_productos_stock.setText("Producto en Stock");
        item_productos_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_productos_stockActionPerformed(evt);
            }
        });
        Menu7.add(item_productos_stock);

        item_mas_vendido_informe.setText("Producto Mas Vendido");
        item_mas_vendido_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_mas_vendido_informeActionPerformed(evt);
            }
        });
        Menu7.add(item_mas_vendido_informe);

        item_venta_categoria_informe.setText("Venta por Categoria");
        item_venta_categoria_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_venta_categoria_informeActionPerformed(evt);
            }
        });
        Menu7.add(item_venta_categoria_informe);

        item_entrada_salida_informe.setText("Entrada/Salida Producto");
        item_entrada_salida_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_entrada_salida_informeActionPerformed(evt);
            }
        });
        Menu7.add(item_entrada_salida_informe);

        jMenu3.add(Menu7);

        menu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444633096_vector_65_08.png"))); // NOI18N
        menu8.setText("Contable");

        item_venta_diaria_informe.setText("Venta Diaria");
        item_venta_diaria_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_venta_diaria_informeActionPerformed(evt);
            }
        });
        menu8.add(item_venta_diaria_informe);

        item_venta_mensual_informe.setText("Venta Mensual");
        item_venta_mensual_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_venta_mensual_informeActionPerformed(evt);
            }
        });
        menu8.add(item_venta_mensual_informe);

        item_balance_general_informe.setText("Balance General");
        item_balance_general_informe.setEnabled(false);
        item_balance_general_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_balance_general_informeActionPerformed(evt);
            }
        });
        menu8.add(item_balance_general_informe);

        item_facturas_anuladas_informe.setText("Facturas Anuladas");
        item_facturas_anuladas_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_facturas_anuladas_informeActionPerformed(evt);
            }
        });
        menu8.add(item_facturas_anuladas_informe);

        item_cortes_caja.setText("Cortes de Caja");
        item_cortes_caja.setEnabled(false);
        item_cortes_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_cortes_cajaActionPerformed(evt);
            }
        });
        menu8.add(item_cortes_caja);

        item_ganancia_inventario.setText("Ganacia Inventario");
        item_ganancia_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ganancia_inventarioActionPerformed(evt);
            }
        });
        menu8.add(item_ganancia_inventario);

        item_ganancia_facturacion.setText("Ganancia Facturacion");
        item_ganancia_facturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ganancia_facturacionActionPerformed(evt);
            }
        });
        menu8.add(item_ganancia_facturacion);

        jMenu3.add(menu8);

        menu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632745_vector_65_14.png"))); // NOI18N
        menu9.setText("Sistema");

        item_historial_informe.setText("Historial Procesos");
        item_historial_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_historial_informeActionPerformed(evt);
            }
        });
        menu9.add(item_historial_informe);

        item_control_sesion.setText("Control Sesion");
        item_control_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_control_sesionActionPerformed(evt);
            }
        });
        menu9.add(item_control_sesion);

        jMenu3.add(menu9);

        Menu_Principal.add(jMenu3);

        jMenu4.setText("Mi Empresa");

        item_mi_empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632745_vector_65_14.png"))); // NOI18N
        item_mi_empresa.setText("Datos Mi Empresa");
        item_mi_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_mi_empresaActionPerformed(evt);
            }
        });
        jMenu4.add(item_mi_empresa);

        item_mi_sucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632745_vector_65_14.png"))); // NOI18N
        item_mi_sucursal.setText("Datos mi Sucursal");
        item_mi_sucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_mi_sucursalActionPerformed(evt);
            }
        });
        jMenu4.add(item_mi_sucursal);

        Menu_Principal.add(jMenu4);

        jMenu5.setText("Usuario");

        item_cambio_clave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632775_vector_65_01.png"))); // NOI18N
        item_cambio_clave.setText("Cambio Clave");
        item_cambio_clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_cambio_claveActionPerformed(evt);
            }
        });
        jMenu5.add(item_cambio_clave);

        item_cerrar_sesion_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632775_vector_65_01.png"))); // NOI18N
        item_cerrar_sesion_usuario.setText("Cerrar Sesion");
        item_cerrar_sesion_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_cerrar_sesion_usuarioActionPerformed(evt);
            }
        });
        jMenu5.add(item_cerrar_sesion_usuario);

        item_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444632729_vector_65_12.png"))); // NOI18N
        item_salir.setText("Salir");
        item_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_salirActionPerformed(evt);
            }
        });
        jMenu5.add(item_salir);

        Menu_Principal.add(jMenu5);

        setJMenuBar(Menu_Principal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void item_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_productoActionPerformed
        // TODO add your handling code here:
        
        frm_Producto frmProducto= new frm_Producto(lbl_usuario.getText());        
        centrarFrames(frmProducto);
    }//GEN-LAST:event_item_productoActionPerformed

    private void item_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_clienteActionPerformed
        // TODO add your handling code here:
        frm_Cliente frmCliente= new frm_Cliente(lbl_usuario.getText());
        centrarFrames(frmCliente);
    }//GEN-LAST:event_item_clienteActionPerformed

    private void item_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_proveedorActionPerformed
        // TODO add your handling code here:
        
        frm_Proveedores frmProeedor= new frm_Proveedores(lbl_usuario.getText());
        centrarFrames(frmProeedor);
    }//GEN-LAST:event_item_proveedorActionPerformed

    private void item_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_categoriaActionPerformed
        // TODO add your handling code here:
        frm_Categorias frm_Categorias= new frm_Categorias(lbl_usuario.getText());
        centrarFrames(frm_Categorias);
        
    }//GEN-LAST:event_item_categoriaActionPerformed

    private void item_marcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_marcasActionPerformed
        // TODO add your handling code here:
        
        frm_Marcas marcas= new frm_Marcas(lbl_usuario.getText());
        centrarFrames(marcas);
    }//GEN-LAST:event_item_marcasActionPerformed

    private void item_medicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_medicionActionPerformed
        // TODO add your handling code here:
        
         frm_Medicion frmTipocCompra= new frm_Medicion(lbl_usuario.getText());
        centrarFrames(frmTipocCompra);
    }//GEN-LAST:event_item_medicionActionPerformed

    private void item_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_usuarioActionPerformed
        // TODO add your handling code here:
        
        frm_Usuario frm_Usuario= new frm_Usuario(lbl_usuario.getText());
        centrarFrames(frm_Usuario);
        
    }//GEN-LAST:event_item_usuarioActionPerformed

    private void item_facturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_facturarActionPerformed
        // TODO add your handling code here:
        
        frm_Factura frm_Facturar= new frm_Factura(lbl_usuario.getText());
        centrarFrames(frm_Facturar);
    }//GEN-LAST:event_item_facturarActionPerformed

    private void item_entrada_salida_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_entrada_salida_inventarioActionPerformed
        // TODO add your handling code here:
        
        
        frm_EntradaInventario frmInventario= new frm_EntradaInventario(lbl_usuario.getText());
        centrarFrames(frmInventario);
    }//GEN-LAST:event_item_entrada_salida_inventarioActionPerformed

    private void item_traspasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_traspasoActionPerformed
        // TODO add your handling code here:
        frm_TraspasoSucursal frmTraspaso= new frm_TraspasoSucursal();
        centrarFrames(frmTraspaso);
        
    }//GEN-LAST:event_item_traspasoActionPerformed

    private void item_reimprimir_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_reimprimir_facturaActionPerformed
        // TODO add your handling code here:
        
        frm_GestionarFactura frmGestionarFactura= new frm_GestionarFactura(lbl_usuario.getText());
        centrarFrames(frmGestionarFactura);
    }//GEN-LAST:event_item_reimprimir_facturaActionPerformed

    private void item_cajon_dineroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_cajon_dineroActionPerformed
        // TODO add your handling code here:
        
        frm_CajonDinero frmCajonDinero= new frm_CajonDinero(lbl_usuario.getText());
        centrarFrames(frmCajonDinero);
    }//GEN-LAST:event_item_cajon_dineroActionPerformed

    private void item_corte_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_corte_cajaActionPerformed
        // TODO add your handling code here:
        
        frm_CorteCaja frmCorteCaja= new frm_CorteCaja(lbl_usuario.getText());
        centrarFrames(frmCorteCaja);
    }//GEN-LAST:event_item_corte_cajaActionPerformed

    private void txt_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuarioActionPerformed

    private void item_balance_general_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_balance_general_informeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item_balance_general_informeActionPerformed

    private void item_cerrar_sesion_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_cerrar_sesion_usuarioActionPerformed
        // TODO add your handling code here:
        
        cerrarSesion();
        
    }//GEN-LAST:event_item_cerrar_sesion_usuarioActionPerformed

    private void item_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_salirActionPerformed
        // TODO add your handling code here:
        cerrarPrograma();
    }//GEN-LAST:event_item_salirActionPerformed

    private void item_productos_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_productos_stockActionPerformed
        // TODO add your handling code here:
         frm_InformeStock frmStock= new frm_InformeStock(lbl_usuario.getText());
        centrarFrames(frmStock);
        
    }//GEN-LAST:event_item_productos_stockActionPerformed

    private void item_historial_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_historial_informeActionPerformed
        // TODO add your handling code here:
        frm_HistorialInforme frmInforme = new frm_HistorialInforme(lbl_usuario.getText());
        centrarFrames(frmInforme);
    }//GEN-LAST:event_item_historial_informeActionPerformed

    private void item_mas_vendido_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_mas_vendido_informeActionPerformed
        // TODO add your handling code here:
        frm_MasVendidoInforme frmMAsVendido = new frm_MasVendidoInforme(lbl_usuario.getText());
        centrarFrames(frmMAsVendido);
    }//GEN-LAST:event_item_mas_vendido_informeActionPerformed

    private void item_venta_categoria_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_venta_categoria_informeActionPerformed
        // TODO add your handling code here:
        
        frm_MasVendidoCategoriaInforme frmCategoriaInforme = new frm_MasVendidoCategoriaInforme(lbl_usuario.getText());
        centrarFrames(frmCategoriaInforme);
    }//GEN-LAST:event_item_venta_categoria_informeActionPerformed

    private void item_mi_sucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_mi_sucursalActionPerformed
        // TODO add your handling code here:
        frm_Mi_Sucursal frmSucursal = new frm_Mi_Sucursal(lbl_usuario.getText());
        centrarFrames(frmSucursal);
    }//GEN-LAST:event_item_mi_sucursalActionPerformed

    private void item_venta_diaria_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_venta_diaria_informeActionPerformed
        // TODO add your handling code here:
        frm_venta_diaria_informe frm_venta_diaria = new frm_venta_diaria_informe(lbl_usuario.getText());
        centrarFrames(frm_venta_diaria);
    }//GEN-LAST:event_item_venta_diaria_informeActionPerformed

    private void item_venta_mensual_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_venta_mensual_informeActionPerformed
        // TODO add your handling code here:
        frm_venta_mensual_informe frmVentaMensual = new frm_venta_mensual_informe(lbl_usuario.getText());
        centrarFrames(frmVentaMensual);
    }//GEN-LAST:event_item_venta_mensual_informeActionPerformed

    private void item_control_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_control_sesionActionPerformed
        // TODO add your handling code here:
        frm_Control_Sesion frmControlSesion = new frm_Control_Sesion(lbl_usuario.getText());
        centrarFrames(frmControlSesion);
    }//GEN-LAST:event_item_control_sesionActionPerformed

    private void item_cambio_claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_cambio_claveActionPerformed
        // TODO add your handling code here:
        
        cambio_clave();
    }//GEN-LAST:event_item_cambio_claveActionPerformed

    private void item_facturas_anuladas_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_facturas_anuladas_informeActionPerformed
        // TODO add your handling code here:
        frm_FacturaAnuladas frmFacturasAnuladas = new frm_FacturaAnuladas(lbl_usuario.getText());
        centrarFrames(frmFacturasAnuladas);
        
        
    }//GEN-LAST:event_item_facturas_anuladas_informeActionPerformed

    private void item_cuentas_cobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_cuentas_cobrarActionPerformed
        // TODO add your handling code here:
        
        frm_CuentasCobrar frm_CuentasCobrar= new frm_CuentasCobrar(lbl_usuario.getText());
        centrarFrames(frm_CuentasCobrar);
    }//GEN-LAST:event_item_cuentas_cobrarActionPerformed

    private void item_cuentas_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_cuentas_pagarActionPerformed
        // TODO add your handling code here:
        frm_CuentasPagar frm_cuentasPagar = new frm_CuentasPagar(lbl_usuario.getText());
        centrarFrames(frm_cuentasPagar);
    }//GEN-LAST:event_item_cuentas_pagarActionPerformed

    private void item_ganancia_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ganancia_inventarioActionPerformed
        // TODO add your handling code here:
        frm_Gestionar_Ganacias frm_gestionar_ganacias = new frm_Gestionar_Ganacias(lbl_usuario.getText());
        centrarFrames(frm_gestionar_ganacias);
    }//GEN-LAST:event_item_ganancia_inventarioActionPerformed

    private void item_cortes_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_cortes_cajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_item_cortes_cajaActionPerformed

    private void item_ganancia_facturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ganancia_facturacionActionPerformed
        // TODO add your handling code here:
        frm_Ganancias_Facturacion frm_ganancia_facturacion = new frm_Ganancias_Facturacion(lbl_usuario.getText());
        centrarFrames(frm_ganancia_facturacion);
        
    }//GEN-LAST:event_item_ganancia_facturacionActionPerformed

    private void item_mi_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_mi_empresaActionPerformed
        // TODO add your handling code here:
        frm_Mi_Empresa frm_empresa = new frm_Mi_Empresa(lbl_usuario.getText());
        centrarFrames(frm_empresa);
    }//GEN-LAST:event_item_mi_empresaActionPerformed

    private void item_entrada_salida_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_entrada_salida_informeActionPerformed
        // TODO add your handling code here:
        
        frm_Informe_Inventario frm_entrada_informe = new frm_Informe_Inventario(lbl_usuario.getText());
        centrarFrames(frm_entrada_informe);
    }//GEN-LAST:event_item_entrada_salida_informeActionPerformed
//  public static void main(String[] args) {
//        // TODO code application logic here
//        
//        frm_Administrador admin = new frm_Administrador();
//        
//        admin.setVisible(true);
//    }
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Menu7;
    private javax.swing.JMenuBar Menu_Principal;
    private javax.swing.JMenuItem item_balance_general_informe;
    private javax.swing.JMenuItem item_cajon_dinero;
    private javax.swing.JMenuItem item_cambio_clave;
    private javax.swing.JMenuItem item_categoria;
    private javax.swing.JMenuItem item_cerrar_sesion_usuario;
    private javax.swing.JMenuItem item_cliente;
    private javax.swing.JMenuItem item_control_sesion;
    private javax.swing.JMenuItem item_corte_caja;
    private javax.swing.JMenuItem item_cortes_caja;
    private javax.swing.JMenuItem item_cuentas_cobrar;
    private javax.swing.JMenuItem item_cuentas_pagar;
    private javax.swing.JMenuItem item_entrada_salida_informe;
    private javax.swing.JMenuItem item_entrada_salida_inventario;
    private javax.swing.JMenuItem item_facturar;
    private javax.swing.JMenuItem item_facturas_anuladas_informe;
    private javax.swing.JMenuItem item_ganancia_facturacion;
    private javax.swing.JMenuItem item_ganancia_inventario;
    private javax.swing.JMenuItem item_historial_informe;
    private javax.swing.JMenuItem item_marcas;
    private javax.swing.JMenuItem item_mas_vendido_informe;
    private javax.swing.JMenuItem item_medicion;
    private javax.swing.JMenuItem item_mi_empresa;
    private javax.swing.JMenuItem item_mi_sucursal;
    private javax.swing.JMenuItem item_producto;
    private javax.swing.JMenuItem item_productos_stock;
    private javax.swing.JMenuItem item_proveedor;
    private javax.swing.JMenuItem item_reimprimir_factura;
    private javax.swing.JMenuItem item_salir;
    private javax.swing.JMenuItem item_traspaso;
    private javax.swing.JMenuItem item_usuario;
    private javax.swing.JMenuItem item_venta_categoria_informe;
    private javax.swing.JMenuItem item_venta_diaria_informe;
    private javax.swing.JMenuItem item_venta_mensual_informe;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_usuario;
    private javax.swing.JMenu menu8;
    private javax.swing.JMenu menu9;
    private javax.swing.JDesktopPane pan_Principal;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
