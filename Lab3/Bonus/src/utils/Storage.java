package utils;

public interface Storage {
	
	/**
	 * @param capacity Sets the capacity to this
	 */
	public void setStorageCapacity(int capacity);
	
	/**
	 * @return The capacity
	 */
	public int getStorageCapacity();
	
	public default long convertCapacity(StorageUnit unit) {
		long capacity = this.getStorageCapacity();
		
		if(unit == StorageUnit.MEGABYTE)
		{
			return capacity * 1_000;
		}
		
		if(unit == StorageUnit.KILOBYTE)
		{
			return capacity * 1_000_000;
		}
		
		if(unit == StorageUnit.BYTE)
		{
			return capacity * 1_000_000_000;
		}
		
		return capacity;
	}
}
