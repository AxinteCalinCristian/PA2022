package bonus.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bonus.database.JDBCDatabasePool;
import bonus.models.City;
import bonus.models.Country;
import lombok.extern.java.Log;

@Log
public class JDBCCityRepository implements Repository<City>{
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private static JDBCCityRepository instance = null;
	
	public static JDBCCityRepository getInstance() {
        if (instance == null)
        	instance = new JDBCCityRepository();
 
        return instance;
    }
	
	private JDBCCityRepository() {
		connection = JDBCDatabasePool.getConnection();
	}
	
	private boolean assertConnectionNotNull() {
		if(connection == null) {
			return false;
		}
		if(statement == null) {
			try {
				this.statement = this.connection.createStatement();
			} catch (SQLException e) {
				log.severe("Cannot initiate PSQL statement");
				e.printStackTrace();
			}
		}
		return true;
	}
	/**
	 * Adds the specified city to the database
	 * @param city
	 * @return whether the operation has been executed or not.
	 */
	@Override
	public boolean addEntry(City city) {
		if(!assertConnectionNotNull()) {
			return false;
		}
		
		try {
			Integer id = city.getId();
			if(id == null || id < 0) {
				resultSet = this.statement.executeQuery("select max(id) as max_id from cities");
				resultSet.next();
				id = resultSet.getInt("max_id");
				id++;
			}
			preparedStatement = this.connection.prepareStatement("insert into cities (id, name, country, capital, latitude, longitude, population) values (?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, city.getName());
			preparedStatement.setInt(3, city.getCountry().getId());
			preparedStatement.setBoolean(4, city.getCapital());
			preparedStatement.setString(5, city.getLatitude());
			preparedStatement.setString(6, city.getLongitude());
			preparedStatement.setInt(7, city.getPopulation());
			
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
	@Override
	public City getById(Integer id) {
		if(id == null) {
			 return null;
		}
		
		if(!assertConnectionNotNull()) {
			return null;
		}
		
		City c = null;
		try {
			resultSet = this.statement.executeQuery("select * from cities where id=" + id);
			resultSet.next();
			
			Country count = JDBCCountryRepository.getInstance().getById(resultSet.getInt("country"));
			c = new City(resultSet.getInt("id"), resultSet.getString("name"), count, 
					resultSet.getBoolean("capital"), resultSet.getString("latitude"), resultSet.getString("longitude"), resultSet.getInt("population"));
			
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
	@Override
	public List<City> getByName(String name) {
		if(!assertConnectionNotNull()) {
			return new LinkedList<>();
		}
		
		name = name.trim();
		List<City> cities = new LinkedList<>();
		
		try {
			preparedStatement = this.connection.prepareStatement("select * from cities where name=?");
			preparedStatement.setString(1, name);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {				
				Country count = JDBCCountryRepository.getInstance().getById(resultSet.getInt("country"));
				City c = new City(resultSet.getInt("id"), resultSet.getString("name"), count, 
						resultSet.getBoolean("capital"), resultSet.getString("latitude"), resultSet.getString("longitude"), resultSet.getInt("population"));

				cities.add(c);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified cit(y/ies)");
			e.printStackTrace();
		}
		return cities;
	}

	@Override
	public List<City> getAll() {
		if(!assertConnectionNotNull()) {
			return new LinkedList<>();
		}
		
		List<City> cities = new LinkedList<>();
		
		try {
			preparedStatement = this.connection.prepareStatement("select * from cities");
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Country count = JDBCCountryRepository.getInstance().getById(resultSet.getInt("country"));
				City c = new City(resultSet.getInt("id"), resultSet.getString("name"), count, 
						resultSet.getBoolean("capital"), resultSet.getString("latitude"), resultSet.getString("longitude"), resultSet.getInt("population"));

				cities.add(c);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified cit(y/ies)");
			e.printStackTrace();
		}
		return cities;
	}

}
