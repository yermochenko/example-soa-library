<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
	<c:when test="${not empty author}">
		<c:set var="title" value="Редактирование автора ${fn:substring(author.firstName, 0, 1)}. ${author.lastName}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Добавление нового автора"/>
		<jsp:useBean id="author" class="domain.Author"/>
	</c:otherwise>
</c:choose>

<u:html title="${title}">
	<c:url var="saveUrl" value="/author/save.html"/>
	<form action="${saveUrl}" method="post">
		<c:if test="${not empty author.id}">
			<input type="hidden" name="id" value="${author.id}">
		</c:if>
		<label for="first-name">Имя:</label><br>
		<input id="first-name" name="firstName" value="${author.firstName}"><br>
		<label for="last-name">Фамилия:</label><br>
		<input id="last-name" name="lastName" value="${author.lastName}"><br>
		<button>Сохранить</button>
	</form>
</u:html>
