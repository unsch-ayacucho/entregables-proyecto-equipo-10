package pe.edu.unsch.service;

import java.util.List;

import pe.edu.unsch.entities.Expediente;

public interface ExpedienteService {
	public List<Expediente> listarExpedientes(long iddocente);
	
	public List<Expediente> listarExpedientesHabiles(long iddocente);

	public int addExpediente(String name, long l);

	public boolean isEditable(long l);
	
	public int removeExpediente(long l);
	
	public int addSolicitud(long l);

	public int removeSolicitud(long l);
}
