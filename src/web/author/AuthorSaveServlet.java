package web.author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Author;
import ioc.IoCContainer;
import ioc.IoCException;
import service.AuthorService;
import service.ServiceException;

public class AuthorSaveServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		try {
			Author author = getAuthor(req);
			try(IoCContainer ioc = new IoCContainer()) {
				AuthorService service = ioc.get(AuthorService.class);
				service.save(author);
				resp.sendRedirect(getServletContext().getContextPath() + "/author/list.html");
			} catch(ServiceException | IoCException e) {
				throw new ServletException(e);
			}
		} catch(IllegalArgumentException e) {
			req.setAttribute("message", e.getMessage());
			req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
		}
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
