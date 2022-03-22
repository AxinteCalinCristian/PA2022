package utils;

public class Street implements Comparable<Street>{
	private String name;
	private Integer length;
	private Pair<Intersection, Intersection> adjacent_intersections;
	
	/**
	 * Takes a name, a length and two intersections to create a street
	 * @param name
	 * @param length
	 * @param first_intersection
	 * @param second_intersection
	 */
	public Street(String name, Integer length, Intersection first_intersection, Intersection second_intersection) {
		this.name = name;
		this.length = length;
		this.adjacent_intersections =new Pair<Intersection, Intersection>(first_intersection, second_intersection);
		first_intersection.addAdjacentStreet(this);
		second_intersection.addAdjacentStreet(this);
	}
	
	/**
	 * Takes a name, a length, and a Pair of intersections to create a street
	 * @param name
	 * @param length
	 * @param adjacent_intersections
	 */
	public Street(String name, Integer length, Pair<Intersection, Intersection> adjacent_intersections) {
		this.name = name;
		this.length = length;
		this.adjacent_intersections = adjacent_intersections;
		adjacent_intersections.first().addAdjacentStreet(this);
		adjacent_intersections.second().addAdjacentStreet(this);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * @return The two intersections adjacent to the street
	 */
	@SuppressWarnings("unchecked")
	public Pair<Intersection, Intersection> getAdjacent_intersections() {
		try {
			return (Pair<Intersection, Intersection>) adjacent_intersections.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }

        if (!(obj instanceof Street)) {
            return false;
        }
        
        Street s = (Street) obj;
        
        if(this.name.equals(s.getName()) && this.length == s.getLength() 
           && ((this.adjacent_intersections.first().equals(s.getAdjacent_intersections().first())
           && this.adjacent_intersections.second().equals(s.getAdjacent_intersections().second())) || 
        	(this.adjacent_intersections.first().equals(s.getAdjacent_intersections().second())
        	 && this.adjacent_intersections.second().equals(s.getAdjacent_intersections().first()))))
        {
        	return true;
        }
        
        return false;
	}
	
	@Override
	public int compareTo(Street o) {
		if(o == null)
		{
			return -1;
		}
		
		if(this.equals(o))
		{
			return 0;
		}
		
		else if(this.length < o.getLength() || this.length == o.getLength() && this.name.compareTo(o.getName()) < 0)
		{
			return -1;
		}
		
		return 1;
	}
}
