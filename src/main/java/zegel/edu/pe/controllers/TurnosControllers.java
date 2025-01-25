package zegel.edu.pe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.persistence.EntityNotFoundException;
import zegel.edu.pe.models.Competiciones;
import zegel.edu.pe.models.Turnos;
import zegel.edu.pe.models.Usuarios;
import zegel.edu.pe.services.CompeticionesServices;
import zegel.edu.pe.services.TurnosServices;
import zegel.edu.pe.services.UsuariosServices;

@Controller
@RequestMapping("/turnos")
public class TurnosControllers {
	
	@Autowired
    private TurnosServices turnosService;

    @Autowired
    private UsuariosServices usuariosService;

    @Autowired
    private CompeticionesServices competicionesService;
        
    @GetMapping("/lista")
    public String listarTurnos(Model model) {
        model.addAttribute("turnos", turnosService.getListTurnos());
        model.addAttribute("usuarios", usuariosService.getListUsuarios());
        model.addAttribute("competiciones", competicionesService.getListarCompeticiones());
        return "competiciones/turnos";
    }
    
    
    @PostMapping("/eliminar")
    public String eliminarTurno(@RequestParam Integer id) {
        turnosService.eliminarTurnos(id);
        return "redirect:/turnos/lista";
    }
    
    @PostMapping("/crear")
    public String crearEnfrentamiento(@RequestParam Integer competicionId, @RequestParam Integer usuario1Id, @RequestParam Integer usuario2Id, Model model) {
    	
        Competiciones competicion = competicionesService.getListCompeticionesId(competicionId);
        Usuarios usuario1 = usuariosService.getUsuarioById(usuario1Id);
        Usuarios usuario2 = usuariosService.getUsuarioById(usuario2Id);
        
        if (competicion == null || usuario1 == null || usuario2 == null) {
            throw new IllegalArgumentException("Competición y usuarios no pueden ser nulos.");
        }
        turnosService.crearEnfrentamiento(competicion, usuario1, usuario2);

        return "redirect:/competiciones/detalle?competicionId=" + competicionId;
    }
    
    @PostMapping("/ganador")
    public String determinarGanador(@RequestParam Integer enfrentamientoId, Model model) {
        try {
            Turnos enfrentamiento = turnosService.obtenerEnfrentamientoPorId(enfrentamientoId);
            Usuarios ganador = turnosService.determinarGanadorAleatorio(enfrentamiento);

            model.addAttribute("ganador", ganador);
            return "redirect:/competiciones/detalle?competicionId=" + enfrentamiento.getCompeticion().getId();
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Enfrentamiento no encontrado.");
            return "error";
        }
    }
    
}
