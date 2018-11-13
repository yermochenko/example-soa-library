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

public class AuthorEditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = null;
		try {
			id = Long.parseLong(req.getParameter("id"));
		} catch(NumberFormatException e) {}
		if(id != null) {
			try(IoCContainer ioc = new IoCContainer()) {
				AuthorService service = ioc.get(AuthorService.class);
				Author author = service.findById(id);
				req.setAttribute("author", author);
			} catch(ServiceException | IoCException e) {
				throw new ServletException(e);
			}
		}
		req.getRequestDispatcher("/WEB-INF/jsp/author/edit.jsp").forward(req, resp);
	}
}
