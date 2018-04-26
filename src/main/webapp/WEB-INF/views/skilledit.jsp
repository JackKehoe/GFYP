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
				<form:form method="POST" modelAttribute="skillForm" onsubmit="return validateForm()" action="">
					<h2 class="form-signin-heading">Update</h2>
					<spring:bind path="skillName">
						Skill name
							<div class="form-group">
							<form:input type="text" path="skillName" class="form-control"
								placeholder="${skillForm.skillName}" autofocus="true"></form:input>
							<form:errors path="skillName"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="category">
						Category
							<div class="form-group">
							<form:select type="select" path="category" class="form-control"
						placeholder="${skillForm.category}">
						<option value="Community Involvement ">Community Involvement </option>
						<option value="Personal Skill">Personal Skill</option>
						<option value="Physical Recreation">Physical Recreation</option>
						<option value="Journey/Research">Journey/Research</option>
							</form:select>
						</div>
					</spring:bind>

					<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>

				</form:form>
			</div>
		</div>
		<div class="col-lg-2"></div>
		<div class="col-lg-4">
			<H2>Current Skill</H2>
			<br>
			<H4>
				<b>Name: </b>${skillForm.skillName}<br> <br>
				<b>Category: </b>${skillForm.category}<br> <br>
			</H4>
		</div>
	</div>
</body>
<script type="text/javascript">
    function validateForm()
    {
    	var a=document.forms["skillForm"]["skillName"].value;
    	
    	if (a==null || a=="")
    		{
    			alert("ERROR: Please fill all fields");
    			return false;
    		}
    }
</script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</html>