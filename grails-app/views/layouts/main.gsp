<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'icon.ico')}" >
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<g:javascript library="application"/>		
		<r:layoutResources />
	</head>
	<body>
		<div id="grailsLogo" role="banner">
			<table>
				<tr>
					<td rowspan="2"><a href="./images/logo.jpg"><img src="${resource(dir: 'images', file: 'logo.jpg')}" alt="imo_logo"/></a></td>
					<td><a href="#" class="action-button shadow animate purple">Films</a></td>
					<td><a href="#" class="action-button shadow animate red">S&eacute;ries</a></td>
					<td><a href="#" class="action-button shadow animate yellow">Livres</a></td>
					<td><a href="${createLink(controller:'user', action:'loginUser')}" class="action-button shadow animate green">Connexion</a></td>
					<td><a href="${createLink(controller:'user', action:'create')}" class="action-button shadow animate green">Inscription</a></td>
				</tr>
				<tr>
					<td colspan="3">
                  <g:form controller="universal" action="doSearchAll" method="post" >
                      <fieldset class="form">
                        <input id="rechercher" type="text"  name="stringToSearch" />
                      </fieldset>
                    </td>
					<td>
                        <g:actionSubmit class="action-button shadow animate blue" controller="universal" action="doSearchAll" value="Valider"/>
                    </td>

                  </g:form>
					<td><a href="#" class="action-button shadow animate blue">&nbsp;&nbsp;&nbsp;&nbsp;Filtrer&nbsp;&nbsp;&nbsp;</a></td>
				</tr>
			</table>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<r:layoutResources />
	</body>
</html>
