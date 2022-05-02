package compulsory.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import compulsory.models.Continent;
import compulsory.models.Country;
import compulsory.repositories.ContinentRepository;
import compulsory.repositories.CountryRepository;
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
	
	private Database() {}
	
	/**
	 * Initializes the database connection
	 * @return true if the connection is made, otherwise false
	 */
	public static boolean initConnection() {
		try {
			connection = DriverManager.getConnection(dotenv.get("DATABASE_URL"), dotenv.get("DATABASE_USER"), dotenv.get("DATABASE_PASS"));
			CountryRepository.setConnection(connection);
			ContinentRepository.setConnection(connection);
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
}
