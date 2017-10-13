<%-- 
    Document   : newgame
    Created on : Oct 6, 2017, 1:57:02 PM
    Author     : czine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, java.util.*, gamemodel.*"%>

<%!
    //Connection con;
    String errorMsg;
    GameModel game;
    //CodePeg[] code;
    int numberOfColors;
    int codeLength;
    int maxNumberOfGuesses;
    boolean colorsRepeatable;
    

%>

<%
    //String userName = (String)session.getAttribute("userName");
    numberOfColors = Integer.parseInt(request.getParameter("numberOfColors"));
    codeLength = Integer.parseInt(request.getParameter("codeLength"));
    maxNumberOfGuesses = Integer.parseInt(request.getParameter("maxNumberOfGuesses"));
    colorsRepeatable = (request.getParameter("colorsRepeatable").equals("true"));
    game = new GameModel(numberOfColors, codeLength, maxNumberOfGuesses, colorsRepeatable);
    
    //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mastermind?zeroDateTimeBehavior=convertToNull", "root", "1234");
    //application.setAttribute("connection", con);
    
    
    session.setAttribute("game", game);
    response.sendRedirect("game.jsp");
    
%>
