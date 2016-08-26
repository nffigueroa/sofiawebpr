/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_MiSucursal;
import Controlador.Funciones_frm_Proveedores;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Nestor1
 */
public final class frm_Mi_Sucursal extends javax.swing.JInternalFrame {
    public String []columnas = new String[9],columnas_articulos = new String[9];
        public int[]ancho_columnas = new int[15],columnas_eliminar = new int[6],columnas_eliminar_articulos = new int[13];
        java.util.Date now = new Date(System.currentTimeMillis());
        String fecha= new String(),fecha2= new String();
        Constructor_Usuario usuario_activo= new Constructor_Usuario();
         private SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
        private String user= null;
        SimpleDateFormat formato_date = new SimpleDateFormat();
        Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
    /**
     * Creates new form frm_Mi_Sucursal
     */
    public frm_Mi_Sucursal(Object usuario) {
        user=usuario.toString();
        initComponents();
        inicializarForm();
        cargarTable();
        this.setTitle("MI SUCURSAL");
    }
    
    public void inicializarForm()
    {
        Funciones_frm_MiSucursal fun = new Funciones_frm_MiSucursal();
         Object[]items_ciudad = null;
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
         TitledBorder borde2 = new TitledBorder(new EtchedBorder(),mi_empresa.getEmpresa()+" Mis Sucursales");
        pan_arriba.setBorder(borde2);
        items_ciudad= fun.llenarComboCiudad();
        for (int t = 0; t < items_ciudad.length; t++) {
            cmb_ciudad_sucursal.addItem(items_ciudad[t]);
        }
    }
    private void consultarDatosMiEmpresa()
    {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa=fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }
     private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
    }
     
     private void parametrosTabla()
    {
        columnas[0] = "IDENTIFICACION";
        columnas[1] = "EMPRESA";
        columnas[2] = "GERENTE";
        columnas[3] = "TELEFONO";
        columnas[4] = "DIRECCION";
        columnas[5] = "CIUDAD";
        columnas[6] = "CANT EMPLEADOS";
        columnas[7] = "FECHA CREACION";
        columnas[8] = "USUARIO";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        columnas_eliminar[0] = 10;
        columnas_eliminar[1] = 10;
        columnas_eliminar[2] = 10;
        columnas_eliminar[3] = 10;
        columnas_eliminar[4] = 10;
        columnas_eliminar[5] = 9;
       
    }
    
    
     private void cargarTable() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Controlador.Funciones_frm_MiSucursal n = new Funciones_frm_MiSucursal();
        
        try{
            consultarDatosMiEmpresa();
            parametrosTabla();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_sucursal.setModel(n.llenarMisSucursales(mi_empresa.getId_empresa(),columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_sucursal.getColumnModel().removeColumn(tbl_sucursal.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         
    }
     
     private void btn_agregar_Sucursal()
     {
         consultarDatosMiEmpresa();
         Funciones_frm_MiSucursal fun = new Funciones_frm_MiSucursal();
         if(fun.registarSucursal(txt_nombre_sucursal.getText(), txt_telefono_sucursal.getText(), txt_direccion_sucursal.getText(),
                 cmb_ciudad_sucursal.getSelectedItem(), spin_cant_empleados.getValue(), mi_empresa.getId_empresa(),usuario_activo.getId_usuario()))
         {
             JOptionPane.showMessageDialog(null, "SE REGISTRO "+txt_nombre_sucursal.getText().toString()+" EXITOSAMENTE");
         }
         else
         {
             JOptionPane.showMessageDialog(null, "UPS! Algo ocurrio , intente de nuevo","",0);
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

        pan_arriba = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_direccion_sucursal = new javax.swing.JTextField();
        txt_telefono_sucursal = new javax.swing.JTextField();
        txt_nombre_sucursal = new javax.swing.JTextField();
        cmb_ciudad_sucursal = new javax.swing.JComboBox();
        spin_cant_empleados = new javax.swing.JSpinner();
        pan_abajo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sucursal = new javax.swing.JTable();
        cmb_agregar_producto = new javax.swing.JButton();

        jLabel1.setText("Nombre Sucursal:");

        jLabel2.setText("Telefono :");

        jLabel3.setText("Direccion:");

        jLabel4.setText("Ciudad:");

        jLabel5.setText("Cant. Empleados:");

        cmb_ciudad_sucursal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "..." }));

        javax.swing.GroupLayout pan_arribaLayout = new javax.swing.GroupLayout(pan_arriba);
        pan_arriba.setLayout(pan_arribaLayout);
        pan_arribaLayout.setHorizontalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_arribaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nombre_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_telefono_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_direccion_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_ciudad_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spin_cant_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pan_arribaLayout.setVerticalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_arribaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txt_nombre_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_telefono_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_direccion_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_ciudad_sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(spin_cant_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pan_abajo.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        tbl_sucursal.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_sucursal);

        cmb_agregar_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634362_add.png"))); // NOI18N
        cmb_agregar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_agregar_productoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_abajoLayout = new javax.swing.GroupLayout(pan_abajo);
        pan_abajo.setLayout(pan_abajoLayout);
        pan_abajoLayout.setHorizontalGroup(
            pan_abajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_abajoLayout.createSequentialGroup()
                .addGroup(pan_abajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pan_abajoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmb_agregar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pan_abajoLayout.setVerticalGroup(
            pan_abajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_abajoLayout.createSequentialGroup()
                .addComponent(cmb_agregar_producto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pan_abajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan_arriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan_arriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_abajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_agregar_productoActionPerformed
        // TODO add your handling code here:
        btn_agregar_Sucursal();
        cargarTable();
    }//GEN-LAST:event_cmb_agregar_productoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmb_agregar_producto;
    private javax.swing.JComboBox cmb_ciudad_sucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pan_abajo;
    private javax.swing.JPanel pan_arriba;
    private javax.swing.JSpinner spin_cant_empleados;
    private javax.swing.JTable tbl_sucursal;
    private javax.swing.JTextField txt_direccion_sucursal;
    private javax.swing.JTextField txt_nombre_sucursal;
    private javax.swing.JTextField txt_telefono_sucursal;
    // End of variables declaration//GEN-END:variables
}
