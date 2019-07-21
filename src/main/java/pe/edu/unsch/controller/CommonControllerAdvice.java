package pe.edu.unsch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import pe.edu.unsch.dao.UsuarioRepository;
import pe.edu.unsch.entities.Usuario;

@ControllerAdvice
public class CommonControllerAdvice {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@ModelAttribute
	public void addAttributes(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			String usuario = auth.getName();
			
			if (usuario != null) {
				Usuario user = usuarioRepository.findByUsuario(usuario);
		    	model.addAttribute("usuario", user);
			}
		}
	}

}