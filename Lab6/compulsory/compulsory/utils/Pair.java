package compulsory.utils;

public class Pair<T, K> implements Cloneable{
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
	
	public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}