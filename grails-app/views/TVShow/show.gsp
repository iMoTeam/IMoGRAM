
<%@ page import="java.math.MathContext; ivvq.ItemUser; ivvq.TVShow" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'TVShow.label', default: 'TVShow')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-TVShow" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

				<table class="table">
					<tr>

						<g:if test="${TVShowInstance?.image}">
							<td><img src="${TVShowInstance.image}" alt="${TVShowInstance.title}"></td>
						</g:if>

						<td>
                            <g:if test="${TVShowInstance?.title}">
                                    <h1><strong><g:fieldValue bean="${TVShowInstance}" field="title"/></strong></h1>
                            </g:if>

                            <g:if test="${TVShowInstance?.network}">
                                <div class="row"><g:fieldValue bean="${TVShowInstance}" field="network"/></div>
                            </g:if>

                            <g:if test="${TVShowInstance?.runtime}">
                                <div class="row"><g:fieldValue bean="${TVShowInstance}" field="runtime"/> minutes.</div>
                            </g:if>

                            <g:if test="${TVShowInstance?.airedEpisodes}">
                                <div class="row"> <g:fieldValue bean="${TVShowInstance}" field="airedEpisodes"/> épisodes.</div>
                            </g:if>

                            <g:if test="${TVShowInstance?.releaseDate}">
                                <div class="row"><g:fieldValue bean="${TVShowInstance}" field="releaseDate"/></div>
                            </g:if>
						</td>
						<td>
							<% double average = 0
							int n = 0 %>
							<g:each var="m" in="${ItemUser?.list()}">
								<g:if test="${session['currentUser'] != null && m.rating != null && TVShowInstance?.imdbID == m.tvShow?.imdbID}" >
									<%
										average += m.rating
										n += 1
									%>
								</g:if>
							</g:each>
							<g:if test="${session['currentUser'] == null}">
								<a href="../../user/loginUser.gsp"><h4>Connectez-vous pour noter cette série !</h4></a>
							</g:if>
							<g:if test="${session['currentUser'] != null && n == 0}">
								<h4>Soyez le premier à noter cette série !</h4>
							</g:if>
							<g:if test="${session['currentUser'] != null && n != 0}">
								<%
									average /= n
									java.math.MathContext mc = new MathContext(2)
									BigDecimal resAverage = new BigDecimal(average, mc)
								%>
								Il y a déjà <strong class="text-danger">${n}</strong> utilisateur(s) ont noté sur cette série </br>
                                La note moyenne de cette série est : <strong class="text-danger">${resAverage}</strong></br>
							</g:if>
							<% def existe = false%>

							<g:each var="m" in="${ItemUser?.list()}">
								<g:if test="${m.rating != null && TVShowInstance?.imdbID == m.tvShow?.imdbID && session['currentUser'] == m?.user}" >
									Vous avez déjà noté pour cette série ! votre note : <strong class="text-danger">${m.rating} / 10</strong>
									</br>
                                    Voulez-vous modifier votre note pour cette série ?

									<g:form controller="itemUser" action="showFormModify">
										<input type="hidden" name="tvShowId" value="${TVShowInstance?.imdbID}" />
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

                                                    <input type="hidden" name="tvShowId" value="${TVShowInstance?.imdbID}" />

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

										<label for="note04">4</label>
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
									<input type="hidden" name="tvShowId" value="${TVShowInstance?.imdbID}" />

									</br>
									<g:submitButton class="btn btn-primary" name="voter" value="Voter" />
								</g:form>
							</g:if>
						</td>
					</tr>
				</table>
			
				<g:if test="${TVShowInstance?.overview}">
					<h2>Résumé</h2>
					
						<span class="property-value" aria-labelledby="overview-label"><g:fieldValue bean="${TVShowInstance}" field="overview"/></span>

				</g:if>
			
				<g:if test="${TVShowInstance?.genres}">
					<h2>Genre</h2>
						<g:each in="${TVShowInstance.genres}" var="g">
						<span class="property-value" aria-labelledby="genres-label"><g:link controller="arrayClass" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
				</g:if>


		</div>
	</body>
</html>
