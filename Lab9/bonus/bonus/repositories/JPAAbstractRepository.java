package bonus.repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import bonus.utils.EntityManagementController;

public abstract class JPAAbstractRepository<T> implements Repository<T> {
	private static EntityManager em = EntityManagementController.emf.createEntityManager();
	
	/**
	 * Adds the specified item to the database
	 * @param item
	 * @return whether the operation has been executed or not.
	 */
	public synchronized void addItem(T item) {
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
	}
	
	/**
	 * Returns item object with given id.
	 * @param id
	 * @return the item or null, if not found.
	 */
	@SuppressWarnings("unchecked")
	public synchronized T getItemById(Integer id) {
		Class<T> tClass = (Class<T>) ((ParameterizedType) ((Object) JPAAbstractRepository.class).getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		T item = em.find(tClass, id);
		return item;
	}
	
	/**
	 * Returns list of items with given name from the given query string.
	 * @param queryName
	 * @param name
	 * @return the specified list.
	 */
	public synchronized List<T> getItemByName(String queryName, String name) {
		return genericQuery(queryName, Map.of("name", name));
	}
	
	/**
	 * Returns all items from given query.
	 * @param queryName
	 * @return the specified list.
	 */
	@SuppressWarnings("unchecked")
	public synchronized List<T> getAllItems(String queryName) {
		return em.createNamedQuery(queryName).getResultList();
	}
	
	/**
	 * Executes a generic query with a given set of parameters
	 * @param queryName
	 * @param params
	 * @return the resulting list
	 */
	@SuppressWarnings("unchecked")
	public synchronized List<T> genericQuery(String queryName, Map<String, Object> params) {
		var query = em.createNamedQuery(queryName);
		
		for(Map.Entry<String, Object> entry : params.entrySet()) {
			query = query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}
}
