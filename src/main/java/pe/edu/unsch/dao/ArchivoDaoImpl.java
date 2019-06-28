package pe.edu.unsch.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.unsch.entities.Archivo;
import pe.edu.unsch.entities.Docente;

@Repository("archivoDao")
public class ArchivoDaoImpl implements ArchivoDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Archivo> listarDocumentos(long l) {
		return (List<Archivo>) entityManager
				.createQuery("select new Archivo(arc.idarchivo as idarchivo, arc.nombre as nombre, arc.data as data, arc.fecha as fecha) from Archivo arc inner join arc.docente doc inner join doc.usuario usu where usu.idusuario = :idUsuario", Archivo.class)
				.setParameter("idUsuario", l)
				.getResultList();
	}
	
	@Override
	public Archivo downloadDocumento(long l) {
		return (Archivo) entityManager

				.createQuery("from Archivo where idarchivo = :idArchivo")
				.setParameter("idArchivo", l)
				.getSingleResult();
	}

	@Override
	public void saveDocumento(MultipartFile data, String nombre) {
		Docente doc = entityManager.find(Docente.class, (long) 1);
		try {
			entityManager.persist(new Archivo(4, doc, nombre, data.getBytes(), new Date()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
