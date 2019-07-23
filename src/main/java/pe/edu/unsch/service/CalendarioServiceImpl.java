package pe.edu.unsch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unsch.dao.CalendarioDao;
import pe.edu.unsch.entities.Calendario;

@Service("calendarioService")
public class CalendarioServiceImpl implements CalendarioService {
	@Autowired
	private CalendarioDao calendarioDao;

	@Override
	public List<Calendario> listarCalendario() {
		return calendarioDao.listarCalendario();
	}

}
