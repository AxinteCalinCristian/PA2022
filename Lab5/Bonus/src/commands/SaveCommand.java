package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.Catalog;

/**
 * Command that saves a catalog to a given path
 * @author Calin Axinte
 *
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SaveCommand implements Command<Catalog>{
	
	private Catalog catalog;
	private String path;
	
	/**
	 * Saves the current catalog to the current path
	 */
	@Override
	public Catalog execute() {
		if(path == null || catalog == null) {
			return null;
		}
		
		ObjectMapper mapper = null;
	    try {
	    	mapper = new ObjectMapper();
	    	File file = new File(path);
	    	file.createNewFile();
			mapper.writeValue(Paths.get(path).toFile(), catalog);
		} catch (IOException e) {
			System.out.println("Couldn't save catalog " + catalog.getName() + " to path " + path);
			e.printStackTrace();
		}
		return catalog;
	}
	
}
