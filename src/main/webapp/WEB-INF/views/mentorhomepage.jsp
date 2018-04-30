<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Homepage</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="container">

		<jsp:include page="navbar.jsp"></jsp:include>

		<c:if test="${pageContext.request.userPrincipal.name != null}">

			<h4>Welcome, 
				${pageContext.request.userPrincipal.name}</h4>
			<br>
			
			Sort By:
          <a class="dropdown-item"
				href="${contextPath}/mentor/mentorhomepage/username">Username</a>
			<a class="dropdown-item" href="${contextPath}/mentor/mentorhomepage/email">Email</a>
			<a class="dropdown-item" href="${contextPath}/mentor/mentorhomepage/school">School</a>

			<c:forEach items="${students}" var="user">
				<div class="panel panel-default">
					<div class="panel-body-center">
						<b><a href=${contextPath}/mentor/student/${user.id}>${user.username}</a></b><br>
						<b>Email:</b>${user.email}<br>
						<b>School:</b>${user.school}<br>
						<div id="mainwrap"></div>
					</div>

				</div>
			</c:forEach>
				<h4>
					<a onclick="document.forms['logoutForm'].submit()">Logout</a>
				</h4>
		</c:if>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>