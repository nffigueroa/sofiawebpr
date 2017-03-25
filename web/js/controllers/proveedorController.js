/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('contrProvee', function (auth, traerProveedor, envioProvee, $cookieStore, ngTableParams,
eliminarProvee, llenarComboCiudad, updateProvee,cerrarSesionS,llenarComboCiudad,traerCategoriasProveedor,category,registrarCategoriasProveedor) {
    var vm = this;
    var idProveedor;
    vm.aux1 = "GESTION PROVEEDOR";
   // vm.ciudades = llenarComboCiudad.consultaLlenarComboCiudad(vm);
    vm.currentPage = 0;
    vm.obtenerAtrr = obtenerAtributoModal3();
    vm.ciudades = llenarComboCiudad.consultaLlenarComboCiudad(vm);
    vm.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página
    vm.pages = [];
      vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
    vm.cerrarSesion = function (){
        cerrarSesionS.BorrarSesion();
    }
    vm.usuario();
    vm.nitProveedor;
    vm.proveedorModificar = [];
    vm.tablaProveedores = [];
    vm.proveedores = []; 
    vm.categoriasSelected = {};
    
    vm.agregarSeleccion = function ()
    {
        for(var i = 0 ; i < vm.categorias.length ; i ++)
        {
            var cate =  vm.categorias[i];
            if(cate.Seleccion === 1)
            {
                vm.categoriasSelected[cate.id_categoria,true];
            }
        }
        
    };
    vm.categorias = category.traerCategorias(vm);
    vm.registrarCategoriasSeleccionadas = function (categorias,nitProveedor)
    {
        //var categoriasEnviar = categoriasSelecciondasOnly(categorias);
        registrarCategoriasProveedor.setCategorias(vm.modificados,nitProveedor);
        vm.alerMessages = "Actualizado con exito";
    }
    vm.categoriasProveedor = function (idProveedor,nit){
            vm.modificados.ids.splice(0,vm.modificados.ids.length); //Limpia el array Ids
            vm.modificados.seleccionado.splice(0,vm.modificados.seleccionado.length); //Limpia el array seleccionado
            traerCategoriasProveedor.getCategorias(idProveedor,vm,nit);
            
    }
    vm.mostrarProveedor = function (idProveedor) // Muestra el proveedor seleccionado en el formulario para modificar
    {
        for (var i = 0; i < vm.tablaProveedores.length; i++)
        {
            var comparar = vm.tablaProveedores[i];

            if (comparar.id_proveedor === idProveedor)
            {
                vm.proveedorModificar = comparar;
            }
        }
    };
    traerProveedor.llenarTablaProveedor(vm);
    //vm.actualizar = {};
    vm.actualizar = function ()
    {
        traerProveedor.llenarTablaProveedor(vm);
    };
//    };
    vm.saveProveedor = function () {
        envioProvee.addProveedor(vm.proveedores, $cookieStore);
    };
    vm.deleteProveedor = function (idPr) {
        if (confirm("Desea eliminar Proveedor?"))
        {
            for (var i = 0; i < vm.tablaProveedores.length; i++)
            {
                var comparar = vm.tablaProveedores[i];
                if (comparar.id_proveedor === idPr)
                {
                    var aux2 = vm.tablaProveedores[i];
                    var indexFila = i;
                }
            }
            eliminarProvee.DeleteProveedor(aux2.id_proveedor, $cookieStore);
            alert("Proveedor Elimiando Correctamente!");
            vm.tablaProveedores.splice(indexFila, 1);
        }
    };
    vm.modificarProveedor = function (proveedorModificar) {
        if (confirm("Modificar"))
        {
            for (var i = 0; i < vm.tablaProveedores.length; i++)
            {
                var comparar = vm.tablaProveedores[i];
                vm.proveedorModificar = comparar;
                if (comparar.id_proveedor === proveedorModificar)
                {
                    var aux2 = vm.tablaProveedores[i];
                    var indexFila = i;
                    i = this.tablaProveedores.length;
                }
            }
            updateProvee.updateProveedor(aux2, $cookieStore);
            vm.tablaProveedores.indexOf(indexFila).push(aux2);
        }
    };
    vm.configPages = function () {
        vm.pages.length = 0;
        var ini = vm.currentPage - 4;
        var fin = vm.currentPage + 5;
        if (ini < 1) {
            ini = 1;
            if (Math.ceil(vm.tablaProveedores.length / vm.pageSize) > 10)
                fin = 10;
            else
                fin = Math.ceil(vm.tablaProveedores.length / vm.pageSize);
        } else {
            if (ini >= Math.ceil(vm.tablaProveedores.length / vm.pageSize) - 10) {
                ini = Math.ceil(vm.tablaProveedores.length / vm.pageSize) - 10;
                fin = Math.ceil(vm.tablaProveedores.length / vm.pageSize);
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
    vm.modificados = {};
    vm.modificados.ids = [];
    vm.modificados.seleccionado = [];
    vm.clicModificar = function (idCategoria) {
        if(vm.modificados.ids.length === 0)
        {
             vm.modificados.ids.push(idCategoria);
             vm.modificados.seleccionado.push(true);
        }
        else
        {
           // var cantidad = vm.modificados.ids.length;
                if(buscarenArray(vm.modificados.ids,idCategoria)) // Si ya existe el idProveedor
                {
                    var ind = vm.modificados.ids.indexOf(idCategoria); // Buscamos el index en el array del proveedor
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
                    vm.modificados.ids.push(idCategoria); 
                    vm.modificados.seleccionado.push(true);
                }
            
        }
    };
    vm.modificarProveedor = function ()
    {
        updateProvee.updateProveedor(vm.proveedorModificar);
    }
});
app.factory('traerProveedor', function ($http) {
    var log2 = {};
    log2.llenarTablaProveedor = function (vm) {
        $http({
            url :'Funciones_Proveedor_BF',
            method: 'POST',
            data: {
                idSucursal : sessionStorage.getItem("idSucursal"),
                accion:      4 //Consultar
            }
        }).then( function (result){
            vm.tablaProveedores = result.data[0];
        });
    };
    return log2;
});
app.factory('envioProvee', function ($http) {
    var envio = {};

    envio.addProveedor = function (proveedor, $cookieStore) {
        $http({
            url: "Funciones_Proveedor_BF",
            method: "POST",
            data: {
                'idSucursal': sessionStorage.getItem("idSucursal"),
                'idUsuario': sessionStorage.getItem("idUsuario"), // usuario que registra
                'empresa': proveedor.empresa,
                'contacto': proveedor.contacto_empresa,
                'telefono': proveedor.telefono_proveedor,
                'direccion': proveedor.direccion_poroveedor,
                'email': proveedor.mail_proveedor,
                'ciudad': proveedor.ciudad,
                'nit': proveedor.nit_proveedor,
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
app.factory('eliminarProvee', function ($http) {
    var funcion = {};
    funcion.DeleteProveedor = function (idProveedor, $cookieStore) {
        $http({
            method: "POST",
            url: "Funciones_Proveedor_BF",
            data: {
                'id_proveedor': idProveedor,
                'idUsuario': sessionStorage.getItem("idUsuario"),
                'accion': 2 //Eliminar
            }
        });
    };
    return funcion;
});
app.factory('updateProvee', function ($http) {
    var modificar = {};
    modificar.updateProveedor = function (proveedor) {
        $http({
            url: 'Funciones_Proveedor_BF',
            method: 'POST',
            data: {
                'idProveedor': proveedor.id_proveedor,
                'usuario': sessionStorage.getItem("idUsuario"),
                'empresa': proveedor.empresa,
                'contacto': proveedor.contaco_empresa,
                'telefono': proveedor.telefono_proveedor,
                'direccion': proveedor.direccion_proveedor,
                'mail': proveedor.mail_proveedor,
                'ciudad': proveedor.ciudad,
                'idSucursal': sessionStorage.getItem("idSucursal"),
                'nit': proveedor.nit_proveedor,
                'accion': 3 //Modificar
            }
        });
    };
    return modificar;
});
app.factory('traerCategoriasProveedor',function ($http){
   var permi = {};
   permi.getCategorias = function (idProveedor,vm,nit) {
       $http({
           url : 'Funciones_Proveedor_BF',
           method : 'POST',
           data : {
               'idProveedor' : idProveedor,
               'accion':        5   // Traer las categorias del proveedor
           }
       }).success(function (result){
           vm.categorias = result[0];
           vm.nitProveedor = nit;
           for(var i = 0 ; i < vm.categorias.length;i ++)
            {
                var categoriaIterator = vm.categorias[i];
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
app.factory('registrarCategoriasProveedor', function ($http) {
   var registrar = {};
   registrar.setCategorias = function (categoriasProveedorSelecionadas,nit) {
       $http({
           url: 'Funciones_Proveedor_BF',
           method : 'POST',
           data : {
               'categorias' : categoriasProveedorSelecionadas,
               'nitProveedor': nit,
               'accion' :       6 // Registra las categorias seleccionadas
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
    
    
