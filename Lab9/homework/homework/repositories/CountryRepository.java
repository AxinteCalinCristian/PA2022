package homework.repositories;

import java.util.List;

import homework.models.Country;

public class CountryRepository extends AbstractRepository<Country> {
	private CountryRepository() {}
	
	/**
	 * Adds the specified country to the database
	 * @param country
	 * @return whether the operation has been executed or not.
	 */
	public static synchronized boolean addCountry(Country country) {
		if(CountryRepository.getByName(country.getName()).size() == 0) { 
			addItem(country);
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
		return getItemById(id);
	}
	
	/**
	 * Returns list of countries with given name.
	 * @param name
	 * @return the specified list.
	 */
	public static synchronized List<Country> getByName(String name) {
		return getItemByName("Country.findByName", name);
	}
	
	/**
	 * Returns all countries in the db.
	 * @return list with the countries.
	 */
	public static synchronized List<Country> getAll() {
		return getAllItems("Country.findAll");
	}
}
