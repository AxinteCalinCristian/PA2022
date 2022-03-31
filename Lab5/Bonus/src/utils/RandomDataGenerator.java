package utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.github.javafaker.Faker;

import exceptions.InvalidDateException;

public abstract class RandomDataGenerator {
	
	public static Catalog generateCatalog()  {
		Catalog catalog = new Catalog();
		Faker f = new Faker();
		Random random = new Random();
		
		catalog.setName(f.book().publisher());
		Integer no_of_items = random.nextInt(5, 100);
		
		for(int i = 0; i < no_of_items; i++) {
			String year = ((Integer) random.nextInt(1400, 2022)).toString();
			Integer no_of_kws = random.nextInt(2, 20);
			Set<String> keywords = new HashSet<>();
			
			for(int j = 0; j < no_of_kws; j++) {
				keywords.add(f.lorem().word());
			}
			
			if(i % 2 == 0)
				try {
					catalog.addItem(new Magazine(f.idNumber().valid(), f.book().title(), f.file().fileName(), f.book().author(), year, new LinkedList<>(keywords)));
				} catch (InvalidDateException e) {
					e.printStackTrace();
				}
			else
				try {
					catalog.addItem(new Book(f.idNumber().valid(), f.book().title(), f.file().fileName(), f.book().author(), year, new LinkedList<>(keywords)));
				} catch (InvalidDateException e) {
					e.printStackTrace();
				}
		}
		
		return catalog;
	}
}
