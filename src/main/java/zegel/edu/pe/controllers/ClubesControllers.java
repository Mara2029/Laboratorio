package zegel.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zegel.edu.pe.models.Clubes;
import zegel.edu.pe.models.Solicitudes;
import zegel.edu.pe.models.Usuarios;
import zegel.edu.pe.services.ClubesServices;
import zegel.edu.pe.services.SolicitudesServices;
import zegel.edu.pe.services.UsuariosServices;

@Controller
@RequestMapping("/clubes")
public class ClubesControllers {
	@Autowired
	private ClubesServices cluS;
	@Autowired
	private SolicitudesServices solS;
	@Autowired
	private UsuariosServices usuS;
	
	@GetMapping("/registro")
	public String listarClubes(Model mod) {
	    
	    List<Clubes> cb = cluS.getListClubes();
	    List<Usuarios> usu = usuS.getListUsuarios();
	    List<Solicitudes> sl = solS.getListSolicitudes();
	    
	    Clubes clu = new Clubes();
	    
	    mod.addAttribute("clubes", clu);
	    mod.addAttribute("usua", usu);
		mod.addAttribute("sol", sl);
		mod.addAttribute("club", cb);
		
		return "clubes/index";
	}
	
	@PostMapping("/guardar")
	public String guardar(Clubes clu, RedirectAttributes redirectAttributes){
		
		Usuarios encargado = usuS.getUsuarioById(clu.getEncargado().getId());
		cluS.guardarClubes(clu);
		redirectAttributes.addFlashAttribute("registroExitoso", true);		
		return "redirect:/clubes/registro";
	}

}
