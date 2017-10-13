<%-- 
    Document   : game
    Created on : Oct 12, 2017, 10:19:54 AM
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
        <title>Playing MasterMind</title>
    </head>
    <header>
        <%@include file="header.jsp" %>
    </header>
    <body>
    
        <div class="codePeg" >
            
        </div>
        <table class="guessesTable">
            <c:if test="${game != null}">
            <c:forEach items="${game.guessesUnmodifiable}" var="guess">
                <tr>
                    <c:forEach items="${guess}" var="guessPeg">
                        <td><div class="codePeg">
                            <c:set var="background" value="${guessPeg.color}"></c:set>
                            
                        </div></td>
                        
                    </c:forEach>
                    <td>

                    </td>
                </tr>
            </c:forEach>
            </c:if>
        </table>
        <table class="guesserTable">
                <tr>
                    <c:forEach items="${game.code}" var="guessPeg" varStatus="status">
                        <td><div class="codePeg" 
                                 style="background-color: <c:out value="${guessPeg.getColorName(guessPeg)}"/>"
                                 id="<c:out value = "guessPeg${String.valueOf(status.count)}"/>" 
                                 onclick="setCurrent(id)">
                        </div></td>
                    </c:forEach>
                    <td>
                        <button>OK</button>
                    </td>
                </tr>
        </table>
        <table class="pickerTable">
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
    </body>
    <footer>
        <%= Calendar.getInstance().getTime() %>
    </footer>
</html>
