<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">More<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="registration">Register</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="login">Login</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="welcome">Homepage</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="report">Report</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="skill">Skill</a></li>
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
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add a skill</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


</head>

<body>

<div class="container">

 <form:form method="POST" modelAttribute="skillForm" class="form-signin">
        <h2 class="form-signin-heading">Add Skill</h2>
        
        <spring:bind path="skillName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="skillName" class="form-control" placeholder="Skill Name"
                            autofocus="true"></form:input>
                <form:errors path="skillName"></form:errors>
            </div>
        </spring:bind>
        
       <spring:bind path="category">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="category" class="form-control" placeholder="Category"></form:input>
                <form:errors path="category"></form:errors>
            </div>
        </spring:bind>            
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        
</form:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
