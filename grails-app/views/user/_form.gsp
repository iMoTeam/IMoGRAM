<%@ page import="ivvq.User" %>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
    <label for="lastName">
        <g:message code="user.lastName.label" default="Nom" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="lastName" required="" value="${userInstance?.lastName}"/>

</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
    <label for="firstName">
        <g:message code="user.firstName.label" default="Prénom" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="firstName" required="" value="${userInstance?.firstName}"/>

</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${userInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Pseudo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'Mot de Passe', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Mot de Passe" />
		<span class="required-indicator">*</span>
	</label>
	<g:passwordField name="password" required="" value="${userInstance?.password}"/>

</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'confirmPassword', 'error')} required">
    <label for="confirmPassword">
        <g:message code="user.password.label" default="Vérifier Mot de Passe" />
        <span class="required-indicator">*</span>
    </label>
    <g:passwordField name="confirmPassword" required=""/>

</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'profilePhoto', 'error')} ">
	<label for="profilePhoto">
		<g:message code="user.profilePhoto.label" default="Photo de Profil" />
		
	</label>
	<input type="file" id="profilePhoto" name="profilePhoto" />

</div>

