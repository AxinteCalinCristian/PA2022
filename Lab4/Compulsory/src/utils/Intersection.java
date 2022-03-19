package utils;

public class Intersection {
	private String name;
	
	/**
	 * Creates a new intersection with the given name
	 * @param name
	 */
	public Intersection(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }

        if (!(obj instanceof Intersection)) {
            return false;
        }
        
        Intersection i = (Intersection) obj;
        
        if(this.name.equals(i.getName()))
        {
        	return true;
        }
        
        return false;
	}
}
