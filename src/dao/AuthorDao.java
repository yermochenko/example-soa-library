package dao;

import java.util.List;

import domain.Author;

public interface AuthorDao extends Dao<Author> {
	List<Author> readAll() throws DaoException;
}
