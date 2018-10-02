package test.service;

import java.util.List;

import service.AuthorService;
import service.AuthorServiceImpl;
import test.Utility;
import dao.AuthorDao;
import dao.fake.AuthorDaoFakeImpl;
import domain.Author;

public class AuthorServiceFindAllTest {
	private static AuthorService getAuthorService() {
		AuthorDao authorDao = new AuthorDaoFakeImpl();
		AuthorServiceImpl authorService = new AuthorServiceImpl();
		authorService.setAuthorDao(authorDao);
		return authorService;
	}

	public static void main(String[] args) {
		AuthorService authorService = getAuthorService();
		List<Author> authors = authorService.findAll();
		System.out.println("Список всех авторов");
		System.out.println("===================");
		for(Author author : authors) {
			System.out.println(Utility.toString(author));
		}
	}
}
