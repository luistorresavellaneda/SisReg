<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
 -->


<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.16/af-2.2.2/b-1.5.1/cr-1.4.1/fc-3.2.4/fh-3.1.3/kt-2.3.2/r-2.2.1/rg-1.0.2/rr-1.2.3/sc-1.4.4/sl-1.2.5/datatables.min.css" />


<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="resources/images/icons/favicon.ico" />
<!--===============================================================================================-->
<!-- <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css"> -->
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/ie10-viewport-bug-workaround.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<link rel="stylesheet" type="text/css" href="resources/css/navbar.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.min.css">
<!--===============================================================================================-->
<title>SISESI</title>
<style>
#contenedor {
	/* border: 60px;
	    background: lightgray;
	    border-radius: 10px;
	    margin-bottom: 10px;
	    padding-top: 10px; */
	
}

.tab-content {
	border: 60px;
	background: #c2c5d4;
	padding-bottom: 600px;
}
</style>

</head>
<body>
	<div class="container">

		<!-- Static navbar -->
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="menu" />">FACTCLOUD</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li id="inicio"><a href="<c:url value="menu" />">Inicio</a></li>
					<li id="rol1" class="active"><a
						href="<c:url value="registro" />">Registro de Guía de remisión</a></li>
					<li id="rol2"><a href="<c:url value="control" />">Listado
							de Guías de remisión</a></li>
					<li id="rol3"><a href="<c:url value="listado" />"></a></li>
					<li align="right" class=""><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> <label id="usuario"></label> <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li class=""><a href="<c:url value="/" />">Cerrar sesion</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
		<!--/.container-fluid --> </nav>
		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron" style="padding-top: 10px;">
			<h4 style="text-align: center; color: blue;">REGISTRO DE SILABO</h4>
			<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
				<li class="nav-item"><a class="nav-link active" id="menuUniDid"
					data-toggle="pill" href="#pills-home" role="tab"
					aria-controls="pills-home" aria-selected="true">DATOS GENERALES</a>
				</li>
				<li class="nav-item"><a class="nav-link" id="menuTema"
					data-toggle="pill" href="#pills-profile" role="tab"
					aria-controls="pills-profile" aria-selected="false">DIRECCIONES
						(PARTIDA - LLEGADA)</a></li>
				<li class="nav-item"><a class="nav-link" id="menuAct"
					data-toggle="pill" href="#pills-contact" role="tab"
					aria-controls="pills-contact" aria-selected="false">REMITENTE -
						DESTINATARIO</a></li>
			</ul>
			<div class="tab-content" id="pills-tabContent">
				<!-- DATOS GENERALES -->
				<div class="tab-pane fade" id="pills-home" role="tabpanel"
					aria-labelledby="menuUniDid">
					<br>
					<div id="contenedor" class="col-md-12">
						<form>
							<div class="col-md-12"
								style="background: white; border-radius: 28px; padding: 17px;">
								<div class="col-md-4">
									<div class="form-group">
										<!-- Default input -->
										<label for="exampleForm2">RUC</label> <input type="text"
											class="form-control" required id="idRuc" name="idRuc">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="example-date-input" class="col-2 col-form-label">FECHA
											DE EMISIÓN</label>
										<div class="col-10">
											<input class="form-control" type="date" value="2011-08-19"
												id="idFecEmi" name="idFecEmi">
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="example-date-input" class="col-2 col-form-label">FECHA
											DE INICIO DE TRABAJO</label>
										<div class="col-10">
											<input class="form-control" type="date" value="2011-08-19"
												id="idFecIni" name="idFecIni">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary">GRABAR</button>
							</div>
						</form>
					</div>
				</div>
				<!-- DIRECCIONES -->
				<div class="tab-pane fade" id="pills-profile" role="tabpanel"
					aria-labelledby="menuTema">
					<br>
					<div id="contenedor" class="col-md-12">
						<form>
							<div class="col-md-12"
								style="background: white; border-radius: 28px; padding: 10px;">
								<div class="col-md-12">
									<h4 style="text-align: center;">DIRECCIÓN DE PARTIDA</h4>
								</div>
								<div class="col-md-12">
									<div class="col-md-3">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Vía tipo</label> <select
												class="form-control" id="idDPTipo" name="idDPTipo">
												<option>Avenida</option>
												<option>Jirón</option>
												<option>Calle</option>
											</select>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Vía Nombre</label> <select
												class="form-control" id="idDPVia" name="idDPVia">
												<option>Opción 1</option>
												<option>Opción 2</option>
												<option>Opción 3</option>
											</select>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">NÚMERO</label> <input type="number"
												id="idDPNum" name="idDPNum" class="form-control" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">INTERIOR</label> <input type="text"
												id="idDPInt" name="idDPInt" class="form-control" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">ZONA</label> <input type="text"
												id="idDPZona" name="idDPZona" class="form-control" required>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Departamento</label> <select
												class="form-control" id="idDPDepa" name="idDPDepa">
												<option>SELECCIONE</option>
												<option>AMAZONAS</option>
												<option>CUSCO</option>
												<option>LIMA</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Provincia</label> <select
												class="form-control" id="idDPPro" name="idDPPro">
												<option>SELECCIONE</option>
												<option>LIMA</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Distrito</label> <select
												class="form-control" id="idDPDis" name="idDPDis">
												<option>SELECCIONE</option>
												<option>ATE</option>
												<option>JESÚS MARÍA</option>
												<option>LINCE</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary">GRABAR</button>
							</div>
						</form>
						<div class="col-md-12">
							<br>
							<br>
						</div>
						<form>
							<div class="col-md-12"
								style="background: white; border-radius: 28px; padding: 10px;">
								<div class="col-md-12">
									<h4 style="text-align: center;">DIRECCIÓN DE LLEGADA</h4>
								</div>
								<div class="col-md-12">
									<div class="col-md-3">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Vía tipo</label> <select
												class="form-control" id="idDLLVia" name="idDLLVia">
												<option>Avenida</option>
												<option>Jirón</option>
												<option>Calle</option>
											</select>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Vía Nombre</label> <select
												class="form-control" id="idDLLNom" name="idDLLNom">
												<option>Opción 1</option>
												<option>Opción 2</option>
												<option>Opción 3</option>
											</select>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">NÚMERO</label> <input type="number"
												id="idDLLNum" name="idDLLNum" class="form-control" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">INTERIOR</label> <input type="text"
												id="idDLLInt" name="idDLLInt" class="form-control" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">ZONA</label> <input type="text"
												id="idDLLZon" name="idDLLZon" class="form-control" required>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Departamento</label> <select
												class="form-control" id="idDLLDep" name="idDLLDep">
												<option>SELECCIONE</option>
												<option>AMAZONAS</option>
												<option>CUSCO</option>
												<option>LIMA</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Provincia</label> <select
												class="form-control" id="idDLLPro" name="idDLLPro">
												<option>SELECCIONE</option>
												<option>LIMA</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="exampleFormControlSelect1">Distrito</label> <select
												class="form-control" id="idDLLDis" name="idDLLDis">
												<option>SELECCIONE</option>
												<option>ATE</option>
												<option>JESÚS MARÍA</option>
												<option>LINCE</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary">GRABAR</button>
							</div>
						</form>
					</div>
				</div>

				<!-- REMITENTE - DESTINATARIO -->
				<div class="tab-pane fade" id="pills-contact" role="tabpanel"
					aria-labelledby="menuAct">
					<br>
					<div id="contenedor" class="col-md-12">
						<form>
							<div class="col-md-12"
								style="background: white; border-radius: 28px; padding: 17px;">
								<div class="col-md-12">
									<h4 style="text-align: center;">REMITENTE</h4>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">APELLIDOS Y NOMBRES / RAZÓN
												SOCIAL</label> <input type="text" id="exampleForm2"
												class="form-control" required>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">RUC</label> <input type="text"
												id="exampleForm2" class="form-control" required>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="exampleFormControlSelect1">TIPO</label> <select
												class="form-control" id="curso4" name="curso4"
												onchange="obtenerGrupo();">
												<option>SELECCIONE</option>
												<option>DNI</option>
												<option>PASAPORTE</option>
												<option>CARNÉ DE EXTRANJERÍA</option>
											</select>
										</div>
									</div>
									<div class="col-md-8">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">Nº DE DOCUMENTO DE
												IDENTIDAD</label> <input type="text" id="exampleForm2"
												class="form-control" required>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary">GRABAR</button>
							</div>
						</form>
						<div class="col-md-12">
							<br><br>
						</div>
						<form>
							<div class="col-md-12"
								style="background: white; border-radius: 28px; padding: 17px;">
								<div class="col-md-12">
									<h4 style="text-align: center;">DESTINATARIO</h4>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">APELLIDOS Y NOMBRES / RAZÓN
												SOCIAL</label> <input type="text" id="exampleForm2"
												class="form-control" required>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">RUC</label> <input type="text"
												id="exampleForm2" class="form-control" required>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="exampleFormControlSelect1">TIPO</label> <select
												class="form-control" id="curso4" name="curso4"
												onchange="obtenerGrupo();">
												<option>SELECCIONE</option>
												<option>DNI</option>
												<option>PASAPORTE</option>
												<option>CARNÉ DE EXTRANJERÍA</option>
											</select>
										</div>
									</div>
									<div class="col-md-8">
										<div class="form-group">
											<!-- Default input -->
											<label for="exampleForm2">Nº DE DOCUMENTO DE
												IDENTIDAD</label> <input type="text" id="exampleForm2"
												class="form-control" required>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary">GRABAR</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->

	<!--===============================================================================================-->
	<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/vendor/bootstrap/js/popper.js"></script>
	<!-- <script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script> -->
	<!--===============================================================================================-->
	<script src="resources/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="resources/vendor/tilt/tilt.jquery.min.js"></script>
	<script>
		$('.js-tilt').tilt({
			scale : 1.1
		})
	</script>
	<script src="resources/js/main.js"></script>
	<script src="resources/js/ie-emulation-modes-warning.js"></script>
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>

	<script type="text/javascript"
		src="https://cdn.datatables.net/v/dt/dt-1.10.16/af-2.2.2/b-1.5.1/cr-1.4.1/fc-3.2.4/fh-3.1.3/kt-2.3.2/r-2.2.1/rg-1.0.2/rr-1.2.3/sc-1.4.4/sl-1.2.5/datatables.min.js"></script>


	<!-- FUNCIONALIDAD AUXILIAR -->
	<script>
		$(document).ready(function() {
			obtenerUsuario();
			listarMenuOpciones();
			$('#inicio').click(function() {
				$("li").removeClass();
				$("#inicio").addClass("active");
			});
			$('#rol1').click(function() {
				$("li").removeClass();
				$("#rol1").addClass("active");
			});
			$('#rol2').click(function() {
				$("li").removeClass();
				$("#rol2").addClass("active");
			});
			$('#menuUniDid').click(function() {
				//Se carga la unidad didáctica
				mostrarUnidadDidactica();
			});
			$('#menuTema').click(function() {
				cargarUnidades("unidadDidactica2");
				//Se carga la unidad didáctica
				//mostrarTema();
			});
			$('#menuAct').click(function() {
				cargarUnidades("unidadDidactica3");
				//Se carga la unidad didáctica
				//mostrarActividad();
			});

		});
		function obtenerUsuario() {
			$.ajax({
				type : 'GET',
				dataType : 'text',
				url : 'obtenerUsuario',
				success : function(result) {
					//console.log(result);
					$("#usuario").text(result);
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status + ' ' + thrownError);
				}
			});
		}
	</script>
	<script src="resources/js/funciones.js"></script>
	<!--  FUNCIONALIDAD AUXILIAR -->
</body>
</html>