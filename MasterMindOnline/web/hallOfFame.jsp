<%-- 
    Document   : hallOfFame
    Created on : Oct 21, 2017, 11:31:58 PM
    Author     : czine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*, gamemodel.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
        <script src="${pageContext.request.contextPath}/script/scripts.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
        <title>JSP Page</title>
    </head>
        <header>
        <%@include file="header.jsp" %>
    </header>
    <body onload="showHallOfFame(${userName})">
        <div class="main" >
            <h1>HALL OF FAME</h1>
            <table class="formTable">
                    <tr>
                        <td>Number of colors:</td>
                        <td id="numberOfColors">8</td>
                        <td><input class="slider" id="numberOfColorsSlider" name="numberOfColors" type="range" 
                                   oninput="refreshValue(this.name,this.value)" min="6" max="10" value="8"></td>
                    </tr>
                    <tr>
                        <td>Length of code:</td>
                        <td id="codeLength">4</td>
                        <td><input class="slider" id="codeLengthSlider" name="codeLength" type="range" 
                                   oninput="refreshValue(this.name,this.value)" min="4" max="8" value="4"></td>
                    </tr>
                    <tr>
                        <td>Colors repeatable:</td>
                        <td></td>
                        <td><input id="colorsRepeatable" type="checkbox" checked="true"></td>
                    </tr>
                    <tr>
                        <td>Only my best results:</td>
                        <td></td>
                        <td><input id="onlyMine" type="checkbox" ></td>
                    </tr>
                </table>
                <p><button onclick="showHallOfFame('${userName}')" id="show" >Show!</button></p>
            
            <table id="hallOfFameTable" class="hallOfFameTable">
                <thead>
                    <th>username</th>
                    <th>guesses</th>
                    <th>time</th>
                    <th>date</th>
                </thead>
                <tbody id="hallOfFameTableRows">
                    
                </tbody>
            </table>
        </div>
    </body>
    <footer>
        <%= Calendar.getInstance().getTime() %>
    </footer>
</html>
