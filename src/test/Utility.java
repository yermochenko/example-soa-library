package test;

import domain.Author;

public class Utility {
	public static String toString(Author author) {
		return String.format("[%d] %s %s", author.getId(), author.getFirstName(), author.getLastName());
	}
}
