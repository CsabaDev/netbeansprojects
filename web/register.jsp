<%-- 
    Document   : register
    Created on : Oct 9, 2017, 2:56:06 PM
    Author     : czine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, java.util.*"%>

<%!
    Connection con;
    String errorMsg;
    private boolean createUser(String userName, String password) {
        if(userName.length() < 4) {
            errorMsg = "That username is too short.";
            return false;
        }
        if(password.length() < 4) {
            errorMsg = "That password is too short.";
            return false;
        }
        try {
            String sql = "select * from users where userName=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                errorMsg = "That username is taken.";
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            String sql = "insert into users values(null,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            int result = ps.executeUpdate();
            if(result > 0) {
                return true;
            } else {
                errorMsg = "Invalid username os password.";
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
        errorMsg = "Invalid username or password.";
        return false;
    }

%>

<%
    String userName = request.getParameter("userName");
    String password = request.getParameter("password");
    
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mastermind?zeroDateTimeBehavior=convertToNull", "root", "1234");
    application.setAttribute("connection", con);
    
    
    boolean authFlag = createUser(userName, password);
    if(authFlag) {
        response.sendRedirect("login.jsp");
    } else {
        session.setAttribute("errorMessage", errorMsg);
        response.sendRedirect("registration.jsp");
    }
%>

