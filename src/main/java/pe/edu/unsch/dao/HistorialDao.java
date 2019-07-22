package pe.edu.unsch.dao;

import java.util.List;

import pe.edu.unsch.entities.Historial;

public interface HistorialDao {

	public void newHistorial(long idusuario, String detalle);

	public List<Historial> listarHistorial(long idusuario);

}
