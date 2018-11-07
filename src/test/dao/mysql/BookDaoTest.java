package test.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DaoException;
import dao.mysql.BookDaoMySqlImpl;
import domain.Author;
import domain.Book;
import pool.ConnectionPool;
import pool.PoolException;
import test.Utility;

public class BookDaoTest {
	private static void output(List<Book> books, String method, String description) {
		System.out.printf("==================================================\nМетод %s()\nСписок книг (%s)\n--------------------------------------------------\n", method, description);
		for(Book book : books) {
			System.out.println(Utility.toString(book));
		}
	}

	public static void main(String[] args) throws SQLException, PoolException, DaoException {
		ConnectionPool.getInstance().init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/soa_library_db?useUnicode=true&characterEncoding=UTF-8", "soa_library_user", "soa_library_password");
		try(Connection connection = ConnectionPool.getInstance().getConnection()) {
			BookDaoMySqlImpl bookDao = new BookDaoMySqlImpl();
			bookDao.setConnection(connection);
			output(bookDao.readByAuthor(1L), "readByAuthor", "автор с идентификатором 1");
			output(bookDao.readByAuthor(1000L), "readByAuthor", "автор с идентификатором 1000");
			output(bookDao.readByAuthor(null), "readByAuthor", "без автора");
			Book book1 = new Book();
			book1.setTitle("Русские национальные сказки");
			Long id1 = bookDao.create(book1);
			System.out.printf("==================================================\nМетод create()\nКнига создана с идентификатором %d\n", id1);
			output(bookDao.readByAuthor(null), "readByAuthor", "без автора");
			Book book2 = new Book();
			book2.setAuthor(new Author());
			book2.getAuthor().setId(1L);
			book2.setTitle("Капитанская дочь");
			Long id2 = bookDao.create(book2);
			System.out.printf("==================================================\nМетод create()\nКнига создана с идентификатором %d\n", id2);
			output(bookDao.readByAuthor(1L), "readByAuthor", "автор с идентификатором 1");
			book1 = bookDao.read(id1);
			System.out.printf("==================================================\nМетод read()\nПрочитана книга с идентификатором %d\n--------------------------------------------------\n", id1);
			System.out.println(Utility.toString(book1));
			book2 = bookDao.read(id2);
			System.out.printf("==================================================\nМетод read()\nПрочитана книга с идентификатором %d\n--------------------------------------------------\n", id2);
			System.out.println(Utility.toString(book2));
			book1.setTitle("Русские народные сказки");
			bookDao.update(book1);
			System.out.printf("==================================================\nМетод update()\nКнига с идентификатором %d обновлена\n", id1);
			output(bookDao.readByAuthor(null), "readByAuthor", "без автора");
			book2.setTitle("Капитанска дочка");
			bookDao.update(book2);
			System.out.printf("==================================================\nМетод update()\nКнига с идентификатором %d обновлена\n", id2);
			output(bookDao.readByAuthor(1L), "readByAuthor", "автор с идентификатором 1");
			bookDao.delete(id1);
			System.out.printf("==================================================\nМетод delete()\nКнига с идентификатором %d удалена\n", id1);
			output(bookDao.readByAuthor(null), "readByAuthor", "без автора");
			bookDao.delete(id2);
			System.out.printf("==================================================\nМетод delete()\nКнига с идентификатором %d удалена\n", id2);
			output(bookDao.readByAuthor(1L), "readByAuthor", "автор с идентификатором 1");
			output(bookDao.readByTitle("ночи"), "readByTitle", "книги со словом \"ночи\" в названии");
			output(bookDao.readByTitle("братья"), "readByTitle", "книги со словом \"братья\" в названии");
			output(bookDao.readByTitle("и"), "readByTitle", "книги со словом \"и\" в названии");
			output(bookDao.readByTitle("полк"), "readByTitle", "книги со словом \"полк\" в названии");
			output(bookDao.readByTitle("рота"), "readByTitle", "книги со словом \"рота\" в названии");
		}
		ConnectionPool.getInstance().destroy();
	}
}
