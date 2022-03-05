package utils;

public class LectureHall extends Room{
	private Boolean hasProjector;
	
	public LectureHall()
	{
		super("", 0);
		this.hasProjector = false;
	}
	
	public LectureHall(String name, Integer capacity, Boolean hasProjector)
	{
		super(name, capacity);
		this.hasProjector = hasProjector;
	}
	
	@Override
	public String toString() {
		return this.name + "(cap="+ this.capacity.toString() + ", has projector=" + this.hasProjector.toString() + ", Lecture Hall)";
	}

	/**
	 * @return whether the lecture hall has a projector or not
	 */
	
	public Boolean getHasProjector() {
		return hasProjector;
	}
	
	/**
	 * @param hasProjector Sets whether the lecture hall has a projector or not to this.
	 */
	
	public void setHasProjector(Boolean hasProjector) {
		this.hasProjector = hasProjector;
	}
}
