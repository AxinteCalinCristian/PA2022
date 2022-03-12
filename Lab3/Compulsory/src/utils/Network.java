package utils;

import java.util.LinkedList;

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
	 * Prints all the nodes in the network
	 */
	public void printNodes() {
		for(Node n : nodes) {
			System.out.print("[" + n.getName() + "] ");
		}
		System.out.print('\n');
	}
}
