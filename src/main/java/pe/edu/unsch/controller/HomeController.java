package pe.edu.unsch.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {
	
	@GetMapping("/home")
	public String home() {
		
	return "views/admin/home/index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	session.removeAttribute("usuario");
	return "redirect:/login";
	}

}
