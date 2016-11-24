<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-fixed-top navbar-light bg-faded" style="background-color: #e3f2fd;">
 <div class="container">
  <button class="navbar-toggler hidden-lg-up" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"></button>
  <div class="collapse navbar-toggleable-md" id="navbarResponsive">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Wishlist</a>
    <ul class="nav navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/wishes">Wishes</a>
      </li>
      <!-- 
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/events">Events</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/profile">My Profile</a>
      </li>
       -->
    </ul>
    <ul class="nav navbar-nav float-lg-right">
      <li class="nav-item">
      	<form:form class="form-inline float-lg-right" action="${pageContext.request.contextPath}/logout" method="post">
	    	<button type="submit" class="btn btn-link nav-link">Logout</button>
	    </form:form>
      </li>
    </ul>
  </div>
 </div>
</nav>
