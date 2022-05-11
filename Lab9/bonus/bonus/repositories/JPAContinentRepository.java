package bonus.repositories;

import java.util.List;

import bonus.models.Continent;

public class JPAContinentRepository extends JPAAbstractRepository<Continent> {
	private static JPAContinentRepository instance = null;
	
	public static JPAContinentRepository getInstance() {
        if (instance == null)
        	instance = new JPAContinentRepository();
 
        return instance;
    }
	
	private JPAContinentRepository() {}
	
	/**
	 * Adds the specified continent to the database
	 * @param continent
	 * @return whether the operation has been executed or not.
	 */
	public synchronized boolean addEntry(Continent continent) {
		if(this.getByName(continent.getName()).size() == 0) {
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
	public synchronized Continent getById(Integer id) {
		return getItemById(id);
	}
	
	/**
	 * Returns list of continents with given name.
	 * @param name
	 * @return the specified list.
	 */
	public synchronized List<Continent> getByName(String name) {
		return getItemByName("Continent.findByName", name);
	}
	
	/**
	 * Returns all continents in the db.
	 * @return list with the continents.
	 */
	public synchronized List<Continent> getAll() {
		return getAllItems("Continent.findAll");
	}
}
