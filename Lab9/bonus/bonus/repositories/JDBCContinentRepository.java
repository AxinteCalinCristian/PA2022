package bonus.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import bonus.database.JDBCDatabasePool;
import bonus.models.Continent;
import lombok.extern.java.Log;

@Log
public class JDBCContinentRepository implements Repository<Continent> {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private static JDBCContinentRepository instance = null;
	
	public static JDBCContinentRepository getInstance() {
        if (instance == null)
        	instance = new JDBCContinentRepository();
 
        return instance;
    }
	
	private JDBCContinentRepository() {
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
	 * Adds the specified continent to the database
	 * @param continent
	 * @return whether the operation has been executed or not.
	 */
	@Override
	public boolean addEntry(Continent continent) {
		if(!assertConnectionNotNull()) {
			return false;
		}
		
		try {
			preparedStatement = this.connection.prepareStatement("insert into continents (name) values (?)");
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
	@Override
	public Continent getById(Integer id) {
		if(id == null) {
			 return null;
		}
		
		if(!assertConnectionNotNull()) {
			return null;
		}
		
		Continent cont = null;
		try {
			resultSet = this.statement.executeQuery("select * from continents where id=" + id);
			
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
	@Override
	public List<Continent> getByName(String name) {
		if(!assertConnectionNotNull()) {
			return new LinkedList<>();
		}
		
		name = name.trim();
		List<Continent> continents = new LinkedList<>();
		
		try {
			preparedStatement = this.connection.prepareStatement("select * from continents where name=?");
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

	/**
	 * Returns all continents in the db.
	 * @return list with the continents.
	 */
	@Override
	public List<Continent> getAll() {
		if(!assertConnectionNotNull()) {
			return new LinkedList<>();
		}
		
		List<Continent> continents = new LinkedList<>();
		
		try {
			preparedStatement = this.connection.prepareStatement("select * from continents");
			
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
