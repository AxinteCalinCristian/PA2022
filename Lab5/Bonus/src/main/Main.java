package main;

import commands.InfoCommand;
import commands.LoadCommand;
import commands.ReportCommand;
import commands.SaveCommand;
import commands.ViewCommand;
import exceptions.InvalidCatalogException;

import utils.*;

public class Main {
	public static void main(String[] args) {
		Catalog catalog = null;
		try {
			catalog = RandomDataGenerator.generateCatalog();
			SaveCommand scmd = new SaveCommand(catalog, "./catalog_test.json");
			scmd.execute();
			
			LoadCommand lcmd = new LoadCommand("./catalog_test.json");
			catalog = lcmd.execute();
			System.out.println(catalog.toString());
			
			ViewCommand vcmd = new ViewCommand("./catalog_test.json");
			//vcmd.execute();
			
			ReportCommand rcmd = new ReportCommand(catalog, "./catalog_test.html");
			rcmd.execute();
			
			InfoCommand icmd = new InfoCommand("./catalog_test.html");
			//icmd.execute();
			HopcroftKarpAlgorithm.printSolution(catalog);
			
		} catch (InvalidCatalogException e) {
			e.printStackTrace();
		}

	}
}