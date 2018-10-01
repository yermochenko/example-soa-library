package dao.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dao.BookDao;
import domain.Author;
import domain.Book;

public class BookDaoFakeImpl implements BookDao {
	/*
	 * Хранилище книг в памяти. Для хранилища используется карта
	 * отображений, что упрощает поиск по идентификатору. В качестве
	 * реализации карты отображений используется ConcurrentHashMap,
	 * что позволяет использовать её в многопоточном режиме.
	 */
	private static Map<Long, Book> books = new ConcurrentHashMap<>();

	/*
	 * Заполняем хранилище в памяти тестовыми данными
	 */
	static {
		Book book = new Book();
		book.setId(1L);
		book.setTitle("Большая красная книга");
		book.setAuthor(new Author());
		book.getAuthor().setId(1L);
		books.put(book.getId(), book);
		book = new Book();
		book.setId(2L);
		book.setTitle("Маленькая жёлтая книга");
		book.setAuthor(new Author());
		book.getAuthor().setId(2L);
		books.put(book.getId(), book);
		book = new Book();
		book.setId(3L);
		book.setTitle("Просто зелёная книга");
		books.put(book.getId(), book);
	}

	@Override
	public Long create(Book book) {
		Long id = 0L;
		if(!books.isEmpty()) {
			id = Collections.max(books.keySet());
		}
		book.setId(++id);
		books.put(id, book);
		return id;
	}

	@Override
	public Book read(Long id) {
		return books.get(id);
	}

	@Override
	public void update(Book book) {
		books.put(book.getId(), book);
	}

	@Override
	public void delete(Long id) {
		books.remove(id);
	}

	@Override
	public List<Book> readByAuthor(Long authorId) {
		List<Book> result = new ArrayList<>();
		for(Book book : books.values()) {
			if((authorId == null && book.getAuthor() == null) || authorId.equals(book.getAuthor().getId())) {
				result.add(book);
			}
		}
		return result;
	}

	@Override
	public List<Book> readByTitle(String title) {
		List<Book> result = new ArrayList<>();
		for(Book book : books.values()) {
			if(book.getTitle().contains(title)) {
				result.add(book);
			}
		}
		return result;
	}
}
