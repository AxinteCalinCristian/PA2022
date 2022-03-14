package utils;

public class Pair<T, K> {
	private T first;
	private K second;
	
	public Pair(T first, K second)
	{
		this.first = first;
		this.second = second;
	}
	
	public T first()
	{
		return this.first;
	}
	
	public K second()
	{
		return this.second;
	}
	
	public void setFirst(T value)
	{
		this.first = value;
	}
	
	public void setSecond(K value)
	{
		this.second = value;
	}
}