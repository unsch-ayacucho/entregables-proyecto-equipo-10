package pe.edu.unsch.dao;

import pe.edu.unsch.entities.Usuario;

public interface UsuarioDao {
	
	public Usuario login(String usuario, String password);
	
	public Usuario datosUsuario(long l);

}
