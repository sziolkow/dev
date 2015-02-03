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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Lista zakupow">
    <meta name="author" content="Rafal Wdowiak">
    <link rel="icon" href="../../favicon.ico">

    <title>Shopping list</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="theme.css" rel="stylesheet">

    <!--
    <link href="css/main.css" rel="stylesheet">
    -->
        <script type="text/javascript">
			
			String.prototype.trim = function() {
			   return this.replace(/^\s+|\s+$/g,"");
			}
			
            function validate()
            {
                var amount = document.getElementById("amount");
             	
                if (amount == null) {
                	alert("Ilosc jest obowiazakowa. Wprowadz interesujaca cie ilosc.")
                	return false;
                }
				
				if (amount.value.trim() == "") {
					alert("Ilosc nie moze byc pusta. Wprowadz interesujaca Cie liczbe.");
					return false;
				}
				
				if ((amount.value<1) || (amount.value>99)) {
				    alert("Nieprawidlowa ilosc. Podaj ilosc pomiedzy 1 a 99.");
					return false;
				}
                
                if(isNaN(amount.value)){
                    alert("Nieprawidlowa ilosc. Wprowadz liczbe.");
                    return false;
                }
                return true;
            };
        </script>      
  </head>
  <body role="document">

  <%
	Dao dao = Dao.INSTANCE;
	
	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();
	
	String url = userService.createLoginURL(request.getRequestURI());
	String urlLinktext = "Zaloguj";
	List<Article> articles = new ArrayList<Article>();
	            
	if (user != null){
	    url = userService.createLogoutURL(request.getRequestURI());
	    urlLinktext = "Wyloguj";
	    articles = dao.getArticles(user.getUserId());
	    ServletContext context = request.getSession().getServletContext();
	    String attribute =(String)context.getAttribute("admin");
	    if((attribute != null) && (attribute.equals(user.getUserId()))) {
	    	isAdmin = true;
	    }
	}
	String error = (String)request.getAttribute("error_");
	    
   %>

  
    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Lista praca</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Strona domowa</a></li>
            <li><a href="about.html">O aplikacji</a></li>
            <li><a href="contact.html">Kontakt</a></li>
            <li>
               <a href="<%=url%>"><%=urlLinktext%> <%=(user==null? "" : user.getNickname())%></a>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    </br></br></br>
      

  <div class="container theme-showcase" role="main">
    <% if (user != null){ %>
	<div class="page-header" >
	        <h4>Witaj, <%=user%></h4>
	        <p><h5>Tutaj nalezy dodac artykuly ktore potrzebujesz do pracy!</h5></p>
	        <p><h5>Dostarczymy je najszybciej jak to mozliwe.</h5></p>
        	<p><h5>Liczba zamowionych artykulow : <%= articles.size() %>  </h5></p>	
	</div>
	
    <% if (error != null){ %>	
		<div class="alert alert-danger" role="alert">
	        <strong><%=error%></strong>
	    </div>
    <%}%>
   
    <div class="page-header">
    <h4>Dodaj towar</h4>
    </div>
    
	<div class="container">
 	<div class="col-md-6">
      <form class="form-signin" role="form" action="/new" method="post" accept-charset="utf-8" onsubmit="return validate();">
        <input type="text" class="form-control" placeholder="Wprowadz nazwe artykulu" required name="name" id="name">
        <input type="text" class="form-control" placeholder="Wprowadz potrzebna ilosc" required name="amount" id="amount" size=3>  
        <p></p>  
        <button class="btn btn-xs btn-success btn-block" type="submit">Dodaj</button>
      </form>
    </div>
    </div>
    
    <div>
    <h3> </h3>
    </div>
    
    <div class="page-header">
    	<h4>Zamowione artykuly:</h4>
    </div>
    <div class="row">
    <div class="container">
    <div class="col-md-6">
	        <table class="table table-striped"">
	        <tr>
	          <th>Nazwa</th>
	          <th>Ilosc</th>
	          <th>&nbsp;</th>
	        </tr>
	  		<% for (Article article : articles) {%>
				<tr> 
					<td><%=article.getName()%></td>
					<td><%=article.getAmount()%></td>
					<td><a class="done" href="/done?id=<%=article.getId()%>" >Delete</a></td>
				</tr> 
			<%}%>
	        </table>
	</div>       
	</div>
	</div>
	
	<div class="row"></div>
	<% if (isAdmin) { %>
		<div class="page-header">
	        <h1>i'm Admin</h1>
	    </div>
	<% } %>      
	<% }else{ %>
	<p> Aby skorzystac z aplikacji zaloguj sie przy pomocy konta Google </p>
	
	<% } %>  
	
</div>  

</body>
</html> 