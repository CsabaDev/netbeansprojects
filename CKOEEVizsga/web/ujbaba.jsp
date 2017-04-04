<%-- 
    Document   : index
    Created on : 2017.04.03., 11:20:26
    Author     : Czinéné Kertész Orsolya
--%>

<%@page import="javax.persistence.Persistence"%>
<%@page import="persistence.KorhazJpaController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Korhaz"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Új baba regisztrálása</h1>
        <form name="ujBaba" action="BabaRegisztralo">
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
                        <td><input type="text" name="ApaNev" value="" /></td>
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
                        <td>adószám</td>
                    </tr>
                    <tr>
                        <td>Város:</td>
                        <td><input type="text" name="varos" value="" /></td>
                    </tr>
                    <tr>
                        <td>Kórház:</td>
                        <td>
                            <select name="korhaz">
                            <%  KorhazJpaController korhazController = new KorhazJpaController(Persistence.createEntityManagerFactory("CKOEEVizsgaPU"));
                            List<Korhaz> korhazak = korhazController.findKorhazEntities();
                            for (int i = 0; i < korhazak.size(); i++) { %>
                                <option value="<%= korhazak.get(i) %>"><%= korhazak.get(i).getNev() %></option>
                            <%}%>
                            </select>
                        <td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Bevitel" name="bevitel" />
        </form>
        
    </body>
</html>
