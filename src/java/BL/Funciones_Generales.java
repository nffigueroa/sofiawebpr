/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package BL;

import Constructores.Contructor_CortesCaja;
import Constructores.Constructor_usuario_permiso;
import Constructores.Contructor_Cliente_Seleccionado;
import Constructores.Constructor_Usuario;
import Constructores.Constructor_Mi_empresa;
import Constructores.Constructo_Cantidad_Productos_Vendido;
import Constructores.Constructor_Cantidad_Categoria_Vendido;
import Constructores.Constructor_Proveedor;
import Constructores.Constructor_venta_Diaria;
import DA.Conexion;
import DA.consultas_Entrada_Inventario;
import DA.Consultas_Generales;
import DA.Consultas_VentasCategorias;
import DA.Consultas_informeMasVendido;
import DA.consultas_Cliente;
import DA.consultas_cortesCaja;
import DA.consultas_login;
import java.sql.Connection;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Nestor1
 */
public class Funciones_Generales extends DA.Consultas_Generales {

    public int filas;
    private java.sql.ResultSetMetaData meta;
    Constructor_Usuario cliente_sesion = new Constructor_Usuario();
    Contructor_Cliente_Seleccionado clientes = new Contructor_Cliente_Seleccionado();
    private ResultSet rs = null;
    Date now = new Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    consultas_cortesCaja corteCaja = new consultas_cortesCaja();
    Contructor_CortesCaja corte_caja = new Contructor_CortesCaja();
    Constructor_usuario_permiso permisos = new Constructor_usuario_permiso();
    Constructor_Mi_empresa mi_empresa = new Constructor_Mi_empresa();
    public Connection conn = null;
    Constructores.Constructor_Proveedor proveedor = new Constructor_Proveedor();
    Constructores.Constructo_Cantidad_Productos_Vendido producto_cantidad = new Constructo_Cantidad_Productos_Vendido();
    Constructores.Constructor_Cantidad_Categoria_Vendido categoria_cantidad = new Constructor_Cantidad_Categoria_Vendido();
    Constructores.Constructor_venta_Diaria venta_diaria = new Constructor_venta_Diaria();

    
    
    public int funcionGananciaProducto(int id_producto_inventario)
    {
        return consultaUtilidadPorProducto(id_producto_inventario);
    }
    
    public ArrayList resultSetToArrayList(ResultSet rs) throws SQLException{
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    ArrayList results = new ArrayList();

    while (rs.next()) {
        HashMap row = new HashMap();
        results.add(row);

        for(int i=1; i<=columns; i++){
          row.put(md.getColumnName(i),rs.getObject(i));
        }
    }
    return results;
}
    
    public boolean registrarHistorialEntradaInventairio(Object id_inventario, Object cantidad, int id_usuario,Object consecutivo,int idSucursal) {
        Object hora_pasar = hora.format(now), fecha = date.format(now);
        return consultaRegistrarHistorialInventarioIngreso(id_inventario, cantidad, hora_pasar, fecha, id_usuario,consecutivo,idSucursal);
    }
    

    public int ideEmpresaXIdeSucursal(int id_sucursal)
    {
        consultas_Entrada_Inventario con = new Funciones_Entrada_Inventario();
        int id_empresa = con.consultaIdEmpresa(id_sucursal);
        return id_empresa;
    }
    
    public boolean registrarCierreSesion(int id_usuario) {
        int id_sesion = consultaIdSesion(id_usuario);
        Object fecha_fin, hora_fin;
        fecha_fin = date.format(now);
        hora_fin = hora.format(now);
        return consultaRegistrarCerrarSession(id_sesion, fecha_fin, hora_fin);
    }
    
    
    
    public String funcionProductoMasGanacia(int id_sucursal)
    {
        ResultSet rf = null;
        DA.consultas_Entrada_Inventario mol = new Funciones_Entrada_Inventario();
        rf =mol.llenarTabla_inventario(id_sucursal);
        int aux_utilidad=0,utilidad=0;
        String nombre_producto = null;
        try {
            while(rf.next())
            {
                aux_utilidad =0;
                aux_utilidad = rf.getInt("PRO.utilidad");
                
                if(aux_utilidad>utilidad)
                {
                    nombre_producto = rf.getString("PR.nombre_producto");
                    utilidad = aux_utilidad;
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombre_producto;
    }
    
    public float promedioVentasDiarias(int id_empresa)
    {
        Consultas_Generales con = new Consultas_Generales();
        String Fecha1=con.consultaPrimerFechaXEmpresa(id_empresa);
        venta_diaria=llenarVentaDiariaConstructor(id_empresa, Fecha1, date.format(now));
        int cantidad_dias = venta_diaria.getFecha().length;
        float aux=0;
        float[] ventas = new float[cantidad_dias];
        ventas= venta_diaria.getTotalVenta();
        for (int i = 0; i < cantidad_dias; i++) {
            aux = aux + (ventas[i]);
        }
        aux = aux/cantidad_dias;
        return aux;
    }
    
//    public float promedioVentasMensuales(int id_empresa)
//    {
//        Consultas_Generales con = new Consultas_Generales();
//        String Fecha1=con.consultaPrimerFechaXEmpresa(id_empresa);
//        venta_diaria=llenarVentaMensualConstructoXEmpresa(id_empresa, Fecha1, date.format(now));
//        int cantidad_dias = venta_diaria.getFecha().length;
//        float aux=0;
//        float[] ventas = new float[cantidad_dias];
//        ventas= venta_diaria.getTotalVenta();
//        for (int i = 0; i < cantidad_dias; i++) {
//            aux = aux + (ventas[i]);
//        }
//        aux = aux/cantidad_dias;
//        return aux;
//    }
    
    
    public Constructores.Constructor_venta_Diaria llenarVentaDiariaConstructor(int id_empresa, String fecha, String fechaHasta) {
        int filas = 0, ban = 0, aux = 0,contador=0;
        
        Funciones_frm_factura fun = new Funciones_frm_factura();
        String fechaRs = new String(),fecha_normal=new String();
        
        
        try {
            // LA VARIABLE aux lleva la cantidad de fils que se deben mostrar
            rs = fun.consultaLlenarTablaFactura(id_empresa, fecha, fechaHasta,false);
            while (rs.next()) {
                 fechaRs = rs.getString("FACT.fecha_creacion"); // SE OBTIENE LA FECHA QUE QUEREMOS COMPARAR DESDE LA CONSULTA
                 if(fecha_normal.equalsIgnoreCase(fechaRs))// SE USA UN ARRAY AUXILIAR (fecha_normal) EL CUAL ACUMULARÁ LAS FECHAS Y CAMBIARA CUANDO ENCUETRE UNA FECHA DISTINTIA
                 {
                     
                 }
                 else
                 {
                     fecha_normal = fechaRs;    //ACUMULAMOS LA FECHA YA QUE ES DISTINTA A LA ACUMULADA
                     aux++;// SE INCREMENTA AUX CADA VEZ QUE SE AÑADE PARA INDICAR CUANTAS FILAS SE DEBEN MOSTRAR
                 }
            }
            String[] fecha_aux = new String[aux];
            for (int i = 0; i < aux; i++) {
                fecha_aux[i]="";
            }
            int con=0;
            rs.beforeFirst();
            fechaRs=null;
            while (rs.next()) {
                ban=0;
                 fechaRs = rs.getString("FACT.fecha_creacion");
                 for (int i = 0; i < fecha_aux.length; i++) {
                     if(fecha_aux[i].equalsIgnoreCase(fechaRs))
                        {
                           ban=1;
                        }
                 }
                         if(ban==0)
                        {
                            fecha_aux[con]=fechaRs;
                            con++;
                        }
            }
            rs.beforeFirst();
            float[] totalVentas = new float[fecha_aux.length], totalIva = new float[fecha_aux.length], totalDescuento = new float[fecha_aux.length];
            while (rs.next()) {
                for (int i = 0; i < fecha_aux.length; i++) {
                    if (fecha_aux[i].equalsIgnoreCase(rs.getString("FACT.fecha_creacion"))) {
                        totalDescuento[i] = totalDescuento[i] + rs.getFloat("FACT.descuento");
                        totalIva[i] = totalIva[i] + rs.getFloat("FACT.iva");
                        totalVentas[i] = totalVentas[i] + rs.getFloat("FACT.total");
                    }
                }
            }
            venta_diaria.setFecha(fecha_aux);
            venta_diaria.setTotalDescuento(totalDescuento);
            venta_diaria.setTotalIva(totalIva);
            venta_diaria.setTotalVenta(totalVentas);
            return venta_diaria;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList llenarVentaMensualConstructoXEmpresa(int id_empresa, String fecha, String fechaHasta) {
        Funciones_frm_factura fun = new Funciones_frm_factura();
        try {
            rs = fun.consultaLlenarVentaXMesXEmpresa(id_empresa, fecha, fechaHasta);
            return this.resultSetToArrayList(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Constructores.Constructor_venta_Diaria llenarVentaMensualConstructoXSucursal(String sucursal, String fecha, String fechaHasta) {
        int filas = 0, ban = 0, aux = 0;
        Funciones_frm_factura fun = new Funciones_frm_factura();
        String fechaRs = new String(), mes= new String();
        int id_sucursal = consultaIdSucursal(sucursal);
        try {
            rs = fun.consultaLlenarVentaXMesXSucursal(id_sucursal, fecha, fechaHasta);
           
             while (rs.next()) {
                 fechaRs = rs.getString("MESespañol"); // SE OBTIENE LA FECHA QUE QUEREMOS COMPARAR DESDE LA CONSULTA
                 if(mes.equalsIgnoreCase(fechaRs))// SE USA UN ARRAY AUXILIAR (fecha_normal) EL CUAL ACUMULARÁ LAS FECHAS Y CAMBIARA CUANDO ENCUETRE UNA FECHA DISTINTIA
                 {
                     
                 }
                 else
                 {
                     mes = fechaRs;    //ACUMULAMOS LA FECHA YA QUE ES DISTINTA A LA ACUMULADA
                     filas++;// SE INCREMENTA AUX CADA VEZ QUE SE AÑADE PARA INDICAR CUANTAS FILAS SE DEBEN MOSTRAR
                 }
            }
            
            
            String[] fecha_para_constructor = new String[filas];
            for (int i = 0; i < filas; i++) {
                fecha_para_constructor[i] = "";
            }
            rs.beforeFirst();
            while (rs.next()) {
                fechaRs = rs.getString("MESespañol");
                for (int i = 0; i < filas; i++) {
                    if (fecha_para_constructor[i].equals(fechaRs)) {
                        ban = 1;
                    }
                }
                if (ban != 1) {
                    fecha_para_constructor[aux] = rs.getString("MESespañol");
                    aux++;
                }
                ban = 0;
            }
            rs.beforeFirst();
            float[] totalVentas = new float[filas], totalIva = new float[filas], totalDescuento = new float[filas];
            while (rs.next()) {
                for (int i = 0; i < filas; i++) {
                    if (fecha_para_constructor[i].equalsIgnoreCase(rs.getString("MESespañol"))) {
                        totalDescuento[i] = totalDescuento[i] + rs.getFloat("descuento");
                        totalIva[i] = totalIva[i] + rs.getFloat("iva");
                        totalVentas[i] = totalVentas[i] + rs.getFloat("total");
                    }
                }
            }
            venta_diaria.setFecha(fecha_para_constructor);
            venta_diaria.setTotalDescuento(totalDescuento);
            venta_diaria.setTotalIva(totalIva);
            venta_diaria.setTotalVenta(totalVentas);
            return venta_diaria;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Constructores.Constructor_venta_Diaria llenarVentaDiariaConstructorXSucursal(String id_sucursal, String fecha, String fechaHasta) {
        int filas = 0, ban = 0, aux = 0;
        Funciones_frm_factura fun = new Funciones_frm_factura();
        String fechaRs = null;
        try {
            int sucur = consultaIdSucursal(id_sucursal);
            rs = fun.consultaLlenarTablaFactura(sucur, fecha, fechaHasta,true);
            while (rs.next()) {
                filas = filas + 1;
            }
            String[] fecha_para_constructor = new String[filas];
            for (int i = 0; i < filas; i++) {
                fecha_para_constructor[i] = "";
            }
            rs.beforeFirst();
            while (rs.next()) {
                fechaRs = rs.getString("FACT.fecha_creacion");
                for (int i = 0; i < filas; i++) {
                    if (fecha_para_constructor[i].equals(fechaRs)) {
                        ban = 1;
                    }
                }
                if (ban != 1) {
                    fecha_para_constructor[aux] = rs.getString("FACT.fecha_creacion");
                    aux++;
                }
                ban = 0;
            }
            rs.beforeFirst();
            float[] totalVentas = new float[filas], totalIva = new float[filas], totalDescuento = new float[filas];
            while (rs.next()) {
                for (int i = 0; i < filas; i++) {
                    if (fecha_para_constructor[i].equalsIgnoreCase(rs.getString("FACT.fecha_creacion"))) {
                        totalDescuento[i] = totalDescuento[i] + rs.getFloat("descuento");
                        totalIva[i] = totalIva[i] + rs.getFloat("iva");
                        totalVentas[i] = totalVentas[i] + rs.getFloat("total");
                    }
                }
            }
            venta_diaria.setFecha(fecha_para_constructor);
            venta_diaria.setTotalDescuento(totalDescuento);
            venta_diaria.setTotalIva(totalIva);
            venta_diaria.setTotalVenta(totalVentas);
            return venta_diaria;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Constructores.Constructor_Cantidad_Categoria_Vendido listadoCategoriasProducto() {
        int aux = 0, filas = 0;
        DA.Consultas_VentasCategorias fun = new Consultas_VentasCategorias();
        rs = fun.consultaListaCategoria();
        try {
            while (rs.next()) {
                filas++;
            }
            rs.beforeFirst();
            int[] id_pro = new int[filas];
            String[] categorias = new String[filas];
            while (rs.next()) {
                id_pro[aux] = (rs.getInt("id_categoria"));
                categorias[aux] = (rs.getString("categoria"));
                aux++;
            }
            categoria_cantidad.setId_categoria(id_pro);
            categoria_cantidad.setCategoria(categorias);
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria_cantidad;
    }

    public Constructo_Cantidad_Productos_Vendido listadoProductoInventario(int id_sucursal) {

        int aux = 0, filas = 0;
        DA.Consultas_informeMasVendido fun = new Consultas_informeMasVendido();
        rs = fun.consultaListaProducto(id_sucursal);
        try {
            while (rs.next()) {
                filas++;
            }
            rs.beforeFirst();
            int[] id_pro = new int[filas];
            String[] nombre = new String[filas];
            while (rs.next()) {
                id_pro[aux] = (rs.getInt("INVEN.id_producto_inventario"));
                nombre[aux] = (rs.getString("PRO.nombre_producto"));
                aux++;
            }
            producto_cantidad.setId_producto_inventario(id_pro);
            producto_cantidad.setProducto(nombre);
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto_cantidad;
    }

    public Constructo_Cantidad_Productos_Vendido listadoProductoInventarioXEmpresa(int id_empresa) {

        int aux = 0, filas = 0;
        DA.Consultas_informeMasVendido fun = new Consultas_informeMasVendido();
        rs = fun.consultaListaProductoXEmpressa(id_empresa);
        try {
            while (rs.next()) {
                filas++;
            }
            rs.beforeFirst();
            int[] id_pro = new int[filas];
            String[] nombre = new String[filas];
            while (rs.next()) {
                id_pro[aux] = (rs.getInt("INVEN.id_producto_inventario"));
                nombre[aux] = (rs.getString("PRO.nombre_producto"));
                aux++;
            }
            producto_cantidad.setId_producto_inventario(id_pro);
            producto_cantidad.setProducto(nombre);
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto_cantidad;
    }

    public boolean registrarSesion(int id_usuario) {
        Object fecha_inicio, hora_inicio;
        fecha_inicio = date.format(now);
        hora_inicio = hora.format(now);
        return consultaRegistrarSesion(id_usuario, fecha_inicio, hora_inicio);
    }

//    public void iniciarConexion() {
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver").N;
//            conn = DriverManager.getConnection("jdbc:mysql://72.29.85.225/swincomc_20140512_siventas", "swincomc_swinfab","CQel8P1WG]ZZ");
//            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swincomc_20140512_siventas", "root","");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }

    public Constructor_Usuario usuario(Object id_usuario) {
        consultas_login log = new Funciones_Login();
        rs = log.consultasDatosLogeoUsuario(id_usuario);
        try {
            while (rs.next()) {
                cliente_sesion.setId_cargo(rs.getInt("id_cargo"));
                cliente_sesion.setId_sucursal(rs.getInt("id_sucursal"));
                cliente_sesion.setId_usuario(rs.getInt("id_usuario"));
                cliente_sesion.setNombre(rs.getString("nombre_usuario"));
                cliente_sesion.setApellido(rs.getString("apellido_usuario"));
                cliente_sesion.setUsuario_nombre(rs.getString("usuario"));
                cliente_sesion.setIdRegimen(rs.getInt("id_regimen"));
                cliente_sesion.setPassword(rs.getString("password"));
            }rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente_sesion;
    }
    
    public Constructor_Usuario usuarioPorId(Object id_usuario) {
        consultas_login log = new Funciones_Login();
        rs = log.consultasDatosLogeoPorID(id_usuario);
        try {
            while (rs.next()) {
                cliente_sesion.setId_cargo(rs.getInt("id_cargo"));
                cliente_sesion.setId_sucursal(rs.getInt("id_sucursal"));
                cliente_sesion.setId_usuario(rs.getInt("id_usuario"));
                cliente_sesion.setNombre(rs.getString("nombre_usuario"));
                cliente_sesion.setApellido(rs.getString("apellido_usuario"));
                cliente_sesion.setUsuario_nombre(rs.getString("usuario"));
                cliente_sesion.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente_sesion;
    }

    public Constructor_Mi_empresa datosMiEmpresa(int id_sucursal) {
        Consultas_Generales log = new Consultas_Generales();
        DA.consultas_factura col = new DA.consultas_factura();
        int numero_factura=col.cosultaNumeroFactura(id_sucursal);
        rs = log.consultaDatosMiEmpresa(id_sucursal);
        try {
            while (rs.next()) {
                mi_empresa.setId_empresa(rs.getInt("EMPRESA.id_empresa"));
                mi_empresa.setEmpresa(rs.getString("EMPRESA.empresa"));
                mi_empresa.setGerente(rs.getString("EMPRESA.gerente"));
                mi_empresa.setCorreo(rs.getString("EMPRESA.correo_empresa"));
                mi_empresa.setNit(rs.getString("EMPRESA.nit_mi_empresa"));
                mi_empresa.setResolucion_facturacion_desde(rs.getString("RESOLUCION.desde"));
                mi_empresa.setResolucion_facturacion_hasta(rs.getString("RESOLUCION.hasta"));
                mi_empresa.setTipo_regimen_empresa(rs.getString("REGIMEN.tipo_regimen"));
                mi_empresa.setNit(rs.getString("EMPRESA.nit_mi_empresa"));
                mi_empresa.setResolucion_facturacion_fecha(date.format(rs.getDate("RESOLUCION.fecha")));
                mi_empresa.setDireccion(rs.getString("SUCUR.direccion_sucursal"));
                mi_empresa.setTelefono(rs.getString("SUCUR.telefono_sucursal"));
                mi_empresa.setCiudad(rs.getString("CIU.ciudad"));
                mi_empresa.setResolucion(rs.getString("RESOLUCION.resolucion_facturacion"));
                mi_empresa.setUltimaFactura(numero_factura);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_frm_producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mi_empresa;
    }

    public Contructor_Cliente_Seleccionado llenarConstructorCliente(Object cc) {
        ResultSet rs = null;
        rs = llenarCliente(cc);
        try {
            while (rs.next()) {
                clientes.setNombre(rs.getObject("nombre_cliente"));
                clientes.setIdUsuario(rs.getObject("id_cliente"));
                clientes.setDireccion(rs.getObject("direccion_cliente"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
    public Contructor_Cliente_Seleccionado llenarConstructorClientePorId(Object id_cliente) {
        ResultSet rs = null;
        rs = llenarClientePorId(id_cliente);
        try {
            while (rs.next()) {
                clientes.setNombre(rs.getObject("nombre_cliente"));
                clientes.setApellido(rs.getObject("apellido_cliente"));
                clientes.setIdUsuario(rs.getObject("id_cliente"));
                clientes.setDireccion(rs.getObject("direccion_cliente"));
                clientes.setCodigo(rs.getObject("cedula_cliente"));
                clientes.setTelefono(rs.getObject("telefono_cliente"));
                clientes.setEmail(rs.getObject("mail_cliente"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public Contructor_CortesCaja corte_caja_totales(int id_sucursal) {
        ResultSet rs = null;

        rs = corteCaja.consultaTotalFacturacion(id_sucursal);
        float total = 0, totalEfectivo = 0, totalTarjetaCredito = 0, totalTarjetaDebito = 0, totalCheque = 0, totalTransac = 0, totalCreditoNota = 0;
        try {
            while (rs.next()) {
                total = total + rs.getFloat("FACT.total");
                if (rs.getString("FORM.forma_pago").equalsIgnoreCase("EFECTIVO")) {
                    totalEfectivo = totalEfectivo + rs.getFloat("FACT.total");
                }
                if (rs.getString("FORM.forma_pago").equalsIgnoreCase("TARJETA CREDITO")) {
                    totalTarjetaCredito = totalTarjetaCredito + rs.getFloat("FACT.total");
                }
                if (rs.getString("FORM.forma_pago").equalsIgnoreCase("TARJETA DEBITO")) {
                    totalTarjetaDebito = totalTarjetaDebito + rs.getFloat("FACT.total");
                }
                if (rs.getString("FORM.forma_pago").equalsIgnoreCase("CHEQUE")) {
                    totalCheque = totalCheque + rs.getFloat("FACT.total");
                }
                if (rs.getString("FORM.forma_pago").equalsIgnoreCase("TRANSACCION")) {
                    totalTransac = totalTransac + rs.getFloat("FACT.total");
                }
                if (rs.getString("FORM.forma_pago").equalsIgnoreCase("NOTA DE CREDITO")) {
                    totalCreditoNota = totalCreditoNota + rs.getFloat("FACT.total");
                }

            }
        } catch (SQLException ex) {
//            Logger.getLogger(Funciones_frm_CortesCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        corte_caja.setTotal(total);
        corte_caja.setTotal_cheque(totalCheque);
        corte_caja.setTotal_efectivo(totalEfectivo);
        corte_caja.setTotal_nota_credito(totalCreditoNota);
        corte_caja.setTotal_tarjeta_credito(totalTarjetaCredito);
        corte_caja.setTotal_tarjeta_debito(totalTarjetaDebito);
        corte_caja.setTotal_transaccion(totalTransac);
        return corte_caja;
    }

    public Contructor_CortesCaja importes_totales(int id_sucursal) {
        ResultSet rs = null;

        rs = corteCaja.consultaTotalImporte(id_sucursal);
        float total = 0, totalEgreso = 0, totalIngreso = 0, totalTarjetaDebito = 0, totalCheque = 0, totalTransac = 0, totalCreditoNota = 0;
        try {
            while (rs.next()) {
                if (rs.getString("id_tipo_importe").equalsIgnoreCase("1")) {
                    totalEgreso = totalEgreso + rs.getFloat("importe");
                }
                if (rs.getString("id_tipo_importe").equalsIgnoreCase("2")) {
                    totalIngreso = totalIngreso + rs.getFloat("importe");
                }

            }
        } catch (SQLException ex) {
//            Logger.getLogger(Funciones_frm_CortesCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        corte_caja.setTotal_importe_egreso(totalEgreso);
        corte_caja.setTotal_importe_ingreso(totalIngreso);
        return corte_caja;
    }

    public boolean registrarEfectivoInicio(int idSucursal, Object valor) {
        return registrarInicioEfectivo(idSucursal, valor);
    }

    public float efectivoInicial(Object id_sucursal) {
        return consultaEfectivoInicial(id_sucursal);
    }

    public int IdCorteInicial(Object id_sucursal) {
        return consultaIdCorteCajaEfectivoInicial(id_sucursal);
    }

    public Constructor_usuario_permiso permisosUsuario(int id_usuario) {

        int indice = 0, filas = 0;
        DA.consultas_usuario usu = new Funciones_frm_Usuario();
        rs = usu.usuario_permiso(id_usuario);
      
        try {
            while (rs.next()) {
                filas++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            int[] permi = new int[filas];
            rs.beforeFirst();
            while (rs.next()) {
                permi[indice] = rs.getInt("id_permiso");
                indice++;
            }
            permisos.setId_usuario(id_usuario);
            permisos.setId_permiso(permi);
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_Generales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return permisos;
    }
    
    public ArrayList llenarComboCiudad() 
    {
        try {
            rs = llenarComboCidad();
            return resultSetToArrayList(rs);
        } catch (SQLException ex) {
            Logger.getLogger(Funciones_Generales.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
    public ArrayList consultarImpuestosxTipo(int idSucursal, int idCliente , int tipoImpuesto)
    {
        try {
           rs = consultaImpuestosEmpresaCliente(idCliente, idSucursal, tipoImpuesto);
           return resultSetToArrayList(rs);
        } catch (Exception e) {
            return null;
        }
    }
    
    public ArrayList historialCorteCaja(int idSucursal)
    {
        try {
            return this.resultSetToArrayList(consultaHistorialCortesCaja(idSucursal));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
