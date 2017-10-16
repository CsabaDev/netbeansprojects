<%-- 
    Document   : babak
    Created on : 2017.04.03., 15:20:20
    Author     : Czinéné Kertész Orsolya
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="javax.persistence.Persistence"%>
<%@page import="persistence.KorhazJpaController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Korhaz"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="scripts/AjaxUtil.js" type="text/javascript"></script>
        <script src="scripts/ajaxRequest.js" type="text/javascript"></script>
        <script src="scripts/Checking.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>Új baba</title>
    </head>
    <body onload="sendReq()">
        <% response.setCharacterEncoding("UTF-8"); %>
        <jsp:useBean id="ujBaba" scope="session" class="entities.Baba" />
        <h1>Új baba regisztrálása</h1>
        <form name="ujBaba" onsubmit="return nevCheck()" action="BabaRegisztralo" >
            <table border="1">
                <tbody>
                    <tr>
                        <td>Név:</td>
                        <td><input type="text" name="nev" value="" /></td>
                    </tr>
                    <tr>
                        <td>Anyja neve:</td>
                        <td><input type="text" name="anyaNev" value="" /></td>
                    </tr>
                    <tr>
                        <td>Apja neve:</td>
                        <td><input type="text" name="apaNev" value="" /></td>
                    </tr>
                    <tr>
                        <td>neme:</td>
                        <td>
                            <select name="nem">
                                <option value="1">férfi</option>
                                <option value="2">nő</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Adószám:</td>
                        <td><input type="text" name="adoszam" value="" readonly="readonly" id="adoszam" /></td>
                    </tr>
                    <tr>
                        <td>Város:</td>
                        <td><input type="text" name="varos" value="" /></td>
                    </tr>
                    <tr>
                        <td>Kórház:</td>
                        <td>
                            <select name="korhaz">
                                <jsp:useBean id="korhazak" scope="page" class="persistence.KorhazJpaController" />
                                <c:set var="korhazLista" scope="page" value="${korhazak.findKorhazEntities()}"/>
                                <c:forEach items="${korhazLista}" var="item">
                                    <option value="${item.id}">${item.nev}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Bevitel" name="bevitel" />
        </form>
        
    </body>
</html>
