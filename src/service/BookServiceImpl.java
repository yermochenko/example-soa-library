package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AuthorDao;
import dao.BookDao;
import domain.Author;
import domain.Book;

public class BookServiceImpl implements BookService {
	private AuthorDao authorDao;
	private BookDao bookDao;

	@Override
	public List<Book> findByTitle(String title) {
		List<Book> books = bookDao.readByTitle(title);
		Map<Long, Author> authors = new HashMap<>();
		for(Book book : books) {
			Author author = book.getAuthor();
			if(author != null) {
				Long id = author.getId();
				author = authors.get(id);
				if(author == null) {
					author = authorDao.read(id);
					authors.put(id, author);
				}
				book.setAuthor(author);
			}
		}
		return books;
	}

	public void setAuthorDao(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
