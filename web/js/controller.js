/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.
controller("contrLog", function (auth,loguear,$location) {
var vm = this;
vm.meesage= "Details";
vm.aux = [];
auth.flash = "";
    //función que llamamos al hacer sumbit al formulario
vm.login = function(){
        loguear.validarLogin(vm.usuario,vm,$location);
        auth.login(vm.usuario);
    }
//vm.login = function(){
//    loguear.validarLogin(vm.usuario,vm,$location,$cookieStore);
//    llenarSesion.getSession($cookieStore,$cookies);
//  };

 
});
app.factory('loguear',function ($http){
	var log = {};
	log.validarLogin = function(usuario,vm){
		 $http({
		     	url : 'Funciones_Login',
		        method : "POST",
                       // datatype : "json",
                        data : {
		        	'usuario' : usuario.nombre,
		        	'pass': usuario.password 
		        }
                       
		        }).then(function (response){
                                vm.aux.push(response.data);
                                if(!vm.aux[0].resultado === "ok")
                                {
                                //    $location.path("/master");
                                alert("Error Verifique sus datos");
                                }
//                                else
//                                {
//                                    
//                                }
                        });
	};
	return log;
});
app.controller('contrPro', function(traerProductoActualizar,cerrarSesionS,category,updatePr,envioPro,eliminarPro) {
var vm = this;
vm.currentPage = 0;
vm.pageSize = 5; // Esta la cantidad de registros que deseamos mostrar por página
vm.pages = [];
vm.usuario = function () {
        vm.user = sessionStorage.getItem("nombreReal");
    };
vm.cerrarSesion = function (){
    cerrarSesionS.BorrarSesion();
}

vm.usuario();
vm.configPages = function() {
   vm.pages.length = 0;
   var ini = vm.currentPage - 4;
   var fin = vm.currentPage + 5;
   if (ini < 1) {
      ini = 1;
      if (Math.ceil(vm.productos.length / vm.pageSize) > 10) fin = 10;
      else fin = Math.ceil(vm.productos.length / vm.pageSize);
   } else {
      if (ini >= Math.ceil(vm.productos.length / vm.pageSize) - 10) {
         ini = Math.ceil(vm.productos.length / vm.pageSize) - 10;
         fin = Math.ceil(vm.productos.length / vm.pageSize);
      }
   }
   if (ini < 1) ini = 1;
   for (var i = ini; i <= fin; i++) {
      vm.pages.push({ no: i });
   }
   if (vm.currentPage >= vm.pages.length)
      vm.currentPage = vm.pages.length - 1;
};
vm.eliminar="Eliminar";
vm.elim = [];
vm.setPage = function(index) {
   vm.currentPage = index - 1;
};
vm.productoModificar= [];
vm.categorias = [];
vm.medicion = [];
vm.marca = [];
vm.presentacion = [];
vm.productos = [];
vm.aux1 = "GESTION PRODUCTO";
vm.categorias = category.traerCategorias(vm);
vm.envioProd = function () {
    var col = vm.productos.length;
        for (var i = 0 ; i < col ; i ++)
        {
            var iterator = vm.productos[i];
            if(iterator.categoria === vm.producto.categoria1)
            {
                if(iterator.nombre_producto == vm.producto.nombre1)
                {
                    if(iterator.marca === vm.producto.marca1)
                    {
                        if(iterator.presentacion === vm.producto.presentacion1)
                        {
                            if(iterator.medicion === vm.producto.medicion1)
                            {
                                alert('Este producto ya fue ingresado!');
                                return;
                            }
                        }
                    }
                }
            }
        }
        vm.producto.nombre1 = vm.producto.nombre1.toUpperCase();
        envioPro.envioProducto(vm.producto,vm);
};
vm.enviarProducto = function () {
  vm.envioProd();
  traerProductoActualizar.traerProductos(vm);
};
vm.configPages();
vm.deleteProduct = function (idPr){ 
        if(confirm("Desea eliminar"))
            {
                for (var i =0 ; i < vm.productos.length ; i++)
                {
                    var comparar = vm.productos[i];
                    if( comparar.id_produccto === idPr)
                    {
                        var aux2 = vm.productos[i];
                        var indexFila = i;
                    }
                }
                eliminarPro.DeletePr(aux2.id_produccto);
                vm.productos.splice(indexFila,1);             
            }
};
vm.mostrarProducto = function (idProducto)
{
    for (var i =0 ; i < vm.productos.length ; i++)
                {
                    var comparar = vm.productos[i];
                    
                    if( comparar.id_produccto === idProducto)
                    {
                        vm.productoModificar = comparar;
                    }
                }
};
vm.actualizar = function ()
    {
        traerProductoActualizar.traerProductos(vm);
    };
vm.updatePro = function (idProducto){
    if(confirm("Modificar Informacion?"))
    {
        for (var i =0 ; i < vm.productos.length ; i++)
                {
                    vm.productos[i].nombre_producto =vm.productos[i].nombre_producto.toUpperCase();
                    var comparar = vm.productos[i];
                    vm.productoModificar = comparar;
                    if( comparar.id_produccto === idProducto)
                    {
                        var aux2 = vm.productos[i];
                        var indexFila = i;
                        i = this.productos.length;
                    }
                }
                updatePr.updateProducto(aux2);
                vm.productos.indexOf(indexFila).push(aux2);
    }
};
        });
        
app.factory('traerProductoActualizar', function ($http) {
            var log4 = {};
            log4.traerProductos = function (vm){
                $http({
                    method: 'POST',
                    url: 'Funciones_GestionProducto',
                    data :
                            {
                                'accion': 5 ,
                                'idSucursal': sessionStorage.getItem("idSucursal")
                            }
                }).success(function (result){
                        vm.productos = (result[4]);
                });
            };
           return log4;
        });
app.factory('category', function ($http) {
            var log2 = {};
            log2.traerCategorias = function (vm){
                $http({
                    method: 'POST',
                    url: 'Funciones_GestionProducto',
                    data :
                            {
                                'accion': 4 ,
                                'idSucursal': sessionStorage.getItem("idSucursal")
                            }
                }).success(function (result){
                        vm.categorias = result[0];
                        var col = vm.categorias.length;
                        for (var i = 0 ; i < col ; i++)
                        {
                            var iterator = vm.categorias[i];
                            var indexFile = i;
                            if(iterator.Seleccion === 0) // Si la categoria no esta parametrizada
                            {
                                vm.categorias.splice(indexFile,1);  // Se elimina el registro
                                i--; // Se baja una iteracion Ej , si estabamos en la fila 4 , se elimino la 4, debe volver a preguntar por la 4
                                col --; // Se resta una columna
                            }
                        }
                        vm.marca = result[1];
                        var col = vm.marca.length;
                        for (var i = 0 ; i < col ; i++)
                        {
                            var iterator = vm.marca[i];
                            var indexFile = i;
                            if(iterator.Seleccion === 0) // Si la categoria no esta parametrizada
                            {
                                vm.marca.splice(indexFile,1);  // Se elimina el registro
                                i--; // Se baja una iteracion Ej , si estabamos en la fila 4 , se elimino la 4, debe volver a preguntar por la 4
                                col --; // Se resta una columna
                            }
                        }
                        vm.medicion = result[2];
                        vm.presentacion= result[3];
                        vm.productos = (result[4]);
                });
//                 $http.get("Funciones_GestionProducto").success( function (result)
//                   {
//                        vm.categorias = result[0];
//                        vm.marca = result[1];
//                        vm.medicion = result[2];
//                        vm.presentacion= result[3];
//                        vm.productos = (result[4]);
//                    });
            };
           return log2;
        });
app.factory('envioPro', function ($http,traerProductoActualizar) {
    var envio = {};
    
    envio.envioProducto = function (producto,vm) {
        $http({
            url: "Funciones_GestionProducto",
            method : "POST",
            data : {
               'nombreProducto1' :  producto.nombre1,
               'marca1' :           producto.marca1,
               'medicion1':         producto.medicion1,
               'presentacion1':     producto.presentacion1,
               'categoria1':        producto.categoria1,
               'usuario':           sessionStorage.getItem("idSucursal"),
               'accion' :            1 // Consultar 
           }
        }).then( function (response){
            
          if(response.data.resultado === "si")
          {
              traerProductoActualizar.traerProductos(vm);
              alert("Producto Guardado Correctamente!");
          }
          else
          {
              alert("Ups! , Ocurrio algo que no debia");
          }
        });
    };
    return envio;
});
app.factory('eliminarPro', function ($http) {
   var funcion = {};
   funcion.DeletePr = function (idProducto) {
       $http ({
           method: "POST",
           url : "Funciones_GestionProducto",
           data : {
               'idProducto':    idProducto,
               'idUsuario':     sessionStorage.getItem("idUsuario"),
               'accion':        2 //Eliminar
           }
       });
   };
   return funcion;
});

app.filter('startFromGrid', function() {
   return function(input, start) {
      start = +start;
      return input.slice(start);
   };
});

app.factory('updatePr', function ($http) {
   var modificar = {};
   modificar.updateProducto = function (producto) {
       $http({
           url: 'Funciones_GestionProducto',
           method: 'POST',
           data : {
               'nombreProducto1' :  producto.nombre_producto,
               'marca1' :           producto.marca,
               'medicion1':         producto.medicion,
               'presentacion1':     producto.presentacion,
               'categoria1':        producto.categoria,
               'idPro':             producto.id_produccto,
               'usuario':           sessionStorage.getItem("idUsuario"),
               'accion':        3 //Modificar
           }
       });
   }; 
   return modificar;
});
app.factory('llenarComboCargo', function ($http){
    var llenar = {};
            llenar.consultaLlenarComboCargo = function (vm){
                 $http({
                     method : 'POST',
                     url: 'Funciones_Generales_BF',
                     data : {
                         'accion': 1 // Llamar Combo Cargo
                     }
                 }).success(function (result) {
                    vm.cargo = result[0]; 
                 });
            };
           return llenar;
});
app.factory('llenarComboCiudad', function ($http){
    var llenar = {};
            llenar.consultaLlenarComboCiudad = function (vm){
                 $http.get("Funciones_Generales_BF").success( function (result)
                   {
                        vm.ciudades = (result[0]);
                    });
            };
           return llenar;
});
app.factory ("auth", function ($http,$location,ControlSesion,mensajeFlash) {
    var cacheSesion = function (username,datosUsuario) {
        ControlSesion.set("UserLogin",true); // usuario logeado bandera
        ControlSesion.set("nombreReal",datosUsuario.nombre); // Nombre real del usuario logueado
        ControlSesion.set("idSucursal",datosUsuario.idSucursal); // Sucursala la que pertecene
        ControlSesion.set("idUsuario",datosUsuario.idUsuario); // Ide del usuario
        ControlSesion.set("idCargo",datosUsuario.idCargo); // Cargo del usuario registrado
        ControlSesion.set("apellido",datosUsuario.apellido); // Apellido del usuario
        ControlSesion.set("username",username); // Nombrel del usuario
        ControlSesion.set("idRegimen",datosUsuario.idRegimen); // Nombrel del usuario
        ControlSesion.set("isLogged",true); // Nombrel del usuario
    }
    var unCacheSesion = function (user) {
        ControlSesion.unset("UserLogin");
        ControlSesion.unset("userName");
    }
    return {
        login : function(usuario){
            $http({
            url: 'session_BF',
            method: 'POST',
            data : {
                    'usuario' : usuario.nombre
		    //'pass': usuario.password 
            },
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (result){
                mensajeFlash.clear();
                cacheSesion(usuario.nombre,result);
                $location.path("/master");
        })
    },
     isLoggedIn : function(){
         var retornar = ControlSesion.get("isLogged");
            return retornar;
        }
    }
    
});
app.factory("ControlSesion" , function () {
   return {
       //Buscamos la sesion
       get : function (item) {
           return sessionStorage.getItem(item);
       },
       set: function (key,val) {
           return sessionStorage.setItem(key,val);
       },
       unset : function (item){
           sessionStorage.removeItem(item);
       }
   } 
});
app.factory("mensajeFlash", function ($rootScope) {
    return { show : function (mensaje){
            $rootScope.flash = mensaje ;
        },
        clear : function (){
            $rootScope.flash = "";
        }
    }
});

app.service("cerrarSesionS", function (ControlSesion) {
    var aux = {};
    aux.BorrarSesion = function (){
        ControlSesion.unset("UserLogin");
        ControlSesion.unset("nombreReal");
        ControlSesion.unset("idSucursal");
        ControlSesion.unset("idUsuario");
        ControlSesion.unset("idCargo");
        ControlSesion.unset("apellido");
        ControlSesion.unset("username");
        ControlSesion.unset("isLogged");
    };
        return aux;
});

app.run(function($rootScope, $location, auth){
	//creamos un array con las rutas que queremos controlar
    var rutasPrivadas = ["/master",
                        "/login",
                        "/GestionUsuario",
                        "/GestionProducto",
                        "/GestionCliente",
                        "/GestionProveedor",
                        "/GestionCategoria",
                        "/GestionMarca",
                        "/GestionCorteCaja",
                        "/GestionIngresoInventario",
                        "/GestionCajonDinero",
                        "/GestionFactura",
                        "/GestionCuentasCobrar",
                        "/GestionReportes"];
    //al cambiar de rutas
    $rootScope.$on('$routeChangeStart', function(){
    	//si en el array rutasPrivadas existe $location.path(), locationPath en el login
    	//es /login, en la home /home etc, o el usuario no ha iniciado sesión, lo volvemos 
    	//a dejar en el formulario de login
        if(in_array($location.path(),rutasPrivadas) && !auth.isLoggedIn()){
            $location.path("/sofiaApp");
            alert("No tienes permiso o no estas autenticado!");
        }
        //en el caso de que intente acceder al login y ya haya iniciado sesión lo mandamos a la home
        if((($location.path() == '/login') || ($location.path() == '/sofiaApp'))  && auth.isLoggedIn()){
            $location.path("/master");
        }
    })
})

function in_array(needle, haystack, argStrict){
  var key = '',
  strict = !! argStrict;
 
  if(strict){
    for(key in haystack){
      if(haystack[key] === needle){
        return true;
      }
    }
  }else{
    for(key in haystack){
      if(haystack[key] == needle){
        return true;
      }
    }
  }
  return false;
}