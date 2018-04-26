<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Skill edit</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<div class="col-lg-6">
			<div id="logbox">
				<form:form method="POST" modelAttribute="goalForm">
					<h2 class="form-signin-heading">Update</h2>
						Goal name
					<spring:bind path="goalName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:radiobutton path="goalName" value="bronze" label="Bronze" />
							<form:radiobutton path="goalName" value="silver" label="Silver" />
						</div>
					</spring:bind>

					<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>

				</form:form>
			</div>
		</div>
		<div class="col-lg-2"></div>
		<div class="col-lg-4">
			<H2>Current Goal</H2>
			<br>
			<H4>
				${goalForm.goalName}<br>
			</H4>
		</div>
	</div>
</body>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</html>