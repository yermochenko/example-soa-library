package dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AuthorDao;
import dao.DaoException;
import domain.Author;

public class AuthorDaoMySqlImpl extends BaseDaoMySqlImpl implements AuthorDao {
	@Override
	public Long create(Author author) throws DaoException {
		String sql = "INSERT INTO `authors` (`first_name`, `last_name`) VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, author.getFirstName());
			statement.setString(2, author.getLastName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			Long id = resultSet.getLong(1);
			author.setId(id);
			return id;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public Author read(Long id) throws DaoException {
		String sql = "SELECT `first_name`, `last_name` FROM `authors` WHERE `id` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Author author = null;
			if(resultSet.next()) {
				author = new Author();
				author.setId(id);
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
			}
			return author;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public List<Author> readAll() throws DaoException {
		String sql = "SELECT `id`, `first_name`, `last_name` FROM `authors`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = getConnection().prepareStatement(sql);
			resultSet = statement.executeQuery();
			List<Author> authors = new ArrayList<>();
			while(resultSet.next()) {
				Author author = new Author();
				author.setId(resultSet.getLong("id"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
				authors.add(author);
			}
			return authors;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void update(Author author) throws DaoException {
		String sql = "UPDATE `authors` SET `first_name` = ?, `last_name` = ? WHERE `id` = ?";
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(sql);
			statement.setString(1, author.getFirstName());
			statement.setString(2, author.getLastName());
			statement.setLong(3, author.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM `authors` WHERE `id` = ?";
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
