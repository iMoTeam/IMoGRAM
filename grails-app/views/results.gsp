<%--
  Created by IntelliJ IDEA.
  User: manantsoa
  Date: 21/10/15
  Time: 20:18
--%>

<%@ page import="ivvq.Book" %>
<%@ page import="ivvq.TVShow" %>
<%@ page import="ivvq.Movie" %>
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

<g:if test="${movieInstanceList}">
<h1 style="color:#D074F2;">Films</h1>
<hr class="separCate">
<g:each var="m" in="${movieInstanceList}">
    <table>
        <tr>
            <td rowspan="4">
                <g:link controller="movie" action="show" id="${m.id}">
                    <g:if test="${!m.poster}">
                        <img  style="width: 100px;" src="../images/film.jpg"/>
                    </g:if>
                    <g:else>
                        <img  style="width: 100px;" src="${m.poster}"/>
                    </g:else>
                </g:link>
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
</g:if>

<g:if test="${tvShowInstanceList}">
<h1  style="color:#E74C3C;">S&eacute;ries</h1>
<hr class="separCate">
<table>
        <g:each var="s" in="${tvShowInstanceList}">
            <tr>
                <td rowspan="4">
                <g:link controller="TVShow" action="show" id="${s.id}">
                        <g:if test="${!s.image}">
                            <img  style="width: 100px;" src="../images/serie.jpg"/>
                        </g:if>
                        <g:else>
                            <img  style="width: 100px;" src="${s.image}"/>
                        </g:else>
                </g:link>
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
</g:if>

<g:if test="${bookInstanceList}">
<h1 style="color:#F2CF66;">Livres</h1>
<hr class="separCate">
<table>
        <g:each var="b" in="${bookInstanceList}">

            <tr>
                    <td rowspan="4">
                        <g:link controller="book" action="show" id="${b.id}">
                            <g:if test="${!b.image}">
                                <img  style="width: 175px;" src="../images/livre.png"/>
                            </g:if>
                            <g:else>
                                <img  style="width: 175px;" src="${b?.image}"/>
                            </g:else>
                        </g:link>
                    </td>
                    <td><h2>${b.title}(${b.publishedDate.substring(0, 4)})</h2></td>
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
</g:if>
</body>
</html>
