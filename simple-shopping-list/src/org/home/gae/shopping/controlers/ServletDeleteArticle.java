package org.home.gae.shopping.controlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.home.gae.shopping.business.ArticleManagementService;
import org.home.gae.shopping.model.Article;
import org.home.gae.shopping.model.ArticleDTO;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ServletDeleteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
	    User user = (User) req.getAttribute("user");   
	    if (user == null) {
	      UserService userService = UserServiceFactory.getUserService();
	      user = userService.getCurrentUser();
	    }
		
		String id = req.getParameter("id");
    	ArticleManagementService articleManagementService = new ArticleManagementService();
    	articleManagementService.deleteArticle(id);   	
       	List<ArticleDTO>articles = articleManagementService.getArticles(user.getUserId());
    	req.setAttribute("articles", articles);
    	req.getRequestDispatcher("/ShoppingApplication.jsp").forward(req, resp);	
	}
}
