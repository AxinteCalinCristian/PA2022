package main;

import utils.*;

public class Main {
	public static void main(String[] args) {
Network network = new Network();
		
		Node a = new Computer("1", 200, "00:00:5e:00:53:af");
		Node b = new Router("2", "00:00:1f:10:67:ff");
		Node c = new Switch("3", true);
		Node d = new Computer("4", 500, "00:00:8a:00:65:ee");
		Node e = new Router("5", "00:00:1f:ab:1f:00");
		Node f = new Switch("6", false);
		
		a.addNeighbor(b, 10, 0.1);
		a.addNeighbor(c, 50, 0.);
		b.addNeighbor(c, 20, 0.2);
		b.addNeighbor(d, 20, 0.3);
		b.addNeighbor(e, 20, 0.1);
		c.addNeighbor(d, 10, 0.25);
		d.addNeighbor(e, 30, 0.35);
		d.addNeighbor(f, 10, 0.15);
		e.addNeighbor(f, 20, 0.2);
		
		network.addNode(a);
		network.addNode(b);
		network.addNode(c);
		network.addNode(d);
		network.addNode(e);
		network.addNode(f);
		
		Dijkstra dj = new Dijkstra(network);
		dj.printSolution(a, f);
	}
}
