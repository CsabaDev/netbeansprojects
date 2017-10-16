<%-- 
    Document   : registration
    Created on : Oct 9, 2017, 2:07:54 PM
    Author     : Czinéné Kertész Orsi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h1>Registration</h1>
        <form name="registrationForm" action="register.jsp">
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
                    <td><input type="submit" name="btnRegister" value="Register"/></td>
                </tr>
            </table>
        </form>
        <p style="color: red"><c:out value="${sessionScope.errorMessage}" /></p>
        <c:remove var="errorMessage" scope="session" />
    </body>
</html>
