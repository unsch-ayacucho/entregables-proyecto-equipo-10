package pe.edu.unsch.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.unsch.entities.Docente;
import pe.edu.unsch.entities.Usuario;

@Repository("usuarioDao")
public class UsuarioDaoImlp implements UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Usuario login(String usuario, String password) {
		return (Usuario) entityManager

				.createQuery("from Usuario where usuario = :usuario and password = :password")

				.setParameter("usuario", usuario)
				.setParameter("password", password)
				.getSingleResult();
	}

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
		
		docente.setCelular(new_celular);
		docente.setDomicilio(new_domicilio);
		
		try {
			entityManager.merge(docente);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
}
