package pe.edu.unsch.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pe.edu.unsch.entities.Archivo;

public interface ArchivoService {
	
	public List<Archivo> listarDocumentos(long l);
	
	public Archivo downloadDocumento(long l);
	
	public void saveDocumento(MultipartFile data, String nombre);
	
	public void removeDocumento(long l);

	public InputStream genSolicitud(String name, String last_name, String doc, String categoria_actual, String categoria_nueva, String domicilio);

}
