
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
			<ol class="property-list book">

                <table>
                    <tr>

                        <g:if test="${bookInstance?.image}">
                            <td><img src="${bookInstance?.image}" alt="${bookInstance?.title}"></td>
                        </g:if>

                        <td>
                            <g:if test="${bookInstance?.title}">
                                <li class="fieldcontain">
                                   <h2><g:fieldValue bean="${bookInstance}" field="title"/></h2>
                                </li>
                            </g:if>

                            <g:if test="${bookInstance?.author}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${bookInstance}" field="author"/>
                                </li>
                            </g:if>

                            <g:if test="${bookInstance?.pageCount}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${bookInstance}" field="pageCount"/> pages.
                                </li>
                            </g:if>

                            <g:if test="${bookInstance?.publishedDate}">
                                <li class="fieldcontain">
                                    <g:fieldValue bean="${bookInstance}" field="publishedDate"/>
                                </li>
                            </g:if>
                        </td>
                    </tr>
                </table>
			
				<g:if test="${bookInstance?.isbn13}">
				<li class="fieldcontain">
					<span id="isbn13-label" class="property-label">ISBN13</span>
					
						<span class="property-value" aria-labelledby="isbn13-label"><g:fieldValue bean="${bookInstance}" field="isbn13"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label">Résumé</span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${bookInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
    <%
        ivvq.User currentUser = session['currentUser']
    %>
    <g:if test="${flash.error}">
        <div class="errors" role="alert alert-error" style="display: block">${flash.error}</div>
    </g:if>
    <div>
        <g:form controller="itemUser" action="commentItem">
         <hr>Title : </hr>   <g:textField name="title">Saisissez le title</g:textField>
            <textarea  name="itemComment" style="width: 80%" >
            </textarea>
            <input type="hidden" name="itemId" value="${bookInstance?.isbn13}">
            <g:submitButton name="Commenter" value="Commenter" style="background-color: #999999"/>
        </g:form>

    </div>
    <div>
        <table style="width: 100%">
        <g:each var="m" in="${ItemUser.list()}">
            <g:if test="${bookInstance?.isbn13 == m.book?.isbn13 }" >
                <g:each var="n" in="${m.comments.toList()}">
            <tr>
                <td><a  style="color: rgba(36, 34, 255, 0.87); text-decoration: none" href="${createLink(controller:'user', action:'show', id: n.user.id)}">${n.user}</a></td>
                <td><h5>Title: ${n.title}</h5></td>
            </tr>
            <tr>
                <td>${n.date}</td>
                <td>${n.comment}</td>
            </tr>
                </g:each>
            </g:if>
        </g:each>
        </table>


    </div>
	</body>
</html>
