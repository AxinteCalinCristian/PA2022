package commands;

import java.io.IOException;

import exceptions.InvalidCatalogException;

public interface Command<T> {
	T execute() throws InvalidCatalogException, IOException;
}
