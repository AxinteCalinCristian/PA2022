package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Magazine extends Item{
	private static final long serialVersionUID = 1L;
	private String type = "Magazine";
	private String author = null;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString());
		if(author != null) {
			str.append(", author: " + author);
		}
		str.append(", type: " + type);
		return str.toString(); 
	}

	public Magazine(String id, String title, String location, String author) {
		super(id, title, location);
		this.author = author;
	}
}
