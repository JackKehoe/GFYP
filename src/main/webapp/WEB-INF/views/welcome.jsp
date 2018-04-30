<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>

<title>Homepage</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css"
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

			<h2>Welcome, ${pageContext.request.userPrincipal.name}</h2>
			<br>
			
			Skills:	
			<c:forEach items="${skills}" var="skill">
				<div class="panel panel-default">
					<div class="panel-body-center">
						<b><a href=${contextPath}/student/skill/${skill.id}>${skill.skillName}</a></b><br>
						<b>Category:</b>${skill.category}<br>
					</div>
				</div>
			</c:forEach>
			<br>
			
			Goal:	
			<c:forEach items="${goal}" var="goal">
				<div class="panel panel-default">
					<div class="panel-body-center">
						<b><a href=${contextPath}/student/goal/${goal.id}>${goal.goalName}</a></b><br>
						<c:if test="${goal.goalName eq 'bronze'}">
							<p>Your goal is Bronze Gaisce, this means you must complete at least 1 hour per week per challenge area for 13 weeks
						</c:if>
						<c:if test="${goal.goalName eq 'silver'}">
							<p>Your goal is Silver Gaisce, this means you must complete at least 1 hour per week per challenge area for 26 weeks
						</c:if>
						<div id="mainwrap"></div>
					</div>
				</div>
			</c:forEach>
			<br>



			<br>
			Reports:
			<c:forEach items="${savedReports}" var="report">
				<div class="panel panel-default">
					<div class="panel-body-center">
						<b><a href=${contextPath}/student/report/${report.id}>${report.title}</a></b><br>
						<b>Date: </b>${report.date}<br> <b>Content: </b>${report.content}<br>
						<b>File: </b>${report.uploadFile}<br>
						<div id="mainwrap"></div>
					</div>
				</div>
			</c:forEach>
		</c:if>

		<div align="right">
			<c:forEach items="${savedReports}" var="report"> 
				
			</c:forEach>
			<c:forEach items="${allRatings}" var="rating">
				<c:set var="passcount" scope="session" />
				<c:set var="failcount" scope="session" />
				<c:if test="${rating.reportRating eq 'pass'}">
					<p>
						<c:set var="passcount" value="${passcount + 1}" />
				</c:if>
				<c:if test="${rating.reportRating eq 'fail'}">
					<p>
						<c:set var="failcount" value="${failcount + 1}" />
				</c:if>
				
			</c:forEach>
			<p>Number of Reports submitted: ${fn:length(savedReports)}</p>
			<p>Number of passes: <c:out value ="${passcount}"/></p>
			<p>Number of fails:	<c:out value ="${failcount}"/></p>
		</div>

	</div>
	<div class="col-lg-5"></div>
	<script src="${contextPath}/resources/js/popper.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
