package commands;

import java.util.Set;

import utils.Catalog;
import utils.Item;

/**
 * Command that lists all the items in a catalog
 * @author Calin Axinte
 *
 */
public class ListCommand implements Command<Catalog>{
	
	private Catalog catalog;
	
	@Override
	public Catalog execute() {
		if(catalog == null) {
			return catalog;
		}
		
		Set<Item> items = catalog.getItems();
		System.out.println("Items in catalog " + catalog.getName() + ":");
		for(Item i : items) {
			System.out.println(i.toString());
		}
		
		return catalog;
	}
	
}
