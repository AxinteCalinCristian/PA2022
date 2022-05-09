package homework.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="continents")
@NamedQueries({
	 @NamedQuery(name = "Continent.findAll",
	 query = "select e from Continent e order by e.name"),
	 @NamedQuery(name = "Continent.findByName",
	 query = "select e from Continent e where e.name=:name")
	})

public class Continent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@NonNull
	@Column(name = "name")
	private String name;
	
	@Override
	public String toString() {
		return id + ": " + name;
	}
}
