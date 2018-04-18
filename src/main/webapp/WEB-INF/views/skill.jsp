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

<title>Add a skill</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">


</head>

<body>

	<div class="container">
		<jsp:include page="navbar.jsp"></jsp:include>

		<form:form method="POST" modelAttribute="skillForm"
			class="form-signin">
			<h2 class="form-signin-heading">Add Skill</h2>

			<spring:bind path="skillName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="skillName" class="form-control"
						placeholder="Skill Name" autofocus="true"></form:input>
					<form:errors path="skillName"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="category">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="category" class="form-control"
						placeholder="Category"></form:input>
					<form:errors path="category"></form:errors>
				</div>
			</spring:bind>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

		</form:form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
