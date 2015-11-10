
<%@ page import="java.math.MathContext; ivvq.ItemUser; ivvq.Movie" %>
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
                        <td>
                            <% double average = 0
                            int n = 0 %>
                            <g:each var="m" in="${ItemUser?.list()}">
                                <g:if test="${session['currentUser'] != null && m.rating != null && movieInstance?.imdbID == m.movie?.imdbID}" >
                                    <%
                                        average += m.rating
                                        n += 1
                                    %>
                                </g:if>
                            </g:each>
                            <g:if test="${session['currentUser'] == null}">
                                <a href="../../user/loginUser.gsp"><h4>Connectez-vous pour noter ce film !</h4></a>
                            </g:if>
                            <g:if test="${session['currentUser'] != null && n == 0}">
                               <h4>Soyez le premier à noter ce film !</h4>
                            </g:if>
                            <g:if test="${session['currentUser'] != null && n != 0}">
                                <%
                                    average /= n
                                    java.math.MathContext mc = new MathContext(2)
                                    BigDecimal resAverage = new BigDecimal(average, mc)
                                %>
                                Il y a déjà <strong class="text-danger">${n}</strong> utilisateur(s) ont noté sur ce film </br>
                                La note moyenne de ce film est : <strong class="text-danger">${resAverage}</strong></br>
                            </g:if>
                            <% def existe = false%>

                            <g:each var="m" in="${ItemUser?.list()}">
                                <g:if test="${m.rating != null && movieInstance?.imdbID == m.movie?.imdbID && session['currentUser'] == m?.user}" >
                                    Vous avez déjà noté pour ce film ! votre note : <strong class="text-danger">${m.rating} / 10</strong>
                                    </br>
                                    Voulez-vous modifier votre note pour ce film ?

                                    <g:form controller="itemUser" action="showFormModify">
                                        <input type="hidden" name="movieId" value="${movieInstance?.imdbID}" />
                                        <g:submitButton class="btn btn-primary" name="modifier" value="Modifier" />
                                    </g:form>
                                    <g:if test="${params.modify}">
                                        <g:form controller="itemUser" action="rateItem">
                                            </br>
                                            <fieldset>
                                                <legend>Veuillez choisir une note !</legend>

                                                    <label for="note01">1</label>
                                                    <g:radio name="itemRating" id="note01" value="1" />&nbsp;&nbsp;

                                                    <label for="note02">2</label>
                                                    <g:radio name="itemRating" id="note02" value="2" />&nbsp;&nbsp;

                                                    <label for="note03">3</label>
                                                    <g:radio name="itemRating" id="note03" value="3" />&nbsp;&nbsp;

                                                    <label for="note04">4</label>
                                                    <g:radio name="itemRating" id="note04" value="4" />&nbsp;&nbsp;

                                                    <label for="note05">5</label>
                                                    <g:radio name="itemRating" id="note05" value="5" />&nbsp;&nbsp;

                                                    <label for="note06">6</label>
                                                    <g:radio name="itemRating" id="note06" value="6" />&nbsp;&nbsp;

                                                    <label for="note07">7</label>
                                                    <g:radio name="itemRating" id="note07" value="7" />&nbsp;&nbsp;

                                                    <label for="note08">8</label>
                                                    <g:radio name="itemRating" id="note08" value="8" /> &nbsp;&nbsp;

                                                    <label for="note09">9</label>
                                                    <g:radio name="itemRating" id="note09" value="9" /> &nbsp;&nbsp;

                                                    <label for="note10">10</label>
                                                    <g:radio name="itemRating" id="note10" value="10" />

                                                <input type="hidden" name="movieId" value="${movieInstance?.imdbID}" />

                                                </br>
                                                <g:submitButton class="btn btn-primary" name="voter" value="Voter" />
                                            </fieldset>

                                        </g:form>
                                    </g:if>
                                    <% existe = true%>
                                </g:if>
                            </g:each>

                            <g:if test="${session['currentUser'] != null && existe == false}" >
                                <g:form controller="itemUser" action="rateItem">
                                    <fieldset>
                                        <legend>Veuillez donner une note !</legend>
                                        <label for="note01">1</label>
                                        <g:radio name="itemRating" id="note01" value="1" />&nbsp;&nbsp;

                                        <label for="note02">2</label>
                                        <g:radio name="itemRating" id="note02" value="2" />&nbsp;&nbsp;

                                        <label for="note03">3</label>
                                        <g:radio name="itemRating" id="note03" value="3" />&nbsp;&nbsp;

                                        <label for="note04">4</label>&nbsp;
                                        <g:radio name="itemRating" id="note04" value="4" />&nbsp;&nbsp;

                                        <label for="note05">5</label>
                                        <g:radio name="itemRating" id="note05" value="5" />&nbsp;&nbsp;

                                        <label for="note06">6</label>
                                        <g:radio name="itemRating" id="note06" value="6" />&nbsp;&nbsp;

                                        <label for="note07">7</label>
                                        <g:radio name="itemRating" id="note07" value="7" />&nbsp;&nbsp;

                                        <label for="note08">8</label>
                                        <g:radio name="itemRating" id="note08" value="8" />&nbsp;&nbsp;

                                        <label for="note09">9</label>
                                        <g:radio name="itemRating" id="note09" value="9" />&nbsp;&nbsp;

                                        <label for="note10">10</label>
                                        <g:radio name="itemRating" id="note10" value="10" />
                                    </fieldset>
                                    <input type="hidden" name="movieId" value="${movieInstance?.imdbID}" />

                                    </br>
                                    <g:submitButton class="btn btn-primary" name="voter" value="Voter" />
                                </g:form>
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
