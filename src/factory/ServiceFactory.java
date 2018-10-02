package factory;

import service.AuthorService;
import service.AuthorServiceImpl;
import service.BookService;
import service.BookServiceImpl;

public class ServiceFactory {
	public static AuthorService getAuthorService() {
		AuthorServiceImpl authorService = new AuthorServiceImpl();
		authorService.setAuthorDao(DaoFactory.getAuthorDao());
		return authorService;
	}

	public static BookService getBookService() {
		BookServiceImpl bookService = new BookServiceImpl();
		bookService.setAuthorDao(DaoFactory.getAuthorDao());
		bookService.setBookDao(DaoFactory.getBookDao());
		return bookService;
	}
}
