/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Controlador.Funciones_Generales;
import Controlador.Funciones_frm_CortesCaja;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Nestor1
 */
public class frm_Control_Sesion extends javax.swing.JInternalFrame {
 public String []columnas_inventario = new String[30];
    public String []columnas = new String[6];
    public int[]ancho_columnas = new int[15],columnas_eliminar = new int[2], columnas_eliminar_inventario =new int[17];
    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
        private String user= null;
        Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
    /**
     * Creates new form frm_HistorialInforme
     */
    public frm_Control_Sesion(Object usuario) {
        user=usuario.toString();
        initComponents();
        cargarTable();
        this.setTitle("HISTORIAL USUARIO");
    }
     private void parametrosTabla()
    {
        columnas[0] = "USUARIO";
        columnas[1] = "SUCURSAL";
        columnas[2] = "FECHA INICIO";
        columnas[3] = "HORA INICIO";
        columnas[4] = "FECHA FIN";
        columnas[5] = "HORA FIN";
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
        columnas_eliminar[0] = 6;
        columnas_eliminar[1] = 6;
    }
    private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
        
    }
    
     private void consultarDatosMiEmpresa()
    {
        Funciones_Generales fun = new Funciones_Generales();
        mi_empresa=fun.datosMiEmpresa(usuario_activo.getId_sucursal());
    }
    private void cargarTable() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Controlador.Funciones_frm_controlSesion n = new Controlador.Funciones_frm_controlSesion();
        
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
        tbl_historial.setModel(n.llenarTablaSesion(mi_empresa.getId_empresa(),columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
        for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_historial.getColumnModel().removeColumn(tbl_historial.getColumnModel().getColumn(columnas_eliminar[i]));
        }
    }
         
    private void cargarTableBuscar() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Controlador.Funciones_frm_controlSesion n = new Controlador.Funciones_frm_controlSesion();
        
        try{
            parametrosTabla();
            consultarDatosUsuario();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
            if(cmb_buscar_por_historial.getSelectedIndex()==0)
            {cargarTable();}
            else
            {
                tbl_historial.setModel(n.llenarTablaSesionBuscar(mi_empresa.getId_empresa(),columnas,ancho_columnas,cmb_buscar_por_historial.getSelectedItem().toString(), txt_buscar_historial.getText().toString()));
            }
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
         for (int i = 0; i < columnas_eliminar.length; i++) {

            tbl_historial.getColumnModel().removeColumn(tbl_historial.getColumnModel().getColumn(columnas_eliminar[i]));
        }
         
    }
    
    private void exporatPdf()
    {
        Fuentes.Reportes  reportePdf= new Fuentes.Reportes();//CREAMOS UN OBJETO DE LA CLASE REPORTES
        String ruta = "src\\Reportes\\reporteSesion.jasper";//RUTA DONDE TIENEN SU REPORTE --
        int id_empresa = 0;
        consultarDatosUsuario();
        consultarDatosMiEmpresa();
        id_empresa = mi_empresa.getId_empresa();
        //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.PDF", "pdf", "PDF"));//filtro para ver solo archivos .pdf
        int seleccion = fileChooser.showSaveDialog(null);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos la direccion del archivo + el nombre a guardar
                try (PrintWriter printwriter = new PrintWriter(JFC)) {
                    printwriter.print(ruta);
                }
                reportePdf.resportesPDF(ruta, PATH,id_empresa);//mandamos como parametros la ruta del archivo a compilar y el nombre y ruta donde se guardaran    
                //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                if (!(PATH.endsWith(".pdf"))) {
                    File temp = new File(PATH + ".pdf");
                    JFC.renameTo(temp);//renombramos el archivo
                }
//                JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos,espere porfavor", "Estamos Generando el Reporte", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null, "Documento Exportado Exitosamente!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (FileNotFoundException | HeadlessException e) {//por alguna excepcion salta un mensaje de error
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al Exportar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar_historial = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_historial = new javax.swing.JTable();
        cmb_buscar_por_historial = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historial Procesos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Buscar:");

        txt_buscar_historial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_historialKeyPressed(evt);
            }
        });

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
        jScrollPane1.setViewportView(tbl_historial);

        cmb_buscar_por_historial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "Usuario", "Sucursal", "Fecha Inicio", "Hora Inicio", "Fecha Fin", "Hora Fin" }));
        cmb_buscar_por_historial.setToolTipText("");

        jLabel2.setText("Por:");

        jButton1.setText("PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar_historial, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_buscar_por_historial, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_buscar_historial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_buscar_por_historial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscar_historialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_historialKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        cargarTableBuscar();
    }//GEN-LAST:event_txt_buscar_historialKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        exporatPdf();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_buscar_por_historial;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_historial;
    private javax.swing.JTextField txt_buscar_historial;
    // End of variables declaration//GEN-END:variables
}
