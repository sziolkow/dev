package org.home.gae.shopping.controlers;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class InitServlet extends GenericServlet {

	private static final long serialVersionUID = 1L;
	protected String myParam = null;

	public void init(ServletConfig servletConfig) throws ServletException {
		servletConfig.getServletContext().setAttribute("admin", "test@example.com");
	}

	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		response.getWriter().write(
				"<html><body>myParam = " + this.myParam + "</body></html>");
	}
}
