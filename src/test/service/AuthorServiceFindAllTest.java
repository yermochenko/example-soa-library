package test.service;

import ioc.IoCConfigurer;
import ioc.IoCContainer;
import ioc.IoCException;
import pool.ConnectionPool;
import pool.PoolException;

import java.util.List;

import service.AuthorService;
import service.ServiceException;
import test.Utility;
import domain.Author;

public class AuthorServiceFindAllTest {
	public static void main(String[] args) throws IoCException, ServiceException, PoolException {
		ConnectionPool.getInstance().init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/soa_library_db?useUnicode=true&characterEncoding=UTF-8", "soa_library_user", "soa_library_password");
		IoCConfigurer.configure();
		try(IoCContainer ioc = new IoCContainer()) {
			AuthorService authorService = ioc.get(AuthorService.class);
			List<Author> authors = authorService.findAll();
			System.out.println("Список всех авторов");
			System.out.println("===================");
			for(Author author : authors) {
				System.out.println(Utility.toString(author));
			}
		} finally {
			ConnectionPool.getInstance().destroy();
		}
	}
}
