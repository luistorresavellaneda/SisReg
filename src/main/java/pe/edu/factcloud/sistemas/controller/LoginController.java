package pe.edu.factcloud.sistemas.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.factcloud.sistemas.service.LoginService;

@Controller
public class LoginController {
	
	@Resource(name="LoginService")
	private LoginService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:/showLoginForm";
	}
	@RequestMapping(value = "/showLoginForm", method = RequestMethod.GET)
	public String showLoginForm() {		
		return "login";
	}
	@RequestMapping(value = "/verifyLogin", method = RequestMethod.POST)
	public String verifyLogin(@RequestParam String usuario, 
			@RequestParam String contrasenia,
			HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.verifyLogin(usuario, contrasenia, session, model);
		return response;
	}
	@RequestMapping(value = "/obtenerUsuario", method = RequestMethod.GET)
	public @ResponseBody String obtenerUsuario(HttpSession session, Model model) {
		String response = service.obtenerUsuario(session);
		return response;
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpSession session,Model model) {
		String usuario = (String) session.getAttribute("usersession");
		model.addAttribute("usuario",usuario);
		return "home";
	}
	@RequestMapping(value = "/listarMenuOpciones", method = RequestMethod.GET)
	public @ResponseBody String listarMenuOpciones(HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.listarMenuOpciones(session, model);
		return response;
	}
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(HttpSession session,Model model) {
		return "menu";
	}
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String registro(HttpSession session,Model model) {
		return "registro";
	}
	@RequestMapping(value = "/registrosilabo", method = RequestMethod.POST)
	public String registrosilabo(HttpSession session,@RequestParam String idCurso,Model model) {
		session.setAttribute("idCurso",idCurso);		
		return "registrosilabo";
	}
	@RequestMapping(value = "/control", method = RequestMethod.GET)
	public String control(HttpSession session,Model model) {
		return "control";
	}
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(HttpSession session,Model model) {
		return "listado";
	}
	@RequestMapping(value = "/listarCursosPorCoordinador", method = RequestMethod.GET)
	public @ResponseBody String listarCursosPorCoordinador(HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.listarCursosPorCoordinador(session, model);
		return response;
	}	
	@RequestMapping(value = "/listarUnidadDidactica", method = RequestMethod.GET)
	public @ResponseBody String listarUnidadDidactica(HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.listarUnidadDidactica(session, model);
		return response;
	}	
	@RequestMapping(value = "/guardarUnidadDidactica", method = RequestMethod.POST)
	public @ResponseBody String guardarUnidadDidactica(@RequestParam String idUD, 
			@RequestParam String idSE, @RequestParam Integer rows,
			HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.guardarUnidadDidactica(idUD, idSE, rows, session, model);
		return response;
	}
	@RequestMapping(value = "/editarUnidadDidactica", method = RequestMethod.GET)
	public @ResponseBody String editarUnidadDidactica(@RequestParam String idEditIdUD, 
			@RequestParam String idEditUD, @RequestParam String idEditCantSem, 
			HttpSession session) throws IOException, InterruptedException, ExecutionException {
		String response = service.editarUnidadDidactica(idEditIdUD, idEditUD, idEditCantSem, session);
		return response;		
	}
	///////
	@RequestMapping(value = "/listarSemanas", method = RequestMethod.GET)
	public @ResponseBody String listarSemanas(@RequestParam Integer unidad, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.listarSemanas(unidad, session, model);
		return response;
	}
	@RequestMapping(value = "/listarTemas", method = RequestMethod.GET)
	public @ResponseBody String listarTemas(@RequestParam Integer semana, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.listarTemas(semana, session, model);
		return response;
	}
	@RequestMapping(value = "/guardarTema", method = RequestMethod.POST)
	public @ResponseBody String guardarTema(@RequestParam Integer semana, 
			@RequestParam String tema, @RequestParam Integer rows,
			HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.guardarTema(semana, tema, rows, session, model);
		return response;
	}
	@RequestMapping(value = "/editarTema", method = RequestMethod.GET)
	public @ResponseBody String editarTema(@RequestParam String idEditIdTem, 
			@RequestParam String idEditTem,	HttpSession session) throws IOException, InterruptedException, ExecutionException {
		String response = service.editarTema(idEditIdTem, idEditTem, session);
		return response;		
	}
	@RequestMapping(value = "/listarActividades", method = RequestMethod.GET)
	public @ResponseBody String listarActividades(@RequestParam Integer perfil, @RequestParam Integer semana, @RequestParam Integer tema, @RequestParam String turno, @RequestParam String grupo, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.listarActividades(perfil, semana, tema, turno, grupo, session, model);
		return response;
	}
	@RequestMapping(value = "/guardarActividad", method = RequestMethod.POST)
	public @ResponseBody String guardarActividad(@RequestParam Integer semana, 
			@RequestParam Integer tema, @RequestParam String vals, @RequestParam String act,
			HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.guardarActividad(semana, tema, vals, act, session, model);
		return response;
	}
	@RequestMapping(value = "/editarActividad", method = RequestMethod.GET)
	public @ResponseBody String editarActividad(@RequestParam Integer semana, 
			@RequestParam Integer tema, @RequestParam String idActOld, @RequestParam String IdEditAct, @RequestParam String vals,
			HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.editarActividad(semana, tema, idActOld, IdEditAct, vals, session, model);
		return response;
	}
	
	@RequestMapping(value = "/getObs", method = RequestMethod.GET)
	public @ResponseBody String getObs(@RequestParam Integer perfil, @RequestParam Integer semana, 
			@RequestParam String turno, @RequestParam String grupo, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.getObs(perfil, semana, turno, grupo, session, model);
		return response;
	}
	@RequestMapping(value = "/updateRespuesta", method = RequestMethod.GET)
	public @ResponseBody String updateRespuesta(@RequestParam Integer perfil, @RequestParam Integer semana, 
			@RequestParam Integer tema, @RequestParam String turno, @RequestParam boolean val, @RequestParam Integer indice, @RequestParam Integer rows,
			HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.updateRespuesta(perfil, semana, tema, turno, val, indice, rows, session, model);
		return response;
	}
	@RequestMapping(value = "/updateObs", method = RequestMethod.GET)
	public @ResponseBody String updateObs(@RequestParam Integer perfil, @RequestParam Integer semana, 
			@RequestParam String turno, @RequestParam String val, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.updateObs(perfil, semana, turno, val, session, model);
		return response;
	}
	@RequestMapping(value = "/listarCursosPorEscuela", method = RequestMethod.GET)
	public @ResponseBody String listarCursosPorEscuela(@RequestParam Integer perfil,HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.listarCursosPorEscuela(perfil,session, model);
		return response;
	}
	@RequestMapping(value = "/mostrarReporte", method = RequestMethod.GET)
	public @ResponseBody String mostrarReporte(@RequestParam String id, @RequestParam Integer semana, @RequestParam Integer tema, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.mostrarReporte(id, semana, tema, session, model);
		return response;
	}
	@RequestMapping(value = "/listarGrupos", method = RequestMethod.GET)
	public @ResponseBody String listarGrupos(@RequestParam String curso,HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException {		
		String response = service.listarGrupos(curso,session, model);
		return response;
	}
	@RequestMapping(value = "/Avance_obs", method = RequestMethod.GET)
	public @ResponseBody String Avance_obs(@RequestParam String id, HttpSession session, Model model) {
		String response = service.Avance_obs(id,session, model);
		return response;
	}
	@RequestMapping(value = "/cargarCursosPerfil", method = RequestMethod.GET)
	public @ResponseBody String cargarCursosPerfil(@RequestParam String user, @RequestParam Integer perfil) {
		String response = service.cargarCursosPerfil(user,perfil);
		return response;
	}
	@RequestMapping(value = "/cargarGruposPerfil", method = RequestMethod.GET)
	public @ResponseBody String cargarGruposPerfil(@RequestParam String user, @RequestParam Integer perfil, @RequestParam String v, HttpSession session) {
		String response = service.cargarGruposPerfil(user,perfil,v,session);
		return response;
	}
}
