package utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public abstract class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String id = null;
	protected String title = null;
	protected String location = null;
	protected String type = null;
	protected String author = null;
	protected String year = null;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("serialVersionUID: " + serialVersionUID);
		if(id != null) {
			str.append(", id: " + id);
		}
		if(title !=  null) {
			str.append(", title: " + title);
		}
		if(location != null) {
			str.append(", location: " + location);
		}
		return str.toString();
	}
}
