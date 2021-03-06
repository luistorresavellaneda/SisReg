<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
 -->
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="resources/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<!-- <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css"> -->
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/css/util.css">
	<link rel="stylesheet" type="text/css" href="resources/css/ie10-viewport-bug-workaround.css">
	<link rel="stylesheet" type="text/css" href="resources/css/main.css">
	<link rel="stylesheet" type="text/css" href="resources/css/navbar.css">
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<!--===============================================================================================-->
<title>FACTCLOUD</title>
</head>
<body>	
	<div class="container">

      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="menu" />">FACTCLOUD</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li id="inicio" class="active"><a href="<c:url value="menu" />">Inicio</a></li>
              <li id="rol1"><a href="<c:url value="registro" />">Registro de Guía de remisión</a></li>
              <li id="rol2"><a href="<c:url value="control" />">Listado de Guías de remisión</a></li>
              <li id="rol3"><a href="<c:url value="listado" />"></a></li>
              <li align="right" class="">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <label id="usuario"></label> <label id="cargo"></label> <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li class=""><a href="<c:url value="/" />">Cerrar sesión</a></li>
                </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
<!--       	<iframe width="0" height="0" src="https://www.youtube.com/embed/dF1I6zGwB1Q?autoplay=1" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen=""></iframe> -->
      	<!-- Card deck -->
		<div class="card-deck">
		  <!-- Card -->
		  <div class="card mb-4">
		    <!--Card image-->
		    <div class="view overlay">
		      <img src="resources/images/mixercon.jpg" alt="IMG">
		      <img src="resources/images/nkf.jpg" alt="IMG">
		      <a href="#!">
		        <div class="mask rgba-white-slight"></div>
		      </a>
		    </div>
		    <!--Card content-->
		    <div class="card-body">
		      <!--Title-->
		      <h4 class="card-title"><b>BIENVENIDO AL SISTEMA DE REGISTRO DE GUÍAS DE REMISIÓN</b></h4>
		    </div>
		  </div>
		  <!-- Card -->
      	</div>

    </div> <!-- /container -->
	
	<%-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
 --%>
	
<!--===============================================================================================-->
	<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/bootstrap/js/popper.js"></script>
	<!-- <script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script> -->
<!--===============================================================================================-->
	<script src="resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
		$(function(){
		    $(document).bind("contextmenu",function(e){
		        return false;
		    });
		});
	</script>
	<script src="resources/js/main.js"></script>
	<script src="resources/js/funciones.js"></script>
	<script src="resources/js/ie-emulation-modes-warning.js"></script>
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	
	<!-- FUNCIONALIDAD AUXILIAR -->
    <script>
        $(document).ready(function () {
        	//obtenerUsuario();
        	listarMenuOpciones();        	
        	configMenuOpciones();
        });        
        function obtenerUsuario(){
        	$.ajax({
        		type : 'GET',
        		dataType : 'text',
        		url : 'obtenerUsuario',
        		success : function(result) 
        		{
        			$("#usuario").text(result);
        		},
        		error : function(xhr, ajaxOptions, thrownError) {
          			alert(xhr.status + ' ' + thrownError);
        		}
        	});
        }
        function configMenuOpciones(){
        	$('#inicio').click(function () {
        		$("li").removeClass();
        		$("#inicio").addClass("active");                
            });
        	$('#rol1').click(function () {
        		$("li").removeClass();
                $("#rol1").addClass("active");                
            });
        	$('#rol2').click(function () {
        		$("li").removeClass();
        		$("#rol2").addClass("active");                
            });
        }
    </script>
    <!--  FUNCIONALIDAD AUXILIAR -->
  </body>  
	
</html>