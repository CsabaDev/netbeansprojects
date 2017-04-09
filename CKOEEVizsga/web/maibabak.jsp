<%-- 
    Document   : maibabak
    Created on : 2017.04.08., 23:00:52
    Author     : User
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ma született babák</h1>
        <jsp:useBean id="osszesBaba" scope="page" class="persistence.BabaJpaController" />
        <c:set var="maiBabaTable" scope="page" value="${osszesBaba.findMaiBabaEntities()}"/>
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
            <c:forEach items="${maiBabaTable}" var="item">
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
    </body>
</html>
