app.service("UbicacionService", function () {

  this.validarDatosUbicacion = function (carreteraCliente, referenciaCliente, nombreCliente, numeroTelefono) {
    //variable binary de inicio
    var valido = 16;

    if (carreteraCliente.length > 0) {
      let validaNombre = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/;
      let esValido = validaNombre.exec(carreteraCliente);
      if (esValido === null || carreteraCliente.length === 0 || /^\s+$/.test(carreteraCliente)) {
        alertify.notify('Coloca correctamente tu Carretera', 'error', 5, null);
      } else {
        valido >>= 1;
      }
    }

    if (referenciaCliente.length > 0) {
      let validaNombre = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/;
      let esValido = validaNombre.exec(referenciaCliente);
      if (esValido === null || referenciaCliente.length === 0 || /^\s+$/.test(referenciaCliente)) {
        alertify.notify('Coloca correctamente tu referencia', 'error', 5, null);
      } else {
        valido >>= 1;
      }
    }


    if (nombreCliente.length > 0) {
      let validaNombre = /^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$/;
      let esValido = validaNombre.exec(nombreCliente);

      if (esValido === null || nombreCliente.length === 0 || /^\s+$/.test(nombreCliente)) {
        alertify.notify('Nombre del Cliente es incorrecto', 'error', 5, null);
      } else {
        valido >>= 1;
      }
    }

    if (numeroTelefono.length > 0) {
      let validaNumTotalPago = /^[0-9]{1,10}$/;
      let esValido = validaNumTotalPago.exec(numeroTelefono);
      if (esValido === null || numeroTelefono <= 0) {
        alertify.notify('El número de telefono no ha sido validado', 'error', 5, null);
      } else {
        valido >>= 1;
      }
    }

    return valido;
  };
});