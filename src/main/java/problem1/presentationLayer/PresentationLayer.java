package problem1.presentationLayer;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import com.google.protobuf.Service;

import problem1.dataAccessLayer.beans.Book;
import problem1.dataAccessLayer.dao.BookDao;
import problem1.dataAccessLayer.dao.BookDaoImpl;
import problem1.dataAccessLayer.exceptions.BookNotFoundException;
import problem1.serviceLayer.ServiceImpl;
import problem1.AppConfig;


@Controller
public class PresentationLayer {
	
	@Autowired
	private ServiceImpl serviceLayer;
	
	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		PresentationLayer presentation = context.getBean(PresentationLayer.class);
		
		
		//Add books
		presentation.addBook();
		presentation.addBook();
		
		//Get by id
		presentation.getBookById();
		
		//update price
		presentation.updateBook();
		
		//delete book
		presentation.deleteBook();
		
		//Show all books
		presentation.getAllBooks();
		
		
	}
	
	public void getAllBooks() {
		ArrayList<Book> books = (ArrayList<Book>) serviceLayer.getAllBooks();
		
		for(Book temp: books) {
			System.out.println(temp);
		}
		
	}
	
	public void getBookById() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("give the id of the book you want to fetch - ");
		int id = scanner.nextInt();
		Book book3 = null;
		try {
			book3 = serviceLayer.getBookById(id);
			
		} catch (BookNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(book3);
	}
	
	public void updateBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the ID of the book whose price you want to update - ");
		int idToUpdate = scanner.nextInt();
		
		System.out.println("Enter the new price you want to set");
		int priceToSet = scanner.nextInt();
		
		boolean result = serviceLayer.updateBookPrice(idToUpdate, priceToSet);
		if(result) {
			System.out.println("Book updated successfully");
		} else {
			System.out.println("Book not updated");
		}
	}
	
	public void deleteBook() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Give the ID of the Book you want to delete - ");
		int idToDelete = scanner.nextInt();
		try {
			serviceLayer.deleteBook(idToDelete);
			System.out.println("Deleted successfully!");
		} catch (BookNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Give the name of the Book you want to add: ");
		String name = scanner.next();
		System.out.println("Give the Author name: ");
		String author = scanner.next();
		System.out.println("Give the Price of the book: ");
		int price = scanner.nextInt();
		
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setPrice(price);
		
		serviceLayer.createBook(book);
		}
}	
