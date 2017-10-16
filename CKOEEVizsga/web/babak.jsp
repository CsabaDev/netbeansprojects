<%-- 
    Document   : babak
    Created on : 2017.04.03., 11:25:26
    Author     : Czinéné Kertész Orsolya
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regisztrált babák</title>
    </head>
    <body>
        <h1>Regisztrált babák</h1>
        <jsp:useBean id="osszesBaba" scope="page" class="persistence.BabaJpaController" />
        <c:set var="babaTable" scope="page" value="${osszesBaba.findBabaEntities()}"/>
        <table border="1">
            <thead>
                <tr>
                    <th>Név</th>
                    <th>Anyja neve</th>
                    <th>Apja neve</th>
                    <th>Születési dátum</th>
                    <th>Nem</th>
                    <th>Város</th>
                    <th>Kórház</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${babaTable}" var="item">
                <tr>
                    <td>${item.nev}</td>
                    <td>${item.anyaNev}</td>
                    <td>${item.apaNev}</td>
                    <td><fmt:formatDate value="${item.szulIdo}" pattern="YYYY-MM-dd"/></td>
                    <td>${item.nem}</td>
                    <td>${item.varos}</td>
                    <td>${item.korhaz.nev}</td>
                  </tr>
            </c:forEach>
            </tbody>
        </table>
    
        <a href="ujbaba.jsp">Új baba regisztrálása</a>
        <a href="index.jsp">Főoldal</a>
        
    </body>
</html>
