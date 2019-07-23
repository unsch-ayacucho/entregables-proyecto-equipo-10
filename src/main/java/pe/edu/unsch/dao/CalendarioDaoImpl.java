package pe.edu.unsch.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import pe.edu.unsch.entities.Calendario;

@Repository("calendarioDao")
public class CalendarioDaoImpl implements CalendarioDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Calendario> listarCalendario() {
		return (List<Calendario>) entityManager
				.createQuery("from Calendario", Calendario.class)
				.getResultList();
	}

}
