<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml"
	xmlns:sec="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html" />

<title>Account edit</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">


</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">

		<div class="col-lg-7">
			<div id="logbox">
				<c:if test="${pageContext.request.isUserInRole('ROLE_STUDENT')}">
					<form:form method="POST" modelAttribute="userForm"
						class="form-signin" action="${contextPath}/student/update">
						<h2 class="form-signin-heading">Update Account</h2>

						First Name
						<spring:bind path="firstname">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="firstname"
									value="${currentUser.firstname}" class="form-control"
									placeholder="First name"></form:input>
								<form:errors path="firstname"></form:errors>
							</div>
						</spring:bind>
					
						Last Name
						<spring:bind path="lastname">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="lastname"
									value="${currentUser.lastname}" class="form-control"
									placeholder="Last name"></form:input>
								<form:errors path="lastname"></form:errors>
							</div>
						</spring:bind>

						School
						<spring:bind path="school">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="school"
									value="${currentUser.school}" class="form-control"
									placeholder="school"></form:input>
								<form:errors path="school"></form:errors>
							</div>
						</spring:bind>
						<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
						<div class="text-center"></div>
					</form:form>
				</c:if>
				
				<c:if test="${pageContext.request.isUserInRole('ROLE_MENTOR')}">
					<form:form method="POST" modelAttribute="userForm"
						class="form-signin" action="${contextPath}/mentor/update">
						<h2 class="form-signin-heading">Update Account</h2>

						First Name
						<spring:bind path="firstname">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="firstname"
									value="${currentUser.firstname}" class="form-control"
									placeholder="First name"></form:input>
								<form:errors path="firstname"></form:errors>
							</div>
						</spring:bind>
					
						Last Name
						<spring:bind path="lastname">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="lastname"
									value="${currentUser.lastname}" class="form-control"
									placeholder="Last name"></form:input>
								<form:errors path="lastname"></form:errors>
							</div>
						</spring:bind>

						School
						<spring:bind path="school">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="school"
									value="${currentUser.school}" class="form-control"
									placeholder="school"></form:input>
								<form:errors path="school"></form:errors>
							</div>
						</spring:bind>
						<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
						<div class="text-center"></div>
					</form:form>
				</c:if>
				
				<H2>Account Details</H2>
				<br>
				<H4>
					<b>Username: </b>${userForm.username}<br> <br> <b>Firstname:
					</b>${userForm.firstname}<br> <br> <b>Lastname: </b>${userForm.lastname}<br>
					<br> <b>Email: </b>${userForm.email}<br> <br> <b>School:
					</b>${userForm.school}<br> <br>
				</H4>

			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>