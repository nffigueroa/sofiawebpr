/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('contrCorteCaja', function (traerFacturaDetalle,
                                            traerTablaImportes,
                                            traerTotalesCorteCaja,
                                            insertarCorteCaja,
                                            cerrarSesionS,
                                            titulos) {
   var vm = this; 
   titulos.ti(vm);
   vm.aux1= 'Corte Caja';
   vm.datosCorteCaja = [];
   vm.tablaFacturaDetalle = [];
   vm.tablaImportes = [];
    vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.usuario();
   vm.factura = {};
   vm.importe = {};
   vm.factura.apellido_cliente = [];
   vm.factura.cambio = [];
   vm.factura.cedula_cliente = [];
   vm.factura.descripcion=[];
   vm.factura.descuento= [];
   vm.factura.digitos_tarjeta = [];
   vm.factura.direccion_cliente=[];
   vm.factura.fecha_creacion = [];
   vm.factura.folio = [];
   vm.factura.forma_pago = [];
   vm.factura.id_cliente = [];
   vm.factura.id_corte_caja =[];
   vm.factura.id_factura=[];
   vm.factura.id_forma_pago=[];
   vm.factura.id_sucursal = [];
   vm.factura.iva = [];
   vm.factura.nombre_cliente = [];
   vm.factura.recibe = [];
   vm.factura.subtotal = [];
   vm.factura.total= [];
   vm.importe.descripcion_importe = [];
   vm.importe.id_corte_caja = [];
   vm.importe.id_importe = [];
   vm.importe.id_motivo_importe = [];
   vm.importe.id_sucursal = [];
   vm.importe.id_tipo_importe = [];
   vm.importe.importe = []
   vm.importe.motivo_importe = [];
   vm.importe.tipo_importe = [];
   traerFacturaDetalle.selectFacturasDetalle(vm); // Llenado de tabla factura detalle
   traerTablaImportes.selectImportes(vm);
   traerTotalesCorteCaja.selectTotales(vm);
   vm.calcularEfectivoFaltante = function () {
       vm.datosCorteCaja.efectivoFaltante = vm.datosCorteCaja.CantidadRelCaja- vm.datosCorteCaja[2]
   };
   vm.eventoPress = function (event) {
       if(event.which  === 13)
       {
          vm.calcularEfectivoFaltante();
       }
   };
   vm.ingresarCorteCaja = function () {
     for(var i = 0 ; i < vm.tablaFacturaDetalle.length ; i ++)
     {
        var comparar= vm.tablaFacturaDetalle[i];
        vm.factura.apellido_cliente.push(comparar.apellido_cliente);
        vm.factura.cambio.push(comparar.cambio);
        vm.factura.cedula_cliente.push(comparar.cedula_cliente);
        vm.factura.descripcion.push(comparar.descripcion);
        vm.factura.descuento.push(comparar.descuento);
        vm.factura.digitos_tarjeta.push(comparar.digitos_tarjeta);
        vm.factura.direccion_cliente.push(comparar.direccion_cliente);
        vm.factura.fecha_creacion.push(comparar.fecha_creacion);
        vm.factura.folio.push(comparar.folio);
        vm.factura.forma_pago.push(comparar.forma_pago);
        vm.factura.id_cliente.push(comparar.id_cliente);
        vm.factura.id_corte_caja.push(comparar.id_corte_caja);
        vm.factura.id_factura.push(comparar.id_factura);
        vm.factura.id_forma_pago.push(comparar.id_forma_pago);
        vm.factura.id_sucursal.push(comparar.id_sucursal);
        vm.factura.iva.push(comparar.iva);
        vm.factura.nombre_cliente.push(comparar.nombre_cliente);
        vm.factura.recibe.push(comparar.recibe);
        vm.factura.subtotal.push(comparar.subtotal);
        vm.factura.total.push(comparar.total);
     }
     for(var j = 0 ; j < vm.tablaImportes.length; j ++)
     {
        var comparar2 = vm.tablaImportes[j];
        vm.importe.descripcion_importe.push(comparar2.descripcion_importe);
        vm.importe.id_corte_caja.push(comparar2.id_corte_caja);
        vm.importe.id_importe.push(comparar2.id_importe);
        vm.importe.id_motivo_importe.push(comparar2.id_motivo_importe);
        vm.importe.id_sucursal.push(comparar2.id_sucursal);
        vm.importe.id_tipo_importe.push(comparar2.id_tipo_importe);
        vm.importe.importe.push(comparar2.importe);
        vm.importe.motivo_importe.push(comparar2.motivo_importe);
        vm.importe.tipo_importe.push(comparar2.tipo_importe);
     }
     insertarCorteCaja.insertCorteCaja(vm);  
   };
});

app.factory ('traerFacturaDetalle', function ($http){
    var log = {};
    log.selectFacturasDetalle = function (vm) {
        $http({
            url : 'Funciones_CorteCaja_BF',
            method: 'POST',
            data: {
                'accion': 1,
                'idSucursal' :  sessionStorage.getItem('idSucursal')
            }
        }).success(function (result) {
            vm.tablaFacturaDetalle = result[0];
        });
    };
    return log;
});

app.factory ('traerTablaImportes', function ($http){
    var log = {};
    log.selectImportes= function (vm) {
        $http({
            url : 'Funciones_CorteCaja_BF',
            method: 'POST',
            data: {
                'accion': 2,
                'idSucursal' :  sessionStorage.getItem('idSucursal')
            }
        }).success(function (result) {
            vm.tablaImportes = result[0];
        });
    };
    return log;
});

app.factory ('traerTotalesCorteCaja', function ($http){
    var log = {};
    log.selectTotales= function (vm) {
        $http({
            url : 'Funciones_CorteCaja_BF',
            method: 'POST',
            data: {
                'accion': 3,
                'idSucursal' :  sessionStorage.getItem('idSucursal')
            }
        }).success(function (result) {
            vm.datosCorteCaja = result[0];
        });
    };
    return log;
});

app.factory('insertarCorteCaja', function ($http) {
   var log = {};
   log.insertCorteCaja = function (vm) {
       $http({
           method : 'POST',
           url: 'Funciones_CorteCaja_BF',
           data :{
               'accion': 4,
               'efectivoReal':  vm.datosCorteCaja.CantidadRelCaja,
               'facturas':  vm.factura,
               'importes':  vm.importe,
               'otrosIngresos': vm.datosCorteCaja[4],
               'totalVentas':   vm.datosCorteCaja[0],
               'transacciones': vm.datosCorteCaja[8],
               'efectivoInicial': vm.datosCorteCaja[9],
               'credito':       vm.datosCorteCaja[5],
               'otrosEgresos':   vm.datosCorteCaja[3],
               'tarjetaCredito':    vm.datosCorteCaja[6],
               'cheques':   vm.datosCorteCaja[1],
               'efectivoTotal': vm.datosCorteCaja[2],
               'tarjetaDebito': vm.datosCorteCaja[7],
               'idSucursal' : sessionStorage.getItem('idSucursal'),
               'idUsuario':   sessionStorage.getItem('idUsuario')
           }
       }).success(function (result){
           
       });
   };
   return log;
});


