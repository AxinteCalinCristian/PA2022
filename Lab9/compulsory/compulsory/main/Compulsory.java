package compulsory.main;

import java.util.List;

import compulsory.models.Country;
import compulsory.repositories.ContinentRepository;
import compulsory.repositories.CountryRepository;

public class Compulsory {
	public static void main(String[] args) {
//		ContinentRepository.addContinent(new Continent("Europe"));
//		CountryRepository.addCountry(new Country("Romania", "RO", 1));
//		CountryRepository.addCountry(new Country("Germany", "DE", ContinentRepository.getByName("Europe").get(0).getId()));
		List<Country> countries = CountryRepository.getAll();
		System.out.println(countries);
	}
}
