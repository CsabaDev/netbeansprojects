<%-- 
    Document   : game
    Created on : Oct 12, 2017, 10:19:54 AM
    Author     : Czinéné Kertész Orsi
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
        <title>Playing MasterMind</title>
    </head>
    <header>
        <%@include file="header.jsp" %>
    </header>
    <body onload="finishIfEnded('${sessionScope.game.gameState.toString()}')">
        <div class="popup" id="popup" >
            <p id="msg"></p>
            <button id="popupOk" >OK</button>
        </div>
        <div class="main" >
        <p id="log" ></p>
        <table id="guesses" class="guessesTable" numberOfGuesses="${sessionScope.game.guessesUnmodifiable.size()}">
        <p id="log" >
        </p>
        <table id="guesses" class="guessesTable" 
               numberOfGuesses="${sessionScope.game.guessesUnmodifiable.size()}" >
            <c:if test="${game != null}">
            <c:forEach items="${game.guessesUnmodifiable}" var="guess" varStatus="guessNumber">
                <tr>
                    <c:forEach items="${guess}" var="guessPeg">
                        <td><div class="codePeg" 
                            style="background-color: <c:out value="${guessPeg.getColorName(guessPeg)}"/>">
                        </div></td>
                    </c:forEach>
                    <td>
                        <table><tr>
                            <c:forEach items="${game.evaluationsUnmodifiable.get(guessNumber.count - 1)}" var="resultPeg" varStatus="pegNumber">
                            <td><div class="resultPegDiv" 
                            style="background-color: <c:out value="${resultPeg.getResultColorName(resultPeg)}"/>">
                            </div></td>
                            <c:if test="${pegNumber.count == Math.floor((game.codeLength + 1) / 2)}" >
                            </tr><tr>
                            </c:if>
                        </c:forEach>
                        </tr>
                        </table>
                    </td>
                </tr>
            </c:forEach>
            </c:if>
        </table>
        <br/>
        <table class="guesserTable">
            <tr>
                <c:forEach items="${game.code}" var="guessPeg" varStatus="status">
                    <td><div class="guessPegDiv" 
                             style="background-color: gray"
                             id="<c:out value = "guessPeg${String.valueOf(status.count)}"/>" 
                             onclick="setCurrent(id)">
                    </div></td>
                </c:forEach>
                <td>
                    <button onclick="startEvaluate()" id="ok">OK</button>
                </td>
            </tr>    
            <tr>
                <c:forEach items="${game.code}" var="guessPeg" varStatus="status">
                    <td><div class="codePeg" 
                             style="background-color: <c:out value="${guessPeg.getColorName(guessPeg)}"/>"
                             id="<c:out value = "guessPeg${String.valueOf(status.count)}"/>" 
                             onclick="setCurrent(id)">
                    </div></td>
                </c:forEach>
                <td></td>
            </tr>
            <tr>
                <c:forEach items="${game.getColors()}" var="pickPeg" varStatus="status">
                    <td><div class="codePeg" 
                             style="background-color: <c:out value="${pickPeg.getColorName(pickPeg)}"/>" 
                             id="<c:out value = "pickPeg${String.valueOf(status.count)}"/>" 
                             onclick="refreshColor(id)">
                    </div></td>
                <c:if test="${status.count == Math.floor((game.numberOfColors + 1) / 2)}" >
            </tr><tr>
                </c:if>
                </c:forEach>
            </tr>
        </table>
        </div>
    </body>
    <footer>
        <%= Calendar.getInstance().getTime() %>
        
    </footer>
</html>
