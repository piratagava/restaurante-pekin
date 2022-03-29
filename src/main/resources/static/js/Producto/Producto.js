var app = angular.module('AltaProductos', ['ngResource']);
app.controller('controllerProducto', ["$scope", "$http", "VentasService", function($scope, $http, VentasService) {

	var TOKEN_KEY = "jwtToken";
	var storage = localStorage.getItem(TOKEN_KEY);
	function removeJwtToken() {
		localStorage.removeItem(TOKEN_KEY);
		window.location.href = "/index";
	}

	function doLogout() {
		removeJwtToken();
	}

	VentasService.ocultarElementosInicialmente();
	//InicargarTableVentascio cargando los registros
	cargarTableVentas();

	$scope.openAgregarMenu = function() {
		$("#formVentaGuardar").show();
		$("#menuForm")[0].reset();
		$("#tablePricipal").hide();
		document.getElementById("btnShowTable").disabled = false;
		document.getElementById("btnAddMenu").disabled = true;
	};

	$scope.cerrarFormularioMenu = function() {
		$("#formVentaGuardar").hide();
		$("#menuForm")[0].reset();
		document.getElementById("btnAddMenu").disabled = false;
	};

	$scope.openListarMenu = function() {
		$("#tablePricipal").show();
		$("#formVentaGuardar").hide();
		document.getElementById("btnShowTable").disabled = true;
		document.getElementById("btnAddMenu").disabled = false;
	};

	$scope.guardarDatosMenu = function() {

		let lector = new FileReader();
		let entradaFoto = document.querySelector('#iptFoto');
		let archivo = entradaFoto.files;
		let blob = new Blob(archivo, { type: 'image/jpeg' });

		let nombreProducto = document.getElementById("txtNombre").value;
		let descripcionProducto = document.getElementById("txtDescripcion").value;
		let PrecioProducto = document.getElementById("txtNumTotal").value;

		lector.readAsBinaryString(blob);
		lector.onload = () => {
			let jpge64 = window.btoa(lector.result);

			let producto = {
				"foto": jpge64,
				"nombre": nombreProducto,
				"descripcion": descripcionProducto,
				"precio": PrecioProducto
			};

			let valido = VentasService.validarDatos(nombreProducto, descripcionProducto, PrecioProducto);
			if (valido === 1) {
				$http({
					method: 'post',
					url: 'insertarProducto',
					headers: VentasService.createAuthorizationTokenHeader(),
					data: producto
				}).then(function onSuccess(response) {
					alertify.notify("Se Registro Correctamente", "success", 5, null);
					$('#tablaMenu').DataTable().ajax.reload();
					$("#menuForm")[0].reset();					
				}).catch(function onError(response) {
					alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
					window.location.href = "/index";
				});
			} else {
				alertify.notify("Capture todos los campos Correctamente", "error", 5, null);
			}
		};
	};

	$scope.actualizarDatos = function() {
		let confirm = alertify.confirm('Actualizar Datos', 'Desea Actualizar el registro ?', null, null).set('labels', {
			ok: 'Si',
			cancel: 'Cancelar'
		});
		confirm.set({
			transition: 'slide'
		});

		confirm.set('onok', function() {
			var idMenu = $("#txtidVenta2").val();
			var nombreMenu = $("#txtNombre2").val();
			var descripcionMenu = $("#txtDescripcion2").val();
			var precioMenu = $("#txtNumTotal2").val();

			let lector = new FileReader();
			let entradaFoto = document.querySelector('#iptFoto2');
			let archivo = entradaFoto.files;
			let blob = new Blob(archivo, { type: 'image/jpeg' });
			console.log(archivo);

			lector.readAsBinaryString(blob);
			lector.onload = () => {
				let jpge64 = window.btoa(lector.result);

				let producto = {
					"idMenu": idMenu,
					"foto": jpge64,
					"nombre": nombreMenu,
					"descripcion": descripcionMenu,
					"precio": precioMenu
				};

				let valido = VentasService.validarDatos(nombreMenu, descripcionMenu, precioMenu);
				if (valido === 1) {
					$http({
						method: 'put',
						url: 'actualizarProducto',
						headers: VentasService.createAuthorizationTokenHeader(),
						data: producto
					}).then(function onSuccess(response) {
						alertify.notify("Se Actualizo Correctamente el menu", "success", 5, null);
						$('#tablaMenu').DataTable().ajax.reload();
						$("#menuForm")[0].reset();
						$('#myModalVenta').modal('toggle');
					}).catch(function onError(response) {
						alertify.notify('ASEGURATE CAMBIAR LA FOTO O VUELVE A SELECCIONARLA', 'error', 5, null);
						alertify.notify('Se genero error interno intente mas tarde', 'error', 5, null);
						window.location.href = "/index";
					});
				} else {
					alertify.notify("Capture todos los campos Correctamente", "Error", 5, null);
				}
			};
		});
		confirm.set('oncancel', function() {
			alertify.error('Cancelo Actualizar su registro', 3);
		});
	}


	function cargarTableVentas() {
		var datatable = $('#tablaMenu').DataTable({
			"ajax": {
				"url": 'listarProductos',
				"type": 'GET',
				"dataSrc": "",
				"dataType": "json",
				"headers": { "Authorization": "Bearer " + storage },
				"error": function(jqXHR) {
					alertify.notify("Error en la vista de la Tabla" + jqXHR.statusText + " " + jqXHR.responseText, 'error', 10, null);
					window.location.href = "/index";
				}
			},
			"columns": [
				{ "data": "idMenu" },
				{
					"data": "foto", render: function(data) {
						return '<img src="data:image/jpeg;base64,' + data + '"width="100px" height="110px">';
					}
				},
				{ "data": "nombre" },
				{ "data": "descripcion" },
				{ "data": "precio" }
			],
			"columnDefs": [{
				"targets": 5,
				"bSortable": false,
				"render": function() {
					return '<button type="button" id="editar" class="editar edit-modal btn btn-outline-primary botonEditar"><span class="fa fa-pen"></span><span class="hidden-xs"></span></button>';
				}
			}, {
				"targets": 6,
				"data": null,
				"bSortable": false,
				"mRender": function(o) {
					return '<a class="btn btn-outline-danger" onclick="dialogEliminar(' + o.idMenu + ')" type="button"><span class="fas fa-trash-alt"></span><span class="hidden-xs"></span></a>';
				}
			}]
		});

		var editar = function(tbody, table) {
			$(tbody).on("click", "button.editar", function() {
				if (table.row(this).child.isShown()) {
					var data = table.row(this).data();
				} else {
					var data = table.row($(this).parents("tr")).data();
				}
				$('#myModalVenta').modal('show');

				$("#txtidVenta2").val(data.idMenu);
				$("#txtNombre2").val(data.nombre);
				console.log(data.foto);

				let urlImagen = cargarBufferJPGE(data.foto);
				desplegarFotoParaActualizar(urlImagen);

				$("#txtDescripcion2").val(data.descripcion);
				$("#txtNumTotal2").val(data.precio);
			});
		};
		editar("#tablaMenu tbody", datatable);
	}
}]);

let cargarBufferJPGE = (jpg64) => {
	let decodedData = window.atob(jpg64);
	let buffer = new ArrayBuffer(jpg64.length);
	let view = new Uint8Array(buffer);
	for (let i = 0; i < jpg64.length; i++) {
		view[i] = decodedData.charCodeAt(i);
	}

	let entradaFoto = document.querySelector('#iptFoto');
	let file = new File([view], "foto.jpeg", { type: 'image/jpeg' });

	let url = URL.createObjectURL(file);
	return url;
};

window.addEventListener('load', () => {
	//evento el cual se dispara cuando el evento de la foto es cambiada.
	let inputFoto = document.querySelector('#iptFoto');
	inputFoto.addEventListener('change', () => {
		if (desplegarFotoGuardar() === false) {
			inputFoto.value = "";
		}
	});

	let inputFoto2 = document.querySelector('#iptFoto2');
	inputFoto2.addEventListener('change', () => {
		if (desplegarFotoUpdateParaHacerCambios() === false) {
			inputFoto2.value = "";
		}
	});

});

//desplegarFoto
let desplegarFotoGuardar = () => {
	/**
	 * Despliega la foto de alumno una vez haya sido seleccionada.
	 */
	let entradaFoto = document.querySelector('#iptFoto');
	let lienzo = document.querySelector("#divLienzo");
	while (lienzo.firstChild) {
		lienzo.removeChild(lienzo.firstChild);
	}
	/**
	 * Contiene la informaciÃ³n de todos los archivos seleccionado
	 */
	let archivo = entradaFoto.files;
	let foto = document.createElement('img');
	foto.setAttribute("width", "65%");

	foto.src = URL.createObjectURL(archivo[0]);
	lienzo.appendChild(foto);
};

//desplegarFoto
let desplegarFotoUpdateParaHacerCambios = () => {
	/**
	 * Despliega la foto de alumno una vez haya sido seleccionada.
	 */
	let entradaFoto = document.querySelector('#iptFoto2');
	let lienzo = document.querySelector("#divLienzo2");
	while (lienzo.firstChild) {
		lienzo.removeChild(lienzo.firstChild);
	}
	/**
	 * Contiene la informaciÃ³n de todos los archivos seleccionado
	 */
	let archivo = entradaFoto.files;
	let foto = document.createElement('img');
	foto.setAttribute("width", "65%");

	foto.src = URL.createObjectURL(archivo[0]);
	lienzo.appendChild(foto);
};


//desplegarFoto cuando doy click en actualizar
let desplegarFotoParaActualizar = (url) => {
	let lienzo = document.querySelector("#divLienzo2");
	while (lienzo.firstChild) {
		lienzo.removeChild(lienzo.firstChild);
	}

	let foto = document.createElement('img');
	foto.setAttribute("width", "80%");
	foto.setAttribute("id", "imgFoto");
	//foto.src = URL.createObjectURL(url);
	foto.src = url;
	lienzo.appendChild(foto);
};



function dialogEliminar(idMenu) {
	let confirm = alertify.confirm('Eliminar Venta', 'Desea eliminar el registro ?', null, null).set('labels', {
		ok: 'Si',
		cancel: 'Cancelar'
	});
	confirm.set({
		transition: 'slide'
	});

	confirm.set('onok', function() {
		eliminarVenta(idMenu);
	});

	confirm.set('oncancel', function() { //llama al pulsar botón negativo
		alertify.error('Cancelo eliminar su registro', 3);
	});
}

var TOKEN_KEY = "jwtToken";
var storage = localStorage.getItem(TOKEN_KEY);
function removeJwtToken() {
	localStorage.removeItem(TOKEN_KEY);
	window.location.href = "/index";
}

function doLogout() {
	removeJwtToken();
}

function eliminarVenta(idMenus) {
	var id_p = idMenus;
	let json = {};
	json.idMenu = id_p;
	$.ajax({
		type: 'DELETE',
		contentType: 'application/json',
		dataType: 'json',
		url: 'eliminarMenu',
		data: JSON.stringify(json),
		headers: { "Authorization": "Bearer " + storage },
		success: function(data) {
			alertify.notify("Se elimino correctamente", 'success', 10, null);
			$('#tablaMenu').DataTable().ajax.reload();
		},
		error: function(response) {
			if (response.status == 401) {
				alertify.notify("Error Expiro su Sesion Vuelva De Entrar", 'error', 10, null);
				 window.location.href = "/index";
			}
		}
	});
}