package utils;

public class Router extends Node implements Identifiable{
	private String address;
	
	/**
	 * Default constructor, initializes an empty router
	 */
	public Router() {
		this.name = "";
		this.address = "";
	}
	
	/**
	 * Creates a router with the given specifications
	 * @param name
	 * @param address
	 */
	public Router(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}
	
}
