<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
        <script src="${pageContext.request.contextPath}/script/scripts.js"></script>
        <title>Let's play MasterMind!</title>
    </head>
    <header>
        <%@include file="header.jsp" %>
    </header>
    <body>
        <div class="popup" id="popup" >
            <p id="msg"></p>
            <button id="popupOk" >OK</button>
            <p/>
        </div>
        <div class="main" >
        <c:out value="${sessionScope.errorMessage}" />
        <h1>Settings</h1>
        <form action="newGame">
        <table class="formTable">
            <tr>
                <td>Number of colors:</td>
                <td id="numberOfColors">8</td>
                <td><input class="slider" name="numberOfColors" type="range" oninput="refreshValue(this.name,this.value)" min="6" max="10" value="8"></td>
            </tr>
            <tr>
                <td>Length of code:</td>
                <td id="codeLength">4</td>
                <td><input class="slider" name="codeLength" type="range" oninput="refreshValue(this.name,this.value)" min="4" max="8" value="4"></td>
            </tr>
            <tr>
                <td>Max number of guesses:</td>
                <td id="maxNumberOfGuesses">20</td>
                <td><input class="slider" name="maxNumberOfGuesses" type="range" oninput="refreshValue(this.name,this.value)" min="12" max="60" value="20"></td>
            </tr>
            <tr>
                <td>Colors repeatable:</td>
                <td></td>
                <td><input name="colorsRepeatable" type="checkbox" checked="true"></td>
            </tr>
        </table>
            <p><input type="submit" value="Start!"></p>
        </form>
        </div>
    </body>
    <footer>
        <%= Calendar.getInstance().getTime() %>
    </footer>
</html>
