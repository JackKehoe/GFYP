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

<title>Homepage</title>

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
			
			Skills:	
			<c:forEach items="${skills}" var="skill">
				<div class="panel panel-default">
					<div class="panel-body-center">
						<b><a href=${contextPath}/student/skill/${skill.id}>${skill.skillName}</a></b><br>
						 <b>Category:</b>${skill.category}<br>
						<div id="mainwrap"></div>
					</div>
				</div>
			</c:forEach>
			<br>
			Goal:
			<c:forEach items="${goal}" var="goal">
				<div class="panel panel-default">
					<div class="panel-body-center">
						<b></b>${goal.goalName}<br>
						<div id="mainwrap"></div>
					</div>
				</div>
			</c:forEach>
			<br>
			Reports:
			<c:forEach items="${savedReports}" var="report">
				<div class="panel panel-default">
					<div class="panel-body-center">
						<b><a href=${contextPath}/student/report/${report.id}>${report.title}</a></b><br>
						<b>Date: </b>${report.date}<br> <b>Content: </b>${report.content}<br>
						<div id="mainwrap"></div>
					</div>
				</div>
			</c:forEach>
			
			<h4>
				<a onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h4>
		</c:if>

	</div>
	<div class="col-lg-5"></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
