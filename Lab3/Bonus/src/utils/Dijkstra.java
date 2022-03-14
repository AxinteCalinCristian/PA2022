package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra {
	
	private Map<Node, Map<Node, Pair<Integer, Double>>> adjacency_list;
	
	/**
	 * Default constructor, creates an empty graph
	 */
	public Dijkstra() {
		this.adjacency_list = new HashMap<>();
	}
	
	/**
	 * Takes a network to construct a graph
	 * @param network
	 */
	public Dijkstra(Network network) {
		this.adjacency_list = new HashMap<>();
		
		convertNetworkToAdjacencyList(network);
	}
	
	/**
	 * @param network Changes the current network to this
	 */
	public void changeNetwork(Network network) {
		this.adjacency_list.clear();
		convertNetworkToAdjacencyList(network);
	}
	
	/**
	 * Converts the given network to a graph adjacency list
	 * @param network
	 */
	private void convertNetworkToAdjacencyList(Network network) {		
		for(Node n : network.getNodes()) {
			this.adjacency_list.put(n, n.getNeighbors());
		}
	}
	
	/**
	 * Using the Dijkstra algorithm, prints a solution for finding the safest path between two provided nodes
	 * @param node_a First node
	 * @param node_b Second node
	 * @return 
	 */
    public Pair<Double, ArrayList<Node>> printSolution(Node node_a, Node node_b)
    {
    	Map<Node, Double> probabilities = new HashMap<>();
    	Map<Node, Boolean> used = new HashMap<>();
        for(Node n : adjacency_list.keySet()) {
        	probabilities.put(n, 0.);
        	used.put(n, false);
        }
        probabilities.put(node_a, 1.);
        Map<Node, Node> parents = new HashMap<>();
        parents.put(node_a, null);
        
        Comparator<Pair<Node, Pair<Integer, Double>>> comparator = 
        	(Pair<Node, Pair<Integer, Double>> v1, Pair<Node, Pair<Integer, Double>> v2) -> {
	        return v2.second().second().compareTo(v1.second().second());
	    };
	    
        PriorityQueue<Pair<Node, Pair<Integer, Double>>> pq = new PriorityQueue<>(comparator);
        
        Pair<Integer, Double> cp = new Pair<Integer, Double>(0, 1.);
        Pair<Node, Pair<Integer, Double>> nc = new Pair<Node, Pair<Integer, Double>>(node_a, cp);
        pq.add(nc);
        
        while (pq.size() > 0) {
        	Pair<Node, Pair<Integer, Double>> current = pq.poll();
        	Node curr_node = current.first();
        	used.put(current.first(), true);
        	Double prob = probabilities.get(current.first());

            for (Map.Entry<Node, Pair<Integer, Double>> neighbor : adjacency_list.get(current.first()).entrySet()) {
            	
        		if(!(used.get(neighbor.getKey()))) {
            		Double res = prob * (1. - neighbor.getValue().second());
            		
        			if(res.compareTo(probabilities.get(neighbor.getKey())) > 0) {

        				parents.put(neighbor.getKey(), curr_node);
        				probabilities.put(neighbor.getKey(), res);
        				Pair<Integer, Double> cp_1 = new Pair<Integer, Double>(0, res);
        		        Pair<Node, Pair<Integer, Double>> nc_1 = new Pair<Node, Pair<Integer, Double>>(neighbor.getKey(), cp_1);
    	                pq.add(nc_1);

        			}
        		}
        	
            }
        }
        
        System.out.println("Highest success rate: " + String.format("%.2f", probabilities.get(node_b) * 100.) + "%");
        ArrayList<Node> arr = printShortestPath(node_a, node_b, parents);
        Pair<Double, ArrayList<Node>> result = new Pair<Double, ArrayList<Node>>(probabilities.get(node_b), arr);
        return result;
    }
    
    /**
     * Given a map of parent-child relations, prints the path between node_a and node_b
     * @param node_a First node
     * @param node_b Second node
     * @param parents Map of parent-child relations
     */
    private ArrayList<Node> printShortestPath(Node node_a, Node node_b, Map<Node, Node> parents) {
    	System.out.println("Safest path:");
        Node current = node_b;
        Stack<Node> st = new Stack<Node>();
        ArrayList<Node> list = new ArrayList<>();
        
        while(current != node_a) {
        	st.push(current);
        	current = parents.get(current);
        }
        st.push(current);

        while(!st.isEmpty()) {
        	Node n = st.pop();
        	System.out.print(n.getName() + ' ');
        	list.add(n);
        }
        System.out.print('\n');
        return list;
    }

}
