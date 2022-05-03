package bonus.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bonus.models.SisterRelationship;
import lombok.extern.java.Log;


@Log
public class SisterRelationshipRepository {
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private SisterRelationshipRepository() {}
	
	/**
	 * Initializes the connection to this
	 * @param connection
	 * @return true if it has been initialized, otherwise false
	 */
	public static boolean setConnection(Connection connection) {
		SisterRelationshipRepository.connection = connection;
		
		try {
			SisterRelationshipRepository.statement = SisterRelationshipRepository.connection.createStatement();
		} catch (SQLException e) {
			log.severe("Cannot initiate PSQL statement");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Adds the specified sister relationship to the database
	 * @param sister relationship
	 * @return whether the operation has been executed or not.
	 */
	public static boolean addSisterRelationship(SisterRelationship sisterRelationship) {
		try {
			preparedStatement = SisterRelationshipRepository.connection.prepareStatement("insert into sister_cities (first_city, second_city) values (?, ?)");
			preparedStatement.setInt(1, sisterRelationship.getFirstCity());
			preparedStatement.setInt(2, sisterRelationship.getSecondCity());
			return (preparedStatement.executeUpdate() != 0);
		} catch (SQLException e) {
			log.warning("Could not insert into sister relationships");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returns sister relationship object with given id.
	 * @param id
	 * @return the sister relationship or null, if not found.
	 */
	public static SisterRelationship getById(Integer id) {
		SisterRelationship c = null;
		try {
			resultSet = SisterRelationshipRepository.statement.executeQuery("select * from sister_cities where id=" + id);
			resultSet.next();
			c = new SisterRelationship(resultSet.getInt("id"), resultSet.getInt("first_city"), resultSet.getInt("second_city"));
		} catch (SQLException e) {
			log.warning("Could not retrieve specified sister relationship");
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * Returns all cities in the db.
	 * @return list with the sister relationships.
	 */
	public static List<SisterRelationship> getAll() {
		List<SisterRelationship> srs = new LinkedList<>();
		
		try {
			preparedStatement = SisterRelationshipRepository.connection.prepareStatement("select * from sister_cities");
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				SisterRelationship c = new SisterRelationship(resultSet.getInt("id"), resultSet.getInt("first_city"), resultSet.getInt("second_city"));

				srs.add(c);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified sister relationship(s)");
			e.printStackTrace();
		}
		
		return srs;
	}
}