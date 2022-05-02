package homework.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import homework.models.City;
import homework.models.Continent;
import homework.models.Country;
import homework.repositories.CityRepository;
import homework.repositories.ContinentRepository;
import homework.repositories.CountryRepository;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.java.Log;

/**
 * Represents the database connection.
 * @author Calin Axinte
 */
@Log
public class Database {
	private static final Dotenv dotenv = Dotenv.load();
	private static Connection connection = null;
	private static boolean isConnOpen = false;
	
	private Database() {}
	
	public static boolean getIsConnectionOpen() {
		return isConnOpen;
	}
	
	/**
	 * Initializes the database connection
	 * @return true if the connection is made, otherwise false
	 */
	public static boolean initConnection() {
		try {
			connection = DriverManager.getConnection(dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_USER"), dotenv.get("DATABASE_PASS"));
			ContinentRepository.setConnection(connection);
			CountryRepository.setConnection(connection);
			CityRepository.setConnection(connection);
			isConnOpen = true;
		} 
		catch(Exception e) {
			log.severe("Error initializing DB connection");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Closes the database connection
	 * @return true if the connection is terminated, otherwise false
	 */
	public static boolean closeConnection() {
		try {
			connection.close();
			isConnOpen = false;
		} catch (SQLException e) {
			log.severe("Error closing DB connection");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Adds the country to the database.
	 * @param country
	 * @return whether the operation has been executed or not.
	 */
	public static boolean addCountry(Country country) {
		return CountryRepository.addCountry(country);
	}
	
	/**
	 * Returns the specified country.
	 * @param id
	 * @return country or null.
	 */
	public static Country getCountry(Integer id) {
		return CountryRepository.getById(id);
	}
	
	/**
	 * Returns the specified country.
	 * @param name
	 * @return country or null.
	 */
	public static List<Country> getCountry(String name) {
		return CountryRepository.getByName(name);
	}
	
	/**
	 * Adds the continent to the database.
	 * @param continent
	 * @return whether the operation has been executed or not.
	 */
	public static boolean addContinent(Continent continent) {
		return ContinentRepository.addContinent(continent);
	}
	
	/**
	 * Returns the specified continent.
	 * @param id
	 * @return continent or null.
	 */
	public static Continent getContinent(Integer id) {
		return ContinentRepository.getById(id);
	}
	
	/**
	 * Returns the specified continent.
	 * @param name
	 * @return continent or null.
	 */
	public static List<Continent> getContinent(String name) {
		return ContinentRepository.getByName(name);
	}
	
	/**
	 * Adds the city to the database.
	 * @param city
	 * @return whether the operation has been executed or not.
	 */
	public static boolean addCity(City city) {
		return CityRepository.addCity(city);
	}
	
	/**
	 * Returns the specified city.
	 * @param id
	 * @return city or null.
	 */
	public static City getCity(Integer id) {
		return CityRepository.getById(id);
	}
	
	/**
	 * Returns the specified city.
	 * @param name
	 * @return city or null.
	 */
	public static List<City> getCity(String name) {
		return CityRepository.getByName(name);
	}
}
