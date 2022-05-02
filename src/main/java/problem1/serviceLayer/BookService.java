package problem1.serviceLayer;

import java.util.ArrayList;

import problem1.dataAccessLayer.beans.Book;
import problem1.dataAccessLayer.exceptions.BookNotFoundException;

public interface BookService {
	public ArrayList<Book> getAllBooks();
	public Book getBookById(int id) throws BookNotFoundException;
	public boolean createBook(Book book);
	public boolean deleteBook(int id) throws BookNotFoundException;
	public boolean updateBookPrice(int id, int newPrice) throws BookNotFoundException;
}
