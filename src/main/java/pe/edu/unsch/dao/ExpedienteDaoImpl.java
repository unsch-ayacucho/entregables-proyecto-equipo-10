package pe.edu.unsch.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.unsch.entities.Archivo;
import pe.edu.unsch.entities.Docente;
import pe.edu.unsch.entities.Expediente;
import pe.edu.unsch.entities.Usuario;
import pe.edu.unsch.service.ArchivoService;
import pe.edu.unsch.service.HistorialService;


@Repository("expedienteDao")
public class ExpedienteDaoImpl implements ExpedienteDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ArchivoService archivoService;
	
	@Autowired
	private HistorialService historialService;

	@Override
	public List<Expediente> listarExpedientes(long iddocente) {
		return (List<Expediente>) entityManager
				.createQuery("from Expediente where iddocente = :iddocente", Expediente.class)
				.setParameter("iddocente", iddocente)
				.getResultList();
	}
	
	@Override
	public List<Expediente> listarExpedientesHabiles(long iddocente) {
		return (List<Expediente>) entityManager
				.createQuery("from Expediente where iddocente = :iddocente and is_active = false and is_closed = false", Expediente.class)
				.setParameter("iddocente", iddocente)
				.getResultList();
	}
	
	@Override
	public boolean isEditable(long idexpediente){
		Expediente exp = (Expediente) entityManager.find(Expediente.class, idexpediente);
		
		boolean bool1 = exp.isIsActive();
		boolean bool2 = exp.getIsClosed();
		
		if(bool1 || bool2) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int addExpediente(String name, long l) {
		Usuario user = entityManager.find(Usuario.class, l);
		try {
			Expediente exp = (Expediente) entityManager.createQuery("from Expediente where nombre = :nombre", Expediente.class)
			.setParameter("nombre", name)
			.getSingleResult();
			
			if(exp.getNombre().trim().equals(name.trim())) {
				return -1;
			}
		} catch (NoResultException e) {
			Docente doc = entityManager.getReference(Docente.class, user.getIdusuario());
			name = StringUtils.capitalize(name);
			entityManager.persist(new Expediente(doc, name, false, false, false, new Date()) );
			this.historialService.newHistorial(doc.getUsuario().getIdusuario(), "Nuevo expediente con nombre <" + name + "> creado.");
			return 1;
		}
			
		return -1;
	}
	
	@Override
	public int removeExpediente(long l) {
		Expediente exp = entityManager.getReference(Expediente.class, l);
		if(exp.isIsActive() || exp.getIsClosed()) {
			return -1;
		} else {
			List<Archivo> ls_archivo = archivoService.listarDocumentos(l);
			for (Archivo archivo : ls_archivo) {
				Archivo arch = entityManager.getReference(Archivo.class, archivo.getIdarchivo());
				entityManager.remove(arch);
				if(arch.getNombre().equals("Solicitud")) {
					this.historialService.newHistorial(exp.getDocente().getUsuario().getIdusuario(),  "Solicitud de promoción removida del expediente con nombre <" + exp.getNombre() + ">.");
				} else {
					this.historialService.newHistorial(exp.getDocente().getUsuario().getIdusuario(), "Archivo con nombre <" + arch.getNombre() + ">, nombre de fichero <" + arch.getFullnombre() + "> y perteneciente al expediente de nombre <" + exp.getNombre() + "> eliminado.");
				}
			}
			entityManager.remove(exp);
			this.historialService.newHistorial(exp.getDocente().getUsuario().getIdusuario(), "Expediente con nombre <" + exp.getNombre() + "> eliminado.");
			return 1;
		}
	}
	
	@Transactional
	@Override
	public int addSolicitud(long l) {
		Expediente exp = entityManager.getReference(Expediente.class, l);
		
		exp.setHaveSolicitud(true);
		
		try {
			entityManager.merge(exp);
			this.historialService.newHistorial(exp.getDocente().getUsuario().getIdusuario(), "Solicitud de promoción añadida al expediente con nombre <" + exp.getNombre() + ">.");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	@Transactional
	@Override
	public int removeSolicitud(long l) {
		Expediente exp = entityManager.getReference(Expediente.class, l);
		
		exp.setHaveSolicitud(false);
		
		try {
			entityManager.merge(exp);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
}
