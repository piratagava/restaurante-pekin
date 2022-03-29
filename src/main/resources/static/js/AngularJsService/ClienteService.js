app.service("ClienteServices", function() {

	this.ocultarElementos = function() {
		//son componentes de la web ocultos
		$("#formClienteGuardar").hide();
		$("#encabezadoCliente").show();
		$("#tablePricipal").hide();
		cargarTable(); // realizo la peticion al servidor que me de la lista de todos los clientes
		document.getElementById("btnHideTable").disabled = true;
	}

	this.obtengoToken = function() {
		//define storage para ver si existe una sesion
		var TOKEN_KEY = "jwtToken";
		var storage = localStorage.getItem(TOKEN_KEY);
		var cachedToken;

		if (storage == null) {
			window.location.href = "/index";
		}
	}

	this.createAuthorizationTokenHeader = function() {
		if (storage) {
			return { "Authorization": "Bearer " + storage };
		} else {
			return {};
		}
	}

	//Existe varias formas de validar campos pero este metodo lo tome de un colega fywer porque es bueno en javascript
	this.validarDatosCliente = function(nombreCliente, apellidoClienteP, apellidoClienteM, passwordCliente) {
		//variable binary de inicio
		var valido = 16;

		if (nombreCliente.length > 0) {
			let validaNombre = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/
			let esValido = validaNombre.exec(nombreCliente);

			if (esValido == null || nombreCliente.length == 0 || /^\s+$/.test(nombreCliente)) {
				alertify.notify('Nombre del Cliente es incorrecto', 'error', 3, null);
			} else {
				//recorre en binary a la derecha uno para posicionar en otro estado (32) test no acepte espacios todo en blanco
				valido >>= 1;
			}
		}

		if (apellidoClienteP.length > 0) {
			let validaApellidoP = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/
			let esValido = validaApellidoP.exec(apellidoClienteP);

			if (esValido == null || apellidoClienteP.length == 0 || /^\s+$/.test(apellidoClienteP)) {
				alertify.notify('Apellido Paterno del Cliente es incorrecto', 'error', 3, null);
			} else {
				//recorre en binary a la derecha uno para posicionar en otro estado (32) test no acepte espacios todo en blanco
				valido >>= 1;
			}
		}

		if (apellidoClienteM.length > 0) {
			let validaApellidoM = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/
			let esValido = validaApellidoM.exec(apellidoClienteM);

			if (esValido == null || apellidoClienteM.length == 0 || /^\s+$/.test(apellidoClienteM)) {
				alertify.notify('Apellido Materno del Cliente es incorrecto', 'error', 3, null);
			} else {
				//recorre en binary a la derecha uno para posicionar en otro estado (32) test no acepte espacios todo en blanco
				valido >>= 1;
			}
		}


		if (passwordCliente.length > 0) {
			let validaPassword = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/
			let esValido = validaPassword.exec(passwordCliente);

			if (esValido == null || passwordCliente.length == 0 || /^\s+$/.test(passwordCliente)) {
				alertify.notify('Password del Cliente es incorrecto', 'error', 3, null);
			} else {
				//recorre en binary a la derecha uno para posicionar en otro estado (32) test no acepte espacios todo en blanco
				valido >>= 1;
			}
		}
		return valido;
	}


});