package test.service;

import ioc.IoCConfigurer;
import ioc.IoCContainer;
import ioc.IoCException;

import java.util.List;

import service.BookService;
import test.Utility;
import domain.Book;

public class BookServiceFindByTitleTest {
	public static void main(String[] args) throws IoCException {
		IoCConfigurer.configure();
		IoCContainer ioc = new IoCContainer();
		BookService bookService = ioc.get(BookService.class);
		List<Book> books = bookService.findByTitle("книга");
		System.out.println("Список всех книг, содержащих в названии строку «книга»");
		System.out.println("======================================================");
		for(Book book : books) {
			System.out.println(Utility.toString(book));
		}
	}
}
