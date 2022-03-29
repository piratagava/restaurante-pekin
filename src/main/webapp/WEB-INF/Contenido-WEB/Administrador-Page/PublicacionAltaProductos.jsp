<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="es" data-ng-app="AltaProductos" data-ng-controller="controllerProducto">

   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta name="description" content="">            
      <title>Upload Archivo</title>

      <!-- Bootstrap core CSS -->
      <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/bootstrap.jsp" %>
      <!-- Importamos las librerias de Java-Core y JSP-->
      <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp" %>
      <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/angular.jsp"%>
      <!-- Favicons -->
      <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/fontawesome.jsp" %>
      <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/ProductoDataJs.jsp"%>
      <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>
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
      <!-- Dashboard -->
      <%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dashboarStyle.jsp" %>
   </head>

   <body>

      <jsp:include page="../Recursos-Web/NavarLogout.jsp"></jsp:include>

         <div class="container-fluid">
            <div class="row">

            <jsp:include page="../Recursos-Web/MenuPricipalAdministrador.jsp"></jsp:include>

            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
               <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                  <h1 class="h2">Productos</h1>
                  <div class="btn-toolbar mb-2 mb-md-0">
                     <div class="btn-group mr-2">
                        <button id="btnAddMenu" type="button" class="btn btn-outline-primary" data-ng-click="openAgregarMenu()">Agregar Menu de Comida</button>
                     </div>
                     <div class="btn-group mr-2">
                        <button id="btnShowTable" data-ng-click="openListarMenu()" type="button" class="btn btn-outline-info">Lista del Menu</button>
                     </div>

                     <div class="btn-group mr-2">
                        <button id="btnShowTable" data-ng-click="publicarProductos()" type="button" class="btn btn-outline-info">Publicar Productos</button>
                     </div>

                  </div>
               </div>

               <!-- Formulario para llenar infomarcion a guardar -->
               <div id="formVentaGuardar">
                  <div class="card-body">
                     <form id="menuForm">
                        <div class="form-group">
                           <input id="txtidVenta" hidden="true" type="text" class="form-control" placeholder="Id" data-ng-model="venta.idMenu" pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                        </div>

                        <div class="form-group">
                           <label> Subir foto del producto</label>
                           <input id="iptFoto" type="file" accept="image/jpeg" required="required" style="color: red">
                        </div>


                        <aside id="divLienzo" class="ui-block-b" style="width:290px; height: auto">
                           <img id="imgFoto" width="50%" />                                   
                        </aside>     

                        <div class="form-group">
                           <label class="col-form-label">Nombre del producto </label> <input
                              id="txtNombre" type="text" class="form-control"
                              placeholder="Nombre"
                              data-ng-model="venta.nombre" required="required"
                              pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                        </div>    

                        <div class="form-group">
                           <label class="col-form-label">Descripcion </label> <input
                              id="txtDescripcion" type="text" class="form-control"
                              placeholder="Descripcion"
                              data-ng-model="venta.descripcion" required="required"
                              pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                        </div>   

                        <div class="form-group">
                           <label for="startPago">Total de la venta:</label> <input
                              type="number" id="txtNumTotal"
                              data-ng-model="venta.precio" required="required" min="1"
                              pattern="^[0-9]{1,5}$" />
                        </div>

                  </div>

                  <button type="submit" class="btn btn-success" data-ng-click="guardarDatosMenu(venta.nombre, venta.descripcion, venta.precio)">Guardar Datos</button>
                  <button type="submit" class="btn btn-success" data-ng-click="cerrarFormularioMenu()">Cerrar</button>
                  </form>
               </div>


               <!--Tabla para Mostar clientes-->
               <div id="tablePricipal">
                  <div class="card-body">
                     <div class="table-responsive">
                        <table id="tablaMenu" class="display nowrap"  style="width: 100%;">
                           <thead valign="top" class="thead-dark">
                              <tr valign="top">
                                 <th align="center">Id Menu</th>
                                 <th align="center">Foto</th>
                                 <th align="center">Nombre</th>
                                 <th align="center">Descripcion</th>
                                 <th style="padding-left: 15px;" align="center">Precio</th>                                 
                                 <th style="width: 40px; padding-right: 15px" align="center">Actualizar</th>
                                 <th align="center">Eliminar</th>
                              </tr>
                           </thead>
                        </table>
                     </div>
                  </div>
               </div>

               <!-- Modal para actualizar Datos cliente -->
               <div class="modal fade" id="myModalVenta" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="modalLabel">Actualizar Datos de Ventas</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span></button>
                        </div>

                        <div class="modal-body">
                           <form id="InversionForm2">

                              <div class="form-group">
                                 <input id="txtidVenta2" hidden="true" type="text" class="form-control" placeholder="Id" data-ng-model="venta.idMenu" pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                              </div>

                              <center>
                                 <aside id="divLienzo2" class="ui-block-b" style="padding:10px;background-color: transparent;"> 
                                    <img id="imgFoto" width="50%" />  
                                    Su imagen sera cargada en esta area !!
                                 </aside>
                              </center>

                              <div class="form-group">
                                 <label> Subir foto del Menú</label>
                                 <input id="iptFoto2" type="file" accept="image/jpeg" required="required" />
                              </div>   

                              <div class="form-group">
                                 <label class="col-form-label">Nombre del producto </label> <input
                                    id="txtNombre2" type="text" class="form-control"
                                    placeholder="Nombre"
                                    data-ng-model="venta.nombre" required="required"
                                    pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                              </div>    

                              <div class="form-group">
                                 <label class="col-form-label">Descripcion </label> <input
                                    id="txtDescripcion2" type="text" class="form-control"
                                    placeholder="Descripcion"
                                    data-ng-model="venta.descripcion" required="required"
                                    pattern="^[A-Z\u00C0-\u00DCa-z\u00E0-\u00FC0-9\s]{1,99}$">
                              </div>   

                              <div class="form-group">
                                 <label for="startPago">Total de la venta:</label> <input
                                    type="number" id="txtNumTotal2"
                                    data-ng-model="venta.precio" required="required" min="1"
                                    pattern="^[0-9]{1,5}$" />
                              </div>

                           </form>
                        </div>

                        <div class="modal-footer">
                           <button type="submit" class="btn btn-primary" data-ng-click="actualizarDatos()">Actualizar Datos</button>
                           <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        </div>
                     </div>
                  </div>
               </div>
               <!-- Termina div del Modal -->             
            </main>
         </div>
      </div>
   </body>

</html>