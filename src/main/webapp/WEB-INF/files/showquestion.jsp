<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="la ruta de el archivo.js"></script>
<link href="/css/showquestion.css" type="text/css" rel="stylesheet" />
<title>Question profile</title>
</head>
<body>
	<div class="container-fluid">
		<a href="/questions">Go back</a>
		<h1>
			<c:out value="${question.question}" />
		</h1>
		<h4>
			Tags:
			<c:forEach var="tag" items="${question.tags}" varStatus="loop">
				<c:out value="${tag.name}"/>${loop.last ? ' ' : ','}
			</c:forEach>
		</h4>
		<div class="left">
			<table class="table-hover table table-bordered">
				<tr>
					<th>Answers</th>
				</tr>
				<c:forEach var="answer" items="${question.answers}">
					<tr>
						<td><c:out value="${answer.answer}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="right">
			<h3>Answer this question: </h3>
			<form action="/questions/${question.id}" method="post">
				<p class="form-group">
					<textarea name="answer" class="form-control"></textarea>
					<form:errors path="answer"/>
				</p>
				<input type="submit" value="Answer" class="btn btn-outline-secondary form-control"/>
			</form>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>