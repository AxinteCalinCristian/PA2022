package pa_lab1_hw;

import utils.Language;
import utils.NeighborsManager;

/**
 * Homework and Bonus for PA Laboratory 1
 * @author Calin Axinte
 */

public class Main 
{	
	public static void main(String[] args)
	{
		// for calculating time and asserting whether to print or not the adjacency list
		final int size_limit = 1000;
		final long startTime = System.nanoTime();
		
		// creating the language based on the provided arguments,
		// as well as populating the adjacency containers
		Language lang = new Language(args);
		
		System.out.println("Language : " + lang.getLanguage());
		
		NeighborsManager neighbors = new NeighborsManager(lang);
		
		if(Integer.parseInt(args[0]) <= size_limit)
		{
			neighbors.printAdjacencyList();
			System.out.println("\nAll cyclic subsets in the current language");
			System.out.println(neighbors.getAllCyclicSubsets());
			
			System.out.println("\nThe largest cyclic subset in the current language");
			System.out.println(neighbors.getLargestCyclicSubset());
		}
		
		System.out.println("Duration (ns): " + (System.nanoTime() - startTime));
	}
}
