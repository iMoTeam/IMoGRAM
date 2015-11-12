<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title>Inscription</title>
	</head>
	<body>
		<div id="create-user" class="content scaffold-create" role="main">
			<h1 align="center">Saisissez vos Informations:</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${userInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${userInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:userInstance, action:'save']"  enctype="multipart/form-data" style="margin-left: 35%">
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
                    <div style="float: left">
                        <g:submitButton name="Valider" value="Valider" style="background-color: #999999"/>
                    </div>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
