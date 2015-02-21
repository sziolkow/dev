package org.home.gae.shopping.controlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.home.gae.shopping.business.ArticleManagementService;
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
    	List<ArticleDTO>articles = (List<ArticleDTO>)req.getSession().getAttribute("articles"); 
    	ArticleManagementService articleManagementService = new ArticleManagementService();		
    	articleManagementService.deleteArticle(id); 
    	removeObsoleteArticleFromList(id, articles);
    	req.getSession().setAttribute("articles",articles);
    	req.getRequestDispatcher("/ShoppingApplication.jsp").forward(req, resp);	
	}

	private void removeObsoleteArticleFromList(String id, List<ArticleDTO> articles) {
		for (ArticleDTO currentArticle : articles) {
			if (currentArticle.getId().equals(new Long(id))) {
				articles.remove(currentArticle);
				return;
			}
		}	
	}
}
