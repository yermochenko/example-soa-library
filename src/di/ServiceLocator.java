package di;

import service.AuthorService;
import service.AuthorServiceImpl;
import service.BookService;
import service.BookServiceImpl;
import dao.AuthorDao;
import dao.BookDao;
import dao.fake.AuthorDaoFakeImpl;
import dao.fake.BookDaoFakeImpl;

public class ServiceLocator {
	public static AuthorService getAuthorService() {
		AuthorServiceImpl authorService = new AuthorServiceImpl();
		authorService.setAuthorDao(getAuthorDao());
		return authorService;
	}

	public static BookService getBookService() {
		BookServiceImpl bookService = new BookServiceImpl();
		bookService.setAuthorDao(getAuthorDao());
		bookService.setBookDao(getBookDao());
		return bookService;
	}

	public static AuthorDao getAuthorDao() {
		return new AuthorDaoFakeImpl();
	}

	public static BookDao getBookDao() {
		return new BookDaoFakeImpl();
	}
}
