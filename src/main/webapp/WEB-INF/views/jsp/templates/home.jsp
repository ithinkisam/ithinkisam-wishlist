<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Dashboard</h1>

<div class="row">
	<div class="col-md-8">
		<div class="jumbotron">
			<h1 class="display-3">Welcome to Wishlist!</h1>
			<p class="lead">Wishlist is a one-stop shop for all your gift
				buying and Secret Santa-ing needs.</p>
			<hr class="my-2">
			<p>Use this form as a simple means of adding new wishes. Other uses will be able to see
				your wishes and check them off once they've been purchased.</p>
			<form:form class="form-inline text-xs-center" action="${pageContext.request.contextPath}/wishes" method="post">
				<div class="form-group">
					<label for="description" class="sr-only">Wish Description</label>
					<input type="text" class="form-control form-control-lg" name="description" id="description" placeholder="Description">
				</div>
				<button type="submit" class="btn btn-primary btn-lg">Add Wish</button>
			</form:form>
		</div>
	</div>
	<div class="col-md-4">
		<div class="list-group">
			<button type="button"
				class="list-group-item list-group-item-action active">Cras
				justo odio</button>
			<button type="button" class="list-group-item list-group-item-action">Dapibus
				ac facilisis in</button>
			<button type="button" class="list-group-item list-group-item-action">Morbi
				leo risus</button>
			<button type="button" class="list-group-item list-group-item-action">Porta
				ac consectetur ac</button>
			<button type="button"
				class="list-group-item list-group-item-action disabled">Vestibulum
				at eros</button>
		</div>
	</div>
</div>

<div class="row">
		<div class="card-columns">
			<div class="card">
				<div class="card-block">
					<h4 class="card-title">Card title that wraps to a new line</h4>
					<p class="card-text">This is a longer card with supporting text
						below as a natural lead-in to additional content. This content is
						a little bit longer.</p>
				</div>
			</div>
			<div class="card card-block">
				<blockquote class="card-blockquote">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Integer posuere erat a ante.</p>
					<footer>
						<small class="text-muted"> Someone famous in <cite
							title="Source Title">Source Title</cite>
						</small>
					</footer>
				</blockquote>
			</div>
			<div class="card">
				<div class="card-block">
					<h4 class="card-title">Card title</h4>
					<p class="card-text">This card has supporting text below as a
						natural lead-in to additional content.</p>
					<p class="card-text">
						<small class="text-muted">Last updated 3 mins ago</small>
					</p>
				</div>
			</div>
			<div class="card card-block card-inverse card-primary text-xs-center">
				<blockquote class="card-blockquote">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Integer posuere erat.</p>
					<footer>
						<small> Someone famous in <cite title="Source Title">Source
								Title</cite>
						</small>
					</footer>
				</blockquote>
			</div>
			<div class="card card-block text-xs-center">
				<h4 class="card-title">Card title</h4>
				<p class="card-text">This card has supporting text below as a
					natural lead-in to additional content.</p>
				<p class="card-text">
					<small class="text-muted">Last updated 3 mins ago</small>
				</p>
			</div>
			<div class="card card-block text-xs-right">
				<blockquote class="card-blockquote">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Integer posuere erat a ante.</p>
					<footer>
						<small class="text-muted"> Someone famous in <cite
							title="Source Title">Source Title</cite>
						</small>
					</footer>
				</blockquote>
			</div>
			<div class="card card-block">
				<h4 class="card-title">Card title</h4>
				<p class="card-text">This is a wider card with supporting text
					below as a natural lead-in to additional content. This card has
					even longer content than the first to show that equal height
					action.</p>
				<p class="card-text">
					<small class="text-muted">Last updated 3 mins ago</small>
				</p>
			</div>
		</div>
</div>
