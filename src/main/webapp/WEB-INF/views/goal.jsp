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

<title>Add a Goal</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

	<jsp:include page="navbar.jsp"></jsp:include>

	<form:form method="POST" modelAttribute="goalForm" class="form-signin">


		<h2 class="form-signin-heading">Add Goal</h2>

		<spring:bind path="goalName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:radiobutton path="goalName" value="bronze" label="Bronze" />
				<form:radiobutton path="goalName" value="silver" label="Silver" />
			</div>
		</spring:bind>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

	</form:form>

</body>

</html>