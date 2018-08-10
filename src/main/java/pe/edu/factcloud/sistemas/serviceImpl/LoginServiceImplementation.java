package pe.edu.factcloud.sistemas.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import pe.edu.factcloud.sistemas.service.LoginService;

@Service("LoginService")
public class LoginServiceImplementation implements LoginService{

	public FirebaseOptions options;
	public FirebaseApp fba;
	public Firestore db;
	public boolean inicializado=false;
	
	private static String base_rest = "https://firestore.googleapis.com/v1beta1/projects/cloudfact-84ac2/databases/(default)/documents/";

	@Override
	public String verifyLogin(String usuario, String contrasenia, HttpSession session, Model model) throws InterruptedException, ExecutionException, IOException {
		session.setAttribute("loginError","");
		String rpta = "showLoginForm";
		String uri = base_rest + ":runQuery";
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.postForObject( uri, "{\r\n" + 
	    		"    \"structuredQuery\": {\r\n" + 
	    		"    	\"select\": { \"fields\": [ {\"fieldPath\": \"id\"}, ],	},\r\n" + 
	    		"        \"where\" : {\r\n" + 
	    		"        	\"compositeFilter\" :\r\n" + 
	    		"        	{\r\n" + 
	    		"        		\"op\": \"AND\",\r\n" + 
	    		"        		\"filters\": [\r\n" + 
	    		"	    			{ \"fieldFilter\" : { \"field\": {\"fieldPath\": \"id\"}, \"op\":\"EQUAL\", \"value\": {\"stringValue\": \"" + usuario + "\"} } },\r\n" + 
	    		"	    			{ \"fieldFilter\" : { \"field\": {\"fieldPath\": \"clave\"}, \"op\":\"EQUAL\", \"value\": {\"stringValue\": \"" + contrasenia + "\"} } },\r\n" + 
	    		"    			],\r\n" + 
	    		"        	}\r\n" + 
	    		"        },\r\n" + 
	    		"        \"from\": [{\"collectionId\": \"usuarios\"}]\r\n" + 
	    		"    }\r\n" + 
	    		"}", String.class);
	    JSONObject jsonObj = new JSONObject(result.replace("[", "").replace("]", ""));
    	if(jsonObj.has("document"))
    	{
	    	usuario = jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("id").getString("stringValue");
	    	session.setAttribute("usersession",usuario);	    	
	    	rpta="menu";
			return "redirect:/"+rpta;
    	}else {
    		session.setAttribute("loginError","Usuario o clave incorrecto.");
    	}    	
		return "redirect:/";
	}
	public String obtenerUsuario(HttpSession session) {
		String usuario = (String) session.getAttribute("usersession");
		return usuario;
	}
	public String listarMenuOpciones(HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {
		String uri = base_rest + "usuarios/" + session.getAttribute("usersession");
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
		JSONObject jsonObj = new JSONObject(result);
		String jsonStr=null;
		if(jsonObj.getJSONObject("fields").has("perfil"))
		{
			jsonStr = jsonObj.getJSONObject("fields").getJSONObject("perfil").getJSONObject("mapValue").getJSONObject("fields").toString();
		}
		return jsonStr;
	}	
	public String listarCursosPorCoordinador(HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {
		String uri = base_rest + ":runQuery";
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.postForObject( uri, "{\r\n" + 
	    		"    \"structuredQuery\": {\r\n" + 
	    		"    	\"select\": { \"fields\": [ {\"fieldPath\": \"id\"}, {\"fieldPath\": \"nombreCurso\"}, ], },\r\n" + 
	    		"        \"where\" : { \"fieldFilter\" : { \"field\": {\"fieldPath\": \"idCoordinador\"}, \"op\":\"EQUAL\", \"value\": {\"stringValue\": \"" + session.getAttribute("usersession") + "\"} }, },\r\n" + 
	    		"        \"from\": [{\"collectionId\": \"cursos\"}]\r\n" + 
	    		"    }\r\n" + 
	    		"}", String.class);
		String listaDeCursos = " ";
		JSONObject jsonObj = new JSONObject();
	    JSONArray list_json = new JSONArray(result);
	    for(int i=0;i<list_json.length();i++)
	    {
	    	jsonObj = list_json.getJSONObject(i);
	    	if(jsonObj.has("document"))
	    		if(jsonObj.getJSONObject("document").has("fields"))
	    		{
	    			listaDeCursos += "{ \"id\":\"" + jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("id").getString("stringValue")
	    							+ "\", \"nombreCurso\":\"" + jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("nombreCurso").getString("stringValue") + "\"},";
	    		}
	    }
	    listaDeCursos = "[" + listaDeCursos.substring(0, listaDeCursos.length()-1) + "]";
	    return listaDeCursos;
	}
	//Unidad dicatica
	public String listarUnidadDidactica(HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/unidades";
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject( uri, String.class);
	    
	    return result; 
	}
	public String guardarUnidadDidactica(String idUD, String idSE, Integer rows, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String result = "-1";
		int cant_semanas = 0;
		RestTemplate restTemplate = new RestTemplate();
		
		//get_semanas
		cant_semanas = get_semanas(restTemplate, session);
	    
	    //set_semanas
	    set_semanas(restTemplate, Integer.parseInt(idSE), cant_semanas, rows, session);
	    
		String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/unidades?documentId="+(rows+1);
		
	    result = restTemplate.postForObject( uri, "{\r\n" + 
	    		"      \"fields\": {\r\n" + 
	    		"        \"nombre\": { \"stringValue\": \"UNIDAD " + idUD + "\" },\r\n" + 
	    		"        \"numero\": { \"integerValue\": \"" + (rows+1) + "\" },\r\n" +
	    		"        \"semanas\": { \"integerValue\": \"" + idSE + "\" }\r\n" +
	    		"      }\r\n" + 
	    		"}", String.class);
	    return result;
	}
	public String editarUnidadDidactica(String idEditIdUD, String idEditUD, String idEditCantSem, HttpSession session) throws IOException, InterruptedException, ExecutionException
	{
		String uri = "https://firestore.googleapis.com/v1beta1/" + idEditIdUD + "?updateMask.fieldPaths=nombre&updateMask.fieldPaths=semanas";
		
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    String result = restTemplate.patchForObject(uri, "{\r\n" + 
	    		"      \"fields\": {\r\n" + 
	    		"        \"nombre\": { \"stringValue\": \"" + idEditUD + "\" },\r\n" + 
	    		"        \"semanas\": { \"integerValue\": \"" + idEditCantSem + "\" }\r\n" + 
	    		"      }\r\n" + 
	    		"}", String.class);	 
	    
	    return result;
	}
	public Integer get_semanas(RestTemplate rt, HttpSession session) {
		int cs = 0;
		String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + ":runQuery";
		String semanas = rt.postForObject( uri, "{\r\n" + 
				"    \"structuredQuery\": {\r\n" + 
				"    	\"select\": { \"fields\": [ {\"fieldPath\": \"semanas\"}] , },\r\n" + 
				"        \"from\": [{\"collectionId\": \"unidades\"}]\r\n" + 
				"    }\r\n" + 
				"}", String.class);
		JSONObject jsonObj = new JSONObject();
	    JSONArray list_json = new JSONArray(semanas);
	    for(int i=0;i<list_json.length();i++)
	    {
	    	jsonObj = list_json.getJSONObject(i);
	    	if(jsonObj.has("document"))
	    	{
	    		if(jsonObj.getJSONObject("document").has("fields"))
	    		{
	    			cs += jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("semanas").getInt("integerValue");
	    		}
	    	}
	    	else break;
	    }
	    return cs;
	}
	public void set_semanas(RestTemplate rt, Integer semanas, Integer cant_semanas, Integer rows, HttpSession session) {
		String uri = "";
		for(int i=1; i<=semanas; i++)
	    {
		    uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/semanas?documentId="+(cant_semanas+i);
		    rt.postForObject( uri, "{\r\n" + 
		    		"      \"fields\": {\r\n" + 
		    		"        \"llenado\": { \"booleanValue\": false},\r\n" + 
		    		"        \"numero\": { \"integerValue\": \"" + (cant_semanas+i) + "\" },\r\n" +
		    		"        \"unidad\": { \"integerValue\": \"" + (rows+1) + "\" }\r\n" +
		    		"      }\r\n" + 
		    		"}", String.class);
	    }
	}
	//Tema
	public String listarSemanas(Integer unidad, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + ":runQuery";
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.postForObject( uri, "{\r\n" + 
	    		"    \"structuredQuery\": {\r\n" + 
	    		"        \"where\" : {\r\n" + 
	    		"            \"fieldFilter\" : { \r\n" + 
	    		"                \"field\": {\"fieldPath\": \"unidad\"}, \r\n" + 
	    		"                \"op\":\"EQUAL\", \r\n" + 
	    		"                \"value\": {\"integerValue\": " + unidad + "}\r\n" + 
	    		"            }\r\n" + 
	    		"        },\r\n" + 
	    		"        \"from\": [{\"collectionId\": \"semanas\"}]\r\n" + 
	    		"    }\r\n" + 
	    		"}", String.class);
	    return result; 
	}
	public String listarTemas(Integer semana, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/semanas/" + semana + "/temas";
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject( uri, String.class);
	    
	    return result; 
	}
	public String guardarTema(Integer semana, String tema, Integer rows, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String result = "-1";		
		String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/semanas/" + semana + "/temas?documentId="+ (rows+1);
		RestTemplate restTemplate = new RestTemplate();
	    result = restTemplate.postForObject( uri, "{\r\n" + 
	    		"      \"fields\": {\r\n" + 
	    		"        \"nombre\": { \"stringValue\": \"" + tema + "\" },\r\n" + 
	    		"        \"numero\": { \"integerValue\": \"" + (rows+1) + "\" }\r\n" + 
	    		"      }\r\n" + 
	    		"}", String.class);
	    return result;
	}	
	public String editarTema(String idEditIdTem, String idEditTem, HttpSession session) throws IOException, InterruptedException, ExecutionException
	{
		String uri = "https://firestore.googleapis.com/v1beta1/" + idEditIdTem  + "?updateMask.fieldPaths=nombre";
		
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    String result = restTemplate.patchForObject(uri, "{\r\n" + 
	    		"      \"fields\": {\r\n" + 
	    		"        \"nombre\": { \"stringValue\": \"" + idEditTem + "\" }\r\n" + 
	    		"      }\r\n" + 
	    		"}", String.class);	    
	    return result;
	}
	//////
	public String listarActividades(Integer perfil, Integer semana, Integer tema, String turno, String grupo, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {
		String result = "";
		JSONObject jsonObj;
		JSONArray ar1, ar2, ar;
		String tipo="";
		if(perfil==1) tipo="_delegados"; else tipo="_profesores";
		if(turno.equals("n"))
		{
			String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/semanas/" + semana + "/temas/" + tema;
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.getForObject(uri, String.class);
		}
		else
		{
			//actividaes
			String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/semanas/" + semana + "/temas/" + tema;
			RestTemplate restTemplate = new RestTemplate();			
			String result1 = restTemplate.getForObject(uri, String.class);
			jsonObj = new JSONObject(result1);
			ar1 = jsonObj.getJSONObject("fields").getJSONObject("actividades").getJSONObject("arrayValue").getJSONArray("values");
			//respuestas
			try {
				uri = base_rest + "respuestas"+tipo+"/" + session.getAttribute("idCurso") + grupo + "t" + turno + "_s" + semana + "_t" + tema;
				result1 = restTemplate.getForObject(uri, String.class);
				jsonObj = new JSONObject(result1);
				ar2 = jsonObj.getJSONObject("fields").getJSONObject("respuestas").getJSONObject("arrayValue").getJSONArray("values");
			}catch (RestClientException e) {
				ar2 = new JSONArray();
			}
			ar = new JSONArray();
			for(int i=0;i<ar1.length();i++)
		    {
		    	jsonObj = ar1.getJSONObject(i);
		    	if(!ar2.isNull(i))
		    		jsonObj = jsonObj.put("booleanValue",ar2.getJSONObject(i).getBoolean("booleanValue"));
		    	else
		    		jsonObj = jsonObj.put("booleanValue",false);
		    	ar.put(jsonObj);
		    }
			result = ar.toString();
		}
	    
	    return result; 
	}
	public String guardarActividad(Integer semana, Integer tema, String vals, String act, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String result = "-1";		
		String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/semanas/" + semana + "/temas/" + tema + "?updateMask.fieldPaths=actividades";
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    result = restTemplate.patchForObject(uri, "{\r\n" + 
	    		"	\"fields\": {\r\n" +  
	    		"	    \"actividades\": {\r\n" + 
	    		"	      \"arrayValue\": {\r\n" + 
	    		"	        \"values\": [\r\n" + vals + 
	    		"	          {\r\n" + 
	    		"	            \"stringValue\": \"" + act + "\"\r\n" + 
	    		"	          }\r\n" + 
	    		"	        ]\r\n" + 
	    		"	      }\r\n" + 
	    		"	    },\r\n" +  
	    		"	}\r\n" + 
	    		"}", String.class);
	    
	    return result;
	}
	public String editarActividad(Integer semana, Integer tema, String idActOld, String IdEditAct, String vals, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException{
		String result = "-1";		
		String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/semanas/" + semana + "/temas/" + tema + "?updateMask.fieldPaths=actividades";
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    result = restTemplate.patchForObject(uri, "{\r\n" + 
	    		"	\"fields\": {\r\n" + 
	    		"	    \"actividades\": {\r\n" + 
	    		"	      \"arrayValue\": {\r\n" + 
	    		"	        \"values\": [\r\n" + vals.replaceAll(idActOld, IdEditAct) +
	    		"	        ]\r\n" + 
	    		"	      }\r\n" + 
	    		"	    },\r\n" +  
	    		"	}\r\n" + 
	    		"}", String.class);
	    
	    return result;
	}
	
	public String getObs(Integer perfil, Integer semana, String turno, String grupo, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException{
		String tipo="";
		if(perfil==1) tipo="_delegados"; else tipo="_profesores";
		String uri = base_rest + "observaciones"+tipo+"/" + session.getAttribute("idCurso") + grupo + "t" + turno + "_s" + semana;
		String result = "";
		RestTemplate restTemplate = new RestTemplate();

		try {
			result = restTemplate.getForObject(uri, String.class);
		    JSONObject jsonObj = new JSONObject(result);
		    
		    if(jsonObj.has("fields")) result = jsonObj.getJSONObject("fields").toString();
		    else result = "{\"observacion\": { \"stringValue\": \"\" }}";
		} catch (RestClientException e) {
			result = "{\"observacion\": { \"stringValue\": \"\" }}";
		}
			    
	    return result;
	}
	public String updateRespuesta(Integer perfil, Integer semana, Integer tema, String turno, boolean val, Integer indice, Integer rows, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException{
		String result = "";
		JSONObject jsonObj;
		JSONArray ar;
		String tipo="";
		if(perfil==1) tipo="_delegados"; else tipo="_profesores";
		String uri = base_rest + "respuestas"+tipo+"/" + session.getAttribute("idCurso") + "g1t" + turno + "_s" + semana + "_t" + tema;
		RestTemplate restTemplate = new RestTemplate();
		try {
			String result1 = restTemplate.getForObject(uri, String.class);
			jsonObj = new JSONObject(result1);
			ar = jsonObj.getJSONObject("fields").getJSONObject("respuestas").getJSONObject("arrayValue").getJSONArray("values");
		}catch (RestClientException e) {
			String uri_aux = base_rest + "respuestas"+tipo+"/?documentId="+ session.getAttribute("idCurso") + "g1t" + turno + "_s" + semana + "_t" + tema;
			restTemplate = new RestTemplate();
			String checklist = "";
			for(int i = 0; i < rows; i++)
				checklist += "{ \"booleanValue\": false },";
		    result = restTemplate.postForObject( uri_aux, "{\r\n" + 
		    		"      \"fields\": {\r\n" + 
		    		"        \"id\": { \"stringValue\": \"" + session.getAttribute("idCurso") + "g1t" + turno + "_s" + semana + "_t" + tema + "\" },\r\n" + 
		    		"	     \"checklist\": {\r\n" + 
		    		"	       \"arrayValue\": {\r\n" + 
		    		"	         \"values\":[ " + checklist + 
		    		"	       ]}\r\n" + 
		    		"	     },\r\n" + 
		    		"      }\r\n" + 
		    		"}", String.class);
		    String result1 = restTemplate.getForObject(uri, String.class);
			jsonObj = new JSONObject(result1);
			ar = jsonObj.getJSONObject("fields").getJSONObject("respuestas").getJSONObject("arrayValue").getJSONArray("values");
		}
		ar.getJSONObject(indice).put("booleanValue", val);
		
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    restTemplate = new RestTemplate(requestFactory);
	    result = restTemplate.patchForObject(uri + "?updateMask.fieldPaths=checklist", "{\r\n" + 
	    		"	\"fields\": {\r\n" + 
	    		"	    \"checklist\": {\r\n" + 
	    		"	      \"arrayValue\": {\r\n" + 
	    		"	        \"values\": " + ar.toString() + 
	    		"	      }\r\n" + 
	    		"	    },\r\n" +  
	    		"	}\r\n" + 
	    		"}", String.class);
	    
	    return result;
	}
	
	public String updateObs(Integer perfil, Integer semana, String turno, String val, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException{
		String result = "";
		String tipo="";
		if(perfil==1) tipo="_delegados"; else tipo="_profesores";
		String uri = base_rest + "observaciones"+tipo+"/" + session.getAttribute("idCurso") + "g1t" + turno + "_s" + semana;
		RestTemplate restTemplate;
		try {
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		    restTemplate = new RestTemplate(requestFactory);
		    result = restTemplate.patchForObject(uri + "?updateMask.fieldPaths=observacion", "{\r\n" + 
		    		"	\"fields\": {\r\n" + 
		    		"	    \"observacion\": {\r\n" + 
		    		"	      \"stringValue\": \r\n\"" + val + 
		    		"	      \"\r\n" + 
		    		"	    },\r\n" +  
		    		"	}\r\n" + 
		    		"}", String.class);
		}catch (RestClientException e) {
			String uri_aux = base_rest + "observaciones"+tipo+"/?documentId="+ session.getAttribute("idCurso") + "g1t" + turno + "_s" + semana;
			restTemplate = new RestTemplate();
		    result = restTemplate.postForObject( uri_aux, "{\r\n" + 
		    		"      \"fields\": {\r\n" + 
		    		"        \"id\": { \"stringValue\": \"" + session.getAttribute("idCurso") + "g1t" + turno + "_s" + semana + "\" },\r\n" + 
		    		"	     \"observacion\": {\r\n" + 
		    		"	       \"stringValue\": \r\n\"" + val + 
		    		"	       \"\r\n" + 
		    		"	     },\r\n" + 
		    		"      }\r\n" + 
		    		"}", String.class);
		}	    
	    return result;
	}
	public String listarCursosPorEscuela(Integer perfil, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {
		String uri = base_rest + ":runQuery";
		RestTemplate restTemplate = new RestTemplate();
		String eap = "";
		if(perfil==3) eap = "SW"; else eap = "SS";
	    String result = restTemplate.postForObject( uri, "{\r\n" + 
	    		"    \"structuredQuery\": {\r\n" + 
	    		"    	\"select\": { \"fields\": [ {\"fieldPath\": \"id\"}, {\"fieldPath\": \"nombreCurso\"}, ], },\r\n" + 
	    		"        \"where\" : { \"fieldFilter\" : { \"field\": {\"fieldPath\": \"eap\"}, \"op\":\"EQUAL\", \"value\": {\"stringValue\": \"" + eap + "\"} }, },\r\n" + 
	    		"        \"from\": [{\"collectionId\": \"cursos\"}]\r\n" + 
	    		"    }\r\n" + 
	    		"}", String.class);
		String listaDeCursos = " ";
		JSONObject jsonObj = new JSONObject();
	    JSONArray list_json = new JSONArray(result);
	    for(int i=0;i<list_json.length();i++)
	    {
	    	jsonObj = list_json.getJSONObject(i);
	    	if(jsonObj.has("document"))
	    		if(jsonObj.getJSONObject("document").has("fields"))
	    		{
	    			listaDeCursos += "{ \"id\":\"" + jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("id").getString("stringValue")
	    							+ "\", \"nombreCurso\":\"" + jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("nombreCurso").getString("stringValue") + "\"},";
	    		}
	    }
	    listaDeCursos = "[" + listaDeCursos.substring(0, listaDeCursos.length()-1) + "]";
	    return listaDeCursos;
	}
	public String listarGrupos(String curso, HttpSession session, Model model){
		session.setAttribute("idCurso",curso);		
		String uri = base_rest + ":runQuery";
		String grupo = "";
		List<String> grupos = new ArrayList<String>();
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.postForObject( uri, "{\r\n" + 
	    		"    \"structuredQuery\": {\r\n" + 
	    		"    	\"select\": { \"fields\": [ {\"fieldPath\": \"id\"}, ], },\r\n" + 
	    		"        \"where\" : { \"fieldFilter\" : { \"field\": {\"fieldPath\": \"codCurso\"}, \"op\":\"EQUAL\", \"value\": {\"stringValue\": \"" + curso + "\"} }, },\r\n" + 
	    		"        \"from\": [{\"collectionId\": \"grupos\"}]\r\n" + 
	    		"    }\r\n" + 
	    		"}", String.class);
		String listaDeGrupos = " ";
		JSONObject jsonObj = new JSONObject();
	    JSONArray list_json = new JSONArray(result);
	    for(int i=0;i<list_json.length();i++)
	    {
	    	jsonObj = list_json.getJSONObject(i);
	    	if(jsonObj.has("document"))
	    		if(jsonObj.getJSONObject("document").has("fields"))
	    		{
	    			grupo = jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("id").getString("stringValue");
	    			grupo = grupo.replace(curso, "").substring(0, 2);
	    			if(!grupos.contains(grupo)) {
	    				listaDeGrupos += "{ \"grupo\":\"" + grupo + "\"},";
	    				grupos.add(grupo);
	    			}
	    		}
	    }
	    listaDeGrupos = "[" + listaDeGrupos.substring(0, listaDeGrupos.length()-1) + "]";
	    return listaDeGrupos;
	}
	public String Avance_obs(String id, HttpSession session, Model model){
		RestTemplate restTemplate = new RestTemplate();
		
		//observacion_profesores
		String obs1 = get_obs("observaciones_profesores/", id, restTemplate);
		//observacion_delegados
		String obs2 = get_obs("observaciones_delegados/", id, restTemplate);
		
	    return "{\"observacion1\": { \"stringValue\": \"" + obs1 + "\" },"
	    		+ " \"observacion2\": { \"stringValue\": \"" + obs2 + "\" }}";
		
	}
	public String get_obs(String u, String id, RestTemplate rt)
	{
		String uri = base_rest + u +id;
		String result = "";
		try {
			result = rt.getForObject(uri, String.class);
		    JSONObject jsonObj = new JSONObject(result);
		    if(jsonObj.has("fields"))
		    	result = jsonObj.getJSONObject("fields").getJSONObject("observacion").getString("stringValue");
		} catch (RestClientException e) {
			result = "";
		}
		return result;
	}
	public String mostrarReporte(String id, Integer semana, Integer tema, HttpSession session, Model model){
		String result = "";
		JSONObject jsonObj;
		JSONArray ar1, ar2, ar3, ar;
		RestTemplate restTemplate = new RestTemplate();
		//actividaes
		String uri = base_rest + "silabus/" + session.getAttribute("idCurso") + "/semanas/" + semana + "/temas/" + tema;			
		String result1 = restTemplate.getForObject(uri, String.class);
		jsonObj = new JSONObject(result1);
		ar1 = jsonObj.getJSONObject("fields").getJSONObject("actividades").getJSONObject("arrayValue").getJSONArray("values");
		//respuestas
		
		ar2 = get_resp("respuestas_profesores/", id, restTemplate);
		ar3 = get_resp("respuestas_delegados/", id, restTemplate);
		
		ar = new JSONArray();
		for(int i=0;i<ar1.length();i++)
	    {
	    	jsonObj = ar1.getJSONObject(i);
	    	if(!ar2.isNull(i))
	    		jsonObj = jsonObj.put("booleanValue1",ar2.getJSONObject(i).getBoolean("booleanValue"));
	    	else
	    		jsonObj = jsonObj.put("booleanValue1",false);
	    	if(!ar3.isNull(i))
	    		jsonObj = jsonObj.put("booleanValue2",ar3.getJSONObject(i).getBoolean("booleanValue"));
	    	else
	    		jsonObj = jsonObj.put("booleanValue2",false);
	    	ar.put(jsonObj);
	    }
		result = ar.toString();
	    
	    return result; 
	}
	public JSONArray get_resp(String u, String id, RestTemplate rt)
	{
		String uri = base_rest + u +id;
		String rst = "";
		JSONArray result = new JSONArray();
		try {
			rst = rt.getForObject(uri, String.class);
		    JSONObject jsonObj = new JSONObject(rst);
		    if(jsonObj.has("fields"))
		    	result = jsonObj.getJSONObject("fields").getJSONObject("respuestas").getJSONObject("arrayValue").getJSONArray("values");
		} catch (RestClientException e) {
			result = new JSONArray();
		}
		return result;
	}
	
	public String cargarCursosPerfil(String user, Integer perfil) {
		String uri = base_rest + ":runQuery";
		String curso, nomCurso = "";
		List<String> cursos = new ArrayList<String>();
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.postForObject( uri, "{\r\n" + 
	    		"    \"structuredQuery\": {\r\n" + 
	    		"    	\"select\": { \"fields\": [ {\"fieldPath\": \"codCurso\"}, {\"fieldPath\": \"nombreCurso\"}, ], },\r\n" + 
	    		"        \"where\" : { \"fieldFilter\" : { \"field\": {\"fieldPath\": \"" + (perfil==1?"codDelegado":"codProfesor") + "\"}, \"op\":\"EQUAL\", \"value\": {\"stringValue\": \"" + user + "\"} }, },\r\n" + 
	    		"        \"from\": [{\"collectionId\": \"grupos\"}]\r\n" + 
	    		"    }\r\n" + 
	    		"}", String.class);
		String listaDeCursos = " ";
		JSONObject jsonObj = new JSONObject();
	    JSONArray list_json = new JSONArray(result);
	    for(int i=0;i<list_json.length();i++)
	    {
	    	jsonObj = list_json.getJSONObject(i);
	    	if(jsonObj.has("document"))
	    		if(jsonObj.getJSONObject("document").has("fields"))
	    		{
	    			curso = jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("codCurso").getString("stringValue");
	    			nomCurso = jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("nombreCurso").getString("stringValue");
	    			if(!cursos.contains(curso)) {
	    				listaDeCursos += "{ \"codigo\":\"" + curso + "\", \"nombre\":\"" + nomCurso + "\"},";
	    				cursos.add(curso);
	    			}
	    		}
	    }
	    listaDeCursos = "[" + listaDeCursos.substring(0, listaDeCursos.length()-1) + "]";
	    return listaDeCursos;
	}
	public String cargarGruposPerfil(String user, Integer perfil, String v, HttpSession session) {
		session.setAttribute("idCurso",v);
		String uri = base_rest + ":runQuery";
		String grupo = "";
		List<String> grupos = new ArrayList<String>();
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.postForObject( uri, "{\r\n" + 
	    		"    \"structuredQuery\": {\r\n" + 
	    		"    	\"select\": { \"fields\": [ {\"fieldPath\": \"id\"}, ], },\r\n" + 
	    		"        \"where\" : { \"compositeFilter\" : \r\n" + 
	    		"	    		      	{\"op\": \"AND\", \r\n" + 
	    		"	    		       		\"filters\": [ \r\n" + 
	    		"	    		    			{ \"fieldFilter\" : { \"field\": {\"fieldPath\": \"" + (perfil==1?"codDelegado":"codProfesor") + "\"}, \"op\":\"EQUAL\", \"value\": {\"stringValue\": \"" + user + "\"} } }, \r\n" + 
	    		"	    		    			{ \"fieldFilter\" : { \"field\": {\"fieldPath\": \"codCurso\"}, \"op\":\"EQUAL\", \"value\": {\"stringValue\": \"" + v + "\"} } }, \r\n" + 
	    		"	    		   			],\r\n" + 
	    		"	    				}\r\n" + 
	    		"	    		  },\r\n" + 
	    		"        \"from\": [{\"collectionId\": \"grupos\"}]\r\n" + 
	    		"    }\r\n" + 
	    		"}", String.class);
		String listaDeGrupos = " ";
		JSONObject jsonObj = new JSONObject();
	    JSONArray list_json = new JSONArray(result);
	    for(int i=0;i<list_json.length();i++)
	    {
	    	jsonObj = list_json.getJSONObject(i);
	    	if(jsonObj.has("document"))
	    		if(jsonObj.getJSONObject("document").has("fields"))
	    		{
	    			grupo = jsonObj.getJSONObject("document").getJSONObject("fields").getJSONObject("id").getString("stringValue");
	    			grupo = grupo.replace(v, "").substring(0, 2);
	    			if(!grupos.contains(grupo)) {
	    				listaDeGrupos += "{ \"grupo\":\"" + grupo + "\"},";
	    				grupos.add(grupo);
	    			}
	    		}
	    }
	    listaDeGrupos = "[" + listaDeGrupos.substring(0, listaDeGrupos.length()-1) + "]";
	    return listaDeGrupos;
	}
	
}
