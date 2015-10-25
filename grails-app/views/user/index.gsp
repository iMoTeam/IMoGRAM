
<%@ page import="ivvq.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<div>
		</div>
			<br/><br/><br/>
			<table>
				<g:each in="${1..3}" var="c">
					<p>Ligne : ${c}</p>
					<tr>

						<g:each in="${items.subList((c-1)*5, (c*5)-1)}" var="ItemUserInstance">
							<g:if test="${ItemUserInstance.book != null}">
								<td><img  style="width: 100px;" src="${ItemUserInstance.book.image}"/></td>
								<td>Nb : ${c}</td>
							</g:if>
							<g:if test="${ItemUserInstance.movie!= null}">
								<td><img  style="width: 100px;" src="${ItemUserInstance.movie.poster}"/></td>
							</g:if>
							<g:if test="${ItemUserInstance.tvShow != null}">
								<td><img  style="width: 100px;" src="${ItemUserInstance.tvShow.image}"/></td>
							</g:if>
						</g:each>
					</tr>
					<tr>
						<p>TEST</p>
						<g:each in="${items.subList((c-1)*5, (c*5)-1)}" var="ItemUserInstance">
							<g:if test="${ItemUserInstance.book != null}">
								<td><img  style="width: 100px;" src="${ItemUserInstance.book.title}"/></td>
							</g:if>
							<g:if test="${ItemUserInstance.movie!= null}">
								<td><img  style="width: 100px;" src="${ItemUserInstance.movie.title}"/></td>
							</g:if>
							<g:if test="${ItemUserInstance.tvShow != null}">
								<td><img  style="width: 100px;" src="${ItemUserInstance.tvShow.title}"/></td>
							</g:if>
						</g:each>
					</tr>
				</g:each>
			</table>
		</div>
	</body>
</html>
