/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('contrCli', function (cerrarSesionS,traerClientes, envioClie, eliminarCli, llenarComboCiudad,
updateClie,traerImpuestoXCliente,updateImpuestosCliente,titulos) {
    var vm = this;
    titulos.ti(vm);
    vm.aux1 = "AGREGAR CLIENTE";
    vm.textoNombre = 'Nombre';
    vm.textoIdentificacion = 'Ide';
    vm.placeholder='Identificacion';
    vm.declaraIva ;
    vm.ciudades = llenarComboCiudad.consultaLlenarComboCiudad(vm);
    vm.currentPage = 0;
    vm.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página
    vm.pages = [];
    vm.eliminar = "Eliminar";
    vm.elim = [];
    vm.clienteModificar = [];
    vm.tablaClientes = [];
    vm.tipoCLiente;
    vm.impuestoCliente = [];
    vm.dinamicoTextos = function (parametro) {
      if(parametro === 0)
      {
          vm.textoNombre = 'Nombre';
          vm.textoIdentificacion = 'Ide';
          vm.placeholder = 'Identificacion';
          vm.parametro= 1;
      }
      else
      {
          vm.textoNombre = 'Ra.Soci ';
          vm.placeholder = 'Razon Social';
          vm.textoIdentificacion = 'Nit ';
          vm.parametro= 2;
      }
    };
     vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.usuario();
    vm.mostrarCliente = function (idCliente)
    {
        for (var i = 0; i < vm.tablaClientes.length; i++)
        {
            var comparar = vm.tablaClientes[i];

            if (comparar.id_cliente === idCliente)
            {
                vm.clienteModificar = comparar;
            }
        }
        vm.idCliente = vm.clienteModificar.id_cliente;
        if(vm.clienteModificar.EsJuridico == null)
            vm.tipoCLienteModificar = 0;
        else
            vm.tipoCLienteModificar = 1;
        traerImpuestoXCliente.traerImpuesto(vm);
        vm.dinamicoTextos(vm.tipoCLienteModificar);
        
    };
    traerClientes.llenarTablacliente(vm);
    vm.actualizar = {};
    vm.actualizar = function ()
    {
        traerClientes.llenarTablacliente(vm);
    };
    vm.saveCliente = function () {
        envioClie.envioCliente(vm.cliente,vm.tipoCLiente);
    };
    vm.deleteClie = function (idPr) {
        if (confirm("Desea eliminar Cliente?"))
        {
            for (var i = 0; i < vm.tablaClientes.length; i++)
            {
                var comparar = vm.tablaClientes[i];
                if (comparar.id_cliente === idPr)
                {
                    var aux2 = vm.tablaClientes[i];
                    var indexFila = i;
                }
            }
            eliminarCli.DeleteClie(aux2.id_cliente);
            alert("Cliente Elimiando Correctamente!");
            vm.tablaClientes.splice(indexFila, 1);
        }
    };
    vm.modificarCliente = function (clienteModificar) {
        if (confirm("Guardar Cambios?"))
        {
            for (var i = 0; i < vm.tablaClientes.length; i++)
            {
                var comparar = vm.tablaClientes[i];
                vm.clienteModificar = comparar;
                if (comparar.id_cliente === clienteModificar)
                {
                    var aux2 = vm.tablaClientes[i];
                   // var indexFila = i;
                    i = vm.tablaClientes.length;
                }
            }
            updateClie.updateCliente(aux2);
            if(vm.clienteModificar.tarifaIca == null)
                vm.clienteModificar.tarifaIca = 0
            updateImpuestosCliente.updateImpuestos(vm);
            //vm.tablaClientes.indexOf(indexFila).push(aux2);
        }
    };
    vm.configPages = function () {
        vm.pages.length = 0;
        var ini = vm.currentPage - 4;
        var fin = vm.currentPage + 5;
        if (ini < 1) {
            ini = 1;
            if (Math.ceil(vm.tablaClientes.length / vm.pageSize) > 10)
                fin = 10;
            else
                fin = Math.ceil(vm.tablaClientes.length / vm.pageSize);
        } else {
            if (ini >= Math.ceil(vm.tablaClientes.length / vm.pageSize) - 10) {
                ini = Math.ceil(vm.tablaClientes.length / vm.pageSize) - 10;
                fin = Math.ceil(vm.tablaClientes.length / vm.pageSize);
            }
        }
        if (ini < 1)
            ini = 1;
        for (var i = ini; i <= fin; i++) {
            vm.pages.push({no: i});
        }
        if (vm.currentPage >= vm.pages.length)
            vm.currentPage = vm.pages.length - 1;
    };
    vm.setPage = function (index) {
        vm.currentPage = index - 1;
    };
    vm.configPages();
});
app.factory('traerClientes', function ($http) {
    var log2 = {};
    log2.llenarTablacliente = function (vm) {
        $http({
            url: 'Funciones_Cliente',
            method : 'POST',
            data : {
                'idSucursal' : sessionStorage.getItem("idSucursal"),
                'accion':      4 // Consulta de resultados
            }
        }).success(function (result){
             vm.tablaClientes=  (result[0]);
        });
    };
    return log2;
});
app.factory('envioClie', function ($http) {
    var envio = {};

    envio.envioCliente = function (Cliente,tipoCliente) {
        $http({
            url: "Funciones_Cliente",
            method: "POST",
            data: {
                'idUsuario': sessionStorage.getItem("idUsuario"),
                'idSucursal': sessionStorage.getItem("idSucursal"),
                'nombreCliente': Cliente.nombreCliente,
                'apellidoCliente': Cliente.apellidoCliente,
                'telefonoCliente': Cliente.telefonoCliente,
                'direccionCliente': Cliente.direccionCliente,
                'emailCliente': Cliente.emailCliente,
                'idCiudad': Cliente.idCiudad,
                'iden': Cliente.identificacionCliente,
                'tipoCliente':  tipoCliente,
                'declaraIva':   Cliente.declaraIva,
                'declaraIca':   Cliente.reteIca,
                'reteFuente':   Cliente.reteFuente,
                'milesIca'  :   Cliente.tarifaIca,
                'dv':           Cliente.dv,
                'accion': 1 // Insertar 
            }
        }).then(function (response) {

            if (response.data.resultado === "si")
            {
                alert("Cliente Guardado Correctamente!");
            }
            else
            {
                alert("Ups! , Ocurrio algo que no debia");
            }
        });
    };
    return envio;
});
app.factory('eliminarCli', function ($http) {
    var funcion = {};
    funcion.DeleteClie = function (idCliente) {
        $http({
            method: "POST",
            url: "Funciones_Cliente",
            data: {
                'idCliente': idCliente,
                'idUsuario': sessionStorage.getItem("idUsuario"),
                'accion': 2 //Eliminar
            }
        });
    };
    return funcion;
});
app.factory('updateClie', function ($http) {
    var modificar = {};
    modificar.updateCliente = function (Cliente) {
        $http({
            url: 'Funciones_Cliente',
            method: 'POST',
            data: {
                'idCliente': Cliente.id_cliente,
                'idUsuarioMod': sessionStorage.getItem("idUsuario"),
                'nombreCliente': Cliente.nombre_cliente,
                'apellidoCliente': Cliente.apellido_cliente,
                'emailCliente': Cliente.mail_cliente,
                'direccionCliente': Cliente.direccion_cliente,
                'telefonoCliente': Cliente.telefono_cliente,
                'idCiudad': Cliente.ciudad,
                'accion': 3 //Modificar
            }
        });
    };
    return modificar;
});

app.factory('traerImpuestoXCliente', function ($http) {
    var factory = {};
    factory.traerImpuesto = function (vm) {
        $http({
            url: 'Funciones_Cliente',
            method: 'POST',
            data: {
                'accion': 5,  // Accion para traer los impuestos
                'idCliente': vm.idCliente
            }
        }).success(function (result){
            vm.impuestoCliente = result[0];
            for (var k = 0 ; k < vm.impuestoCliente.length ; k++)
        {
            var compararImpuesto = vm.impuestoCliente[k];
            if(compararImpuesto.impuesto === "RETEFUENTE")
            { 
                if(compararImpuesto.activo === 1)
                    vm.clienteModificar.reteFuente = 1;
                else
                    vm.clienteModificar.reteFuente = 0;
            }
            if(compararImpuesto.impuesto === "RETEICA")
            {
                if(compararImpuesto.activo === 1)
                    vm.clienteModificar.reteIca = 1;
                else
                    vm.clienteModificar.reteIca = 0;
            }
            if(compararImpuesto.impuesto === "IVA")
            {
                if(compararImpuesto.activo === 1)
                    vm.clienteModificar.declaraIva = 1;
                else
                    vm.clienteModificar.declaraIva = 0;
            }
        }
        });
    };
    return factory;
});

app.factory("updateImpuestosCliente", function ($http){
    var factory = {};
    factory.updateImpuestos = function (vm) {
        $http({
                url: 'Funciones_Cliente',
                method:'POST',
                data: {
                    'idCliente' :   vm.idCliente,
                    'milesIca':     String(vm.clienteModoficar.tarifaIca),
                    'declaraIva':   vm.clienteModificar.declaraIva,
                    'declaraIca':   vm.clienteModificar.reteIca,
                    'retefuente':   vm.clienteModificar.reteFuente,
                    'accion':       6
                }
            });
    }
    return factory;
});

app.factory("titulos", function (){
   var factory = {};
   factory.ti = function(vm){
    vm.footer="Swin SAS 2017"; //Pie de pagina para la aplicacion
   }
   return factory;
});