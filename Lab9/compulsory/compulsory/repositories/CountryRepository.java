package compulsory.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import compulsory.models.Country;
import compulsory.utils.EntityManagementController;

public class CountryRepository {
	private static EntityManager em = EntityManagementController.emf.createEntityManager();
	
	private CountryRepository() {}
	
	/**
	 * Adds the specified country to the database
	 * @param country
	 * @return whether the operation has been executed or not.
	 */
	public static synchronized boolean addCountry(Country country) {
		if(CountryRepository.getByName(country.getName()).size() == 0) { 
			em.getTransaction().begin();
			em.persist(country);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	/**
	 * Returns country object with given id.
	 * @param id
	 * @return the country or null, if not found.
	 */
	public static synchronized Country getById(Integer id) {
		Country c = em.find(Country.class, id);
		return c;
	}
	
	/**
	 * Returns list of countries with given name.
	 * @param name
	 * @return the specified list.
	 */
	@SuppressWarnings("unchecked")
	public static synchronized List<Country> getByName(String name) {
		return em.createNamedQuery("Country.findByName").setParameter("name", name).getResultList();
	}
	
	/**
	 * Returns all countries in the db.
	 * @return list with the countries.
	 */
	@SuppressWarnings("unchecked")
	public static synchronized List<Country> getAll() {
		return em.createNamedQuery("Country.findAll").getResultList();
	}
}
