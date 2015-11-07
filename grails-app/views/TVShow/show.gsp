
<%@ page import="ivvq.TVShow; ivvq.ItemUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'TVShow.label', default: 'TVShow')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-TVShow" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

				<table class="table">
					<tr>

						<g:if test="${TVShowInstance?.image}">
							<td><img src="${TVShowInstance.image}" alt="${TVShowInstance.title}"></td>
						</g:if>

						<td>
                            <g:if test="${TVShowInstance?.title}">
                                    <h1><strong><g:fieldValue bean="${TVShowInstance}" field="title"/></strong></h1>
                            </g:if>

                            <g:if test="${TVShowInstance?.network}">
                                <div class="row"><g:fieldValue bean="${TVShowInstance}" field="network"/></div>
                            </g:if>

                            <g:if test="${TVShowInstance?.runtime}">
                                <div class="row"><g:fieldValue bean="${TVShowInstance}" field="runtime"/> minutes.</div>
                            </g:if>

                            <g:if test="${TVShowInstance?.airedEpisodes}">
                                <div class="row"> <g:fieldValue bean="${TVShowInstance}" field="airedEpisodes"/> épisodes.</div>
                            </g:if>

                            <g:if test="${TVShowInstance?.releaseDate}">
                                <div class="row"><g:fieldValue bean="${TVShowInstance}" field="releaseDate"/></div>
                            </g:if>
						</td>
					</tr>
				</table>
			
				<g:if test="${TVShowInstance?.overview}">
					<h2>Résumé</h2>
					
						<span class="property-value" aria-labelledby="overview-label"><g:fieldValue bean="${TVShowInstance}" field="overview"/></span>

				</g:if>
			
				<g:if test="${TVShowInstance?.genres}">
					<h2>Genre</h2>
						<g:each in="${TVShowInstance.genres}" var="g">
						<span class="property-value" aria-labelledby="genres-label"><g:link controller="arrayClass" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
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
            <input type="hidden" name="itemTVShowId" value="${TVShowInstance?.imdbID}">
            <g:submitButton name="Commenter" value="Commenter" style="background-color: #999999"/>
        </g:form>

        </div>
    </g:if>
    <div>
        <table style="width: 100%; margin-top: 20px">
            <g:each var="m" in="${ItemUser?.list()}">
                <g:if test="${TVShowInstance?.imdbID == m.tvShow?.imdbID }" >
                    <g:each var="n" in="${m.comments.toList()}">
                        <tr class="bg-info">
                            <td><a  style="color: rgba(36, 34, 255, 0.87); text-decoration: none" href="${createLink(controller:'user', action:'show', id: n.user.id)}"><strong>${n.user}</strong></a></td>
                            <td><strong>Title: ${n.title}</strong></td>
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
