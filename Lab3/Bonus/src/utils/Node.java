package utils;

import java.util.HashMap;
import java.util.Map;

public abstract class Node {
	protected String name;
	protected Map<Node, Pair<Integer, Double>> edge_costs = new HashMap<>();
	
	/**
	 * @param name the node's name will be set to this
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the node's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Adds the specified neighbor with the corresponding cost and fail probability to the list, if it's not already present
	 * @param neighbor
	 * @param cost
	 * @param fail_probability
	 */
	public void addNeighbor(Node neighbor, Integer cost, Double fail_probability) {
		if(edge_costs.containsKey(neighbor)) {
			return;
		}
		
		edge_costs.put(neighbor, new Pair<Integer, Double>(cost, fail_probability));
		neighbor.addNeighbor(this, cost, fail_probability);
	}
	
	/**
	 * @param neighbor Removes this neighbor from the list, if it exists
	 */
	public void removeNeighbor(Node neighbor) {
		if(edge_costs.containsKey(neighbor)) {
			edge_costs.remove(neighbor);
		}
	}
	
	/**
	 * @return A map containing all neighboring nodes and the costs + fail probabilities to reach them
	 */
	public Map<Node, Pair<Integer, Double>> getNeighbors() {
		return this.edge_costs;
	}
	
}
