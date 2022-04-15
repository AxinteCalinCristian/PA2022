package homework.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonSerialize
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
	
	public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}