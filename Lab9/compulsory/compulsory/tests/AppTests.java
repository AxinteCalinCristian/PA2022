package compulsory.tests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import compulsory.models.Continent;
import compulsory.models.Country;
import compulsory.repositories.ContinentRepository;
import compulsory.repositories.CountryRepository;

class AppTests {

	@Test
	void test_continent() {
		ContinentRepository.addContinent(new Continent("Asia"));
		List<Continent> conts = ContinentRepository.getByName("Asia");
		Assertions.assertNotNull(conts);
		Assertions.assertNotEquals(conts.size(), 0);
	}
	
	@Test
	void test_country() {
		CountryRepository.addCountry(new Country("China", "CN", ContinentRepository.getByName("Asia").get(0).getId()));
		List<Country> countries = CountryRepository.getByName("China");
		Assertions.assertNotNull(countries);
		Assertions.assertNotEquals(countries.size(), 0);
	}
}
