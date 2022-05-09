package homework.main;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import homework.models.Country;
import homework.repositories.CountryRepository;
import homework.utils.Utils;

public class Homework {
	public static void main(String[] args) {
		Configurator.setLevel("org.hibernate.SQL", Level.DEBUG);
		Configurator.setLevel("org.hibernate.stat", Level.DEBUG);
//		Utils.addDummyData(1000);
		
		List<Country> countries = CountryRepository.getAll();
		System.out.println(countries);
		
		Utils.solveCitiesConstraintsProblem(0, 5_000_000);
	}
}
