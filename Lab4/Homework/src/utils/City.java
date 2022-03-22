package utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class City {
	Set<Intersection> intersections = new HashSet<>();
	List<Street> streets = new LinkedList<>();
	
	/**
	 * @param in Adds this to a set of intersections
	 */
	public void addIntersection(Intersection in) {
		intersections.add(in);
	}
	
	/**
	 * @param s Adds this to a list of streets
	 */
	public void addStreet(Street s) {
		streets.add(s);
	}
	
	/**
	 * Clears all intersections and streets
	 */
	public void resetCity() {
		intersections.clear();
		streets.clear();
	}
	
	/**
	 * @return A list of all intersections in the city
	 */
	public List<Intersection> getIntersections() {
		List<Intersection> new_inters = new LinkedList<>(this.intersections);
		return new_inters;
	}
	
	/** 
	 * @return A list of all the streets in the city
	 */
	public List<Street> getStreets() {
		List<Street> new_streets = new LinkedList<>(this.streets);
		return new_streets;
	}
	
	public void printCity() {
		System.out.println("Intersections: ");
		for(Intersection i : intersections) {
			System.out.print("[ " + i.getName() + " ] ");
		}
		System.out.println("\nStreets");
		for(Street s : streets) {
			System.out.println("[ " + s.getName() + " ]: " + "[ " + s.getAdjacent_intersections().first().getName() + " ] -- " +
					 "[ " + s.getAdjacent_intersections().second().getName() + " ] (length=" + s.getLength() + ")");
		}
	}
	
	/**
	 * @param min_length
	 * @return All the streets in the city with length >= min_length
	 */
	public List<Street> getStreetsWithLength(Integer min_length) {
		List<Street> str = streets.stream().filter(s -> s.getLength() >= min_length && 
				((s.getAdjacent_intersections().first().getAdjacentStreets().size() + 
				  s.getAdjacent_intersections().second().getAdjacentStreets().size()) >= 5)).toList();
		return str;
	}
}
