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
	.when("/sofiaApp",{templateUrl: "tpl/login.tpl.html", controller : "contrLog"})
        .when("/master", {templateUrl :  "tpl/masterPage.html" })
        .when("/GestionProducto", {templateUrl :  "tpl/forms/gestionProducto.html", controller :"contrPro" })
        .when("/GestionCliente", {templateUrl :  "tpl/forms/gestionCliente.html", controller :"contrCli" })
        .when("/GestionProveedor", {templateUrl :  "tpl/forms/gestionProveedor.html", controller :"contrProvee" })
        .when("/GestionCategoria", {templateUrl :  "tpl/forms/gestionCategoria.html", controller :"contrCategoria as vm" })
        .when("/GestionMarca", {templateUrl :  "tpl/forms/gestionMarca.html", controller :"contrMarca as vm" })
        .when("/GestionUsuario", {templateUrl :  "tpl/forms/gestionUsuario.html", controller :"contrUsuario as vm" })
	.otherwise({redirectTo : "/sofiaApp"})
;
}]);