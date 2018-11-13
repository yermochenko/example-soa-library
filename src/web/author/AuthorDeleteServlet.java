package web.author;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ioc.IoCContainer;
import ioc.IoCException;
import service.AuthorService;
import service.ServiceException;

public class AuthorDeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = null;
		try {
			id = Long.parseLong(req.getParameter("id"));
			try(IoCContainer ioc = new IoCContainer()) {
				AuthorService service = ioc.get(AuthorService.class);
				service.delete(id);
			} catch(ServiceException | IoCException e) {
				throw new ServletException(e);
			}
		} catch(NumberFormatException e) {}
		resp.sendRedirect(getServletContext().getContextPath() + "/author/list.html");
	}
}
