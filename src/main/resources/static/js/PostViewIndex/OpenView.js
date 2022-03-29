var app = angular.module('MuestraProductos', ['ngResource']);
app.controller('controllerProducto', ["$scope", "$http", "UbicacionService", function ($scope, $http, UbicacionService) {

    localStorage.clear();
    //oculto mi icono flotante de pago
    document.getElementById("muestroIconPago").hidden = true;

    $http.get("listarProductosIndex").then(function (response) {
      $scope.productos = response.data;
    });
    
    $http.get("listarDetalleProducto").then (function (response){
		$scope.productosActivos = response.data;
	});

    $scope.mandoInfoProducto = function (id, foto, name, descripcion, precioUnitario) {
      var fotoImg = foto;
      $scope.foto = fotoImg;
      $('#modalOrden').modal('show');
      $scope.datosCliente = {idMenu: id, nombre: name, descripcion: descripcion, precio: precioUnitario};
      //limpiamos input
      $scope.mostrarCalculoTotal = '';
      $scope.cantidadProducto = '';      
    };

    $scope.guardoProductoSeleccionadoDetalles = [];
    $scope.localidadesAceptadas = ["Potzontepec", "El Calvarito", "3 de Mayo", "El Ranchito", "Manialt"];

    $scope.pushProducto = function (idMenu, nameProducto, descripcion, precioUnitario) {
      var obtengoPorIdCantidadProducto = document.getElementById("cantidadProducto").value;
      //mayor de 6 no permite por desbordamiento de operacion en memoria
      if (obtengoPorIdCantidadProducto.length >= 6) {
        alertify.set('notifier', 'position', 'top-center');
        alertify.error('Cantidad No valida');
      } else {
        var costoProductoObtenido = $scope.datosCliente.precio;
        var obtengoCantidadProducto = $scope.cantidadProducto;
        var validar = validarInputCantidad(obtengoPorIdCantidadProducto);
        var banderaExisteIdMenu = 0;

        //verifica si existe el producto en array por medio de id
        var verificarProductoExiste = $scope.guardoProductoSeleccionadoDetalles.length;
        for (i = 0; i < verificarProductoExiste; i++) {
          if ($scope.guardoProductoSeleccionadoDetalles[i].menu.idMenu === idMenu) {
            banderaExisteIdMenu++;
          }
        }

        if (banderaExisteIdMenu === 0) {
          if (validar === 1) {
            //realizo la operacion del monto a pagar         
            var calculoTotal = costoProductoObtenido * obtengoCantidadProducto;
            //Sirve para Guardar los productos seleccionados y mostrarlo despues en detalles
            var datosDetalleProducto = {menu: {idMenu: idMenu}, nombre: nameProducto, descripcion: descripcion, precio: precioUnitario, totalPago: calculoTotal, cantidad: obtengoCantidadProducto};

            $scope.mostrarCalculoTotal = calculoTotal;
            //en angularjs para facilitar el muestreo de confirmacion en detalle
            $scope.guardoProductoSeleccionadoDetalles.push(datosDetalleProducto);           
            alertify.set('notifier', 'position', 'top-center');
            alertify.success('Producto Agregado');

            setTimeout(function () {
              $('#modalOrden').modal('hide');
            }, 2000);
            
            $scope.cantidadProducto = '';
            document.getElementById("muestroIconPago").hidden = false;
          } else {
            alertify.set('notifier', 'position', 'top-center');
            alertify.error('Asegurese poner una cantidad correcta !');
          }
        } else {
          alertify.set('notifier', 'position', 'top-center');
          alertify.notify('Producto ya agregado, puede modificar su pedido en Realizar Compra', 'error', 6, null);
          $('#modalOrden').modal('hide');
        }
      }
    };

    validarInputCantidad = function (inputPrecio) {
      //variable binary de inicio
      var valido = 2;
      if (inputPrecio.length > 0) {
        let validaNumTotal = /^[0-9]{1,15}$/;
        let esValido = validaNumTotal.exec(inputPrecio);

        if (esValido === null || inputPrecio <= 0) {
          alertify.notify('Su cantidad no ah sido valido', 'error', 3, null);
        } else {
          valido >>= 1;
        }
      }
      return valido;
    };

    $scope.realizarCompra = function () {
      $scope.seleccionLocalidad = "";
      $('#modalDetallesProducto').modal('show');
      $('#totalPago').hide();
      document.getElementById("realizaCompra").disabled = true;      
    };

    $scope.seleccionLocalidad = null;
    var suma = 0;
    $scope.calculaCostoSegunLocalidad = function () {
      //Potzo envio cuota de envio es 25
      if ($scope.seleccionLocalidad === "Potzontepec") {
        suma = 0;
        var paraObtenerTamañoProductosHacerSuma = $scope.guardoProductoSeleccionadoDetalles.length;
        for (i = 0; i < paraObtenerTamañoProductosHacerSuma; i++) {
          suma += $scope.guardoProductoSeleccionadoDetalles[i].totalPago;
        }
        $scope.sumaTotal = suma + 25;
        $scope.tiempoEnvio = "Su pedido llega en 25 minutos aproximadamente";
        $('#totalPago').show();
        document.getElementById("realizaCompra").disabled = false;
        //Clavarito cuota de envio es de 30
      } else if ($scope.seleccionLocalidad === "El Calvarito") {
        suma = 0;
        var paraObtenerTamañoProductosHacerSuma = $scope.guardoProductoSeleccionadoDetalles.length;
        for (i = 0; i < paraObtenerTamañoProductosHacerSuma; i++) {
          suma += $scope.guardoProductoSeleccionadoDetalles[i].totalPago;
        }
        $scope.sumaTotal = suma + 30;
        $('#totalPago').show();
        document.getElementById("realizaCompra").disabled = false;
        $scope.tiempoEnvio = "Su pedido llega en 30 minutos aproximadamente";
        //3 de Mayo cuota de envio es de 30  
      } else if ($scope.seleccionLocalidad === "3 de Mayo") {
        suma = 0;
        var paraObtenerTamañoProductosHacerSuma = $scope.guardoProductoSeleccionadoDetalles.length;
        for (i = 0; i < paraObtenerTamañoProductosHacerSuma; i++) {
          suma += $scope.guardoProductoSeleccionadoDetalles[i].totalPago;
        }
        $scope.sumaTotal = suma + 30;
        $('#totalPago').show();
        document.getElementById("realizaCompra").disabled = false;
        $scope.tiempoEnvio = "Su pedido llega en 30 minutos aproximadamente";
        //El Ranchito cuota de envio es de 35
      } else if ($scope.seleccionLocalidad === "El Ranchito") {
        suma = 0;
        var paraObtenerTamañoProductosHacerSuma = $scope.guardoProductoSeleccionadoDetalles.length;
        for (i = 0; i < paraObtenerTamañoProductosHacerSuma; i++) {
          suma += $scope.guardoProductoSeleccionadoDetalles[i].totalPago;
        }
        $scope.sumaTotal = suma + 35;
        $('#totalPago').show();
        document.getElementById("realizaCompra").disabled = false;
        $scope.tiempoEnvio = "Su pedido llega en 35 minutos aproximadamente";
        //Clavarito cuota de envio es de 55
      } else if ($scope.seleccionLocalidad === "Manialt") {
        suma = 0;
        var paraObtenerTamañoProductosHacerSuma = $scope.guardoProductoSeleccionadoDetalles.length;
        for (i = 0; i < paraObtenerTamañoProductosHacerSuma; i++) {
          suma += $scope.guardoProductoSeleccionadoDetalles[i].totalPago;
        }
        $scope.sumaTotal = suma + 55;
        $('#totalPago').show();
        document.getElementById("realizaCompra").disabled = false;
        $scope.tiempoEnvio = "Su pedido llega en 50 minutos aproximadamente";
      } else if ($scope.seleccionLocalidad !== "Potzontepec" && $scope.seleccionLocalidad !== "El Calvarito" && $scope.seleccionLocalidad !== "3 de Mayo" && $scope.seleccionLocalidad !== "El Ranchito" && $scope.seleccionLocalidad !== "Manialt") {
        document.getElementById("realizaCompra").disabled = true;
        $('#totalPago').hide();
      }
    };

    $scope.mostrarUpdateProducto = function (nombre, descripcion, precio, totalPago, cantidad) {
      $scope.detalleProducto = {};
      var nombreMenu = nombre;
      var descripcionMenu = descripcion;
      var precioMenu = precio;
      var totalPagoMenu = totalPago;
      var cantidadMenu = cantidad;
      //reasignamos los datos enviados a nuestro json ......numero si											
      $scope.detalleProducto.nombre = nombreMenu;
      $scope.detalleProducto.descripcion = descripcionMenu;
      $scope.detalleProducto.precio = precioMenu;
      $scope.detalleProducto.totalPago = totalPagoMenu;
      $scope.detalleProducto.cantidad = cantidadMenu;

      $scope.cantidadNuevaProducto = "";
      $('#modalDetallesProducto').modal('hide');
      $('#modalModificaCantidad').modal('show');
    };

    $scope.mostrarModalDetalleProducto = function () {
      $('#modalDetallesProducto').modal('show');
      $scope.cantidadNuevaProducto = "";
    };


    $scope.actualizaCantidadNueva = function (nombre, descripcion) {
      $scope.detalleProductoUpdateCantidad = {};
      var nombreMenu = nombre;
      var descripcionMenu = descripcion;

      $scope.seleccionLocalidad = "";
      $('#totalPago').hide();
      document.getElementById("realizaCompra").disabled = true;
      //Sirve para mostrar los detalle detalle en el  modal									
      $scope.detalleProductoUpdateCantidad.nombre = nombreMenu;
      $scope.detalleProductoUpdateCantidad.descripcion = descripcionMenu;

      var recorreGuardoProductoSeleccionadoDetalles = $scope.guardoProductoSeleccionadoDetalles;
      var obtengoNuevaCantidadProducto = $scope.cantidadNuevaProducto;

      //para validar el dato
      var obtengoCantidadProductoNuevo = document.getElementById("cantidadProductoUpdate").value;
      var validar = validarInputCantidad(obtengoCantidadProductoNuevo);

      if (validar === 1 && obtengoCantidadProductoNuevo.length < 6) {
        recorreGuardoProductoSeleccionadoDetalles.map(function (dato) {
          if (dato.nombre === nombreMenu && dato.descripcion === descripcionMenu) {
            dato.cantidad = obtengoNuevaCantidadProducto;
            var obtnegoDatoPrecioUnitarioProductoSeleccionado = dato.precio;
            var calculoTotalCantidadNueva = obtnegoDatoPrecioUnitarioProductoSeleccionado * obtengoNuevaCantidadProducto;
            dato.totalPago = calculoTotalCantidadNueva;
          }
          return dato;
        });

        alertify.set('notifier', 'position', 'top-center');
        alertify.success('Cantidad Modificada Correctamente :) Porfavor vuelva calcular su costo de envio', 6, null);
        $scope.cantidadNuevaProducto = "";
      } else {
        alertify.set('notifier', 'position', 'top-center');
        alertify.error('Asegurese poner una cantidad correcta !');
        $scope.cantidadNuevaProducto = "";
      }


      $('#modalModificaCantidad').modal('hide');
      $scope.cantidadNuevaProducto = "";

      //Realizo el recalculo total de todos los productos y muestro el modal detalles
      $scope.guardoProductoSeleccionadoDetalles;      
      var paraObtenerTamañoProductosHacerSuma = $scope.guardoProductoSeleccionadoDetalles.length;
      var suma = 0;
      for (i = 0; i < paraObtenerTamañoProductosHacerSuma; i++) {
        suma += $scope.guardoProductoSeleccionadoDetalles[i].totalPago;
      }
      $scope.sumaTotal = suma;
      $('#modalDetallesProducto').modal('show');
    };

    $scope.quitarProducto = function (index) {
      $scope.seleccionLocalidad = "";
      $('#totalPago').hide();
      document.getElementById("realizaCompra").disabled = true;
      //no con filter uso basico con $index      
      $scope.guardoProductoSeleccionadoDetalles.splice(index, 1);

      //significa que no existe mas productos en el array agregados
      var paraObtenerTamañoProductosHacerSuma = $scope.guardoProductoSeleccionadoDetalles.length;
      if (paraObtenerTamañoProductosHacerSuma === 0) {
        $('#modalDetallesProducto').modal('hide');
        document.getElementById("muestroIconPago").hidden = true;
      } else {
        alertify.set('notifier', 'position', 'top-center');
        alertify.success('Por seguridad vuelva seleccionar el costo de envio seleccionando tu ubicacion', 7, null);
      }
    };

    $scope.realizarSiguienteProcesoPedido = function () {
      $('#confirmarCompraFinal').modal('show');
      $('#modalDetallesProducto').modal('hide');
    };

    $scope.pushProductoHide = function () {
      $scope.seleccionLocalidad = "";
      $('#modalDetallesProducto').modal('hide');
    };

    $scope.cancelarCompra = function () {
      location.reload(true);
    };

    $scope.enviarDatosDelPedido = function () {
      let confirm = alertify.confirm('¡ Confirmo que mis datos son correctos !', 'Fue un gusto atenderte, Restaurante Pekin les desea un excelente dia vuelva pronto :) ', null, null).set('labels', {
        ok: 'Si Pedirlo',
        cancel: 'Verificar nuevamente mis datos'
      });
      confirm.set({
        transition: 'slide'
      });

      confirm.set('onok', function () {
        //obtengo los datos del input
        var obtengoNumero = $scope.numero_telefono;
        var obtengoNameCliente = $scope.nombre_cliente;
        var obtengoCalle = $scope.calle;
        var obtengoReferencia = $scope.referencia;
        var obtenerDatosDetalleProducto = $scope.guardoProductoSeleccionadoDetalles.length;
        $scope.guardarPedidoConcatenado = '';
        
        var contador = 1;

        for (i = 0; i < obtenerDatosDetalleProducto; i++) {
          $scope.guardarPedidoConcatenado += ' Pedido:' + contador + ' ' + $scope.guardoProductoSeleccionadoDetalles[i].nombre + ' ' + $scope.guardoProductoSeleccionadoDetalles[i].descripcion + ' Cantidad:' + $scope.guardoProductoSeleccionadoDetalles[i].cantidad;
          contador++;
        }        

        //capturo los datos orden_cliente a la bd lista
        var obtengoLocalidadSeleccionadaAnteriormente = $scope.seleccionLocalidad;
        var carreteraCliente = document.getElementById("txtCarretera").value;
        var referenciaCliente = document.getElementById("txtReferencia").value;
        var nombreCliente = document.getElementById("txtClienteNamec").value;
        var numeroTelefono = document.getElementById("txtcantidadProducto").value;


        var validar = UbicacionService.validarDatosUbicacion(carreteraCliente, referenciaCliente, nombreCliente, numeroTelefono);
        if (validar === 1 && $('#selectLocalidad').val().trim() !== '') {
          var solicitudOrdenComida = {nombre: obtengoNameCliente, localidad: obtengoLocalidadSeleccionadaAnteriormente, calle: obtengoCalle, referencia: obtengoReferencia, pedido: $scope.guardarPedidoConcatenado, totalpago: $scope.sumaTotal, telefono: obtengoNumero};

          $http({method: 'post', url: 'insertarDetalleProducto', data: solicitudOrdenComida})
                  .then(function onSuccess(response) {
                    location.reload();                    
                  }).catch(function onError(response) { //CIERRA POST INSERTAR DETALLE
            alertify.notify("Se Produjo un error interno intente mas tarde" + "motivo ", "error", 5, null);
          });

        } else if (validar !== 1) {
          alertify.notify('Llena los campos correctamente', 'error', 4, null);
        } else if ($('#selectLocalidad').val().trim() === '') {
          alertify.notify('Debiste seleccionar tu localidad hazlo porfavor', 'error', 5, null);
          $('#confirmarCompraFinal').modal('hide');
          $('#modalDetallesProducto').modal('show');
        }
      });

      confirm.set('oncancel', function () { //llama al pulsar botón negativo
        alertify.error('Verifique que todo sus datos sean correctos', 5);
        //muestro el formulario de nuevo para verificar 
      });
    };

    $scope.cerrarFormularioCliente = function () {
      location.reload();
    };
  }]);