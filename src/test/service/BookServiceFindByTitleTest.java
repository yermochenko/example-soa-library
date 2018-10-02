package test.service;

import java.util.List;

import service.BookService;
import test.Utility;
import domain.Book;
import factory.ServiceFactory;

public class BookServiceFindByTitleTest {
	public static void main(String[] args) {
		BookService bookService = ServiceFactory.getBookService();
		List<Book> books = bookService.findByTitle("книга");
		System.out.println("Список всех книг, содержащих в названии строку «книга»");
		System.out.println("======================================================");
		for(Book book : books) {
			System.out.println(Utility.toString(book));
		}
	}
}
