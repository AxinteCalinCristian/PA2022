package utils;

public class Vertex implements Comparable<Vertex>{
	private Integer saturation;
	private Integer degree;
	private Event event;
	
	Vertex(Integer saturation, Integer degree, Event event)
	{
		this.saturation = saturation;
		this.degree = degree;
		this.event = event;
	}
	
	@Override
	public int compareTo(Vertex o) {
		if(this.saturation == o.getSaturation()){
			if(this.degree > o.getDegree()){
				return -1;
			} else if (this.degree < o.getDegree()) {
				return 1;
			}
			else return 0;
		}
		else if(this.saturation >  o.getSaturation()){
			return -1;
		}
		else{
			return 1;
		}
	}
	
	public Integer getSaturation() {
		return saturation;
	}
	public void setSaturation(Integer saturation) {
		this.saturation = saturation;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
}
