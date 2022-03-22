package utils;

import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

public class RandomDataGenerator {
	Faker f = new Faker();
	
	/**
	 * Given a city, generates amount of random streets between intersections in said city
	 * @param city
	 * @param amount
	 * @return city with the newly added streets
	 */
	public City generateStreets(City city, int amount) {
		List<Intersection> intersections = city.getIntersections();
		
		if(intersections.size() < 2) {
			return null;
		}
		
		Random random = new Random();
		
		for(int i=0;i<amount;i++) {
			Integer length = random.nextInt(1, 20);
			Intersection a = intersections.get(random.nextInt(0, intersections.size()));
			Intersection b = intersections.get(random.nextInt(0, intersections.size()));
			while(b.equals(a)) {
				b = intersections.get(random.nextInt(0, intersections.size()));
			}
			
			city.addStreet(new Street(f.address().streetName(), length, a, b));
		}
		
		return city;
	}
	
	/**
	 * Generates amount of random intersections
	 * @param amount
	 * @return city with the newly added intersections
	 */
	public City generateIntersections(City city, int amount) {
		for(int i=0;i<amount;i++) {
			city.addIntersection(new Intersection(f.address().fullAddress()));
		}
		
		return city;
	}
}
