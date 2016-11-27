<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
	<div class="col-md-3">
		<ul class="nav nav-pills nav-stacked">
			<li class="nav-item">
				<a class="nav-link active" data-toggle="pill" href="#myEvents">
					My Events
					<span class="tag tag-default">${memberEvents.size()}</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-toggle="pill" href="#eventInvites">
					Event Invites
					<span class="tag tag-default">${eventInvites.size()}</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-toggle="pill" href="#managedEvents">
					Managed Events
					<span class="tag tag-default">${adminEvents.size()}</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-toggle="pill" href="#newEvent">
					Create an Event
				</a>
			</li>
		</ul>
	</div>
	<div class="col-md-9">
		<div class="tab-content">
			<div class="tab-pane fade in active" id="myEvents" role="tab">
				<h2>My Events</h2>
				<div class="row">
				<c:if test="${empty memberEvents}">
					<p class="lead">No events found.</p>
				</c:if>
				<c:forEach var="event" items="${memberEvents}" varStatus="loop">
					<div class="col-md-6">
						<div class="card text-xs-center">
							<div class="card-header">
								<javatime:format value="${event.date}" pattern="MMM d, yyyy" />
							</div>
							<div class="card-block">
								<h4 class="card-title">${event.name}</h4>
								<p class="card-text">${event.description}</p>
								<a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/events/${event.id}">
									Details
								</a>
							</div>
							<div class="card-footer text-muted">
								Managed by <em>${event.admin.username}</em>
							</div>
						</div>
					</div>
					<c:if test="${loop.index % 2 == 1}"><div class="clearfix"></div></c:if>
				</c:forEach>
				</div>
			</div>
			
			<div class="tab-pane fade" id="eventInvites" role="tab">
				<h2>Event Invites</h2>
				<div class="row">
				<c:if test="${empty eventInvites}">
					<p class="lead">You don't have any pending event invitations.</p>
				</c:if>
				<c:forEach var="event" items="${eventInvites}" varStatus="loop">
					<div class="col-md-6">
						<div class="card text-xs-center">
							<div class="card-header">
								<javatime:format value="${event.date}" pattern="MMM d, yyyy" />
							</div>
							<div class="card-block">
								<h4 class="card-title">${event.name}</h4>
								<p class="card-text">${event.description}</p>
								<div class="row">
									<div class="col-sm-6">
										<form:form action="${pageContext.request.contextPath}/events/${event.id}/join" method="post">
											<input type="submit" class="btn btn-primary btn-block" value="Join">
										</form:form>
									</div>
									<div class="col-sm-6">
										<form:form action="${pageContext.request.contextPath}/events/${event.id}/reject" method="post">
											<input type="submit" class="btn btn-danger btn-block" value="Reject">
										</form:form>
									</div>
								</div>
							</div>
							<div class="card-footer text-muted">
								Managed by <em>${event.admin.username}</em>
							</div>
						</div>
					</div>
					<c:if test="${loop.index % 2 == 1}"><div class="clearfix"></div></c:if>
				</c:forEach>
				</div>
			</div>

			<div class="tab-pane fade" id="managedEvents" role="tab">
				<h2>Managed Events</h2>
				<div class="row">
				<c:if test="${empty adminEvents}">
					<p class="lead">You don't administer any events.</p>
				</c:if>
				<c:forEach var="event" items="${adminEvents}" varStatus="loop">
					<div class="col-md-6">
						<div class="card text-xs-center">
							<div class="card-header">
								<ul class="nav nav-pills card-header-pills">
									<li class="nav-item">
										<a class="nav-link active" data-toggle="pill" href="#detail-${event.id}">Details</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" data-toggle="pill" href="#members-${event.id}">Members</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" data-toggle="pill" href="#invite-${event.id}">Invite</a>
									</li>
								</ul>
							</div>
							<div class="card-block">
								<div class="tab-content">
									<div class="tab-pane active" id="detail-${event.id}" role="tabpanel">
										<h4 class="card-title">${event.name}</h4>
										<p class="card-text">${event.description}</p>
									</div>
									<div class="tab-pane" id="members-${event.id}" role="tabpanel">
										<c:if test="${not empty event.members}">
											<table class="table table-sm">
											<c:forEach var="user" items="${event.members}">
												<tr>
													<td>${user.username}</td>
													<td>
														<form:form action="${pageContext.request.contextPath}/events/${event.id}/remove" method="post">
															<input type="hidden" name="username" value="${user.username}">
															<input type="submit" class="btn btn-danger btn-sm" value="Remove">
														</form:form>
													</td>
												</tr>
											</c:forEach>
											</table>
										</c:if>
										<c:if test="${empty event.members}">
											No members
										</c:if>
									</div>
									<div class="tab-pane" id="invite-${event.id}" role="tabpanel">
										<form:form class="form-inline" action="${pageContext.request.contextPath}/events/${event.id}/invite" method="post">
											<div class="input-group">
												<input type="text" id="inviteUser-${event.id}" name="username" class="form-control form-control-sm" placeholder="Username" aria-label="Username to invite to this event">
												<span class="input-group-btn">
													<input type="submit" class="btn btn-primary btn-sm" value="Send Invite">
												</span>
											</div>
										</form:form>
									</div>
								</div>
							</div>
							<div class="card-footer">
								<a href="${pageContext.request.contextPath}/events/${event.id}" class="btn btn-secondary btn-block">Go To Event Page</a>
							</div>
						</div>
					</div>
					<c:if test="${loop.index % 2 == 1}"><div class="clearfix"></div></c:if>
				</c:forEach>
				</div>
			</div>
			
			<div class="tab-pane" id="newEvent" role="tab">
				<h2>New Event</h2>
				<form:form action="${pageContext.request.contextPath}/events" method="post">
					<div class="form-group">
						<label for="newEventName" class="sr-only">Name</label>
						<input class="form-control" type="text" id="newEventName" name="name" placeholder="Name">
					</div>
					<div class="form-group">
						<label for="newEventDescription" class="sr-only ">Description</label>
						<input class="form-control" type="text" id="newEventDescription" name="description" placeholder="Description">
					</div>
					<div class="form-group">
						<label for="newEventDate" class="sr-only">Date</label>
						<input class="form-control" type="date" id="newEventDate" name="date" placeholder="Date">
					</div>
					<input type="submit" class="btn btn-primary" value="Create Event">
				</form:form>
			</div>
		</div>
	</div>
</div>
