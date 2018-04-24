<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Student</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>

	<div class="row">

		<div class="col-lg-3">
			<div id="logbox"></div>

		</div>

		<div class="col-md-4">
			<div id="logbox">
				<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-body-center">
							<b><a href=${contextPath}/student/${user.id}>${user.username}</a></b><br>
							<b>First Name: </b>${user.firstname} <br> <b>Last Name:
							</b>${user.lastname} <br> <b>Email: </b>${user.email} <br>
							<b>School: </b>${user.school} <br>
							<c:forEach items="${user.reports}" var="report">
								<b><a href=${contextPath}/mentor/report/${report.id}>${report.title}</a></b><br>
								<b>Date: </b>${report.date}<br>
								<b>Content: </b>${report.content}<br>
								<b>File: </b>${report.uploadFile}<br>
							</c:forEach>
							<c:forEach items="${user.skills}" var="skills">
								<b>Skill Name: </b>${skill.skillName}<br>
								<b>Skill Type: </b>${skill.skillType}<br>
							</c:forEach>
							<c:forEach items="${user.goals}" var="goals">
								<b>Goal </b>${goal.goalName}<br>
							</c:forEach>
						&nbsp;	
						</div>
					</div>
				</div>
				<div class="col-md-7"></div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>