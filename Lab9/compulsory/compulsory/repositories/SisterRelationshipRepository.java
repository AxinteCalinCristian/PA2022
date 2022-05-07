package compulsory.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import compulsory.models.SisterRelationship;
import compulsory.utils.EntityManagementController;

public class SisterRelationshipRepository {
	private static EntityManager em = EntityManagementController.emf.createEntityManager();
	
	private SisterRelationshipRepository() {}
	
	/**
	 * Adds the specified sister relationship to the database
	 * @param sister relationship
	 * @return whether the operation has been executed or not.
	 */
	public static synchronized void addSisterRelationship(SisterRelationship sisterRelationship) {
		em.getTransaction().begin();
		em.persist(sisterRelationship);
		em.getTransaction().commit();
	}
	
	/**
	 * Returns sister relationship object with given id.
	 * @param id
	 * @return the sister relationship or null, if not found.
	 */
	public static synchronized SisterRelationship getById(Integer id) {
		SisterRelationship c = em.find(SisterRelationship.class, id);
		return c;
	}
	
	/**
	 * Returns all sister relationships in the db.
	 * @return list with the sister relationships.
	 */
	@SuppressWarnings("unchecked")
	public static synchronized List<SisterRelationship> getAll() {
		return em.createNamedQuery("SisterRelationship.findAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static synchronized List<SisterRelationship> findByPair(Integer cityA, Integer cityB) {
		return em.createNamedQuery("SisterRelationship.findByPair").setParameter("city_a", cityA).setParameter("city_b", cityB).getResultList();
	}
}