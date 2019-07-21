package pe.edu.unsch.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.unsch.dao.UsuarioDao;
import pe.edu.unsch.dao.UsuarioRepository;
import pe.edu.unsch.entities.Perfil;
import pe.edu.unsch.entities.Usuario;
import pe.edu.unsch.entities.UsuarioPerfil;

@Service("usuarioService")
//@Transactional
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsuario(username);
		if (user == null) throw new UsernameNotFoundException(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (UsuarioPerfil usuarioPerfil : user.getUsuarioPerfils()){
			Perfil perfil = usuarioPerfil.getPerfil();
			grantedAuthorities.add(new SimpleGrantedAuthority(perfil.getNombre()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getPassword(), grantedAuthorities);
	}
	
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


