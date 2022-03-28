package utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public abstract class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id = null;
	private String title = null;
	private String location = null;

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
