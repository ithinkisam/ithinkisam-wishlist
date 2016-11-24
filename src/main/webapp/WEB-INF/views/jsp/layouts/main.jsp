<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tiles:importAttribute name="stylesheets" />
<tiles:importAttribute name="appStylesheets" />
<tiles:importAttribute name="javascripts" />
<tiles:importAttribute name="appJavascripts" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<sec:csrfMetaTags />
		
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
		
		<title><tiles:getAsString name="title" /></title>
		
		<c:forEach var="css" items="${stylesheets}">
			<link rel="stylesheet" type="text/css" href="${css}">
		</c:forEach>
		<c:forEach var="css" items="${appStylesheets}">
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}${css}">
		</c:forEach>
		
		<style>
			.custom-container-padding { padding-top: 60px; padding-bottom: 60px; }
		</style>
		
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
	</head>
	<body>
		
		<tiles:insertAttribute name="header" />
		
		<div class="container custom-container-padding">
			<tiles:insertAttribute name="content" />
		</div>
		
		<tiles:insertAttribute name="footer" />
		
		<!-- JavaScript -->
		<c:forEach var="js" items="${javascripts}">
			<script src="${js}"></script>
		</c:forEach>
		<c:forEach var="js" items="${appJavascripts}">
			<script src="${pageContext.request.contextPath}${js}"></script>
		</c:forEach>
		
	</body>
</html>
