package test.service;

import ioc.IoCConfigurer;
import ioc.IoCContainer;
import ioc.IoCException;
import pool.ConnectionPool;
import pool.PoolException;

import java.util.List;

import service.BookService;
import service.ServiceException;
import test.Utility;
import domain.Book;

public class BookServiceFindByTitleTest {
	private static void output(BookService bookService, String bookTitle) throws ServiceException {
		List<Book> books = bookService.findByTitle(bookTitle);
		System.out.printf("Список книг, содержащих в названии строку \"%s\"\n------------------------------------------------------\n", bookTitle);
		for(Book book : books) {
			System.out.println(Utility.toString(book));
		}
		System.out.println("======================================================");
	}

	public static void main(String[] args) throws IoCException, ServiceException, PoolException {
		ConnectionPool.getInstance().init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/soa_library_db?useUnicode=true&characterEncoding=UTF-8", "soa_library_user", "soa_library_password");
		IoCConfigurer.configure();
		IoCContainer ioc = new IoCContainer();
		BookService bookService = ioc.get(BookService.class);
		System.out.println("======================================================");
		output(bookService, "ночи");
		output(bookService, "братья");
		output(bookService, "и");
		output(bookService, "полк");
		output(bookService, "рота");
		ConnectionPool.getInstance().destroy();
	}
}
