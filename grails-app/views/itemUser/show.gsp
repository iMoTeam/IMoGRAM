
<%@ page import="ivvq.ItemUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'itemUser.label', default: 'ItemUser')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-itemUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-itemUser" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list itemUser">
			
				<g:if test="${itemUserInstance?.movie}">
				<li class="fieldcontain">
					<span id="movie-label" class="property-label"><g:message code="itemUser.movie.label" default="Movie" /></span>
					
						<span class="property-value" aria-labelledby="movie-label"><g:link controller="movie" action="show" id="${itemUserInstance?.movie?.id}">${itemUserInstance?.movie?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemUserInstance?.tvShow}">
				<li class="fieldcontain">
					<span id="tvShow-label" class="property-label"><g:message code="itemUser.tvShow.label" default="Tv Show" /></span>
					
						<span class="property-value" aria-labelledby="tvShow-label"><g:link controller="TVShow" action="show" id="${itemUserInstance?.tvShow?.id}">${itemUserInstance?.tvShow?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemUserInstance?.book}">
				<li class="fieldcontain">
					<span id="book-label" class="property-label"><g:message code="itemUser.book.label" default="Book" /></span>
					
						<span class="property-value" aria-labelledby="book-label"><g:link controller="book" action="show" id="${itemUserInstance?.book?.id}">${itemUserInstance?.book?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemUserInstance?.rating}">
				<li class="fieldcontain">
					<span id="rating-label" class="property-label"><g:message code="itemUser.rating.label" default="Rating" /></span>
					
						<span class="property-value" aria-labelledby="rating-label"><g:fieldValue bean="${itemUserInstance}" field="rating"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemUserInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="itemUser.comments.label" default="Comments" /></span>
					
						<g:each in="${itemUserInstance.comments}" var="c">
						<span class="property-value" aria-labelledby="comments-label"><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${itemUserInstance?.favourite}">
				<li class="fieldcontain">
					<span id="favourite-label" class="property-label"><g:message code="itemUser.favourite.label" default="Favourite" /></span>
					
						<span class="property-value" aria-labelledby="favourite-label"><g:formatBoolean boolean="${itemUserInstance?.favourite}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${itemUserInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="itemUser.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${itemUserInstance?.user?.id}">${itemUserInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:itemUserInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${itemUserInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
