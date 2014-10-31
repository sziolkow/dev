package org.home.gae.shopping;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.home.gae.common.ShoppingUtil;
import org.home.gae.shopping.dao.Dao;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class ServletCreateArticle extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    System.out.println("Creating new todo ");
    User user = (User) req.getAttribute("user");
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
    }

    String name = checkNull(req.getParameter("name"));
    String amount = checkNull(req.getParameter("amount"));
    if (ShoppingUtil.isNumber(amount)) {
       Dao.INSTANCE.add(user.getUserId(), name, new Integer(amount));
    }
    resp.sendRedirect("/ShoppingApplication.jsp");
    // TODO if not true then redirect to the other part
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
}

