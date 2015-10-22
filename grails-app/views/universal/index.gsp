<%--
  Created by IntelliJ IDEA.
  User: kilosakeyrocker
  Date: 14/10/15
  Time: 08:41
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Resultats de recherche</title>
</head>

<body>
<table>
    <h2>Les films:</h2>
    <g:if test="${movieInstanceCount <= 0}">
        <h3>Aucun resultat pour les films </h3>
    </g:if>
<g:else>
    <thead>
    <tr>

        <th><g:message code="movie.poster.label" default="Poster" /></th>
        <th><g:message code="movie.title.label" default="Title" /></th>
        <th><g:message code="movie.plot.label" default="Description" /></th>

    </tr>
    </thead>
    <tbody>
    <div>
        <g:each in="${movieInstanceList}" status="i" var="movieInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>
                    <a href="#">
                        <g>
                            <img  style="width: 100px;" src="${movieInstance.poster}"/>
                        </g>
                    </a>
                </td>
                <td>${fieldValue(bean: movieInstance, field: "title")}</td>
                <td>${fieldValue(bean: movieInstance, field: "plot")}</td>
            </tr>
        </g:each>
    </div>
    </tbody>
</g:else>
</table>
<br><br>
<table>
    <h2>Les livres: </h2>
    <g:if test="${bookInstanceCount <= 0}">
        <h3>Aucun resultat pour les livres </h3>
    </g:if>
<g:else>
    <thead>
    <tr>

        <th><g:message code="book.image.label" default="Poster" /></th>
        <th><g:message code="book.title.label" default="Title" /></th>
        <th><g:message code="book.description.label" default="Description" /></th>

    </tr>
    </thead>
    <div>
        <g:each in="${bookInstanceList}" status="i" var="bookInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>
                    <a href="#">
                        <g>
                            <img  style="width: 100px;" src="${bookInstance.image}"/>
                        </g>
                    </a>
                </td>
                <td>${fieldValue(bean: bookInstance, field: "title")}</td>
                <td>${fieldValue(bean: bookInstance, field: "description")}</td>
            </tr>
        </g:each>
    </div>
</g:else>
</table>
<br><br>
<table>
    <h2>TV Shows:</h2>
<g:if test="${tvShowInstanceCount <= 0}">
    <h3>Aucun resultat pour les TV Shows </h3>
</g:if>
    <g:else>
        <thead>
        <tr>

            <th><g:message code="tvshow.image.label" default="Poster" /></th>
            <th><g:message code="tvshow.title.label" default="Title" /></th>
            <th><g:message code="tvshow.overview.label" default="Description" /></th>

        </tr>
        </thead>
    <div>
        <g:each in="${tvShowInstanceList}" status="i" var="tvShowInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>
                    <a href="#">
                        <g>
                            <img  style="width: 100px;" src="${tvShowInstance.image}"/>
                        </g>
                    </a>
                </td>
                <td>${fieldValue(bean: tvShowInstance, field: "title")}</td>
                <td>${fieldValue(bean: tvShowInstance, field: "overview")}</td>
            </tr>
        </g:each>
    </div>
    </g:else>
</table>
</body>
</html>