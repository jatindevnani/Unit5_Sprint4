package problem1.serviceLayer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import problem1.dataAccessLayer.beans.Book;
import problem1.dataAccessLayer.dao.BookDaoImpl;
import problem1.dataAccessLayer.exceptions.BookNotFoundException;



@Service
public class ServiceImpl implements BookService{
	
	@Autowired
	private BookDaoImpl dao;

	@Override
	public ArrayList<Book> getAllBooks() {
		// TODO Auto-generated method stub
		ArrayList<Book> listOfBooks = (ArrayList<Book>) dao.getAllBooks();
		System.out.println("Details Fetched!");
		return listOfBooks;
		
	}

	@Override
	public Book getBookById(int id) throws BookNotFoundException {
	
				Book book = null;
				try {
					book = dao.getBookById(id);
				} catch (BookNotFoundException e) {
					e.printStackTrace();
				}
				return book;

	}

	@Override
	public boolean createBook(Book book) {
		boolean result = dao.createBook(book);
		
		if(result) {
			System.out.println("Book details added successfully");
		} else {
			System.out.println("Book details not added :(");
		}
		
		return result;
	}

	@Override
	public boolean deleteBook(int id) throws BookNotFoundException {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = dao.deleteBook(id);
		} catch (BookNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateBookPrice(int id, int newPrice) throws BookNotFoundException {
		boolean result = false;
		
		try {
			result = dao.updateBookPrice(id, newPrice);
		} catch (BookNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
