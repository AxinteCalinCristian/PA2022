package utils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	/**
	 * Saves the catalog to path
	 * @param path
	 */
	public void saveToFile(String path) {
		ObjectMapper mapper = new ObjectMapper();
	    try {
	    	File file = new File(path);
	    	file.createNewFile();
			mapper.writeValue(Paths.get(path).toFile(), this);
		} catch (IOException e) {
			System.out.println("Couldn't save catalog to path " + path);
			e.printStackTrace();
		}
	}
	
	public Catalog loadFromFile(String path) {
		Catalog catalog = null;
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    catalog = new Catalog();
		    Map<String, Object> map = new HashMap<>();
		    map = mapper.readValue(Paths.get(path).toFile(), Map.class);
		    this.name = (String) map.get("name");
		    this.items.clear();
		    List<Map<String, Object>> its = (List<Map<String, Object>>) map.get("items");
		    for(Map<String, Object> itm : its) {
		    	if(((String) itm.get("type")).equals("Magazine")) {
		    		this.items.add(new Magazine((String) itm.get("id"),(String) itm.get("title"),(String) itm.get("location"),(String) itm.get("author")));
		    	} else if(((String) itm.get("type")).equals("Book")) {
		    		this.items.add(new Book((String) itm.get("id"),(String) itm.get("title"),(String) itm.get("location"), (String) itm.get("author")));
		    	}
		    }
		    catalog.setItems(items);
		    catalog.setName(name);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return catalog;
	}
}
