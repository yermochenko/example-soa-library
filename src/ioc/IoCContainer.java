package ioc;

import java.util.HashMap;
import java.util.Map;

import service.AuthorService;
import service.AuthorServiceImpl;
import service.BookService;
import service.BookServiceImpl;
import dao.AuthorDao;
import dao.BookDao;
import dao.fake.AuthorDaoFakeImpl;
import dao.fake.BookDaoFakeImpl;

public class IoCContainer {
	private static Map<Class<?>, Class<?>> dependencyInversionMap = new HashMap<>();
	static {
		dependencyInversionMap.put(AuthorDao.class, AuthorDaoFakeImpl.class);
		dependencyInversionMap.put(BookDao.class, BookDaoFakeImpl.class);
	}

	private Map<Class<?>, Object> cache = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> key) throws IoCException {
		T object = (T)cache.get(key);
		if(object == null) {
			Class<?> value = dependencyInversionMap.get(key);
			try {
				if(value != null) {
					object = (T)value.newInstance();
					cache.put(key, object);
				}
			} catch(InstantiationException | IllegalAccessException e) {
				throw new IoCException(e);
			}
		}
		return object;
	}

	private AuthorService authorService = null;
	public AuthorService getAuthorService() throws IoCException {
		if(authorService == null) {
			AuthorServiceImpl authorServiceImpl = new AuthorServiceImpl();
			authorService = authorServiceImpl;
			authorServiceImpl.setAuthorDao(get(AuthorDao.class));
		}
		return authorService;
	}

	private BookService bookService = null;
	public BookService getBookService() throws IoCException {
		if(bookService == null) {
			BookServiceImpl bookServiceImpl = new BookServiceImpl();
			bookService = bookServiceImpl;
			bookServiceImpl.setAuthorDao(get(AuthorDao.class));
			bookServiceImpl.setBookDao(get(BookDao.class));
		}
		return bookService;
	}
}
