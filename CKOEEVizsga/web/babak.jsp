<%-- 
    Document   : babak
    Created on : 2017.04.03., 11:25:26
    Author     : Czinéné Kertész Orsolya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <jsp:useBean id="ujBaba" scope="request" class="entities.Baba" />
        <jsp:setProperty name="ujBaba" property="*" />
    <p1>Új Baba neve: <jsp:getProperty name="ujBaba" property="nev" /></p1>
    <p1>Új Baba neme: <jsp:getProperty name="ujBaba" property="nem" /></p1>
    </body>
</html>
