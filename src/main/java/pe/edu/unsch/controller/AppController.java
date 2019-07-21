package pe.edu.unsch.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.unsch.entities.Usuario;
import pe.edu.unsch.service.ArchivoService;

@Controller
@RequestMapping("/app")
public class AppController {
	@Autowired
	private ArchivoService archivoService;
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Dashboard");
		return "views/app/index";
	}
	
	@GetMapping("/doc")
	public String doc(Model model, @ModelAttribute("usuario") Usuario usuario) {
		model.addAttribute("title", "Documentos");
		model.addAttribute("docs", this.archivoService.listarDocumentos(usuario.getIdusuario()));
		return "views/app/modulos/documentos";
	}
}
