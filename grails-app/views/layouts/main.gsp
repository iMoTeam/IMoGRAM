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

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${resource(dir:"css/bootstrap",file:"bootstrap.min.css")}">
    <link rel="stylesheet" href="${resource(dir:"css/bootstrap",file:"bootstrap-theme.min.css")}">
    <!-- Latest compiled and minified JavaScript -->
    <script src="${resource(dir:"js",file:"jquery-1.11.3.min.js")}"></script>
    <script src="${resource(dir:"js/bootstrap",file:"bootstrap.min.js")}"></script>

    <style>
    html {
        background-color: #000000;
    }

    body {
        width: 87%;
        margin: auto;
    }

    .vertical-align {
        display: flex;
        align-items: center;
    }

    .banner {
        background-color: black;
    }
    </style>

	<g:layoutHead/>
	<g:javascript library="application"/>
	<r:layoutResources />
</head>
<body>
<div class="banner">
    <div class="row vertical-align">
        <div class="col-md-2">
            <a href="${createLink(uri: "/")}"><img src="${resource(dir: 'images', file: 'imogram.png')}" alt="imo_logo"/></a>
        </div>
        <div class="col-md-2">
            <a href="${createLink(controller:'universal', action:'doSearchMovies')}" class="btn btn-primary btn-lg btn-block"><span class="glyphicon glyphicon-film"></span><strong> Films</strong></a>
        </div>
        <div class="col-md-2">
            <a href="${createLink(controller:'universal', action:'doSearchTvShow')}" class="btn btn-danger btn-lg btn-block"><span class="glyphicon glyphicon-sound-dolby"></span><strong> S&eacute;ries</strong></a>
        </div>
        <div class="col-md-2">
            <a href="${createLink(controller:'universal', action:'doSearchBooks')}" class="btn btn-warning btn-lg btn-block"><span class="glyphicon glyphicon-book"></span><strong> Livres</strong></a>
        </div>
        <div class="col-md-1">
            <g:if test="${session["currentUser"] != null}">
                <a href="${createLink(controller:'user', action:'index')}" class="btn btn-success btn-lg">Profile</a>
            </g:if>
            <g:else>
                <a href="${createLink(controller:'user', action:'loginUser')}" class="btn btn-success btn-lg">Connexion</a>
            </g:else>
        </div>
        <div class="col-md-1">
            <a href="${createLink(controller:'user', action:'create')}" class="btn btn-success btn-lg">Inscription</a>
        </div>
    </div>
</div>

<div class="row text-primary text-center panel panel-primary">
    <h1><em>Nous sommes les garants de votre culture.</em></h1>
</div>
<div class="row">
    <div class="form-group col-lg-5 col-lg-offset-4">
        <g:form controller="universal" action="doSearchAll" method="post">
            <input id="rechercher" class="input-lg" type="text"  name="stringToSearch" placeholder="Tapez votre recherche..." />
            <g:actionSubmit class="btn btn-primary btn-lg" controller="universal" action="doSearchAll" value="Rechercher"/>
            <a href="#" class="btn btn-link">Recherche avancée</a>
        </g:form>
    </div>
</div>
<div></div>
<g:layoutBody/>
<div class="footer" role="contentinfo"></div>
<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<r:layoutResources />
</body>
</html>
