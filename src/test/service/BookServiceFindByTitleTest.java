package test.service;

import java.util.List;

import dao.AuthorDao;
import dao.BookDao;
import dao.fake.AuthorDaoFakeImpl;
import dao.fake.BookDaoFakeImpl;
import domain.Book;
import service.BookService;
import service.BookServiceImpl;
import test.Utility;

public class BookServiceFindByTitleTest {
	private static BookService getBookService() {
		AuthorDao authorDao = new AuthorDaoFakeImpl();
		BookDao bookDao = new BookDaoFakeImpl();
		BookServiceImpl bookService = new BookServiceImpl();
		bookService.setAuthorDao(authorDao);
		bookService.setBookDao(bookDao);
		return bookService;
	}

	public static void main(String[] args) {
		BookService bookService = getBookService();
		List<Book> books = bookService.findByTitle("книга");
		System.out.println("Список всех книг, содержащих в названии строку «книга»");
		System.out.println("======================================================");
		for(Book book : books) {
			System.out.println(Utility.toString(book));
		}
	}
}
