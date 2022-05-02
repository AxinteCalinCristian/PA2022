package homework.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Country {
	private Integer id;
	private String name;
	private String code;
	private String continent;
	
	@Override
	public String toString() {
		return id + ": " + name + "("+ code + ")" + " -> " + continent;
	}
}
