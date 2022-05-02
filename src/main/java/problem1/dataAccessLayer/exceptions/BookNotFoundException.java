package problem1.dataAccessLayer.exceptions;

public class BookNotFoundException extends RuntimeException{
	public BookNotFoundException (String message) {
		super(message);
	}
}
