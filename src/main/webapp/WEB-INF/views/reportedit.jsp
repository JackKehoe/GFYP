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

<title>Report edit</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<div class="col-lg-6">
			<div id="logbox">
				<form:form method="POST" modelAttribute="reportForm" onsubmit="return validateForm()" action="">
					<h2 class="form-signin-heading">Update</h2>
					<spring:bind path="title">
						Title
							<div class="form-group">
							<form:input type="text" path="title" class="form-control"
								placeholder="${reportForm.title}" autofocus="true"></form:input>
							<form:errors path="title"></form:errors>
						</div>
					</spring:bind>
					
					<spring:bind path="date">
						Date
							<div class="form-group">
							<form:input type="date" path="date" class="form-control"
								placeholder="${reportForm.date}"></form:input>
							<form:errors path="date"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="content">
						Content
							<div class="form-group">
							<form:input type="text" path="content" class="form-control"
								placeholder="${reportForm.content}"></form:input>
							<form:errors path="content"></form:errors>
						</div>
					</spring:bind>

					<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>

				</form:form>
				<form enctype="multipart/form-data" method="post"
					action="${contextPath}/student/add/${reportForm.id}/uploadFile">
					<input type="file" name="uploadFile">
					<button type="submit" class="btn btn-default">
						<span class="glyphicon glyphicon-floppy-disk"></span> Save
					</button>
				</form>
			</div>
		</div>
		<div class="col-lg-2"></div>
		<div class="col-lg-4">
			<H2>Current Report</H2>
			<br>
			<H4>
				<b>Title:</b>${reportForm.title}<br> <br>
				<b>Date: </b>${reportForm.date}<br> <br>
				<b>Content:</b>${reportForm.content}<br> <br>
				<b>File: </b>${reportForm.uploadFile}<br> <br>
			</H4>
		</div>
	</div>
</body>
<script type="text/javascript">
    function validateForm()
    {
    	var a=document.forms["reportForm"]["title"].value;
    	var b=document.forms["reportForm"]["content"].value;
    	var c=document.forms["reportForm"]["date"].value;


    	
    	if (a==null || a=="" || b==null || b=="" || c==null || c=="")
    		{
    			alert("ERROR: Please fill all fields");
    			return false;
    		}
    }
</script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</html>