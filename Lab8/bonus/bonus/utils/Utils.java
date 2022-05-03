package bonus.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.jgrapht.Graph;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import bonus.repositories.CityRepository;
import bonus.repositories.ContinentRepository;
import bonus.repositories.CountryRepository;
import bonus.repositories.SisterRelationshipRepository;
import bonus.models.City;
import bonus.models.Continent;
import bonus.models.Country;
import bonus.models.SisterRelationship;
import lombok.extern.java.Log;

@Log
public class Utils {
	private static final Faker faker = new Faker();
	private static final Random rand = new Random();
	
	public static double getDistance(City a, City b) {
		double lat1 = Double.parseDouble(a.getLatitude());
		double long1 = Double.parseDouble(a.getLongitude());
		double lat2 = Double.parseDouble(b.getLatitude());
		double long2 = Double.parseDouble(b.getLongitude());
		
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		long1 = Math.toRadians(long1);
        long2 = Math.toRadians(long2);
  
        double dlat = lat2 - lat1;
        double dlon = long2 - long1;
        
        double ab = Math.pow(Math.sin(dlat / 2), 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.pow(Math.sin(dlon / 2),2);
             
        double c = 2 * Math.asin(Math.sqrt(ab));
        double r = 6371;
        return(c * r);
	}
	
	public static boolean populateDatabase(String path) {
		try {
			CSVReader reader = new CSVReader(new FileReader(path));
			List<String[]> r = reader.readAll();
			r.remove(0);
			
			r.forEach(x -> addToDB(x));
			
		} catch (IOException | CsvException e) {
			log.severe("Could not parse file");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
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
		
		if(!addContinent(continent)) {
			return;
		}
		
		if(!addCountry(country, code, continent)) {
			return;
		}
		
		if(!addCity(capital, country, lat, lon)) {
			return;
		}
		
	}
	
	private static boolean addContinent(String name) {
		List<Continent> conts = ContinentRepository.getByName(name);
		if(conts.size() > 0) {
			return true;
		}
		return Database.addContinent(new Continent(-1, name));
	}
	
	private static boolean addCountry(String name, String code, String continent) {
		List<Country> countries = CountryRepository.getByName(name);
		if(countries.size() > 0) {
			return true;
		}
		
		return Database.addCountry(new Country(-1, name, code, continent));
	}
	
	private static boolean addCity(String name, String country, String lat, String lon) {
		List<City> cities = CityRepository.getByName(name);
		if(cities.size() > 0) {
			return true;
		}
		
		return Database.addCity(new City(-1, name, country, true, lat, lon));
	}
	
	public static void addDummyData(Integer noOfEntries) {
		List<Continent> conts = ContinentRepository.getAll();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		
		executor.submit(() -> {
			for(int i=0;i<noOfEntries;i++) {
				addToDB(generateCity(conts));
			}
			
			addSisterRelationships(noOfEntries / 20);
		});
		
	}
	
	public static void addSisterRelationships(Integer noOfRels) {
		List<City> cities = CityRepository.getAll();
		
		for(int i=0;i<noOfRels;i++) {
			int idx1 = Math.abs(rand.nextInt()) % cities.size();
			int idx2 = Math.abs(rand.nextInt()) % cities.size();
			
			while(idx1 == idx2) {
				idx2 = Math.abs(rand.nextInt()) % cities.size();
			}

			SisterRelationshipRepository.addSisterRelationship(new SisterRelationship(-1, cities.get(idx1).getId(), cities.get(idx2).getId()));
		}
	}
	
	private static String[] generateCity(List<Continent> conts) {
		com.github.javafaker.Country c = faker.country();
		Address a = faker.address();
		
		String country = c.name();
		String name = a.cityName();
		String lat = a.latitude();
		String lon = a.longitude();
		String code = c.countryCode2();
		String continent = conts.get(Math.abs(rand.nextInt()) % conts.size()).getName();
		
		String[] args = { country, name, lat, lon, code, continent };
		return args;
	}
	
	/**
	 * Runs the BronKerbosch algorithm on the current database.
	 */
	public static void runBronKerbosch() {
		Graph<City, DefaultEdge> g = constructGraph();
		BronKerboschCliqueFinder<City, DefaultEdge> s = new BronKerboschCliqueFinder<City, DefaultEdge>(g);
		Collection<Set<City>> cliques = s.getAllMaximalCliques();
		
		for(Set<City> clique : cliques) {
			if(clique.size() >= 3) {
				for(City c : clique) {
					System.out.print("[ " + c.getName() + " ] ");
				}
				System.out.println();
			}
		}
	}
	
	private static Graph<City, DefaultEdge> constructGraph() {
		List<Pair<City, City>> cityPairs = getEdges();
		
		Graph<City, DefaultEdge> g = new SimpleGraph<City, DefaultEdge>(DefaultEdge.class);
		for(Pair<City, City> cityPair : cityPairs) {
			if(!g.containsVertex(cityPair.first()))
				g.addVertex(cityPair.first());
			if(!g.containsVertex(cityPair.second()))
				g.addVertex(cityPair.second());
			if(!g.containsEdge(cityPair.first(), cityPair.second()))
				g.addEdge(cityPair.first(), cityPair.second());
			if(!g.containsEdge(cityPair.second(), cityPair.first()))
				g.addEdge(cityPair.second(), cityPair.first());
		}
		return g;
	}
	
	private static List<Pair<City, City>> getEdges() {
		List<City> cities = CityRepository.getAll();
		List<SisterRelationship> srs = SisterRelationshipRepository.getAll();
		List<Pair<City, City>> cityPairs = new LinkedList<>();
		
		for(SisterRelationship sr : srs) { 
			City a = null;
			City b = null;
			
			for(City c : cities) { 
				if(c.getId().equals(sr.getFirstCity())) {
					a = c;
				}
				if(c.getId().equals(sr.getSecondCity())) {
					b = c;
				}
			}
			if(a != null && b != null) {
				cityPairs.add(new Pair<City, City>(a, b));
			}
		}
		return cityPairs;
	}
}
