<%--
  Created by IntelliJ IDEA.
  User: kilosakeyrocker
  Date: 02/10/15
  Time: 15:57
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ivvq.User" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Inscription</title>
</head>

<body>
<h1 align="center" style="color: rgba(255, 239, 252, 0)">Saisissez vos informations </h1>
<g:form controller="UserController" action="create">
    <fieldset class="form">
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
            <label for="lastName">
                <g:message code="user.email.label" default="Nom" />
                <span class="required-indicator">*</span>
            </label>
            <g:textField name="lastName" required="" value="${userInstance?.lastName}"/>

        </div>
        <div class="fieldcontain">
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
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'profilePhoto', 'error')} ">
            <label for="profilePhoto">
                <g:message code="user.profilePhoto.label" default="Photo de Profil" />

            </label>
            <input type="file" id="profilePhoto" name="profilePhoto" />

        </div>
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
            <label for="motDePasse1">
                <g:message code="user.password.label" default="Mot de Passe" />
                <span class="required-indicator">*</span>
            </label>
            <g:textField name="motDePasse1" />
        </div>
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
            <label for="motDePasse2">
                <g:message code="user.password.label" default="Verifier Mot de Passe" />
                <span class="required-indicator">*</span>
            </label>
            <g:textField name="motDePasse2"/>
        </div>
        <br>
        <div style="float: none">
            <g:submitButton name="Valider" value="Valider" style="background-color: #999999"/>
        </div>
    </fieldset>
</g:form>
</body>
</html>
