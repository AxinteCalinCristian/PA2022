package exceptions;

public class InvalidDateException extends Exception {
	private static final long serialVersionUID = -2174712277560959343L;

	public InvalidDateException(Exception ex) {
		 super("Invalid date.", ex);
	}
}
