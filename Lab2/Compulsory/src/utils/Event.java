package utils;

public class Event {
	private String name;
	private Integer size;
	private Integer start_time;
	private Integer end_time;
	
	public Event() {
		this.name = "";
		this.size = 0;
		this.start_time = 0;
		this.end_time = 0;
	}
	
	public Event(String name, Integer size, Integer start_time, Integer end_time) {
		this.name = name;
		this.size = size;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	
	@Override
	public String toString() {
		return this.name + "(size="+ this.size.toString() + ", start=" + this.start_time.toString() + ", end=" + this.end_time.toString() + ")";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStartTime() {
		return start_time;
	}
	public void setStartTime(Integer start_time) {
		this.start_time = start_time;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getEndTime() {
		return end_time;
	}
	public void setEndTime(Integer end_time) {
		this.end_time = end_time;
	}
	
}
