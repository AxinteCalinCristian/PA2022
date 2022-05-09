package homework.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import homework.repositories.CityRepository;
import homework.repositories.CountryRepository;
import homework.models.City;
import homework.models.Continent;
import homework.models.Country;
import homework.repositories.ContinentRepository;

public class Utils {
	private static final Faker faker = new Faker();
	private static final Random rand = new Random();
	private static final String[] continents = {"Asia", "Africa", "North America", "South America", "Antarctica", "Europe", "Australia" };
	
	private static void addToDB(String[] args) {
		if(args.length < 6) {
			return;
		}
		
		String country = args[0];
		String capital = args[1];
		String lat = args[2];
		String lon = args[3];
		String code = args[4];
		String continent = args[5];
		
		addContinent(continent);
		
		Continent cont = null;
		if(ContinentRepository.getByName(continent).size() > 0) {
			cont = ContinentRepository.getByName(continent).get(0);
		} else {
			return;
		}
		
		addCountry(country, code, cont);
		
		Country count = null;
		if(CountryRepository.getByName(country).size() > 0) {
			count = CountryRepository.getByName(country).get(0);
		} else {
			return;
		}
		
		addCity(capital, count, lat, lon);
		
	}
	
	private static void addContinent(String name) {
		ContinentRepository.addContinent(new Continent(name));
	}
	
	private static void addCountry(String name, String code, Continent continent) {
		CountryRepository.addCountry(new Country(name, code, continent));
	}
	
	private static void addCity(String name, Country country, String lat, String lon) {
		CityRepository.addCity(new City(name, country, rand.nextBoolean(), lat, lon, Math.abs(rand.nextInt() % 2_000_000 + 5_000 )));
	}
	
	public static void addDummyData(Integer noOfEntries) {
		for(int i=0;i<noOfEntries;i++) {
			addToDB(generateCity());
		}
	}

	private static String[] generateCity() {
		com.github.javafaker.Country c = faker.country();
		Address a = faker.address();
		
		String country = c.name();
		String name = a.cityName();
		String lat = a.latitude();
		String lon = a.longitude();
		String code = c.countryCode2();
		String continent = continents[Math.abs(rand.nextInt()) % continents.length];
		
		String[] args = { country, name, lat, lon, code, continent };
		return args;
	}

	public static void solveCitiesConstraintsProblem(Integer lowerBound, Integer upperBound) {
		List<City> cities = CityRepository.getAll();
		String pop = getPopConstraintSet(cities, lowerBound, upperBound);
		String fl = getFLConstraintSet(cities);
		String c = getCountryConstraintSet(cities);
		System.out.println(getResultingSet(pop, fl, c));
	}
	
	private static Set<String> getResultingSet(String pop, String fl, String c) {
		Set<String> res = getTokens(pop);
		res.retainAll(getTokens(fl));
		res.retainAll(getTokens(c));
		return res;
	}
	
	private static Set<String> getTokens(String s) {
		String[] tokenize = s.split(", ");  
		Set<String> tokens = new HashSet<>();
		
		for(String t : tokenize) {
			tokens.add(t);
		}
		
		return tokens;
	}
	
	private static String getPopConstraintSet(List<City> cities, Integer lowerBound, Integer upperBound) {
		Model popModel = new Model("Population constraint");
		IntVar[] populations = new IntVar[cities.size()];
		for(int q = 0; q < cities.size(); q++){
			populations[q] = popModel.intVar(cities.get(q).getName(), cities.get(q).getPopulation());
		}
		
		for(int q=0; q < populations.length; q++) {
			popModel.arithm(populations[q], ">=", lowerBound).post();
			popModel.arithm(populations[q], "<=", upperBound).post();
		}
		
		Solution sol = popModel.getSolver().findSolution();
		if(sol != null)
			return sol.toString();
		else
			return "";
	}
	
	private static String getFLConstraintSet(List<City> cities) {
		Model flModel = new Model("First letter in name constraint");
		IntVar[] first_letters = new IntVar[cities.size()];
		for(int q = 0; q < cities.size(); q++){
			first_letters[q] = flModel.intVar(cities.get(q).getName(), cities.get(q).getName().charAt(0));
		}
		
		flModel.allDifferent(first_letters).post();
		
		Solution sol = flModel.getSolver().findSolution();
		if(sol != null)
			return sol.toString();
		else
			return "";
	}
	
	private static String getCountryConstraintSet(List<City> cities) {
		Model dcModel = new Model("Disjunct countries constraint");
		IntVar[] countries = new IntVar[cities.size()];
		for(int q = 0; q < cities.size(); q++){
			countries[q] = dcModel.intVar(cities.get(q).getName(), cities.get(q).getCountry().getName().hashCode());
		}
		
		dcModel.allDifferent(countries).post();
		
		Solution sol = dcModel.getSolver().findSolution();
		if(sol != null)
			return sol.toString();
		else
			return "";
	}
}
