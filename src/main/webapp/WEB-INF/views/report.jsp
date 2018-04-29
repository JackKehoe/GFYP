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

<title>Report</title>

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
							<b><a href=${contextPath}/student/report/${report.id}>${report.title}</a></b><br>
							<b>Title: </b>${report.title} <br> <b>Date: </b>${report.date}
							<br> <b>Text: </b>${report.content} <br> <b>File: <a
								href="/resources/files/${report.uploadFile}" download>file</a></b> <br>
							<c:if test="${pageContext.request.isUserInRole('ROLE_STUDENT')}">
								<br>
								<a href="${contextPath}/student/report/edit/${report.id}">Edit</a>
							</c:if>
						</div>
					</div>
					<div class="col-md-7">
						<c:if test="${pageContext.request.isUserInRole('ROLE_MENTOR')}">
							<form:form commandName="commentForm"
								action="${contextPath}/mentor/comment/${report.id}"
								method="POST">
								<form:input path="content" placeholder="Write a comment"
									cssClass="form-control" />
								<input type="submit" class="btn btn-failure btn-sm" value="Post" />
							</form:form>
							&nbsp;
							<form:form commandName="ratingForm"
								action="${contextPath}/mentor/rating/${report.id}" method="POST">
									<form:radiobutton path="reportRating" value="pass" label="Pass" />
									<form:radiobutton path="reportRating" value="fail" label="Fail" />
									<input type="submit" class="btn btn-sm" value="Post" />
							</form:form>
						</c:if>
						&nbsp;
						<c:forEach items="${rating}" var="rating">
							<div class="panel panel-default">
								<div class="panel-body">
								 ${rating.reportRating}
								</div>
							</div>
						</c:forEach>
						&nbsp;
						<c:forEach items="${comments}" var="comment">
							<div class="panel panel-default">
								<div class="panel-body">
									<b>${comment.user.username} commented:</b> ${comment.content}
								</div>
								<div class="panel-footer">${comment.timeStamp}</div>
							</div>
						</c:forEach>
						

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