package bonus.repositories;

import java.util.List;

public interface Repository<T> {
	public boolean addEntry(T entry);
	public T getById(Integer id);
	List<T> getByName(String name);
	public List<T> getAll();
}
