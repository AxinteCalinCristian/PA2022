package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import utils.Computer;
import utils.Dijkstra;
import utils.Network;
import utils.Node;
import utils.Pair;
import utils.Router;
import utils.Switch;

public class test_3 {

	@Test
	public void test() {
		Network network = new Network();
		
		Node a = new Computer("1", 200, "00:00:5e:00:53:af");
		Node b = new Router("2", "00:00:1f:10:67:ff");
		Node c = new Switch("3", false);
		Node d = new Computer("4", 500, "00:00:8a:00:65:ee");
		Node e = new Router("5", "00:00:1f:ab:1f:00");
		Node f = new Switch("6", true);
		Node g = new Router("7", "00:00:a6:ff:1b:01");
		Node h = new Computer("8", 350, "00:00:ab:ff:11:b8");
		Node i = new Switch("9", false);
		
		a.addNeighbor(b, 10, 0.15);
		a.addNeighbor(c, 50, 0.1);
		b.addNeighbor(c, 20, 0.2);
		b.addNeighbor(d, 20, 0.3);
		b.addNeighbor(g, 35, 0.1);
		b.addNeighbor(e, 20, 0.1);
		c.addNeighbor(d, 10, 0.2);
		d.addNeighbor(e, 30, 0.35);
		d.addNeighbor(f, 10, 0.15);
		d.addNeighbor(g, 10, 0.3);
		d.addNeighbor(h, 15, 0.4);
		e.addNeighbor(f, 20, 0.25);
		e.addNeighbor(g, 30, 0.35);
		e.addNeighbor(h, 50, 0.4);
		h.addNeighbor(i, 20, 0.25);
		e.addNeighbor(i, 25, 0.6);
		
		network.addNode(a);
		network.addNode(b);
		network.addNode(c);
		network.addNode(d);
		network.addNode(e);
		network.addNode(f);
		network.addNode(g);
		network.addNode(h);
		network.addNode(i);
		
		Dijkstra dj = new Dijkstra(network);
		Pair<Double, ArrayList<Node>> result = dj.printSolution(b, i);
		
		assertEquals("40.50", String.format("%.2f", result.first() * 100.));
		ArrayList<Node> correct_arr = new ArrayList<>();
		
		correct_arr.add(b);
		correct_arr.add(e);
		correct_arr.add(h);
		correct_arr.add(i);
		assertEquals(result.second(), correct_arr);
	}

}
