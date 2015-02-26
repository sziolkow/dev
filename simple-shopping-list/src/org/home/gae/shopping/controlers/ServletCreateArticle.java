package org.home.gae.shopping.controlers;

import static org.home.gae.common.ShoppingUtil.checkIfEmptyOrNull;
import static org.home.gae.common.ShoppingUtil.isNumber;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
public class ServletCreateArticle extends HttpServlet {
  
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    System.out.println("Creating new article ");
    User user = (User) req.getAttribute("user");
   
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
    }
    
    if (checkIfInputDataAreValid(req, req.getParameter("name"), req.getParameter("amount"))) {
    	ArticleManagementService articleManagementService = new ArticleManagementService();
    	
    	List<ArticleDTO>articles = (List<ArticleDTO>)req.getSession().getAttribute("articles");        
    	System.out.println("Number of articles before adding a new one: " +articles.size() );
        
    	ArticleDTO newArticle = articleManagementService.addArticle(user.getEmail(), 
    			                            req.getParameter("name"), 
    			                            req.getParameter("amount"));
    	
    	RequestDispatcher rd = req.getRequestDispatcher("/ShoppingApplication.jsp");
    	
    	articles.add(newArticle);
        System.out.println("Number of articles after adding a new one: " +articles.size() );
    	req.getSession().setAttribute("articles",articles);
    	rd.forward(req, resp);
    } else {
    	req.getRequestDispatcher("/ShoppingApplication.jsp").forward(req, resp);
    }

  }

	private boolean checkIfInputDataAreValid(HttpServletRequest req,
			String name, String amount) {
		if (checkIfEmptyOrNull(name)) {
			req.setAttribute("error_", "Name of the article can't be empty!");
			return false;
		}
		if (checkIfEmptyOrNull(amount)) {
			req.setAttribute("error_", "Amount can't be empty!");
			return false;
		}
		if (!isNumber(amount.trim())) {
			req.setAttribute("error_", "Amount must be a number!");
			return false;
		}		
		return true;

	}
}