package utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Network {
	LinkedList<Node> nodes;
	
	/**
	 * Default constructor, initializes the network as an empty linked list of nodes
	 */
	public Network() {
		nodes = new LinkedList<Node>();
	}
	
	/**
	 * Adds a node to the network, if it hasn't been previously added
	 * @param node
	 */
	public void addNode(Node node) {
		if(!nodes.contains(node))
			nodes.add(node);
	}
	
	/**
	 * Removes the specified node
	 * @param node
	 */
	public void removeNode(Node node) {
		if(nodes.contains(node))
			nodes.remove(node);
	}
	
	/**
	 * @return a list containing all nodes in the network
	 */
	public LinkedList<Node> getNodes() {
		return this.nodes;
	}
	
	/**
	 * Prints all the nodes in the network
	 */
	public void printNodes() {
		for(Node n : nodes) {
			System.out.print("[" + n.getName() + "] ");
		}
		System.out.print('\n');
	}
	
	/**
	 * Prints the entire network
	 */
	public void printNetwork() {
		Map<Node, Integer> list = null;
		
		for(Node n : nodes) {
			list = n.getNeighbors();
			for(Map.Entry<Node, Integer> neighbor : list.entrySet()){
				System.out.println("[ " + n.getName() + " ] -- [ " + neighbor.getKey().getName() + " ] (cost=" + neighbor.getValue()+") ");
			}
		}
	}
	
	class SortByAddress implements Comparator<Node>
	{
		@Override
		public int compare(Node o1, Node o2) {
			if(!(o1 instanceof Identifiable) || !(o2 instanceof Identifiable)) {
				return 0;
			}
			Identifiable i1 = (Identifiable) o1;
			Identifiable i2 = (Identifiable) o2;
			
			return i1.getAddress().compareTo(i2.getAddress());
		}
	}
	
	public void printIdentifiableNodes() {
		List<Node> identifiables = nodes.stream().filter(n -> n instanceof Identifiable).collect(Collectors.toList());
		Collections.sort(identifiables, new SortByAddress());
		
		System.out.println("Identifiables:");
		for(Node n : identifiables) {
			System.out.println("[ " + n.getName() + " ](address=" + ((Identifiable)n).getAddress() + ")");
		}
	}
}
