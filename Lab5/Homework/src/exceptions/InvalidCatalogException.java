package exceptions;

public class InvalidCatalogException extends Exception {
	private static final long serialVersionUID = -2914829287090024845L;

	public InvalidCatalogException(Exception ex) {
		 super("Invalid catalog file.", ex);
	}
}
