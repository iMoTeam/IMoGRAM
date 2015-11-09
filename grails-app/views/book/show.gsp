<%@ page import="ivvq.BookController; ivvq.BookService; ivvq.Book; ivvq.ItemUser" %>
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
                            <g:if test="${session["currentUser"]}">
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

            <div class="panel panel-info">
                <g:if test="${bookInstance?.isbn13}">
                    <h2>ISBN13</h2>
                    <span class="property-value" aria-labelledby="isbn13-label"><g:fieldValue bean="${bookInstance}" field="isbn13"/></span>
                </g:if>

                <g:if test="${bookInstance?.description}">
                    <h2>Résumé</h2>

                    <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${bookInstance}" field="description"/></span>
                </g:if>
            </div>


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
        <div class="panel panel-warning">
            <div class="panel-heading"><h2><strong>Donnez votre avis!</strong></h2></div>
            <div class="panel-body">
                <g:form controller="itemUser" action="commentItem">
                    <input id="titre" class="input-field" type="text"  name="title" placeholder="Saisissez le titre..." /><br />
                    <textarea  name="itemComment" class="input-field" rows="4" cols="120" placeholder="Tapez votre commentaire ici..."></textarea><br />
                    </textarea> <br>
                    <input type="hidden" name="itemBookId" value="${bookInstance?.isbn13}">
                    <g:submitButton name="Commenter" value="Commenter" style="background-color: #999999"/>
                </g:form>
            </div>
        </div>


    </g:if>
    <div>
        <div class="panel panel-warning">
            <div class="panel-heading"><h2><strong>Commentaires</strong></h2></div>
            <div class="panel-body">
                <table class="table">
                    <g:each var="m" in="${ItemUser?.list()}">
                        <g:if test="${bookInstance?.isbn13 == m.book?.isbn13 }" >
                            <g:each var="n" in="${m.comments.toList()}">
                                <tr class="bg-warning">
                                    <td><a href="${createLink(controller:'user', action:'show', id: n.user.id)}"><h2 class="text-warning">${n.user}</h2></a></td>
                                    <td><h2>${n.title}</h2></td>
                                </tr>
                                <tr class="bg-warning">
                                    <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${n.date}"/></td>
                                    <td>${n.comment}</td>
                                </tr>
                                <tr  style="height: 5px; background-color: #ffffff; !important;">
                                    <td colspan="2"></td>
                                </tr>
                            </g:each>
                        </g:if>
                    </g:each>
                </table>
            </div>

        </div>



    </div>

	</body>
</html>
