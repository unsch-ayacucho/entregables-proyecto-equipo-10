package pe.edu.unsch.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unsch.dao.PromocionDao;

@Service("promocionService")
@Transactional
public class PromocionServiceImpl implements PromocionService {
	@Autowired
	private PromocionDao promocionDao;
	
	@Override
	public int savePromocion(long idexpediente, long iddocente) {
		return promocionDao.savePromocion(idexpediente, iddocente);
	}

}
