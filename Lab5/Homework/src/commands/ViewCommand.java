package commands;

import java.awt.Desktop;
import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ViewCommand implements Command<String>{
	
	private String path;
	
	@Override
	public String execute() {
		if(path == null) {
			return null;
		}
		
		Desktop desktop = null;
		File file = null;
		try {
			desktop = Desktop.getDesktop();
			file = new File(path);
			desktop.open(file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return path;
	}

}
