package zegel.edu.pe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resultados")
public class ResultadosControllers {

	@GetMapping("/index")
	public String prueba(){
		return "resultados/resultados";
	}
}
