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
app.controller('contrCategoria', function (titulos,cerrarSesionS,traerCategoriaSucursal, traerCategoria, envioCategoria, registrarCategoriaSucursal, ngTableParams, eliminarCli, llenarComboCiudad, updateClie) {
    
    var vm = this;
    titulos.ti(vm);
    vm.aux1 = "GESTION CATEGORIA";
    vm.categoriaIngresar={};
    vm.categoriasMostrar = [];
    //traerCategoria.llenarTablaCategoria(vm);
    vm.currentPage = 0;
    vm.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página
    vm.pages = [];
    vm.configPages = function() {
        vm.pages.length = 0;
        var ini = vm.currentPage - 4;
        var fin = vm.currentPage + 5;
        if (ini < 1) {
          ini = 1;
          if (Math.ceil(vm.categoriasMostrar.length / vm.pageSize) > 10) fin = 10;
          else fin = Math.ceil(vm.categoriasMostrar.length / vm.pageSize);
       } else {
          if (ini >= Math.ceil(vm.categoriasMostrar.length / vm.pageSize) - 10) {
             ini = Math.ceil(vm.categoriasMostrar.length / vm.pageSize) - 10;
             fin = Math.ceil(vm.categoriasMostrar.length / vm.pageSize);
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
    vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.usuario();
    vm.insertarCategoria =  function (categorias)
    {
            envioCategoria.addCategoria(categorias);
    }
    vm.actualizarTabla = function ()
    {
        traerCategoria.llenarTablaCategoria(vm);
    }
    vm.modificados = {};
    vm.modificados.ids = [];
    vm.modificados.seleccionado = [];
    vm.categoriaSucursal = function (){
            vm.modificados.ids.splice(0,vm.modificados.ids.length); // Limpia el array Ids
            vm.modificados.seleccionado.splice(0,vm.modificados.seleccionado.length); // Limpia el array Seleccionado
            traerCategoriaSucursal.getCategoriaSucursal(vm); // Se llenan de nuevo los array con los valores de la db
    }
    vm.categoriaSucursal();
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
    vm.registrarCategoriasSelected = function ()
    {
        registrarCategoriaSucursal.setCategoria(vm.modificados); //Factory que registra los permisos en db
    }
    });
    
    
    
app.factory('traerCategoria', function ($http) {
    var log2 = {};
    log2.llenarTablaCategoria = function (vm) {
       $http({
           method : 'POST',
           url:     'Funciones_Categoria_BF',
           data : {
               accion : '4' // consultar Categorias
           }
       }).success(function (result) {
           vm.categoriasMostrar = result[0];
           //vm.categoriasMostrar.push(result[0]);
       });
    };
    return log2;
});
app.factory('envioCategoria', function ($http) {
    var envio = {};
    envio.addCategoria = function (categoria) {
        $http({
            url: "Funciones_Categoria_BF",
            method: "POST",
            data: {
                'idUsuario': sessionStorage.getItem("idUsuario"),
                'categoriaNombre':  categoria.nombreCategoria,
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

app.factory('traerCategoriaSucursal',function ($http){
   var permi = {};
   permi.getCategoriaSucursal = function (vm) {
       $http({
           url : 'Funciones_Categoria_BF',
           method : 'POST',
           data : {
               'idSucursal' : sessionStorage.getItem('idSucursal'),
               'accion':        6   // Traer las categorias del proveedor
           }
       }).success(function (result){
           vm.categoriasMostrar = result[0];
           vm.configPages();
           for(var i = 0 ; i < vm.categoriasMostrar.length;i ++)
            {
                var categoriaIterator = vm.categoriasMostrar[i];
                if(categoriaIterator.Seleccion === 1)
                {
                    vm.modificados.ids.push(categoriaIterator.id_categoria);//Se guardan los permisos Seleccionados
                    vm.modificados.seleccionado.push(true);
                }
            }
            
           //vm.agregarSeleccion();
       });
   };
   return permi;
});
app.factory('registrarCategoriaSucursal', function ($http) {
   var registrar = {};
   registrar.setCategoria = function (categoriaSelected) {
       $http({
           url: 'Funciones_Categoria_BF',
           method : 'POST',
           data : {
               'categorias' : categoriaSelected,
               'idSucursal' : sessionStorage.getItem('idSucursal'),
               'accion' :       5 // Registra los permisos seleccionadas
           }
       })
   };
   return registrar;
});


