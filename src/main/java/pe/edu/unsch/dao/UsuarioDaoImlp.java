package pe.edu.unsch.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.unsch.entities.Docente;
import pe.edu.unsch.entities.Usuario;
import pe.edu.unsch.service.HistorialService;

@Repository("usuarioDao")
public class UsuarioDaoImlp implements UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private HistorialService historialService;

	@Override
	public Usuario datosUsuario(long l) {
		return (Usuario) entityManager.find(Usuario.class, l);
	}
	
	@Transactional
	@Override
	public int changePass(String old_pass, String new_pass, long l) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		Usuario user = (Usuario) entityManager.find(Usuario.class, l);
		String new_pass_c = enc.encode(new_pass);
		if(enc.matches(old_pass, user.getPassword())) {
			user.setPassword(new_pass_c);
			try {
				entityManager.merge(user);
				this.historialService.newHistorial(l, "Contraseña actualizada.");
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		} else {
			return 0;
		}
	}
	
	@Transactional
	@Override
	public int changeParam(String new_celular, String new_domicilio, long l) {
		Usuario user = (Usuario) entityManager.find(Usuario.class, l);
		Docente docente = user.getDocentes().stream().findFirst().get();
		
		boolean bool_cel = !docente.getCelular().trim().toLowerCase().equals(new_celular.trim().toLowerCase());
		boolean bool_dom = !docente.getDomicilio().trim().toLowerCase().equals(new_domicilio.trim().toLowerCase());
		
		if(bool_cel) {
			docente.setCelular(new_celular);
		}
		
		if(bool_dom) {
			docente.setDomicilio(new_domicilio);
		}
		
		
		try {
			entityManager.merge(docente);
			if(bool_cel) {
				this.historialService.newHistorial(l, "Número celular actualizado.");
			}
			
			if(bool_dom) {
				this.historialService.newHistorial(l, "Domicilio actualizado.");
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
}
