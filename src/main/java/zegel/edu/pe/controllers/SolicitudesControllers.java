package zegel.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zegel.edu.pe.models.Clubes;
import zegel.edu.pe.models.Estados;
import zegel.edu.pe.models.Solicitudes;
import zegel.edu.pe.services.ClubesServices;
import zegel.edu.pe.services.EstadosServices;
import zegel.edu.pe.services.SolicitudesServices;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesControllers {
	@Autowired
	private ClubesServices cluS;
	@Autowired
	private SolicitudesServices solS;
	@Autowired
	private EstadosServices estS;
	
	@GetMapping("/registro")
	public String listar(Model mod) {
	    List<Solicitudes> sl = solS.getListSolicitudes();
	    List<Estados> es = estS.getListEstados();
	    
	    Solicitudes sol = new Solicitudes();
	    
		mod.addAttribute("solicitudes", sl);
		mod.addAttribute("est", es);
		mod.addAttribute("soli", sol);
		
		return "solicitudes/index";
	}
	
	@PostMapping("/guardar")
	public String guardar(Solicitudes sol){
		
		Clubes nuevoClu = new Clubes();
	
		Estados est = estS.getListEstadosId(2);
		nuevoClu.setSolicitudes(sol);
		
		sol.setEstados(est);
		solS.guardarSolicitudes(sol);
		cluS.guardarClubes(nuevoClu);
		System.out.println("Se registro una solicitud");
		
		return "redirect:/solicitudes/registro";
	}

}
