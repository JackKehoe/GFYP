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

			<h2>Current logged in user:
				${pageContext.request.userPrincipal.name}</h2>
			<br>

			<h4>
				All Users:
				<table>
					<c:forEach var="o" items="${users}">
						<tr>
							<td>Name: <c:out value="${o.username}" /></td>
							<td>| School: <c:out value="${o.school}" />
							</td>
							<td><a href="<c:url value='/mentor/addStudent/${o.id}' />">
									Add Student </a>
						</tr>
					</c:forEach>
				</table>
			</h4>

			<h4>
				My Students:
				<table>
					<c:forEach var="o" items="${students}">
						<tr>
							<td>Name: <c:out value="${o.username}" /></td>
							<td>| School: <c:out value="${o.school}" />
							</td>
						</tr>
					</c:forEach>
				</table>
			</h4>

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