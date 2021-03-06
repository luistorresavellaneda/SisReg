package pe.edu.factcloud.sistemas.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface LoginService {
	
	public String verifyLogin(String usuario, String contrasenia, HttpSession session, Model model) throws InterruptedException, ExecutionException, IOException;
	public String obtenerUsuario(HttpSession session);
	public String listarMenuOpciones(HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String listarCursosPorCoordinador(HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	//Unidad didactica
	public String listarUnidadDidactica(HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String guardarUnidadDidactica(String idUD, String idSE, Integer rows, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String editarUnidadDidactica(String idEditIdUD, String idEditUD, String idEditCantSem, HttpSession session) throws IOException, InterruptedException, ExecutionException;
	//Tema
	public String listarSemanas(Integer unidad, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String listarTemas(Integer semana, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String guardarTema(Integer semana, String tema, Integer rows, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String editarTema(String idEditIdTem, String idEditTem, HttpSession session) throws IOException, InterruptedException, ExecutionException;
	//Actividad
	public String listarActividades(Integer perfil, Integer semana, Integer tema, String turno, String grupo, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String guardarActividad(Integer semana, Integer tema, String vals, String act, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String editarActividad(Integer semana, Integer tema, String idActOld, String IdEditAct, String vals, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	
	public String getObs(Integer perfil, Integer semana, String turno, String grupo, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String updateRespuesta(Integer perfil, Integer semana, Integer tema, String turno, boolean val, Integer indice, Integer rows, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String updateObs(Integer perfil, Integer semana, String turno, String val, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	
	public String listarCursosPorEscuela(Integer perfil, HttpSession session, Model model) throws IOException, InterruptedException, ExecutionException;
	public String mostrarReporte(String id, Integer semana, Integer tema, HttpSession session, Model model);
	public String listarGrupos(String curso, HttpSession session, Model model);
	
	public String Avance_obs(String id, HttpSession session, Model model);
	
	public String cargarCursosPerfil(String user, Integer perfil);
	public String cargarGruposPerfil(String user, Integer perfil, String v, HttpSession session);
}


