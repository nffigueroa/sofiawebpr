/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('contrCajonDinero',function (traerMotivoTipoImporte,
                                            traerTipoImporte,
                                            insertarImporte,
                                            cerrarSesionS,
                                            titulos){
    var vm = this;
    titulos.ti(vm);
    vm.tiposImporte = [];
    vm.tiposMotivoImporte = [];
    vm.importeEnviar = [];
    traerTipoImporte.selectTipoImporte(vm);
    vm.eventoCambioItem = function () {
      traerMotivoTipoImporte.selectMotivoTipoImporte(vm);
    };
    vm.enviarImporteToBD = function () {
        insertarImporte.insertImporte(vm);
    };
    vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.usuario();
});

app.factory('traerTipoImporte', function ($http){
   var log = {};
   log.selectTipoImporte = function (vm){
     $http({
       url: 'Funciones_CajonDinero_BF',
       method: 'POST',
       data : {
          'accion': 1 // Consulta tipos de importe
       }
     }).success(function (result){
         vm.tiposImporte = result[0];
     });
   };
   return log;
});
app.factory('traerMotivoTipoImporte', function ($http){
   var log = {};
   log.selectMotivoTipoImporte = function (vm){
     $http({
       url: 'Funciones_CajonDinero_BF',
       method: 'POST',
       data : {
          'accion': 2, // Consulta tipos de importe
          'tipoImporte':    vm.importeEnviar.idTipoImporte
       }
     }).success(function (result){
         vm.tiposMotivoImporte = result[0];
     });
   };
   return log;
});

app.factory('insertarImporte', function ($http){
   var log = {};
   log.insertImporte = function (vm){
       $http({
            url : 'Funciones_CajonDinero_BF',
            method : 'POST',
            data : {
            'accion': 3 ,
            'tipoImporte':  vm.importeEnviar.idTipoImporte,
            'motivoImporte':    vm.importeEnviar.idMotivoTipoImporte,
            'importe':      vm.importeEnviar.importe,
            'descripcion' : vm.importeEnviar.descripcion,
            'idSucursal':   sessionStorage.getItem('idSucursal'),
            'idUsuario':    sessionStorage.getItem('idUsuario')
            }
       }).success(function () {
           delete (vm.importeEnviar);
           vm.importeEnviar = [];
           alert('Importe Registrado');
       });
      
   };
   return log;
});
