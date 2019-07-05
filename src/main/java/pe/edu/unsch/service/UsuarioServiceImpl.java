package pe.edu.unsch.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unsch.dao.UsuarioDao;
import pe.edu.unsch.entities.Usuario;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public Usuario login(String usuario, String password) {
		return usuarioDao.login(usuario, password);
	}

	@Override
	public Usuario datosUsuario(long l) {
		return usuarioDao.datosUsuario(l);
	}
	
	@Override
	public int changePass(String old_pass, String new_pass, long l) {
		return usuarioDao.changePass(old_pass, new_pass, l);
	}

	@Override
	public int changeParam(String new_celular, String new_domicilio, long l) {
		return usuarioDao.changeParam(new_celular, new_domicilio, l);
	}
	
}


