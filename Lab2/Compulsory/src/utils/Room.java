package utils;

public class Room {
	private String name;
	private Integer capacity;
	private RoomType type;
	
	public Room()
	{
		this.name = "";
		this.capacity = 0;
		this.type = RoomType.None;
	}
	
	public Room(String name, Integer capacity, RoomType type)
	{
		this.name = name;
		this.capacity = capacity;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.name + "(cap="+ this.capacity.toString() + ", " + this.type.toString() + ")";
	}
	
	public RoomType getType() {
		return type;
	}
	public void setType(RoomType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}
