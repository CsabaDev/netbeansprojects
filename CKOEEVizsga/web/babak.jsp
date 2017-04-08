<%-- 
    Document   : babak
    Created on : 2017.04.03., 11:25:26
    Author     : Czinéné Kertész Orsolya
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <jsp:useBean id="osszesBaba" scope="page" class="persistence.BabaJpaController" />
        
        <c:set var="babaTable" scope="page" value="${osszesBaba.findBabaEntities()}"/>
        <table border="1">
            <thead>
                <tr>
                    <th>Név</th>
                    <th>Anyja neve</th>
                    <th>Apja neve</th>
                    <th>Születési dátum</th>
                    <th>Kórház</th>
                </tr>
            </thead>
            <tbody>
        <c:forEach items="${babaTable}" var="item">
                <tr>
                    <td>${item.nev}</td>
                    <td>${item.anyaNev}</td>
                    <td>${item.apaNev}</td>
                    <td>${item.szulDatum}</td>
                    <td>${item.korhaz}</td>
                 
                  </tr>
        </c:forEach>
        
            </tbody>
        </table>

        
        
        
    <jsp:useBean id="ujBaba" scope="session" class="entities.Baba" />
    <p>Új Baba neve: <jsp:getProperty name="ujBaba" property="nev" /></p>
    <p>Új Baba neme: <jsp:getProperty name="ujBaba" property="nem" /></p>
    <p>Új Baba anyja neve: <jsp:getProperty name="ujBaba" property="anyaNev" /></p>
    <p>Új Baba neme: <jsp:getProperty name="ujBaba" property="nem" /></p>
    <p>Új Baba neve: <jsp:getProperty name="ujBaba" property="nev" /></p>
    <p>Új Baba neme: <jsp:getProperty name="ujBaba" property="nem" /></p>
    
    
    
    
    </body>
</html>
