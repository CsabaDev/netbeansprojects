<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/script/scripts.js"></script>
        <title>Login</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <div class="popup" id="popup" >
            <p id="msg"></p>
            <button id="popupOk" >OK</button>
            <p/>
        </div>
        <div class="main" >
        <h1>Login</h1>
        <form name="loginForm" class="formTable" action="LoginServlet" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input name="userName" type="text"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input name="password" type="password"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="btnRegister" value="Login"/></td>
                </tr>
            </table>
        </form>
        <p style="color: red"><c:out value="${sessionScope.errorMessage}" /></p>
        <c:remove var="errorMessage" scope="session" />
        </div>
    </body>
</html>
