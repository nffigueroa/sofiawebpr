'use strict';

var app = angular.module('sofiaApp',['ngRoute',
                                    'ngCookies',
                                    'ngTable',
                                    'ui.grid',
                                    'ui.grid.edit',
                                    'checklist-model'
                                    ])
.config(['$routeProvider', function ($routeProvider){
	$routeProvider
	.when("/sofiaApp",{templateUrl: "tpl/login.tpl.html", controller : "contrLog as vm"})
        .when("/master", {templateUrl :  "tpl/masterPage.html" })
        .when("/GestionProducto", {templateUrl :  "tpl/forms/gestionProducto.html", controller :"contrPro as vm" })
        .when("/GestionCliente", {templateUrl :  "tpl/forms/gestionCliente.html", controller :"contrCli as vm" })
        .when("/GestionProveedor", {templateUrl :  "tpl/forms/gestionProveedor.html", controller :"contrProvee as vm" })
        .when("/GestionCategoria", {templateUrl :  "tpl/forms/gestionCategoria.html", controller :"contrCategoria as vm" })
        .when("/GestionMarca", {templateUrl :  "tpl/forms/gestionMarca.html", controller :"contrMarca as vm" })
        .when("/GestionUsuario", {templateUrl :  "tpl/forms/gestionUsuario.html", controller :"contrUsuario as vm" })
        .when("/GestionIngresoInventario", {templateUrl :  "tpl/forms/gestionEntradaInventario.html", controller :"contrInventario as vm" })
        .when("/GestionCorteCaja", {templateUrl :  "tpl/forms/gestionCorteCaja.html", controller :"contrCorteCaja as vm" })
        .when("/GestionCajonDinero", {templateUrl :  "tpl/forms/gestionCajonDinero.html", controller :"contrCajonDinero as vm" })
	.otherwise({redirectTo : "/sofiaApp"})
;
}]);
