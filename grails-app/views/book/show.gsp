
<%@ page import="ivvq.Book" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-book" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-book" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list book">
			
				<g:if test="${bookInstance?.googleID}">
				<li class="fieldcontain">
					<span id="googleID-label" class="property-label"><g:message code="book.googleID.label" default="Google ID" /></span>
					
						<span class="property-value" aria-labelledby="googleID-label"><g:fieldValue bean="${bookInstance}" field="googleID"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.isbn13}">
				<li class="fieldcontain">
					<span id="isbn13-label" class="property-label"><g:message code="book.isbn13.label" default="Isbn13" /></span>
					
						<span class="property-value" aria-labelledby="isbn13-label"><g:fieldValue bean="${bookInstance}" field="isbn13"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="book.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${bookInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="book.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${bookInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.author}">
				<li class="fieldcontain">
					<span id="author-label" class="property-label"><g:message code="book.author.label" default="Author" /></span>
					
						<span class="property-value" aria-labelledby="author-label"><g:fieldValue bean="${bookInstance}" field="author"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.pageCount}">
				<li class="fieldcontain">
					<span id="pageCount-label" class="property-label"><g:message code="book.pageCount.label" default="Page Count" /></span>
					
						<span class="property-value" aria-labelledby="pageCount-label"><g:fieldValue bean="${bookInstance}" field="pageCount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.image}">
				<li class="fieldcontain">
					<span id="image-label" class="property-label"><g:message code="book.image.label" default="Image" /></span>
					
						<span class="property-value" aria-labelledby="image-label"><g:fieldValue bean="${bookInstance}" field="image"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.publisher}">
				<li class="fieldcontain">
					<span id="publisher-label" class="property-label"><g:message code="book.publisher.label" default="Publisher" /></span>
					
						<span class="property-value" aria-labelledby="publisher-label"><g:fieldValue bean="${bookInstance}" field="publisher"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.publishedDate}">
				<li class="fieldcontain">
					<span id="publishedDate-label" class="property-label"><g:message code="book.publishedDate.label" default="Published Date" /></span>
					
						<span class="property-value" aria-labelledby="publishedDate-label"><g:fieldValue bean="${bookInstance}" field="publishedDate"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:bookInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${bookInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
