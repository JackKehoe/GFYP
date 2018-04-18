<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Welcome</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="container">

		<jsp:include page="navbar.jsp"></jsp:include>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<br>
			
			<a class="dropdown-item"
					href="${contextPath}/mentor/viewusers/username">Username</a>
				<a class="dropdown-item"
					href="${contextPath}/mentor/viewusers/email">Email</a>
				<a class="dropdown-item"
					href="${contextPath}/mentor/viewusers/school">School</a>
					
			<h4>
				<c:forEach items="${userList}" var="user">
					<div class="panel panel-default">
						<div class="panel-body-center">
						<a href=${contextPath}/mentor/viewusers/${userId}>${user.username}</a><br>
							<b>School:</b>${user.school}<br>
							<b>Email:</b>${user.email}<br>
							<b></b><a href="<c:url value='/mentor/addStudent/${user.id}' />"> Add Student </a><br>
							<div id="mainwrap"></div>
						</div>
					</div>

				</c:forEach>
			</h4>

		</c:if>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>