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
	private AuthorService authorService = null;
	public AuthorService getAuthorService() {
		if(authorService == null) {
			AuthorServiceImpl authorServiceImpl = new AuthorServiceImpl();
			authorService = authorServiceImpl;
			authorServiceImpl.setAuthorDao(getAuthorDao());
		}
		return authorService;
	}

	private BookService bookService = null;
	public BookService getBookService() {
		if(bookService == null) {
			BookServiceImpl bookServiceImpl = new BookServiceImpl();
			bookService = bookServiceImpl;
			bookServiceImpl.setAuthorDao(getAuthorDao());
			bookServiceImpl.setBookDao(getBookDao());
		}
		return bookService;
	}

	private AuthorDao authorDao = null;
	public AuthorDao getAuthorDao() {
		if(authorDao == null) {
			authorDao = new AuthorDaoFakeImpl();
		}
		return authorDao;
	}

	private BookDao bookDao = null;
	public BookDao getBookDao() {
		if(bookDao == null) {
			bookDao = new BookDaoFakeImpl();
		}
		return bookDao;
	}
}
