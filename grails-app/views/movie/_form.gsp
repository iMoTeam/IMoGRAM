<%@ page import="ivvq.Movie" %>



<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'imdbID', 'error')} required">
	<label for="imdbID">
		<g:message code="movie.imdbID.label" default="Imdb ID" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="imdbID" pattern="${movieInstance.constraints.imdbID.matches}" required="" value="${movieInstance?.imdbID}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="movie.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${movieInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'director', 'error')} required">
	<label for="director">
		<g:message code="movie.director.label" default="Director" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="director" required="" value="${movieInstance?.director}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'runtime', 'error')} required">
	<label for="runtime">
		<g:message code="movie.runtime.label" default="Runtime" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="runtime" required="" value="${movieInstance?.runtime}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'releaseDate', 'error')} required">
	<label for="releaseDate">
		<g:message code="movie.releaseDate.label" default="Release Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="releaseDate" required="" value="${movieInstance?.releaseDate}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'writers', 'error')} required">
	<label for="writers">
		<g:message code="movie.writers.label" default="Writers" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="writers" required="" value="${movieInstance?.writers}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'actors', 'error')} required">
	<label for="actors">
		<g:message code="movie.actors.label" default="Actors" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="actors" required="" value="${movieInstance?.actors}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'plot', 'error')} required">
	<label for="plot">
		<g:message code="movie.plot.label" default="Plot" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="plot" required="" value="${movieInstance?.plot}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'genre', 'error')} required">
	<label for="genre">
		<g:message code="movie.genre.label" default="Genre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="genre" required="" value="${movieInstance?.genre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="movie.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="country" required="" value="${movieInstance?.country}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieInstance, field: 'poster', 'error')} required">
	<label for="poster">
		<g:message code="movie.poster.label" default="Poster" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="poster" required="" value="${movieInstance?.poster}"/>

</div>

