<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertAttribute name="messages" />

<c:if test="${not empty param.message}">
<div class="row">
	<div class="offset-lg-3 col-lg-6 offset-md-2 col-md-8 offset-sm-2 col-sm-8">
		<div class="alert alert-${param.messageType} text-xs-center">
			<spring:message code="message.login.${param.message}" text="Not sure what you did to get this message..." />
		</div>
	</div>
</div>
</c:if>

<div class="row">
	<div class="offset-lg-4 col-lg-4 offset-md-3 col-md-6 offset-sm-2 col-sm-8">
		<form:form action="${pageContext.request.contextPath}/login" method="post">
			<h2 class="text-xs-center">Sign-in</h2>
			
			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
			</div>
			
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
			</div>
			
			<button type="submit" class="btn btn-lg btn-primary btn-block">Sign In</button>
		</form:form>
		
		<div class="text-xs-center mt-1">
			<p>Don't have creds? <a href="${pageContext.request.contextPath}/register">Register for an account!</a></p>
		</div>
		
	</div>
</div>