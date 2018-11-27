package web.author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AuthorService;
import service.ServiceException;
import web.Action;
import web.ActionResult;

public class AuthorDeleteActionImpl implements Action {
	private AuthorService service;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = null;
		try {
			id = Long.parseLong(req.getParameter("id"));
			try {
				service.delete(id);
			} catch(ServiceException e) {
				throw new ServletException(e);
			}
		} catch(NumberFormatException e) {}
		return new ActionResult("/author/list.html");
	}

	public void setAuthorService(AuthorService service) {
		this.service = service;
	}
}
