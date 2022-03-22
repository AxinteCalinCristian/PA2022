package utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChristophidesAlgorithm {
	private City city;
	private Map<Intersection, Map<Intersection, Integer>> adjacency_list = new HashMap<>();
	
	/**
	 * Sets the current city to this
	 * @param city
	 */
	public void setCity(City city) {
		this.city = city;
		this.adjacency_list = city.getGraph();
	}
	
	/**
	 * Prints a solution to the given problem using the Christophides algorithm for the TSP
	 */
	public void printSolution() {
		PrimAlgorithm pa = new PrimAlgorithm();
		pa.setCity(city);
		List<Street> mst = pa.getSolution();
		List<Intersection> odd_vertices= getOddVertices(mst);
		mst = getMinimumWeightMatching(mst, odd_vertices);
		List<Intersection> ep = getEulerianPath(mst);
		print(ep);
		
	}
	
	/**
	 * Prints the Eulerian path
	 * @param ep
	 */
	private void print(List<Intersection> ep) {
		Intersection current = ep.get(0);
		List<Intersection> path = new LinkedList<>();
		path.add(current);
		Map<Intersection, Boolean> visited = new HashMap<>();
		for(Intersection i : ep) {
			visited.put(i, false);
		}
		visited.put(current, true);
		
		Integer length = 0;
		
		for(Intersection i : ep) {
			if(!visited.get(i)) {
				if(adjacency_list.get(current).containsKey(i)) {
					path.add(i);
					visited.put(i, true);
					length += adjacency_list.get(current).get(i);
					current = i;
				}
			}
		}
		
		path.add(path.get(0));
		for(Intersection i : path) {
			System.out.print("[ " + i.getName() + "] ");
		}
		System.out.println("\nTotal length: " + length);
	}
	
	/**
	 * Given a matched MST, returns an Eulerian tour for the current graph
	 * @param mst
	 * @return list of intersections representing an Eulerian tour
	 */
	private List<Intersection> getEulerianPath(List<Street> mst) {
		Map<Intersection, Set<Intersection>> neighbor = new HashMap<>();
		for(Street s : mst) {
			if(!neighbor.containsKey(s.getAdjacent_intersections().first())) {
				neighbor.put(s.getAdjacent_intersections().first(), new HashSet<>());
			}
			if(!neighbor.containsKey(s.getAdjacent_intersections().second())) {
				neighbor.put(s.getAdjacent_intersections().second(), new HashSet<>());
			}
			
			neighbor.get(s.getAdjacent_intersections().first()).add(s.getAdjacent_intersections().second());
			neighbor.get(s.getAdjacent_intersections().second()).add(s.getAdjacent_intersections().first());
		}
		
		Intersection start_vertex = mst.get(0).getAdjacent_intersections().first();
		List<Intersection> eulerian_path = new LinkedList<>();
		eulerian_path.add(neighbor.get(start_vertex).stream().toList().get(0));
		
		List<Street> new_mst = new LinkedList<>(mst);
		
		while(new_mst.size() > 0) {
			Integer idx = 0;
			while(idx < eulerian_path.size()) {
				if(neighbor.get(eulerian_path.get(idx)).size() > 0) {
					break;
				}
				idx++;
			}
			Intersection v = eulerian_path.get(idx);
			while(neighbor.get(v).size() > 0) {
				Intersection u = neighbor.get(v).stream().toList().get(0);
				final Intersection curr = v;
				new_mst = new_mst.stream().filter(s -> 
				        ((s.getAdjacent_intersections().first().equals(curr)
	    				&& s.getAdjacent_intersections().second().equals(u)) 
	    				|| (s.getAdjacent_intersections().second().equals(curr)
	    	    		&& s.getAdjacent_intersections().first().equals(u))))
						.toList();
				
				neighbor.get(v).remove(u);
				neighbor.get(u).remove(v);
				idx++;
				eulerian_path.add(idx, u);
				v = u;
			}
		}
		return eulerian_path;
	}
	
	/**
	 * Given an mst and a set of odd vertices, returns MST with added minimum weight matching
	 * @param mst
	 * @param odd_vertices
	 * @return
	 */
	private List<Street> getMinimumWeightMatching(List<Street> mst, List<Intersection> odd_vertices) {
		List<Street> new_mst = new LinkedList<>(mst);
		List<Intersection> odd_vert = new LinkedList<>(odd_vertices);
		List<Street> streets = city.getStreets();
		Collections.shuffle(odd_vert);
		
		while(odd_vert.size() > 0) {
			Intersection v = odd_vert.remove(0);
			Integer length = Integer.MAX_VALUE;
			Intersection u = null;
			
			for(Intersection inter : odd_vert) {
				if(adjacency_list.get(v).containsKey(inter)) {
					if(!v.equals(inter) && adjacency_list.get(v).get(inter) < length) {
						length = adjacency_list.get(v).get(inter);
						u = inter;
					}
				}
				
			}
			
			if(u == null) {
				continue;
			}
			
			final Intersection closest = u;
			Street res_street = streets.stream().filter(s -> (s.getLength() == adjacency_list.get(v).get(closest)
    				&& (s.getAdjacent_intersections().first().equals(v)
    				&& s.getAdjacent_intersections().second().equals(closest)) 
    				|| (s.getAdjacent_intersections().second().equals(v)
    	    			&& s.getAdjacent_intersections().first().equals(closest)))).toList().get(0);
			new_mst.add(res_street);
			odd_vert.remove(u);
		}
		
		return new_mst;
	}
	
	/**
	 * Returns a list containing all the odd vertices in the MST
	 * @param mst
	 * @return
	 */
	private List<Intersection> getOddVertices(List<Street> mst) {
		Set<Intersection> inters = new HashSet<>();
		Map<Intersection, Integer> tmp_graph = new HashMap<>();
		for(Street s : mst) {
			if(!tmp_graph.containsKey(s.getAdjacent_intersections().first())) {
				tmp_graph.put(s.getAdjacent_intersections().first(), 0);
			}
			if(!tmp_graph.containsKey(s.getAdjacent_intersections().second())) {
				tmp_graph.put(s.getAdjacent_intersections().second(), 0);
			}
			tmp_graph.put(s.getAdjacent_intersections().first(), 
					tmp_graph.get(s.getAdjacent_intersections().first()) + 1);
			tmp_graph.put(s.getAdjacent_intersections().second(), 
					tmp_graph.get(s.getAdjacent_intersections().second()) + 1);
		}
		
		for(Map.Entry<Intersection, Integer> entry : tmp_graph.entrySet()) {
			if(entry.getValue() % 2 == 1) {
				inters.add(entry.getKey());
			}
		}
		
		List<Intersection> odd_vertices= new LinkedList<>(inters);
		return odd_vertices;
	}
}
