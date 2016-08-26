/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Controlador.Funciones_Entrada_Inventario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_Proveedores;
import Controlador.Funciones_frm_Usuario;
import Controlador.Funciones_frm_clientes;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javafx.scene.layout.Border;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author Nestor1
 */
public class frm_Usuario extends javax.swing.JInternalFrame {
    public String []columnas = new String[14];
        public int[]ancho_columnas = new int[15],columnas_eliminar = new int[4];
        Date now = new Date(System.currentTimeMillis());
        private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
        Constructor_Usuario usuario_activo= new Constructor_Usuario();
        private String user= null;
        Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();

    /**
     * Creates new form frm_Producto
     */
    public frm_Usuario(Object usuario) {
        user= usuario.toString();
        initComponents();
        llenarTablaCategoria();
        inicializarForm();
        cargarTable();
        this.setTitle("USUARIOS");
    }
    private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
        
    }
    private void inicializarForm()
    {
        consultarDatosUsuario();
        Object[]items_ciudad = null;
        Controlador.Funciones_frm_Usuario fun = new Funciones_frm_Usuario();
        fun.llenarComboCargo();
        TitledBorder borde = new TitledBorder(new EtchedBorder(),"Buscar Usuario");
        pan_medio.setBorder(borde);
        TitledBorder borde1 = new TitledBorder(new EtchedBorder(),"Formulario Usuario");
        Panel_Arriba.setBorder(borde1);
        items_ciudad= fun.funcionesLLenarComboCargo();
        for (int t = 0; t < items_ciudad.length; t++) {
            cmb_cargo_usuario.addItem(items_ciudad[t]);
        }
          Object [] items_sucursal;
        Funciones_Entrada_Inventario funciones_inventario= new Funciones_Entrada_Inventario();
        items_sucursal= funciones_inventario.llenarComboSucursal(usuario_activo.getId_sucursal());
        for (int t = 0; t < items_sucursal.length; t++) {
            cmb_sucursal.addItem(items_sucursal[t]);
        }
       
    }
    
    private void llenarTablaCategoria()
    {
        Controlador.Funciones_frm_Usuario con = new Controlador.Funciones_frm_Usuario();
        tbl_permiso.setModel(con.llenarTablaPermiso());
        JCheckBox che = new JCheckBox();
        tbl_permiso.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(che) );
        tbl_permiso.getColumnModel().getColumn(2).setCellRenderer(new Fuentes.Clase_CellRender() );
        tbl_permiso.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbl_permiso.getColumnModel().getColumn(2).setPreferredWidth(10);
    }
    
     private void parametrosTabla()
    {
        columnas[0] = "IDENTIFICACION";
        columnas[1] = "NOMBRE";
        columnas[2] = "APELLIDO";
        columnas[3] = "CC";
        columnas[4] = "TELEFONO";
        columnas[5] = "DIRECCION";
        columnas[6] = "DESCRIP.";
        columnas[7] = "CREADO";
        columnas[8] = "USUARIO";
        columnas[9] = "SUCURSAL";
        columnas[10] = "BORRAR1";
        columnas[11] = "BORRAR2";
        columnas[12] = "BORRAR3";
        columnas[13] = "BORRAR4";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        columnas_eliminar[0] = 11;
        columnas_eliminar[1] = 11;
        columnas_eliminar[2] = 11;
        columnas_eliminar[3] = 10;
        
       
    }
      private void consultarDatosMiEmpresa()
    {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa=fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }
   
     private void cargarTable() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_frm_Usuario n = new Funciones_frm_Usuario();
        consultarDatosUsuario();
        try{
            parametrosTabla();
            consultarDatosUsuario();
            consultarDatosMiEmpresa();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_usuario.setModel(n.llenarUsuario(mi_empresa.getId_empresa(),columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_usuario.getColumnModel().removeColumn(tbl_usuario.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         
    }
     
      private void btn_agregar_usuario()
    {
        consultarDatosUsuario();
        //bject[]Permisos,String nombre,String apellido,String cc, String telefono , String dir, Object id_cargo ,
//             Object descri,String fecha_cre,Object id_usuario_cre,Object id_sucursal,Object usuario, Object psw)
       String nombre = null,apellido=null,cc=null,telefono=null,dire=null,cargo=null,descrip=null,fecha=null,usuario=null,psw = null,repsw = null; //Los campos dde texto del formulario
       int id_usuario=0; 
       Object id_sucursal=null;
       //Los combobox del formulario
       psw = txt_password_usuario.getText();
       repsw = txt_re_password.getText();
           if(repsw.isEmpty())
           {
               JOptionPane.showMessageDialog(null, "Por favor diligenciar todos los campos");
                
           }
           else
           {
               if(psw.equals(repsw))
               {
                   nombre=txt_nombre_usuario.getText();
                   usuario = txt_usuario_usuario.getText();
                   cc = txt_cc_usuario.getText();
                   apellido = txt_apellido_usuario.getText();
                   telefono= txt_tel_usuario.getText();
                   cargo= cmb_cargo_usuario.getSelectedItem().toString();
                   dire= txt_direccion_usuario.getText();
                   usuario = txt_usuario_usuario.getText();
                   id_usuario = usuario_activo.getId_usuario();
                   id_sucursal = cmb_sucursal.getSelectedItem();
                   fecha = date.format(now);
                   descrip= txt_descripcion_usuario.getText();
                    Funciones_frm_Usuario clientes_funciones = new Funciones_frm_Usuario();
                    int aux=0,filas_llenas=0;
                     DefaultTableModel modelo = new DefaultTableModel();
                     for (int j = 0; j < tbl_permiso.getModel().getRowCount(); j++) 
                    {
                      modelo = (DefaultTableModel) tbl_permiso.getModel();
                        if((Boolean.valueOf(modelo.getValueAt(j, 2).toString()))==true)
                        {
                            filas_llenas++;
                        }
                    }
                    Object[]permisos = new Object[filas_llenas];
                    for (int i = 0; i < tbl_permiso.getModel().getRowCount(); i++) 
                        {
                            modelo = (DefaultTableModel) tbl_permiso.getModel();
                            if((Boolean.valueOf(modelo.getValueAt(i, 2).toString()))==true)
                            {                            
                                permisos[aux]=modelo.getValueAt(i, 0);
                                aux++;
                            }                    
                        }
                            if(clientes_funciones.insertarUsuario(permisos,nombre.toString(), apellido.toString(), cc.toString(), telefono.toString(), dire.toString(),cargo,descrip,fecha.toString(),id_usuario,id_sucursal,usuario,psw))
                            {   
                                cargarTable();
                                JOptionPane.showMessageDialog(null, "Se registro a "+nombre+" exitosamente!");
                            }
        
                }
               else
               {
                   JOptionPane.showMessageDialog(null, "LAS CONTRASEÑAS NO COINCIDEN!");
               }
           }
          
            
//bject[]Permisos,String nombre,String apellido,String cc, String telefono , String dir, Object id_cargo ,
//             Object descri,String fecha_cre,Object id_usuario_cre,Object id_sucursal,Object usuario, Object psw)
              


       
    }
      
       private void mostrarPermisosUsuario()
      {
          try{
          Object[]permisos = null;
          consultarDatosUsuario();
          Controlador.Funciones_frm_Usuario fun = new Funciones_frm_Usuario();
          permisos=fun.permisosUsuario(tbl_usuario.getValueAt(tbl_usuario.getSelectedRow(), 0));
           for (int i = 0; i < tbl_permiso.getRowCount(); i++) {
              for (int j = 0; j < permisos.length; j++) {
                if(tbl_permiso.getValueAt(i, 1).equals(permisos[j]))
                  tbl_permiso.setValueAt(true, i, 2);
              }
              
          }
          }
          catch(Exception ex)
          {
              JOptionPane.showMessageDialog(null, "SELECCIONE UN PROVEEDOR DE LA TABLA!");
          }
         
      }
      
       private void actualizarUsuario()
       {
           consultarDatosUsuario();
        //bject[]Permisos,String nombre,String apellido,String cc, String telefono , String dir, Object id_cargo ,
//             Object descri,String fecha_cre,Object id_usuario_cre,Object id_sucursal,Object usuario, Object psw)
       String nombre = null,apellido=null,cc=null,telefono=null,dire=null,cargo=null,descrip=null,fecha=null,usuario=null,psw = null,repsw = null; //Los campos dde texto del formulario
       int id_usuario=0; 
       Object id_sucursal=null;
       //Los combobox del formulario
       psw = txt_password_usuario.getText();
       repsw = txt_re_password.getText();
           if(repsw.isEmpty())
           {
               JOptionPane.showMessageDialog(null, "Por favor diligenciar todos los campos");
                
           }
           else
           {
               if(psw.equals(repsw))
               {
                   nombre=txt_nombre_usuario.getText();
                   usuario = txt_usuario_usuario.getText();
                   cc = txt_cc_usuario.getText();
                   apellido = txt_apellido_usuario.getText();
                   telefono= txt_tel_usuario.getText();
                   cargo= cmb_cargo_usuario.getSelectedItem().toString();
                   dire= txt_direccion_usuario.getText();
                   usuario = txt_usuario_usuario.getText();
                   id_usuario = usuario_activo.getId_usuario();
                   id_sucursal = cmb_sucursal.getSelectedItem();
                   fecha = date.format(now);
                   descrip= txt_descripcion_usuario.getText();
                    Funciones_frm_Usuario clientes_funciones = new Funciones_frm_Usuario();
                    int aux=0,filas_llenas=0;
                     DefaultTableModel modelo = new DefaultTableModel();
                     for (int j = 0; j < tbl_permiso.getModel().getRowCount(); j++) 
                    {
                      modelo = (DefaultTableModel) tbl_permiso.getModel();
                        if((Boolean.valueOf(modelo.getValueAt(j, 2).toString()))==true)
                        {
                            filas_llenas++;
                        }
                    }
                    Object[]permisos = new Object[filas_llenas];
                    for (int i = 0; i < tbl_permiso.getModel().getRowCount(); i++) 
                        {
                            modelo = (DefaultTableModel) tbl_permiso.getModel();
                            if((Boolean.valueOf(modelo.getValueAt(i, 2).toString()))==true)
                            {                            
                                permisos[aux]=modelo.getValueAt(i, 0);
                                aux++;
                            }                    
                        }
                            if(clientes_funciones.modificarUsuario(Integer.parseInt(tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 0).toString()),permisos,nombre.toString(), apellido.toString(), cc.toString(), telefono.toString(), dire.toString(),cargo,descrip,fecha.toString(),id_usuario,id_sucursal,usuario,psw))
                            {   
                                cargarTable();
                                JOptionPane.showMessageDialog(null, "Se actualizo a "+nombre+" exitosamente!");
                            }
        
                }
               else
               {
                   JOptionPane.showMessageDialog(null, "LAS CONTRASEÑAS NO COINCIDEN!");
               }
               
           }
       }
       
      private void limpiarCampos()
      {
          txt_tel_usuario.setText(null);
          txt_apellido_usuario.setText(null);
          txt_cc_usuario.setText(null);
          txt_descripcion_usuario.setText(null);
          txt_direccion_usuario.setText(null);
          txt_nombre_usuario.setText(null);
          txt_password_usuario.setText(null);
          txt_re_password.setText(null);
          txt_usuario_usuario.setText(null);
          cmb_cargo_usuario.setSelectedIndex(0);
          cmb_sucursal.setSelectedIndex(0);
          for (int i = 0; i < tbl_permiso.getRowCount(); i++) {
              tbl_permiso.setValueAt(false, i, 2);
          }
      }
      
      private void btn_eliminar_usuario()
    {
        consultarDatosUsuario();
        int aux= Integer.parseInt((tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 0).toString()));
        Funciones_frm_Usuario eliminar = new Funciones_frm_Usuario();
        eliminar.eliminarUsuario(aux,usuario_activo.getId_usuario());
        cargarTable();
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
        txt_nombre_usuario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_usuario_usuario = new javax.swing.JTextField();
        txt_cc_usuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_tel_usuario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmb_cargo_usuario = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_permiso = new javax.swing.JTable();
        txt_apellido_usuario = new javax.swing.JTextField();
        txt_direccion_usuario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_password_usuario = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        txt_re_password = new javax.swing.JPasswordField();
        jLabel17 = new javax.swing.JLabel();
        txt_descripcion_usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmb_sucursal = new javax.swing.JComboBox();
        btn_mostrar_permisos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_usuario = new javax.swing.JTable();
        pan_medio = new javax.swing.JPanel();
        btn_agregar_proveedor = new javax.swing.JButton();
        btn_limpiar_form_proveedor = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_actualizar_proveedor = new javax.swing.JButton();

        jLabel1.setText("Nombre:");

        jLabel8.setText("Usuario:");

        jLabel9.setText("CC:");

        jLabel11.setText("Tel:");

        jLabel12.setText("Permisos:");

        jLabel13.setText("Cargo:");

        cmb_cargo_usuario.setToolTipText("");

        tbl_permiso.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbl_permiso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_permiso.setShowHorizontalLines(false);
        jScrollPane2.setViewportView(tbl_permiso);

        jLabel10.setText("Apellido:");

        jLabel14.setText("Direccion:");

        jLabel15.setText("Pass:");

        jLabel16.setText("Re-Pass:");

        jLabel17.setText("Descripcion:");

        jLabel2.setText("Sucursal:");

        btn_mostrar_permisos.setText("Mostrar Permisos");
        btn_mostrar_permisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostrar_permisosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_ArribaLayout = new javax.swing.GroupLayout(Panel_Arriba);
        Panel_Arriba.setLayout(Panel_ArribaLayout);
        Panel_ArribaLayout.setHorizontalGroup(
            Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ArribaLayout.createSequentialGroup()
                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ArribaLayout.createSequentialGroup()
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Panel_ArribaLayout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel11))
                                .addGroup(Panel_ArribaLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(jLabel8))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_tel_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(txt_usuario_usuario)
                            .addComponent(cmb_sucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ArribaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ArribaLayout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(Panel_ArribaLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(11, 11, 11)))
                            .addGroup(Panel_ArribaLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(4, 4, 4)))
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_password_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_direccion_usuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_descripcion_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_apellido_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_ArribaLayout.createSequentialGroup()
                                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_cc_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmb_cargo_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12))
                            .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn_mostrar_permisos, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_re_password, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_ArribaLayout.createSequentialGroup()
                                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_cc_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_apellido_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_cargo_usuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(txt_tel_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13))))
                            .addGroup(Panel_ArribaLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txt_direccion_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_usuario_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txt_password_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(txt_re_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_ArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_descripcion_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel2)
                            .addComponent(cmb_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_mostrar_permisos))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(Panel_ArribaLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        tbl_usuario.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_usuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_usuario);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_agregar_proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634362_add.png"))); // NOI18N
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
                .addContainerGap()
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_actualizar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_limpiar_form_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_agregar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pan_medioLayout.setVerticalGroup(
            pan_medioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_medioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_medioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_agregar_proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_limpiar_form_proveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_actualizar_proveedor, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pan_medio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Panel_Arriba, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Panel_Arriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pan_medio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_proveedorActionPerformed
        // TODO add your handling code here:
        btn_agregar_usuario();
        cargarTable();
    }//GEN-LAST:event_btn_agregar_proveedorActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        btn_eliminar_usuario();
        cargarTable();
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void tbl_usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_usuarioMouseClicked
        // TODO add your handling code here:
        
        txt_nombre_usuario.setText(tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 1).toString());
        txt_apellido_usuario.setText(tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 2).toString());
        txt_cc_usuario.setText(tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 3).toString());
        txt_tel_usuario.setText(tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 4).toString());
        txt_direccion_usuario.setText(tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 5).toString());
        txt_descripcion_usuario.setText(tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 6).toString());
        txt_usuario_usuario.setText(tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 8).toString());
        cmb_sucursal.setSelectedItem(tbl_usuario.getModel().getValueAt(tbl_usuario.getSelectedRow(), 9).toString());
        
    }//GEN-LAST:event_tbl_usuarioMouseClicked

    private void btn_limpiar_form_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiar_form_proveedorActionPerformed
        // TODO add your handling code here:
        
        limpiarCampos();
    }//GEN-LAST:event_btn_limpiar_form_proveedorActionPerformed

    private void btn_mostrar_permisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrar_permisosActionPerformed
        // TODO add your handling code here:
        
        mostrarPermisosUsuario();
    }//GEN-LAST:event_btn_mostrar_permisosActionPerformed

    private void btn_actualizar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_proveedorActionPerformed
        // TODO add your handling code here:
        actualizarUsuario();
        cargarTable();
    }//GEN-LAST:event_btn_actualizar_proveedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Arriba;
    private javax.swing.JButton btn_actualizar_proveedor;
    private javax.swing.JButton btn_agregar_proveedor;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_limpiar_form_proveedor;
    private javax.swing.JButton btn_mostrar_permisos;
    private javax.swing.JComboBox cmb_cargo_usuario;
    private javax.swing.JComboBox cmb_sucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pan_medio;
    private javax.swing.JTable tbl_permiso;
    private javax.swing.JTable tbl_usuario;
    private javax.swing.JTextField txt_apellido_usuario;
    private javax.swing.JTextField txt_cc_usuario;
    private javax.swing.JTextField txt_descripcion_usuario;
    private javax.swing.JTextField txt_direccion_usuario;
    private javax.swing.JTextField txt_nombre_usuario;
    private javax.swing.JPasswordField txt_password_usuario;
    private javax.swing.JPasswordField txt_re_password;
    private javax.swing.JTextField txt_tel_usuario;
    private javax.swing.JTextField txt_usuario_usuario;
    // End of variables declaration//GEN-END:variables
}
