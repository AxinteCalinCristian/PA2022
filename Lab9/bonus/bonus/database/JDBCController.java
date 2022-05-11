package bonus.database;

import bonus.models.City;
import bonus.models.Continent;
import bonus.models.Country;
import bonus.models.SisterRelationship;
import bonus.repositories.JDBCCityRepository;
import bonus.repositories.JDBCContinentRepository;
import bonus.repositories.JDBCCountryRepository;
import bonus.repositories.JDBCSisterRelationshipRepository;
import bonus.repositories.Repository;

public class JDBCController implements DatabaseController {
	private static JDBCController instance = null;
	
	public static JDBCController getInstance() {
        if (instance == null)
        	instance = new JDBCController();
 
        return instance;
    }
	
	private JDBCController() {}

	@Override
	public Repository<City> getCityRepository() {
		return JDBCCityRepository.getInstance();
	}

	@Override
	public Repository<Country> getCountryRepository() {
		return JDBCCountryRepository.getInstance();
	}

	@Override
	public Repository<Continent> getContinentRepository() {
		return JDBCContinentRepository.getInstance();
	}

	@Override
	public Repository<SisterRelationship> getSisterRelationshipRepository() {
		return JDBCSisterRelationshipRepository.getInstance();
	}
}
