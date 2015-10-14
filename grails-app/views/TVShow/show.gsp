
<%@ page import="ivvq.TVShow" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'TVShow.label', default: 'TVShow')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-TVShow" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-TVShow" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list TVShow">
			
				<g:if test="${TVShowInstance?.imdbID}">
				<li class="fieldcontain">
					<span id="imdbID-label" class="property-label"><g:message code="TVShow.imdbID.label" default="Imdb ID" /></span>
					
						<span class="property-value" aria-labelledby="imdbID-label"><g:fieldValue bean="${TVShowInstance}" field="imdbID"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="TVShow.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${TVShowInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.releaseDate}">
				<li class="fieldcontain">
					<span id="releaseDate-label" class="property-label"><g:message code="TVShow.releaseDate.label" default="Release Date" /></span>
					
						<span class="property-value" aria-labelledby="releaseDate-label"><g:fieldValue bean="${TVShowInstance}" field="releaseDate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.runtime}">
				<li class="fieldcontain">
					<span id="runtime-label" class="property-label"><g:message code="TVShow.runtime.label" default="Runtime" /></span>
					
						<span class="property-value" aria-labelledby="runtime-label"><g:fieldValue bean="${TVShowInstance}" field="runtime"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.network}">
				<li class="fieldcontain">
					<span id="network-label" class="property-label"><g:message code="TVShow.network.label" default="Network" /></span>
					
						<span class="property-value" aria-labelledby="network-label"><g:fieldValue bean="${TVShowInstance}" field="network"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.overview}">
				<li class="fieldcontain">
					<span id="overview-label" class="property-label"><g:message code="TVShow.overview.label" default="Overview" /></span>
					
						<span class="property-value" aria-labelledby="overview-label"><g:fieldValue bean="${TVShowInstance}" field="overview"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.genres}">
				<li class="fieldcontain">
					<span id="genres-label" class="property-label"><g:message code="TVShow.genres.label" default="Genres" /></span>
					
						<g:each in="${TVShowInstance.genres}" var="g">
						<span class="property-value" aria-labelledby="genres-label"><g:link controller="arrayClass" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.airedEpisodes}">
				<li class="fieldcontain">
					<span id="airedEpisodes-label" class="property-label"><g:message code="TVShow.airedEpisodes.label" default="Aired Episodes" /></span>
					
						<span class="property-value" aria-labelledby="airedEpisodes-label"><g:fieldValue bean="${TVShowInstance}" field="airedEpisodes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.country}">
				<li class="fieldcontain">
					<span id="country-label" class="property-label"><g:message code="TVShow.country.label" default="Country" /></span>
					
						<span class="property-value" aria-labelledby="country-label"><g:fieldValue bean="${TVShowInstance}" field="country"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.actors}">
				<li class="fieldcontain">
					<span id="actors-label" class="property-label"><g:message code="TVShow.actors.label" default="Actors" /></span>
					
						<g:each in="${TVShowInstance.actors}" var="a">
						<span class="property-value" aria-labelledby="actors-label"><g:link controller="role" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.crews}">
				<li class="fieldcontain">
					<span id="crews-label" class="property-label"><g:message code="TVShow.crews.label" default="Crews" /></span>
					
						<g:each in="${TVShowInstance.crews}" var="c">
						<span class="property-value" aria-labelledby="crews-label"><g:link controller="arrayClass" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.seasons}">
				<li class="fieldcontain">
					<span id="seasons-label" class="property-label"><g:message code="TVShow.seasons.label" default="Seasons" /></span>
					
						<g:each in="${TVShowInstance.seasons}" var="s">
						<span class="property-value" aria-labelledby="seasons-label"><g:link controller="season" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:TVShowInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${TVShowInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
