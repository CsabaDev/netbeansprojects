<%-- 
    Document   : registration
    Created on : Oct 9, 2017, 2:07:54 PM
    Author     : czine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <h1>Login</h1>
        <form name="loginForm" action="authenticate.jsp">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input name="userName" type="text"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input name="password" type="password"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="btnRegister" value="Login"/></td>
                </tr>
            </table>
        </form>
        <p style="color: red"><c:out value="${sessionScope.errorMessage}" /></p>
        <c:remove var="errorMessage" scope="session" />
    </body>
</html>
