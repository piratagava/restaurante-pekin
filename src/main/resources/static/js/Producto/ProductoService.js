app.service("VentasService", function () {

   this.ocultarElementosInicialmente = function () {
      //son componentes de la web ocultos inicialmente
      $("#formVentaGuardar").hide();
      $("#tablePricipal").hide();
   }
   
   	this.obtengoToken = function() {
		//define storage para ver si existe una sesion
		var TOKEN_KEY = "jwtToken";
		var storage = localStorage.getItem(TOKEN_KEY);
		var cachedToken;

		if (storage == null) {
			//window.location.href = "/es-login-page-cfe";
		}
	}

	this.createAuthorizationTokenHeader = function() {
		if (storage) {
			return { "Authorization": "Bearer " + storage };
		} else {
			return {};
		}
	}

   this.validarDatos = function (nombreProducto, descripcionProducto, PrecioProducto) {
      //variable binary de inicio
      var valido = 8;

      if (nombreProducto.length > 0) {
         let validaDescripcion = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/;
         let esValido = validaDescripcion.exec(nombreProducto);

         if (esValido == null || nombreProducto.length == 0 || /^\s+$/.test(nombreProducto)) {
            alertify.notify('Nombre del producto es incorrecto', 'error', 3, null);
         } else {
            valido >>= 1;
         }
      }


      if (descripcionProducto.length > 0) {
         let validaDescripcion = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/;
         let esValido = validaDescripcion.exec(descripcionProducto);

         if (esValido == null || descripcionProducto.length == 0 || /^\s+$/.test(descripcionProducto)) {
            alertify.notify('La descripcion es incorrecto', 'error', 3, null);
         } else {
            valido >>= 1;
         }
      }

      if (PrecioProducto.length > 0) {
         let validaNumTotal = /^[0-9]{1,15}$/;
         let esValido = validaNumTotal.exec(PrecioProducto);

         if (esValido == null || PrecioProducto <= 0) {
            alertify.notify('El Precio total no ah sido validado', 'error', 3, null);
         } else {
            valido >>= 1;
         }
      }
      return valido;
   };

});