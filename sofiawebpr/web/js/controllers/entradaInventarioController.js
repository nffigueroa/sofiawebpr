/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('contrInventario', function (insertarProductoInventario,
        $http,
        traerInventario,
        traerProducto,
        traerProveedores,
        traerSucursalesEmpresa,
        modificarProductoInventario,
        agregarCantidadProductoInventario) {
    var vm = this;
    vm.aux1 = 'Entrada y Salida Inventario';
    vm.productos = [];
    vm.tablaInventario = [];
    vm.consecutivo;
    vm.producto = [];
    vm.productoModificar = [];
    vm.productoACargarMostrar = [];
    vm.productosACargar = {};
    vm.productosEnviarPedido = {};
    vm.productosEnviarPedido.nombreProducto = [];
    vm.productosEnviarPedido.presentacion = [];
    vm.productosEnviarPedido.marca = [];
    vm.productosEnviarPedido.medicion = [];
    vm.productosEnviarPedido.categoria = [];
    vm.productosEnviarPedido.stock = [];
    vm.productosEnviarPedido.cantidad = [];
    vm.productosEnviarPedido.iva = [];
    vm.productosEnviarPedido.expiracion = [];
    vm.productosEnviarPedido.barras = [];
    vm.productosEnviarPedido.precioVenta = [];
    vm.productosEnviarPedido.precioCompra = [];
    vm.productosEnviarPedido.sucursal = [];
    vm.productosEnviarPedido.proveedor = [];
    vm.productosEnviarPedido.idProducto = [];
    vm.mostrarProducto = [];
    vm.productoModificarCantidad = [];
    vm.comboProveedores = [];
    traerProveedores.selectProveedores(vm); //Llena combo proveedor
    vm.comboSucursal = [];
    traerSucursalesEmpresa.selectSucursal(vm);// Llenar combo sucursal
    //vm.ingresarProductoInventario = ingresarInventario();
    vm.llenarTablaInventarioEmpresa = traerInventario.selectInventario(vm); // Llena grilla de inventario empresa
    vm.llenartablaProducto = traerProducto.selectProducto(vm);
    vm.insertarProductoInventario = function () {
         insertarProductoInventario.addProductoInventario(vm, vm.productosEnviarPedido);       
    };
    vm.clicSeleccionarProducto = function (productoSeleccionado) {
        for (var i = 0; i < vm.productos.length; i++)
        {
            var comparar = vm.productos[i];
            if (comparar.id_produccto === productoSeleccionado)
            {
                var productoComparar = comparar;
            }
        }
        vm.producto.nombreProducto = productoComparar.nombre_producto;
        vm.producto.categoria = productoComparar.categoria;
        vm.producto.marca = productoComparar.marca;
        vm.producto.presentacion = productoComparar.presentacion;
        vm.producto.medicion = productoComparar.medicion;
        vm.producto.id_producto = productoComparar.id_produccto;
    };
    vm.actualizarTabla = function () {
        traerInventario.selectInventario(vm);
    };
    vm.agregarProductoACargar = function () {
        var ban = 0;
        for (var i = 0; i < vm.tablaInventario.length; i++)
        {
            var productoComparar = vm.tablaInventario[i];
            if (productoComparar.id_producto === vm.producto.id_producto)
            {
                // espacio para indicar el error de que el producto ya se encuentra agregado
                alert('Producto ya tiene existencias en el inventario');
                ban = 1;
            }
        }

        if (vm.productosEnviarPedido.idProducto.length === 0 && (ban === 0))
        {
            vm.mostrarProducto.push(vm.producto);
            vm.productosEnviarPedido.nombreProducto.push(vm.producto.nombreProducto);
            vm.productosEnviarPedido.presentacion.push(vm.producto.presentacion);
            vm.productosEnviarPedido.marca.push(vm.producto.marca);
            vm.productosEnviarPedido.medicion.push(vm.producto.medicion);
            vm.productosEnviarPedido.categoria.push(vm.producto.categoria);
            vm.productosEnviarPedido.stock.push(vm.producto.stock);
            vm.productosEnviarPedido.cantidad.push(vm.producto.cantidad);
            vm.productosEnviarPedido.iva.push(vm.producto.iva);
            vm.productosEnviarPedido.expiracion.push(vm.producto.expiracion);
            vm.productosEnviarPedido.barras.push(vm.producto.codigoBarras);
            vm.productosEnviarPedido.precioVenta.push(vm.producto.precioVenta);
            vm.productosEnviarPedido.precioCompra.push(vm.producto.precioCompra);
            vm.productosEnviarPedido.sucursal.push(vm.producto.sucursal);
            vm.productosEnviarPedido.proveedor.push(vm.producto.proveedor);
            vm.productosEnviarPedido.idProducto.push(vm.producto.id_producto);
            delete(vm.producto); // Eliminar instacia del proudcto seleccionado
            vm.producto = []; // Se crea una nueva instacia para reiniciar el $$haskeym b    


        }
        else
        {
            for (var j = 0; j < vm.productosEnviarPedido.idProducto.length; j++)
            {
                if ((vm.productosEnviarPedido.idProducto[j] === vm.producto.id_producto) && (ban === 0))
                {
                    // Espacio para error de que ya se encuentra agregado el producto
                    alert('Producto ya se encuentra en pedido');
                    ban = 1;
                }
            }
            if (ban === 0)
            {
                vm.mostrarProducto.push(vm.producto);
                vm.productosEnviarPedido.nombreProducto.push(vm.producto.nombreProducto);
                vm.productosEnviarPedido.presentacion.push(vm.producto.presentacion);
                vm.productosEnviarPedido.marca.push(vm.producto.marca);
                vm.productosEnviarPedido.medicion.push(vm.producto.medicion);
                vm.productosEnviarPedido.categoria.push(vm.producto.categoria);
                vm.productosEnviarPedido.stock.push(vm.producto.stock);
                vm.productosEnviarPedido.cantidad.push(vm.producto.cantidad);
                vm.productosEnviarPedido.iva.push(vm.producto.iva);
                vm.productosEnviarPedido.expiracion.push(vm.producto.expiracion);
                vm.productosEnviarPedido.barras.push(vm.producto.codigoBarras);
                vm.productosEnviarPedido.precioVenta.push(vm.producto.precioVenta);
                vm.productosEnviarPedido.precioCompra.push(vm.producto.precioCompra);
                vm.productosEnviarPedido.sucursal.push(vm.producto.sucursal);
                vm.productosEnviarPedido.proveedor.push(vm.producto.proveedor);
                vm.productosEnviarPedido.idProducto.push(vm.producto.id_producto);
                delete(vm.producto); // Eliminar instacia del proudcto seleccionado
                vm.producto = []; // Se crea una nueva instacia para reiniciar el $$haskeym b    
            }
        }


    };
    vm.modificarProductoSeleccionado = function () {
        modificarProductoInventario.updateProducto(vm.productoModificar);
    };
    vm.mostrarProductoModificar = function (idProducto) {
        for (var i = 0; i < vm.tablaInventario.length; i++)
        {
            var comparar = vm.tablaInventario[i];

            if (comparar.id_producto_inventario === idProducto)
            {
                vm.productoModificar = comparar;
            }
        }
    };
    vm.agregarCantidadProductoUnico = function (){
      agregarCantidadProductoInventario.addCantidadProducto(vm);
    };
    vm.cargarProductoModificarCantidad = function (idProducto) {
        for (var i = 0; i < vm.tablaInventario.length; i++)
        {
            var comparar = vm.tablaInventario[i];

            if (comparar.id_producto_inventario === idProducto)
            {
                vm.productoModificarCantidad = comparar;
            }
        }
    };
    

});

app.factory('traerInventario', function ($http) {
    var log = {};
    log.selectInventario = function (vm) {
        $http({
            method: 'POST',
            url: 'Funciones_EntradaInventario_BF',
            data: {
                'accion': 1,
                'idSucursal': sessionStorage.getItem("idSucursal")
            }
        }).success(function (result) {
            vm.tablaInventario = result[0];
        });
    };
    return log;
});

app.factory('traerProducto', function ($http) {
    var log = {};
    log.selectProducto = function (vm) {
        $http({
            method: 'POST',
            url: 'Funciones_GestionProducto',
            data: {
                'accion': 4,
                'idSucursal': sessionStorage.getItem("idSucursal")
            }
        }).success(function (result) {
            vm.productos = result[4];
        });
    };
    return log;
});

app.factory('traerProveedores', function ($http) {
    var log = {};
    log.selectProveedores = function (vm) {
        $http({
            url: 'Funciones_EntradaInventario_BF',
            method: 'POST',
            data: {
                'accion': 2
            }
        }).success(function (result) {
            vm.comboProveedores = result[0];
        });
    }
    return log;
});

app.factory('traerSucursalesEmpresa', function ($http) {
    var log = {};
    log.selectSucursal = function (vm) {
        $http({
            url: 'Funciones_EntradaInventario_BF',
            method: 'POST',
            data: {
                'accion': 3,
                'idSucursal': sessionStorage.getItem("idSucursal")
            }
        }).success(function (result) {
            vm.comboSucursal = result[0];
        });
    }
    return log;
});

app.factory('insertarProductoInventario', function ($http) {
    var log = {};
    log.addProductoInventario = function (vm, productoInventarioIngresar) {
        $http({
            url: 'Funciones_EntradaInventario_BF',
            method: 'POST',
            data: {
                'idSucursal': sessionStorage.getItem('idSucursal'),
                'idUsuario': sessionStorage.getItem('idUsuario'),
                'listadoProducto': productoInventarioIngresar,
//              'idProducto':             productoInventarioIngresar.id_producto,
//              'cantidad':               productoInventarioIngresar.cantidad,
//              'stock':                  productoInventarioIngresar.stock,
//              'sucursalSeleccionada':   productoInventarioIngresar.sucursal,
//              'proveedorSeleccionado':  productoInventarioIngresar.proveedor,
//              'barrasProducto':         productoInventarioIngresar.codigoBarras,
//              'precio1':                productoInventarioIngresar.precioVenta,
//              'precio2':                productoInventarioIngresar.precioCompra,
//              'iva':                    productoInventarioIngresar.iva,
//              'expiracion':             productoInventarioIngresar.expiracion,
//              'consecutivo':            productoInventarioIngresar.consecutivo,
                'accion': 5// Insertar Producto
            }
        }).success(function (result) {
            vm.consecutivo = result[0];
             delete(vm.mostrarProducto);
        delete(vm.productosEnviarPedido.nombreProducto);
        delete(vm.productosEnviarPedido.presentacion);
        delete(vm.productosEnviarPedido.marca);
        delete(vm.productosEnviarPedido.medicion);
        delete(vm.productosEnviarPedido.categoria);
        delete(vm.productosEnviarPedido.stock);
        delete(vm.productosEnviarPedido.cantidad);
        delete(vm.productosEnviarPedido.iva);
        delete(vm.productosEnviarPedido.expiracion);
        delete(vm.productosEnviarPedido.barras);
        delete(vm.productosEnviarPedido.precioVenta);
        delete(vm.productosEnviarPedido.precioCompra);
        delete(vm.productosEnviarPedido.sucursal);
        delete(vm.productosEnviarPedido.proveedor);
        delete(vm.productosEnviarPedido.idProducto);
        vm.productosEnviarPedido.nombreProducto = [];
        vm.productosEnviarPedido.presentacion = [];
        vm.productosEnviarPedido.marca = [];
        vm.productosEnviarPedido.medicion = [];
        vm.productosEnviarPedido.categoria = [];
        vm.productosEnviarPedido.stock = [];
        vm.productosEnviarPedido.cantidad = [];
        vm.productosEnviarPedido.iva = [];
        vm.productosEnviarPedido.expiracion = [];
        vm.productosEnviarPedido.barras = [];
        vm.productosEnviarPedido.precioVenta = [];
        vm.productosEnviarPedido.precioCompra = [];
        vm.productosEnviarPedido.sucursal = [];
        vm.productosEnviarPedido.proveedor = [];
        vm.productosEnviarPedido.idProducto = [];
        vm.mostrarProducto = [];
        alert('Pedido Agregado Correctamente Consecutivo: ' + vm.consecutivo);
        });
    };
    return log;
});

app.factory('traerConsecutivo', function ($http) {
    var log = {};
    log.selectConsecutivo = function (vm) {
        $http({
            url: 'Funciones_EntradaInventario_BF',
            method: 'POST',
            'accion': 5
        }).success(function (result) {
            vm.consecutivo = result[0];
        });
    };
    return log;
});

app.factory('elmininarProductoInventario', function ($http) {

});

app.factory('modificarProductoInventario', function ($http) {
    var log = {};
    log.updateProducto = function (productoModificar) {
        $http({
            url: 'Funciones_EntradaInventario_BF',
            method: 'POST',
            data: {
                'cantidad': productoModificar.cantidad_producto_inventario,
                'stock': productoModificar.stock_producto_inventario,
                'idSucursal': productoModificar.nombre_sucursal,
                'proveedor': productoModificar.empresa,
                'barras': productoModificar.barras_producto_inventario,
                'precio1': productoModificar.precio_producto_inventario,
                'precio2': productoModificar.precio_secundario_producto_inventario,
                'iva': productoModificar.iva_producto_inventario,
                'date': productoModificar.expiracion_producto_inventario,
                'idProductoInventario': productoModificar.id_producto_inventario,
                'idUsuario': sessionStorage.getItem('idUsuario'),
                'accion': 4 // Modificar Producto
            }
        });
    };
    return log;
});

app.factory('agregarCantidadProductoInventario', function ($http) {
var log  = {};
log.addCantidadProducto = function (vm) {
    $http({
       url : 'Funciones_EntradaInventario_BF',
       method : 'POST',
       data : {
           'idProductoInventario' : vm.productoModificarCantidad.id_producto_inventario,
           'cantidad':              vm.productoModificarCantidad.cantidad_producto_inventario,
           'idUsuario':              sessionStorage.getItem('idUsuario'),
           'cantidad2':             vm.productoModificarCantidad.cantidadNueva,
           'idSucursal':            sessionStorage.getItem('idSucursal'),
           'accion':                6 // agregar Cantidad Producto Unitario
       }
    }).success(function (result){
        vm.consecutivo = result[0];
        alert('Ingreso Correcto! Consecutivo : ' + vm.consecutivo);
    });
}
return log;
});

app.factory('descontarProductoInventario', function ($http) {

});


