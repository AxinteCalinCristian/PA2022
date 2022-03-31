package commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;

import exceptions.InvalidCatalogException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class InfoCommand implements Command<Map<String, String>>{
	
	private String path;
	
	@Override
	public Map<String, String> execute() throws InvalidCatalogException, IOException {
		if(path == null) {
			return null;
		}
		Map<String, String> metadata_map = new HashMap<>();
		File initialFile = new File(path);
	    InputStream stream = new FileInputStream(initialFile);
	    Tika tika = new Tika();
	    Metadata metadata = new Metadata();
	    tika.parse(stream, metadata);
	    for(String name : metadata.names()) {
	    	System.out.println("[ " + name + " ]: [ " + metadata.get(name)+" ]");
	    	metadata_map.put(name, metadata.get(name));
	    }
		stream.close();
		return metadata_map;
	}
}
