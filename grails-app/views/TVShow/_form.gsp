<%@ page import="ivvq.TVShow" %>



<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'imdbID', 'error')} required">
	<label for="imdbID">
		<g:message code="TVShow.imdbID.label" default="Imdb ID" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="imdbID" pattern="${TVShowInstance.constraints.imdbID.matches}" required="" value="${TVShowInstance?.imdbID}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="TVShow.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${TVShowInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'releaseDate', 'error')} required">
	<label for="releaseDate">
		<g:message code="TVShow.releaseDate.label" default="Release Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="releaseDate" required="" value="${TVShowInstance?.releaseDate}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'runtime', 'error')} required">
	<label for="runtime">
		<g:message code="TVShow.runtime.label" default="Runtime" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="runtime" required="" value="${TVShowInstance?.runtime}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'network', 'error')} required">
	<label for="network">
		<g:message code="TVShow.network.label" default="Network" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="network" required="" value="${TVShowInstance?.network}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'overview', 'error')} required">
	<label for="overview">
		<g:message code="TVShow.overview.label" default="Overview" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="overview" required="" value="${TVShowInstance?.overview}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'genres', 'error')} ">
	<label for="genres">
		<g:message code="TVShow.genres.label" default="Genres" />
		
	</label>
	<g:select name="genres" from="${ivvq.ArrayClass.list()}" multiple="multiple" optionKey="id" size="5" value="${TVShowInstance?.genres*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'airedEpisodes', 'error')} required">
	<label for="airedEpisodes">
		<g:message code="TVShow.airedEpisodes.label" default="Aired Episodes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="airedEpisodes" type="number" min="0" value="${TVShowInstance.airedEpisodes}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="TVShow.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="country" required="" value="${TVShowInstance?.country}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'actors', 'error')} ">
	<label for="actors">
		<g:message code="TVShow.actors.label" default="Actors" />
		
	</label>
	<g:select name="actors" from="${ivvq.Role.list()}" multiple="multiple" optionKey="id" size="5" value="${TVShowInstance?.actors*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'crews', 'error')} ">
	<label for="crews">
		<g:message code="TVShow.crews.label" default="Crews" />
		
	</label>
	<g:select name="crews" from="${ivvq.ArrayClass.list()}" multiple="multiple" optionKey="id" size="5" value="${TVShowInstance?.crews*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: TVShowInstance, field: 'seasons', 'error')} ">
	<label for="seasons">
		<g:message code="TVShow.seasons.label" default="Seasons" />
		
	</label>
	<g:select name="seasons" from="${ivvq.Season.list()}" multiple="multiple" optionKey="id" size="5" value="${TVShowInstance?.seasons*.id}" class="many-to-many"/>

</div>

