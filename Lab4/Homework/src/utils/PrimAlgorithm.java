package utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrimAlgorithm {
	private City city;
	private Map<Intersection, Map<Intersection, Integer>> adjacency_list = new HashMap<>();
	/**
	 * Sets the current city to this
	 * @param city
	 */
	public void setCity(City city) {
		this.city = city;
		convertCityToAdjacencyList(this.city);
	}
	
	private void convertCityToAdjacencyList(City city) {
		List<Street> streets = city.getStreets();
		List<Intersection> inters = city.getIntersections();
		
		adjacency_list.clear();
		
		for(Intersection i : inters) {
			this.adjacency_list.put(i, new HashMap<>());
		}
		
		for(Street s : streets) {
			Intersection a = s.getAdjacent_intersections().first();
			Intersection b = s.getAdjacent_intersections().second();
			Integer length = s.getLength();
			adjacency_list.get(a).put(b, length);
			adjacency_list.get(b).put(a, length);
		}
	}
	
	/**
	 * Prints the MST and the corresponding costs for the current city
	 */
	public void printSolution() {
		Map<Intersection, Intersection> parent = new HashMap<>();
		Map<Intersection, Integer> key = new HashMap<>();
		Set<Intersection> mst = new HashSet<>();
		
		for(Intersection i : city.getIntersections()) {
			key.put(i, Integer.MAX_VALUE);
			parent.put(i, null);
		}
		
		key.put(city.getIntersections().get(0), 0);
		
		for(Intersection i : city.getIntersections()) {
			Intersection u = minKey(key, mst);
			mst.add(u);
			for(Map.Entry<Intersection, Integer> adj : adjacency_list.get(u).entrySet()) {
				if(!(mst.contains(adj.getKey())) && adj.getValue() < key.get(adj.getKey())) {
					parent.put(adj.getKey(), u);
					key.put(adj.getKey(), adj.getValue());
				}
			}
		}

		print(parent);
	}
	
	/**
	 * Prints the solution
	 * @param parent Map of child - parent relations
	 */
	private void print(Map<Intersection, Intersection> parent)
	{
	    System.out.println("Edge \tWeight");
	    Integer total_cost = 0;
	    for(Intersection i : adjacency_list.keySet()) {
	    	if(parent.get(i) != null) {
	    		System.out.println("[ " + parent.get(i).getName() + " ] -- [ " + i.getName() + " ]: Length=" + adjacency_list.get(i).get(parent.get(i)));
	    		total_cost += adjacency_list.get(i).get(parent.get(i));
	    	}	
	    }
	    System.out.println("Total cost = " + total_cost);
	}
	 
	/**
	 * Gets the minimum value key (intersection) for the current loop
	 * @param key
	 * @param mst
	 * @return minimum value key
	 */
	 private Intersection minKey(Map<Intersection, Integer> key, Set<Intersection> mst) {
		 Integer min = Integer.MAX_VALUE;
		 Intersection min_key = null;
		 for(Intersection i : city.getIntersections()) {
			 if(!(mst.contains(i)) && key.get(i) < min) {
				 min = key.get(i);
				 min_key = i;
			 }
		 }
		 return min_key;
	 }
}
