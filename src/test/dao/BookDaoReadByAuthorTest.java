package test.dao;

import java.util.List;

import test.Utility;
import dao.BookDao;
import dao.DaoException;
import dao.fake.BookDaoFakeImpl;
import domain.Book;

public class BookDaoReadByAuthorTest {
	private static void output(BookDao bookDao, Long authorId) throws DaoException {
		List<Book> books = bookDao.readByAuthor(authorId);
		System.out.printf("Список всех книг автора с идентификатором [%d]\n", authorId);
		System.out.println("================================================");
		for(Book book : books) {
			System.out.println(Utility.toString(book));
		}
		System.out.println();
	}

	public static void main(String[] args) throws DaoException {
		BookDao bookDao = new BookDaoFakeImpl();
		output(bookDao, 1L);
		output(bookDao, 2L);
		output(bookDao, null);
	}
}
