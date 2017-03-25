/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('contrCli', function (cerrarSesionS,traerClientes, envioClie, ngTableParams, eliminarCli, llenarComboCiudad, updateClie) {
    var vm = this;
    vm.aux1 = "GESTION CLIENTE";
    vm.ciudades = llenarComboCiudad.consultaLlenarComboCiudad(vm);
    vm.currentPage = 0;
    vm.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página
    vm.pages = [];
    vm.eliminar = "Eliminar";
    vm.elim = [];
    vm.clienteModificar = [];
    vm.tablaClientes = [];
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
    };
    traerClientes.llenarTablacliente(vm);
    vm.actualizar = {};
    vm.actualizar = function ()
    {
        traerClientes.llenarTablacliente(vm);
    };
    vm.saveCliente = function () {
        envioClie.envioCliente(vm.cliente);
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
        if (confirm("Modificar"))
        {
            for (var i = 0; i < vm.tablaClientes.length; i++)
            {
                var comparar = vm.tablaClientes[i];
                vm.clienteModificar = comparar;
                if (comparar.id_cliente === clienteModificar)
                {
                    var aux2 = vm.tablaClientes[i];
                    var indexFila = i;
                    i = this.tablaClientes.length;
                }
            }
            updateClie.updateCliente(aux2);
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
        $http.get("Funciones_Cliente").success(function (result)
        {
            vm.tablaClientes=  (result[0]);
        });
    };
    return log2;
});
app.factory('envioClie', function ($http) {
    var envio = {};

    envio.envioCliente = function (Cliente, $cookieStore) {
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


