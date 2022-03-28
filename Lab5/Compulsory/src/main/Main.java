package main;

import java.util.Arrays;

import utils.*;

public class Main {
	public static void main(String[] args) {
		Catalog catalog = new Catalog();
		catalog.setName("catalog_test");
		catalog.addItem(new Magazine("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", "Donald E. Knuth"));
		catalog.addItem(new Book("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", "James Gosling & others"));
		
		catalog.saveToFile("./catalog_test.json");
		catalog.loadFromFile("./catalog_test.json");
		
		System.out.println(catalog.toString());
	}
}