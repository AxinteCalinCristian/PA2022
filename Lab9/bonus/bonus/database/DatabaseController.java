package bonus.database;

import bonus.models.City;
import bonus.models.Continent;
import bonus.models.Country;
import bonus.models.SisterRelationship;
import bonus.repositories.Repository;

/**
 * Represents a generic database controller.
 * @author Calin Axinte
 */
public interface DatabaseController {
	public Repository<City> getCityRepository();
	public Repository<Country> getCountryRepository();
	public Repository<Continent> getContinentRepository();
	public Repository<SisterRelationship> getSisterRelationshipRepository();
}
