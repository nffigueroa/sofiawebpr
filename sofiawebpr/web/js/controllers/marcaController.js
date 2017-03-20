/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('contrMarca', function (traerMarca, envioMarca,cerrarSesionS) {
    
    var vm = this;
    vm.aux1 = "GESTION MARCA";
    vm.marcaIngresar={};
    vm.marcaMostrar = [];
    vm.currentPage = 0;
    vm.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página   
    vm.pages = [];
    vm.eliminar = "Eliminar";
    traerMarca.llenarTablaMarca(vm);
      vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.usuario();
    vm.insertarMarca =  function (marca)
    {
            envioMarca.addMarca(marca);
    }
    vm.actualizarTabla = function ()
    {
        traerMarca.llenarTablaMarca(vm);
    }
    vm.elim = [];
    vm.clienteModificar = [];
    vm.marcaMostrar = [];
    vm.actualizar = {};    
    vm.configPages = function() {
    vm.pages.length = 0;
    
   var ini = vm.currentPage - 4;
   var fin = vm.currentPage + 5;
   alert(vm.marcaMostrar.length);
   if (ini < 1) {
      ini = 1;
      if (Math.ceil(vm.marcaMostrar.length / vm.pageSize) > 10) fin = 10;
      else fin = Math.ceil(vm.marcaMostrar.length / vm.pageSize);
   } else {
      if (ini >= Math.ceil(vm.marcaMostrar.length / vm.pageSize) - 10) {
         ini = Math.ceil(vm.marcaMostrar.length / vm.pageSize) - 10;
         fin = Math.ceil(vm.marcaMostrar.length / vm.pageSize);
         
      }
   }
   if (ini < 1) ini = 1;
   for (var i = ini; i <= fin; i++) {
      vm.pages.push({ no: i });
   }
   if (vm.currentPage >= vm.pages.length)
      vm.currentPage = vm.pages.length - 1;
};
   vm.setPage = function(index) {
   vm.currentPage = index - 1;
};
   
     });
     
app.factory('traerMarca', function ($http) {
    var log2 = {};
    log2.llenarTablaMarca= function (vm) {
       $http({
           method : 'POST',
           url:     'Funciones_Marca_BF',
           data : {
               accion : '4' // consultar Marca
           }
       }).success(function (result) {
           vm.marcaMostrar = result[0];
           vm.configPages();
           //vm.categoriasMostrar.push(result[0]);
       });
    };
    return log2;
});
app.factory('envioMarca', function ($http) {
    var envio = {};
    envio.addMarca = function (marca) {
        $http({
            url: "Funciones_Marca_BF",
            method: "POST",
            data: {
                'idUsuario': sessionStorage.getItem("idUsuario"),
                'marca':  marca.nombreMarca,
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
//app.factory('eliminarCli', function ($http) {
//    var funcion = {};
//    funcion.DeleteClie = function (idCliente, $cookieStore) {
//        $http({
//            method: "POST",
//            url: "Funciones_Cliente",
//            data: {
//                'idCliente': idCliente,
//                'idUsuario': $cookieStore.get("idUsuario"),
//                'accion': 2 //Eliminar
//            }
//        });
//    };
//    return funcion;
//});
//app.factory('updateClie', function ($http) {
//    var modificar = {};
//    modificar.updateCliente = function (Cliente, $cookieStore) {
//        $http({
//            url: 'Funciones_Cliente',
//            method: 'POST',
//            data: {
//                'idCliente': Cliente.id_usuario,
//                'idUsuarioMod': $cookieStore.get("idUsuario"),
//                'nombreCliente': Cliente.nombreCliente,
//                'apellidoCliente': Cliente.apellidoCliente,
//                'emailCliente': Cliente.emailCliente,
//                'direccionCliente': Cliente.direccionCliente,
//                'telefonoCliente': Cliente.telefonoCliente,
//                'idCiudad': Cliente.idCiudad,
//                'accion': 3 //Modificar
//            }
//        });
//    };
//    return modificar;
//});


