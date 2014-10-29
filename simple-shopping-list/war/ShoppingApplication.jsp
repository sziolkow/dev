<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="org.home.gae.shopping.model.Article" %>
<%@ page import="org.home.gae.shopping.dao.Dao" %>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
  <head>
    <title>Articles</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8">
            
        <script type="text/javascript">
            function validate()
            {
                var amount = document.getElementById("amount");
                	x = parseInt("amount");
                	
                alert(typeof amount);
                alert(amount.value);
                                            
                if(typeof amount == "undefined") {
                	alert("1. Amount is mandatory. Please enter a number.");
                	return false;  				
				}
				                
                if(isNaN(amount.value)){
                    alert("2. Invalid amount. Please enter a number");
                    return false;
                }       
                
                if(amount.value === "") {
                	alert("3. Amount is mandatory. Please enter a number.");
                	return false;  				
				}
				
				if(amount.value < 0 || amount.value > 99) {
					alert("4. Enter a number between 0-99.");
					return false;	
                }
                
                return true;
            };

        </script>    
  </head>
  <body>
<%
Dao dao = Dao.INSTANCE;

UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

String url = userService.createLoginURL(request.getRequestURI());
String urlLinktext = "Login";
List<Article> articles = new ArrayList<Article>();
            
if (user != null){
    url = userService.createLogoutURL(request.getRequestURI());
    urlLinktext = "Logout";
    articles = dao.getArticles(user.getUserId());
}
    
%>
  <div style="width: 100%;">
    <div class="line"></div>
    <div class="topLine">
      <div style="float: left;"><img src="images/todo.png" /></div>
      <div style="float: left;" class="headline">Articles</div>
      <div style="float: right;"><a href="<%=url%>"><%=urlLinktext%></a> <%=(user==null? "" : user.getNickname())%></div>
    </div>
  </div>

<div style="clear: both;"/>  
You have a total number of <%= articles.size() %>  Articles.

<table>
  <tr>
      <th>Name</th>
      <th>Amount</th>
    </tr>

<% for (Article article : articles) {%>
<tr> 
<td>
<%=article.getName()%>
</td>
<td>
<%=article.getAmount()%>
</td>

<td>
<a class="done" href="/done?id=<%=article.getId()%>" >Delete</a>
</td>
</tr> 
<%}
%>
</table>


<hr />

<div class="main">

<div class="headline">New article</div>

<% if (user != null){ %> 

<form action="/new" method="post" accept-charset="utf-8" onsubmit="return validate();">
  <table>
    <tr>
      <td><label for="summary">name</label></td>
      <td><input type="text" name="name" id="name" size="65"/></td>
    </tr>
  <tr>
    <td valign="top"><label for="url">Amount</label></td>
    <td><input type="amount" name="amount" id="amount" size="65" /></td>
  </tr>
  <tr>
      <td colspan="2" align="right"><input type="submit" value="Add"/></td>
    </tr>
  </table>
</form>

<% }else{ %>

Please login with your Google account

<% } %>
</div>
</body>
</html> 