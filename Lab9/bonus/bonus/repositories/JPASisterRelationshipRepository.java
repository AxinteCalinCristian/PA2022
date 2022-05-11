package bonus.repositories;

import java.util.List;
import java.util.Map;

import bonus.models.SisterRelationship;

public class JPASisterRelationshipRepository extends JPAAbstractRepository<SisterRelationship> {
	private static JPASisterRelationshipRepository instance = null;
	
	public static JPASisterRelationshipRepository getInstance() {
        if (instance == null)
        	instance = new JPASisterRelationshipRepository();
 
        return instance;
    }
	
	private JPASisterRelationshipRepository() {}
	
	/**
	 * Adds the specified sister relationship to the database
	 * @param sister relationship
	 * @return whether the operation has been executed or not.
	 */
	public synchronized boolean addEntry(SisterRelationship sisterRelationship) {
		addItem(sisterRelationship);
		return true;
	}
	
	/**
	 * Returns sister relationship object with given id.
	 * @param id
	 * @return the sister relationship or null, if not found.
	 */
	public synchronized SisterRelationship getById(Integer id) {
		return getItemById(id);
	}
	
	/**
	 * Returns all sister relationships in the db.
	 * @return list with the sister relationships.
	 */
	public synchronized List<SisterRelationship> getAll() {
		return getAllItems("SisterRelationship.findAll");
	}
	
	/**
	 * Returns all sister relationships for given pair
	 * @param cityA
	 * @param cityB
	 * @return the specified list
	 */
	public synchronized List<SisterRelationship> findByPair(Integer cityA, Integer cityB) {
		return genericQuery("SisterRelationship.findByPair", Map.of("city_a", cityA, "city_b", cityB));
	}

	@Override
	public List<SisterRelationship> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}