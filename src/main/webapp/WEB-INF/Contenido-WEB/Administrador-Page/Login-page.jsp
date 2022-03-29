<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Hernan García Valladares">
<title>Página Login Admin</title>

<!-- Importamos las librerias de Java-Core y JSP-->
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/include.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/bootstrap.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/angular.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dashboarStyle.jsp"%>
<%@ include
    file="/WEB-INF/Contenido-WEB/Recursos-Web/notificaciones.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/fontawesome.jsp"%>
<%@ include file="/WEB-INF/Contenido-WEB/Recursos-Web/dataTable.jsp"%>
<script src="https://kit.fontawesome.com/64d58efce2.js"
    crossorigin="anonymous"></script>

<link rel="stylesheet" href="css_global/login.css" />
<script type="text/javascript" src="js/LoginCliente/client.js"></script>

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


    <div id="login">
        <img class="wave" src="img/wave.png">
        <div class="login-container">
            <div class="img">
                <img src="img/acss.svg">
            </div>
            <div class="login-content">
                <form id="loginForm">
                    <img src="img/user.png">                                       
                    <div class="alert alert-danger" id="notLoggedIn">Es Necesario
                        Iniciar Sesion!</div>

                    <h2 class="title">Bienvenido!</h2>
                    <div class="input-div one">
                        <div class="i">
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="div">
                            <h5>Escriba su Nombre</h5>
                            <input type="text" class="input" id="exampleInputEmail1" required
                                name="username">
                        </div>
                    </div>
                    <div class="input-div pass">
                        <div class="i">
                            <i class="fas fa-lock"></i>
                        </div>
                        <div class="div">
                            <h5>Escriba su contraseña</h5>
                            <input type="password" class="input" id="exampleInputPassword1"
                                required name="password">
                        </div>
                    </div>
                    <a href="#openModal">Como iniciar sesion?</a> <a href="/index">regresar</a>

                    <button type="submit" class="btns" style="width: 200px;">Entrar</button>
                </form>
            </div>
        </div>
    </div>
    <!-- fin login div  -->
    <script type="text/javascript" src="js/responsiveMain.js"></script>
</body>
</html>
