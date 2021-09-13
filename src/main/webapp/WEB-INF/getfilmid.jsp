<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film</title>
</head>
<body>
<h1>Your Selection</h1>
	<c:if test="${not empty film}">
		<br>
		Title: ${film.title} 
		<br>
		Release Year: ${film.releaseYear}
		<br>
		Rated: ${film.rating}
		<br>
		Film Description: ${film.description}
		<br>
		${film.length } minutes long
		<br>
	</c:if>
	<c:if test="${empty film }">Could not find a film with that ID.
	</c:if>
	
	<br><a href="deleteFilm.do?filmId=${film.id}">Delete this film</a>
	<br>
	<a href="filmGettingUpdate.do?filmId=${film.id}">Update this film
			information</a>
	<br><a href="home.do">Home</a>
</body>
</html>