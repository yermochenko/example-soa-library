<%@page errorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Ошибка">
	<c:if test="${not empty message}">
		<p style="color: red;">${message}</p>
	</c:if>
</u:html>
