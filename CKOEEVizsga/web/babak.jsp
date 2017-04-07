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
    <jsp:useBean id="ujBaba" scope="session" class="entities.Baba" />
    <p>Új Baba neve: <jsp:getProperty name="ujBaba" property="nev" /></p>
    <p>Új Baba neme: <jsp:getProperty name="ujBaba" property="nem" /></p>
    <p>Új Baba anyja neve: <jsp:getProperty name="ujBaba" property="anyaNev" /></p>
    <p>Új Baba neme: <jsp:getProperty name="ujBaba" property="nem" /></p>
    <p>Új Baba neve: <jsp:getProperty name="ujBaba" property="nev" /></p>
    <p>Új Baba neme: <jsp:getProperty name="ujBaba" property="nem" /></p>
    </body>
</html>
