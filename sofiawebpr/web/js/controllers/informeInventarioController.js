/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('contrInformesInventario', function (traerProductoStock,
                                                     traerMasVedido,
                                                     $filter,
                                                     traerEntradaSalidaInventario,
                                                     traerCoincidenciaProveedores) {
    var vm = this;
    vm.aux1 = 'INFORME STOCK'; 
    vm.idInventario;
    vm.htmlMensaje='';
    vm.tablaInventarioStock= [];
    vm.tablaMasVendido = [];
    vm.tablaSalidaProducto = [];
    vm.tablaEntradaProducto = [];
    vm.tablaCoincidenciasProveedor = [];
    var today = new Date();
    vm.fechaInicial = new Date(today.getTime() - (30 * 24 * 3600 * 1000));
    vm.fechaFinal= $filter('date')(new Date(),'yyyy-MM-dd') ;
    vm.fechaInicial = $filter('date')(vm.fechaInicial,'yyyy-MM-dd');
    vm.traerinformStock = function () {
        if(vm.tablaInventarioStock.length > 0)
        {}
        else
        {traerProductoStock.selectProductosStock(vm);}
    };
    vm.traerMasVendido = function () {
     
        traerMasVedido.selectMasVendido(vm);
    };
    vm.traerMovimientoProducto = function () {
      traerEntradaSalidaInventario.selectEntradaSalida(vm);  
    };
    vm.trarCoincidenciasInventario = function (idInventario){ 
        vm.idInventario= idInventario;
        traerCoincidenciaProveedores.selectCoincidencias(vm);
    };
});

app.factory('traerProductoStock', function ($http) {
    var log =  {};
    
    log.selectProductosStock = function (vm) {
      $http({
        url : 'Funciones_InformesInventario_BF',
        method : 'POST',
        data : {
            'accion': 1,
            'idSucursal': sessionStorage.getItem('idSucursal')
        }
      }).success(function (result){
          vm.tablaInventarioStock = result[0];
      });
    };
    return log;
});

app.factory('traerMasVedido', function ($http) {
    var log =  {};
    
    log.selectMasVendido = function (vm) {
      $http({
        url : 'Funciones_InformesInventario_BF',
        method : 'POST',
        data : {
            'accion': 2,
            'idSucursal': sessionStorage.getItem('idSucursal'),
            'fechaIni': vm.fechaInicial,
            'fechaFin': vm.fechaFinal
        }
      }).success(function (result){
          vm.tablaMasVendido = result[0];
      });
    };
    return log;
});

app.factory('traerEntradaSalidaInventario', function ($http) {
    var log =  {};
    
    log.selectEntradaSalida = function (vm) {
      $http({
        url : 'Funciones_InformesInventario_BF',
        method : 'POST',
        data : {
            'accion': 3,
            'idSucursal': sessionStorage.getItem('idSucursal'),
            'fechaIni': vm.fechaInicial,
            'fechaFin': vm.fechaFinal
        }
      }).success(function (result){
        vm.tablaSalidaProducto = result [1];
        vm.tablaEntradaProducto = result[0];
      });
    };
    return log;
});

app.factory('traerCoincidenciaProveedores', function ($http) {
   var log= {} ;
   log.selectCoincidencias = function (vm){
       $http({
          url: 'Funciones_InformesInventario_BF',
          method: 'POST',
          data : {
              'accion': 4,
              'idSucursal':sessionStorage.getItem('idSucursal'),
              'idInventario' : vm.idInventario
          }
       }).success(function (result)
       {
           vm.tablaCoincidenciasProveedor= result[0];
           if( vm.tablaCoincidenciasProveedor.length === 0) 
                vm.htmlMensaje = '<div class="alert alert-warning">No se han encontrado resultados</div>';
           else
                vm.htmlMensaje = '<div class="alert alert-success">Hemos Encontrado Proveedores</div>';           
       });
   };
   return log;
});
