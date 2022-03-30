package utils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @JsonSerialize
public class Catalog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Set<Item> items = new HashSet<>();
	
	 public void addItem(Item item) {
		 items.add(item);
	 }
	 
	 /**
	  * Searches for item by id and returns if it exists
	  * @param id
	  * @return the item with the specified id or null
	  */
	 public Item findById(String id) {
		 Item res = null;
		 try {
			 res = items.stream().filter(i -> i.getId().compareTo(id) == 0).toList().get(0);
		 }
		 catch(Exception e) {
			e.printStackTrace();
		 }
		 return res;
	 }
	 
	 @Override
	public String toString() {
		 StringBuilder str = new StringBuilder();
		 for(Item item : items) {
			 str.append(item.toString() + " ");
		 }
		 return str.toString();
	}
	
}
