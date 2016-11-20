<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="offset-md-3 col-md-6">
		<h2 class="text-xs-center mb-1">Registration</h2>
		<form:form action="${pageContext.request.contextPath}/register" method="post">
			<div class="form-group row">
				<label for="username" class="sr-only">Username</label>
				<input type="text" class="form-control" id="username" name="username" placeholder="Username">
			</div>
			<div class="form-group row">
				<label for="firstName" class="sr-only">First Name</label>
				<input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name">
			</div>
			<div class="form-group row">
				<label for="lastName" class="sr-only">Last Name</label>
				<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name">
			</div>
			<div class="form-group row">
				<label for="email" class="sr-only">Email</label>
				<input type="email" class="form-control" id="email" name="email" placeholder="Email">
			</div>
			<div class="form-group row">
				<label for="password" class="sr-only">Password</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Password">
			</div>
			<div class="form-group row">
				<label for="passwordConfirmation" class="sr-only">Confirm Password</label>
				<input type="password" class="form-control" id="passwordConfirmation" name="passwordConfirmation" placeholder="Confirm Password">
			</div>
			
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
