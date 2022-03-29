var app = angular.module('ListaProductosActivos', ['ngResource']);
app.controller('controllerProductoListaActivos', ["$scope", "$http", function($scope, $http) {

	var TOKEN_KEY = "jwtToken";
	var storage = localStorage.getItem(TOKEN_KEY);


	$http({
		url: 'listarDetalleProducto', contentType: "application/json; charset=utf-8", dataType: "json",
		headers: { "Authorization": "Bearer " + storage }
	}).then(function(response) {
		$scope.productosActivos = response.data;
	}, function errorCallback(response) {
		alertify.notify("Sin Permisos Suficientes ah caducado su sesion", "error", 10, null);
		window.location.href = "/index";
	});


	$scope.desactivoEtregado = function(activo, id_detalle) {
		var pasoDesactivar = activo;
		var detalleId = id_detalle;
		console.log(detalleId);
		if (pasoDesactivar == 'activo') {
			alertify.confirm('Envio de Pedido', 'Desea Enviar y Hacer Entrega del pedido', function() {
				$http({
					method: 'put', url: 'actualizarDetalle', headers: { "Authorization": "Bearer " + storage },
					data: { idDetalle: detalleId }
				}).then(function onSuccess(response) {
					$http({
						method: 'get', url: 'listarDetalleProducto', headers: { "Authorization": "Bearer " + storage },
						data: { idDetalle: detalleId }
					}).then(function onSuccess(response) {
						$scope.productosActivos = response.data;
						alertify.set('notifier', 'position', 'top-center');
						alertify.notify('Pedido Atendido y Enviado', 'success', 6, null);
					}).catch(function onError(response) {
						alertify.notify("Sin Permisos Suficientes ah caducado su sesion", "error", 10, null);
						window.location.href = "/index";
					});
				}).catch(function onError(response) {
					if (response == false) {
						alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
					}
					if (response.status == 401) {
						alertify.notify('Su Sesion Expiro Inicie Sesion Nuevamente', 'error', 8, null);
						window.location.href = "/index";
					}
				});
			}, function() {
				alertify.set('notifier', 'position', 'top-center');
				alertify.notify('Cancelo Enviar El Pedido', 'error', 4, null);

			});
		}
	};
}]);