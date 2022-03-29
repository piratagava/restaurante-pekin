<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="es" data-ng-app=ListaProductosActivos data-ng-controller="controllerProductoListaActivos">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Hernan García Valladares">
<title>Admin Pekin</title>

<!-- Importamos las librerias de Java-Core y JSP-->
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/bootstrap.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/angular.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dashboarStyle.jsp"%>
<%@ include
	file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/fontawesome.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dataTable.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/DetalleListaActivosDataJs.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/DetalleListaActivosStyle.jsp"%>
	

<link rel="stylesheet" href="css_global/login.css" />

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body>

	<!-- LLamo el menu Logout y barra principal de ecabezado de Admin -->
	<jsp:include page="../Recursos-Web/NavarLogout.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">

			<!-- LLamo el menu principal de Admin -->
			<jsp:include page="../Recursos-Web/MenuPricipalAdministrador.jsp"></jsp:include>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">

				<!--Para Cliente boton guardar y listar lado derecho del encabezado-->
				<div id="encabezadoCliente">
					<div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
						<h1 class="h2">Pedidos Por Entregar</h1>
						<div class="btn-toolbar mb-2 mb-md-0">													
							<div class="btn-group mr-2">
								<button type="button" onclick="doLogout()"
									class="btn btn-outline-success">Cerrar Sesion</button>
							</div>
						</div>
					</div>
				</div>

				<div class="card" ng-repeat="detalle in productosActivos">
					<p style="margin-left: 12px;">Nombre quien lo pide: {{detalle.nombre}}</p>
					<p style="margin-left: 12px;">{{detalle.pedido}}</p>
					<p style="color: blue;margin-left: 12px;">Lugar de entrega : {{detalle.localidad}} entre {{detalle.calle}} y {{detalle.referencia}}</p>										
					<p style="margin-left: 12px;">Total a pagar $ {{detalle.totalpago}}</p>															
					<p style="margin-left: 12px;">Numero del cliente: {{detalle.telefono}}</p>
					<p style="color:red;margin-left: 12px;"> Fecha pedido: {{detalle.fechahoracaptura}}</p>					
					<br>
					<button style="width: 118px; height: 45px; margin-top: 6px; margin-left: 15px;"
							class="btn btn-warning"
							data-ng-click="desactivoEtregado(detalle.activo, detalle.idDetalle)">Entregar</button>					
				</div>
				<!--Inicio de barra lateral-->
				<div class="clearfix"></div>
			</main>
		</div>
	</div>
</body>
</html>
