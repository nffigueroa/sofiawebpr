/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('contrUsuario', function (titulos,ControlSesion,traerUsuario, traerPermisosUsuario, envioUsuario, 
eliminarUsuario, llenarComboCiudad, updateUser,llenarComboCiudad,registrarPermisoUsuario,llenarComboCargo,cerrarSesionS) 
{
    var vm = this;
    titulos.ti(vm);
    vm.aux1 = "GESTION USUARIO";
   // vm.ciudades = llenarComboCiudad.consultaLlenarComboCiudad(vm);
    vm.currentPage = 0;
    vm.ciudades = llenarComboCiudad.consultaLlenarComboCiudad(vm);
    vm.cargo = llenarComboCargo.consultaLlenarComboCargo(vm);
    vm.usuario = [];
    vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.usuario();
    vm.ccUsuario;
    vm.usuarioModificar = [];
    vm.tablaUsuario = [];
    vm.proveedores = []; 
    vm.currentPage = 0;
    vm.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página
    vm.pages = [];
    vm.configPages = function() {
        vm.pages.length = 0;
        var ini = vm.currentPage - 4;
        var fin = vm.currentPage + 5;
        if (ini < 1) {
          ini = 1;
          if (Math.ceil(vm.tablaUsuario.length / vm.pageSize) > 10) fin = 10;
          else fin = Math.ceil(vm.tablaUsuario.length / vm.pageSize);
       } else {
          if (ini >= Math.ceil(vm.tablaUsuario.length / vm.pageSize) - 10) {
             ini = Math.ceil(vm.tablaUsuario.length / vm.pageSize) - 10;
             fin = Math.ceil(vm.tablaUsuario.length / vm.pageSize);
          }
       }
       if (ini < 1) ini = 1;
       for (var i = ini; i <= fin; i++) {
          vm.pages.push({ no: i });
       }
       if (vm.currentPage >= vm.pages.length)
          vm.currentPage = vm.pages.length - 1;
};
    vm.registrarPermisosSeleccionados = function (ccUsuario)
    {
        //var categoriasEnviar = categoriasSelecciondasOnly(categorias);
        registrarPermisoUsuario.setPermiso(vm.modificados,ccUsuario); //Factory que registra los permisos en db
        vm.alerMessages = "Actualizado con exito";
    }
    vm.permisosUsuario = function (idProveedor,ccUsuario){
            vm.modificados.ids.splice(0,vm.modificados.ids.length); // Limpia el array Ids
            vm.modificados.seleccionado.splice(0,vm.modificados.seleccionado.length); // Limpia el array Seleccionado
            vm.ccUsuario = ccUsuario;
            traerPermisosUsuario.getPermisos(idProveedor,vm,ccUsuario); // Se llenan de nuevo los array con los valores de la db
            
            
    }
    vm.mostrarUsuario = function (idUsuario) // Muestra el usuario seleccionado en el formulario para modificar
    {
        for (var i = 0; i < vm.tablaUsuario.length; i++)
        {
            var comparar = vm.tablaUsuario[i];

            if (comparar.id_usuario === idUsuario)
            {
                vm.usuarioModificar = comparar;
            }
        }
    };
    traerUsuario.llenarTablaUsuario(vm);
    //vm.actualizar = {};
    vm.actualizar = function ()
    {
        traerProveedor.llenarTablaProveedor(vm);
    };
//    };
    vm.saveUsuario = function () {
        envioUsuario.addUsuario(vm.usuario);
    };
    vm.deleteUsuario = function (idUsu) {
        if (confirm("Desea eliminar Usuario?"))
        {
            for (var i = 0; i < vm.tablaUsuario.length; i++)
            {
                var comparar = vm.tablaUsuario[i];
                if (comparar.id_usuario === idUsu)
                {
                    var aux2 = vm.tablaUsuario[i];
                    var indexFila = i;
                }
            }
            eliminarUsuario.DeleteUsuario(aux2.id_usuario);
            alert("Usuario Elimiando Correctamente!");
            vm.tablaUsuario.splice(indexFila, 1);
        }
    };
    vm.modificarUsuario = function (usuarioModificar) {
        if (confirm("Modificar"))
        {
            for (var i = 0; i < vm.tablaUsuario.length; i++)
            {
                var comparar = vm.tablaUsuario[i];
                if (comparar.id_usuario === usuarioModificar)
                {
                    var aux2 = vm.tablaUsuario[i];
                    var indexFila = i;
                    i = vm.tablaUsuario.length;
                }
            }
            updateUser.updateUsuario(aux2);
           // vm.tablaUsuario.indexOf(indexFila).push(aux2);
        }
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.modificados = {};
    vm.modificados.ids = [];
    vm.modificados.seleccionado = [];
    vm.clicModificar = function (idPermiso) {
        if(vm.modificados.ids.length === 0)
        {
             vm.modificados.ids.push(idPermiso);
             vm.modificados.seleccionado.push(true);
        }
        else
        {
           // var cantidad = vm.modificados.ids.length;
                if(buscarenArray(vm.modificados.ids,idPermiso)) // Si ya existe el idProveedor
                {
                    var ind = vm.modificados.ids.indexOf(idPermiso); // Buscamos el index en el array del proveedor
                    if(vm.modificados.seleccionado[ind] === true) // Si el idProveedor esta marcado como si
                    {
                        
                        //vm.modificados.seleccionado[ind] = false; // Si esta verdadero , cambiar a false
                        vm.modificados.seleccionado.splice(ind,1);
                        vm.modificados.ids.splice(ind,1);
                    }                        
                    else
                    {
                        vm.modificados.seleccionado[ind] = true; // SI es false , cambiar a true
                    }
                }
                else // Si no se encuentra el registro se añade
                {
                    vm.modificados.ids.push(idPermiso); 
                    vm.modificados.seleccionado.push(true);
                }
            
        }
    };
    vm.modificarProveedor = function ()
    {
        updateProvee.updateProveedor(vm.proveedorModificar);
    }
});
app.factory('traerUsuario', function ($http) {
    var log2 = {};
    log2.llenarTablaUsuario= function (vm) {
        $http({
            url :'Funciones_Usuario_BF',
            method: 'POST',
            data: {
                idSucursal : sessionStorage.getItem("idSucursal"),
                accion:      4 //Consultar
            }
        }).then( function (result){
            vm.tablaUsuario = result.data[0];
            vm.configPages();
        });
    };
    return log2;
});
app.factory('envioUsuario', function ($http) {
    var envio = {};

    envio.addUsuario = function (usuario) {
        $http({
            url: "Funciones_Usuario_BF",
            method: "POST",
            data: {
                'nombreUsuario': usuario.nombre,
                'apellidoUsuario': usuario.apellido, // usuario que registra
                'idenUsuario': usuario.identificacion,
                'telefonoUsuario': usuario.telefono,
                'direccionUsuario': usuario.direccion,
                'cargoUsuario': usuario.cargo,
                'descripcionUsuario': usuario.descripcion,
                'idUsuario': sessionStorage.getItem("idUsuario"), // usuario que registra
                'idSucursal': sessionStorage.getItem("idSucursal"),
                'usuarioNombre': usuario.usuarioName,
                'psw' :usuario.psw,
                'accion': 1// Insertar
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
app.factory('eliminarUsuario', function ($http) {
    var funcion = {};
    funcion.DeleteUsuario = function (idUsuario) {
        $http({
            method: "POST",
            url: "Funciones_Usuario_BF",
            data: {
                'idUsuarioAEliminar': idUsuario,
                'idUsuarioLogueado': sessionStorage.getItem("idUsuario"),
                'accion': 2 //Eliminar
            }
        });
    };
    return funcion;
});
app.factory('updateUser', function ($http) {
    var modificar = {};
    modificar.updateUsuario = function (usuarioModificar) {
        $http({
            url: 'Funciones_Usuario_BF',
            method: 'POST',
            data: {
                'nombreUsuario': usuarioModificar.nombre_usuario,
                'apellidoUsuario': usuarioModificar.apellido_usuario, // usuario que registra
                'idenUsuario': usuarioModificar.cc_usuario,
                'telefonoUsuario': usuarioModificar.telefono_usuario,
                'direccionUsuario': usuarioModificar.direccion_usuario,
                'cargoUsuario': usuarioModificar.cargo,
                'descripcionUsuario': usuarioModificar.descripcion,
                'idUsuarioCreacion': sessionStorage.getItem("idUsuario"), // usuario que registra
                'idSucursal': sessionStorage.getItem("idSucursal"),
                'usuarioNombre': usuarioModificar.usuario,
                'idUsuario': usuarioModificar.id_usuario,
                'accion': 3 //Modificar
            }
        });
    };
    return modificar;
});
app.factory('traerPermisosCompletos', function ($http){
     var permi = {};
   permi.getPermisos = function (vm) {
       $http({
           url : 'Funciones_Usuario_BF',
           method : 'POST',
           data : {
               'idUsuario' : idUsuario,
               'accion':        7   // Traer los permisos de la aplicacion
           }
       }).success(function (result){
           vm.permisos = result[0];
        //   vm.nitProveedor = nit;
           for(var i = 0 ; i < vm.permisos.length;i ++)
            {
                var categoriaIterator = vm.permisos[i];
                if(categoriaIterator.Seleccion === 1)
                {
                    vm.modificados.ids.push(categoriaIterator.id_categoria);
                    vm.modificados.seleccionado.push(true);
                }
            }
           //vm.agregarSeleccion();
       });
   };
   return permi;
});
app.factory('traerPermisosUsuario',function ($http){
   var permi = {};
   permi.getPermisos = function (idUsuario,vm,ccUsuario) {
       $http({
           url : 'Funciones_Usuario_BF',
           method : 'POST',
           data : {
               'idUsuario' : idUsuario,
               'accion':        5   // Traer las categorias del proveedor
           }
       }).success(function (result){
           vm.permisos = result[0];
           vm.ccUsuario = ccUsuario;
           for(var i = 0 ; i < vm.permisos.length;i ++)
            {
                var categoriaIterator = vm.permisos[i];
                if(categoriaIterator.Seleccion === 1)
                {
                    vm.modificados.ids.push(categoriaIterator.id_permiso);//Se guardan los permisos Seleccionados
                    vm.modificados.seleccionado.push(true);
                }
            }
           //vm.agregarSeleccion();
       });
   };
   return permi;
});
app.factory('registrarPermisoUsuario', function ($http) {
   var registrar = {};
   registrar.setPermiso = function (permisosSeleccionado,ccUsuario) {
       $http({
           url: 'Funciones_Usuario_BF',
           method : 'POST',
           data : {
               'permisos' : permisosSeleccionado,
               'ccUsuario': ccUsuario, // Identificacion del Usuario
               'accion' :       6 // Registra los permisos seleccionadas
           }
       })
   };
   return registrar;
});


function buscarenArray(array,fish)
    {
        var resultado ;
        for(var i  = 0 ; i < array.length ; i ++)
        {
            if(array[i] === fish)
                resultado = true;
        }
        return resultado;
    }
function obtenerAtributoModal3()
{
    var elemento = document.querySelector("#myModal3");
    var atributo = elemento.getAttribute("aria-hidden");
    var esvisible = $("#myModal3").is(":visible");
    return esvisible;
}
    
    
