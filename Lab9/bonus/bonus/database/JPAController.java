package bonus.database;

import bonus.models.City;
import bonus.models.Continent;
import bonus.models.Country;
import bonus.models.SisterRelationship;
import bonus.repositories.JPACityRepository;
import bonus.repositories.JPAContinentRepository;
import bonus.repositories.JPACountryRepository;
import bonus.repositories.JPASisterRelationshipRepository;
import bonus.repositories.Repository;

public class JPAController implements DatabaseController {
	private static JPAController instance = null;
	
	public static JPAController getInstance() {
        if (instance == null)
        	instance = new JPAController();
 
        return instance;
    }
	
	private JPAController() {}

	@Override
	public Repository<City> getCityRepository() {
		return JPACityRepository.getInstance();
	}

	@Override
	public Repository<Country> getCountryRepository() {
		return JPACountryRepository.getInstance();
	}

	@Override
	public Repository<Continent> getContinentRepository() {
		return JPAContinentRepository.getInstance();
	}

	@Override
	public Repository<SisterRelationship> getSisterRelationshipRepository() {
		return JPASisterRelationshipRepository.getInstance();
	}
}
