<%@ page import="ivvq.ItemUser" %>



<div class="fieldcontain ${hasErrors(bean: itemUserInstance, field: 'movie', 'error')} ">
	<label for="movie">
		<g:message code="itemUser.movie.label" default="Movie" />
		
	</label>
	<g:select id="movie" name="movie.id" from="${ivvq.Movie.list()}" optionKey="id" value="${itemUserInstance?.movie?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemUserInstance, field: 'tvShow', 'error')} ">
	<label for="tvShow">
		<g:message code="itemUser.tvShow.label" default="Tv Show" />
		
	</label>
	<g:select id="tvShow" name="tvShow.id" from="${ivvq.TVShow.list()}" optionKey="id" value="${itemUserInstance?.tvShow?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemUserInstance, field: 'book', 'error')} ">
	<label for="book">
		<g:message code="itemUser.book.label" default="Book" />
		
	</label>
	<g:select id="book" name="book.id" from="${ivvq.Book.list()}" optionKey="id" value="${itemUserInstance?.book?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemUserInstance, field: 'rating', 'error')} ">
	<label for="rating">
		<g:message code="itemUser.rating.label" default="Rating" />
		
	</label>
	<g:field name="rating" type="number" min="0" max="10" value="${itemUserInstance.rating}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemUserInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="itemUser.comments.label" default="Comments" />
		
	</label>
	<g:select name="comments" from="${ivvq.Comment.list()}" multiple="multiple" optionKey="id" size="5" value="${itemUserInstance?.comments*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: itemUserInstance, field: 'favourite', 'error')} ">
	<label for="favourite">
		<g:message code="itemUser.favourite.label" default="Favourite" />
		
	</label>
	<g:checkBox name="favourite" value="${itemUserInstance?.favourite}" />

</div>

<div class="fieldcontain ${hasErrors(bean: itemUserInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="itemUser.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${ivvq.User.list()}" optionKey="id" required="" value="${itemUserInstance?.user?.id}" class="many-to-one"/>

</div>

