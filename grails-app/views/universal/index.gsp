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

                <td>${fieldValue(bean: movieInstance, field: "poster")}</td>
                <td>${fieldValue(bean: movieInstance, field: "title")}</td>
                <td>${fieldValue(bean: movieInstance, field: "plot")}</td>
            </tr>
        </g:each>
    </div>
    <div>
        <g:each in="${bookInstanceList}" status="i" var="bookInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: bookInstance, field: "image")}</td>
                <td>${fieldValue(bean: bookInstance, field: "title")}</td>
                <td>${fieldValue(bean: bookInstance, field: "description")}</td>
            </tr>
        </g:each>
    </div>
    <div>
        <g:each in="${tvShowInstanceList}" status="i" var="tvShowInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: tvShowInstance, field: "image")}</td>
                <td>${fieldValue(bean: tvShowInstance, field: "title")}</td>
                <td>${fieldValue(bean: tvShowInstance, field: "overview")}</td>
            </tr>
        </g:each>
    </div>

    </tbody>
</table>
</body>
</html>