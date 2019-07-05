package pe.edu.unsch.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.unsch.dao.ArchivoDao;
import pe.edu.unsch.entities.Archivo;

@Service("archivoService")
@Transactional
public class ArchivoServiceImpl implements ArchivoService {
	
	@Autowired
	private ArchivoDao archivoDao;

	@Override
	public List<Archivo> listarDocumentos(long l) {
		return archivoDao.listarDocumentos(l);
	}

	@Override
	public Archivo downloadDocumento(long l) {
		return archivoDao.downloadDocumento(l);
	}

	@Override
	public void saveDocumento(MultipartFile data, String nombre) {
		archivoDao.saveDocumento(data, nombre);
	}

	@Override
	public void removeDocumento(long l) {
		archivoDao.removeDocumento(l);
		
	}

	@Override
	public void genSolicitud(String name) {
		archivoDao.genSolicitud(name);
		
	}
	
	
	
}
