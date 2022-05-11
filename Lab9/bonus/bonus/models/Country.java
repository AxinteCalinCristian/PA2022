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
@Table(name="countries")
@NamedQueries({
	 @NamedQuery(name = "Country.findAll",
	 query = "select e from Country e order by e.name"),
	 @NamedQuery(name = "Country.findByName",
	 query = "select e from Country e where e.name=:name")
	})
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@NonNull
	@Column(name = "name")
	private String name;
	
	@NonNull
	@Column(name = "code")
	private String code;
	
	@NonNull
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "continent", referencedColumnName = "id")
	private Continent continent;
	
	@Override
	public String toString() {
		return id + ": " + name + "(code: "+ code + ", continent: " + continent.getName() + ")";
	}
}
