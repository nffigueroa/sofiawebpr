/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('contrProvee', function (auth,$scope, traerProveedor, envioProvee, $cookieStore, ngTableParams,
eliminarProvee, llenarComboCiudad, updateProvee,$cookies,llenarComboCiudad,traerCategoriasProveedor,category,registrarCategoriasProveedor) {
    var vm = this;
    var idProveedor;
    $scope.aux1 = "GESTION PROVEEDOR";
   // $scope.ciudades = llenarComboCiudad.consultaLlenarComboCiudad($scope);
    $scope.currentPage = 0;
    $scope.obtenerAtrr = obtenerAtributoModal3();
    $scope.ciudades = llenarComboCiudad.consultaLlenarComboCiudad($scope);
    $scope.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página
    $scope.pages = [];
    $scope.nitProveedor;
    $scope.proveedorModificar = [];
    $scope.tablaProveedores = [];
    $scope.proveedores = []; 
    $scope.categoriasSelected = {};
    
    $scope.agregarSeleccion = function ()
    {
        for(var i = 0 ; i < $scope.categorias.length ; i ++)
        {
            var cate =  $scope.categorias[i];
            if(cate.Seleccion === 1)
            {
                $scope.categoriasSelected[cate.id_categoria,true];
            }
        }
        
    };
    $scope.categorias = category.traerCategorias($scope);
    $scope.registrarCategoriasSeleccionadas = function (categorias,nitProveedor)
    {
        //var categoriasEnviar = categoriasSelecciondasOnly(categorias);
        registrarCategoriasProveedor.setCategorias($scope.modificados,nitProveedor);
        $scope.alerMessages = "Actualizado con exito";
    }
    $scope.categoriasProveedor = function (idProveedor,nit){
            $scope.modificados.ids.splice(0,$scope.modificados.ids.length); //Limpia el array Ids
            $scope.modificados.seleccionado.splice(0,$scope.modificados.seleccionado.length); //Limpia el array seleccionado
            traerCategoriasProveedor.getCategorias(idProveedor,$scope,nit);
            
    }
    $scope.mostrarProveedor = function (idProveedor) // Muestra el proveedor seleccionado en el formulario para modificar
    {
        for (var i = 0; i < $scope.tablaProveedores.length; i++)
        {
            var comparar = $scope.tablaProveedores[i];

            if (comparar.id_proveedor === idProveedor)
            {
                $scope.proveedorModificar = comparar;
            }
        }
    };
    traerProveedor.llenarTablaProveedor($scope);
    //$scope.actualizar = {};
    $scope.actualizar = function ()
    {
        traerProveedor.llenarTablaProveedor($scope);
    };
//    };
    $scope.saveProveedor = function () {
        envioProvee.addProveedor($scope.proveedores, $cookieStore);
    };
    $scope.deleteProveedor = function (idPr) {
        if (confirm("Desea eliminar Proveedor?"))
        {
            for (var i = 0; i < $scope.tablaProveedores.length; i++)
            {
                var comparar = $scope.tablaProveedores[i];
                if (comparar.id_proveedor === idPr)
                {
                    var aux2 = $scope.tablaProveedores[i];
                    var indexFila = i;
                }
            }
            eliminarProvee.DeleteProveedor(aux2.id_proveedor, $cookieStore);
            alert("Proveedor Elimiando Correctamente!");
            $scope.tablaProveedores.splice(indexFila, 1);
        }
    };
    $scope.modificarProveedor = function (proveedorModificar) {
        if (confirm("Modificar"))
        {
            for (var i = 0; i < $scope.tablaProveedores.length; i++)
            {
                var comparar = $scope.tablaProveedores[i];
                $scope.proveedorModificar = comparar;
                if (comparar.id_proveedor === proveedorModificar)
                {
                    var aux2 = $scope.tablaProveedores[i];
                    var indexFila = i;
                    i = this.tablaProveedores.length;
                }
            }
            updateProvee.updateProveedor(aux2, $cookieStore);
            $scope.tablaProveedores.indexOf(indexFila).push(aux2);
        }
    };
    $scope.configPages = function () {
        $scope.pages.length = 0;
        var ini = $scope.currentPage - 4;
        var fin = $scope.currentPage + 5;
        if (ini < 1) {
            ini = 1;
            if (Math.ceil($scope.tablaProveedores.length / $scope.pageSize) > 10)
                fin = 10;
            else
                fin = Math.ceil($scope.tablaProveedores.length / $scope.pageSize);
        } else {
            if (ini >= Math.ceil($scope.tablaProveedores.length / $scope.pageSize) - 10) {
                ini = Math.ceil($scope.tablaProveedores.length / $scope.pageSize) - 10;
                fin = Math.ceil($scope.tablaProveedores.length / $scope.pageSize);
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
    $scope.modificados = {};
    $scope.modificados.ids = [];
    $scope.modificados.seleccionado = [];
    $scope.clicModificar = function (idCategoria) {
        if($scope.modificados.ids.length === 0)
        {
             $scope.modificados.ids.push(idCategoria);
             $scope.modificados.seleccionado.push(true);
        }
        else
        {
           // var cantidad = $scope.modificados.ids.length;
                if(buscarenArray($scope.modificados.ids,idCategoria)) // Si ya existe el idProveedor
                {
                    var ind = $scope.modificados.ids.indexOf(idCategoria); // Buscamos el index en el array del proveedor
                    if($scope.modificados.seleccionado[ind] === true) // Si el idProveedor esta marcado como si
                    {
                        
                        //$scope.modificados.seleccionado[ind] = false; // Si esta verdadero , cambiar a false
                        $scope.modificados.seleccionado.splice(ind,1);
                        $scope.modificados.ids.splice(ind,1);
                    }                        
                    else
                    {
                        $scope.modificados.seleccionado[ind] = true; // SI es false , cambiar a true
                    }
                }
                else // Si no se encuentra el registro se añade
                {
                    $scope.modificados.ids.push(idCategoria); 
                    $scope.modificados.seleccionado.push(true);
                }
            
        }
    };
    $scope.modificarProveedor = function ()
    {
        updateProvee.updateProveedor($scope.proveedorModificar);
    }
});
app.factory('traerProveedor', function ($http) {
    var log2 = {};
    log2.llenarTablaProveedor = function ($scope) {
        $http({
            url :'Funciones_Proveedor_BF',
            method: 'POST',
            data: {
                idSucursal : sessionStorage.getItem("idSucursal"),
                accion:      4 //Consultar
            }
        }).then( function (result){
            $scope.tablaProveedores = result.data[0];
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
   permi.getCategorias = function (idProveedor,$scope,nit) {
       $http({
           url : 'Funciones_Proveedor_BF',
           method : 'POST',
           data : {
               'idProveedor' : idProveedor,
               'accion':        5   // Traer las categorias del proveedor
           }
       }).success(function (result){
           $scope.categorias = result[0];
           $scope.nitProveedor = nit;
           for(var i = 0 ; i < $scope.categorias.length;i ++)
            {
                var categoriaIterator = $scope.categorias[i];
                if(categoriaIterator.Seleccion === 1)
                {
                    $scope.modificados.ids.push(categoriaIterator.id_categoria);
                    $scope.modificados.seleccionado.push(true);
                }
            }
           //$scope.agregarSeleccion();
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
    
    
