<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>

<title>Statistics</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">



</head>
<body>
	<div class="container">

		<jsp:include page="navbar.jsp"></jsp:include>

		<c:forEach items="${allSkills}" var="skill">
					<c:set var="cicount" scope="session"  />
					<c:set var="pscount" scope="session" />
					<c:set var="prcount" scope="session" />
					<c:set var="jrcount" scope="session" />
					<c:if test="${skill.category eq 'Community Involvement'}">
						<p>
							<c:set var="cicount" value="${cicount + 1}" />
					</c:if>
					<c:if test="${skill.category eq 'Personal Skill'}">
						<p>
							<c:set var="pscount" value="${pscount + 1}" />
					</c:if>
					<c:if test="${skill.category eq 'Physical Recreation'}">
						<p>
							<c:set var="prcount" value="${prcount + 1}" />
					</c:if>
					<c:if test="${skill.category eq 'Journey/Research'}">
						<p>
							<c:set var="jrcount" value="${jrcount + 1}" />
					</c:if>
		</c:forEach>
		
		<c:forEach items="${allGoals}" var="goal">
			<c:set var="bronzecount" scope="session"  />
			<c:set var="silvercount" scope="session"  />
			<c:if test="${goal.goalName eq 'bronze'}">
						<p>
							<c:set var="bronzecount" value="${bronzecount + 1}" />
					</c:if>
			<c:if test="${goal.goalName eq 'silver'}">
						<p>
							<c:set var="silvercount" value="${silvercount + 1}" />
					</c:if>
		</c:forEach>
		
		<p>Number of Students involved in -</p>
					<p>Community involvement: <c:out value ="${cicount}"/></p>
					
					<p>Physical Recreation: <c:out value ="${prcount}"/></p>
						
					<p>Personal Skill: <c:out value ="${pscount}"/></p>
							
					<p>Journey/Research: <c:out value ="${jrcount}"/></p>
					
		
		<p>Number of Students involved in- </p>
					<p>Bronze Gaisce: <c:out value="${bronzecount}"/></p>
					
					<p>Silver Gaisce: <c:out value="${silvercount}"/></p>
					
	</div>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>