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
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sister_cities")
@NamedQueries({
	 @NamedQuery(name = "SisterRelationship.findAll",
	 query = "select e from SisterRelationship e order by e.id"),
	 @NamedQuery(name = "SisterRelationship.findByPair",
	 query = "select e from SisterRelationship e where (e.firstCity.id=:city_a and e.secondCity.id=:city_b) or (e.firstCity.id=:city_b and e.secondCity.id=:city_a)")
	})
public class SisterRelationship {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@NonNull
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "first_city", referencedColumnName = "id")
	private City firstCity;
	
	@NonNull
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "second_city", referencedColumnName = "id")
	private City secondCity;
	
	@Override
	public String toString() {
		return id + ": [ " + firstCity.getName() + " -- " + secondCity.getName() + " ]";
	}
}
