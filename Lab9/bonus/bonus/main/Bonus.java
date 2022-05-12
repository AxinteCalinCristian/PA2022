package bonus.main;

import java.util.List;

import bonus.database.Database;
import bonus.models.Country;
import bonus.models.SisterRelationship;
import bonus.utils.Utils;

public class Bonus {
	public static void main(String[] args) {
		
//		Utils.addDummyData(1000);
//		Utils.addSisterRelationships(2000);
//		List<SisterRelationship> sr = Database.getDatabaseController().getSisterRelationshipRepository().getAll();
		List<Country> countries = Database.getDatabaseController().getCountryRepository().getAll();
		System.out.println(countries);
		
		SisterRelationship sr = Database.getDatabaseController().getSisterRelationshipRepository().getById(1208);
		System.out.println(sr);
	}
}
