package homework.repositories;

import java.util.List;

import homework.models.Continent;

public class ContinentRepository extends AbstractRepository<Continent> {
	private ContinentRepository() {}
	
	/**
	 * Adds the specified continent to the database
	 * @param continent
	 * @return whether the operation has been executed or not.
	 */
	public static synchronized boolean addContinent(Continent continent) {
		if(ContinentRepository.getByName(continent.getName()).size() == 0) {
			addItem(continent);
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
		return getItemById(id);
	}
	
	/**
	 * Returns list of continents with given name.
	 * @param name
	 * @return the specified list.
	 */
	public static synchronized List<Continent> getByName(String name) {
		return getItemByName("Continent.findByName", name);
	}
	
	/**
	 * Returns all continents in the db.
	 * @return list with the continents.
	 */
	public static synchronized List<Continent> getAll() {
		return getAllItems("Continent.findAll");
	}
}
