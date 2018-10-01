package dao.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dao.AuthorDao;
import domain.Author;

public class AuthorDaoFakeImpl implements AuthorDao {
	/*
	 * Хранилище авторов в памяти. Для хранилища используется карта
	 * отображений, что упрощает поиск по идентификатору. В качестве
	 * реализации карты отображений используется ConcurrentHashMap,
	 * что позволяет использовать её в многопоточном режиме.
	 */
	private static Map<Long, Author> authors = new ConcurrentHashMap<>();

	/*
	 * Заполняем хранилище в памяти тестовыми данными
	 */
	static {
		Author author = new Author();
		author.setId(1L);
		author.setFirstName("Иван");
		author.setLastName("Иванов");
		authors.put(author.getId(), author);
		author = new Author();
		author.setId(2L);
		author.setFirstName("Петр");
		author.setLastName("Петров");
		authors.put(author.getId(), author);
	}

	@Override
	public Long create(Author author) {
		Long id = 0L;
		if(!authors.isEmpty()) {
			id = Collections.max(authors.keySet());
		}
		author.setId(++id);
		authors.put(id, author);
		return id;
	}

	@Override
	public Author read(Long id) {
		return authors.get(id);
	}

	@Override
	public void update(Author author) {
		authors.put(author.getId(), author);
	}

	@Override
	public void delete(Long id) {
		authors.remove(id);
	}

	@Override
	public List<Author> readAll() {
		return new ArrayList<>(authors.values());
	}
}
