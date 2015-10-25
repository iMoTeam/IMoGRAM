<%@ page import="ivvq.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>Welcome to iMoGRAM</title>
	<style>
	.separCate {
	height: 20px;
	background: url(./images/hr.PNG)  center;
	border: none;
	}
	</style>

</head>
<br>
<hr class="separCate">
<div class="panel panel-info">
<div class="panel-heading "><h1><strong><span class="glyphicon glyphicon-film"></span> Films</strong></h1></div>
<table class="table">
<g:each var="m" in="${Movie.list().subList(0, 3)}">
		<tr>
			<td rowspan="4">
				<g:link controller="movie" action="show" id="${m.id}">
					<g:if test="${!m.poster}">
						<img  style="width: 150px;" src="./images/film.jpg"/>
					</g:if>
					<g:else>
						<img  style="width: 150px;" src="${m.poster}"/>
					</g:else>
				</g:link>
			</td>
			<td><a href="${createLink(controller: "movie", action: "show", id: "${m.id}")}" class="text-info"><h2><strong>${m.title}</strong></h2></a></td>
		</tr>
		<tr class="info">
			<td>Film de ${m.director}</td>
		</tr>
		<tr class="info">
			<td>Sortie : ${m.releaseDate.substring(0, 10)}</td>
		</tr>
		<tr class="info">
			<td>${m.plot}</td>
		</tr>


</g:each>
</table>
</div>
<hr class="separ">

<hr class="separCate">
<div class="panel panel-danger">
<div class="panel-heading "><h1><strong><span class="glyphicon glyphicon-sound-dolby"></span> Séries</strong></h1></div>
<table class="table">
    <g:each var="s" in="${TVShow.list().subList(0, 3)}">
        <tr>
			<td rowspan="4">
				<g:link controller="TVShow" action="show" id="${s.id}">
					<g:if test="${!s.image}">
						<img  style="width: 150px;" src="./images/serie.jpg"/>
					</g:if>
					<g:else>
						<img  style="width: 150px;" src="${s.image}"/>
					</g:else>
				</g:link>
			</td>
            <td><a href="${createLink(controller: "TVShow", action: "show", id: "${s.id}")}" class="text-danger"><h2><strong>${s.title} (${s.releaseDate.substring(0, 4)})</strong></h2></a></td>
        </tr>
        <tr class="danger">
            <td>S&eacute;ries de ${s.network}</td>
        </tr>
        <tr class="danger">
            <td>Sortie : ${s.releaseDate.substring(0, 10)}</td>
        </tr>
        <tr class="danger">
            <td>${s.overview}</td>
        </tr>
    </g:each>
</table>
</div>
    <hr class="separ">

<hr class="separCate">
<div class="panel panel-warning">
<div class="panel-heading "><h1><strong><span class="glyphicon glyphicon-book"></span> Livres</strong></h1></div>
<table class="table">
    <g:each var="b" in="${Book.list().subList(0, 3)}">
        <tr>
			<td rowspan="4">
				<g:link controller="book" action="show" id="${b.id}">
					<g:if test="${!b.image}">
						<img  style="width: 150px;" src="./images/livre.png"/>
					</g:if>
					<g:else>
						<img  style="width: 150px;" src="${b.image}"/>
					</g:else>
				</g:link>
			</td>
            <td><a href="${createLink(controller: "book", action: "show", id: "${b.id}")}" class="text-warning"><h2><strong>${b.title} (${b.publishedDate.substring(0, 4)})</strong></h2></a></td>
        </tr>
        <tr class="warning">
            <td>Livre de ${b.author}</td>
        </tr>
        <tr class="warning">
            <td>Sortie : ${b.publishedDate}</td>
        </tr>
        <tr class="warning">
            <td>${b.description}</td>
        </tr>
    </g:each>
</table>
</div>
    <hr class="separ">
</body>
</html>
