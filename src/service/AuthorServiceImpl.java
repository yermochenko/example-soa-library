package service;

import java.util.List;

import dao.AuthorDao;
import domain.Author;

public class AuthorServiceImpl implements AuthorService {
	private AuthorDao authorDao;

	@Override
	public List<Author> findAll() {
		return authorDao.readAll();
	}

	public void setAuthorDao(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}
}
