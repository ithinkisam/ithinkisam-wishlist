<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="offset-md-3 col-md-6">
		<h2 class="text-xs-center mb-1">Registration</h2>
		<form:form modelAttribute="newUser" action="${pageContext.request.contextPath}/register" method="post">
			<form:errors element="div" cssClass="form-group row has-danger" />
			<div class="form-group row">
				<form:label path="username" class="sr-only">Username</form:label>
				<form:input class="form-control" path="username" placeholder="Username" autofocus="true" />
				<form:errors path="username" element="div" cssClass="form-control-feedback" />
			</div>
			<div class="form-group row">
				<form:label path="firstName" class="sr-only">First Name</form:label>
				<form:input class="form-control" path="firstName" placeholder="First Name" />
				<form:errors path="firstName" element="div" cssClass="form-control-feedback" />
			</div>
			<div class="form-group row">
				<form:label path="lastName" class="sr-only">Last Name</form:label>
				<form:input class="form-control" path="lastName" placeholder="Last Name" />
				<form:errors path="lastName" element="div" cssClass="form-control-feedback" />
			</div>
			<div class="form-group row">
				<form:label path="email" class="sr-only">Email</form:label>
				<form:input type="email" class="form-control" path="email" placeholder="Email" />
				<form:errors path="email" element="div" cssClass="form-control-feedback" />
			</div>
			<div class="form-group row">
				<form:label path="password" class="sr-only">Password</form:label>
				<form:input type="password" class="form-control" path="password" placeholder="Password" />
				<form:errors path="password" element="div" cssClass="form-control-feedback" />
			</div>
			<div class="form-group row">
				<form:label path="matchingPassword" class="sr-only">Confirm Password</form:label>
				<form:input type="password" class="form-control" path="matchingPassword" placeholder="Confirm Password" />
			</div>
			
			<p><strong>Important Note:</strong> The connection on this site is not secure! Your password is not encrypted
				until it reaches our servers, so <strong>PLEASE</strong> do not use a password that you use for important
				sites (bank accounts, retail sites, etc).</p>
			
			<div class="form-group row">
				<button type="submit" class="btn btn-primary btn-block">Sign up!</button>
			</div>
		</form:form>
		
		<hr/>
		
		<div class="text-xs-center mt-1">
			<p>Already have an account? <a href="${pageContext.request.contextPath}/login">Log in here!</a></p>
		</div>
		
	</div>
</div>
