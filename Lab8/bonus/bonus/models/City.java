package bonus.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class City {
	private Integer id;
	private String name;
	private String country;
	private Boolean capital;
	private String latitude;
	private String longitude;
}
