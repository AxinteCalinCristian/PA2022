package utils;

import exceptions.InvalidDateException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Book extends Item{
	private static final long serialVersionUID = 1L;
	
	public Book(String id, String title, String location, String author, String year) throws InvalidDateException {
 		super(id, title, location, "Magazine", author, year);
 		if(year.compareTo("2022") > 0 || year.compareTo("0") < 0 || year.length() != 4) {
			throw new InvalidDateException(new Exception());
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString());
		if(author != null) {
			str.append(", author: " + author);
		}
		str.append(", type: " + this.type);
		return str.toString(); 
	}
	
}
