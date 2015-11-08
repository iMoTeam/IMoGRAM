
<%@ page import="ivvq.ItemUser; ivvq.Movie" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'movie.label', default: 'Movie')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-movie" class="content scaffold-show" role="main">

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>


                <table class="table">
                    <tr>
                        <g:if test="${movieInstance?.poster}">
                            <td><img src="${movieInstance.poster}" alt="${movieInstance.title}"></td>
                        </g:if>
                        <td>
                            <% def existe = false%>
                            <g:each var="m" in="${ItemUser?.list()}">
                                <g:if test="${movieInstance?.imdbID == m.movie?.imdbID && session['currentUser'] == m?.user}" >
                                    Vous avez déjà noté pour ce film ! votre note : ${m.rating} / 10
                                </br>
                                    Vous voulez modifier votre note pour ce film ?
                                    <input type="hidden" name="rated" value="true" />
                                    <g:actionSubmit name="modifier" value="Modifier" onclick="showForm()"/>
                                    <g:form controller="itemUser" action="rateItem" id="formRateItem" style="display: none;">
                                        <g:radio name="itemRating" id="note01" value="1" />
                                        <g:radio name="itemRating" id="note02" value="2" />
                                        <g:radio name="itemRating" id="note03" value="3" />
                                        <g:radio name="itemRating" id="note04" value="4" />
                                        <g:radio name="itemRating" id="note05" value="5" />
                                        <input type="hidden" name="rated" value="false" />
                                        <input type="hidden" name="movieId" value="${movieInstance?.imdbID}" />
                                        </br>
                                        <g:submitButton name="voter" value="Voter" />
                                    </g:form>
                                    <% existe = true%>
                                    <g:javascript type="text/javascript">
                                        function showForm() {
                                            var form = document.getElementById('formRateItem');
                                            form.style.display = 'block';
                                        }
                                    </g:javascript>
                                </g:if>
                             </g:each>

                            <g:if test="${session['currentUser'] != null && existe == false}" >
                                <g:form controller="itemUser" action="rateItem">
                                    <g:radio name="itemRating" id="note01" value="1" />
                                    <g:radio name="itemRating" id="note02" value="2" />
                                    <g:radio name="itemRating" id="note03" value="3" />
                                    <g:radio name="itemRating" id="note04" value="4" />
                                    <g:radio name="itemRating" id="note05" value="5" />
                                    <input type="hidden" name="rated" value="false" />
                                    <input type="hidden" name="movieId" value="${movieInstance?.imdbID}" />
                                    </br>
                                    <g:submitButton name="voter" value="Voter" />
                                </g:form>
                            </g:if>
                        </td>
						<td>
                            <g:if test="${movieInstance?.title}">
                                <div class="row"><h2><strong><g:fieldValue bean="${movieInstance}" field="title"/></strong></h2></div>
                            </g:if>
                            <g:if test="${movieInstance?.director}">
                                <div class="row"><g:fieldValue bean="${movieInstance}" field="director"/></div>
                            </g:if>
                            <g:if test="${movieInstance?.genre}">
                                <div class="row"><g:fieldValue bean="${movieInstance}" field="genre"/></div>
                            </g:if>
                            <g:if test="${movieInstance?.runtime}">
                                <div class="row"><g:fieldValue bean="${movieInstance}" field="runtime"/></div>
                            </g:if>
                            <g:if test="${movieInstance?.releaseDate}">
                                <div class="row"><g:fieldValue bean="${movieInstance}" field="releaseDate"/></div>
                            </g:if>
                        </td>
                    </tr>
                </table>
			
				<g:if test="${movieInstance?.writers}">
					<h2>Scénariste(s)</h2>
					<span class="property-value" aria-labelledby="writers-label"><g:fieldValue bean="${movieInstance}" field="writers"/></span>
				</g:if>
			
				<g:if test="${movieInstance?.actors}">
					<h2>Acteurs principaux</h2>
                    <span class="property-value" aria-labelledby="actors-label"><g:fieldValue bean="${movieInstance}" field="actors"/></span>
				</g:if>
			
				<g:if test="${movieInstance?.plot}">
					<h2>Résumé</h2>
                    <span class="property-value" aria-labelledby="plot-label"><g:fieldValue bean="${movieInstance}" field="plot"/></span>
				</g:if>
		</div>
	</body>
</html>
