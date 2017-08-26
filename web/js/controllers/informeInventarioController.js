/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('contrInformesInventario', function (traerProductoStock,
                                                     traerMasVedido,
                                                     $filter,
                                                     traerEntradaSalidaInventario,
                                                     traerCoincidenciaProveedores,
                                                     cerrarSesionS,
                                                     titulos) {
    var vm = this;
    titulos.ti(vm);
    vm.aux1 = 'INFORME STOCK'; 
    vm.idInventario;
    vm.htmlMensaje='';
     vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.usuario();
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

app.factory('Excel',function($window){
		var uri='data:application/vnd.ms-excel;base64,',
			template='<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
			base64=function(s){return $window.btoa(unescape(encodeURIComponent(s)));},
			format=function(s,c){return s.replace(/{(\w+)}/g,function(m,p){return c[p];})};
		return {
			tableToExcel:function(tableId,worksheetName){
				var table=$(tableId),
					ctx={worksheet:worksheetName,table:table.html()},
					href=uri+base64(format(template,ctx));
				return href;
			}
		};
	})
	.controller('MyCtrl',function(Excel,$timeout){
	  $scope.exportToExcel=function(tableId){ // ex: '#my-table'
            var exportHref=Excel.tableToExcel(tableId,'sheet name');
            $timeout(function(){location.href=exportHref;},100); // trigger download
        }
	});
