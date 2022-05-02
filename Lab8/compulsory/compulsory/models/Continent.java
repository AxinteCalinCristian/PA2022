package compulsory.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Continent {
	private Integer id;
	private String name;
	
	@Override
	public String toString() {
		return id + ": " + name;
	}
}
