package test.service;

import java.util.List;

import service.AuthorService;
import test.Utility;
import domain.Author;
import factory.ServiceFactory;

public class AuthorServiceFindAllTest {
	public static void main(String[] args) {
		AuthorService authorService = ServiceFactory.getAuthorService();
		List<Author> authors = authorService.findAll();
		System.out.println("Список всех авторов");
		System.out.println("===================");
		for(Author author : authors) {
			System.out.println(Utility.toString(author));
		}
	}
}
