<%@ page import="ivvq.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
	<title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<br/><br/><br/>

<div style="width: 50%; margin: auto">
	<h1>${userInstance.username}</h1>
		<div class="btn btn-primary">Suivre</div>
	<div class="panel panel-default">
		<div class="panel-body" style="float:left; width:180px;">
			<div class="dropdown dropdown-menu-right" >
				Univers :
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true">
					${params.type ?: 'All'}
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><g:link controller="user" action="recherche ">All</g:link></li>
					<li><g:link controller="user" action="recherche" params="[type: 'movie']">Movies</g:link></li>
					<li><g:link controller="user" action="recherche" params="[type: 'book']">Books</g:link></li>
					<li><g:link controller="user" action="recherche" params="[type: 'tvShow']">Tv Shows</g:link></li>
				</ul>
			</div>
		</div>
		<div class="panel-body" style="margin-left: 190px;">
			<div class="dropdown dropdown-menu-right" >
				Type d'action :
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true">
					${params.kind ?: 'All'}
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><g:link controller="user" action="recherche ">All</g:link></li>
					<li><g:link controller="user" action="recherche" params="[kind: 'rating']">Notés</g:link></li>
					<li><g:link controller="user" action="recherche" params="[kind: 'interested']">Envie de voir</g:link></li>
					<li><g:link controller="user" action="recherche" params="[kind: 'favourite']">Coup de coeur</g:link></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-body">
			<g:if test="${itemsCount == 0}">
				<p>
					Aucun résultats ! Consulter les films, séries et livres afin qu'ils soient visible !
				</p>
			</g:if>
			<g:else>
				<table>
					<g:each in="${1..nbRows}" var="currentRow">
						<tr>
							<g:each in="${items.subList((currentRow - 1) * nbItemByRow, currentRow != nbRows ? (currentRow - 1) * nbItemByRow + (nbItemByRow) : items.size())}"
									var="ItemUserInstance">
								<td>
									<g:if test="${ItemUserInstance.book != null}">
										<g:link controller="book" action="show" id="${ItemUserInstance.book.id}">
											<img style="width: 150px;" src="${ItemUserInstance.book.image}"
												 class="img-thumbnail"/>
										</g:link>
									</g:if>
									<g:if test="${ItemUserInstance.movie != null}">
										<g:link controller="movie" action="show" id="${ItemUserInstance.movie.id}">
											<img style="width: 150px;" src="${ItemUserInstance.movie.poster}"
												 class="img-thumbnail"/>
										</g:link>
									</g:if>
									<g:if test="${ItemUserInstance.tvShow != null}">
										<g:link controller="TVShow" action="show" id="${ItemUserInstance.tvShow.id}">
											<img style="width: 150px;" src="${ItemUserInstance.tvShow.image}"
												 class="img-thumbnail"/>
										</g:link>
									</g:if>
								</td>
							</g:each>
						</tr>
					</g:each>
				</table>
			</g:else>
		</div>
	</div>

	<div class="pagination">
		<g:paginate action="show" controller="user" total="${itemsCount}" params="${params}"/>
	</div>

	<g:if test="${userInstance.following}">
		<div>
			Utilisateurs suivis
			<div>
				<g:each in="${userInstance.following}" var="f">
					<g:link action="show" id="${f.id}">${f.username}</g:link>
				</g:each>
			</div>
		</div>
	</g:if>
</div>
</body>
</html>
