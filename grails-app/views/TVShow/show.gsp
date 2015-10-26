
<%@ page import="ivvq.TVShow" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'TVShow.label', default: 'TVShow')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-TVShow" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

				<table class="table">
					<tr>

						<g:if test="${TVShowInstance?.image}">
							<td><img src="${TVShowInstance.image}" alt="${TVShowInstance.title}"></td>
						</g:if>

						<td>
                            <g:if test="${TVShowInstance?.title}">
                                    <h1><strong><g:fieldValue bean="${TVShowInstance}" field="title"/></strong></h1>
                            </g:if>

                            <g:if test="${TVShowInstance?.network}">
                                <div class="row"><g:fieldValue bean="${TVShowInstance}" field="network"/></div>
                            </g:if>

                            <g:if test="${TVShowInstance?.runtime}">
                                <div class="row"><g:fieldValue bean="${TVShowInstance}" field="runtime"/> minutes.</div>
                            </g:if>

                            <g:if test="${TVShowInstance?.airedEpisodes}">
                                <div class="row"> <g:fieldValue bean="${TVShowInstance}" field="airedEpisodes"/> épisodes.</div>
                            </g:if>

                            <g:if test="${TVShowInstance?.releaseDate}">
                                <div class="row"><g:fieldValue bean="${TVShowInstance}" field="releaseDate"/></div>
                            </g:if>
						</td>
					</tr>
				</table>
			
				<g:if test="${TVShowInstance?.overview}">
					<h2>Résumé</h2>
					
						<span class="property-value" aria-labelledby="overview-label"><g:fieldValue bean="${TVShowInstance}" field="overview"/></span>

				</g:if>
			
				<g:if test="${TVShowInstance?.genres}">
					<h2>Genre</h2>
						<g:each in="${TVShowInstance.genres}" var="g">
						<span class="property-value" aria-labelledby="genres-label"><g:link controller="arrayClass" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
				</g:if>


		</div>
	</body>
</html>
