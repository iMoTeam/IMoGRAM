<%@ page import="ivvq.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>Welcome to iMoGRAM</title>
	<style type="text/css" media="screen">
	#status {
		background-color: #eee;
		border: .2em solid #fff;
		margin: 2em 2em 1em;
		padding: 1em;
		width: 12em;
		float: left;
		-moz-box-shadow: 0px 0px 1.25em #ccc;
		-webkit-box-shadow: 0px 0px 1.25em #ccc;
		box-shadow: 0px 0px 1.25em #ccc;
		-moz-border-radius: 0.6em;
		-webkit-border-radius: 0.6em;
		border-radius: 0.6em;
	}

	.ie6 #status {
		display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
	}

	#status ul {
		font-size: 0.9em;
		list-style-type: none;
		margin-bottom: 0.6em;
		padding: 0;
	}

	#status li {
		line-height: 1.3;
	}

	#status h1 {
		text-transform: uppercase;
		font-size: 1.1em;
		margin: 0 0 0.3em;
	}

	#page-body {
		margin: 2em 1em 1.25em 18em;
	}

	h2 {
		margin-top: 1em;
		margin-bottom: 0.3em;
		font-size: 1em;
	}

	p {
		line-height: 1.5;
		margin: 0.25em 0;
	}

	#controller-list ul {
		list-style-position: inside;
	}

	#controller-list li {
		line-height: 1.3;
		list-style-position: inside;
		margin: 0.25em 0;
	}

	@media screen and (max-width: 480px) {
		#status {
			display: none;
		}

		#page-body {
			margin: 0 1em 1em;
		}

		#page-body h1 {
			margin-top: 0;
		}
	}
	</style>
</head>
<br>
<h1 style="color:#D074F2;">Films</h1>
<hr class="separCate">
<g:each var="m" in="${Movie.list().subList(0, 2)}">
	<table>
		<tr>
			<td rowspan="4">
				<a href="#">
					<g:if test="${!m.poster.contains("jpg") && !m.poster.contains("png")}">
						<img  style="width: 100px;" src="./images/film.jpg"/>
					</g:if>
					<g:else>
						<img  style="width: 100px;" src="${m.poster}"/>
					</g:else>
				</a>
			</td>
			<td><a href="#" class="linkDesc" style="color: black;"><h2>${m.title}</h2></a></td>
		</tr>
		<tr>
			<td>Film de ${m.director}</td>
		</tr>
		<tr>
			<td>Sortie : ${m.releaseDate.substring(0, 10)}</td>
		</tr>
		<tr>
			<td>${m.plot}</td>
		</tr>
	</table>
    <hr class="separ">
</g:each>

<h1  style="color:#E74C3C;">S&eacute;ries</h1>
<hr class="separCate">
<table>
    <g:each var="s" in="${TVShow.list().subList(0, 3)}">
        <tr>
			<td rowspan="4">
				<a href="#">
					<g:if test="${!s.overview.contains("jpg") && !s.overview.contains("png")}">
						<img  style="width: 100px;" src="./images/serie.jpg"/>
					</g:if>
					<g:else>
						<img  style="width: 100px;" src="${s.overview}"/>
					</g:else>
				</a>
			</td>
            <td><a href="#" class="linkDesc" style="color: black;"><h2>${s.title}(${s.releaseDate.substring(0, 4)})</h2></a></td>
        </tr>
        <tr>
            <td>S&eacute;ries de ${s.network}</td>
        </tr>
        <tr>
            <td>Sortie : ${s.releaseDate.substring(0, 10)}</td>
        </tr>
        <tr>
            <td>${s.overview}</td>
        </tr>
    </g:each>
</table>
    <hr class="separ">

<h1 style="color:#F2CF66;">Livres</h1>
<hr class="separCate">
<table>
    <g:each var="b" in="${Book.list().subList(0, 3)}">
        <tr>
			<td rowspan="4">
				<a href="#">
					<g:if test="${!b.image.contains("jpg") && !b.image.contains("png")}">
						<img  style="width: 100px;" src="./images/livre.png"/>
					</g:if>
					<g:else>
						<img  style="width: 100px;" src="${b.image}"/>
					</g:else>
				</a>
			</td>
            <td><a href="#" class="linkDesc" style="color: black;"><h2>${b.title}(${b.publishedDate.substring(0, 4)})</h2></a></td>
        </tr>
        <tr>
            <td>livre de ${b.author}</td>
        </tr>
        <tr>
            <td>Sortie : ${b.publishedDate}</td>
        </tr>
        <tr>
            <td>${b.description}</td>
        </tr>
    </g:each>
</table>
    <hr class="separ">
</body>
</html>
