
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
                            <g:if test="${session['currentUser'] == null && n == 0}">
                                <a href="../user/loginUser.gsp">Connectez-vous et soyez le premier à noter ce film !</a>
                            </g:if>
                            <g:if test="${session['currentUser'] != null && n == 0}">
                                Soyez le premier à noter ce film !
                            </g:if>
                            <g:if test="${session['currentUser'] != null && n != 0}">
                                <%
                                    average /= n
                                    java.math.MathContext mc = new MathContext(2)
                                    BigDecimal resAverage = new BigDecimal(average, mc)
                                %>
                                Il y a déjà ${n} utilisateur(s) ont noté sur ce film </br>
                                la note moyenne de ce film : ${resAverage} </br>
                            </g:if>
                            <% def existe = false, modify = false%>
                            <g:each var="m" in="${ItemUser?.list()}">
                                <g:if test="${m.rating != null && movieInstance?.imdbID == m.movie?.imdbID && session['currentUser'] == m?.user}" >
                                    Vous avez déjà noté pour ce film ! votre note : ${m.rating} / 10
                                </br>
                                    Vous voulez modifier votre note pour ce film ?
                                    <input type="hidden" name="rated" value="true" />
                                    <g:form controller="itemUser" action="showFormModify">
                                        <g:submitButton name="modifier" value="Modifier" />
                                    </g:form>

                                    <g:if test="${modify == true}">
                                        <g:form controller="itemUser" action="rateItem">
                                            <g:radio name="itemRating" id="note01" value="1" />
                                            <g:radio name="itemRating" id="note02" value="2" />
                                            <g:radio name="itemRating" id="note03" value="3" />
                                            <g:radio name="itemRating" id="note04" value="4" />
                                            <g:radio name="itemRating" id="note05" value="5" />
                                            <input type="hidden" name="rated" value="true" />
                                            <input type="hidden" name="movieId" value="${movieInstance?.imdbID}" />
                                            </br>
                                            <g:submitButton name="voter" value="Voter" />
                                        </g:form>
                                    </g:if>



                                    <% existe = true%>
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
