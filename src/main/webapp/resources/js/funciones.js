

var perfil=0;

function listarMenuOpciones(){	
	$("#rol1").hide();
	//con esto muestro las opciones según el perfil del usuario...
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : 'listarMenuOpciones',
		success : function(result) 
		{
			obtenerUsuario();
			$("#cargo").text("");
			if(result["admin"].booleanValue){
				$("#rol1").show();
				$("#rol2").show();
				$("#rol3").show();
			}
			if(result["admin"].booleanValue){
				$("#rol1").show();
				$("#rol2").show();
				$("#rol3").hide();
			}			
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
}

/* INICIO UNIDAD DIDACTICA */        
function mostrarUnidadDidactica(){  
	/* TABLA DE UNIDAD DIDACTICA  */
	$('#tab1').DataTable().destroy();
	var dataSet = new Array();
    var filaset;
  	//Obtenemos las unidades didacticas del curso seleccionado
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'listarUnidadDidactica',
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			 $.each(data.documents, function(i, item) {
				filaset = new Array();
				filaset.push(i);
				filaset.push(i+1);
				filaset.push(item.fields.nombre.stringValue);
				filaset.push(item.fields.semanas.integerValue);
				filaset.push('<button onclick="editarUD('+item.fields.semanas.integerValue+',\''+ item.fields.nombre.stringValue +'\',\''+ item.name +'\');" type="button" class="btn btn-primary" data-toggle="modal" data-target="#udModal">EDITAR</button>');
	            dataSet.push(filaset);       			    
			});
			// TABLA DE CURSOS 
			$('#tab1').DataTable( {
                "language": {
                  "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                },
                data: dataSet,
                bPaginate: true,
                bInfo: false,
                searching: true,
                responsive: true,
                columns: [
                  { title: "Código", "visible": false },
                  { title: "#" },
                  { title: "Unidad didáctica" },
                  { title: "Cantidad de semanas" },
                  { title: "Acción" }
                ]
            } );
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	}); 
}        
function editarUD(CANTSEM , UD , IDUD){
	$("#idEditUD").val(UD);
	$("#idEditCantSem").val(CANTSEM);
	$("#idEditIdUD").val(IDUD);        	
}        
function actualizarUD(){
	var idEditIdUD = $("#idEditIdUD").val();
	var idEditUD = $("#idEditUD").val();
	var idEditCantSem = $("#idEditCantSem").val();
	var dataAux = "idEditIdUD="+idEditIdUD+"&idEditUD="+idEditUD+"&idEditCantSem="+idEditCantSem;
	$.ajax({
		type : 'GET',
		dataType : 'json',
		data: dataAux,
		url : 'editarUnidadDidactica',
		contentType: "application/json; charset=utf-8",
		success : function(result) 
		{
			$("#udModal").modal('toggle');
			$('#tab1').DataTable().destroy();
			mostrarUnidadDidactica();
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
}        
function guardarUnidadDidactica(){
	var idUD = $("#idUD").val();
	var idSE = $("#idSE").val();
	if(idUD==null || idUD.trim()==undefined || idUD=='' || idUD.trim()==''){
		alert("INGRESE UNIDAD DIDACTICA");
	}else{
		if(idSE==null || idSE.trim()==undefined || idSE=='' || idUD.trim()==''){
			alert("INGRESE CANTIDAD DE SEMANAS");
		}else{
			if(idSE<1 || idSE>5){
				alert("CANTIDAD INVALIDA");
			}else{
				var dataAux = "idUD="+idUD+"&idSE="+idSE+"&rows="+$('#tab1').DataTable().rows().count();
				$.ajax({
					type : 'POST',
					dataType : 'json',
					data: dataAux,
					url : 'guardarUnidadDidactica',
					success : function(result) 
					{
						mostrarUnidadDidactica();
					},
					error : function(xhr, ajaxOptions, thrownError) {
			  			alert(xhr.status + ' ' + thrownError);
					}
				});
			}
		}		
	}	
}
/* FIN UNIDAD DIDACTICA */

/* INICIO TEMA */
function cargarUnidades(select){
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'listarUnidadDidactica',
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			var s = document.getElementById(select);
			$("#"+select).empty();
			$("#"+select).append("<option value=0>Seleccione</option>")
			$.each(data.documents, function(i, item) {
				var op = document.createElement("option");
				op.value = item.fields.numero.integerValue;
				op.text = item.fields.nombre.stringValue;
				s.add(op);
			});
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	}); 
}

function listarSemanas(v, select){
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'listarSemanas',
		data: 'unidad='+v,
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			var s = document.getElementById(select);
			$("#"+select).empty();
			$("#"+select).append("<option value=0>Seleccione</option>")
			$.each(data, function(i, item) {
				var op = document.createElement("option");
				op.value = item.document.fields.numero.integerValue;
				op.text = item.document.fields.numero.integerValue;
				s.add(op);
			});
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	}); 
}

function listarTemas(semana){  
	/* TABLA DE TEMA  */
	$('#tab2').DataTable().destroy();
	var dataSet = new Array();
	var filaset;
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'listarTemas',
		data: 'semana='+semana,
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			 $.each(data.documents, function(i, item) {
				filaset = new Array();
				filaset.push(i);
				filaset.push(i+1);
				filaset.push(semana);
				filaset.push(item.fields.nombre.stringValue);
				filaset.push('<button onclick="editarTem(\''+ item.name +'\','+ semana +',\''+ item.fields.nombre.stringValue +'\');" type="button" class="btn btn-primary" data-toggle="modal" data-target="#temModal">EDITAR</button>');
				dataSet.push(filaset);       			    
			});
			// TABLA DE TEMA 
			$('#tab2').DataTable( {
	               "language": {
	                 "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
	               },
	               data: dataSet,
	               bPaginate: true,
	               bInfo: false,
	               searching: true,
	               responsive: true,
	               columns: [
	                   { title: "Código", "visible": false },
	                   { title: "#" },
	                   { title: "Semana" },
	                   { title: "Tema" },
	                   { title: "Acción" }
	               ]
	           } );
		},
		error : function(xhr, ajaxOptions, thrownError) {
	 			alert(xhr.status + ' ' + thrownError);
   		}
   	}); 
}
function guardarTema(){
	var semana = $("#semana2").val();
	var tema = $("#idT").val();
	var dataAux = "semana="+semana+"&tema="+tema+"&rows="+$('#tab2').DataTable().rows().count();
	$.ajax({
		type : 'POST',
		dataType : 'json',
		data: dataAux,
		url : 'guardarTema',
		success : function(result) 
		{
			listarTemas(semana);
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
}
function editarTem(IDTem, semana, tema){
	$("#IdEditSem").val(semana);
	$("#idEditTem").val(tema);
	$("#idEditIdTem").val(IDTem);
}
function actualizarTem(){
	var idEditIdTem = $("#idEditIdTem").val();
	var idEditTem = $("#idEditTem").val();
	var dataAux = "idEditIdTem="+idEditIdTem+"&idEditTem="+idEditTem;
	$.ajax({
		type : 'GET',
		dataType : 'json',
		data: dataAux,
		url : 'editarTema',
		contentType: "application/json; charset=utf-8",
		success : function(result) 
		{
			$("#temModal").modal('toggle');
			$('#tab2').DataTable().destroy();
			listarTemas($("#IdEditSem").val());
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
} 
/* FIN TEMA */

function ActionSemana(v, select){
	selectTemas(v, select);
	get_obs(v, document.getElementById("turno3").value, document.getElementById("grupo3").value);
}

function ActionTurno(s, v, t){
	if(v != 0) listarActividades2(s, v, t);
	get_obs(s, t, document.getElementById("grupo3").value);
}

function get_obs(v, t, g){
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : 'getObs',
		data: 'perfil='+perfil+'&semana='+v+"&turno="+t+"&grupo="+g,
		success : function(result) 
		{
			document.getElementById("observaciones").value = result.observacion.stringValue;
		},
		error : function(xhr, ajaxOptions, thrownError) {
	 			alert(xhr.status + ' ' + thrownError);
   		}
   	}); 
}

function selectTemas(v, select){
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'listarTemas',
		data: 'semana='+v,
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			var s = document.getElementById(select);
			$("#"+select).empty();
			$("#"+select).append("<option value=0>Seleccione</option>")
			$.each(data.documents, function(i, item) {
				var op = document.createElement("option");
				op.value = item.fields.numero.integerValue;
				op.text = item.fields.nombre.stringValue;
				s.add(op);
			});
		},
		error : function(xhr, ajaxOptions, thrownError) {
	 			alert(xhr.status + ' ' + thrownError);
   		}
   	}); 
}
function listarActividades(v){
	var semana = $("#semana3").val();
	$('#tab3').DataTable().destroy();
	var dataSet = new Array();
	var filaset;
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'listarActividades',
		data: 'perfil='+perfil+'&semana='+semana+"&tema="+v+"&turno=n&grupo=n",
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			var vals = "";
			$.each(data.fields.actividades.arrayValue.values, function(i, item) {
				filaset = new Array();
				filaset.push(i);
				filaset.push(i+1);
				filaset.push($("#tema3 option:selected").text());
				vals += "{\"stringValue\": \"" + item.stringValue + "\"},",
				filaset.push(item.stringValue);
				filaset.push('<button onclick="editarAct(\''+ item.stringValue +'\');" type="button" class="btn btn-primary" data-toggle="modal" data-target="#actModal">EDITAR</button>');
				dataSet.push(filaset);       			    
			});
			$("#acts").val(vals);
			// TABLA DE TEMA 
			$('#tab3').DataTable( {
	               "language": {
	                 "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
	               },
	               data: dataSet,
	               bPaginate: true,
	               bInfo: false,
	               searching: true,
	               responsive: true,
	               columns: [
	                   { title: "Código", "visible": false },
	                   { title: "#" },
	                   { title: "Tema" },
	                   { title: "Actividad" },
	                   { title: "Acción" }
	               ]
	           } );
		},
		error : function(xhr, ajaxOptions, thrownError) {
	 			alert(xhr.status + ' ' + thrownError);
   		}
   	}); 
}
function listarActividades2(s, v, t){
	$('#tab2').DataTable().destroy();
	var dataSet = new Array();
	var filaset;
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'listarActividades',
		data: 'perfil='+perfil+'&semana='+s+"&tema="+v+"&turno="+t+"&grupo="+document.getElementById("grupo3").value,
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			var vals = "";
			$.each(data, function(i, item) {
				filaset = new Array();
				filaset.push(i);
				filaset.push(i+1);
				filaset.push(item.stringValue);
				filaset.push('<form method="post"><div class="form-check"><input type="checkbox" '+(item.booleanValue==true?'checked':'')+' class="form-check-input" onclick="update_res(this.checked,'+i+')"></div></form>');
				dataSet.push(filaset);       			    
			});
			// TABLA DE TEMA 
			$('#tab2').DataTable( {
                "language": {
                  "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                },
                data: dataSet,
                bPaginate: true,
                bInfo: false,
                searching: true,
                responsive: true,
                columns: [
                  { title: "Código", "visible": false },
                  { title: "#" },
                  { title: "Actividad" },
                  { title: "Validad" }
                ]
            } );
		},
		error : function(xhr, ajaxOptions, thrownError) {
	 			alert(xhr.status + ' ' + thrownError);
   		}
   	}); 
}
function update_res(c, i){
	var semana = $("#semana3").val();
	var v = $("#tema3").val();
	var t = $("#turno3").val();
	$.ajax({
		type : 'GET',
		dataType : 'json',
		data: 'perfil='+perfil+'&semana='+semana+'&tema='+v+'&turno='+t+"&val="+c+"&indice="+i+"&rows="+$('#tab2').DataTable().rows().count(),
		url : 'updateRespuesta',
		contentType: "application/json; charset=utf-8",
		success : function(result) 
		{
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
}
function post_obs(v){
	var semana = $("#semana3").val();
	var t = $("#turno3").val();
	$.ajax({
		type : 'GET',
		dataType : 'json',
		data: 'perfil='+perfil+'&semana='+semana+'&turno='+t+"&val="+v,
		url : 'updateObs',
		contentType: "application/json; charset=utf-8",
		success : function(result) 
		{
			
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
}
function guardarActividad(){
	var semana = $("#semana3").val();
	var tema = $("#tema3").val();
	var act = $("#act3").val();
	var dataAux = "semana="+semana+"&tema="+tema+"&vals="+$("#acts").val()+"&act="+act;
	$.ajax({
		type : 'POST',
		dataType : 'json',
		data: dataAux,
		url : 'guardarActividad',
		success : function(result) 
		{
			listarActividades(tema);
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
}
function editarAct(actividad){
	$("#IdEditTem2").val($("#tema3 option:selected").text());
	$("#IdEditAct, #idActOld").val(actividad);  	
}
function actualizarAct(){
	var semana = $("#semana3").val();
	var tema = $("#tema3").val();
	var idActOld = $("#idActOld").val();
	var IdEditAct = $("#IdEditAct").val();
	var dataAux = "semana="+semana+"&tema="+tema+"&idActOld="+idActOld+"&IdEditAct="+IdEditAct+"&vals="+$("#acts").val();
	$.ajax({
		type : 'GET',
		dataType : 'json',
		data: dataAux,
		url : 'editarActividad',
		contentType: "application/json; charset=utf-8",
		success : function(result) 
		{
			$("#actModal").modal('toggle');
			$('#tab3').DataTable().destroy();
			listarActividades(tema);
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
} 

function valida_copia(e) { 
    tecla = (document.all)?e.keyCode:e.which; 
    if(tecla==86 && e.ctrlKey){
      return false;
    }         
}

function valida_caracteres(e,div){
  tecla = (document.all) ? e.keyCode : e.which;
  if (tecla==8){
    document.getElementById(div).disabled = false;
    return true;
  }        
  patron =/^[0-9]+$/i;
  tecla_final = String.fromCharCode(tecla);  
  return patron.test(tecla_final);
}

function listarCursosPorEscuela(){
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'listarCursosPorEscuela',
		data: 'perfil='+perfil,
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			$("#curso4").empty();
			$("#curso4").append("<option value=0>Seleccione</option>");
			$.each(data, function(i, item) {
				var valor = item.id;
				var texto = item.nombreCurso;
				$("#curso4").append("<option value="+valor+">"+texto+"</option>");
			});
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
}

function obtenerGrupo(){
	var curso = $("#curso4").val();
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'listarGrupos',
		data: 'curso='+curso,
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			$("#grupo4").empty();
			$("#grupo4").append("<option value=0>Seleccione</option>");
			$.each(data, function(i, item) {
				$("#grupo4").append("<option value="+item.grupo+">"+item.grupo+"</option>");
			});
			cargarUnidades('unidadDidactica4');
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});	
}

/* INICIO LISTADO DE CURSOS (DOCENTE-DELEGADO) */ 
function ActionWeek(v, s){
	selectTemas(v,s);
	get_Obs();
}
function get_Obs(){
	var curso4 = $("#curso4").val();
	var grupo4 = $("#grupo4").val();
	var turno4 = $("#turno4").val();
	var unidadDidactica4 = $("#unidadDidactica4").val();
	var semana4 = $("#semana4").val();
	var id = curso4 + grupo4 + "t" + turno4 + "_s" + semana4;
    $.ajax({
		type : 'GET',
		dataType : 'json',
		url : 'Avance_obs',
		data: 'id=' + id,
		success : function(result) 
		{
			$("#observacionesDoce").val(result.observacion1.stringValue);
			$("#observacionesDele").val(result.observacion2.stringValue);
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	}); 
}
function get_Ans(){
	$('#tabListado').DataTable().destroy();
	var dataSet = new Array();
	var curso4 = $("#curso4").val();
	var grupo4 = $("#grupo4").val();
	var turno4 = $("#turno4").val();
	var unidadDidactica4 = $("#unidadDidactica4").val();
	var semana4 = $("#semana4").val();
	var tema4 = $("#tema4").val();
	var id = curso4 + grupo4 + "t" + turno4 + "_s" + semana4 + "_t" + tema4;
	var filaset;
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : 'mostrarReporte',
		data: 'id='+id+'&semana='+semana4+'&tema='+tema4,
		success : function(result) 
		{
			var data = jQuery.parseJSON(result);
			 $.each(data, function(i, item) {
				filaset = new Array();
				filaset.push(i); //codigo
				filaset.push(i+1); //#
				filaset.push(item.stringValue);
				filaset.push('<input type="checkbox" disabled '+(item.booleanValue1==true?'checked':'')+'>'); //respuesta docente
				filaset.push('<input type="checkbox" disabled '+(item.booleanValue2==true?'checked':'')+'>'); //respuesta delegado
				dataSet.push(filaset);       			    
			});
			// TABLA DE CURSOS 
			$('#tabListado').DataTable( {
                "language": {
                  "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                },
                data: dataSet,
                bPaginate: true,
                bInfo: false,
                searching: true,
                responsive: true,
                columns: [
                  { title: "Código", "visible": false },
                  { title: "#" },
                  { title: "Actividad" },
                  { title: "Respuesta docente" },
                  { title: "Respuesta delegado" }
                ]
            } );
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	}); 
}
function cargarCursosPerfil(id, user){
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : 'cargarCursosPerfil',
		data : 'user='+user+'&perfil='+perfil,
		success : function(result) 
		{
			var s = document.getElementById(id);
			$.each(result, function(i, item) {
				var op = document.createElement("option");
				op.value = item.codigo;
				op.text = item.nombre;
				s.add(op);
			});
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
}
function ActionCurso2(v, id){
	obtenerGrupo2(v, id);
}
function obtenerGrupo2(v, id){
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : 'cargarGruposPerfil',
		data : 'user='+$("#usuario").text()+'&perfil='+perfil+'&v='+v,
		success : function(result) 
		{
			var s = document.getElementById(id);
			$("#"+id).empty();
			$("#"+id).append("<option value=0>Seleccione</option>")
			$.each(result, function(i, item) {
				var op = document.createElement("option");
				op.value = item.grupo;
				op.text = item.grupo;
				s.add(op);
			});
			cargarUnidades("unidadDidactica3");
		},
		error : function(xhr, ajaxOptions, thrownError) {
  			alert(xhr.status + ' ' + thrownError);
		}
	});
}