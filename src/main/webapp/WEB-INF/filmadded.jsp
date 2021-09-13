<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Added</title>

</head>
<body>
	<c:choose>
		<c:when test="${film != null}">
			<div class="alert alert-success" role="alert">Film added</div>
			${film.title}
		</c:when>
		<c:when test="${film == null}">
			<div class="alert alert-danger" role="alert">Error adding Film</div>
		</c:when>
		<c:otherwise>

		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<br><a href="home.do">Home</a>
	
	
</body>
</html>