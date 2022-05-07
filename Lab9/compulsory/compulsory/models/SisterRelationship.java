package compulsory.models;

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
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="sister_cities")
@NamedQueries({
	 @NamedQuery(name = "SisterRelationship.findAll",
	 query = "select e from SisterRelationship e order by e.id"),
	 @NamedQuery(name = "SisterRelationship.findByPair",
	 query = "select e from SisterRelationship e where (e.firstCityId=:city_a and e.secondCityId=:city_b) or (e.firstCityId=:city_b and e.secondCityId=:city_a)")
	})
public class SisterRelationship {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	Integer id;
	@Column(name = "first_city")
	Integer firstCityId;
	@Column(name = "second_city")
	Integer secondCityId;
}
