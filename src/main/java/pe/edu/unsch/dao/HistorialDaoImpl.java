package pe.edu.unsch.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import pe.edu.unsch.entities.Historial;
import pe.edu.unsch.entities.Usuario;

@Repository("historialDao")
public class HistorialDaoImpl implements HistorialDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Historial> listarHistorial(long idusuario) {
		return (List<Historial>) entityManager
				.createQuery("from Historial where idusuario = :idusuario", Historial.class)
				.setParameter("idusuario", idusuario)
				.getResultList();
	}
	
	@Override
	public void newHistorial(long idusuario, String detalle) {
		Usuario user = entityManager.find(Usuario.class, idusuario);
		
		Historial hist = new Historial(user, detalle, new Date());
		entityManager.persist(hist);
	}
}
