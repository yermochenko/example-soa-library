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
import web.ActionResultType;

public class AuthorSaveActionImpl implements Action {
	private AuthorService service;

	@Override
	public ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		try {
			Author author = getAuthor(req);
			try {
				service.save(author);
				return new ActionResult("/author/list.html");
			} catch(ServiceException e) {
				throw new ServletException(e);
			}
		} catch(IllegalArgumentException e) {
			req.setAttribute("message", e.getMessage());
			return new ActionResult("/error", ActionResultType.FORWARD);
		}
	}

	public void setAuthorService(AuthorService service) {
		this.service = service;
	}

	private Author getAuthor(HttpServletRequest req) {
		Author author = new Author();
		try {
			author.setId(Long.parseLong(req.getParameter("id")));
		} catch(NumberFormatException e) {}
		author.setFirstName(req.getParameter("firstName"));
		author.setLastName(req.getParameter("lastName"));
		if(author.getFirstName() == null || author.getFirstName().isEmpty()) {
			throw new IllegalArgumentException("Не заполнено поле \"Имя\"");
		}
		if(author.getLastName() == null || author.getLastName().isEmpty()) {
			throw new IllegalArgumentException("Не заполнено поле \"Фамилия\"");
		}
		return author;
	}
}
