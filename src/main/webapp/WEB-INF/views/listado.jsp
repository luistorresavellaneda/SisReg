<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<!--  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
-->

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.16/af-2.2.2/b-1.5.1/cr-1.4.1/fc-3.2.4/fh-3.1.3/kt-2.3.2/r-2.2.1/rg-1.0.2/rr-1.2.3/sc-1.4.4/sl-1.2.5/datatables.min.css"/>

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
<title>SISESI</title>
</head>
<body>	
	<div class="container">
      <!-- Static navbar -->
      <nav class="navbar navbar-default">  <!-- style="background: #182233;" -->
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="menu" />">SISESI</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li id="inicio"><a href="<c:url value="menu" />">Registro</a></li>              
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron" style="padding-bottom: 300px;">
      	<h4 style="text-align: center;color:blue;">SISTEMA DE REGISTRO</h4>
      	<div>
      		<br>
      	</div>
      	
      	<div class="col-md-12">
      		<div class="col-md-4">
      			<div class="form-group">
				      <label for="exampleFormControlSelect1">DNI</label>
				      <input id="dni" name="dni" type="text" placeholder="Ingrese dni"/>
				    </div>
      		</div>      		
      	</div>
      	<div class="col-md-12">
      		<div class="col-md-6">
      			<label for="nombreDocente">Apellidos:  ROMERO DE LA CRUZ</label>            
      		</div>
          <div class="col-md-6">
            <label for="nombreDocente">Nombres:  ALEXANDER BRIAN</label>            
          </div>
      	</div> 
        <div class="col-md-12">
            <input type="checkbox" name="c1D1" id="c1d1"/>
            <label for="c1d1label">TEMA 1</label>
            <label for="c1d1label">ASISTENTES 11</label>
            <label for="c1d1label"> 20</label>
        </div>
        <div class="col-md-12">
            <input type="checkbox" name="c2D2" id="c2d2"/>
            <label for="c2d2label">TEMA 2</label>
            <label for="c2d2label">ASISTENTES 10</label>
            <label for="c2d2label"> 30</label>
        </div>
        <div class="col-md-12">
            <input type="checkbox" name="c3D3" id="c3d3"/>
            <label for="c3d3label">TEMA 3</label>
            <label for="c3d3label">ASISTENTES 11</label>
            <label for="c3d3label"> 20</label>
        </div>
      </div>

    </div> <!-- /container -->
	
<!--===============================================================================================-->
	<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/bootstrap/js/popper.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
	<script src="resources/js/main.js"></script>
	<script src="resources/js/funciones.js"></script>
	<script src="resources/js/ie-emulation-modes-warning.js"></script>
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
	<!-- DATATABLE -->
	<!-- <script src="resources/js/jquery.dataTables.min.js"></script> -->
	<!-- <script src="resources/js/datatables.min.js"></script> -->
	<!-- DATATABLE -->
	
	<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.16/af-2.2.2/b-1.5.1/cr-1.4.1/fc-3.2.4/fh-3.1.3/kt-2.3.2/r-2.2.1/rg-1.0.2/rr-1.2.3/sc-1.4.4/sl-1.2.5/datatables.min.js"></script>
    	
	
	<!-- FUNCIONALIDAD AUXILIAR -->
    <script>
        $(document).ready(function () {        	
        });
    </script>
    <!--  FUNCIONALIDAD AUXILIAR -->
  </body>  
	
</html>