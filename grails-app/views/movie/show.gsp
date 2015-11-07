
<%@ page import="ivvq.Movie; ivvq.ItemUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'movie.label', default: 'Movie')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-movie" class="content scaffold-show" role="main">

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>


                <table class="table">
                    <tr>
                        <g:if test="${movieInstance?.poster}">
                            <td><img src="${movieInstance.poster}" alt="${movieInstance.title}"></td>
                        </g:if>

						<td>
                            <g:if test="${movieInstance?.title}">
                                <div class="row"><h2><strong><g:fieldValue bean="${movieInstance}" field="title"/></strong></h2></div>
                            </g:if>
                            <g:if test="${movieInstance?.director}">
                                <div class="row"><g:fieldValue bean="${movieInstance}" field="director"/></div>
                            </g:if>
                            <g:if test="${movieInstance?.genre}">
                                <div class="row"><g:fieldValue bean="${movieInstance}" field="genre"/></div>
                            </g:if>
                            <g:if test="${movieInstance?.runtime}">
                                <div class="row"><g:fieldValue bean="${movieInstance}" field="runtime"/></div>
                            </g:if>
                            <g:if test="${movieInstance?.releaseDate}">
                                <div class="row"><g:fieldValue bean="${movieInstance}" field="releaseDate"/></div>
                            </g:if>
                        </td>
                    </tr>
                </table>
			
				<g:if test="${movieInstance?.writers}">
					<h2>Scénariste(s)</h2>
					<span class="property-value" aria-labelledby="writers-label"><g:fieldValue bean="${movieInstance}" field="writers"/></span>
				</g:if>
			
				<g:if test="${movieInstance?.actors}">
					<h2>Acteurs principaux</h2>
                    <span class="property-value" aria-labelledby="actors-label"><g:fieldValue bean="${movieInstance}" field="actors"/></span>
				</g:if>
			
				<g:if test="${movieInstance?.plot}">
					<h2>Résumé</h2>
                    <span class="property-value" aria-labelledby="plot-label"><g:fieldValue bean="${movieInstance}" field="plot"/></span>
				</g:if>
		</div>
    <%
        ivvq.User currentUser = session['currentUser']
    %>
    <g:if test="${flash.error}">
        <br>
        <br>
        <div class="errors" role="alert alert-error" style="display: block; color: red">${flash.error}</div>
    </g:if>
    <g:if test="${currentUser != null}">
        <div>
        <g:form controller="itemUser" action="commentItem">
            <hr>Title : </hr>   <g:textField name="title">Saisissez le title</g:textField><br>
            <textarea  name="itemComment" style="width: 80%" >
            </textarea> <br>
            <input type="hidden" name="itemMovieId" value="${movieInstance?.imdbID}">
            <g:submitButton name="Commenter" value="Commenter" style="background-color: #999999"/>
        </g:form>

        </div>
    </g:if>
    <div>
        <table style="width: 100%; margin-top: 20px">
            <g:each var="m" in="${ItemUser?.list()}">
                <g:if test="${movieInstance?.imdbID == m.movie?.imdbID }" >
                    <g:each var="n" in="${m.comments.toList()}">
                        <tr class="bg-info">
                            <td><a  style="color: rgba(36, 34, 255, 0.87); text-decoration: none" href="${createLink(controller:'user', action:'show', id: n.user.id)}"><strong>${n.user}</strong></a></td>
                            <td><h5><strong>Title: ${n.title}</strong></h5></td>
                        </tr>
                        <tr class="bg-info">
                            <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${n.date}"/></td>
                            <td>${n.comment}</td>
                        </tr>
                        <tr style="height: 5px; background-color: #ffffff; !important;">
                            <td colspan="2"></td>
                        </tr>
                    </g:each>
                </g:if>
            </g:each>
        </table>


    </div>
	</body>
</html>
