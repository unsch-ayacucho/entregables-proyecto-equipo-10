package pe.edu.unsch.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.unsch.entities.Archivo;
import pe.edu.unsch.entities.Categoria;
import pe.edu.unsch.entities.Docente;
import pe.edu.unsch.entities.Expediente;
import pe.edu.unsch.entities.Promocion;
import pe.edu.unsch.service.ExpedienteService;
import pe.edu.unsch.service.HistorialService;

@Repository("promocionlDao")
public class PromocionDaoImpl implements PromocionDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private HistorialService historialService;
	
	@Autowired
	private ExpedienteService expedienteService;

	@Override
	public int savePromocion(long idexpediente, long iddocente) {
		Docente doc = entityManager.find(Docente.class, iddocente);
		Expediente exp = entityManager.find(Expediente.class, idexpediente);
		
		List<Expediente> ls_exp = expedienteService.listarExpedientes(iddocente);
		for (Expediente exp_aux : ls_exp) {
			if(exp_aux.isIsActive()) {
				return -9;
			}
		}
		
		
		if(!exp.isHaveSolicitud()) {
			return 0;
		}
		
		Archivo arch_aux = null;
		
		List<Archivo> ls_arch = new ArrayList<Archivo>(exp.getArchivos());
		for (Archivo arch : ls_arch) {
			if(arch.getNombre().equals("Solicitud")) {
				arch_aux = arch;
				break;
			}
		}
		
		try {
			ls_arch.remove(arch_aux);
		} catch (Exception e) {
			return -1;
		}
		
		
		if(ls_arch.isEmpty()) {
			return -2;
		}
			
		Categoria cater;
		
		switch (doc.getCategoria().getNombre()) {
			case "Auxiliar":
				cater = entityManager.createQuery("from Categoria where nombre = 'Asociado'", Categoria.class).getSingleResult();
				break;
			case "Asociado":
				cater = entityManager.createQuery("from Categoria where nombre = 'Principal'", Categoria.class).getSingleResult();
				break;
			default:
				return -3;
		}
		
		try {
			exp.setIsActive(true);
			entityManager.persist(exp);
			Promocion prom = new Promocion(cater, doc, exp, new Date());
			entityManager.persist(prom);
			this.historialService.newHistorial(doc.getUsuario().getIdusuario(), "Se solicitó ser promovido a la categoría de profesor <" + cater.getNombre() + ">; usando el expediente <" + exp.getNombre() + ">.");
			return 1;
		} catch (Exception e) {
			return -4;
		}
		
		
	}

}
