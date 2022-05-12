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
import bonus.models.Country;
import lombok.extern.java.Log;

@Log
public class JDBCCountryRepository implements Repository<Country> {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private static JDBCCountryRepository instance = null;
	
	public static JDBCCountryRepository getInstance() {
        if (instance == null)
        	instance = new JDBCCountryRepository();
 
        return instance;
    }
	
	private JDBCCountryRepository() {
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
	 * Adds the specified country to the database
	 * @param country
	 * @return whether the operation has been executed or not.
	 */
	@Override
	public boolean addEntry(Country country) {
		if(!assertConnectionNotNull()) {
			return false;
		}
		
		try {
			Integer id = country.getId();
			if(id == null || id < 0) {
				resultSet = this.statement.executeQuery("select max(id) as max_id from countries");
				resultSet.next();
				id = resultSet.getInt("max_id");
				id++;
			}
			
			preparedStatement = this.connection.prepareStatement("insert into countries (id, name, code, continent) values (?, ?, ?, ?)");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, country.getName());
			preparedStatement.setString(3, country.getCode());
			preparedStatement.setInt(4, country.getContinent().getId());

			List<Continent> conts = JDBCContinentRepository.getInstance().getByName(country.getContinent().getName());
			
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
	@Override
	public Country getById(Integer id) {
		if(id == null) {
			 return null;
		}
		
		if(!assertConnectionNotNull()) {
			return null;
		}
		
		Country c = null;
		try {
			resultSet = this.statement.executeQuery("select * from countries where id=" + id);
			resultSet.next();
			
			Continent cont = JDBCContinentRepository.getInstance().getById(resultSet.getInt("continent"));
			c = new Country(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("code"), cont);
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
	@Override
	public List<Country> getByName(String name) {
		if(!assertConnectionNotNull()) {
			return new LinkedList<>();
		}
		
		name = name.trim();
		List<Country> countries = new LinkedList<>();
		
		try {
			preparedStatement = this.connection.prepareStatement("select * from countries where name=?");
			preparedStatement.setString(1, name);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Continent cont = JDBCContinentRepository.getInstance().getById(resultSet.getInt("continent"));
				Country c = new Country(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("code"), cont);
				countries.add(c);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified countr(y/ies)");
			e.printStackTrace();
		}
		return countries;
	}

	@Override
	public List<Country> getAll() {
		if(!assertConnectionNotNull()) {
			return new LinkedList<>();
		}
		
		List<Country> countries = new LinkedList<>();
		
		try {
			preparedStatement = this.connection.prepareStatement("select * from countries");
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Continent cont = JDBCContinentRepository.getInstance().getById(resultSet.getInt("continent"));
				Country c = new Country(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("code"), cont);
				countries.add(c);
			}
			
		} catch (SQLException e) {
			log.warning("Could not retrieve specified countr(y/ies)");
			e.printStackTrace();
		}
		return countries;
	}
}
