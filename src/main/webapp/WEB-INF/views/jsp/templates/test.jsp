<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Test Page</h1>

<h5>Users</h5>
<ul>
<c:forEach var="user" items="${users}">
	<li>${user}</li>
</c:forEach>
</ul>
