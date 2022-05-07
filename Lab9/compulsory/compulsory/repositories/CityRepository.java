package compulsory.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import compulsory.models.City;
import compulsory.utils.EntityManagementController;

public class CityRepository {
	private static EntityManager em = EntityManagementController.emf.createEntityManager();
	
	private CityRepository() {}
	
	/**
	 * Adds the specified city to the database
	 * @param city
	 * @return whether the operation has been executed or not.
	 */
	public static synchronized void addCity(City city) {
		em.getTransaction().begin();
		em.persist(city);
		em.getTransaction().commit();
	}
	
	/**
	 * Returns city object with given id.
	 * @param id
	 * @return the city or null, if not found.
	 */
	public static synchronized City getById(Integer id) {
		City c = em.find(City.class, id);
		return c;
	}
	
	/**
	 * Returns list of cities with given name.
	 * @param name
	 * @return the specified list.
	 */
	@SuppressWarnings("unchecked")
	public static synchronized List<City> getByName(String name) {
		return em.createNamedQuery("City.findByName").setParameter("name", name).getResultList();
	}
	
	/**
	 * Returns all cities in the db.
	 * @return list with the cities.
	 */
	@SuppressWarnings("unchecked")
	public static synchronized List<City> getAll() {
		return em.createNamedQuery("City.findAll").getResultList();
	}
}
