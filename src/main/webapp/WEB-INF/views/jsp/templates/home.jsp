<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Dashboard</h1>

<div class="jumbotron">
  	<h1 class="display-3">Welcome to Wishlist!</h1>
  	<p class="lead">Wishlist is a one-stop shop for all your gift buying and Secret Santa-ing needs.</p>
  	<hr class="my-2">
  	<p>This site is still under construction, but we're pushing things out as we're ready! See below for available options.</p>
  	<p class="lead">
    	<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/wishes" role="button">Manage My Wishlist</a>
  	</p>
</div>
