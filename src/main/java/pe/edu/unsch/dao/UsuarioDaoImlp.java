package pe.edu.unsch.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

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
	
	@Override
	public int changePass(String old_pass, String new_pass, long l) {
		Usuario user = (Usuario) entityManager.find(Usuario.class, l);
		if(user.getPassword().equals(old_pass)) {
			user.setPassword(new_pass);
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
