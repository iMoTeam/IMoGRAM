
<%@ page import="ivvq.User; ivvq.Book" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-book" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

                <table class="table">
                    <tr>

                        <g:if test="${bookInstance?.image}">
                            <td><img src="${bookInstance?.image}" alt="${bookInstance?.title}"></td>
                        </g:if>

                        <td>
                            <g:if test="${bookInstance?.title}">
                                   <h2><strong><g:fieldValue bean="${bookInstance}" field="title"/></strong></h2>
                            </g:if>

                            <g:if test="${bookInstance?.author}">
                                    <g:fieldValue bean="${bookInstance}" field="author"/>
                            </g:if>

                            <g:if test="${bookInstance?.pageCount}">
                                    <g:fieldValue bean="${bookInstance}" field="pageCount"/> pages.
                            </g:if>

                            <g:if test="${bookInstance?.publishedDate}">
                                    <g:fieldValue bean="${bookInstance}" field="publishedDate"/>
                            </g:if>
                            <g:if test="${((User)session["currentUser"])}">
                                <g:if test="${isFavourite}">
                                    <div><g:link action="deleteToFavourite" id="${bookInstance.id}"><div class="btn btn-lg btn-primary">Supprimer de mes favoris</div></g:link></div>
                                </g:if>
                                <g:else>
                                    <div><g:link action="addToFavourite" id="${bookInstance.id}"><div class="btn btn-lg btn-primary">Ajouter à mes favoris</div></g:link></div>
                                </g:else>
                            </g:if>
                        </td>
                    </tr>
                </table>
			
				<g:if test="${bookInstance?.isbn13}">
					<h2>ISBN13</h2>
						<span class="property-value" aria-labelledby="isbn13-label"><g:fieldValue bean="${bookInstance}" field="isbn13"/></span>
				</g:if>
			
				<g:if test="${bookInstance?.description}">
					<h2>Résumé</h2>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${bookInstance}" field="description"/></span>
				</g:if>

		</div>
	</body>
</html>
