package bonus.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cities")
@NamedQueries({
	 @NamedQuery(name = "City.findAll",
	 query = "select e from City e order by e.name"),
	 @NamedQuery(name = "City.findByName",
	 query = "select e from City e where e.name=:name")
	})

public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@NonNull
	@Column(name = "name")
	private String name;
	
	@NonNull
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "country", referencedColumnName = "id")
	private Country country;
	
	@NonNull
	@Column(name = "capital")
	private Boolean capital;
	
	@NonNull
	@Column(name = "latitude")
	private String latitude;
	
	@NonNull
	@Column(name = "longitude")
	private String longitude;
	
	@NonNull
	@Column(name = "population")
	private Integer population;
	
	@Override
	public String toString() {
		return id + ": " + name + "(country: " + country.getName() + ", capital(?): " +
				capital.toString() + ", latitude: " + latitude + ", longitude: " + longitude + ", population: " + population + ")";
	}
}
