<%@ page import="ivvq.Book" %>



<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'googleID', 'error')} required">
	<label for="googleID">
		<g:message code="book.googleID.label" default="Google ID" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="googleID" pattern="${bookInstance.constraints.googleID.matches}" required="" value="${bookInstance?.googleID}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'isbn13', 'error')} ">
	<label for="isbn13">
		<g:message code="book.isbn13.label" default="Isbn13" />
		
	</label>
	<g:textField name="isbn13" pattern="${bookInstance.constraints.isbn13.matches}" value="${bookInstance?.isbn13}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="book.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${bookInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="book.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${bookInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'author', 'error')} required">
	<label for="author">
		<g:message code="book.author.label" default="Author" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="author" required="" value="${bookInstance?.author}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'pageCount', 'error')} required">
	<label for="pageCount">
		<g:message code="book.pageCount.label" default="Page Count" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="pageCount" type="number" min="0" value="${bookInstance.pageCount}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'image', 'error')} ">
	<label for="image">
		<g:message code="book.image.label" default="Image" />
		
	</label>
	<g:textField name="image" value="${bookInstance?.image}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'publisher', 'error')} required">
	<label for="publisher">
		<g:message code="book.publisher.label" default="Publisher" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="publisher" required="" value="${bookInstance?.publisher}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'publishedDate', 'error')} required">
	<label for="publishedDate">
		<g:message code="book.publishedDate.label" default="Published Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="publishedDate" required="" value="${bookInstance?.publishedDate}"/>

</div>

