/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Mi_empresa;
import Constructores.Constructor_Usuario;
import Constructores.Contructor_Cliente_Seleccionado;
import java.text.SimpleDateFormat;
import Controlador.Funciones_Entrada_Inventario;
import Controlador.Funciones_Generales;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import Controlador.Funciones_frm_factura;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Nestor1
 */
public class frm_Factura extends javax.swing.JInternalFrame {
    private int filas = 0,con=0;
    public String []columnas_inventario = new String[30];
    public String []columnas = new String[14];
    public int[]ancho_columnas = new int[15],columnas_eliminar = new int[8], columnas_eliminar_inventario =new int[17];
    DecimalFormat formateador = new DecimalFormat("###,###");
    DecimalFormat formateador2 = new DecimalFormat("###.###");
    private final Object [] items_tabla = new Object[8];
    private int descuento_total=0,subtotalTotal=0,ivaTotal=0,totalTotal=0;
    private float totales = 0,cantidad_producto=0,pagarCredito=0;
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
    private String user= null;
    Date now = new Date(System.currentTimeMillis());
    private final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Mi_empresa mi_empresa= new Constructor_Mi_empresa();
    Contructor_Cliente_Seleccionado clientesel = new Contructor_Cliente_Seleccionado();

    /**
     * Creates new form frm_Producto
     * @param usuario
     */
    public frm_Factura(Object usuario) {
        
        initComponents();
        pan_arriba.requestFocus();
        pan_buscar.requestFocus();
        txt_buscar_producto.requestFocus();
        txt_buscar_producto.selectAll();
        user=usuario.toString();
        inicializarForm();
        
        llenar_combo_pagar_con();
        this.setTitle("FACTURA");
        
    }
    private void consultarDatosUsuario()
    {
        Controlador.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
    }
    private void inicializarForm()
    {
        radio_no_imprimir.setSelected(true);
        ultimaFacturaNumero();
        txt_usuario_vendedor.setText(user);
        txt_digitos_tarjeta_credito_factura.setEnabled(false);
        TitledBorder borde2 = new TitledBorder(new EtchedBorder(),"Total");
        pan_totales.setBorder(borde2);
         TitledBorder borde1 = new TitledBorder(new EtchedBorder(),"Factura");
        pan_arriba.setBorder(borde1);
        TitledBorder borde = new TitledBorder(new EtchedBorder(),"Generar Factura");
        panel_jdialog_factura.setBorder(borde);
        cargarTablaInventario();
        txt_total.setEnabled(false);
        txt_fecha_factura.setText(String.valueOf(date.format(now)));
        TableColumn columna = tbl_factura.getColumnModel().getColumn(6);
        columna.setMaxWidth(0);
        columna.setMinWidth(0);
        columna.setPreferredWidth(0);
        tbl_factura.doLayout();
        TableColumn columna1 = tbl_factura.getColumnModel().getColumn(12);
        columna1.setMaxWidth(0);
        columna1.setMinWidth(0);
        columna1.setPreferredWidth(0);
        tbl_factura.doLayout();
        txt_iva_cantidad.setEnabled(false);
        txt_id_cliente.setVisible(false);
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
        columnas_inventario[15] = "id_producto";
        columnas_inventario[16] = "nombre_producto";
        columnas_inventario[17] = "id_categoria";
        columnas_inventario[18] = "id_presentacion";
        columnas_inventario[19] = "id_marca";
        columnas_inventario[20] = "id_categoria";
        columnas_inventario[21] = "CATEGORIA";
        columnas_inventario[22] = "id_medicion";
        columnas_inventario[23] = "MEDICION";
        columnas_inventario[24] = "ID_PREENTACION";
        columnas_inventario[25] = "PRESENTACION";
        columnas_inventario[26] = "ELIMINALIMI1R1";
        columnas_inventario[27] = "ELISI";
        columnas_inventario[28] = "ELIMINARA";
        columnas_inventario[29] = "nueba";
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
        columnas_eliminar_inventario[0] = 15;
        columnas_eliminar_inventario[1] = 15;
        columnas_eliminar_inventario[2] = 15;
        columnas_eliminar_inventario[3] = 15;
        columnas_eliminar_inventario[4] = 15;
        columnas_eliminar_inventario[5] = 15;
        columnas_eliminar_inventario[6] = 15;     
        columnas_eliminar_inventario[7] = 15;     
        columnas_eliminar_inventario[8] = 15;     
        columnas_eliminar_inventario[9] = 15;     
        columnas_eliminar_inventario[10] = 15;     
        columnas_eliminar_inventario[11] = 15;     
        columnas_eliminar_inventario[12] = 15;     
        columnas_eliminar_inventario[13] = 15;     
        columnas_eliminar_inventario[14] = 15;        
        columnas_eliminar_inventario[15] = 15;        
        columnas_eliminar_inventario[16] = 15;        
        //columnas_eliminar_inventario[17] = 15;        
    }
    
    private void cargarTablaInventario()
    {
           //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        Funciones_Entrada_Inventario n = new Funciones_Entrada_Inventario();
        
        try{
            consultarDatosUsuario();
            parametrosTablaInventario();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }  
        try{
        tbl_inventario.setModel(n.llenarTablaInventarioFunciones(usuario_activo.getId_sucursal(),columnas_inventario, ancho_columnas));
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
         for (int i = 0; i < columnas_eliminar_inventario.length; i++) {
            tbl_inventario.getColumnModel().removeColumn(tbl_inventario.getColumnModel().getColumn(columnas_eliminar_inventario[i]));
        }
    }
    
    private void evento_txt_tecla_presionada_buscar_producto_inventario()
    {
        String comboBox = " ";
        comboBox= cmb_buscar_producto_por.getSelectedItem().toString();
        Funciones_Entrada_Inventario n = new Funciones_Entrada_Inventario();        
        parametrosTablaInventario();
        consultarDatosUsuario();
        try{
        tbl_inventario.setModel(n.buscarProductoInventario(usuario_activo.getId_sucursal(),txt_buscar_producto.getText(),comboBox,columnas_inventario, ancho_columnas));
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
         for (int i = 0; i < columnas_eliminar_inventario.length; i++) {

            tbl_inventario.getColumnModel().removeColumn(tbl_inventario.getColumnModel().getColumn(columnas_eliminar_inventario[i]));
        }
         //tbl_inventario.setRowSelectionInterval(0, 0);
    }
    
   
    
    private void llenarFactura()
    {
        float precio = 0 , subtotal = 0,iva=0,precio_sin_iva=0,precio_iva=0;
        Float cantidad;
        for (int i = 0; i < items_tabla.length; i++) {
            tbl_factura.getModel().setValueAt(tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), (int) items_tabla[i]), filas, i+1);
            if(i==6)
            {
                tbl_factura.getModel().setValueAt(1, filas, i+1);
            }
            if(i==0)
            {
                tbl_factura.getModel().setValueAt(con+1, filas, i);
            }
            
            if(i==7)
            {
                if(cantidad_producto==0)
                    cantidad_producto=1;
                tbl_factura.getModel().setValueAt(cantidad_producto, filas, 7);
                iva= Float.parseFloat(tbl_factura.getModel().getValueAt(filas, 6).toString());
                cantidad = Float.parseFloat(tbl_factura.getModel().getValueAt(filas, 7).toString());
                precio = Integer.parseInt((tbl_factura.getModel().getValueAt(filas, 8).toString()));
                precio_iva = precio;
                precio_sin_iva= precio * (iva/100);
                precio = precio + precio_sin_iva;
                subtotal= cantidad * precio;                
                tbl_factura.getModel().setValueAt(((Math.round(precio))), filas, 8);
                tbl_factura.getModel().setValueAt(((Math.round(precio_iva))), filas, 12);
            }
            tbl_factura.getModel().setValueAt(0.0, filas, 9);
            tbl_factura.getModel().setValueAt(((Math.round(precio))), filas, 10);
            tbl_factura.getModel().setValueAt(((Math.round(subtotal))), filas, 11);  
        }
        calcularIva();
        calcularSubTotal();
    }
    
    private float calcularTotal()
    {
        float total=0;
        Object totalito;
        for (int j = 0; j < filas+1; j++) {
                total = Math.round(total + Float.parseFloat(tbl_factura.getModel().getValueAt(j, 11).toString()));
                totalito = total;
                totalito = formateador.format(totalito);
                txt_total.setText(String.valueOf(totalito));
                totales = total;
                totalTotal= (int) total;
            }
        return total;
    }
    
    private float calcularSubTotal()
    {
        float total=0,cantidad =0,acumulado_sinIva=0;
        Object totalito;
        //acumulado_sinIva=Float.parseFloat(txt_subtotal.getText().toString());
        for (int j = 0; j < filas+1; j++) {
                total =Math.round(Float.parseFloat(tbl_factura.getModel().getValueAt(j, 12).toString()));
                cantidad = Math.round(Float.parseFloat(tbl_factura.getModel().getValueAt(j, 7).toString()));
                acumulado_sinIva = Math.round(acumulado_sinIva + total * cantidad);
                totalito = acumulado_sinIva;
                totalito = formateador.format(totalito);
                txt_subtotal.setText(String.valueOf(totalito));
                subtotalTotal= (int) acumulado_sinIva;
            }
        return total;
    }
     private float calcularDescuento()
    {
        float unidad_descuento=0,precio=0,cantidad=0,desc=0;
        Object totalito = null;
        
        for (int j = 0; j < filas+1; j++) {

                unidad_descuento =  Math.round(unidad_descuento+ Float.parseFloat(tbl_factura.getModel().getValueAt(j, 10).toString()));
                precio = Math.round(precio+ Float.parseFloat(tbl_factura.getModel().getValueAt(j, 8).toString()));
                desc=Math.round(Float.parseFloat(tbl_factura.getModel().getValueAt(j, 9).toString()));
                if(desc!= 0)
                {
                    cantidad =Float.parseFloat(tbl_factura.getModel().getValueAt(j, 7).toString());
                    totalito = Math.round(((precio -unidad_descuento) * cantidad)) ;
                    descuento_total = (int) totalito;
                    totalito = formateador.format(totalito);
                    txt_descuento_cantidad.setText(String.valueOf(totalito));
                }
            }
        return unidad_descuento;
    }
    
      private float calcularIva()
    {
        float iva= 0,precio=0,acumulado_impuesto=0,precio_sin_iva=0,cantidad=0;
        Object totalito ;
        acumulado_impuesto=0;
        for (int j = 0; j < filas+1; j++) {               
                precio_sin_iva=0;
                precio=0;
                precio_sin_iva = Math.round(Float.parseFloat(tbl_factura.getModel().getValueAt(j, 12).toString()));
                cantidad = Math.round(Float.parseFloat(tbl_factura.getModel().getValueAt(j, 7).toString()));
                precio=  Math.round(Float.parseFloat(tbl_factura.getModel().getValueAt(j, 8).toString()));
                acumulado_impuesto= (acumulado_impuesto + ((precio*cantidad) - (precio_sin_iva*cantidad)));
                totalito = acumulado_impuesto;
                totalito = formateador.format(totalito);
                txt_iva_cantidad.setText(String.valueOf(totalito));
                ivaTotal = (int) acumulado_impuesto;
                
            }
        return iva;
    }
     
    private void evento_txt_tecla_presionada_inventario()
    {
        items_tabla[0]=0;
        items_tabla[1]=1;
        items_tabla[2]=3;
        items_tabla[3]=5;
        items_tabla[4]=7;
        items_tabla[5]=2;
        items_tabla[6]=8;
        items_tabla[7]=11;
        for (int i = 0; i < 30; i++) {
            if(tbl_factura.getModel().getValueAt(i, 0)== null)
            {
                con= filas=i;
                llenarFactura();
                i=30;
            }
            calcularTotal();
            calcularSubTotal();
            calcularIva();
        } 
    }
    
    private void calculuarDescuento(Double cal,int row , int col)
    {
        float precio = 0 , subtotal = 0,subtotalVacio=0,descuento=0,valor_descuento=0,valor_unidad_descuento=0;
        Float cantidad;
        if(col == 9)//Si es descuento...
        {
            cantidad = Float.parseFloat(tbl_factura.getModel().getValueAt(row, 7).toString());
            precio = Float.parseFloat((tbl_factura.getModel().getValueAt(row, 8).toString()));
            subtotal= cantidad * precio;
            tbl_factura.getModel().setValueAt(subtotal, row, 11);
            subtotalVacio = Float.parseFloat(tbl_factura.getModel().getValueAt(row, 11).toString());
            descuento = Float.parseFloat(tbl_factura.getModel().getValueAt(row, 9).toString());
            valor_descuento= subtotalVacio * (descuento/100);
            subtotalVacio = subtotalVacio - valor_descuento;
            valor_unidad_descuento = precio-(precio * descuento/100);
            tbl_factura.getModel().setValueAt(valor_unidad_descuento, row, 10);
            tbl_factura.getModel().setValueAt(subtotalVacio, row, 11);
            calcularTotal();
            calcularSubTotal();
            calcularDescuento();
            calcularIva();
        }
        else
        {
            if(col==7)//Si es cantidad...
            {
                cantidad = Float.parseFloat(tbl_factura.getModel().getValueAt(row, 7).toString());
                precio = Float.parseFloat((tbl_factura.getModel().getValueAt(row, 8).toString()));
                subtotal= cantidad * precio;
                tbl_factura.getModel().setValueAt(subtotal, row, 11);
                calcularTotal();
                calcularSubTotal();
                calcularIva();
                calcularDescuento();
            }
        }        
    }
    
    private void recalcularItem()
    {
        for (int i = 0; i < filas+1; i++) {
            tbl_factura.setValueAt(i+1, i, 0);
        }
    }
    
    private void eliminarFilaFactura()
    {
        if(JOptionPane.showConfirmDialog(null, "¿Desea eliminar esta fila?")==0){
            filas = filas -1;
            DefaultTableModel tb=(DefaultTableModel) tbl_factura.getModel(); 
            tb.removeRow(tbl_factura.getSelectedRow());
            calcularIva();
            calcularSubTotal();
            calcularTotal();
            recalcularItem();
        }
    }
    
    private boolean buscarCliente()
    {
        try{
        Funciones_Generales func= new Funciones_Generales();
        clientesel =func.llenarConstructorCliente(txt_codigo_cliente.getText());
        txt_cliente.setText(clientesel.getNombre().toString());
        txt_direccion_cliente.setText(clientesel.getDireccion().toString());
        txt_id_cliente.setText(clientesel.getIdUsuario().toString());
        }
        catch(Exception ex)
        {
        return false;    
        }
        return true;
    }
    
    private void btn_accionar(String opcion)
    {
        switch(opcion)
        {
            case "Facturar":
                txt_total_factura.setText(txt_total.getText());
                txt_cliente_factura.setText(txt_cliente.getText());
                dlg_facturar.setLocationRelativeTo(null);
                dlg_facturar.setVisible(true);
                dlg_facturar.setTitle("Facturar");
                break;
            case "Cotizar":
                txt_total_cotizacion.setText(txt_total.getText());
                txt_cliente_cotizacion.setText(txt_cliente.getText());
                dlg_cotizaciones.setLocationRelativeTo(null);
                dlg_cotizaciones.setVisible(true);
                dlg_cotizaciones.setTitle("Cotizaciones");
                break;
            case "Creditar":
                Calendar cal = Calendar.getInstance();
//                cal.setTime(now);
                cal.add(Calendar.HOUR, 720);
//                dch_fecha_credito.setCalendar(cal);
                txt_total_credito.setText(txt_total.getText());
                txt_cliente_factura.setText(txt_cliente.getText());
                dlg_credito.setLocationRelativeTo(null);
                dlg_credito.setVisible(true);
                dlg_credito.setTitle("Credito");
                    break;
        }
    }
     private void llenar_combo_pagar_con() 
    {
        txt_id_cliente.setVisible(false);
         Object [] items_formas_pago;
        Funciones_frm_factura funci= new Funciones_frm_factura();
        
        items_formas_pago= funci.llenarComboPagarCon();
        for (int t = 0; t < items_formas_pago.length; t++) {
            cmb_paga_con_factura.addItem(items_formas_pago[t]);
        }
       
    }
      private void evento_cambio_combobox()
      {
          if(cmb_paga_con_factura.getSelectedItem().equals("TARJETA CREDITO"))
          {
              txt_digitos_tarjeta_credito_factura.setEnabled(true);
          }
          else
              {
                  txt_digitos_tarjeta_credito_factura.setText("");
                  txt_digitos_tarjeta_credito_factura.setEnabled(false);
              }
              
          
      }
      private void operacionCambio()
      {
          float recibe=0;
          Float cambio;   
          Object aux2 ;
          recibe = (Float.parseFloat(txt_recibe_factura.getText()));
          cambio = recibe - totales;
          aux2= cambio;
          aux2=formateador.format(aux2);
          txt_cambio_factura.setText(aux2.toString());
      }
     
      private void registrarFactura()
      {
        Funciones_frm_factura fun = new Funciones_frm_factura();
        Funciones_Generales gen = new Funciones_Generales();
        float utilidad =0;
        float descuento_cantidad=0,cantidad=0;
        Object id_producto_inventario= null,item=null,cantidad_factura=null,descuento=null,valorUnidad=null,subtotal=null,tarjeta_cfredi=null;
        tarjeta_cfredi = txt_digitos_tarjeta_credito_factura.getText();
        consultarDatosUsuario();
        if(fun.funccionesRegistrarFactura(usuario_activo.getId_usuario(),usuario_activo.getId_sucursal(),txt_folio_factura.getText(),subtotalTotal, descuento_total , ivaTotal, totalTotal, txt_codigo_cliente.getText(), cmb_paga_con_factura.getSelectedItem(), txt_recibe_factura.getText(), txt_cambio_factura.getText(),Integer.parseInt(txt_numero_factura.getText().toString()),tarjeta_cfredi))
        {
            float utilidad_total=0;
          for (int j = 0; j < filas+1; j++) 
          {    
                id_producto_inventario= tbl_factura.getValueAt(j, 1);
                item= tbl_factura.getValueAt(j, 0);
                cantidad_factura= tbl_factura.getValueAt(j, 7);
                descuento= tbl_factura.getValueAt(j, 9);
                valorUnidad= tbl_factura.getValueAt(j, 10);
                subtotal= tbl_factura.getValueAt(j, 11); 
                
                if(fun.funccionesRegistrarFacturaDetaalle(id_producto_inventario, item, cantidad_factura, descuento, valorUnidad, subtotal,usuario_activo.getId_sucursal(),false)){
                    
                    float cantidad_calculo =Float.parseFloat(cantidad_factura.toString());
                    utilidad=gen.funcionGananciaProducto(Integer.parseInt(id_producto_inventario.toString()));
                    utilidad_total = utilidad_total + (utilidad*cantidad_calculo);
                    cantidad = fun.existenciaProductoInventario(id_producto_inventario);
                    descuento_cantidad = cantidad - Float.parseFloat(cantidad_factura.toString());
                    fun.descontarCantidadProducto(id_producto_inventario, descuento_cantidad);
                    cantidad=0;
                    cantidad_calculo=0;
                }
          }
          fun.funcionRegistrarUtilidad(utilidad_total, usuario_activo.getId_sucursal());
        }
          
      }
      
      
      private void registrarCotizacion()
      {
        Funciones_frm_factura fun = new Funciones_frm_factura();
        Object id_producto_inventario= null,item=null,cantidad_factura=null,descuento=null,valorUnidad=null,subtotal=null,tarjeta_cfredi=null;
        tarjeta_cfredi = txt_digitos_tarjeta_credito_factura.getText();
        consultarDatosUsuario();
        if(fun.funccionesRegistrarCotizacion(usuario_activo.getId_usuario(), usuario_activo.getId_sucursal(), null,
               subtotalTotal, descuento_total , ivaTotal, totalTotal, txt_codigo_cliente.getText(), txtAre_descripcion_factura1.getText(),date.format(jdc_fecha_validez.getDate())))
        {
          for (int j = 0; j < filas+1; j++) 
          {    
                id_producto_inventario= tbl_factura.getValueAt(j, 1);
                item= tbl_factura.getValueAt(j, 0);
                cantidad_factura= tbl_factura.getValueAt(j, 7);
                descuento= tbl_factura.getValueAt(j, 9);
                valorUnidad= tbl_factura.getValueAt(j, 10);
                subtotal= tbl_factura.getValueAt(j, 11); 
                try{
                if(fun.funcionesRegistrarCotizacionDetalle(id_producto_inventario, item, cantidad_factura, descuento, valorUnidad, subtotal,usuario_activo.getId_sucursal())){
                     Funciones_Generales funG = new Funciones_Generales();
                     funG.imprimirCotizacion(usuario_activo.getId_sucursal(),date.format(jdc_fecha_validez.getDate()),usuario_activo.getId_usuario() );
                }
                }
                catch(Exception e)
                {e.printStackTrace();
                }
          }
        }
          
      }
      
      
      private void registrarCredito()
      {
        Funciones_frm_factura fun = new Funciones_frm_factura();
        float descuento_cantidad=0,cantidad=0;
        Object id_producto_inventario= null,item=null,cantidad_factura=null,descuento=null,valorUnidad=null,subtotal=null,tarjeta_cfredi=null;
        tarjeta_cfredi = txt_digitos_tarjeta_credito_factura.getText();
        consultarDatosUsuario();
        if(fun.funccionesRegistrarCredito(usuario_activo.getId_usuario(),usuario_activo.getId_sucursal(),pagarCredito,
                subtotalTotal, descuento_total , ivaTotal, totalTotal, txt_codigo_cliente.getText(),txt_cuotas_credito.getText(), 
                txt_porcentaje_interes_credito.getText(),0,0))
        {
          for (int j = 0; j < filas+1; j++) 
          {    
                id_producto_inventario= tbl_factura.getValueAt(j, 1);
                item= tbl_factura.getValueAt(j, 0);
                cantidad_factura= tbl_factura.getValueAt(j, 7);
                descuento= tbl_factura.getValueAt(j, 9);
                valorUnidad= tbl_factura.getValueAt(j, 10);
                subtotal= tbl_factura.getValueAt(j, 11); 
                
                if(fun.funccionesRegistrarFacturaDetaalle(id_producto_inventario, item, cantidad_factura, descuento, valorUnidad, subtotal,usuario_activo.getId_sucursal(),true)){
                    cantidad = fun.existenciaProductoInventario(id_producto_inventario);
                    descuento_cantidad = cantidad - Float.parseFloat(cantidad_factura.toString());
                    fun.descontarCantidadProducto(id_producto_inventario, descuento_cantidad);
                    cantidad=0;
                    
                }
          }
          fun.funcionGenerarHistorial(dch_fecha_credito.getDate(),Integer.parseInt(txt_cuotas_credito.getText().toString()),usuario_activo.getId_sucursal());
        }
          
      }
      private void funcionImprimirTirilla()
      {
          consultarDatosUsuario();
          Funciones_Generales fun = new Funciones_Generales();
          fun.imprimirTirilla(usuario_activo.getId_sucursal(),0,usuario_activo.getId_usuario());
      }
      
      private void funcionImprimirCotizacion()
      {
          consultarDatosUsuario();
          Funciones_Generales fun = new Funciones_Generales();
//          fun.imprimirCotizacion(usuario_activo.getId_sucursal(),date.format(jdc_fecha_validez.getDate()),usuario_activo.getId_usuario());
      }
      
      private void limpiarInternalFrame()
      { 
          Object[] datos = new Object[12];
          DefaultTableModel modelo = (DefaultTableModel) tbl_factura.getModel();
          while(modelo.getRowCount()>0) modelo.removeRow(0);
          while(modelo.getRowCount()<25) modelo.addRow(datos);
          tbl_factura.setModel(modelo);
          cargarTablaInventario();
          ultimaFacturaNumero();
          limpiarTextos();
          dlg_facturar.dispose();
          tbl_factura.requestFocus();
      }
      
      private void limpiarTextos()
      {
          
          txt_buscar_producto.setText("");
          txt_cambio_factura.setText("");
//          txt_cantidad_producto.setText("");
//          txt_categoria.setText("");
          txt_cliente.setText("");
          txt_cliente_factura.setText("");
          txt_codigo_cliente.setText("");
          txt_descuento_cantidad.setText("");
          txt_descuento_procentaje.setText("");
          txt_digitos_tarjeta_credito_factura.setText("");
          txt_direccion_cliente.setText("");
//          txt_expiracion_producto.setText("");
          //txt_fecha_factura.setText("");
          txt_folio_factura.setText("");
          txt_id_cliente.setText("");
          txt_iva_cantidad.setText("");
          txt_iva_porcentaje.setText("");
//          txt_marca_producto.setText("");
//          txt_medicion_producto.setText("");
//          txt_nombre_producto.setText("");
//          txt_precio1_producto.setText("");
//          txt_precio2_producto.setText("");
//          txt_presentacion_producto.setText("");
//          txt_proveedor_producto.setText("");
          txt_recibe_factura.setText("");
          txt_subtotal.setText("");
//          txt_sucursal_producto.setText("");
          txt_total.setText("");
          txt_total_factura.setText("");
          
      }
      
      private void calcularDescuentoCajaTexto()
      {
//          float descuento_total_con_texto=0,porcentaje_descuento=0;
//          Object des =null;
//          porcentaje_descuento=(totalTotal* (Integer.parseInt(txt_iva_porcentaje.getText()) /100));
//          descuento_total_con_texto= totalTotal-porcentaje_descuento  ;
//          totalTotal=(int)descuento_total_con_texto;
//          txt_descuento_cantidad.setText(formateador.format(porcentaje_descuento));
//          des= descuento_total_con_texto;
//          JOptionPane.showMessageDialog(null, descuento_total_con_texto);
//          descuento_total= Integer.parseInt(des.toString());
//          calcularTotal();
      }
      
      private void ultimaFacturaNumero()
      {
          consultarDatosUsuario();
          Funciones_frm_factura fun = new Funciones_frm_factura();
          int n=fun.ultimaFactura(usuario_activo.getId_sucursal());
          txt_numero_factura.setText(String.valueOf(n+1));
                
      }
      
      private boolean existenciasEnProducto(Object id_producto_inventario,float cantidad_seleccionado)
      {
          Funciones_frm_factura fun = new Funciones_frm_factura();
          float cantidad = 0,stock = 0;
          cantidad = fun.existenciaProductoInventario(id_producto_inventario);
          stock = fun.stockProductoInventario(id_producto_inventario);
          if(cantidad_seleccionado>cantidad)
          {
            JOptionPane.showMessageDialog(null, "¡NO HAY SUFICIENTE PRODUCTO EN INVENTARIO!");
          }
          else
          {
              if((cantidad-cantidad_producto) <= stock)
            {
              JOptionPane.showMessageDialog(null, "¡PRODUCTO BAJO STOCK!");
              evento_txt_tecla_presionada_inventario();
            }
            else
            {
                evento_txt_tecla_presionada_inventario();
            }
          }
          return false;
      }
      
      private void calcularValorAPagarCredito()
      {
          float total_credito =0,cuotas=0,interes=0,pagar_con_interes=0;
          total_credito = totales;
          cuotas = Float.parseFloat(txt_cuotas_credito.getText());
          interes = Float.parseFloat(txt_porcentaje_interes_credito.getText());
          interes = interes /100;
          pagar_con_interes = (total_credito / cuotas) * interes;
          pagarCredito = (total_credito / cuotas) + pagar_con_interes;
          
          txt_valor_pagar_cuota.setText(String.valueOf(formateador.format(pagarCredito)));
      }
      
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlg_facturar = new javax.swing.JDialog();
        panel_jdialog_factura = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_total_factura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_cliente_factura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmb_paga_con_factura = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txt_recibe_factura = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_cambio_factura = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_digitos_tarjeta_credito_factura = new javax.swing.JTextField();
        txt_folio_factura = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        radio_imprimir_tirilla = new javax.swing.JRadioButton();
        radio_imprimir_factura = new javax.swing.JRadioButton();
        radio_no_imprimir = new javax.swing.JRadioButton();
        grupo_Factura = new javax.swing.ButtonGroup();
        dlg_cotizaciones = new javax.swing.JDialog();
        panel_jdialog_factura1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_total_cotizacion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_cliente_cotizacion = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAre_descripcion_factura1 = new javax.swing.JTextArea();
        jLabel39 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jdc_fecha_validez = new com.toedter.calendar.JDateChooser();
        dlg_credito = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        txt_cuotas_credito = new javax.swing.JTextField();
        cmb_generar_credito = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txt_total_credito = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txt_valor_pagar_cuota = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txt_porcentaje_interes_credito = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        dch_fecha_credito = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_inventario = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        pan_arriba = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_factura = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_cliente = new javax.swing.JTextField();
        txt_direccion_cliente = new javax.swing.JTextField();
        btn_accionar_facturar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cmb_acciones_facturar = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        txt_codigo_cliente = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_usuario_vendedor = new javax.swing.JTextField();
        txt_fecha_factura = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txt_numero_factura = new javax.swing.JTextField();
        txt_descuento_cantidad = new javax.swing.JTextField();
        txt_descuento_procentaje = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_iva_cantidad = new javax.swing.JTextField();
        txt_iva_porcentaje = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        pan_totales = new javax.swing.JPanel();
        txt_total = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        pan_buscar = new javax.swing.JPanel();
        txt_buscar_producto = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        cmb_buscar_producto_por = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        txt_id_cliente = new javax.swing.JTextField();

        dlg_facturar.setMaximumSize(new java.awt.Dimension(442, 460));
        dlg_facturar.setMinimumSize(new java.awt.Dimension(442, 460));
        dlg_facturar.setPreferredSize(new java.awt.Dimension(442, 460));

        panel_jdialog_factura.setBorder(javax.swing.BorderFactory.createTitledBorder("Facturar"));

        jLabel2.setText("TOTAL:");

        txt_total_factura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_total_factura.setText("0");
        txt_total_factura.setEnabled(false);

        jLabel3.setText("Cliente:");

        txt_cliente_factura.setEnabled(false);

        jLabel4.setText("Paga Con:");

        cmb_paga_con_factura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_paga_con_facturaItemStateChanged(evt);
            }
        });

        jLabel5.setText("Recibe:");

        txt_recibe_factura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_recibe_factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_recibe_facturaKeyPressed(evt);
            }
        });

        jLabel6.setText("Cambio:");

        txt_cambio_factura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sources/1444634336_save.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setText("GENERAR FACTURA");

        txt_folio_factura.setEnabled(false);

        jLabel8.setText("Folio Factura:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo Factura"));

        grupo_Factura.add(radio_imprimir_tirilla);
        radio_imprimir_tirilla.setText("Imprimir Tirilla");

        grupo_Factura.add(radio_imprimir_factura);
        radio_imprimir_factura.setText("Imprimir Factura");
        radio_imprimir_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_imprimir_facturaActionPerformed(evt);
            }
        });

        grupo_Factura.add(radio_no_imprimir);
        radio_no_imprimir.setText("No Imprimir");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radio_no_imprimir)
                    .addComponent(radio_imprimir_tirilla)
                    .addComponent(radio_imprimir_factura))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(radio_imprimir_tirilla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radio_no_imprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radio_imprimir_factura))
        );

        javax.swing.GroupLayout panel_jdialog_facturaLayout = new javax.swing.GroupLayout(panel_jdialog_factura);
        panel_jdialog_factura.setLayout(panel_jdialog_facturaLayout);
        panel_jdialog_facturaLayout.setHorizontalGroup(
            panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_jdialog_facturaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_jdialog_facturaLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addComponent(txt_folio_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_jdialog_facturaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(panel_jdialog_facturaLayout.createSequentialGroup()
                                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_cliente_factura)
                                    .addComponent(txt_total_factura, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))))
                    .addGroup(panel_jdialog_facturaLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_jdialog_facturaLayout.createSequentialGroup()
                                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_cambio_factura, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_recibe_factura, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_paga_con_factura, javax.swing.GroupLayout.Alignment.LEADING, 0, 150, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_digitos_tarjeta_credito_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_jdialog_facturaLayout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panel_jdialog_facturaLayout.setVerticalGroup(
            panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_jdialog_facturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_total_factura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cliente_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_folio_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_paga_con_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_digitos_tarjeta_credito_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_recibe_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_cambio_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_jdialog_facturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout dlg_facturarLayout = new javax.swing.GroupLayout(dlg_facturar.getContentPane());
        dlg_facturar.getContentPane().setLayout(dlg_facturarLayout);
        dlg_facturarLayout.setHorizontalGroup(
            dlg_facturarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlg_facturarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_jdialog_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        dlg_facturarLayout.setVerticalGroup(
            dlg_facturarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_facturarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_jdialog_factura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dlg_cotizaciones.setMinimumSize(new java.awt.Dimension(350, 350));

        panel_jdialog_factura1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cotizaciones"));

        jLabel10.setText("TOTAL:");

        txt_total_cotizacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_total_cotizacion.setText("0");
        txt_total_cotizacion.setEnabled(false);

        jLabel11.setText("Cliente:");

        txt_cliente_cotizacion.setEnabled(false);

        txtAre_descripcion_factura1.setColumns(20);
        txtAre_descripcion_factura1.setRows(5);
        jScrollPane4.setViewportView(txtAre_descripcion_factura1);

        jLabel39.setText("Descripcion:");

        jButton3.setText("Generar Cotización");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel40.setText("GENERAR COTIZACIÒN");

        jLabel1.setText("Validez:");

        javax.swing.GroupLayout panel_jdialog_factura1Layout = new javax.swing.GroupLayout(panel_jdialog_factura1);
        panel_jdialog_factura1.setLayout(panel_jdialog_factura1Layout);
        panel_jdialog_factura1Layout.setHorizontalGroup(
            panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_jdialog_factura1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_jdialog_factura1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_jdialog_factura1Layout.createSequentialGroup()
                                .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdc_fecha_validez, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_cliente_cotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel40)))
                    .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_total_cotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_jdialog_factura1Layout.createSequentialGroup()
                            .addComponent(jLabel39)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_jdialog_factura1Layout.setVerticalGroup(
            panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_jdialog_factura1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_total_cotizacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdc_fecha_validez, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cliente_cotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_jdialog_factura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );

        javax.swing.GroupLayout dlg_cotizacionesLayout = new javax.swing.GroupLayout(dlg_cotizaciones.getContentPane());
        dlg_cotizaciones.getContentPane().setLayout(dlg_cotizacionesLayout);
        dlg_cotizacionesLayout.setHorizontalGroup(
            dlg_cotizacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_cotizacionesLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(panel_jdialog_factura1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dlg_cotizacionesLayout.setVerticalGroup(
            dlg_cotizacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_cotizacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_jdialog_factura1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dlg_credito.setMinimumSize(new java.awt.Dimension(300, 270));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Credito"));

        txt_cuotas_credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cuotas_creditoActionPerformed(evt);
            }
        });

        cmb_generar_credito.setText("Generar Credito");
        cmb_generar_credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_generar_creditoActionPerformed(evt);
            }
        });

        jLabel36.setText("Total:");

        jLabel41.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel41.setText("GENERAR CREDITO");

        txt_total_credito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_total_credito.setText("0");
        txt_total_credito.setEnabled(false);

        jLabel37.setText("# Cuotas:");

        jLabel38.setText("(Meses).");

        jLabel42.setText("$");

        txt_valor_pagar_cuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_valor_pagar_cuotaActionPerformed(evt);
            }
        });

        jLabel43.setText("(Por Cuota).");

        jLabel44.setText("% Interes:");

        txt_porcentaje_interes_credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_porcentaje_interes_creditoActionPerformed(evt);
            }
        });
        txt_porcentaje_interes_credito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_porcentaje_interes_creditoKeyPressed(evt);
            }
        });

        jLabel45.setText("Fecha Cuota:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_total_credito)
                        .addGap(107, 107, 107))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(71, 71, 71))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel44))
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_generar_credito)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dch_fecha_credito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_porcentaje_interes_credito, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(txt_cuotas_credito))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38))
                            .addComponent(txt_valor_pagar_cuota, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txt_total_credito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cuotas_credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_porcentaje_interes_credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_valor_pagar_cuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel45)
                    .addComponent(dch_fecha_credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(cmb_generar_credito)
                .addGap(69, 69, 69))
        );

        javax.swing.GroupLayout dlg_creditoLayout = new javax.swing.GroupLayout(dlg_credito.getContentPane());
        dlg_credito.getContentPane().setLayout(dlg_creditoLayout);
        dlg_creditoLayout.setHorizontalGroup(
            dlg_creditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_creditoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlg_creditoLayout.setVerticalGroup(
            dlg_creditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlg_creditoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
        );

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_inventario.setShowHorizontalLines(false);
        tbl_inventario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tbl_inventarioFocusGained(evt);
            }
        });
        tbl_inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_inventarioMousePressed(evt);
            }
        });
        tbl_inventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_inventarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_inventarioKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_inventario);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 1196, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbl_factura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "     Item", "     Id", "     Producto", "     Marca", "Presentacion", "Medicion", "  Iva", "Cant.", "Precio Unitario", "Descuento %", "Valor Und. Dcto", "     SubTotal$", "Precio Sin iva"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Float.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_factura.setShowHorizontalLines(false);
        tbl_factura.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tbl_facturaCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tbl_factura.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbl_facturaPropertyChange(evt);
            }
        });
        tbl_factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_facturaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_factura);
        if (tbl_factura.getColumnModel().getColumnCount() > 0) {
            tbl_factura.getColumnModel().getColumn(0).setPreferredWidth(35);
            tbl_factura.getColumnModel().getColumn(1).setPreferredWidth(60);
            tbl_factura.getColumnModel().getColumn(2).setPreferredWidth(160);
            tbl_factura.getColumnModel().getColumn(5).setPreferredWidth(35);
            tbl_factura.getColumnModel().getColumn(6).setPreferredWidth(0);
            tbl_factura.getColumnModel().getColumn(7).setPreferredWidth(35);
            tbl_factura.getColumnModel().getColumn(12).setPreferredWidth(0);
        }

        jLabel12.setText("Cliente:");

        jLabel13.setText("Direccion:");

        txt_cliente.setEditable(false);
        txt_cliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_direccion_cliente.setEditable(false);
        txt_direccion_cliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_accionar_facturar.setText("Accionar");
        btn_accionar_facturar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_accionar_facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_accionar_facturarActionPerformed(evt);
            }
        });

        jLabel18.setText("Acciones:");

        cmb_acciones_facturar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Facturar", "Cotizar", "Creditar" }));
        cmb_acciones_facturar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setText("Cod. Cliente:");

        txt_codigo_cliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_codigo_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_codigo_clienteKeyPressed(evt);
            }
        });

        jLabel19.setText("Vendedor:");

        txt_usuario_vendedor.setEditable(false);
        txt_usuario_vendedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_fecha_factura.setEditable(false);
        txt_fecha_factura.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel34.setText("Fecha:");

        jLabel35.setText("# Factura:");

        txt_numero_factura.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_numero_factura.setEnabled(false);
        txt_numero_factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_numero_facturaKeyPressed(evt);
            }
        });

        txt_descuento_cantidad.setEditable(false);
        txt_descuento_cantidad.setText("0");
        txt_descuento_cantidad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_descuento_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descuento_cantidadKeyPressed(evt);
            }
        });

        txt_descuento_procentaje.setEditable(false);
        txt_descuento_procentaje.setText("0");
        txt_descuento_procentaje.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_descuento_procentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descuento_procentajeKeyPressed(evt);
            }
        });

        jLabel14.setText("Descuento%:");

        txt_iva_cantidad.setEditable(false);
        txt_iva_cantidad.setText("0");
        txt_iva_cantidad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_iva_porcentaje.setEditable(false);
        txt_iva_porcentaje.setText("0");
        txt_iva_porcentaje.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel33.setText("IVA%:");

        txt_total.setEditable(false);
        txt_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_total.setText("0.0");
        txt_total.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("TOTAL:");

        jLabel15.setText("Total Bruto:");

        txt_subtotal.setEditable(false);
        txt_subtotal.setText("0");
        txt_subtotal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pan_totalesLayout = new javax.swing.GroupLayout(pan_totales);
        pan_totales.setLayout(pan_totalesLayout);
        pan_totalesLayout.setHorizontalGroup(
            pan_totalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_totalesLayout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pan_totalesLayout.setVerticalGroup(
            pan_totalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_totalesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pan_totalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        txt_buscar_producto.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_buscar_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_buscar_productoMousePressed(evt);
            }
        });
        txt_buscar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_productoKeyPressed(evt);
            }
        });

        jLabel20.setText("Buscar Producto:");

        cmb_buscar_producto_por.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Identificacion", "Nombre", "Iva", "Marca", "Categoria", "Presentacion", "Expiracion", "Medicion", "Cantidad", "Stock", "Barras", "Precio1", "Precio2", "Sucursal", "Proveedor" }));
        cmb_buscar_producto_por.setSelectedIndex(10);

        jLabel21.setText("Por:");

        javax.swing.GroupLayout pan_buscarLayout = new javax.swing.GroupLayout(pan_buscar);
        pan_buscar.setLayout(pan_buscarLayout);
        pan_buscarLayout.setHorizontalGroup(
            pan_buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_buscarLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmb_buscar_producto_por, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(txt_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(231, 231, 231))
        );
        pan_buscarLayout.setVerticalGroup(
            pan_buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_buscarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pan_buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(cmb_buscar_producto_por, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        javax.swing.GroupLayout pan_arribaLayout = new javax.swing.GroupLayout(pan_arriba);
        pan_arriba.setLayout(pan_arribaLayout);
        pan_arribaLayout.setHorizontalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_arribaLayout.createSequentialGroup()
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE))
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addComponent(pan_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pan_totales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_descuento_procentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descuento_cantidad))
                    .addGroup(pan_arribaLayout.createSequentialGroup()
                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createSequentialGroup()
                                    .addComponent(jLabel35)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_numero_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_codigo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(pan_arribaLayout.createSequentialGroup()
                                            .addGap(14, 14, 14)
                                            .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(pan_arribaLayout.createSequentialGroup()
                                                    .addComponent(jLabel13)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txt_direccion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pan_arribaLayout.createSequentialGroup()
                                                        .addComponent(jLabel18)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(cmb_acciones_facturar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btn_accionar_facturar)))
                                                    .addGroup(pan_arribaLayout.createSequentialGroup()
                                                        .addComponent(jLabel19)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txt_usuario_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_fecha_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pan_arribaLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_iva_porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_iva_cantidad)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pan_arribaLayout.setVerticalGroup(
            pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_arribaLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_numero_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_codigo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_direccion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_usuario_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txt_fecha_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_descuento_procentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_descuento_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txt_iva_porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_iva_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_acciones_facturar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_arribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_accionar_facturar)
                    .addComponent(pan_totales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pan_arriba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan_arriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscar_productoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_buscar_productoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_productoMousePressed

    private void txt_buscar_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_productoKeyPressed
        // TODO add your handling code here:
        
           if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            evento_txt_tecla_presionada_buscar_producto_inventario();
            tbl_factura.setRowSelectionInterval(0, 0);
        }
        else
        {
            
        }
    }//GEN-LAST:event_txt_buscar_productoKeyPressed

    private void btn_accionar_facturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_accionar_facturarActionPerformed
        // TODO add your handling code here:
        btn_accionar(cmb_acciones_facturar.getSelectedItem().toString());
    }//GEN-LAST:event_btn_accionar_facturarActionPerformed

    private void tbl_inventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_inventarioMousePressed
        // TODO add your handling code here:
        
//        click_en_tbl_producto_factura("");
    }//GEN-LAST:event_tbl_inventarioMousePressed

    private void tbl_inventarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_inventarioKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            cantidad_producto = Float.parseFloat(JOptionPane.showInputDialog("CANTIDAD",1));
            existenciasEnProducto(tbl_inventario.getModel().getValueAt(tbl_inventario.getSelectedRow(), 0),cantidad_producto);
        }
        else
        {
            if(evt.getKeyCode()==KeyEvent.VK_UP  )
            {
//                click_en_tbl_producto_factura("ARRIBA");
            }
               else
                { 
                    if(evt.getKeyCode()==KeyEvent.VK_DOWN  )
                    {
//                        click_en_tbl_producto_factura("ABAJO");
                    }
                    else
                    {
//                        click_en_tbl_producto_factura("");
                    }
                    if(evt.getKeyCode() == KeyEvent.VK_TAB)
                    {
                        txt_codigo_cliente.requestFocus();
                    }
                }
                
             
        }
        
    }//GEN-LAST:event_tbl_inventarioKeyPressed

    private void tbl_facturaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbl_facturaPropertyChange
        // TODO add your handling code here:
        if(tbl_factura.getModel().getValueAt(0, 0)==null)
        {}
        else
        {
            if(Integer.parseInt(tbl_factura.getModel().getValueAt(0, 0).toString())==0)
            {}
            else
            {
                try{
                calculuarDescuento(Double.parseDouble(tbl_factura.getModel().getValueAt(tbl_factura.getSelectedRow(), tbl_factura.getSelectedColumn()).toString()),tbl_factura.getSelectedRow(),tbl_factura.getSelectedColumn());
                }
                catch(Exception ex)
                {
                    
                }
            }
            
        }
        
    }//GEN-LAST:event_tbl_facturaPropertyChange

    private void tbl_facturaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tbl_facturaCaretPositionChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tbl_facturaCaretPositionChanged

    private void tbl_facturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_facturaKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyChar()==KeyEvent.VK_DELETE)
        {
            eliminarFilaFactura();
        }
        else
        {
            
        }
    }//GEN-LAST:event_tbl_facturaKeyPressed

    private void tbl_inventarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbl_inventarioFocusGained
        // TODO add your handling code here:
        
//         click_en_tbl_producto_factura("");
    }//GEN-LAST:event_tbl_inventarioFocusGained

    private void txt_codigo_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigo_clienteKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            
                     if(buscarCliente())
                        {
                            btn_accionar(cmb_acciones_facturar.getSelectedItem().toString());
                        }
                    else {
                        JOptionPane.showMessageDialog(null, "NO SE ENCONTRO CLIENTE VERIFIQUE SUS DATOS!");
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_TAB) {
                        btn_accionar_facturar.requestFocus();
                    }
           
        }
           
    }//GEN-LAST:event_txt_codigo_clienteKeyPressed

    private void txt_numero_facturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numero_facturaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numero_facturaKeyPressed

    private void txt_descuento_cantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descuento_cantidadKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            
        }
        else
        {
            
        }
    }//GEN-LAST:event_txt_descuento_cantidadKeyPressed

    private void txt_descuento_procentajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descuento_procentajeKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            calcularDescuentoCajaTexto();
        }
        else
        {
            
        }
    }//GEN-LAST:event_txt_descuento_procentajeKeyPressed

    private void tbl_inventarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_inventarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_inventarioKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         if(txt_total_cotizacion.getText().equalsIgnoreCase("0.0"))
                {
                    JOptionPane.showMessageDialog(null, "NO HA SELECCIONADO NINGUN PRODUCTO A FACTURAR!");
                }
                else
                {
        registrarCotizacion();
        dlg_cotizaciones.dispose();
        limpiarInternalFrame();
        funcionImprimirCotizacion();
                }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_cuotas_creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cuotas_creditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cuotas_creditoActionPerformed

    private void txt_valor_pagar_cuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_valor_pagar_cuotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_valor_pagar_cuotaActionPerformed

    private void txt_porcentaje_interes_creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_porcentaje_interes_creditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_porcentaje_interes_creditoActionPerformed

    private void cmb_generar_creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_generar_creditoActionPerformed
        // TODO add your handling code here:
         if(txt_total_credito.getText().equalsIgnoreCase("0.0"))
                {
                    JOptionPane.showMessageDialog(null, "NO HA SELECCIONADO NINGUN PRODUCTO A FACTURAR!");
                }
                else
                {
        registrarCredito();
        dlg_credito.dispose();
        limpiarInternalFrame();
                }
    }//GEN-LAST:event_cmb_generar_creditoActionPerformed

    private void txt_porcentaje_interes_creditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_porcentaje_interes_creditoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            calcularValorAPagarCredito();
        }
        else
        {
            
        } 
        
    }//GEN-LAST:event_txt_porcentaje_interes_creditoKeyPressed

    private void radio_imprimir_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_imprimir_facturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_imprimir_facturaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String opcion=null;
        if(radio_imprimir_tirilla.isSelected())
        opcion ="radio_imprimir_tirilla";

        if(radio_no_imprimir.isSelected())
        opcion = "radio_no_imprimir";

        switch (opcion){
            case "radio_imprimir_tirilla":
            if(txt_total_factura.getText().equalsIgnoreCase("0.0"))
            {
                JOptionPane.showMessageDialog(null, "NO HA SELECCIONADO NINGUN PRODUCTO A FACTURAR!");
            }
            else
            {
                registrarFactura();
                funcionImprimirTirilla();
                dlg_facturar.dispose();
                limpiarInternalFrame();
            }
            break;
            case "radio_no_imprimir":
            if(txt_total_factura.getText().equalsIgnoreCase("0.0"))
            {
                JOptionPane.showMessageDialog(null, "NO HA SELECCIONADO NINGUN PRODUCTO A FACTURAR!");
            }
            else
            {
                registrarFactura();
                dlg_facturar.dispose();
                limpiarInternalFrame();
            }
            break;
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_recibe_facturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_recibe_facturaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        {
            operacionCambio();
        }
        else
        {

        }
    }//GEN-LAST:event_txt_recibe_facturaKeyPressed

    private void cmb_paga_con_facturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_paga_con_facturaItemStateChanged
        // TODO add your handling code here:
        evento_cambio_combobox();
    }//GEN-LAST:event_cmb_paga_con_facturaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_accionar_facturar;
    private javax.swing.JComboBox cmb_acciones_facturar;
    private javax.swing.JComboBox cmb_buscar_producto_por;
    private javax.swing.JButton cmb_generar_credito;
    private javax.swing.JComboBox cmb_paga_con_factura;
    private com.toedter.calendar.JDateChooser dch_fecha_credito;
    private javax.swing.JDialog dlg_cotizaciones;
    private javax.swing.JDialog dlg_credito;
    private javax.swing.JDialog dlg_facturar;
    private javax.swing.ButtonGroup grupo_Factura;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator4;
    private com.toedter.calendar.JDateChooser jdc_fecha_validez;
    private javax.swing.JPanel pan_arriba;
    private javax.swing.JPanel pan_buscar;
    private javax.swing.JPanel pan_totales;
    private javax.swing.JPanel panel_jdialog_factura;
    private javax.swing.JPanel panel_jdialog_factura1;
    private javax.swing.JRadioButton radio_imprimir_factura;
    private javax.swing.JRadioButton radio_imprimir_tirilla;
    private javax.swing.JRadioButton radio_no_imprimir;
    private javax.swing.JTable tbl_factura;
    private javax.swing.JTable tbl_inventario;
    private javax.swing.JTextArea txtAre_descripcion_factura1;
    private javax.swing.JTextField txt_buscar_producto;
    private javax.swing.JTextField txt_cambio_factura;
    private javax.swing.JTextField txt_cliente;
    private javax.swing.JTextField txt_cliente_cotizacion;
    private javax.swing.JTextField txt_cliente_factura;
    private javax.swing.JTextField txt_codigo_cliente;
    private javax.swing.JTextField txt_cuotas_credito;
    private javax.swing.JTextField txt_descuento_cantidad;
    private javax.swing.JTextField txt_descuento_procentaje;
    private javax.swing.JTextField txt_digitos_tarjeta_credito_factura;
    private javax.swing.JTextField txt_direccion_cliente;
    private javax.swing.JTextField txt_fecha_factura;
    private javax.swing.JTextField txt_folio_factura;
    private javax.swing.JTextField txt_id_cliente;
    private javax.swing.JTextField txt_iva_cantidad;
    private javax.swing.JTextField txt_iva_porcentaje;
    private javax.swing.JTextField txt_numero_factura;
    private javax.swing.JTextField txt_porcentaje_interes_credito;
    private javax.swing.JTextField txt_recibe_factura;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_cotizacion;
    private javax.swing.JTextField txt_total_credito;
    private javax.swing.JTextField txt_total_factura;
    private javax.swing.JTextField txt_usuario_vendedor;
    private javax.swing.JTextField txt_valor_pagar_cuota;
    // End of variables declaration//GEN-END:variables
}
