package org.home.gae.shopping.controlers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.home.gae.shopping.business.ArticleManagementService;
import org.home.gae.shopping.model.ArticleDTO;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ControlServlet extends HttpServlet {
  
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {

    User user = (User) req.getSession().getAttribute("user");   
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
      req.getSession().setAttribute("user",user);
      req.getSession().setAttribute("admin",checkIfUserIsAdmin(user));  
    }
    
    if(user != null && req.getSession().getAttribute("articles")==null) {
    	ArticleManagementService ams = new ArticleManagementService();
    	List<ArticleDTO>articles = ams.getArticles(user.getUserId());
    	req.getSession().setAttribute("articles",articles);
    }
    
    req.getRequestDispatcher("/ShoppingApplication.jsp").forward(req, resp);

  }

	private boolean checkIfUserIsAdmin(User user) throws IOException {
		if(user!=null) {
			InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/user.properties");
			Properties props = new Properties();
			props.load(is);
			String whatever = props.getProperty("admin.list");
			if(user.getEmail().equals(whatever)) {
				return true;
			}
		}
		return false;
	}

}
