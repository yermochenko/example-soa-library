package test.service;

import java.util.List;

import service.BookService;
import test.Utility;
import di.ServiceLocator;
import domain.Book;

public class BookServiceFindByTitleTest {
	public static void main(String[] args) {
		ServiceLocator locator = new ServiceLocator();
		BookService bookService = locator.getBookService();
		List<Book> books = bookService.findByTitle("книга");
		System.out.println("Список всех книг, содержащих в названии строку «книга»");
		System.out.println("======================================================");
		for(Book book : books) {
			System.out.println(Utility.toString(book));
		}
	}
}
