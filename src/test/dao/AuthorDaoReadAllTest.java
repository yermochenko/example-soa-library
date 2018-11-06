package test.dao;

import java.util.List;

import test.Utility;

import dao.AuthorDao;
import dao.DaoException;
import dao.fake.AuthorDaoFakeImpl;
import domain.Author;

public class AuthorDaoReadAllTest {
	public static void main(String[] args) throws DaoException {
		AuthorDao authorDao = new AuthorDaoFakeImpl();
		List<Author> authors = authorDao.readAll();
		System.out.println("Список всех авторов");
		System.out.println("===================");
		for(Author author : authors) {
			System.out.println(Utility.toString(author));
		}
	}
}
