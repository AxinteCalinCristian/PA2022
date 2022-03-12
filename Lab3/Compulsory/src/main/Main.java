package main;

import utils.*;

public class Main {
	public static void main(String[] args) {
		Network network = new Network();
		
		network.addNode(new Computer("Computer A", 200, "00:00:5e:00:53:af"));
		network.addNode(new Router("Router A", "00:00:1f:10:67:ff"));
		network.addNode(new Switch("Switch A", true));
		network.addNode(new Computer("Computer B", 500, "00:00:8a:00:65:ee"));
		network.addNode(new Router("Router B", "00:00:1f:ab:1f:00"));
		network.addNode(new Switch("Switch B", false));
		
		network.printNodes();
	}
}
