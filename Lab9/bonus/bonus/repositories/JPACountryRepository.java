package bonus.repositories;

import java.util.List;

import bonus.models.Country;

public class JPACountryRepository extends JPAAbstractRepository<Country> {
	private static JPACountryRepository instance = null;
	
	public static JPACountryRepository getInstance() {
        if (instance == null)
        	instance = new JPACountryRepository();
 
        return instance;
    }
	
	private JPACountryRepository() {}
	
	/**
	 * Adds the specified country to the database
	 * @param country
	 * @return whether the operation has been executed or not.
	 */
	public synchronized boolean addEntry(Country country) {
		if(this.getByName(country.getName()).size() == 0) { 
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
	public synchronized Country getById(Integer id) {
		return getItemById(id);
	}
	
	/**
	 * Returns list of countries with given name.
	 * @param name
	 * @return the specified list.
	 */
	public synchronized List<Country> getByName(String name) {
		return getItemByName("Country.findByName", name);
	}
	
	/**
	 * Returns all countries in the db.
	 * @return list with the countries.
	 */
	public synchronized List<Country> getAll() {
		return getAllItems("Country.findAll");
	}
}
