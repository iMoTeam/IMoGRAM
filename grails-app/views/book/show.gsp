
<%@ page import="ivvq.Book" %>
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
	</body>
</html>
