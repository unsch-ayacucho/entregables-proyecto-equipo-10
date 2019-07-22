package pe.edu.unsch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unsch.dao.HistorialDao;
import pe.edu.unsch.entities.Historial;

@Service("historialService")
public class HistorialServiceImpl implements HistorialService {
	@Autowired
	private HistorialDao historialDao;
	
	@Override
	public List<Historial> listarHistorial(long idusuario) {
		return historialDao.listarHistorial(idusuario);
	}

	@Override
	public void newHistorial(long idusuario, String detalle) {
		historialDao.newHistorial(idusuario, detalle);
	}

}
