package utils;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.javafaker.Faker;

public class RandomDataGenerator {
	private Faker f = new Faker();
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
			Street s = new Street(f.address().streetName(), length, a, b);
			
			Boolean cond = true;
			
			for(Intersection inter : intersections) {
				if(!inter.equals(a) && !inter.equals(b)) {
					Map<Intersection, Integer> adj_inter = city.getAdjacentIntersections(inter);
					if(adj_inter.containsKey(a) && adj_inter.containsKey(b)) {
						Integer len = adj_inter.get(a) + adj_inter.get(b);
						if(len < length) {
							cond = false;
						}
					}
				}
			}
			
			if(cond) {
				city.addStreet(s);
			}
			else {
				i--;
			}
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
	
	/**
	 * Randomly generates a new city
	 * @param no_of_intersections
	 * @param no_of_streets
	 * @return the newly generated city
	 */
	public City generateCity(int no_of_intersections, int no_of_streets) {
		City city = new City();
		Random random = new Random();
		city = generateIntersections(city, no_of_intersections);
		city = createTree(city);
		
		if(no_of_streets < city.getStreets().size()) {
			System.out.println("Number of streets too low. Corrected to " + city.getStreets().size());
		}
		else {
			city = generateStreets(city, no_of_streets - city.getStreets().size() );
		}
		
		return city;
	}
	
	private City createTree(City city) {
		List<Intersection> unused = city.getIntersections();
		List<Intersection> intersections = city.getIntersections();
		Intersection current = unused.remove(0);
		Random random = new Random();
		while(unused.size() > 0) {
			Integer length = random.nextInt(1, 20);
			Intersection a = unused.get(random.nextInt(0, unused.size()));
			while(a.equals(current)) {
				a = unused.get(random.nextInt(0, unused.size()));
			}
			Street s = new Street(f.address().streetName(), length, current, a);
			
			Boolean cond = true;
			
			for(Intersection inter : intersections) {
				if(!inter.equals(current) && !inter.equals(a)) {
					Map<Intersection, Integer> adj_inter = city.getAdjacentIntersections(inter);
					if(adj_inter.containsKey(current) && adj_inter.containsKey(a)) {
						Integer len = adj_inter.get(current) + adj_inter.get(a);
						if(len < length) {
							cond = false;
						}
					}
				}
			}
			
			if(cond) {
				city.addStreet(s);
				current = a;
				unused.remove(current);
			}
		}
		return city;
	}
}
