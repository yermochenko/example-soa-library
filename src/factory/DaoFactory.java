package factory;

import dao.AuthorDao;
import dao.BookDao;
import dao.fake.AuthorDaoFakeImpl;
import dao.fake.BookDaoFakeImpl;

public class DaoFactory {
	public static AuthorDao getAuthorDao() {
		return new AuthorDaoFakeImpl();
	}

	public static BookDao getBookDao() {
		return new BookDaoFakeImpl();
	}
}
