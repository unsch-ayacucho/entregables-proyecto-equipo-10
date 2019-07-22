package pe.edu.unsch.dao;

import java.util.List;

import pe.edu.unsch.entities.Expediente;

public interface ExpedienteDao {
	public List<Expediente> listarExpedientes(long iddocente);

	public int addExpediente(String name, long l);

	public List<Expediente> listarExpedientesHabiles(long iddocente);

	public boolean isEditable(long idexpediente);

	public int removeExpediente(long l);

	public int addSolicitud(long l);

	public int removeSolicitud(long l);

}
