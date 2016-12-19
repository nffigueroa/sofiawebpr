/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('contrInformesInventario', function (traerProductoStock,
                                                     traerMasVedido,$filter) {
    var vm = this;
    vm.aux1 = 'INFORME STOCK'; 
    vm.tablaInventarioStock= [];
    vm.tablaMasVendido = [];
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