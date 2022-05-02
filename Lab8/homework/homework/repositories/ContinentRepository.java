package homework.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import homework.models.Continent;
import lombok.extern.java.Log;

@Log
public class ContinentRepository {
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private ContinentRepository() {}
	
	/**
	 * Initializes the connection to this
	 * @param connection
	 * @return true if it has been initialized, otherwise false
	 */
	public static boolean setConnection(Connection connection) {
		ContinentRepository.connection = connection;
		
		try {
			ContinentRepository.statement = ContinentRepository.connection.createStatement();
		} catch (SQLException e) {
			log.severe("Cannot initiate PSQL statement");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Adds the specified continent to the database
	 * @param continent
	 * @return whether the operation has been executed or not.
	 */
	public static boolean addContinent(Continent continent) {
		try {
			preparedStatement = ContinentRepository.connection.prepareStatement("insert into continents (name) values (?)");
			preparedStatement.setString(1, continent.getName());
			
			return (preparedStatement.executeUpdate() != 0);
		} catch (SQLException e) {
			log.warning("Could not insert into continents");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returns continent object with given id.
	 * @param id
	 * @return the continent or null, if not found.
	 */
	public static Continent getById(Integer id) {
		Continent cont = null;
		try {
			resultSet = ContinentRepository.statement.executeQuery("select * from continents where id=" + id);
			
			resultSet.next();
			cont = new Continent(resultSet.getInt("id"), resultSet.getString("name"));
		} catch (SQLException e) {
			log.warning("Could not retrieve specified continent");
			e.printStackTrace();
		}
		return cont;
	}
	
	/**
	 * Returns list of continents with given name.
	 * @param name
	 * @return the specified list.
	 */
	public static List<Continent> getByName(String name) {
		name = name.trim();
		List<Continent> continents = new LinkedList<>();
		
		try {
			preparedStatement = ContinentRepository.connection.prepareStatement("select * from continents where name=?");
			preparedStatement.setString(1, name);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Continent cont = new Continent(resultSet.getInt("id"), resultSet.getString("name"));
				continents.add(cont);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified continent(s)");
			e.printStackTrace();
		}
		return continents;
	}
}
