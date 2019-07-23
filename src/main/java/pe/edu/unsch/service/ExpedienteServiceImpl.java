package pe.edu.unsch.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unsch.dao.ExpedienteDao;
import pe.edu.unsch.entities.Expediente;

@Service("expedienteService")
@Transactional
public class ExpedienteServiceImpl implements ExpedienteService {
	@Autowired
	private ExpedienteDao expedienteDao;

	@Override
	public List<Expediente> listarExpedientes(long iddocente) {
		return expedienteDao.listarExpedientes(iddocente);
	}
	
	@Override
	public List<Expediente> listarExpedientesHabiles(long iddocente) {
		return expedienteDao.listarExpedientesHabiles(iddocente);
	}

	@Override
	public int addExpediente(String name, long l) {
		return expedienteDao.addExpediente(name, l);
	}
	
	@Override
	public boolean isEditable(long l) {
		return expedienteDao.isEditable(l);
	}

	@Override
	public int removeExpediente(long l) {
		return expedienteDao.removeExpediente(l);
	}

	@Override
	public int addSolicitud(long l) {
		return expedienteDao.addSolicitud(l);
	}

	@Override
	public int removeSolicitud(long l) {
		return expedienteDao.removeSolicitud(l);
	}

	@Override
	public boolean isSendable(long iddocente) {
		return expedienteDao.isSendable(iddocente);
	}
}
