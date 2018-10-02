package test.service;

import java.util.List;

import service.AuthorService;
import test.Utility;
import di.ServiceLocator;
import domain.Author;

public class AuthorServiceFindAllTest {
	public static void main(String[] args) {
		ServiceLocator locator = new ServiceLocator();
		AuthorService authorService = locator.getAuthorService();
		List<Author> authors = authorService.findAll();
		System.out.println("Список всех авторов");
		System.out.println("===================");
		for(Author author : authors) {
			System.out.println(Utility.toString(author));
		}
	}
}
