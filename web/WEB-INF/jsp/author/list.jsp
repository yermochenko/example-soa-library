<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Список авторов">
	<c:choose>
		<c:when test="${not empty authors}">
			<table border="1">
				<tr>
					<th>Фамилия</th>
					<th>Имя</th>
				</tr>
				<c:forEach var="author" items="${authors}">
					<tr>
						<td>${author.lastName}</td>
						<td>${author.firstName}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<p>Список авторов пустой</p>
		</c:otherwise>
	</c:choose>
</u:html>
