package org.home.gae.shopping.controlers;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.home.gae.shopping.business.ArticleManagementService;

public class ServletDeleteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String id = req.getParameter("id");
    	ArticleManagementService articleManagementService = new ArticleManagementService();
    	articleManagementService.deleteArticle(id);
		resp.sendRedirect("/ShoppingApplication.jsp");
	}
}
