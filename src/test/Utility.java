package test;

import domain.Author;
import domain.Book;

public class Utility {
	public static String toString(Author author) {
		return String.format("[%d] %s %s", author.getId(), author.getFirstName(), author.getLastName());
	}

	public static String toString(Book book) {
		return String.format("[%d] автор {%s}. «%s»", book.getId(), book.getAuthor() != null ? toString(book.getAuthor()) : null, book.getTitle());
	}
}
