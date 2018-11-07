package test.dao;

import java.util.List;

import test.Utility;
import dao.BookDao;
import dao.DaoException;
import dao.fake.BookDaoFakeImpl;
import domain.Book;

public class BookDaoReadByTitleTest {
	private static void output(BookDao bookDao, String title) throws DaoException {
		List<Book> books = bookDao.readByTitle(title);
		System.out.printf("Список всех книг, содержащих в названии строку «%s»\n", title);
		System.out.println("====================================================");
		for(Book book : books) {
			System.out.println(Utility.toString(book));
		}
		System.out.println();
	}

	public static void main(String[] args) throws DaoException {
		BookDao bookDao = new BookDaoFakeImpl();
		output(bookDao, "книга");
		output(bookDao, "синяя книга");
	}
}
