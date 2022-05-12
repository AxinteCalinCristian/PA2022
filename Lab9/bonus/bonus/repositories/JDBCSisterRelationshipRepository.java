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
import bonus.models.SisterRelationship;
import lombok.extern.java.Log;

@Log
public class JDBCSisterRelationshipRepository implements Repository<SisterRelationship> {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private static JDBCSisterRelationshipRepository instance = null;
	
	public static JDBCSisterRelationshipRepository getInstance() {
        if (instance == null)
        	instance = new JDBCSisterRelationshipRepository();
 
        return instance;
    }
	
	private JDBCSisterRelationshipRepository() {
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
	 * Adds the specified sister relationship to the database
	 * @param sister relationship
	 * @return whether the operation has been executed or not.
	 */
	@Override
	public boolean addEntry(SisterRelationship sisterRelationship) {
		if(!assertConnectionNotNull()) {
			return false;
		}
		
		try {
			Integer id = sisterRelationship.getId();
			if(id == null || id < 0) {
				resultSet = this.statement.executeQuery("select max(id) as max_id from sister_cities");
				resultSet.next();
				id = resultSet.getInt("max_id");
				id++;
			}
			preparedStatement = this.connection.prepareStatement("insert into sister_cities (id, first_city, second_city) values (?, ?, ?)");
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, sisterRelationship.getFirstCity().getId());
			preparedStatement.setInt(3, sisterRelationship.getSecondCity().getId());
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
	@Override
	public SisterRelationship getById(Integer id) {
		if(id == null) {
			 return null;
		}
		
		if(!assertConnectionNotNull()) {
			return null;
		}
		
		SisterRelationship c = null;
		try {
			resultSet = this.statement.executeQuery("select * from sister_cities where id=" + id);
			resultSet.next();
			
			City a = JDBCCityRepository.getInstance().getById(resultSet.getInt("first_city"));
			City b = JDBCCityRepository.getInstance().getById(resultSet.getInt("second_city"));
			
			c = new SisterRelationship(resultSet.getInt("id"), a, b);
		} catch (SQLException e) {
			log.warning("Could not retrieve specified sister relationship");
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<SisterRelationship> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns all cities in the db.
	 * @return list with the sister relationships.
	 */
	@Override
	public List<SisterRelationship> getAll() {
		if(!assertConnectionNotNull()) {
			return new LinkedList<>();
		}
		
		List<SisterRelationship> srs = new LinkedList<>();
		
		try {
			preparedStatement = this.connection.prepareStatement("select * from sister_cities");
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				City a = JDBCCityRepository.getInstance().getById(resultSet.getInt("first_city"));
				City b = JDBCCityRepository.getInstance().getById(resultSet.getInt("second_city"));
				
				SisterRelationship c = new SisterRelationship(resultSet.getInt("id"), a, b);

				srs.add(c);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified sister relationship(s)");
			e.printStackTrace();
		}
		
		return srs;
	}
}
