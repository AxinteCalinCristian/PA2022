package main;

import java.io.IOException;

import commands.InfoCommand;
import commands.LoadCommand;
import commands.ReportCommand;
import commands.SaveCommand;
import commands.ViewCommand;
import exceptions.InvalidCatalogException;
import exceptions.InvalidDateException;
import utils.*;

public class Main {
	public static void main(String[] args) {
		Catalog catalog = new Catalog();
		catalog.setName("Test catalog");
		try {
			catalog.addItem(new Magazine("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", "Donald E. Knuth", "1967"));
			catalog.addItem(new Book("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", "James Gosling & others", "2021"));
			SaveCommand scmd = new SaveCommand(catalog, "./catalog_test.json");
			scmd.execute();
			
			LoadCommand lcmd = new LoadCommand("./catalog_test.json");
			catalog = lcmd.execute();
			System.out.println(catalog.toString());
			
			ViewCommand vcmd = new ViewCommand("./catalog_test.json");
			vcmd.execute();
			
			ReportCommand rcmd = new ReportCommand(catalog, "./catalog_test.html");
			rcmd.execute();
			
			InfoCommand icmd = new InfoCommand("./catalog_test.html");
			icmd.execute();
			
		} catch (InvalidDateException | InvalidCatalogException | IOException e) {
			e.printStackTrace();
		}

	}
}