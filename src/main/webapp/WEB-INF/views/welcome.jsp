<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Gaisce</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="registration">Register</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="login">Login</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="welcome">Home</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="report">Report</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="skill">Skill</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="goal">Goal</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

          <h2>Current logged in user: ${pageContext.request.userPrincipal.name} </h2>
		<br>
		<h3> Your Skills:
			<table>
				<c:forEach var="o" items="${skills}" > 
					<tr>
		              <td>Name: <c:out value = "${o.skillName}"/></td>
		              <td>| Category:  <c:out value = "${o.category}"/> </td>          
					</tr>
				</c:forEach>            
			</table>
		</h3>
		<br>
		<h3> 
			Your Goal:
			  <c:forEach var="o" items="${goal}" > 
				<tr>
		        	<td><c:out value = "${o.goalName}"/></td>
				</tr>
			  </c:forEach> 
		</h3>
		<br>
		<h3>
			Your reports:
			<table>
			  <c:forEach var="o" items="${savedReports}" > 
				<tr>
		       		<td>Report date: <c:out value = "${o.date}"/></td>
		       		<td>| Content: <c:out value = "${o.content}"/></td>          
				</tr>
			  </c:forEach>  
			 </table>          
		</h3>
		
		<h4><a onclick="document.forms['logoutForm'].submit()">Logout</a></h4>
    </c:if>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
