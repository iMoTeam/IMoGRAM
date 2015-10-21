
<%@ page import="ivvq.Movie" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'movie.label', default: 'Movie')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-movie" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="show-movie" class="content scaffold-show" role="main">

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

			<ol class="property-list movie">

                <table>
                    <tr>
                        <g:if test="${movieInstance?.poster}">
                            <td><img src="${movieInstance.poster}" alt="${movieInstance.title}"></td>
                        </g:if>

						<td>
                            <g:if test="${movieInstance?.title}">
                                <h2><g:fieldValue bean="${movieInstance}" field="title"/></h2>
                            </g:if>

                            <g:if test="${movieInstance?.director}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${movieInstance}" field="director"/>
                                </li>
                            </g:if>

                            <g:if test="${movieInstance?.genre}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${movieInstance}" field="genre"/>
                                </li>
                            </g:if>

                            <g:if test="${movieInstance?.runtime}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${movieInstance}" field="runtime"/>
                                </li>
                            </g:if>

                            <g:if test="${movieInstance?.releaseDate}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${movieInstance}" field="releaseDate"/>
                                </li>
                            </g:if>
                        </td>
                    </tr>
                </table>
			
				<g:if test="${movieInstance?.writers}">
				<li class="fieldcontain">
					<span  class="property-label">Scénariste(s)</span>
					<span class="property-value" aria-labelledby="writers-label"><g:fieldValue bean="${movieInstance}" field="writers"/></span>
				</li>
				</g:if>
			
				<g:if test="${movieInstance?.actors}">
				<li class="fieldcontain">
					<span id="actors-label" class="property-label">Acteurs principaux</span>
                    <span class="property-value" aria-labelledby="actors-label"><g:fieldValue bean="${movieInstance}" field="actors"/></span>
				</li>
				</g:if>
			
				<g:if test="${movieInstance?.plot}">
				<li class="fieldcontain">
					<span id="plot-label" class="property-label">Résumé</span>
                    <span class="property-value" aria-labelledby="plot-label"><g:fieldValue bean="${movieInstance}" field="plot"/></span>
				</li>
				</g:if>
			</ol>
		</div>
	</body>
</html>
