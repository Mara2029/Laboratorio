package zegel.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import zegel.edu.pe.models.Categorias;
import zegel.edu.pe.models.Clubes;
import zegel.edu.pe.models.Eventos;
import zegel.edu.pe.models.Niveles;
import zegel.edu.pe.models.Puntaje;
import zegel.edu.pe.models.Solicitudes;
import zegel.edu.pe.models.Tipo_Usuario;
import zegel.edu.pe.models.Usuarios;
import zegel.edu.pe.services.CategoriasServices;
import zegel.edu.pe.services.ClubesServices;
import zegel.edu.pe.services.EventosServices;
import zegel.edu.pe.services.NivelesServices;
import zegel.edu.pe.services.PuntajeServices;
import zegel.edu.pe.services.SolicitudesServices;
import zegel.edu.pe.services.Tipo_UsuarioServices;
import zegel.edu.pe.services.UsuariosServices;

@Controller
@RequestMapping("/usuarios")
public class UsuariosControllers {
	@Autowired
	private UsuariosServices usuS;
	@Autowired
	private CategoriasServices catS;
	@Autowired
	private NivelesServices nivS;
	@Autowired
	private PuntajeServices punS;
	@Autowired
	private ClubesServices cluS;
	@Autowired
	private SolicitudesServices solS;
	@Autowired
	private Tipo_UsuarioServices tipoS;
	@Autowired
	private EventosServices eveS;
	
	@GetMapping("/registro")
	public String listar(Model mod) {
		
		List<Usuarios> us = usuS.getListUsuarios();
		List<Categorias> ct = catS.getListarCategorias();
		List<Niveles> nv = nivS.getListarNiveles();
	    List<Puntaje> pt = punS.getListPuntaje();
	    List<Clubes> cb = cluS.getListClubes();
	    List<Solicitudes> sl = solS.getListSolicitudes();
	    List<Tipo_Usuario> tp = tipoS.getListarTipo_Usuario();
		
		Usuarios usu = new Usuarios();
		
		mod.addAttribute("usuarios", us);	
		mod.addAttribute("cat", ct);
		mod.addAttribute("niv", nv);
		mod.addAttribute("pun", pt);
		mod.addAttribute("clu", cb);
		mod.addAttribute("sol", sl);
		mod.addAttribute("tipo", tp);
		mod.addAttribute("usua", usu);
		
		return "usuarios/registro";
	}
	
	@PostMapping("/guardar")
	public String guardar(Usuarios usu, @RequestParam("file") MultipartFile file){
		
		Usuarios usua = usuS.buscarUsu(usu.getDni());
		
		if( usua == null ) {
			System.out.println(file.getOriginalFilename());
			
			usu.setFoto(file.getOriginalFilename());
			
            Puntaje nuevoPun = new Puntaje();
			nuevoPun.setPuntaje(0);
			Puntaje pun = punS.guardarPuntaje(nuevoPun);
			
			Niveles niv = nivS.getListNivelesId(1);
			
			Tipo_Usuario tipo = tipoS.getListTipo_UsuarioId(5);
			
			usu.setNiveles(niv);
			usu.setPuntaje(pun);
			usu.setTipo_usuario(tipo);
			
			usuS.save(file);
			usuS.guardarUsuarios(usu);
		}
		else {
			System.out.println("Ya existe este usuario");
		}
		
		return "redirect:/login";
	}

	
	@GetMapping("/prueba")
	public String prueba(){
		return "prueba";
	}
	
//Perfil
	@GetMapping({"/perfil"})
	public String mostrar(HttpServletRequest request, Model modelo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		modelo.addAttribute("isAuthenticated", authentication != null && authentication.isAuthenticated());
		modelo.addAttribute("currentUri", request.getRequestURI());
		
	    String username = authentication.getName();
	    Usuarios usuarioAutenticado = usuS.CorreoUsuario(username);
	    if (usuarioAutenticado == null) {
	        return "redirect:/login";
	    }
	    
	    Integer categoriaId = usuarioAutenticado.getCategorias().getId();
	    List<Eventos> eventosPorCategoria = eveS.getEventosPorCategoria(categoriaId);
	    
	    List<Usuarios> usuariosPorCategoria = usuS.getListUsuariosPorCategoria(
	            usuarioAutenticado.getCategorias().getId());
	    
	    String foto = usuarioAutenticado.getFoto();
	    if (foto == null || foto.isEmpty()) {
	        foto = "default.jpg";
	    }
		
		modelo.addAttribute("usuar", usuarioAutenticado);
		modelo.addAttribute("correo", usuarioAutenticado.getCorreo());
		modelo.addAttribute("foto", usuarioAutenticado.getFoto());
		modelo.addAttribute("categoria", usuarioAutenticado.getCategorias().getNombre());
		modelo.addAttribute("club", usuarioAutenticado.getClubes().getNombre());
		modelo.addAttribute("nivel", usuarioAutenticado.getNiveles().getNombre());
		modelo.addAttribute("puntaje", usuarioAutenticado.getPuntaje().getPuntaje());
		modelo.addAttribute("eventos", eventosPorCategoria);
		modelo.addAttribute("usuario", usuariosPorCategoria);
		
		return "usuarios/perfil";
	}
	
	@GetMapping({"/perfil/orden-merito"})
	public String ordenMerito(@RequestParam(required = false)Integer categoriaId, HttpServletRequest request, Model modelo){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		modelo.addAttribute("isAuthenticated", authentication != null && authentication.isAuthenticated());
		modelo.addAttribute("currentUri", request.getRequestURI());
		
        // Lista de usuarios filtrados según la categoría seleccionada
        List<Usuarios> usuariosPorCategoria = (categoriaId == null)
            ? usuS.getListUsuarios() // Si no se seleccionó categoría, muestra todos los usuarios
            : usuS.getListUsuPorCategoria(categoriaId);
        
        // Ordenar usuarios por puntaje de mayor a menor
        usuariosPorCategoria.sort((u1, u2) -> u2.getPuntaje().getPuntaje() - u1.getPuntaje().getPuntaje());

        // Agregar datos al modelo
        modelo.addAttribute("usuario", usuariosPorCategoria);
        modelo.addAttribute("categorias", catS.getListarCategorias()); // Para llenar el select
		
		return "usuarios/orden-merito";
	}
}
