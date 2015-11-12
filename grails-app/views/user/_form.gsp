<%@ page import="ivvq.User" %>
<table>
    <tr>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
    <td>
    <label for="lastName">
        <g:message code="user.lastName.label" default="Nom" />
        <span class="required-indicator">*</span>
    </label>
    </td>
    <td>
    <g:textField name="lastName" required="" value="${userInstance?.lastName}"/>
    </td>
</div>
    </tr>
    <tr>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
    <td>
    <label for="firstName">
        <g:message code="user.firstName.label" default="Prénom" />
        <span class="required-indicator">*</span>
    </label>
    </td>
    <td>
    <g:textField name="firstName" required="" value="${userInstance?.firstName}"/>
    </td>

</div>
    </tr>
    <tr>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
    <td>
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
    </td>
    <td>
	<g:field type="email" name="email" required="" value="${userInstance?.email}"/>
    </td>

</div>
    </tr>

<tr>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
    <td>
	<label for="username">
		<g:message code="user.username.label" default="Pseudo" />
		<span class="required-indicator">*</span>
	</label>
    </td>
    <td>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
    </td>

</div>
</tr>
    <tr>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'Mot de Passe', 'error')} required">
    <td>
	<label for="password">
		<g:message code="user.password.label" default="Mot de Passe" />
		<span class="required-indicator">*</span>
	</label>
    </td>
    <td>
	<g:passwordField name="password" required="" value="${userInstance?.password}"/>
    </td>
</div>
    </tr>
    <tr>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'confirmPassword', 'error')} required">
    <td>
    <label for="confirmPassword">
        <g:message code="user.password.label" default="Vérifier Mot de Passe" />
        <span class="required-indicator">*</span>
    </label>
    </td>
    <td>
    <g:passwordField name="confirmPassword" required=""/>
    </td>
</div>
    </tr>
    <tr>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'profilePhoto', 'error')} ">
    <td>
	<label for="profilePhoto">
		<g:message code="user.profilePhoto.label" default="Photo de Profil" />
		
	</label>
    </td>
    <td>
	<input type="file" id="profilePhoto" name="profilePhoto" />
    </td>

</div>
    </tr>

</table>