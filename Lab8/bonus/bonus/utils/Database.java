package bonus.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import bonus.models.City;
import bonus.models.Continent;
import bonus.models.Country;
import bonus.repositories.CityRepository;
import bonus.repositories.ContinentRepository;
import bonus.repositories.CountryRepository;
import bonus.repositories.SisterRelationshipRepository;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.java.Log;

/**
 * Represents the database connection.
 * @author Calin Axinte
 */
@Log
public class Database {
	private static final Dotenv dotenv = Dotenv.load();
	private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    
	private Database() {}
	
	/**
	 * Initializes the database connection
	 * @return true if the connection is made, otherwise false
	 */
	public static boolean initDB() {
		try {
			config.setJdbcUrl(dotenv.get("DATABASE_URL"));
	        config.setUsername(dotenv.get("DATABASE_USER"));
	        config.setPassword(dotenv.get("DATABASE_PASS"));
	        config.addDataSourceProperty("cachePrepStmts" , "true");
	        config.addDataSourceProperty("prepStmtCacheSize" , "250");
	        config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");
	        ds = new HikariDataSource(config);
	        
	        ContinentRepository.setConnection(getConnection());
	        CountryRepository.setConnection(getConnection());
	        CityRepository.setConnection(getConnection());
	        SisterRelationshipRepository.setConnection(getConnection());
		} 
		catch(Exception e) {
			log.severe("Error initializing DB connection");
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
	
	public static Connection getConnection() {
        try {
			return ds.getConnection();
		} catch (SQLException e) {
			log.severe("Could not get connection to DB");
			e.printStackTrace();
			return null;
		}
    }
}
