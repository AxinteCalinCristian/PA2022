package utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Intersection {
	private String name;
	private Set<Street> adjacent_streets = new HashSet<>();
	/**
	 * Creates a new intersection with the given name
	 * @param name
	 */
	public Intersection(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }

        if (!(obj instanceof Intersection)) {
            return false;
        }
        
        Intersection i = (Intersection) obj;
        
        if(this.name.equals(i.getName()))
        {
        	return true;
        }
        
        return false;
	}
	
	/**
	 * Adds the street as adjacent to the intersection, if it isn't already added
	 * @param s
	 */
	public void addAdjacentStreet(Street s) {
		this.adjacent_streets.add(s);
	}
	
	/**
	 * @return A list containing all the streets adjacent to this intersection
	 */
	public List<Street> getAdjacentStreets() {
		List<Street> adj_streets = new LinkedList<>(this.adjacent_streets);
		return adj_streets;
	}
}
