package pe.edu.unsch.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class HomeController {
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		if(session.getAttribute("usuario") == null || session.getAttribute("usuario").equals("")) {
			return "redirect:/login";
		} else {
			model.addAttribute("title", "Dashboard");
			return "views/admin/home/index";
			
		}
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redir) {
		session.invalidate();
		redir.addFlashAttribute("error", "Ha cerrado sesión correctamente.");
		return "redirect:/login";
	}

}
