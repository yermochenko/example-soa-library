package web.author;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Author;
import ioc.IoCContainer;
import ioc.IoCException;
import service.AuthorService;
import service.ServiceException;

public class AuthorListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(IoCContainer ioc = new IoCContainer()) {
			AuthorService service = ioc.get(AuthorService.class);
			List<Author> authors = service.findAll();
			req.setAttribute("authors", authors);
			req.getRequestDispatcher("/WEB-INF/jsp/author/list.jsp").forward(req, resp);
		} catch(ServiceException | IoCException e) {
			throw new ServletException(e);
		}
	}
}
