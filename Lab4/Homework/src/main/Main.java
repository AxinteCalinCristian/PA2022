package main;

import java.util.List;

import utils.*;

public class Main {
	public static void main(String[] args) {
			RandomDataGenerator rand = new RandomDataGenerator();
			City city = new City();
			city = rand.generateIntersections(city, 20);
			city = rand.generateStreets(city, 50);
			
			//city.printCity();
			List<Street> str = city.getStreetsWithLength(10);
			
			for(Street s : str) {
				System.out.print("[ " + s.getName() + "] ");
			}
			
			PrimAlgorithm pa = new PrimAlgorithm();
			pa.setCity(city);
			pa.printSolution();
		}
}