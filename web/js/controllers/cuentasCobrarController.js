/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('contrCuentasCobrar', function (traerCuentasCobrar,
                                                traerDetalleCredito,
                                                traerDatosCredito,
                                                insertarAbono,
                                                cerrarSesionS,
                                                titulos) {
   var vm = this;
   titulos.ti(vm);
   vm.tablaCuentasCobrar= [];
   vm.detalleCuentacobrar = [];
   vm.aux1= 'CUENTAS POR COBRAR';
    vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.usuario();
   vm.nomreApellidoCliente;
   vm.cedulaCliente;
   vm.totalCredito;
   vm.cuotaMensual;
   vm.estadoCredito;
   vm.cuotasPendientes;
   vm.numeroCuota;
   traerCuentasCobrar.selectCuentasCobrar(vm);
   vm.mostrarHistorialCredito = function (idCredito) {
       vm.idCredito= idCredito;
       traerDetalleCredito.selectDetalleCredito(vm);
   };
   vm.mostrarDatosCredito = function (idCliente,idCredito){
       vm.idCliente = idCliente;
       vm.idCredito = idCredito;
       traerDatosCredito.selectDatosCredito(vm);
   };
   vm.calcularCambio = function (event) {
       if(event.which  === 13)
       {
          vm.calcularCambioEfectivo(vm);
       }
   };
   vm.calcularCambioEfectivo = function (vm)
   {
       var diferencia =vm.recibe -vm.cuotaMensual ;
       vm.diferenciaCambio = diferencia;
       
   };
   vm.addAbono = function () {
     insertarAbono.insertAbono(vm);
   };
});

app.factory('traerCuentasCobrar', function ($http) {
   var log = {};
   log.selectCuentasCobrar = function (vm) {
       $http({
          url : 'Funciones_CuentasCobrar_BF',
          method:   'POST',
          data : {
              'accion': 1,
              'idSucursal': sessionStorage.getItem('idSucursal')
          }
       }).success(function (result) {
           vm.tablaCuentasCobrar = result [0];
       });
   };
   return log;
});

app.factory('traerDetalleCredito', function ($http) {
    var log = {};
    log.selectDetalleCredito = function (vm){
        $http({
           url:'Funciones_CuentasCobrar_BF',
           method : 'POST',
           data :
                   {
                       'accion': 2,
                       'idCredito': vm.idCredito
                   }
        }).success(function (result){
            vm.detalleCuentaCobrar = result [0];
        });
    };
   return log; 
});

app.factory('traerDatosCredito', function ($http) {
    var log = {};
    log.selectDatosCredito = function (vm){
        $http({
           url:'Funciones_CuentasCobrar_BF',
           method : 'POST',
           data :
                   {
                       'accion': 3,
                       'idCliente': vm.idCliente,
                       'idCredito': vm.idCredito
                   }
        }).success(function (result){
            vm.nomreApellidoCliente  = result[0];
            vm.cedulaCliente = result[1];
            vm.totalCredito = result [2];
            vm.cuotaMensual= result[3];
            vm.estadoCredito = result [4];
            vm.cuotasPendientes = result[5];
            vm.numeroCuota = result [6];
            vm.siguienteCuota = result [7];
        });
    };
   return log; 
});

app.factory('insertarAbono', function ($http) {
   var log = {};
   log.insertAbono = function (vm) {
     $http({
       url:'Funciones_CuentasCobrar_BF',
       method : 'POST',
       data : {
          'accion' : 4,
          'idCredito' : vm.idCredito,
          'idCuota': vm.siguienteCuota,
          'recibe': vm.recibe,
          'cambioAbono': vm.diferenciaCambio,
          'idUsuario': sessionStorage.getItem('idUsuario'),
          'cuotasPendientes': vm.cuotasPendientes
       }
     }).success(function () {
         alert('Abono registrado Correctamente!');
     });
   };
   return log;
});