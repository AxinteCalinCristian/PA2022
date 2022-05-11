package bonus.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import bonus.utils.ApplicationProperties;
import lombok.extern.java.Log;

@Log
public class JDBCDatabasePool {
	private static final ApplicationProperties appConfig = ApplicationProperties.getInstance();
	private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds = null;
    
    static {
    	try {
			config.setJdbcUrl(appConfig.getProperty("jdbc_database_url"));
	        config.setUsername(appConfig.getProperty("jdbc_database_user"));
	        config.setPassword(appConfig.getProperty("jdbc_database_pass"));
	        config.addDataSourceProperty("cachePrepStmts" , "true");
	        config.addDataSourceProperty("prepStmtCacheSize" , "250");
	        config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048");
	        ds = new HikariDataSource(config);
		} 
		catch(Exception e) {
			log.severe("Error initializing DB connection");
			e.printStackTrace();
		}
    }
    
    public static Connection getConnection() {
        try {
        	if(ds == null) {
        		log.severe("Connection pool is null.");
        		return null;
        	}
        	
			return ds.getConnection();
		} catch (SQLException e) {
			log.severe("Could not get connection to DB");
			e.printStackTrace();
			return null;
		}
    }
}
