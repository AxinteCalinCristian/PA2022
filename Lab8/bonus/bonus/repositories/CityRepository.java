package bonus.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bonus.models.City;
import bonus.models.Country;
import lombok.extern.java.Log;

@Log
public class CityRepository {
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private CityRepository() {}
	
	/**
	 * Initializes the connection to this
	 * @param connection
	 * @return true if it has been initialized, otherwise false
	 */
	public static boolean setConnection(Connection connection) {
		CityRepository.connection = connection;
		
		try {
			CityRepository.statement = CityRepository.connection.createStatement();
		} catch (SQLException e) {
			log.severe("Cannot initiate PSQL statement");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Adds the specified city to the database
	 * @param city
	 * @return whether the operation has been executed or not.
	 */
	public static boolean addCity(City city) {
		try {
			preparedStatement = CityRepository.connection.prepareStatement("insert into cities (name, country, capital, latitude, longitude) values (?, ?, ?, ?, ?)");
			List<Country> countries = CountryRepository.getByName(city.getCountry());
			if(countries.size() == 0) {
				log.warning("Invalid country");
				return false;
			}
			
			preparedStatement.setString(1, city.getName());
			preparedStatement.setInt(2, countries.get(0).getId());
			preparedStatement.setBoolean(3, city.getCapital());
			preparedStatement.setString(4, city.getLatitude());
			preparedStatement.setString(5, city.getLongitude());
			
			return (preparedStatement.executeUpdate() != 0);
		} catch (SQLException e) {
			log.warning("Could not insert into cities");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returns city object with given id.
	 * @param id
	 * @return the city or null, if not found.
	 */
	public static City getById(Integer id) {
		City c = null;
		try {
			resultSet = CityRepository.statement.executeQuery("select * from continents where id=" + id);
			resultSet.next();
			
			ResultSet rs = CityRepository.statement.executeQuery("select name from countries where id=" + resultSet.getInt("country"));
			rs.next();
			
			c = new City(resultSet.getInt("id"), resultSet.getString("name"), rs.getString("name"),
					resultSet.getBoolean("capital"), resultSet.getString("latitude"), resultSet.getString("longitude"));
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified city");
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * Returns list of cities with given name.
	 * @param name
	 * @return the specified list.
	 */
	public static List<City> getByName(String name) {
		name = name.trim();
		List<City> cities = new LinkedList<>();
		
		try {
			preparedStatement = CityRepository.connection.prepareStatement("select * from cities where name=?");
			preparedStatement.setString(1, name);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ResultSet rs = CityRepository.statement.executeQuery("select name from countries where id=" + resultSet.getInt("country"));
				rs.next();
				
				City c = new City(resultSet.getInt("id"), resultSet.getString("name"), rs.getString("name"),
						resultSet.getBoolean("capital"), resultSet.getString("latitude"), resultSet.getString("longitude"));

				cities.add(c);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified cit(y/ies)");
			e.printStackTrace();
		}
		return cities;
	}
	
	/**
	 * Returns all cities in the db.
	 * @return list with the cities.
	 */
	public static List<City> getAll() {
		List<City> cities = new LinkedList<>();
		
		try {
			preparedStatement = CityRepository.connection.prepareStatement("select * from cities");
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ResultSet rs = CityRepository.statement.executeQuery("select name from countries where id=" + resultSet.getInt("country"));
				rs.next();
				
				City c = new City(resultSet.getInt("id"), resultSet.getString("name"), rs.getString("name"),
						resultSet.getBoolean("capital"), resultSet.getString("latitude"), resultSet.getString("longitude"));

				cities.add(c);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified cit(y/ies)");
			e.printStackTrace();
		}
		
		return cities;
	}
}
