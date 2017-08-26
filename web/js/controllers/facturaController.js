/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('contrFactura', function (cerrarSesionS,
                                            traerFechainicialFacturacion,
                                            $filter,
                                            traerDetalleFactura,
                                            anularFactura,
                                            traerMotivoCombo,
                                            titulos,
                                            traerMotivosAnulacion,traerFacturasSinAnular) {
    var vm = this;
    titulos.ti(vm);
    vm.tablaFacturaSinAnular = [];
    vm.fechaInicial = '';
    var date = $filter('date')(new Date(),'yyyy-MM-dd') ;
    vm.fechaHasta = '';
    vm.detalleFactura = [];
    vm.comboMotivoEliminacion = [];
    vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.usuario();
    vm.idFactura;
    vm.facturaAnular = [];
    vm.ban = true;
   // traerFechainicialFacturacion.selectFechaInicial(vm);
    vm.facturaDetalleTraer = function (idFactura){
        vm.idFactura = idFactura;
        traerDetalleFactura.selectDetalleFactura(vm);
    };
    vm.anularFacturaSeleccion = function (){
        anularFactura.insertanularFactura(vm);
    };
    vm.mostrarFacturaAnular = function (idFactura) {
        for (var i = 0; i < vm.tablaFacturaSinAnular.length; i++)
        {
            var comparar = vm.tablaFacturaSinAnular[i];

            if (comparar.id_factura === idFactura)
            {
                vm.facturaAnular = comparar;
            }
        }
        vm.llenarMotivosAnulacion();
    };
    vm.llenarMotivosAnulacion = function () {
        traerMotivosAnulacion.selectMotivosAnulacion(vm);
    }
    traerFacturasSinAnular.selectFacturaSinAnular(vm);
});

app.factory('traerFechainicialFacturacion', function ($http,traerFacturasSinAnular){
    var log = {};
    log.selectFechaInicial = function (vm) {
      $http({
          url : 'Funciones_Factura_BF',
          method : 'POST',
          data : {
              'accion': 2,
              'idSucursal': sessionStorage.getItem('idSucursal')
          }
      }).success(function (result){
          vm.fechaInicial = result [0];
      });  
    };
    return log;
});

app.factory('traerFacturasSinAnular',function ($http){
   var log = {};
   
   log.selectFacturaSinAnular = function(vm) {
       $http({
           url: 'Funciones_Factura_BF',
           method: 'POST',
           data: {
               'accion':    1,
               'idSucursal': sessionStorage.getItem('idSucursal'),
               'fechaInicio': vm.fechaInicial,
               'fechaHasta': vm.fechaHasta,
               'ban': vm.ban
           }
       }).success(function (result) {
           vm.tablaFacturaSinAnular = result[0];
       });
   };
   return log;
});

app.factory('formatDate', function (){
    var log = {};
    log.formatearDate = function (date) {
        $filter('date')(date,'yyyy-mm-dd') ;
    };
   return log;
});

app.factory('traerDetalleFactura', function ($http) {
   var log = {};
   log.selectDetalleFactura = function (vm){
       $http({
           url: 'Funciones_Factura_BF',
           method: 'POST',
           data : {
               'accion':    3,
               'idFactura': vm.idFactura
           }
       }).success(function (result){
           vm.detalleFactura = result[0];
       });
   }
   return log;
});

app.factory('anularFactura', function ($http) {
    var log = {};
    log.insertanularFactura = function (vm){
      $http({
          method : 'POST',
          url : 'Funciones_Factura_BF',
          data : {
              'accion': 4,
              'idFactura': vm.facturaAnular.id_factura,
              'idMotivo':   vm.facturaAnular.idMotivo,
              'idUsuario':  sessionStorage.getItem('idUsuario')
          }
      }).success(function (){
          alert('Anulada Correctamente');
      });
    };
    return log;
});

app.factory('traerMotivosAnulacion', function ($http){
   var log = {};
   log.selectMotivosAnulacion = function (vm){
       $http({
          url : 'Funciones_Factura_BF',
          method : 'POST',
          data : {
              'accion': 6 // Traer Motivos anulacion
          }
       }).success(function (result) {
           vm.comboMotivoEliminacion = result[0];
       });
   };
   return log;
});