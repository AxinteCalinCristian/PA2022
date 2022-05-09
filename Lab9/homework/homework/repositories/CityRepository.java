package homework.repositories;

import java.util.List;

import homework.models.City;

public class CityRepository extends AbstractRepository<City> {
	private CityRepository() {}
	
	/**
	 * Adds the specified city to the database
	 * @param city
	 * @return whether the operation has been executed or not.
	 */
	public static synchronized void addCity(City city) {
		addItem(city);
	}
	
	/**
	 * Returns city object with given id.
	 * @param id
	 * @return the city or null, if not found.
	 */
	public static synchronized City getById(Integer id) {
		return getItemById(id);
	}
	
	/**
	 * Returns list of cities with given name.
	 * @param name
	 * @return the specified list.
	 */
	public static synchronized List<City> getByName(String name) {
		return getItemByName("City.findByName", name);
	}
	
	/**
	 * Returns all cities in the db.
	 * @return list with the cities.
	 */
	public static synchronized List<City> getAll() {
		return getAllItems("City.findAll");
	}
}
