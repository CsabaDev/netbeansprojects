<%-- 
    Document   : authenticate
    Created on : Oct 9, 2017, 2:56:06 PM
    Author     : czine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, java.util.*"%>

<%!
    Connection con;
    String errorMsg = "Invalid username or password!";
    private boolean isValidUser(String userName, String password) {
        try {
            String sql = "select * from users where userName=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean checkValues(String userName, String password) {
        
        return false;
    }
%>

<%
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mastermind?zeroDateTimeBehavior=convertToNull", "root", "1234");
    application.setAttribute("connection", con);
    boolean authFlag = isValidUser(userName, password);
    if(authFlag) {
        session.setAttribute("user", userName);
        response.sendRedirect("index.jsp");
    } else {
        session.setAttribute("errorMessage", errorMsg);
        response.sendRedirect("login.jsp");
    }
%>

