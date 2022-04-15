package compulsory.utils;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Tile {
	private Character letter;
	private Integer points;
	
	@Override
	public String toString() {
		return letter.toString() + "-" + points.toString();
	}
}
