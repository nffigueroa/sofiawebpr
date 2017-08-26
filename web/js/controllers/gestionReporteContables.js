/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('ctrContableInforme',function ($scope,trarVentasDiariasXEmpresa,
trarVentasDiariasXSucursal,cerrarSesionS,traerSucursalesXEmpresa,
ventasMensualesInforme,facturasAnuladasReporte,corteCajaGestion,inventarioGanancia,traerFacturasSinAnular) {
    var vm = $scope;
    var vmt = this;
    //#region Variables
    vm.DaiylySalesByCompany = [];
    vm.DaiylySalesBySucursal = [];
    vm.rangoFechas = [];
    vm.rangoValores = [];
    vmt.sucursales = [];
    vmt.fechaInicial = '';
    vmt.fechaHasta = '';
    vmt.tipoGrafico='chart-line';
    vmt.facturasAnuladas= [];
    vmt.historialCorte=[];
    vmt.funcionProductoMasGanacia = [];
    vmt.funcionCuantoDineroInventario = [];
    vmt.funcionCuantoGanaciaInventario = [];
    vmt.totalInversionInventario = [];
    vmt.inventarioGanancias= []; 
    vmt.tablaFacturaSinAnular=[];
    //#endregion
    vmt.llenarVentasSucursal  = function() {
        trarVentasDiariasXEmpresa.getDailySalesByCompany(vm,vmt);
    };
     // Rango de fechas de 31 dias
    vmt.usuario = function () {
        vmt.user = sessionStorage.getItem("nombreReal");
    };
    vmt.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    };
    vmt.actualizarGrafico = function()
    {
        trarVentasDiariasXEmpresa.getDailySalesByCompany(vm,vmt);
    };
    vmt.llenarComboSucursales = function ()
    {
      traerSucursalesXEmpresa.getSucursales(vmt);  
    };
    vmt.sucursalChanged = function ()
    {
        trarVentasDiariasXSucursal.getDailySalesBySucursal(vmt,vm);
    }
    vmt.llenarVentasMensualesXComapany = function () {
        ventasMensualesInforme.getMonthlySalesByCompany(vm,vmt);
    };
    vmt.llenarVentasMensualesXSucursal = function () {
        ventasMensualesInforme.getMonthlySalesBySucursal(vm,vmt);
    };
    vmt.llenarListarFacturaAnulada = function () {
        facturasAnuladasReporte.traerFacturasAnuladas(vmt);
    };
    vmt.llenarHistorialCorteCaja = function () {
        corteCajaGestion.getHistorialCorte(vmt);
    };
    vmt.llenarGananciasInventario = function (){
      inventarioGanancia.getGananciasInventario(vmt);  
    };
    vmt.llenarFacturaSinAnularGanancia = function (){
      vmt.ban=false;
      traerFacturasSinAnular.selectFacturaSinAnular(vmt);  
    };
    vmt.llenarComboSucursales();
    vmt.usuario();
    vmt.llenarVentasSucursal();
    
    
});

app.factory('trarVentasDiariasXEmpresa', function ($http){
    var log = {};
    log.getDailySalesByCompany= function (vm,vmt)
    {
        $http({
                method : 'POST',
                url:   'Funciones_InformeContable',
                data :  {
                    'idSucursal'    : sessionStorage.getItem('idSucursal'),
                    'fechaIni'      : vmt.fechaInicial,
                    'fechaHasta'    : vmt.fechaHasta,
                    'accion':       1
                } 
                    }).success(function (result)
                    {
                        vm.DaiylySalesByCompany = result [0];
                        var totales = [];
                        var fecha = [];
                        for(var i = 0 ; i < vm.DaiylySalesByCompany.length ; i ++)
                        {
                            var aux = vm.DaiylySalesByCompany[i];
                            totales.push(aux["total"]);
                            fecha.push(aux["fecha_creacion"]);
                        }
                        vm.labels = fecha;
                        vm.data = totales;
                        vm.series= ["Serie 1"];
                    });
    };
    return log;
});

app.factory('trarVentasDiariasXSucursal', function ($http){
    var log = {};
    log.getDailySalesBySucursal= function (vmt,vm)
    {
        $http({
                method : 'POST',
                url:   'Funciones_InformeContable',
                data :  {
                    'idSucursal' : vmt.sucursalesSeleccionado,
                    'fechaIni'      : vmt.fechaInicial,
                    'fechaHasta'    : vmt.fechaHasta,
                    'accion':       2
                } 
                    }).success(function (result)
                    {
                        vm.DaiylySalesBySucursal = result [0];
                        var totales = [];
                        var fecha = [];
                        for(var i = 0 ; i < vm.DaiylySalesBySucursal.length ; i ++)
                        {
                            var aux = vm.DaiylySalesBySucursal[i];
                            totales.push(aux["total"]);
                            fecha.push(aux["fecha_creacion"]);
                        }
                        vm.labels = fecha;
                        vm.data = totales;
                        vm.series= ["Serie 1"];
                    });
    };
    return log;
});

app.factory('traerSucursalesXEmpresa', function ($http){
   var log = {};
   log.getSucursales = function (vmt){
       $http({
                url: 'Funciones_InformeContable',
                method: 'POST',
                data: {
                    'accion': 3,
                    'idSucursal' : sessionStorage.getItem('idSucursal')
                }
                    }).success(function (result){
                        vmt.sucursales = result[0];
                    });
   };
   return log;
});

app.factory('ventasMensualesInforme',function ($http){
    var log = {};
    log.getMonthlySalesByCompany = function (vm,vmt){
      $http({
          url: 'Funciones_InformeContable',
          method: 'POST',
          data : {
              'idSucursal' : sessionStorage.getItem('idSucursal'),
              'fechaIni':    vmt.fechaInicial,
              'fechaHasta':  vmt.fechaHasta,
              'accion':     4
          }
      }).success(function (result) {
            vmt.ventasMensuales = result[0];
            var totales = [];
            var fecha = [];
            for(var i = 0 ; i < vmt.ventasMensuales.length ; i ++)
            {
                var aux = vmt.ventasMensuales[i];
                totales.push(aux["total"]);
                fecha.push(aux["MESespanol"]);
            }
            vm.labels = fecha;
            vm.data = totales;
            vm.series= ["Serie 1"];
      });  
    };
    log.getMonthlySalesBySucursal = function (vm,vmt){
        $http({
           url: 'Funciones_InformeContable',
           method: 'POST',
           data: {
               'idSucursal': vmt.sucursalSelected,
               'fechaIni':    vmt.fechaInicial,
               'fechaHasta':  vmt.fechaHasta,
               'accion':    5
           }
        }).success(function(result){
            vmt.ventasMensualesXSucursal= result[0];
        });
    };
    return log;
});

app.factory('facturasAnuladasReporte', function ($http){
   var log = {};
   log.traerFacturasAnuladas = function (vmt){
       $http({
           method:  'POST',
           url: 'Funciones_Factura_BF',
           data:{
               'accion' :   5,
               'idSucursal':    sessionStorage.getItem("idSucursal")
           }
       }).success(function (result){
           vmt.facturasAnuladas = result[0];
       });
   };
   return log;
});

app.factory('corteCajaGestion', function ($http){
    var log = {};
    log.getHistorialCorte = function (vmt){
        $http({
            url:'Funciones_InformeContable',
            method:'POST',
            data: {
                'accion': 5,
                'idSucursal': sessionStorage.getItem('idSucursal')
            }
        }).success(function (result){
            vmt.historialCorte = result[0];
        });
    };
    return log;
});

app.factory('inventarioGanancia', function($http) {
    var log = {};
    log.getGananciasInventario = function (vmt){
            $http({
               url: 'Funciones_InformeContable',
               method: 'POST',
               data:{
                'accion' : 6,
                'idSucursal': sessionStorage.getItem("idSucursal")
               }
            }).success(function (result){
                vmt.funcionProductoMasGanacia = result[0];
                vmt.funcionCuantoDineroInventario = result[1];
                vmt.funcionCuantoGanaciaInventario = result[2];
                vmt.totalInversionInventario = result[3];
                vmt.inventarioGanancias = result[4];
            });
    };
    return log;
});
