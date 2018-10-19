package test.service;

import ioc.IoCConfigurer;
import ioc.IoCContainer;
import ioc.IoCException;

import java.util.List;

import service.AuthorService;
import test.Utility;
import domain.Author;

public class AuthorServiceFindAllTest {
	public static void main(String[] args) throws IoCException {
		IoCConfigurer.configure();
		IoCContainer ioc = new IoCContainer();
		AuthorService authorService = ioc.getAuthorService();
		List<Author> authors = authorService.findAll();
		System.out.println("Список всех авторов");
		System.out.println("===================");
		for(Author author : authors) {
			System.out.println(Utility.toString(author));
		}
	}
}
