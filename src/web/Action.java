package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	ActionResult exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
