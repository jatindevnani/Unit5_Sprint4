package problem1.dataAccessLayer.dao;

import problem1.dataAccessLayer.beans.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import antlr.collections.List;
import problem1.dataAccessLayer.exceptions.BookNotFoundException;
import problem1.dataAccessLayer.utilites.EMUtil;


@Repository
public class BookDaoImpl implements BookDao{

	@Override
	public ArrayList<Book> getAllBooks() {
		// TODO Auto-generated method stub
		EntityManager em = EMUtil.getEntityManager();
		
		String jpql = "from Book";
		TypedQuery<Book> query = em.createQuery(jpql, Book.class);
		
	
		ArrayList<Book> listOfBooks = (ArrayList<Book>) query.getResultList();
		
		
		return listOfBooks;
	}

	@Override
	public Book getBookById(int id) throws BookNotFoundException {
		EntityManager em = EMUtil.getEntityManager();
		String jpql = "from Book WHERE id=:idd";
		
		Book result = null;
		
		try {
			TypedQuery<Book> query = em.createQuery(jpql, Book.class);
			query.setParameter("idd", id);
			result = query.getSingleResult();
		} catch (Exception e) {
			throw new BookNotFoundException("Book not found");
		}

		return result;
	}

	@Override
	public boolean createBook(Book book) {
		// TODO Auto-generated method stub
		EntityManager em = EMUtil.getEntityManager();
		
		boolean isSuccess = false;
		
		try {
			em.getTransaction().begin();
			em.persist(book);
			em.getTransaction().commit();
			isSuccess = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public boolean deleteBook(int id) throws BookNotFoundException {
		EntityManager em = EMUtil.getEntityManager();
		boolean isSuccess = false;
		
		String jpql = "delete from Book WHERE id=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		
		try {
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
		isSuccess = true;
		} catch (Exception e) {
			throw new BookNotFoundException("Book not available");
		}
		
		return isSuccess;
	}

	@Override
	public boolean updateBookPrice(int id, int newPrice) throws BookNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em = EMUtil.getEntityManager();
		Book book = em.find(Book.class, id);
		
		boolean flag = false;
		
		if(book == null) {
			throw new BookNotFoundException("Book not found");
		} else {
			em.getTransaction().begin();
			book.setPrice(newPrice);
			em.getTransaction().commit();
			flag = true;
		}
		
		return flag;
	}

}
