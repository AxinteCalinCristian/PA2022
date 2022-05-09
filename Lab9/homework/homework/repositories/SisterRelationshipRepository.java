package homework.repositories;

import java.util.List;
import java.util.Map;

import homework.models.SisterRelationship;

public class SisterRelationshipRepository extends AbstractRepository<SisterRelationship> {
	private SisterRelationshipRepository() {}
	
	/**
	 * Adds the specified sister relationship to the database
	 * @param sister relationship
	 * @return whether the operation has been executed or not.
	 */
	public static synchronized void addSisterRelationship(SisterRelationship sisterRelationship) {
		addItem(sisterRelationship);
	}
	
	/**
	 * Returns sister relationship object with given id.
	 * @param id
	 * @return the sister relationship or null, if not found.
	 */
	public static synchronized SisterRelationship getById(Integer id) {
		return getItemById(id);
	}
	
	/**
	 * Returns all sister relationships in the db.
	 * @return list with the sister relationships.
	 */
	public static synchronized List<SisterRelationship> getAll() {
		return getAllItems("SisterRelationship.findAll");
	}
	
	/**
	 * Returns all sister relationships for given pair
	 * @param cityA
	 * @param cityB
	 * @return the specified list
	 */
	public static synchronized List<SisterRelationship> findByPair(Integer cityA, Integer cityB) {
		return genericQuery("SisterRelationship.findByPair", Map.of("city_a", cityA, "city_b", cityB));
	}
}