package homework.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import homework.models.Continent;
import homework.models.Country;
import lombok.extern.java.Log;

@Log
public class CountryRepository {
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private CountryRepository() {}
	
	/**
	 * Initializes the connection to this
	 * @param connection
	 * @return true if it has been initialized, otherwise false
	 */
	public static boolean setConnection(Connection connection) {
		CountryRepository.connection = connection;
		
		try {
			CountryRepository.statement = CountryRepository.connection.createStatement();
		} catch (SQLException e) {
			log.severe("Cannot initiate PSQL statement");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Adds the specified country to the database
	 * @param country
	 * @return whether the operation has been executed or not.
	 */
	public static boolean addCountry(Country country) {
		try {
			preparedStatement = CountryRepository.connection.prepareStatement("insert into countries (name, code, continent) values (?, ?, ?)");
			preparedStatement.setString(1, country.getName());
			preparedStatement.setString(2, country.getCode());

			List<Continent> conts = ContinentRepository.getByName(country.getContinent());
			
			if(conts.size() == 0) {
				log.warning("Invalid continent");
				return false;
			}
			
			preparedStatement.setInt(3, conts.get(0).getId());
			
			return (preparedStatement.executeUpdate() != 0);
		} catch (SQLException e) {
			log.warning("Could not insert into countries");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returns country object with given id.
	 * @param id
	 * @return the country or null, if not found.
	 */
	public static Country getById(Integer id) {
		Country c = null;
		try {
			resultSet = CountryRepository.statement.executeQuery("select * from countries where id=" + id);
			resultSet.next();
			
			ResultSet rs = CountryRepository.statement.executeQuery("select name from continents where id=" + resultSet.getInt("continent"));
			rs.next();
			
			c = new Country(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("code"), rs.getString("name"));
		} catch (SQLException e) {
			log.warning("Could not retrieve specified country");
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * Returns list of countries with given name.
	 * @param name
	 * @return the specified list.
	 */
	public static List<Country> getByName(String name) {
		name = name.trim();
		List<Country> countries = new LinkedList<>();
		
		try {
			preparedStatement = CountryRepository.connection.prepareStatement("select * from countries where name=?");
			preparedStatement.setString(1, name);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ResultSet rs = CountryRepository.statement.executeQuery("select name from continents where id=" + resultSet.getInt("continent"));
				rs.next();
				
				Country c = new Country(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("code"), rs.getString("name"));
				countries.add(c);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified countr(y/ies)");
			e.printStackTrace();
		}
		return countries;
	}
}
