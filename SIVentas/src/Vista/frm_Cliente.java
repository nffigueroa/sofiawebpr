/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Usuario;
import Controlador.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Nestor1
 */
public class frm_Cliente extends javax.swing.JInternalFrame {

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
    public frm_Cliente(Object usua) {
        initComponents();
        user=usua.toString();
        cargarTable();
        inicializarForm();
        this.setTitle("CLIENTES");
        
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
        columnas[2] = "APELLIDO";
        columnas[3] = "IDENTIDAD";
        columnas[4] = "TELEFONO";
        columnas[5] = "DIRECCION";
        columnas[6] = "MAIL";
        columnas[7] = "id_ciudad";
        columnas[8] = "id_ciudad_2";
        columnas[9] = "CIUDAD";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        columnas_eliminar[0] = 7;
        columnas_eliminar[1] = 7;
        columnas_eliminar[2] = 7;
       
    }
    
    
    
    
    private void cargarTable() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_clientes n = new Funciones_frm_clientes();
        
        try{
            parametrosTabla();
            consultarDatosUsuario();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_clientes.setModel(n.llenarCliente(usuario_activo.getId_sucursal(),columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length-1; i++) {

            tbl_clientes.getColumnModel().removeColumn(tbl_clientes.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         
    }
    
     private void inicializarForm() 
    {
        TitledBorder borde1 = new TitledBorder(new EtchedBorder(),"Formulario Clientes");
        pan_formulario.setBorder(borde1);
        
        TitledBorder borde2 = new TitledBorder(new EtchedBorder(),"BuscarClientes");
        pan_central.setBorder(borde2);
        
        TitledBorder borde3 = new TitledBorder(new EtchedBorder(),"Tabla Clientes");
        pan_tabla_cliente.setBorder(borde3);
        
        txt_id_cliente.setVisible(false);
         Object [] items_ciudad;
        Controlador.Funciones_frm_clientes funciones_cliente= new Controlador.Funciones_frm_clientes();
        
        items_ciudad= funciones_cliente.llenarComboCiudad();
        for (int t = 0; t < items_ciudad.length; t++) {
            cmb_ciudad_cliente.addItem(items_ciudad[t]);
        }
       
    }
    
     private void btn_agregar_cliente()
    {
       String nombre,apellido,telefono,direccion,email,usuario_creacion,identi; //Los campos dde texto del formulario
       Object ciudad;       //Los combobox del formulario
       try
       {
       Constructor_Usuario con_usuario = new Constructor_Usuario();
       usuario_creacion=con_usuario.getUsuario();
       nombre=txt_nombre_cliente.getText();
       apellido = txt_apellido_cliente.getText();
       telefono = txt_telefono_cliente.getText();
       direccion = txt_direccion_cliente.getText();
       email= txt_mail_cliente.getText();
       ciudad= cmb_ciudad_cliente.getSelectedItem();
       identi= txt_identificacion_cliente.getText();
       if(validarForm(nombre, apellido,telefono,direccion,email,ciudad)==true){
       Funciones_frm_clientes clientes_funciones = new Funciones_frm_clientes();
       clientes_funciones.insertarCliente(usuario_activo.getId_usuario(),usuario_activo.getId_sucursal(),nombre, apellido, telefono, direccion, email, ciudad,identi);
       cargarTable();
       JOptionPane.showMessageDialog(null, "Se registro a "+nombre+" exitosamente!");
        }
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }

       
    }
     
     private boolean validarForm(String nombre,String apellido, String telefono, String direccion, String email,Object ciudad)
    {
        if(nombre.equals(""))
        {
            JOptionPane.showMessageDialog(null, "El campo NOMBRE no puede estar vacio!");
            txt_nombre_cliente.requestFocus();
            return false;
        }
        else
            {
                if(apellido.equals(""))
                        {
                            JOptionPane.showMessageDialog(null, "El campo APELLIDO no puede estar vacio!");
                            return false;
                        }
                else
                {
                    if((telefono.equals("")))
                    {
                        JOptionPane.showMessageDialog(null, "El campo TELEFONO no puede estar vacio!");
                        return false;
                    }
                    else
                    {
                        if((direccion.equals("")))
                        {
                            JOptionPane.showMessageDialog(null, "El campo DIRECCION no puede estar vacio!");
                            return false;
                        }
                        else
                        {
                            if((email.equals("")))
                            {
                                JOptionPane.showMessageDialog(null, "El campo E-MAIL no puede estar vacio!");
                                return false;
                            }
                            else
                            {
                                if((ciudad==("...")))
                                {
                                JOptionPane.showMessageDialog(null, "Seleccione una CIUDAD");
                                return false;
                                }
                                
                                return true;
                            }
                        }
                    }
                }
            }
    }
    
      private void btn_limpiar_campos()
    {
        txt_identificacion_cliente.setText(null);
        btn_agregar_cliente.setEnabled(true);
        txt_nombre_cliente.setText(null);
        txt_apellido_cliente.setText(null);
        txt_direccion_cliente.setText(null);
        txt_id_cliente.setText(null);
        txt_mail_cliente.setText(null);
        txt_telefono_cliente.setText(null);
        cmb_ciudad_cliente.setSelectedIndex(0);
        txt_nombre_cliente.requestFocus();
    }
      
      private void btn_eliminar_cliente()
    {
        int aux= Integer.parseInt((tbl_clientes.getModel().getValueAt(tbl_clientes.getSelectedRow(), 0).toString()));
        Funciones_frm_clientes eliminar = new Funciones_frm_clientes();
        eliminar.eliminarCliente(aux);
        cargarTable();
    }
     
      private void llenar_form_actualizar()
    {
    btn_agregar_cliente.setEnabled(false);
    txt_id_cliente.setText(tbl_clientes.getModel().getValueAt(tbl_clientes.getSelectedRow(), 0).toString());
    txt_nombre_cliente.setText(tbl_clientes.getModel().getValueAt(tbl_clientes.getSelectedRow(), 1).toString());
    txt_apellido_cliente.setText((tbl_clientes.getModel().getValueAt(tbl_clientes.getSelectedRow(), 2).toString()));
    txt_telefono_cliente.setText((tbl_clientes.getModel().getValueAt(tbl_clientes.getSelectedRow(), 4).toString()));
    txt_direccion_cliente.setText((tbl_clientes.getModel().getValueAt(tbl_clientes.getSelectedRow(), 5).toString()));
    txt_identificacion_cliente.setText((tbl_clientes.getModel().getValueAt(tbl_clientes.getSelectedRow(), 3).toString()));  
    cmb_ciudad_cliente.setSelectedIndex(Integer.parseInt(tbl_clientes.getModel().getValueAt(tbl_clientes.getSelectedRow(), 7).toString()));
    txt_mail_cliente.setText((tbl_clientes.getModel().getValueAt(tbl_clientes.getSelectedRow(), 6).toString()));  
        
    }
      
      private void btn_actualizar_cliente()
    {
       String nombre,apellido,email,direccion,telefono;
       Object ciudad,id_cliente;
       id_cliente= txt_id_cliente.getText();
       nombre=txt_nombre_cliente.getText();
       ciudad = cmb_ciudad_cliente.getSelectedItem();
       apellido = txt_apellido_cliente.getText();
       email = txt_mail_cliente.getText();
       direccion= txt_direccion_cliente.getText();
       telefono = txt_telefono_cliente.getText();
       Funciones_frm_clientes cliente_funciones = new Funciones_frm_clientes();
       cliente_funciones.actualizarCliente(id_cliente,nombre, apellido, email, direccion, telefono,ciudad);
       cargarTable();
       JOptionPane.showMessageDialog(null, "Se Actualizo "+nombre+" exitosamente!");
    }
      
      private void evento_txt_tecla_presionada_cliente()
    {
        String comboBox = "";
        comboBox= cmb_buscar_por_cliente.getSelectedItem().toString();
        Funciones_frm_clientes n = new Funciones_frm_clientes();    
        consultarDatosUsuario();
        parametrosTabla();
        try{
        tbl_clientes.setModel(n.buscarCliente(usuario_activo.getId_sucursal(),txt_buscar_cliente.getText(),comboBox,columnas, ancho_columnas));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_clientes.getColumnModel().removeColumn(tbl_clientes.getColumnModel().getColumn(columnas_eliminar[i]));
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

        pan_formulario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nombre_cliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_apellido_cliente = new javax.swing.JTextField();
        txt_telefono_cliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_direccion_cliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_mail_cliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmb_ciudad_cliente = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txt_identificacion_cliente = new javax.swing.JTextField();
        pan_tabla_cliente = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_clientes = new javax.swing.JTable();
        pan_central = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_buscar_cliente = new javax.swing.JTextField();
        cmb_buscar_por_cliente = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        btn_agregar_cliente = new javax.swing.JButton();
        btn_limpiar_cliente = new javax.swing.JButton();
        btn_eliminar_cliente = new javax.swing.JButton();
        btn_actualizar_cliente = new javax.swing.JButton();
        txt_id_cliente = new javax.swing.JTextField();

        jLabel1.setText("Nombre:");

        jLabel8.setText("Apellido:");

        jLabel9.setText("Telefono:");

        jLabel10.setText("Direccion:");

        txt_mail_cliente.setText("@");

        jLabel11.setText("E-Mail:");

        jLabel12.setText("Ciudad:");

        cmb_ciudad_cliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "..." }));

        jLabel13.setText("Identificacion:");

        javax.swing.GroupLayout pan_formularioLayout = new javax.swing.GroupLayout(pan_formulario);
        pan_formulario.setLayout(pan_formularioLayout);
        pan_formularioLayout.setHorizontalGroup(
            pan_formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_formularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pan_formularioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pan_formularioLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_identificacion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pan_formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(3, 3, 3)
                .addGroup(pan_formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_apellido_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(txt_mail_cliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pan_formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_formularioLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_telefono_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_direccion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pan_formularioLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_ciudad_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        pan_formularioLayout.setVerticalGroup(
            pan_formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_formularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_apellido_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_telefono_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_direccion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_formularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_mail_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cmb_ciudad_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txt_identificacion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tbl_clientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_clientesMousePressed(evt);
            }
        });
        tbl_clientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_clientesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_clientes);

        javax.swing.GroupLayout pan_tabla_clienteLayout = new javax.swing.GroupLayout(pan_tabla_cliente);
        pan_tabla_cliente.setLayout(pan_tabla_clienteLayout);
        pan_tabla_clienteLayout.setHorizontalGroup(
            pan_tabla_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_tabla_clienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addContainerGap())
        );
        pan_tabla_clienteLayout.setVerticalGroup(
            pan_tabla_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_tabla_clienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel6.setText("Buscar:");

        txt_buscar_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_clienteKeyPressed(evt);
            }
        });

        cmb_buscar_por_cliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Apellido", "Telefono", "Direccion", "Mail" }));

        jLabel7.setText("Por:");

        btn_agregar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634362_add.png"))); // NOI18N
        btn_agregar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_clienteActionPerformed(evt);
            }
        });

        btn_limpiar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634912_delete-notes.png"))); // NOI18N
        btn_limpiar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiar_clienteActionPerformed(evt);
            }
        });

        btn_eliminar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634317_delete.png"))); // NOI18N
        btn_eliminar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_clienteActionPerformed(evt);
            }
        });

        btn_actualizar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634328_reload.png"))); // NOI18N
        btn_actualizar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_clienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_centralLayout = new javax.swing.GroupLayout(pan_central);
        pan_central.setLayout(pan_centralLayout);
        pan_centralLayout.setHorizontalGroup(
            pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_centralLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_buscar_por_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_eliminar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_actualizar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_limpiar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_agregar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pan_centralLayout.setVerticalGroup(
            pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_centralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_agregar_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_actualizar_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_limpiar_cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_eliminar_cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_centralLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_buscar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cmb_buscar_por_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pan_tabla_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pan_formulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pan_central, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(pan_formulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txt_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_central, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_tabla_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_clienteActionPerformed
        // TODO add your handling code here:
        btn_agregar_cliente();
    }//GEN-LAST:event_btn_agregar_clienteActionPerformed

    private void btn_limpiar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiar_clienteActionPerformed
        // TODO add your handling code here:
        btn_limpiar_campos();
    }//GEN-LAST:event_btn_limpiar_clienteActionPerformed

    private void btn_eliminar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_clienteActionPerformed
        // TODO add your handling code here:
        
        btn_eliminar_cliente();
    }//GEN-LAST:event_btn_eliminar_clienteActionPerformed

    private void tbl_clientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_clientesKeyPressed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_tbl_clientesKeyPressed

    private void tbl_clientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_clientesMousePressed
        // TODO add your handling code here:
        llenar_form_actualizar();
        
    }//GEN-LAST:event_tbl_clientesMousePressed

    private void btn_actualizar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_clienteActionPerformed
        // TODO add your handling code here:
        btn_actualizar_cliente();
    }//GEN-LAST:event_btn_actualizar_clienteActionPerformed

    private void txt_buscar_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_clienteKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==evt.VK_ENTER)
        {
            evento_txt_tecla_presionada_cliente();
        }
        else
        {
            
        }
    }//GEN-LAST:event_txt_buscar_clienteKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar_cliente;
    private javax.swing.JButton btn_agregar_cliente;
    private javax.swing.JButton btn_eliminar_cliente;
    private javax.swing.JButton btn_limpiar_cliente;
    private javax.swing.JComboBox cmb_buscar_por_cliente;
    private javax.swing.JComboBox cmb_ciudad_cliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pan_central;
    private javax.swing.JPanel pan_formulario;
    private javax.swing.JPanel pan_tabla_cliente;
    private javax.swing.JTable tbl_clientes;
    private javax.swing.JTextField txt_apellido_cliente;
    private javax.swing.JTextField txt_buscar_cliente;
    private javax.swing.JTextField txt_direccion_cliente;
    private javax.swing.JTextField txt_id_cliente;
    private javax.swing.JTextField txt_identificacion_cliente;
    private javax.swing.JTextField txt_mail_cliente;
    private javax.swing.JTextField txt_nombre_cliente;
    private javax.swing.JTextField txt_telefono_cliente;
    // End of variables declaration//GEN-END:variables
}
