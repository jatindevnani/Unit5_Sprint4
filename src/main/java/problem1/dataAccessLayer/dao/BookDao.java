package problem1.dataAccessLayer.dao;

import problem1.dataAccessLayer.beans.*;
import java.util.ArrayList;

import antlr.collections.List;
import problem1.dataAccessLayer.exceptions.BookNotFoundException;

public interface BookDao {
	public ArrayList<Book> getAllBooks();
	public Book getBookById(int id) throws BookNotFoundException;
	public boolean createBook(Book book);
	public boolean deleteBook(int id) throws BookNotFoundException;
	public boolean updateBookPrice(int id, int newPrice) throws BookNotFoundException;
	
}
