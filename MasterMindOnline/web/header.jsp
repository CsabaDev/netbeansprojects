<%-- 
    Document   : header
    Created on : Oct 6, 2017, 2:12:49 PM
    Author     : Czinéné Kertész Orsi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>header</title>
    </head>
    <body>
        <ul class="navigation" >
    <c:choose>
        <c:when test="${userName == null}">
            <li><a href="index.jsp">new game</a></li>
            <li><a href="hallOfFame.jsp">Hall of Fame</a></li>
            <li><a href="register.jsp">register</a></li>
            <li><a href="login.jsp">login</a></li>
        </c:when>
        <c:when test="${userName != null}">
            <li>Hello <c:out value="${userName}"></c:out>!</li>
            <li><a href="index.jsp">new game</a></li>
            <li><a href="hallOfFame.jsp">Hall of Fame</a></li>
            <li><a href="signout.jsp">sign out</a></li>
        </c:when>
    </c:choose>
        </ul>
    </body>
</html>
