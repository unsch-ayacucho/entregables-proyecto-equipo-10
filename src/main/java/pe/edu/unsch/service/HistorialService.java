package pe.edu.unsch.service;

import java.util.List;

import pe.edu.unsch.entities.Historial;

public interface HistorialService {
	
	public List<Historial> listarHistorial(long idusuario);
	
	public void newHistorial(long idusuario, String detalle);

}
