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
app.controller('contrMarca', function (titulos,traerMarca, envioMarca,cerrarSesionS,traerMarcaSucursal,registrarMarcaSucursal) {
    
    var vm = this;
    titulos.ti(vm);
    vm.aux1 = "GESTION MARCA";
    vm.marcaIngresar={};
    vm.marcaMostrar = [];
    vm.currentPage = 0;
    vm.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página   
    vm.pages = [];
    vm.eliminar = "Eliminar";
   // traerMarca.llenarTablaMarca(vm);
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
 vm.modificados = {};
    vm.modificados.ids = [];
    vm.modificados.seleccionado = [];
    vm.categoriaSucursal = function (){
            vm.modificados.ids.splice(0,vm.modificados.ids.length); // Limpia el array Ids
            vm.modificados.seleccionado.splice(0,vm.modificados.seleccionado.length); // Limpia el array Seleccionado
            traerMarcaSucursal.getMarcaSucursal(vm); // Se llenan de nuevo los array con los valores de la db
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
    vm.registrarMarcasSelected = function ()
    {
        registrarMarcaSucursal.setMarca(vm.modificados); //Factory que registra los permisos en db
    }
   
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
app.factory('traerMarcaSucursal',function ($http){
   var permi = {};
   permi.getMarcaSucursal = function (vm) {
       $http({
           url : 'Funciones_Marca_BF',
           method : 'POST',
           data : {
               'idSucursal' : sessionStorage.getItem('idSucursal'),
               'accion':        6   // Traer las categorias del proveedor
           }
       }).success(function (result){
           vm.marcaMostrar = result[0];
           vm.configPages();
           for(var i = 0 ; i < vm.marcaMostrar.length;i ++)
            {
                var categoriaIterator = vm.marcaMostrar[i];
                if(categoriaIterator.Seleccion === 1)
                {
                    vm.modificados.ids.push(categoriaIterator.id_marca);//Se guardan los permisos Seleccionados
                    vm.modificados.seleccionado.push(true);
                }
            }
            
           //vm.agregarSeleccion();
       });
   };
   return permi;
});
app.factory('registrarMarcaSucursal', function ($http) {
   var registrar = {};
   registrar.setMarca = function (categoriaSelected) {
       $http({
           url: 'Funciones_Marca_BF',
           method : 'POST',
           data : {
               'marca' : categoriaSelected,
               'idSucursal' : sessionStorage.getItem('idSucursal'),
               'accion' :       5 // Registra los permisos seleccionadas
           }
       })
   };
   return registrar;
});

