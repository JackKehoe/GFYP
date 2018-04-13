<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Gaisce</a>
    </div>

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
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Reports</title>
	<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

		<table>
		<c:forEach var="o" items="${savedReports}" > 
		<tr>
		               <td>Report date:<c:out value = "${o.date}"/></td>

		              <td>Content:  <c:out value = "${o.content}"/>  </td>          
		</tr>
		</c:forEach>            
		</table>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>