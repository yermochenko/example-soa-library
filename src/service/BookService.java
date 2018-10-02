package service;

import java.util.List;

import domain.Book;

public interface BookService {
	List<Book> findByTitle(String title);
}
