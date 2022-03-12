package utils;

public class Computer extends Node implements Identifiable, Storage{
	private int storage_capacity;
	private String address;
	
	/**
	 * Default constructor, initializes an empty computer
	 */
	public Computer() {
		this.storage_capacity = 0;
		this.address = "";
		this.setName("");
	}
	
	/**
	 * Creates a computer with the given specifications
	 * @param name
	 * @param storage_capacity
	 * @param address
	 */
	public Computer(String name, int storage_capacity, String address) {
		this.name = name;
		this.storage_capacity = storage_capacity;
		this.address = address;
	}
	
	@Override
	public int getStorageCapacity() {
		return this.storage_capacity;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public void setStorageCapacity(int capacity) {
		this.storage_capacity = capacity;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}
	
}
