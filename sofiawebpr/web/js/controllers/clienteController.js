/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('contrCli', function ($scope, traerClientes, envioClie, $cookieStore, ngTableParams, eliminarCli, llenarComboCiudad, updateClie) {
    $scope.aux1 = "GESTION CLIENTE";
//var vm = this;
    $scope.ciudades = llenarComboCiudad.consultaLlenarComboCiudad($scope);
    $scope.currentPage = 0;
    $scope.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página
    $scope.pages = [];
    $scope.eliminar = "Eliminar";
    $scope.elim = [];
    $scope.clienteModificar = [];
    $scope.tablaClientes = [];
    $scope.mostrarCliente = function (idCliente)
    {
        for (var i = 0; i < $scope.tablaClientes.length; i++)
        {
            var comparar = $scope.tablaClientes[i];

            if (comparar.id_cliente === idCliente)
            {
                $scope.clienteModificar = comparar;
            }
        }
    };
    traerClientes.llenarTablacliente($scope);
    $scope.actualizar = {};
    $scope.actualizar = function ()
    {
        traerClientes.llenarTablacliente($scope);
    };
    $scope.saveCliente = function () {
        envioClie.envioCliente($scope.cliente, $cookieStore);
    };
    $scope.deleteClie = function (idPr) {
        if (confirm("Desea eliminar Cliente?"))
        {
            for (var i = 0; i < $scope.tablaClientes.length; i++)
            {
                var comparar = $scope.tablaClientes[i];
                if (comparar.id_cliente === idPr)
                {
                    var aux2 = $scope.tablaClientes[i];
                    var indexFila = i;
                }
            }
            eliminarCli.DeleteClie(aux2.id_cliente, $cookieStore);
            alert("Cliente Elimiando Correctamente!");
            $scope.tablaClientes.splice(indexFila, 1);
        }
    };
    $scope.modificarCliente = function (clienteModificar) {
        if (confirm("Modificar"))
        {
            for (var i = 0; i < $scope.tablaClientes.length; i++)
            {
                var comparar = $scope.tablaClientes[i];
                $scope.clienteModificar = comparar;
                if (comparar.id_cliente === clienteModificar)
                {
                    var aux2 = $scope.tablaClientes[i];
                    var indexFila = i;
                    i = this.tablaClientes.length;
                }
            }
            updateClie.updateCliente(aux2, $cookieStore);
            $scope.tablaClientes.indexOf(indexFila).push(aux2);
        }
    };
    $scope.configPages = function () {
        $scope.pages.length = 0;
        var ini = $scope.currentPage - 4;
        var fin = $scope.currentPage + 5;
        if (ini < 1) {
            ini = 1;
            if (Math.ceil($scope.tablaClientes.length / $scope.pageSize) > 10)
                fin = 10;
            else
                fin = Math.ceil($scope.tablaClientes.length / $scope.pageSize);
        } else {
            if (ini >= Math.ceil($scope.tablaClientes.length / $scope.pageSize) - 10) {
                ini = Math.ceil($scope.tablaClientes.length / $scope.pageSize) - 10;
                fin = Math.ceil($scope.tablaClientes.length / $scope.pageSize);
            }
        }
        if (ini < 1)
            ini = 1;
        for (var i = ini; i <= fin; i++) {
            $scope.pages.push({no: i});
        }
        if ($scope.currentPage >= $scope.pages.length)
            $scope.currentPage = $scope.pages.length - 1;
    };
    $scope.setPage = function (index) {
        $scope.currentPage = index - 1;
    };
    $scope.configPages();
});
app.factory('traerClientes', function ($http) {
    var log2 = {};
    log2.llenarTablacliente = function ($scope) {
        $http.get("Funciones_Cliente").success(function (result)
        {
            $scope.tablaClientes=  (result[0]);
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
    funcion.DeleteClie = function (idCliente, $cookieStore) {
        $http({
            method: "POST",
            url: "Funciones_Cliente",
            data: {
                'idCliente': idCliente,
                'idUsuario': $cookieStore.get("idUsuario"),
                'accion': 2 //Eliminar
            }
        });
    };
    return funcion;
});
app.factory('updateClie', function ($http) {
    var modificar = {};
    modificar.updateCliente = function (Cliente, $cookieStore) {
        $http({
            url: 'Funciones_Cliente',
            method: 'POST',
            data: {
                'idCliente': Cliente.id_usuario,
                'idUsuarioMod': $cookieStore.get("idUsuario"),
                'nombreCliente': Cliente.nombreCliente,
                'apellidoCliente': Cliente.apellidoCliente,
                'emailCliente': Cliente.emailCliente,
                'direccionCliente': Cliente.direccionCliente,
                'telefonoCliente': Cliente.telefonoCliente,
                'idCiudad': Cliente.idCiudad,
                'accion': 3 //Modificar
            }
        });
    };
    return modificar;
});

