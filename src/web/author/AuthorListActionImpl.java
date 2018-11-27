package web.author;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Author;
import service.AuthorService;
import service.ServiceException;
import web.Action;
import web.ActionResult;

public class AuthorListActionImpl implements Action {
	private AuthorService service;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Author> authors = service.findAll();
			req.setAttribute("authors", authors);
			return null;
		} catch(ServiceException e) {
			throw new ServletException(e);
		}
	}

	public void setAuthorService(AuthorService service) {
		this.service = service;
	}
}
