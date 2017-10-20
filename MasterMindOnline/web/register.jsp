<%-- 
    Document   : register
    Created on : Oct 9, 2017, 2:07:54 PM
    Author     : Czinéné Kertész Orsi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="${pageContext.request.contextPath}/script/scripts.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Form</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <h1>Register</h1>
        <form name="registrationForm" action="RegisterServlet" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input name="userName" type="text"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input name="password" type="password" id="password"/></td>
                </tr>
                <tr>
                    <td>Password again:</td>
                    <td><input name="passwordagain" type="password" id="passwordagain" oninput="checkEquality()"/></td>
                    <td><div id="passwordsEquals" style="color: red">!!!</div></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input id="btnRegister" type="submit" name="btnRegister" value="Register" disabled="true"/></td>
                </tr>
            </table>
        </form>
        <p style="color: red"><c:out value="${sessionScope.errorMessage}" /></p>
        <c:remove var="errorMessage" scope="session" />
    </body>
</html>
