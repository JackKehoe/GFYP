<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">

<html lang="en">
<head>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<form id="logoutForm" method="POST" action="${contextPath}/logout">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<c:if test="${pageContext.request.isUserInRole('ROLE_MENTOR')}">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a href="${contextPath}/mentor/mentorhomepage"> <img border="0"
						alt="nasc" src="/resources/files/nascnavbar.png" width="80" height="50">
					</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<form class="navbar-form navbar-left" method="get"
						action="${contextPath}/mentor/search">
						<div class="form-group" role="search">
							<input type="text" name="searchString" class="form-control"
								placeholder="Search">
						</div>
						<button type="submit" class="btn btn-default">Search</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${contextPath}/mentor/mentorhomepage">Homepage</a></li>
						<li><a href="${contextPath}/mentor/account">Account</a></li>
						<li><a href="${contextPath}/mentor/viewusers">View Users</a></li>
						<li><a href="${contextPath}/mentor/statistics">Statistics</a></li>
						<li><a onclick="document.forms['logoutForm'].submit()">Logout</a></li>
					</ul>
				</div>
			</c:if>

			<c:if test="${pageContext.request.isUserInRole('ROLE_STUDENT')}">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a href="${contextPath}/student/welcome"> <img border="0"
						alt="nasc" src="/resources/files/nascnavbar.png" width="80" height="50">
					</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">


					<ul class="nav navbar-nav navbar-right">
						<li><a href="${contextPath}/student/welcome">Homepage</a></li>
						<li><a href="${contextPath}/student/account">Account</a></li>
						<li><a href="${contextPath}/student/skill">Skills </a></li>
						<li><a href="${contextPath}/student/addreport">Reports </a></li>
						<li><a href="${contextPath}/student/goal">Goals </a></li>
						<li><a onclick="document.forms['logoutForm'].submit()">Logout</a></li>
					</ul>
				</div>
			</c:if>
		</div>
	</nav>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>