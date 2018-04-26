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

<title>Skill</title>

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
							<b><a href=${contextPath}/student/skill/${skill.id}>${skill.skillName}</a></b><br>
							<b>Category: </b>${skill.category} <br>
							<a href="${contextPath}/student/skill/edit/${skill.id}">Edit</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-7"></div>
		</div>
	</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>