package utils;

import java.time.*;

public class Event {
	private String name;
	private Integer size;
	private LocalTime start_time;
	private LocalTime end_time;
	
	public Event() {
		this.name = "";
		this.size = 0;
		this.start_time = LocalTime.MIN;
		this.end_time = LocalTime.MIN;
	}
	
	public Event(String name, Integer size, LocalTime start_time, LocalTime end_time) {
		this.name = name;
		this.size = size;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	
	/**
	 * Firstly checks if the object types match. Then it checks if the events have the same name, size, start and end times, in which case returns true. Otherwise returns false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }

        if (!(obj instanceof Event)) {
            return false;
        }
        
        Event e = (Event) obj;
        
		return (this.name.equals(e.getName()) && this.size.equals(e.getSize()) &&
				this.start_time.equals(e.getStartTime()) && this.end_time.equals(e.getEndTime()));
	}
	
	@Override
	public String toString() {
		return this.name + "(size="+ this.size.toString() + ", start=" + this.start_time.toString() + ", end=" + this.end_time.toString() + ")";
	}
	
	/**
	 * @return the event name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name Sets the event name to this.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the event start time
	 */
	public LocalTime getStartTime() {
		return start_time;
	}
	
	/**
	 * @param start_time Sets the event start time to this.
	 */
	public void setStartTime(LocalTime start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * @return the event size
	 */
	public Integer getSize() {
		return size;
	}
	
	/**
	 * @param size Sets the event size to this.
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
	
	/**
	 * @return the event end time
	 */
	public LocalTime getEndTime() {
		return end_time;
	}
	
	/**
	 * @param end_time Sets the event end time to this.
	 */
	public void setEndTime(LocalTime end_time) {
		this.end_time = end_time;
	}
	
}
