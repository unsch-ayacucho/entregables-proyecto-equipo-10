package pe.edu.unsch.service;

import java.io.InputStream;
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
	public void saveDocumento(MultipartFile data, String nombre, long idExpediente) {
		archivoDao.saveDocumento(data, nombre, idExpediente);
	}

	@Override
	public void removeDocumento(long l) {
		archivoDao.removeDocumento(l);
		
	}

	@Override
	public InputStream genSolicitud(String name, String last_name, String doc, String categoria_actual, String categoria_nueva, String domicilio, long idusuario) {
		return archivoDao.genSolicitud(name, last_name, doc, categoria_actual, categoria_nueva, domicilio, idusuario);
		
	}

	@Override
	public Archivo getArchivo(long l) {
		return archivoDao.getArchivo(l);
	}

	@Override
	public int saveSolicitud(MultipartFile data, long idExpediente) {
		return archivoDao.saveSolicitud(data, idExpediente);
	}
	
}
