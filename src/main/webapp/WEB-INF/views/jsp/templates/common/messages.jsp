<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty messages}">
	<c:forEach var="message" items="${messages}">
		<c:choose>
			<c:when test="${message.severity == 'ERROR'}">
				<div class="alert alert-danger" role="alert">
					<spring:message code="${message.code}" arguments="${message.arguments}" />
				</div>
			</c:when>
			<c:when test="${message.severity == 'WARNING'}">
				<div class="alert alert-warning" role="alert">
					<spring:message code="${message.code}" arguments="${message.arguments}" />
				</div>
			</c:when>
			<c:when test="${message.severity == 'INFO'}">
				<div class="alert alert-info" role="alert">
					<spring:message code="${message.code}" arguments="${message.arguments}" />
				</div>
			</c:when>
			<c:when test="${message.severity == 'SUCCESS'}">
				<div class="alert alert-success" role="alert">
					<spring:message code="${message.code}" arguments="${message.arguments}" />
				</div>
			</c:when>
		</c:choose>
	</c:forEach>
</c:if>
