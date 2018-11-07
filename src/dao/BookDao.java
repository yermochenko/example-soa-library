package dao;

import java.util.List;

import domain.Book;

public interface BookDao extends Dao<Book> {
	List<Book> readByAuthor(Long authorId) throws DaoException;

	List<Book> readByTitle(String title) throws DaoException;
}
