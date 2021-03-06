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

<title>Create a report</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">

</head>

<body>

	<div class="container">
		<jsp:include page="navbar.jsp"></jsp:include>

		<form:form method="POST" modelAttribute="reportForm"
			class="form-signin">
			<h2 class="form-signin-heading">Create report</h2>

			<spring:bind path="title">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="title" class="form-control"
						placeholder="Title" autofocus="true"></form:input>
					<form:errors path="date"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="date">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="date" path="date" class="form-control"
						placeholder="Date"></form:input>
					<form:errors path="date"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="content">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="content" class="form-control"
						placeholder="Content"></form:input>
					<form:errors path="content"></form:errors>
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
