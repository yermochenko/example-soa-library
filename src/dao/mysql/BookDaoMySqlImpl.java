package dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dao.BookDao;
import dao.DaoException;
import domain.Author;
import domain.Book;

public class BookDaoMySqlImpl extends BaseDaoMySqlImpl implements BookDao {
	@Override
	public Long create(Book book) throws DaoException {
		String sql = "INSERT INTO `books` (`title`, `author_id`) VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, book.getTitle());
			if(book.getAuthor() != null) {
				statement.setLong(2, book.getAuthor().getId());
			} else {
				statement.setNull(2, Types.BIGINT);
			}
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			Long id = resultSet.getLong(1);
			book.setId(id);
			return id;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public Book read(Long id) throws DaoException {
		String sql = "SELECT `title`, `author_id` FROM `books` WHERE `id` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Book book = null;
			if(resultSet.next()) {
				book = new Book();
				book.setId(id);
				book.setTitle(resultSet.getString("title"));
				Long authorId = resultSet.getLong("author_id");
				if(!resultSet.wasNull()) {
					book.setAuthor(new Author());
					book.getAuthor().setId(authorId);
				}
			}
			return book;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public List<Book> readByAuthor(Long authorId) throws DaoException {
		String sql = null;
		if(authorId != null) {
			sql = "SELECT `id`, `title` FROM `books` WHERE `author_id` = ?";
		} else {
			sql = "SELECT `id`, `title` FROM `books` WHERE `author_id` IS NULL";
		}
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql);
			Author author = null;
			if(authorId != null) {
				statement.setLong(1, authorId);
				author = new Author();
				author.setId(authorId);
			}
			resultSet = statement.executeQuery();
			List<Book> books = new ArrayList<>();
			while(resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getLong("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(author);
				books.add(book);
			}
			return books;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public List<Book> readByTitle(String title) throws DaoException {
		String sql = "SELECT `id`, `title`, `author_id` FROM `books` WHERE `title` LIKE ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, String.format("%%%s%%", title));
			resultSet = statement.executeQuery();
			List<Book> books = new ArrayList<>();
			while(resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getLong("id"));
				book.setTitle(resultSet.getString("title"));
				Long authorId = resultSet.getLong("author_id");
				if(!resultSet.wasNull()) {
					book.setAuthor(new Author());
					book.getAuthor().setId(authorId);
				}
				books.add(book);
			}
			return books;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void update(Book book) throws DaoException {
		String sql = "UPDATE `books` SET `title` = ?, `author_id` = ? WHERE `id` = ?";
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, book.getTitle());
			if(book.getAuthor() != null) {
				statement.setLong(2, book.getAuthor().getId());
			} else {
				statement.setNull(2, Types.BIGINT);
			}
			statement.setLong(3, book.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM `books` WHERE `id` = ?";
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch(Exception e) {}
		}
	}
}
