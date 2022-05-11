package bonus.main;

import java.util.List;

import bonus.database.Database;
import bonus.models.Country;

public class Bonus {
	public static void main(String[] args) {
		
//		Utils.addDummyData(1000);
		
		List<Country> countries = Database.getDatabaseController().getCountryRepository().getAll();
		System.out.println(countries);
	}
}
