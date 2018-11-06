package test.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DaoException;
import dao.mysql.AuthorDaoMySqlImpl;
import domain.Author;
import pool.ConnectionPool;
import pool.PoolException;
import test.Utility;

public class AuthorDaoTest {
	private static void output(List<Author> authors) {
		System.out.println("========================================\nМетод readAll()\nСписок всех авторов\n----------------------------------------");
		for(Author author : authors) {
			System.out.println(Utility.toString(author));
		}
	}

	public static void main(String[] args) throws SQLException, PoolException, DaoException {
		ConnectionPool.getInstance().init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/soa_library_db?useUnicode=true&characterEncoding=UTF-8", "soa_library_user", "soa_library_password");
		try(Connection connection = ConnectionPool.getInstance().getConnection()) {
			AuthorDaoMySqlImpl authorDao = new AuthorDaoMySqlImpl();
			authorDao.setConnection(connection);
			output(authorDao.readAll());
			Author author = new Author();
			author.setFirstName("Вильям");
			author.setLastName("Шекспир");
			Long id = authorDao.create(author);
			System.out.printf("========================================\nМетод create()\nАвтор создан с идентификатором %d\n", id);
			output(authorDao.readAll());
			author = authorDao.read(id);
			System.out.printf("========================================\nМетод read()\nПрочитан автор с идентификатором %d\n----------------------------------------\n", id);
			System.out.println(Utility.toString(author));
			author.setFirstName("Уильям");
			authorDao.update(author);
			System.out.printf("========================================\nМетод update()\nАвтор с идентификатором %d обновлён\n", id);
			output(authorDao.readAll());
			authorDao.delete(id);
			System.out.printf("========================================\nМетод delete()\nАвтор с идентификатором %d удалён\n", id);
			output(authorDao.readAll());
		}
		ConnectionPool.getInstance().destroy();
	}
}
