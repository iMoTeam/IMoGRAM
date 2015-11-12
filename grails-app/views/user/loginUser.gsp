<%--
  Created by IntelliJ IDEA.
  User: kilosakeyrocker
  Date: 02/10/15
  Time: 15:57
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ivvq.UserController" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Connexion</title>
</head>

<body>
<g:if test="${flash.error}">
    <div class="errors" role="alert alert-error" style="display: block">${flash.error}</div>
</g:if>
<h1 align="center" style="color: #1A4491">Saisissez vos informations </h1>
<g:form controller="user" action="loggedInUser" style="margin-left: 35%">
    <fieldset class="form">
        <table style="border-collapse: separate">
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
        <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
            <td>
            <label for="password">
                <g:message code="user.password.label" default="Mot de Passe" />
                <span class="required-indicator">*</span>
            </label>
            </td>
            <td>
            <g:passwordField name="password"  required="" value="${userInstance?.password}" />
            </td>
        </div>
            </tr>
            <tr>
        <div style="float: none">
            <td></td>
            <td>
            <g:submitButton name="Se Connecter" value="Valider" style="background-color: #999999"/>
            </td>
            <td></td>
        </div>
            </tr>
        </table>
    </fieldset>
</g:form>
</body>
</html>
