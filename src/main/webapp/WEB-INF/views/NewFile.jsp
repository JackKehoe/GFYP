<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--  This is how your trying to print list-->
<table>
		<c:forEach var="o" items="${savedSkills}" > 
		<tr>
		               <td>Skill Name:<c:out value = "${o.skillName}"/></td>
		              <td>Category:  <c:out value = "${o.category}"/>  </td>          
		</tr>
		</c:forEach>            
		</table>
</body>
</html>