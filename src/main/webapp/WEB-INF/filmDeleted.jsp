<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Delete Results</title>

</head>
<body>
	<c:choose>
		<c:when test="${film != null}">
			<div class="alert alert-success" role="alert">Film deleted</div>
			${film.title}
		</c:when>
		<c:when test="${film == null}">
			<div class="alert alert-danger" role="alert">Film could not be deleted</div>
		</c:when>
		<c:otherwise>

		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<br><a href="home.do"><button>Home</button></a>
	
	
</body>
</html>