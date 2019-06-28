package pe.edu.unsch.controller;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.unsch.entities.Usuario;
import pe.edu.unsch.service.UsuarioService;

@Controller
public class LoginController {
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping({"/", "/login"})
	public String login(HttpSession session, Model model) {
		if(session.getAttribute("usuario") == null || session.getAttribute("usuario").equals("")) {
			model.addAttribute("title", "LogIn");
			return "views/login/index";
		} else {
			return "redirect:/admin/home";
		}
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, HttpSession session, Model model, RedirectAttributes redir) {
		model.addAttribute("user", new Usuario());		
		Usuario user = null;
		
		try{
			user = this.usuarioService.login(request.getParameter("username"), request.getParameter("password"));
		}			
		catch(NoResultException | EmptyResultDataAccessException err) {
			redir.addFlashAttribute("error", "Cuenta inválida.");
			return "redirect:/login";
		}
		session.setAttribute("usuario", user);
		return "redirect:/admin/home";
	}

}
