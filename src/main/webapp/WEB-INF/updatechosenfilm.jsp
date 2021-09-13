<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
</head>
<body>
<h1>Film Update</h1>
<c:if test="${film.id > 1}">${film.title } updated successfully.
</c:if>
<c:if test="${film.id < 1}">I'm sorry, the film didn't update correctly.  Please try again
</c:if>
<br><a href="home.do">Home</a>
</body>
</html>