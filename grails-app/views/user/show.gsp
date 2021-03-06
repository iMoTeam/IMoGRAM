<%@ page import="ivvq.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
	<title>${userInstance.username}</title>
</head>

<body>
<br/><br/><br/>

<div style="width: 50%; margin: auto">
	<div class="panel">
        <div class="row vertical-align">
            <h1 class="col-md-2">${userInstance.username}</h1>
            <g:if test="${((User)session["currentUser"]).following.asList().contains(userInstance)}">
                <div class="col-md-2"><g:link action="unfollow" id="${userInstance.id}"><div class="btn btn-lg btn-primary">Ne plus suivre</div></g:link></div>
            </g:if>
            <g:else>
               <div class=" col-md-2"><g:link action="follow" id="${userInstance.id}"><div class="btn btn-lg btn-primary">Suivre</div></g:link></div>
            </g:else>
        </div>
    </div>

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
					<li><g:link controller="user" action="show" id="${userInstance.id}">All</g:link></li>
					<li><g:link controller="user" action="show" params="[type: 'movie']" id="${userInstance.id}">Movies</g:link></li>
					<li><g:link controller="user" action="show" params="[type: 'book']" id="${userInstance.id}">Books</g:link></li>
					<li><g:link controller="user" action="show" params="[type: 'tvShow']" id="${userInstance.id}">Tv Shows</g:link></li>
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
					<li><g:link controller="user" action="show" id="${userInstance.id}">All</g:link></li>
					<li><g:link controller="user" action="show" params="[kind: 'rating']" id="${userInstance.id}">Notés</g:link></li>
					<li><g:link controller="user" action="show" params="[kind: 'interested']" id="${userInstance.id}">Envie de voir</g:link></li>
					<li><g:link controller="user" action="show" params="[kind: 'favourite']" id="${userInstance.id}">Coup de coeur</g:link></li>
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
        <div class="panel">
            <div class="row">
                <h2>Utilisateurs suivis</h2>
            </div>
            <g:each in="${userInstance.following}" var="f">
                <div class="row"><g:link action="show" id="${f.id}"><div class="btn btn-lg text-primary"><span class="glyphicon glyphicon-user"></span><strong> ${f.username}</strong></div></g:link></div>
            </g:each>
        </div>
	</g:if>
</div>
</body>
</html>
