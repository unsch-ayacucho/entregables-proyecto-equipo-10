package pe.edu.unsch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping({"/", "/login"})
	public String login(Model model) {
		model.addAttribute("title", "LogIn");
		return "views/login/index";
	}
}
