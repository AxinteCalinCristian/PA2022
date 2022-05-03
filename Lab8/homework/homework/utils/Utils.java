package homework.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import homework.repositories.CityRepository;
import homework.repositories.ContinentRepository;
import homework.repositories.CountryRepository;
import homework.graphics.GraphicsController;
import homework.graphics.MapPoint;
import homework.graphics.SphericalMercator;
import homework.models.City;
import homework.models.Continent;
import homework.models.Country;
import lombok.extern.java.Log;

@Log
public class Utils {
	private static SphericalMercator sphMercator = new SphericalMercator();
	
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
		if(!Database.getIsConnectionOpen()) {
			Database.initConnection();
		}
		
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
	
	public static void displayCities() {
		List<City> cities = CityRepository.getAll();
		List<MapPoint> points = convertCitiesToMap(cities);
		
		GraphicsController.run("Cities map", 800, 600, points);
	}
	
	private static List<MapPoint> convertCitiesToMap(List<City> cities) {
		List<MapPoint> points = new LinkedList<>();
		
		for(City c : cities) {
			double x = Double.parseDouble(c.getLatitude());
			x = sphMercator.xAxisProjection(x);
			
			double y = Double.parseDouble(c.getLongitude());
			y = sphMercator.yAxisProjection(y);
			
			if(!Double.isNaN(x) && !Double.isNaN(y)) {
				points.add(new MapPoint(x, y, 4.0, c.getName(), null));
			}
		}
		
		return points;
	}
}
