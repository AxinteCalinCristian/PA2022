package compulsory.main;

import compulsory.models.Continent;
import compulsory.models.Country;
import compulsory.utils.Database;

public class Compulsory {
	public static void main(String[] args) {
		Database.initConnection();
		Database.addContinent(new Continent(-1, "Europe"));
		Database.addContinent(new Continent(-1, "Asia"));
		Database.addCountry(new Country(-1, "Romania", "RO", "Europe"));
		Database.addCountry(new Country(-1, "China", "CN", "Asia"));
		Database.addCountry(new Country(-1, "France", "FR", "Europe"));
		
		Country c = Database.getCountry("Romania").get(0);
		Continent cont = Database.getContinent("Asia").get(0);
		System.out.println(c.toString());
		System.out.println(cont.toString());
	}
}
