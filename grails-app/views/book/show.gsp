
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
            <input type="hidden" name="itemBookId" value="${bookInstance?.isbn13}">
            <g:submitButton name="Commenter" value="Commenter" style="background-color: #999999"/>
        </g:form>

    </div>
    </g:if>
    <div>
        <table style="width: 100%; margin-top: 20px">
        <g:each var="m" in="${ItemUser?.list()}">
            <g:if test="${bookInstance?.isbn13 == m.book?.isbn13 }" >
                <g:each var="n" in="${m.comments.toList()}">
            <tr class="bg-info">
                <td><a  style="color: rgba(36, 34, 255, 0.87); text-decoration: none" href="${createLink(controller:'user', action:'show', id: n.user.id)}"><strong>${n.user}</strong></a></td>
                <td><strong>Title: ${n.title}</strong></td>
            </tr>
            <tr class="bg-info">
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

	</body>
</html>
