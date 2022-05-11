package bonus.database;

import bonus.utils.ApplicationProperties;

public abstract class Database {
	private static final ApplicationProperties appConfig = ApplicationProperties.getInstance();
	
	public static DatabaseController getDatabaseController() {
		String impl_type = getDesiredConfig();
		
		if(impl_type.equals("jpa")) {
			return JPAController.getInstance();
		}
		
		else if(impl_type.equals("jdbc")) {
			return JDBCController.getInstance();
		}
		
		return null;
	}
	
	private static String getDesiredConfig() {
		String impl_type = "jpa";
		
		if(appConfig.containsProperty("db_implementation_type")) {
			impl_type = appConfig.getProperty("db_implementation_type");
		}	
		
		return impl_type;
	}
}
