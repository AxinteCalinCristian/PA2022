package commands;

import java.io.IOException;

import exceptions.InvalidCatalogException;
/**
 * Default class for a Command
 * @author Calin Axinte
 *	
 * @param <T>
 */
public interface Command<T> {
	T execute() throws InvalidCatalogException, IOException;
}
