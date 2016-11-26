/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import java.sql.ResultSet;

/**
 *
 * @author Nestor1
 */
public class consultas_cuentasPagar extends Conexion{
    
    private ResultSet rh = null;
    
    public ResultSet consultaProductosEnStok(int id_empresa)
    {
        
        return ejecutarSQLSelect("SELECT DISTINCT INVEN.id_producto_inventario,PRO.nombre_producto,INVEN.stock_producto_inventario,INVEN.cantidad_producto_inventario,"
                + "CAT.categoria,MED.medicion,PRE.presentacion,MARC.marca FROM producto AS PRO,producto_inventario AS INVEN,"
                + "categoria AS CAT,"
                + " medicion AS MED , presentacion AS PRE, sucursal AS SUC,mi_empresa AS EMPRE,marca AS MARC WHERE INVEN.id_producto=PRO.id_produccto AND "
                + "PRO.id_categoria = CAT.id_categoria AND PRO.id_presentacion = PRE.id_presentacion AND MED.id_medicion=PRO.id_medicion "
                + "AND PRO.id_marca=MARC.id_marca AND INVEN.cantidad_producto_inventario<INVEN.stock_producto_inventario "
                + "AND INVEN.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+"");
    }
    
}
