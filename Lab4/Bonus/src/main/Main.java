package main;

import utils.*;

public class Main {
	public static void main(String[] args) {
			RandomDataGenerator rand = new RandomDataGenerator();
			City city = rand.generateCity(20, 50);
			
			ChristophidesAlgorithm ca = new ChristophidesAlgorithm();
			ca.setCity(city);
			ca.printSolution();
		}
}