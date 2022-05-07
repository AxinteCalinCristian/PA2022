package compulsory.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import compulsory.models.Continent;
import compulsory.utils.EntityManagementController;

public class ContinentRepository {
	private static EntityManager em = EntityManagementController.emf.createEntityManager();
	
	private ContinentRepository() {}
	
	/**
	 * Adds the specified continent to the database
	 * @param continent
	 * @return whether the operation has been executed or not.
	 */
	public static synchronized boolean addContinent(Continent continent) {
		if(ContinentRepository.getByName(continent.getName()).size() == 0) {
			em.getTransaction().begin();
			em.persist(continent);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	/**
	 * Returns continent object with given id.
	 * @param id
	 * @return the continent or null, if not found.
	 */
	public static synchronized Continent getById(Integer id) {
		Continent c = em.find(Continent.class, id);
		return c;
	}
	
	/**
	 * Returns list of continents with given name.
	 * @param name
	 * @return the specified list.
	 */
	@SuppressWarnings("unchecked")
	public static synchronized List<Continent> getByName(String name) {
		return em.createNamedQuery("Continent.findByName").setParameter("name", name).getResultList();
	}
	
	/**
	 * Returns all continents in the db.
	 * @return list with the continents.
	 */
	@SuppressWarnings("unchecked")
	public static synchronized List<Continent> getAll() {
		return em.createNamedQuery("Continent.findAll").getResultList();
	}
}
