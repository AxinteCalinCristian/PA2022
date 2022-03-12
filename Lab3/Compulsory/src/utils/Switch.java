package utils;

public class Switch extends Node{
	private Boolean state;
	
	/**
	 * Default constructor, initializes an empty switch
	 */
	public Switch() {
		this.name = "";
		this.state = false;
	}
	
	/**
	 * Creates a switch with the given specifications
	 * @param name
	 * @param state
	 */
	public Switch(String name, Boolean state) {
		this.name = name;
		this.state = state;
	}
	
	/**
	 * Toggles the current state of the switch
	 */
	public void toggleSwitch() {
		this.state = !this.state;
	}
	
	/**
	 * @param state sets the switch's state to this
	 */
	public void setState(Boolean state) {
		this.state = state;
	}
	
	/**
	 * @return the current state of the switch
	 */
	public boolean getState() {
		return this.state;
	}
}
