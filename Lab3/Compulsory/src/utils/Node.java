package utils;

public abstract class Node {
	protected String name;
	
	/**
	 * @param name the node's name will be set to this
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the node's name
	 */
	public String getName() {
		return this.name;
	}
}
