package utils;

public class Lab extends Room{
	
	private String running_os;
	
	public Lab()
	{
		super("", 0);
		this.running_os = "";
	}
	
	public Lab(String name, Integer capacity, String running_os)
	{
		super(name, capacity);
		this.running_os = running_os;
	}
	
	@Override
	public String toString() {
		return this.name + "(cap="+ this.capacity.toString() + ", running OS=" + this.running_os + ", Lab)";
	}

	/**
	 * @return the lab running OS
	 */
	public String getRunningOS() {
		return running_os;
	}
	
	/**
	 * @param running_os Sets the lab running OS to this.
	 */
	public void setRunningOS(String running_os) {
		this.running_os = running_os;
	}
}
