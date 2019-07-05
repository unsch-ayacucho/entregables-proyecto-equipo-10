package pe.edu.unsch.service;

import pe.edu.unsch.entities.Usuario;

	public interface UsuarioService {
	
		public Usuario login(String usuario, String password);
		
		public Usuario datosUsuario(long l);

		public int changePass(String old_pass, String new_pass, long l);
		
		public int changeParam(String new_celular, String new_domicilio, long l);

}
