<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es" data-ng-app="MuestraProductos" data-ng-controller="controllerProducto">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Hernán García">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Restaurante Pekin</title>
     
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">
    
    <!-- Importamos las librerias de Java-Core y JSP-->
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/bootstrap.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/angular.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/carruselStyleGlobal.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/OpenViewDataJs.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/fontawesome.jsp" %>
    <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dataTable.jsp"%>

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
  </head>

  <body>
    <jsp:include page="header.jsp"></jsp:include>

    <main role="main">

      <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>               
        </ol>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="img/1.jpg" width="100px">
            <div class="container">
              <div class="carousel-caption text-left">
                <p align="center"><a class="btn btn-lg btn-warning" href="#" role="button">Bienvenid@</a></p>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <img src="img/Pekin.png" width="100px">
          </div>  
          <div class="carousel-item">
            <img src="img/3.jpg" width="100px">
          </div>                      
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>

      </div>

      <center>
        <div style="border-bottom: 20px;">
          <h4 >Que quieres comer hoy ?</h4>
        </div>
      </center>

      <!-- Marketing messaging and featurettes
================================================== -->
      <div class="card" ng-repeat="course in productos">                
        <p class="icon">{{course.nombre}}</p>               
        <h2 class="category">{{course.descripcion}}</h2>
        <p class="description">Precio $ {{course.precio}}</p>
        <img style="width:130px;height: 135px;" data-ng-src="data:image/jpeg;base64,{{course.foto}}" />
        <br>
        <center>            
          <button style="width: 118px; height: 45px; margin-top: 6px;" class="btn btn-warning" data-ng-click="mandoInfoProducto(course.idMenu, course.foto, course.nombre, course.descripcion, course.precio)" >Agregar</button>
        </center>           
      </div>
      <!--Inicio de barra lateral-->
      <div class="clearfix"></div>


      <a target="_blanck" id="muestroIconPago" class="back-to-top" data-ng-click="realizarCompra()" ><i class="fas fa-shopping-bag"></i> Realizar Compra</a>

      <!-- Modal para actualizar Datos cliente -->
      <div class="modal fade" id="modalOrden" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="modalLabel" style="color: #ff8b33;">Usted Eligió</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
                <span class="btn btn-dark">Cancelar</span>
              </button>
            </div>

            <div class="modal-body">
              <div class="cards">                        
                <div class="card-body">
                  <h7 class="list-group-item" style="color: black;border-color: red;">Platillo : {{datosCliente.nombre}} {{datosCliente.descripcion}}</h7>                  
                  <div class="imgMenuSeleccionado">
                    <center>
                      <img style="width:110px;height: 125px;" data-ng-src="data:image/jpeg;base64,{{foto}}" />
                    </center>
                  </div>                                                    
                  <!-- Formulario para llenar infomarcion a guardar -->

                  <form id="menuForm">                      
                    <center>
                      <label id="cantidad" for="startPago">Cantidad</label> <input
                        type="number" id="cantidadProducto"
                        ng-model="cantidadProducto" required="required" min="1"
                        pattern="^[0-9]{1,5}$" style="width: 50px; border-color: orange;"/>
                    </center>
                  </form>

                  <div class="botonPush">                                      
                    <button type="submit" class="btn btn-success" data-ng-click="pushProducto(datosCliente.idMenu, datosCliente.nombre, datosCliente.descripcion, datosCliente.precio, totalPago, cantidadProducto)">Agregar (+)</button> 
                  </div>

                </div>                                        
              </div>

            </div>

          </div>
        </div>
      </div>
      <!-- Termina div del Modal -->   



      <!-- Modal para actualizar Datos cliente -->
      <div class="modal fade" id="modalDetallesProducto" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="modalLabel" style="color: #ff8b33;">Usted Eligió</h5>              
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
                <span class="btn btn-dark" data-ng-click="cancelarCompra()">Cancelar Orden</span>
              </button>
            </div>

            <div class="modal-body">
              <div class="cards">                        
                <div class="card-body">                  
                  <table id="example" class="table table-bordered table-responsive-md table-striped text-center">
                    <thead>
                      <tr>
                        <th style="padding-right: 2px; padding-left: 2px;">Plato</th>                        
                        <th style="padding-right: 2px; padding-left: 2px;">Cantidad</th>
                        <th style="padding-right: 2px; padding-left: 2px;">Total</th>
                        <th style="padding-left: 2px; padding-right: 2px;">Modificar</th>
                        <th style="padding-left: 2px; padding-right: 2px;">Quitar</th> 
                      </tr>
                    </thead>
                    <tbody>
                      <tr ng-repeat="dato in guardoProductoSeleccionadoDetalles">
                        <td>{{dato.nombre}} {{dato.descripcion}}</td>                        
                        <td>{{dato.cantidad}}</td>
                        <td>{{dato.totalPago}}</td>
                        <td style="padding-left: 2px; padding-right: 2px;">
                          <a ng-click="mostrarUpdateProducto(dato.nombre, dato.descripcion, dato.precio, dato.totalPago, dato.cantidad)" class="btn btn-outline-info"><span class="fa fa-pen"></span></a>                          
                        </td>
                        <td style="padding-left: 2px; padding-right: 2px;">                          
                          <a ng-click="quitarProducto($index)" class="btn btn-outline-danger custom-width"><span class="fas fa-trash"></span></a>  
                        </td>
                      </tr>
                    </tbody>                    
                  </table>                    
                  <div class="form-group">
                    <center>
                      <label class="col-form-label" style="font-size: 17px; color: black; font-style: italic">Calcule su costo de envio y total a pagar</label>
                      <select style="font-size: 16px; color: orangered;" required data-ng-model="seleccionLocalidad" id="selectLocalidad" ng-change="calculaCostoSegunLocalidad()" >                      
                        <option value="">¿ Desde donde solicitas tu pedido ?</option>
                        <option data-ng-repeat="x in localidadesAceptadas" value="{{x}}">{{x}}</option>
                      </select>
                    </center>
                  </div>
                  <div id="totalPago">     
                    <center>
                      <label style="font-size: 17px; color: black;"> {{tiempoEnvio}}</label>
                      <label style="font-size: 17px; color: black;"> Pagara un total de : $</label>
                      <label class="col-form-label" style="font-size: 18px; color:red; font-style: italic">{{sumaTotal}} pesos</label>
                      <label style="font-size: 17px; color: black;">incluye envio a su domicilio</label>
                    </center>
                  </div>

                </div>            

              </div>
            </div>
            <div class="modal-footer">
              <button type="submit" class="btn btn-success" data-ng-click="pushProductoHide()">Agregar(+)</button>
              <button id="realizaCompra" type="submit" class="btn btn-danger" data-ng-click="realizarSiguienteProcesoPedido()">Confirmar Compra</button>              
            </div>
          </div>
        </div>
      </div>
      <!-- Termina div del Modal --> 



      <!-- Modal para actualizar Datos cliente -->
      <div class="modal fade" id="modalModificaCantidad" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="modalLabel" style="color: #ff8b33;">Modifique su cantidad</h5>
            </div>

            <div class="modal-body">
              <div class="cards">                        
                <div class="card-body">

                  <!-- Formulario para llenar cambiar la info de cantidad producto -->
                  <div id="formVentaGuardar">                  
                    <form id="menuForm">      

                      <div class="form-group">
                        <label for="startPago">Nombre del platillo: </label> {{detalleProducto.nombre}} {{detalleProducto.descripcion}}
                      </div> 

                      <div class="form-group">
                        <label for="startPago">Pecio Unitario: </label> {{detalleProducto.precio}}                       
                      </div> 

                      <div class="form-group">
                        <label for="startPago">Ingresa nueva cantidad: </label> <input
                          type="number" id="cantidadProductoUpdate"
                          ng-model="cantidadNuevaProducto" required="required" min="1" placeholder="cantidad"
                          pattern="^[0-9]{1,5}$" style="width: 120px; border-color: orange;"/>
                      </div>  

                    </form>
                  </div>                  
                </div>                        
                <button type="submit" class="btn btn-warning" data-ng-click="actualizaCantidadNueva(detalleProducto.nombre, detalleProducto.descripcion)">Confirmar cantidad</button>
                <button type="button" class="btn btn-info" data-dismiss="modal" data-ng-click="mostrarModalDetalleProducto()">Cancelar</button>
              </div>
            </div>            
          </div>
        </div>
      </div>
      <!-- Termina div del Modal -->   


      <!-- Modal -->
      <div class="modal fade" id="confirmarCompraFinal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-scrollable" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLongTitle">Concluya su pedido aqui</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">

              <!-- Formulario para llenar infomarcion a guardar -->
              <div id="formClienteGuardar">
                <div class="card-body">
                  <form id="clienteForm">    

                    <div class="form-group">
                      <label class="col-form-label">A nombre de quien lo pide ? </label> <input
                        id="txtClienteNamec" type="text" class="form-control"
                        placeholder="Nombre del Cliente"
                        data-ng-model="nombre_cliente" required="required"
                        pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                    </div>                   

                    <div class="form-group">
                      <label class="col-form-label">Indique entre que carretera se ubica: </label> 
                      <input id="txtCarretera" type="text"
                             class="form-control" placeholder="Ej: Principal, Por la curva etc"
                             data-ng-model="calle" required="required"
                             pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                    </div>

                    <div class="form-group">
                      <label class="col-form-label">Otra referencia: </label> <input
                        id="txtReferencia" type="text" class="form-control"
                        placeholder="Ej: Frente a una casa color verde etc" data-ng-model="referencia"
                        required="required"
                        pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                    </div>

                    <div class="form-group">                      
                      <label class="col-form-label">En caso de no llegar a su ubicacion nosotros le llamaremos </label>
                      <input class="form-control" type="number" id="txtcantidadProducto" placeholder="Telefono"
                             data-ng-model="numero_telefono" required="required" min="1"
                             pattern="^[0-9]{1,10}$"/>
                    </div>
                </div>

                <button type="submit" class="btn btn-warning"
                        data-ng-click="enviarDatosDelPedido()">Guardar
                  Datos</button>
                <button type="submit" class="btn btn-warning"
                        data-ng-click="cerrarFormularioCliente()">Cancelar Pedido</button>
                </form>
              </div>
            </div>            
          </div>
        </div>
      </div>
      <!-- FOOTER -->
      <div id="footer" style="padding-top: 100px; margin-right: 175px;">
        <footer class="container">        
        <p>Creado por el Ing. Hernán García Valladares</p>
          <p style="font-style: italic; color: black">&copy; 2022-2023 Company Restaurante Pekin China Food - García, Inc. &middot;</p>
      </footer>
      </div>
      
    </main>
  </body>

</html>