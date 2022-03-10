package utils;

public abstract class Room {
	protected String name;
	protected Integer capacity;
	
	public Room()
	{
		this.name = "";
		this.capacity = 0;
	}
	
	public Room(String name, Integer capacity)
	{
		this.name = name;
		this.capacity = capacity;
	}
	
	/**
	 * Firstly checks if the object types match. Then it checks if the rooms have the same name and capacity, in which case returns true. Otherwise returns false.
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }

        if (!(obj instanceof Room)) {
            return false;
        }
        
        Room r = (Room) obj;
        
		return (this.name.equals(r.getName()) && this.capacity.equals(r.getCapacity()));
	}
	
	/**
	 * @return the room name
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * @param name Sets the room name to this.
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the room capacity
	 */
	
	public Integer getCapacity() {
		return capacity;
	}
	
	/**
	 * @param capacity Sets the room capacity to this.
	 */
	
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}
