
<%@ page import="java.math.MathContext; ivvq.BookController; ivvq.BookService; ivvq.Book; ivvq.ItemUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-book" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

                <table class="table">
                    <tr>

                        <g:if test="${bookInstance?.image}">
                            <td><img src="${bookInstance?.image}" alt="${bookInstance?.title}"></td>
                        </g:if>

                        <td>
                            <g:if test="${bookInstance?.title}">
                                   <h2><strong><g:fieldValue bean="${bookInstance}" field="title"/></strong></h2>
                            </g:if>

                            <g:if test="${bookInstance?.author}">
                                    <g:fieldValue bean="${bookInstance}" field="author"/>
                            </g:if>

                            <g:if test="${bookInstance?.pageCount}">
                                    <g:fieldValue bean="${bookInstance}" field="pageCount"/> pages.
                            </g:if>

                            <g:if test="${bookInstance?.publishedDate}">
                                    <g:fieldValue bean="${bookInstance}" field="publishedDate"/>
                            </g:if>
                        </td>
                        <td>
                            <% double average = 0
                            int n = 0 %>
                            <g:each var="m" in="${ItemUser?.list()}">
                                <g:if test="${session['currentUser'] != null && m.rating != null && bookInstance?.isbn13 == m.book?.isbn13}" >
                                    <%
                                        average += m.rating
                                        n += 1
                                    %>
                                </g:if>
                            </g:each>
                            <g:if test="${session['currentUser'] == null}">
                                <a href="../../user/loginUser.gsp"><h4>Connectez-vous pour noter ce livre !</h4></a>
                            </g:if>
                            <g:if test="${session['currentUser'] != null && n == 0}">
                                <h4>Soyez le premier à noter ce livre !</h4>
                            </g:if>
                            <g:if test="${session['currentUser'] != null && n != 0}">
                                <%
                                    average /= n
                                    java.math.MathContext mc = new MathContext(2)
                                    BigDecimal resAverage = new BigDecimal(average, mc)
                                %>
                                Il y a déjà <strong class="text-danger">${n}</strong> utilisateur(s) ont noté sur ce livre </br>
                                La note moyenne de ce livre est : <strong class="text-danger">${resAverage}</strong></br>
                            </g:if>
                            <% def existe = false%>

                            <g:each var="m" in="${ItemUser?.list()}">
                                <g:if test="${m.rating != null && bookInstance?.isbn13 == m.book?.isbn13 && session['currentUser'] == m?.user}" >
                                    Vous avez déjà noté pour ce livre ! votre note : <strong class="text-danger">${m.rating} / 10</strong>
                                    </br>
                                    Voulez-vous modifier votre note pour ce livre ?

                                    <g:form controller="itemUser" action="showFormModify">
                                        <input type="hidden" name="bookId" value="${bookInstance?.isbn13}" />
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

                                            <input type="hidden" name="bookId" value="${bookInstance?.isbn13}" />

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
                                    <input type="hidden" name="bookId" value="${bookInstance?.isbn13}" />

                                    </br>
                                    <g:submitButton class="btn btn-primary" name="voter" value="Voter" />
                                </g:form>
                            </g:if>
                        </td>
                    </tr>
                </table>
			
				<g:if test="${bookInstance?.isbn13}">
					<h2>ISBN13</h2>
						<span class="property-value" aria-labelledby="isbn13-label"><g:fieldValue bean="${bookInstance}" field="isbn13"/></span>
				</g:if>
			
				<g:if test="${bookInstance?.description}">
					<h2>Résumé</h2>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${bookInstance}" field="description"/></span>
				</g:if>

		</div>
	</body>
</html>
