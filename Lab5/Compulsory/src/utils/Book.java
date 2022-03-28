package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Book extends Item{
	private static final long serialVersionUID = 1L;
	private String type = "Book";
	private String author = null;
	
	public Book(String id, String title, String location, String author) {
		super(id, title, location);
		this.author = author;
	}
	
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
}
