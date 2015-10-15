
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
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-movie" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
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

                            <g:if test="${movieInstance?.runtime}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${movieInstance}" field="runtime"/>
                                </li>
                            </g:if>

                            <g:if test="${movieInstance?.director}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${movieInstance}" field="director"/>
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
					<span id="writers-label" class="property-label"><g:message code="movie.writers.label" default="Writers" /></span>
					
						<span class="property-value" aria-labelledby="writers-label"><g:fieldValue bean="${movieInstance}" field="writers"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movieInstance?.actors}">
				<li class="fieldcontain">
					<span id="actors-label" class="property-label"><g:message code="movie.actors.label" default="Actors" /></span>
					
						<span class="property-value" aria-labelledby="actors-label"><g:fieldValue bean="${movieInstance}" field="actors"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movieInstance?.plot}">
				<li class="fieldcontain">
					<span id="plot-label" class="property-label"><g:message code="movie.plot.label" default="Plot" /></span>
					
						<span class="property-value" aria-labelledby="plot-label"><g:fieldValue bean="${movieInstance}" field="plot"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movieInstance?.genre}">
				<li class="fieldcontain">
					<span id="genre-label" class="property-label"><g:message code="movie.genre.label" default="Genre" /></span>
					
						<span class="property-value" aria-labelledby="genre-label"><g:fieldValue bean="${movieInstance}" field="genre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${movieInstance?.country}">
				<li class="fieldcontain">
					<span id="country-label" class="property-label"><g:message code="movie.country.label" default="Country" /></span>
					
						<span class="property-value" aria-labelledby="country-label"><g:fieldValue bean="${movieInstance}" field="country"/></span>
					
				</li>
				</g:if>
			</ol>
		</div>
	</body>
</html>
