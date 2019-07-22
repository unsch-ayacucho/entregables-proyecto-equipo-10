package pe.edu.unsch.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pe.edu.unsch.entities.Archivo;

public interface ArchivoService {
	
	public Archivo getArchivo(long l);
	
	public List<Archivo> listarDocumentos(long l);
	
	public Archivo downloadDocumento(long l);
	
	public void saveDocumento(MultipartFile data, String nombre, long idExpediente);
	
	public void removeDocumento(long l);

	public InputStream genSolicitud(String name, String last_name, String doc, String categoria_actual, String categoria_nueva, String domicilio, long idusuario);
	
	public int saveSolicitud(MultipartFile data, long idExpediente);

}
