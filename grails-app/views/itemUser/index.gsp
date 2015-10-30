
<%@ page import="ivvq.ItemUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'itemUser.label', default: 'ItemUser')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-itemUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-itemUser" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="itemUser.movie.label" default="Movie" /></th>
					
						<th><g:message code="itemUser.tvShow.label" default="Tv Show" /></th>
					
						<th><g:message code="itemUser.book.label" default="Book" /></th>
					
						<g:sortableColumn property="rating" title="${message(code: 'itemUser.rating.label', default: 'Rating')}" />
					
						<g:sortableColumn property="favourite" title="${message(code: 'itemUser.favourite.label', default: 'Favourite')}" />
					
						<th><g:message code="itemUser.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${itemUserInstanceList}" status="i" var="itemUserInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${itemUserInstance.id}">${fieldValue(bean: itemUserInstance, field: "movie")}</g:link></td>
					
						<td>${fieldValue(bean: itemUserInstance, field: "tvShow")}</td>
					
						<td>${fieldValue(bean: itemUserInstance, field: "book")}</td>
					
						<td>${fieldValue(bean: itemUserInstance, field: "rating")}</td>
					
						<td><g:formatBoolean boolean="${itemUserInstance.favourite}" /></td>
					
						<td>${fieldValue(bean: itemUserInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${itemUserInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
