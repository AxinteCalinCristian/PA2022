package bonus.repositories;

import java.util.List;

import bonus.models.City;

public class JPACityRepository extends JPAAbstractRepository<City> {
	private static JPACityRepository instance = null;
	
	public static JPACityRepository getInstance() {
        if (instance == null)
        	instance = new JPACityRepository();
 
        return instance;
    }
	
	private JPACityRepository() {}
	
	/**
	 * Adds the specified city to the database
	 * @param city
	 * @return whether the operation has been executed or not.
	 */
	public synchronized boolean addEntry(City city) {
		addItem(city);
		return true;
	}
	
	/**
	 * Returns city object with given id.
	 * @param id
	 * @return the city or null, if not found.
	 */
	public synchronized City getById(Integer id) {
		return getItemById(id);
	}
	
	/**
	 * Returns list of cities with given name.
	 * @param name
	 * @return the specified list.
	 */
	public synchronized List<City> getByName(String name) {
		return getItemByName("City.findByName", name);
	}
	
	/**
	 * Returns all cities in the db.
	 * @return list with the cities.
	 */
	public synchronized List<City> getAll() {
		return getAllItems("City.findAll");
	}
}
