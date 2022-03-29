/**
 * Created by hernan Garcia Valladares on 10.12.21.
 */

$(function() {
	// VARIABLES =============================================================
	var TOKEN_KEY = "jwtToken"
	var $notLoggedIn = $("#notLoggedIn");
	var $loggedIn = $("#loggedIn").hide();
	var $login = $("#login");
	var $userInfo = $("#userInfo").hide();
	var $habilitarSiEstaLogeado = $("#habilitarSiEstaLogeado").hide();


	// FUNCTIONS =============================================================
	function getJwtToken() {
		return localStorage.getItem(TOKEN_KEY);
	}

	function setJwtToken(token) {
		localStorage.setItem(TOKEN_KEY, token);
	}

	function removeJwtToken() {
		localStorage.removeItem(TOKEN_KEY);
	}

	function doLogin(loginData) {
		$.ajax({
			url: 'api/authenticate',
			type: 'POST',
			data: JSON.stringify(loginData),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				setJwtToken(data.id_token);
				showUserInformation();		
				top.location.href="public-menu";

			},
			error: function(jqXHR, textStatus, errorThrown) {
				if (jqXHR.status === 401) {
					alertify.notify(" Sus Datos no son Correctos Verifique Nuevamente", 'error', 10, null);
				} else {
					throw new Error("an unexpected error occured: " + errorThrown);
				}
			}
		});
	}

	function showUserInformation() {
		//Hago esta peticion para saber si es ROLE_USER
		$.ajax({
			url: 'api/cliente',
			type: 'GET',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			headers: createAuthorizationTokenHeader(),
			success: function(response) {

			},
			error: function(response) {
				if (response.status == 403) {
					//si desde esta peticion no es ROLE_USER significa que es administrador
					//muestro los datos de resources para admin
					$login.hide();
					$notLoggedIn.hide();
					$habilitarSiEstaLogeado.show();
				}
			}
		});
	}

	function doLogout() {
		removeJwtToken();
		$login.show();
		$habilitarSiEstaLogeado.hide();
		$notLoggedIn.show();
		$("#loginForm")[0].reset();
	}

	function createAuthorizationTokenHeader() {
		var token = getJwtToken();
		if (token) {
			return { "Authorization": "Bearer " + token };
		} else {
			return {};
		}
	}

	function showTokenInformation() {
		$loggedIn
			.text("Token: " + getJwtToken())
			.attr("title", "Token: " + getJwtToken())
			.show();
	}

	function showResponse(statusCode, message) {
		$response
			.empty()
			.text(
				"status code: "
				+ statusCode + "\n-------------------------\n"
				+ (typeof message === "object" ? JSON.stringify(message) : message)
			);
	}

	// CUANDO INICIA EL EVENTO BOTON SUBMIT DEL FORM
	// =============================================================
	$("#loginForm").submit(function(event) {
		event.preventDefault();
		var $form = $(this);
		var formData = {
			username: $form.find('input[name="username"]').val(),
			password: $form.find('input[name="password"]').val()
		};
		doLogin(formData);
	});

	$("#logoutButton").click(doLogout);

	$loggedIn.click(function() {
		$loggedIn
			.toggleClass("text-hidden")
			.toggleClass("text-shown");
	});


	$("#logoutButtons").click(cerrarSesionModal);
	function cerrarSesionModal() {
		//depende como se llame tu app cambia de ruta
		window.location.href = "/index";
	}

	// =============================================================
	if (getJwtToken()) {
		/*
		 * Una ves generado el Token verificamos si no a expirado para mostrar
		 * el menu del Admin
		 */
		const { exp } = getJwtToken();
		if (Date.now() <= exp * 1000) {
			$login.hide();
			$notLoggedIn.hide();
			showTokenInformation();
			$habilitarSiEstaLogeado.show();
		} else {
			$login.show();
			$notLoggedIn.show();
			showTokenInformation();
			$habilitarSiEstaLogeado.hide();
			removeJwtToken();
		}
	}
});
