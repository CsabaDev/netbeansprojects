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
    <c:choose>
        <c:when test="${userName == null}">
            <a href="index.jsp">Start a new game as guest</a>,
             <a href="hallOfFame.jsp">see Hall of Fame</a>,
             <a href="register.jsp">register</a>
             or <a href="login.jsp">login</a>
        </c:when>
        <c:when test="${userName != null}">
            <a href="hallOfFame.jsp">See Hall of Fame</a>,
            <a href="signout.jsp">Sign out</a> or 
            <a href="index.jsp">Start a new game!</a>
        </c:when>
    </c:choose>
    </body>
</html>
