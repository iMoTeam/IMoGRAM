
<%@ page import="java.math.MathContext; ivvq.ItemUser; ivvq.Movie" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'movie.label', default: 'Movie')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
        <style>
        ul.notes-echelle {
            margin:0;
            padding:0;
            font:.75em/1.2 Arial, Helvetica, sans-serif;
        }
        ul.notes-echelle li {
            float:left;
            margin:0;
            padding:0;
            list-style:none;
            min-width:30px;
            min-height:30px;
        }

        /* Correctif IE6 sur min-width & min-height */
        * html ul.notes-echelle.js li {
            width:30px;
            height:30px;
        }

        ul.notes-echelle li label {
            display:block;
            text-align:center;
            line-height:20px;
            background:url(../images/etoiles.gif) center top no-repeat;
            cursor:pointer;
        }

        ul.notes-echelle li.note-off label {
            background-position:center -60px;
        }

        ul.notes-echelle.js input {
            position:absolute;
            left:-999%;
        }

        /* Effet lorsque une note est cochée */
        ul.notes-echelle li.note-checked {
            font-weight:bold;
        }
        /* Effet lorsque une note est tabulée au clavier */
        ul.notes-echelle.js li.note-focus {
            outline:1px dotted #000;
        }
        </style>
        <g:javascript>

            $("ul.notes-echelle").addClass("js");

            $("ul.notes-echelle li").addClass("note-off");

            $("ul.notes-echelle li").mouseover(function() {

                $(this).nextAll("li").addClass("note-off");

                $(this).prevAll("li").removeClass("note-off");

                $(this).removeClass("note-off");
            });

            $("ul.notes-echelle").mouseout(function() {

                $(this).children("li").addClass("note-off");

                $(this).find("li input:checked").parent("li").trigger("mouseover");
            });

            $("ul.notes-echelle input")

                    .focus(function() {

                        $(this).parent("li").nextAll("li").addClass("note-off");

                        $(this).parent("li").prevAll("li").removeClass("note-off");

                        $(this).parent("li").removeClass("note-off");
                    })

                    .blur(function() {

                        if($(this).parents("ul.notes-echelle").find("li input:checked").length == 0) {

                            $(this).parents("ul.notes-echelle").find("li").addClass("note-off");
                        }
                    });

            $("ul.notes-echelle input")

                    .focus(function() {

                        $(this).parents("ul.notes-echelle").find("li").removeClass("note-focus");

                        $(this).parent("li").addClass("note-focus");

                    })

                    .blur(function() {

                        $(this).parents("ul.notes-echelle").find("li").removeClass("note-focus");

                    })

                    .click(function() {

                        $(this).parents("ul.notes-echelle").find("li").removeClass("note-checked");

                        $(this).parent("li").addClass("note-checked");
                    });


            $("ul.notes-echelle input:checked").parent("li").trigger("mouseover");

            $("ul.notes-echelle input:checked").trigger("click");
        </g:javascript>
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
                                <a href="../../user/loginUser.gsp">Connectez-vous pour noter ce film !</a>
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
                            <% def existe = false%>

                            <g:each var="m" in="${ItemUser?.list()}">
                                <g:if test="${m.rating != null && movieInstance?.imdbID == m.movie?.imdbID && session['currentUser'] == m?.user}" >
                                    Vous avez déjà noté pour ce film ! votre note : ${m.rating} / 10
                                    </br>
                                    Voulez-vous modifier votre note pour ce film ?
                                    <input type="hidden" name="rated" value="true" />
                                    <g:form controller="itemUser" action="showFormModify">
                                        <input type="hidden" name="movieId" value="${movieInstance?.imdbID}" />
                                        <g:submitButton name="modifier" value="Modifier" />
                                    </g:form>
                                    <g:if test="${params.modify}">
                                        <g:form controller="itemUser" action="modifyItemRating">
                                            Veuillez cliquer sur un nombre pour noter !
                                            <ul class="notes-echelle">
                                                <li>
                                                    <label for="note01">1</label>
                                                    <g:radio name="modifyRating" id="note01" value="1" />
                                                </li>
                                                <li>
                                                    <label for="note02">2</label>
                                                    <g:radio name="modifyRating" id="note02" value="2" />
                                                </li>
                                                <li>
                                                    <label for="note03">3</label>
                                                    <g:radio name="modifyRating" id="note03" value="3" />
                                                </li>
                                                <li>
                                                    <label for="note04">4</label>
                                                    <g:radio name="modifyRating" id="note04" value="4" />
                                                </li>
                                                <li>
                                                    <label for="note05">5</label>
                                                    <g:radio name="modifyRating" id="note05" value="5" />
                                                </li>
                                                <li>
                                                    <label for="note06">6</label>
                                                    <g:radio name="modifyRating" id="note06" value="6" />
                                                </li>
                                                <li>
                                                    <label for="note07">7</label>
                                                    <g:radio name="modifyRating" id="note07" value="7" />
                                                </li>
                                                <li>
                                                    <label for="note08">8</label>
                                                    <g:radio name="modifyRating" id="note08" value="8" />
                                                </li>
                                                <li>
                                                    <label for="note09">9</label>
                                                    <g:radio name="modifyRating" id="note09" value="9" />
                                                </li>
                                                <li>
                                                    <label for="note10">10</label>
                                                    <g:radio name="modifyRating" id="note10" value="10" />
                                                </li>
                                            </ul>
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
                                    <ul class="radio-note">
                                        <li>
                                            <label for="note01">1</label>
                                            <g:radio name="modifyRating" id="note01" value="1" />
                                        </li>
                                        <li>
                                            <label for="note02">2</label>
                                            <g:radio name="modifyRating" id="note02" value="2" />
                                        </li>
                                        <li>
                                            <label for="note03">3</label>
                                            <g:radio name="modifyRating" id="note03" value="3" />
                                        </li>
                                        <li>
                                            <label for="note04">4</label>
                                            <g:radio name="modifyRating" id="note04" value="4" />
                                        </li>
                                        <li>
                                            <label for="note05">5</label>
                                            <g:radio name="modifyRating" id="note05" value="5" />
                                        </li>
                                        <li>
                                            <label for="note06">6</label>
                                            <g:radio name="modifyRating" id="note06" value="6" />
                                        </li>
                                        <li>
                                            <label for="note07">7</label>
                                            <g:radio name="modifyRating" id="note07" value="7" />
                                        </li>
                                        <li>
                                            <label for="note08">8</label>
                                            <g:radio name="modifyRating" id="note08" value="8" />
                                        </li>
                                        <li>
                                            <label for="note09">9</label>
                                            <g:radio name="modifyRating" id="note09" value="9" />
                                        </li>
                                        <li>
                                            <label for="note10">10</label>
                                            <g:radio name="modifyRating" id="note10" value="10" />
                                        </li>
                                    </ul>
                                    <input type="hidden" name="movieId" value="${movieInstance?.imdbID}" />
                                    </br>
                                    <g:submitButton name="voter" value="Voter" />
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
