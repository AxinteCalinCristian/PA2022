package utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class FloydWarshall {
	final static int INF = 0x3f3f3f3f;
	
	private Map<Node, Map<Node, Integer>> adjacency_list;
	
	/**
	 * Default constructor, creates an empty graph
	 */
	public FloydWarshall() {
		this.adjacency_list = new HashMap<>();
	}
	
	/**
	 * Takes a network to construct a graph
	 * @param network
	 */
	public FloydWarshall(Network network) {
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
	 * Using the Floyd-Warshall algorithm, prints a solution for finding all the shortest pair distances in the network
	 */
	public void printSolution() {
		if(adjacency_list.isEmpty()) {
			System.out.println("No network provided");
			return;
		}
		
		Map<Node, Map<Node, Integer>> dist = new HashMap<>();

		for(Map.Entry<Node, Map<Node, Integer>> entry : adjacency_list.entrySet()) {
			Map<Node, Integer> list = new HashMap<>();
			
			for(Map.Entry<Node, Map<Node, Integer>> entry2 : adjacency_list.entrySet()) {
				if(!(entry2.getKey().equals(entry))) {
					if(entry.getValue().containsKey(entry2.getKey())) {
						list.put(entry2.getKey(), entry.getValue().get(entry2.getKey()));
					}
					else {
						list.put(entry2.getKey(), INF);
					}
				}
			}
			
			dist.put(entry.getKey(), list);
		}
		
		for (Node k : dist.keySet())
        {
            for (Node i : dist.keySet())
            {
                for (Node j : dist.keySet())
                {
                    if (dist.get(i).get(k) + dist.get(k).get(j) < dist.get(i).get(j))
                    	dist.get(i).put(j, dist.get(i).get(k) + dist.get(k).get(j));
                }
            }
        }
		
		printAdjacencyList(dist);

	}
	
	/**
	 * Prints the provided adjacency list, ordered ascending by keys
	 * @param dist
	 */
	private void printAdjacencyList(Map<Node, Map<Node, Integer>> dist) {
		Comparator<Node> comparator = (Node o1, Node o2) -> {
	        return o1.getName().compareTo(o2.getName());
	    };

		SortedSet<Node> keys = new TreeSet<Node>(comparator);
		keys.addAll(dist.keySet());
		
		for(Node key : keys) {
			System.out.print("[ " + key.getName() + " ]: ");
			
			SortedSet<Node> list = new TreeSet<Node>(comparator);
			list.addAll(dist.get(key).keySet());
			
			for(Node value : list) {
				if(dist.get(key).get(value) != INF) {
					System.out.print("[ " + value.getName() + " ](cost=" + dist.get(key).get(value) + ") ");
				}
			}
			System.out.print('\n');
		}
	}
}
