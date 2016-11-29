<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h1 class="display-4">${event.name}</h1>
<p class="lead"><javatime:format value="${event.date}" pattern="MMM dd, yyyy" /> // ${event.description}</p>

<tiles:insertAttribute name="extras" ignore="true" />

<div class="row pt-2">
	<div class="col-sm-3">
		<ul class="nav nav-pills nav-stacked">
			<c:set var="navLinkClass" value="active" />
			<c:forEach var="member" items="${event.members}" varStatus="loop">
				<c:if test="${member.username != user.username}">
					<li class="nav-item">
						<a class="nav-link ${navLinkClass}" data-toggle="pill" href="#user-${member.username}">
							${member.firstName} - <small><var>@${member.username}</var></small>
						</a>
					</li>
					<c:set var="navLinkClass" value="" />
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<div class="col-sm-9">
		<div class="tab-content">
			<c:set var="navLinkClass" value="in active" />
			<c:forEach var="member" items="${event.members}" varStatus="loop">
				<c:if test="${member.username != user.username}">
					<div class="tab-pane fade ${navLinkClass}" id="user-${member.username}" role="tab">
						<c:set var="navLinkClass" value="" />
						<table class="table">
							<thead>
								<tr>
									<th>Description</th>
									<th><span class="sr-only">Tags</span></th>
									<th><span class="sr-only">Actions</span></th>
								</tr>
							</thead>
							<tbody>
							<c:if test="${empty lists[member.username]}">
								<tr><td colspan="3">This user hasn't added any wishes to their list.</td></tr>
							</c:if>
							<c:forEach var="wish" items="${lists[member.username]}">
								<tr class="${wish.purchased and wish.purchaser == user.username ? 'table-success' : 
											 wish.purchased and wish.purchaser != user.username ? 'table-danger' : ''}">
									<td>${wish.description}</td>
									<td>
										<c:forEach var="tag" items="${wish.tags}">
											<a href="${tag}" target="_blank"><i class="fa fa-tag fa-fw"></i></a>
										</c:forEach>
									</td>
									<td class="text-xs-center">
										<c:choose>
											<c:when test="${wish.purchased and wish.purchaser == user.username}">
												<form:form action="${pageContext.request.contextPath}/purchase/unfulfill" method="post">
													<input type="hidden" name="eventId" value="${event.id}" />
													<input type="hidden" name="wishId" value="${wish.id}" />
													<input type="submit" class="btn btn-success btn-sm" value="Return">
												</form:form>
											</c:when>
											<c:when test="${wish.purchased and wish.purchaser != user.username}">
												<button type="button" class="btn btn-secondary btn-sm" disabled>Not Available</button>
											</c:when>
											<c:otherwise>
												<form:form action="${pageContext.request.contextPath}/purchase/fulfill" method="post">
													<input type="hidden" name="eventId" value="${event.id}" />
													<input type="hidden" name="wishId" value="${wish.id}" />
													<input type="submit" class="btn btn-primary btn-sm" value="Purchase">
												</form:form>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>
