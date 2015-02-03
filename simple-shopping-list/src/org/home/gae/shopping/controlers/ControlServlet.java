package org.home.gae.shopping.controlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.home.gae.shopping.business.ArticleManagementService;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ControlServlet extends HttpServlet {
  
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {

    User user = (User) req.getAttribute("user");   
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
    }
    
    if(user != null) {
    	ArticleManagementService ams = new ArticleManagementService();
    	ams.loadArticlesForUser(user.getUserId());
    }
    
    req.getRequestDispatcher("/ShoppingApplication.jsp").forward(req, resp);


  }

}