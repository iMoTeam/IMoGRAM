
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
			<ol class="property-list TVShow">

				<table>
					<tr>

						<td>IMAGE</td>

						<td>
                            <g:if test="${TVShowInstance?.title}">
                                <li class="fieldcontain">
                                    <h2><g:fieldValue bean="${TVShowInstance}" field="title"/></h2>
                                </li>
                            </g:if>

                            <g:if test="${TVShowInstance?.network}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${TVShowInstance}" field="network"/>
                                </li>
                            </g:if>

                            <g:if test="${TVShowInstance?.runtime}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${TVShowInstance}" field="runtime"/> minutes.
                                </li>
                            </g:if>

                            <g:if test="${TVShowInstance?.airedEpisodes}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${TVShowInstance}" field="airedEpisodes"/> épisodes.
                                </li>
                            </g:if>

                            <g:if test="${TVShowInstance?.releaseDate}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${TVShowInstance}" field="releaseDate"/>
                                </li>
                            </g:if>
						</td>
					</tr>
				</table>
			
				<g:if test="${TVShowInstance?.overview}">
				<li class="fieldcontain">
					<span id="overview-label" class="property-label">Résumé</span>
					
						<span class="property-value" aria-labelledby="overview-label"><g:fieldValue bean="${TVShowInstance}" field="overview"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${TVShowInstance?.genres}">
				<li class="fieldcontain">
					<span id="genres-label" class="property-label">Genre</span>
					
						<g:each in="${TVShowInstance.genres}" var="g">
						<span class="property-value" aria-labelledby="genres-label"><g:link controller="arrayClass" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>

			
			</ol>
		</div>
	</body>
</html>
