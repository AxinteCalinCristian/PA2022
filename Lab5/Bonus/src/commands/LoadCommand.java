package commands;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.InvalidCatalogException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.Book;
import utils.Catalog;
import utils.Magazine;

/**
 * Command that loads a catalog from a JSON file
 * @author Calin Axinte
 *
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class LoadCommand implements Command<Catalog>{
	
	private String path;
	
	@SuppressWarnings("unchecked")
	@Override
	public Catalog execute() throws InvalidCatalogException {
		Catalog catalog = null;
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    catalog = new Catalog();
		    Map<String, Object> map = new HashMap<>();
		    map = mapper.readValue(Paths.get(path).toFile(), Map.class);
		    catalog.setName((String) map.get("name"));
		    List<Map<String, Object>> its = (List<Map<String, Object>>) map.get("items");
		    for(Map<String, Object> itm : its) {
		    	if(((String) itm.get("type")).equals("Magazine")) {
		    		catalog.addItem(new Magazine((String) itm.get("id"),(String) itm.get("title"),(String) itm.get("location"),(String) itm.get("author"), (String) itm.get("year"), (List<String>) itm.get("keywords")));
		    	} else if(((String) itm.get("type")).equals("Book")) {
		    		catalog.addItem(new Book((String) itm.get("id"),(String) itm.get("title"),(String) itm.get("location"), (String) itm.get("author"), (String) itm.get("year"), (List<String>) itm.get("keywords")));
		    	}
		    }
		} catch (Exception ex) {
			throw new InvalidCatalogException(ex);
		}
		return catalog;
	}

}
