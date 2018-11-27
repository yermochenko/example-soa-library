package web.author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Author;
import service.AuthorService;
import service.ServiceException;
import web.Action;
import web.ActionResult;

public class AuthorEditActionImpl implements Action {
	private AuthorService service;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = null;
		try {
			id = Long.parseLong(req.getParameter("id"));
		} catch(NumberFormatException e) {}
		if(id != null) {
			try {
				Author author = service.findById(id);
				req.setAttribute("author", author);
			} catch(ServiceException e) {
				throw new ServletException(e);
			}
		}
		return null;
	}

	public void setAuthorService(AuthorService service) {
		this.service = service;
	}
}
