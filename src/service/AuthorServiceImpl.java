package service;

import java.util.List;

import dao.AuthorDao;
import dao.DaoException;
import domain.Author;

public class AuthorServiceImpl implements AuthorService {
	private AuthorDao authorDao;

	@Override
	public List<Author> findAll() throws ServiceException {
		try {
			return authorDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public void setAuthorDao(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}
}
